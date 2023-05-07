import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/showdonor")
public class Display_Servlet extends HttpServlet {
	
	private final static String query= "select "
			+ "name,email,bloodgroup,gender,dob,age,phone_number,address,state,city "
			+ "from bloodbank_reg_table ";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException {
	PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	try {
	Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e) {
	pw.println("<h2>"+e.toString()+"<h2>");
	e.printStackTrace();
	}
	String link= "jdbc:mysql://localhost:3306/bloodbank";
	String user="root";
	String pass="root";
	
	try( Connection con=DriverManager.getConnection(link,user,pass);
	PreparedStatement ps = con.prepareStatement(query);)
	{
		ResultSet rs =ps.executeQuery();
		pw.println("<header>");
		pw.println("<h1>Blood Bank</h1>");
		pw.println("<nav>");
		pw.println("<ul>");
		pw.println("<li><a href='Home_page.html'>Home</a></li>");
		pw.println("<li><a href='showdonor'>Donors</a></li>");
		pw.println("<li><a href='Aboutus_page.html'>About US</a></li>");
		pw.println("<li><a href='Contact_page.html'>Contact</a></li>");
	    pw.println("<li><a href='Login_page.html'>Login</a></li>");
	    pw.println("<li><a href'Signup_page.html'>Signup</a></li>");
	    pw.println("</ul>");
	    pw.println("</nav>");
	    pw.println("</header>");
	
	pw.println("<table>");
	pw.println("<tr>");
	pw.println("<th>Name</th>");
	pw.println("<th>E-mail </th>");
	pw.println("<th>Blood Group</th>");
	pw.println("<th>Gender</th>");
	pw.println("<th>Date Of Birth</th>");
	pw.println("<th>Age</th>");
	pw.println("<th>Moblie Number</th>");
	pw.println("<th>Address</th>");
	pw.println("<th>State</th>");
	pw.println("<th>City</th>");
	pw.println("</tr>");
	
	while(rs.next()) {
	pw.println("<tr>");
	pw.println("<td>"+rs.getString(1)+"</td>");
	pw.println("<td>"+rs.getString(2)+"</td>");
	pw.println("<td>"+rs.getString(3)+"</td>");
	pw.println("<td>"+rs.getString(4)+"</td>");
	pw.println("<td>"+rs.getDate(5)+"</td>");
	pw.println("<td>"+rs.getInt(6)+"</td>");	
	pw.println("<td>"+rs.getString(7)+"</td>");
	pw.println("<td>"+rs.getString(8)+"</td>");
	pw.println("<td>"+rs.getString(9)+"</td>");
	pw.println("<td>"+rs.getString(10)+"</td>");
	pw.println("</tr>");
	}
	pw.println("</table>");
	pw.println("<a href='Home_page.html'><button>Home</button></a>");
	} catch (SQLException sqle) {
	pw.println("<h2>"+sqle.getMessage()+"<h2>");
	sqle.printStackTrace();
	} catch(Exception e) {
	e.printStackTrace();
	}
	pw.close(); 
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException {
	doGet(req,res);
	}

}
