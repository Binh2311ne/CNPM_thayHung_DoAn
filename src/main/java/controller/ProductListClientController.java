package controller;

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

@WebServlet(urlPatterns = { "/product/list" })
public class ProductListClientController extends HttpServlet {
	ProductService productService = new ProductServiceImpl();
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> productList = productService.getAll();
		req.setAttribute("productList", productList);
		
		//Get session username
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
			User u=(User) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/view/product-list.jsp");
		dispatcher.forward(req, resp);
	}// c�i n�y sai

}
