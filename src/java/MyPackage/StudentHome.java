/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rohit
 */
public class StudentHome extends HttpServlet {

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
        String rollno = sess.getAttribute("rollno").toString();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/placement", "root", "root");
            PreparedStatement ps = con.prepareStatement("select * from Student where rollno=?");
            ps.setString(1, rollno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Date dob = rs.getDate("dob");
                String branch = rs.getString("branch");
                double cgpa = rs.getDouble("cgpa");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String school = rs.getString("SchoolBackLog");
                String college = rs.getString("CollegeBackLog");

                StudDetailContainer obj = new StudDetailContainer();
                obj.setName(name);
                obj.setRollno(rollno);
                obj.setDob(dob);
                obj.setBranch(branch);
                obj.setContact(contact);
                obj.setEmail(email);
                obj.setCgpa(cgpa);
                obj.setCollege(college);
                obj.setSchool(school);

                RequestDispatcher rd = request.getRequestDispatcher("StudentHome.jsp");
                request.setAttribute("obj", obj);
                rd.forward(request, response);
            } else {
                response.sendRedirect("Error.jsp");
            }
        } catch (ClassNotFoundException | SQLException | ServletException | IOException e) {

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
