import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class Register_servlet extends HttpServlet {
	
	private final static String query= "insert into bloodbank_reg_table "
			+ "(name,email, password,bloodgroup,gender,dob,age,phone_number,address,state,city) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
		PrintWriter pw=res.getWriter();
		
		res.setContentType("text/html");
		
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String bloodgroup = req.getParameter("bloodgroup");
		String gender = req.getParameter("gender");
		String dob = req.getParameter("dob");
		String age=req.getParameter("age");
		String phone_number = req.getParameter("phone_number");
		String address = req.getParameter("address");
		String state = req.getParameter("state");
		String city = req.getParameter("city");
		
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
		ps.setString(3, password);
		ps.setString(4, bloodgroup);
		ps.setString(5, gender);
		ps.setString(6, dob);
		ps.setString(7, age);
		ps.setString(8, phone_number);
		ps.setString(9, address);
		ps.setString(10, state);
		ps.setString(11, city);
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
