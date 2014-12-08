/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BpackPageBeans.DBOperations;
/**
 *
 * @author eric
 */
@WebServlet(name = "SaveBackpackPage", urlPatterns = {"/SaveBackpackPage"})
public class SaveBackpackPage extends HttpServlet {

    //variables to hold data that will be written to DB
    //for groupdetails table:
    private String names;
    private String groupID;
    
    //for backpack table:
    private String contents;
    
    
    /*
     * this will be called from backpack-App and will receive a JSON object containing the 
     * group details. It will update groupdetails name String and the backpack contents field'
     * with the information
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String contents)
            throws ServletException, IOException {
    
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
        
        groupID = request.getParameter("groupID");
        
        names = request.getParameter("names");
        
        contents = request.getParameter("packContents");

        DBOperations.updateIntoGroupDetails(Integer.parseInt(groupID), names);
        DBOperations.updateIntoBackpack(Integer.parseInt(groupID), contents);
        
        request.setAttribute("groupID", groupID);
        request.getRequestDispatcher("PopulateApp").forward(request,response);
    }

    
}
