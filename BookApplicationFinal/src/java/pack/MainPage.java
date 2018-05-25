/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hicran
 */
public class MainPage extends HttpServlet {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	  // static final String DB_URL = "jdbc:mysql://localhost:3306/bookapp"; 
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
	 
	   //  Database credentials 
    static final String USER = "root"; 
	   static final String PASS = ""; 
	   
	   Connection conn = null; 
	   Statement stmt = null;
	   PreparedStatement ps = null;
	   ResultSet rs = null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		String book_id = request.getParameter("book_id");
                Integer book_id_int=Integer.parseInt(book_id);
                
                String user_id = request.getParameter("user_id");
                Integer user_id_int=Integer.parseInt(user_id);
                
                try {       		
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        			 System.out.println("başarılı");
        		 } catch(ClassNotFoundException e) {
        			 System.out.println("Hata burda"+e);
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      System.out.println("Connecting to database..."); 
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
        	      //STEP 4: Execute a query 
        	      System.out.println("Creating statement..."); 
        	      stmt = conn.createStatement(); 
        	      
        	      //stmt.executeQuery("insert into customer(name,password) values('"+username2+"','"+password+"');");
        	      stmt.executeUpdate("insert into basket(user_id,book_id) values("+user_id_int+","+book_id_int+");");
        		
        	      conn.close();
        	      response.sendRedirect("/BookApplicationFinal/userPage.jsp");
        }
        catch(IOException | SQLException e) {
        		System.out.println(e);
        		System.out.println("Bağlantı yok");
       	}
                
                
			
	}


}
