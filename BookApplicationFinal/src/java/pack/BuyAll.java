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

/**
 *
 * @author Hicran
 */
public class BuyAll extends HttpServlet {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	  // static final String DB_URL = "jdbc:mysql://localhost:3306/bookapp"; 
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
	 
	   //  Database credentials 
    static final String USER = "root"; 
	   static final String PASS = ""; 
	   
	   Connection conn = null; 
	   Statement stmt4 = null;
           Statement stmt5 = null;
	   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String user_idForBuyAll = request.getParameter("user_idForBuyAll");
        
        int user_idForBuyAll_int = Integer.parseInt(user_idForBuyAll);
        
		try {
        		
        		 try{
        			 Class.forName("com.mysql.cj.jdbc.Driver");
        		 } catch(Exception e) {
        			 out.println("Hata burda");
        		 }
        		 
        	      //STEP 3: Open a connection 
        	      out.println("Connecting to database..."); 
        	      conn = DriverManager.getConnection(DB_URL,USER,PASS); 
        	 
                      String sql = "Select * from basket where user_id=?"; 
                      PreparedStatement psm = conn.prepareStatement(sql);
                      psm.setInt(1, user_idForBuyAll_int);
                                 
                      ResultSet rs = psm.executeQuery();
                        while(rs.next()){
                            String bookNameBasket=rs.getString("book_id");
                            
                            stmt4 = conn.createStatement(); 
                            stmt4.executeUpdate("insert into purchased(user_id,book_id) values("+user_idForBuyAll_int+","+bookNameBasket+");");
                            
                            stmt5 = conn.createStatement();        	      
                            stmt5.executeUpdate("delete from basket where user_id="+user_idForBuyAll_int+";");

                             
                        }
                        response.sendRedirect("/BookApplicationFinal/userInformation.jsp");
                        
            }catch( IOException | SQLException e){
                    System.out.print(e);
            }
    }

}
