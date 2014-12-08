/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPage;

import BpackPageBeans.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BpackPageBeans.*;
import java.sql.*;


/**
 *
 * @author eric
 */
@WebServlet(name = "NewBpackPage", urlPatterns = {"/NewBpackPage"})
public class NewBpackPage extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    //this will handle new account creation
    //get request Parameters, check server for existence of groupName
    //if group detected send error message to setupPage.jsp
    //group success send success message BackpackApp and flag as brand new group
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get parameters from setup page
        String group = request.getParameter("Group-Name").toString();
        String password = request.getParameter("Group-Password").toString();
        int groupSize = Integer.parseInt(request.getParameter("Group-Size"));
        int daysUntilTrip = Integer.parseInt(request.getParameter("Countdown"));
        
        //check for duplicate groupname and password
        if(DBOperations.selectFromLoginTable(group, password) != 0){
            //handle duplicate group found
            request.setAttribute("error", "DuplicateName");
            request.getRequestDispatcher("setupPage.jsp").forward(request,response);

        }else{
            //no duplicate groups get parameters to write group to login and groupdetails tables
            
            //for first write to DB only need to interact with group and login tables
            //write new login info to DB, groupID generated automatically
       
            //all backpackers unnamed for first write
            String tempNameList = "unnamed";
            for(int i=1;i<groupSize;i++){
                tempNameList += ", unnamed";
            }
            tempNameList += ";";
            //insert into login, generates groupID
            DBOperations.insertIntoLogin(group,password);

            //get newly issued groupID
            int groupID = DBOperations.selectFromLoginTable(group, password);
            //write new groupdetails to DB
            DBOperations.insertIntoGroupDetails(groupID, daysUntilTrip, groupSize, tempNameList);
            DBOperations.insertIntoBackpack(groupID, "");

            
            BackpackerGroup backpackerGroup = new BackpackerGroup(groupSize, daysUntilTrip);
            
            //store group in session
            request.setAttribute("groupID", groupID);
            request.setAttribute("groupInfo", backpackerGroup);
            
            //send request and response to jsp page
            String url = "/BackpackApp.jsp";
            request.getRequestDispatcher(url).forward(request,response);
        }
        
    }

    public boolean duplicateGroups(String user, String password){
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
        if(groupID == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public int insertIntoLogin(String group, String password){
        
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
         PreparedStatement ps = null;
        try{
            Connection connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
           "INSERT INTO login (name, password) VALUES (?,?)" );
            ps.setString(1,group);
            ps.setString(2, password);
            return ps.executeUpdate();
                
        }catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            };
            return 0;
        } finally{
            DBUtil.closePreparedStatement(ps);
            
        }
    }
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
