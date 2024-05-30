package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
//import tools.SendMail;
import util.Constant;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			resp.sendRedirect(req.getContextPath() + "/admin");
			return;
		}
		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = req.getSession(true);
					session.setAttribute("username", cookie.getValue());
					resp.sendRedirect(req.getContextPath() + "/admin");
					return;
				}
			}
		}

		req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String email = req.getParameter("email");
	    String address = req.getParameter("address");
	    String phone = req.getParameter("phone");
	    
	 
	    UserService service = new UserServiceImpl();
	    String alertMsg = "";
	 
	    if (service.checkExistEmail(email)) {
	        alertMsg = "Email đã tồn tại!";
	    } else if (service.checkExistUsername(username)) {
	        alertMsg = "Username đã tồn tại!";
	    } else {
	        boolean isSuccess = service.register(username, password, email, address, phone);

	        if (isSuccess) {
	            // Đăng ký thành công
	            alertMsg = "Đăng ký thành công!";
	            req.setAttribute("successMsg", alertMsg);
	            resp.sendRedirect(req.getContextPath() + "/login");
	            return;
	        } else {
	            alertMsg = "Lỗi hệ thống!";
	        }
	    }

	    req.setAttribute("alert", alertMsg);
	    req.getRequestDispatcher(Constant.Path.REGISTER).forward(req, resp);
	}

	
}
