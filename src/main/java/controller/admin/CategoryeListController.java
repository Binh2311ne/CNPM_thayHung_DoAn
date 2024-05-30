package controller.admin;

import java.io.IOException;
import java.util.List;

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

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryeListController extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object obj=session.getAttribute("account");
		User user=(User) obj;
		req.setAttribute("username", user.getUsername());
		
		CategoryService cateService = new CategoryServiceImpl();
		List<Category> cateList = cateService.getAll();
		req.setAttribute("catList", cateList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-category.jsp");
		dispatcher.forward(req, resp);
	}

}
