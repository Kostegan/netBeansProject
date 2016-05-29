/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import calc.CalcOperations;
import calc.OperationType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author homek
 */
public class CalcServlet extends HttpServlet {

    private ArrayList<String> listOperations = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CalcServlet</title>");
        out.println("</head>");
        out.println("<body>");

        try {

            double numberOne = Double.parseDouble(request.getParameter("numberOne"));
            double numberTwo = Double.parseDouble(request.getParameter("numberTwo"));
            String operation = String.valueOf(request.getParameter("operation"));

            HttpSession session = request.getSession(true);

            OperationType operationType = OperationType.valueOf(operation.toUpperCase());

            double result = calcResult(operationType, numberOne, numberTwo);

            if (session.isNew()) {
                listOperations.clear();
            }

//            else {
//                listOperations = (ArrayList) request.getAttribute("formula");
//            }
            listOperations.add(numberOne + " " + operationType.getStringValue() + " " + numberTwo + " = " + result);
            System.out.println("---------------------r-r-r-r-r-r-r-r-r-r-r--r-rr--r-r-rr-");
            session.setAttribute("formula", listOperations);

            out.println("<h1>Your session id is :" + session.getId() + "</h1>");
            out.println("<h1>Operation count is :" + listOperations.size() + "</h1>");

            for (String op : listOperations) {
                out.println("<h1>" + op + "</h1>");
            }
        } catch (Exception ex) {
            out.println("<h1>Error</h1>");
        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CalcServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CalcServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

    private double calcResult(OperationType operation, double one, double two) {
        double result = 0;
        switch (operation) {
            case ADD: {
                result = CalcOperations.add(one, two);
                break;
            }
            case SUBTRACT: {
                result = CalcOperations.subtract(one, two);
                break;
            }
            case MULTIPLY: {
                result = CalcOperations.multiply(one, two);
                break;
            }
            case DIVIDE: {
                result = CalcOperations.divide(one, two);
                break;
            }
        }
        return result;
    }

}
