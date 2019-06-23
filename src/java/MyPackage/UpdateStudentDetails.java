/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rohit
 */
public class UpdateStudentDetails extends HttpServlet {

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
        
        HttpSession sess=request.getSession(false);
        String rollno=sess.getAttribute("rollno").toString();
        
        double tenth=Double.parseDouble(request.getParameter("10th"));
        double twelvth=Double.parseDouble(request.getParameter("12th"));
        double s1=Double.parseDouble(request.getParameter("s1"));
        double s2=Double.parseDouble(request.getParameter("s2"));
        double s3=Double.parseDouble(request.getParameter("s3"));
        double s4=Double.parseDouble(request.getParameter("s4"));
        double s5=Double.parseDouble(request.getParameter("s5"));
        double s6=Double.parseDouble(request.getParameter("s6"));
        double s7=Double.parseDouble(request.getParameter("s7"));
        double s8=Double.parseDouble(request.getParameter("s8"));
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "root");
            PreparedStatement ps=con.prepareStatement("Update StudentMarks set 10th=?,12th=?,sem1=?,sem2=?,sem3=?,sem4=?,sem5=?,sem6=?,sem7=?,sem8=? where rollno=?");
            
            ps.setDouble(1, tenth);
            ps.setDouble(2, twelvth);
            ps.setDouble(3, s1);
            ps.setDouble(4, s2);
            ps.setDouble(5, s3);
            ps.setDouble(6, s4);
            ps.setDouble(7, s5);
            ps.setDouble(8, s6);
            ps.setDouble(9, s7);
            ps.setDouble(10, s8);
            ps.setString(11, rollno);
            
            ps.executeUpdate();
            
            response.sendRedirect("StudentIndex.jsp");
            
        }catch(ClassNotFoundException | SQLException | IOException e){
                response.sendRedirect("Error.jsp");
        }
        
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

}
