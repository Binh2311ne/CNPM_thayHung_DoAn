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

import model.Product;
import model.User;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = { "/admin/product/list" })
public class ProductListController extends HttpServlet {
	ProductService productService = new ProductServiceImpl();
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object obj=session.getAttribute("account");
		User user=(User) obj;
		req.setAttribute("username", user.getUsername());
		
		List<Product> proList = productService.getAll();
		req.setAttribute("proList", proList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-product.jsp");
		dispatcher.forward(req, resp);
	}

}
