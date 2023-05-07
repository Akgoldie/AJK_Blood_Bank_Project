package net.javas.login.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javas.login.file.loginfile;
import net.javas.login.database.logindao;

/**
 * Servlet implementation class login_servelt
 */
@WebServlet("/login_servelt")
public class login_servelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private logindao loginDao;

    public void init() {
        loginDao = new logindao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        loginfile logfile = new loginfile();
        logfile.setUsername(username);
        logfile.setPassword(password);

        try {
            if (loginDao.validate(logfile)) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
                response.sendRedirect("Home_page.html");
            } else {
                HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                response.sendRedirect("Wrong_login.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}