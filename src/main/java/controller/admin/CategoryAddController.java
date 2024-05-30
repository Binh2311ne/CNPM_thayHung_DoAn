package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.User;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object obj=session.getAttribute("account");
		User user=(User) obj;
		req.setAttribute("username", user.getUsername());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println(req);
		String name = req.getParameter("cate_name");
		System.out.println("***********");
		System.out.println(name);
		System.out.println("***********");
		Category category = new Category();
		CategoryService cateService = new CategoryServiceImpl();
		category.setName(name);
		cateService.insert(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");

	}
}
