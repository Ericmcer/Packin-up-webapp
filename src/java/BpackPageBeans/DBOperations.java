/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPageBeans;


import java.sql.*;
import BpackPageBeans.DBUtil;

/**
 *
 * @author eric
 * OPERATIONS NEEDED BY DB
 * select from login table with groupname, password - verify login, get groupID
 * insert into login table groupname, password -create new account
 * select from groupdetails with groupID - populate size and days fields, get names for backpack query
 * insert into groupDetails groupID, days, size and names(null if empty)
 * select from backpack using groupID and name - get contents 
 * update into backpack using groupID and name - update contents
 */


public class DBOperations {
    
    //will return 0 if no group found or groupID if found
    public static int selectFromLoginTable(String groupname, String password){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        int groupID;
        PreparedStatement ps = null;
        ResultSet groupResult = null;
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "SELECT * from login "+
            "WHERE name = ? AND password = ?");
            ps.setString(1, groupname);
            ps.setString(2, password);
            groupResult = ps.executeQuery();
            
            if(groupResult.next()){
                groupID = groupResult.getInt("groupID");
            }else{
                groupID = 0;
            }
         
        }catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            }
            groupID = 0;
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(groupResult);
            DBUtil.closeConnection(connection);
        }
        return groupID;
    }
    
    //inserts group and password into login table generates new ID
    public static int insertIntoLogin(String group, String password){
        
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
         Connection connection = null;
        try{
            connection = DriverManager.getConnection(dbURL, username, pwd);
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
            DBUtil.closeConnection(connection);
        }
    }
    
    //will return BackpackerGroup populated with days, size and backpacker[] attributes
    //groupID was already taken from selectFromLogin so after calling this and selectLogin
    //you will have groupID, size, days, and a string to parse of all names
    public static BackpackerGroup selectFromGroupDetails(int groupID, String contents){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        ResultSet groupResult = null;
        Connection connection = null;
        BackpackerGroup backpackerGroup = null; 
        try{
            connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "SELECT * from groupdetails "+
            "WHERE groupID = ?");
            ps.setInt(1, groupID);
            groupResult = ps.executeQuery();
            
           if(groupResult.next()){
                backpackerGroup = new BackpackerGroup(groupResult.getInt("size"), groupResult.getInt("days"));
                backpackerGroup.setNameList(groupResult.getString("names"));
                backpackerGroup.setGroup(contents);
            }else{
               backpackerGroup = new BackpackerGroup(1,1);
               backpackerGroup.setNameList("DB Error");
           }
        }catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(groupResult);
        }
        return backpackerGroup;
    }
    
    //will manually pass in groupID as this will only be run once per group
    //this should be called after new groupID is accepted with info from group. 
    //It should also be called when an old group is saved
    //if this returns -1 something is wrong, it should return 1 as one row was manipulated
    public static int insertIntoGroupDetails(int groupID, int days, int size, String names){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        Connection connection = null;
        try{
             connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "INSERT INTO groupdetails (groupID, names, days, size) " + 
            "VALUES (?,?,?,?)");
            ps.setInt(1,groupID);
            ps.setString(2,names);
            ps.setInt(3,days);
            ps.setInt(4, size);
            return ps.executeUpdate();
        
        }catch (SQLException e){
            
           for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
        return -1;
    }
    
    public static int insertIntoBackpack(int groupID, String contents){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        Connection connection = null;
        try{
             connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "INSERT INTO backpack (groupID, contents) " + 
            "VALUES (?,?)");
            ps.setInt(1,groupID);
            ps.setString(2,contents);
            return ps.executeUpdate();
        
        }catch (SQLException e){
            
           for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
        return -1;
    }
    
    public static int updateIntoGroupDetails(int groupID, String names){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        Connection connection = null;
        try{
             connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "update groupdetails set names = ? where groupID = ?");
            ps.setString(1,names);
            ps.setInt(2,groupID);
            return ps.executeUpdate();
        
        }catch (SQLException e){
            
           for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
        return -1;
    }
    
    public static int updateIntoBackpack(int groupID, String contents){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        Connection connection = null;
        try{
             connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "update backpack set contents = ? where groupID = ?");
            ps.setString(1,contents);
            ps.setInt(2,groupID);
            return ps.executeUpdate();
        
        }catch (SQLException e){
            
           for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
        }
        return -1;
    }
    
    public static String selectFromBackpacks(int groupID){
        String dbURL = "jdbc:mysql://localhost:3306/backpackers";
        String username = "root";
        String pwd = "puzzle";
        PreparedStatement ps = null;
        ResultSet groupResult = null;
        Connection connection = null;
        String contentString = "error";
        try{
            connection = DriverManager.getConnection(dbURL, username, pwd);
            ps = connection.prepareStatement(
            "SELECT * from backpack " +
            "WHERE groupID = ?");
            ps.setInt(1, groupID);
            groupResult = ps.executeQuery();
            if(groupResult.next()){
                contentString = groupResult.getString("contents");
            }else{
                contentString = "DB Error: no Items found";
            }
        }catch(SQLException e){
            for(Throwable t : e){
                t.printStackTrace();
            };
        }finally{
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(groupResult);
        }
        return contentString;
    }

}
