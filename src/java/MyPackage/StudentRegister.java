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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rohit
 */
public class StudentRegister extends HttpServlet {

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
        
        String rollno = request.getParameter("rollno");
        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        double cgpa = Double.parseDouble(request.getParameter("cgpa"));
        String passwd = request.getParameter("passwd");
        String date = request.getParameter("dob");
        
        String school=request.getParameter("school");
        String college=request.getParameter("college");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            
            java.util.Date dt1 = sdf.parse(date);
            long ms = dt1.getTime();
            java.sql.Date dob = new java.sql.Date(ms);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "root");
            PreparedStatement ps = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, rollno);
            ps.setString(2, passwd);
            ps.setString(3, name);
            ps.setDate(4, dob);
            ps.setString(5, branch);
            ps.setDouble(6, cgpa);
            ps.setString(7, contact);
            ps.setString(8, email);
            ps.setString(9, school);
            ps.setString(10, college);
            ps.executeUpdate();
            
            ps=con.prepareStatement("insert into StudentMarks(rollno) values(?)");
            ps.setString(1, rollno);
            ps.executeUpdate();

            response.sendRedirect("StudentLogin.jsp");

        } catch (ParseException | ClassNotFoundException | SQLException | IOException e) {
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
