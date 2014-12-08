/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BpackPageBeans.DBOperations;
/**
 *
 * @author eric
 */
public class BpackPage extends HttpServlet {

   

    
    
    @Override
    //only doGet request is for a static page so it just sends it 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //land at group setup JSP 
        request.getRequestDispatcher("setupPage.jsp").forward(request,response);
        
    }

  
    @Override
    //will route to BackpackApp.jsp if login data is correct,
    //and will route back to mainPage.jsp with a generated error message if input is wrong
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String user = request.getParameter("Group-Name");
        String password = request.getParameter("Group-Password");
     
        System.out.println(user + "," + password);
        //check DB for groupname and password, if 0 it was not found
        int groupID = DBOperations.selectFromLoginTable(user, password);
        
        if(groupID == 0){
            
            request.setAttribute("error", "WrongName");
            request.getRequestDispatcher("mainPage.jsp").forward(request,response);
        
        }else if(groupID != 0){
            
            request.setAttribute("groupID", groupID);
            request.getRequestDispatcher("PopulateApp").forward(request,response);
        
        }
        
    }
    //this can be modified later to represent actual checking of userData
    private int testLogin(String user, String password){
       
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        int groupID;
        try{
            Connection connection = DriverManager.getConnection(dbURL, username, pwd);
            PreparedStatement ps = connection.prepareStatement(
            "SELECT * from login "+
            "WHERE name = ? AND password = ?");
            ps.setString(1, user);
            ps.setString(2, password);
            ResultSet groupResult = ps.executeQuery();
            
            if(groupResult.next()){
                 groupID = groupResult.getInt("groupID");
            }else{
                groupID = 0;
            }
           
            ps.close();
            groupResult.close();
        }catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            }
            groupID = 0;
        }
        return groupID;
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
