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
import BpackPageBeans.Backpacker;
import BpackPageBeans.BackpackerGroup;
import BpackPageBeans.DBOperations;

/**
 *
 * @author eric
 */
@WebServlet(name = "PopulateApp", urlPatterns = {"/PopulateApp"})
public class PopulateApp extends HttpServlet {

    private int groupID;
    private String contents;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
    
    //Alot happens here
    /*This page triggered by a successful login from main page or update from SaveBackpackPage
    * will receive groupID in request object and use it to query DB for group details:
    * names, days, size, backpack contents. It will then populate a backpackergroup object with
    * these details and pass it to backpackApp.jsp
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        groupID = Integer.parseInt(request.getAttribute("groupID").toString());
        //need to read following info from dbs:
        //groupdetails :names, days, size;
        //backpack: contents;
        
        //the selectFromGroupDetails operation returns a backpackergroup with days and size and
        //with backpacker array already populated with names
        contents = DBOperations.selectFromBackpacks(groupID);

        BackpackerGroup backpackerGroup = DBOperations.selectFromGroupDetails(groupID, contents);
        //returns string of contents
        
        request.setAttribute("groupID", groupID);
        request.setAttribute("groupInfo", backpackerGroup);
        //send request and response to jsp page
        String url = "/BackpackApp.jsp";
        request.getRequestDispatcher(url).forward(request,response);
        
        
    }

   
}
