package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartItem;

@WebServlet(urlPatterns = { "/member/cart/remove" }) // ?pId=?123
public class CartItemRemove extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("cart");// Doc tu Session ra
		String pId = req.getParameter("pId");
		String cancelOrder = req.getParameter("cancel");
		if (obj != null) {
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) obj; // ep ve kieu cua no
//			// Xoa san pham trong map
//			map.remove(Integer.parseInt(pId));
//
//			// Cap nhat lai Session
//			httpSession.setAttribute("cart", map);
		
			if (cancelOrder != null && cancelOrder.equals("true")) {
	            // Xóa sản phẩm khỏi giỏ hàng
	            map.remove(Integer.parseInt(pId));
	            httpSession.setAttribute("cart", map);
	        }
	    }

	    // Xóa khỏi danh sách đã mua (nếu đã mua)
	    List<CartItem> purchasedItems = (List<CartItem>) httpSession.getAttribute("purchasedItems");
	    if (purchasedItems != null) {
	        // Xác định cách loại bỏ phần tử có id sản phẩm là pId từ danh sách purchasedItems
	        purchasedItems.removeIf(item -> item.getProduct().getId() == Integer.parseInt(pId));
	        // Cập nhật lại session
	        httpSession.setAttribute("purchasedItems", purchasedItems);
	    
		}
		resp.sendRedirect(req.getContextPath() + "/member/cart");
	}
}
