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
public class InsertingBook extends HttpServlet {
    
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
		
        String book_name=request.getParameter("bookName");
        String book_author=request.getParameter("bookAuthor");       
        String book_price=request.getParameter("bookPrice");
        int price_int = Integer.parseInt(book_price);
        
        try {       		
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        			 System.out.println("başarılı");
        		 } catch(Exception e) {
        			 System.out.println("Hata burda");
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      System.out.println("Connecting to database..."); 
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
        	      //STEP 4: Execute a query 
        	      System.out.println("Creating statement..."); 
        	      stmt = conn.createStatement(); 
        	      
        	      //stmt.executeQuery("insert into customer(name,password) values('"+username2+"','"+password+"');");
        	      stmt.executeUpdate("insert into book(book_name,author,price) values('"+book_name+"','"+book_author+"',"+price_int+");");
        		
        	      conn.close();
        	      response.sendRedirect("/BookApplicationFinal/adminPage.jsp");
        	}
        	catch(IOException | SQLException e) {
        		System.out.println(e);
        		System.out.println("Bağlantı yok");
        	}
        
                	
    }

}
