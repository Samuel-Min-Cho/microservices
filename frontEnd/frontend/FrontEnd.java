/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontEnd.frontend;

import frontEnd.business.Business;
import frontEnd.helper.Event;
import frontEnd.helper.EventsXML;
import frontEnd.helper.Friend;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    Authenticate autho;

    public FrontEnd() {
        autho = new Authenticate();
    }
    private final String authenticationCookieName = "login_token";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";

        System.out.println("TOKEN IS");
        try {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        } catch (Exception e) {

        }
        if (!token.isEmpty())
           try {
            if (this.autho.verify(token).getKey()) {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>(token, this.autho.verify(token).getValue());
                return entry;

            } else {
                Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
                return entry;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map.Entry entry = new AbstractMap.SimpleEntry<String, String>("", "");
        return entry;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = isAuthenticated(request).getKey();
        String uname = isAuthenticated(request).getValue();
        String hiddenParam = request.getParameter("pageName");
        String query = " ";
        switch (hiddenParam) {
            case "login":
                String username = request.getParameter("username");
                String passwrod = request.getParameter("passwrod");
                boolean isAuthenticated = Business.isAuthenticated(username, passwrod);
                if (isAuthenticated) {
                    request.setAttribute("username", username);
                    token = autho.createJWT("FrontEnd", username, 100000);

                    Cookie newCookie = new Cookie(authenticationCookieName, token);
                    response.addCookie(newCookie);
                    RequestDispatcher requestDispatcher = request.
                            getRequestDispatcher("frontpageWithLogin.jsp");

                    requestDispatcher.forward(request, response);

                }
                break;

            case "search":
                EventsXML result;
                boolean invite;
                query = request.getParameter("query");
                System.out.println("QUERY : " + query);
                if (token.isEmpty()) {
                    result = retreiveServicesFromBackend(query, null, hiddenParam);
                    request.setAttribute("eventResults", result);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithoutLogin.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                } else {
                    request.setAttribute("username", uname);
                    result = retreiveServicesFromBackend(query, token, hiddenParam);
                    request.setAttribute("eventResults", result);
                    EventsXML events = result;
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
                
            case "invite":
                String code = request.getParameter("code");
                String friendName = request.getParameter("friendName");
//                System.out.println("Query in Invite : " + query);
//                request.setAttribute("queryP", query);       
                System.out.println("INVITE:" + code + "," + friendName);
                String update = Business.invitePost(code, friendName);
                request.setAttribute("update", update);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
                requestDispatcher.forward(request, response);    
                break;
        }

    }

    private void searchPhase() {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private EventsXML retreiveServicesFromBackend(String query, String token, String hiddenParam) {
        try {
            return (Business.getServices(query, token, hiddenParam));
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return (null);
        }

    }

}
