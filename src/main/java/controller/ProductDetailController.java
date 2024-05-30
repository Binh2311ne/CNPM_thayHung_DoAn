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

import model.Category;
import model.Product;
import model.User;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

@WebServlet(urlPatterns="/product/detail")
public class ProductDetailController extends HttpServlet {
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				
		String id = req.getParameter("id");
		Product product = productService.get(Integer.parseInt(id));
		List<Category> categories = categoryService.getAll();

		req.setAttribute("categories", categories);

		req.setAttribute("product", product);
		
		//Get session username
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
			User u=(User) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			}
		
		 // Validate quantity only if it's provided
        String quantityString = req.getParameter("quantity");

        if (quantityString != null && !quantityString.isEmpty()) {
            int quantity = 0;

            try {
                quantity = Integer.parseInt(quantityString);

                if (quantity <= 0) {
                    req.setAttribute("quantityError", "Quantity should be greater than 0");
                    RequestDispatcher errorDispatcher = req.getRequestDispatcher("/view/client/view/product-detail.jsp");
                    errorDispatcher.forward(req, resp);
                    return;
                }
            } catch (NumberFormatException e) {
                req.setAttribute("quantityError", "Invalid quantity format");
                RequestDispatcher errorDispatcher = req.getRequestDispatcher("/view/client/view/product-detail.jsp");
                errorDispatcher.forward(req, resp);
                return;
            }
        }

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/view/product-detail.jsp");
		dispatcher.forward(req, resp);
	}

}
