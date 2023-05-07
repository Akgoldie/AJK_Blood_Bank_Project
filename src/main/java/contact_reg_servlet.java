

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class contact_reg_servlet
 */
@WebServlet("/contact_reg_servlet")
public class contact_reg_servlet extends HttpServlet {
	private final static String query= "insert into contact_table "
			+ "(name,email, subject,message) "
			+ "values (?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter pw=res.getWriter();
		
		res.setContentType("text/html");
		
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
		pw.println("<h2>"+e.toString()+"<h2>");
		e.printStackTrace();
		}
		
		String link= "jdbc:mysql://localhost:3306/bloodbank";
		String user="root";
		String pass="root";
		
		/*try( Connection 
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","root")
		;
		PreparedStatement ps = con.prepareStatement(query);)
		{*/
		
		try( Connection con=DriverManager.getConnection(link,user,pass);
		PreparedStatement ps = con.prepareStatement(query);)
		{
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, subject);
		ps.setString(4, message);
		int count = ps.executeUpdate();
		
		
		if(count==1) {
			res.sendRedirect("Home_page.html");
		} else {
			res.sendRedirect("Wrong_input.html");
		}
		} 
		
		catch (SQLException sqle) {
		pw.println("<h2>"+sqle.getMessage()+"<h2>");
		sqle.printStackTrace();
		} 
		
		catch(Exception e) {
		e.printStackTrace();
		}
		pw.close();

		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		doGet(req, res);
	}
}
