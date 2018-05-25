
package pack;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hicran
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
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
	System.out.print("inşAllah burayı görüyosundur");
        String userName1=request.getParameter("userName1");
        String password1=request.getParameter("password1");
        
        String userName2=request.getParameter("userName2");
        String password2=request.getParameter("password2");
        
        String submitType=request.getParameter("submit");      
        
        String dbName=null;
        String dbPassword=null;
        
        if(submitType.equals("register")) {
        	try {
        		System.out.println(" registerdan merhaba");
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        			 System.out.println("başarılı");
        		 } catch(Exception e) {
        			 System.out.println("Hata burda");
        		 }
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
        	      //STEP 4: Execute a query 
        	      System.out.println("Creating statement..."); 
        	      stmt = conn.createStatement(); 
        	      
        	      //stmt.executeQuery("insert into customer(name,password) values('"+username2+"','"+password+"');");
        	      stmt.executeUpdate("insert into customer(name,password) values('"+userName2+"','"+password2+"');");
        		
        	      conn.close();
        	      response.sendRedirect("/BookApplicationFinal/");
        	}
        	catch(IOException | SQLException e) {
        		System.out.println(e);
        		System.out.println("Bağlantı yok");
        	}
	}if(submitType.equals("login")) {
         
            try {
        		out.println(" registerdan merhaba");
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        			 System.out.println("başarılı");
        		 } catch(Exception e) {
        			 out.println("Hata burda");
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      out.println("Connecting to database..."); 
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
                      String sql = "Select * from customer where name=? and password=?"; 
                      PreparedStatement psm = conn.prepareStatement(sql);
                      psm.setString(1, userName1);
                      psm.setString(2, password1);
            
                      ResultSet rs = psm.executeQuery();
                        if(password1.equals("1234") && userName1.equals("admin")){
                                response.sendRedirect("/BookApplicationFinal/adminPage.jsp");
                                
                            }
                        else if(rs.next()){
                                HttpSession recordUserData = request.getSession(true);
                                recordUserData.setAttribute("user_name", userName1);
                                recordUserData.setAttribute("user_id", rs.getString("userid"));
                                
                                response.sendRedirect("/BookApplicationFinal/userPage.jsp");
                        }
                        else {
                            response.sendRedirect("/BookApplicationFinal/");
                        } 

        	}
        	catch(IOException | SQLException e) {
        		System.out.println(e);
        		System.out.println("Bağlantı yok");
        	}
        
        }
        else{
    
    
        }

 }  

}
