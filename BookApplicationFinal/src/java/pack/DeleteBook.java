package pack;

import java.io.IOException;
import static java.lang.System.out;
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
public class DeleteBook extends HttpServlet {
    
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
        String id = request.getParameter("id");
        
        int id_int = Integer.parseInt(id);
        
		try {
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        		 } catch(Exception e) {
        			 out.println("Hata burda");
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      out.println("Connecting to database..."); 
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
                      String sql = "Select * from book where book_id=?"; 
                      PreparedStatement psm = conn.prepareStatement(sql);
                      psm.setInt(1, id_int);
                      
            
                      ResultSet rs = psm.executeQuery();
                        if(rs.next()){
                            
                            stmt = conn.createStatement();        	      
                            stmt.executeUpdate("delete from book where book_id="+id_int+";");

                            response.sendRedirect("/BookApplicationFinal/adminPage.jsp");
                        }
                        else {
                            response.sendRedirect("/BookApplicationFinal/adminPage.jsp");
                        }
		
		
            }catch( IOException | SQLException e){
                    System.out.print(e);
            }
    }

}
