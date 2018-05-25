/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hicran
 */
public class Destroy extends HttpServlet {
    
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_idForDestroy= request.getParameter("user_idForDestroy");
                Integer user_idForDestroy_int=Integer.parseInt(user_idForDestroy);
        
            try {
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        		 } catch(Exception e) {
        			 System.out.println("Hata burda");
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
                      String sql = "Select * from basket where user_id=?"; 
                      PreparedStatement psm5 = conn.prepareStatement(sql);
                      psm5.setInt(1, user_idForDestroy_int);
                      
            
                      ResultSet rs = psm5.executeQuery();
                        if(rs.next()){
                            
                            stmt = conn.createStatement();        	      
                            stmt.executeUpdate("delete from basket where user_id="+user_idForDestroy+";");

                            response.sendRedirect("/BookApplicationFinal/userPage.jsp");
                        }
                        else {
                            response.sendRedirect("/BookApplicationFinal/userPage.jsp");
                        }
		
		
            }catch( IOException | SQLException e){
                    System.out.print(e);
            }
        
		
                
                
                
		/*HttpSession session = request.getSession(false);
		session.invalidate(); 
		if (session != null) {
			response.sendRedirect("/BookApplicationFinal/userPage.jsp");
		}*/
    }

}
