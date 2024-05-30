package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartItem;
import model.User;
import service.CartItemService;
import service.CartService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.CartServiceItemImpl;
import service.impl.UserServiceImpl;
import util.RandomUUID;

@WebServlet(urlPatterns = "/member/order")
public class OrderController extends HttpServlet {
	UserService userService = new UserServiceImpl();
	CartService cartService = new CartServiceImpl();
	CartItemService cartItemService = new CartServiceItemImpl();
	long time = System.currentTimeMillis();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("account");
		User buyer = (User) obj;
		Cart cart = new Cart();
		cart.setBuyer(buyer);
		cart.setBuyDate(new java.sql.Date(time));
		cart.setId(RandomUUID.getRandomID());
		cartService.insert(cart);

		Object objCart = session.getAttribute("cart");
		if (objCart != null) {
			// ep ve dung kieu cua no khi them vao o phan them vao gio hang controller
			Map<Integer, CartItem> map = (Map<Integer, CartItem>) objCart;

			for (CartItem cartItem : map.values()) {
				cartItem.setCart(cart);
				cartItem.setId(RandomUUID.getRandomID());
				cart.setOder_address(buyer.getAddress());
			    cart.setOder_phone(buyer.getPhone());

				//sm.sendMail(cart.getBuyer().getEmail(), "UNIFY", "Payment success. We will contact you soon ! ");
				cartItemService.insert(cartItem);				
			}
			System.out.println("**********inserted oder to db***********");

//			 resp.getWriter().write("Payment success! Mua Hàng Thành Công!");
		
//			// Lưu danh sách sản phẩm đã mua vào session
//			 List<CartItem> purchasedItems = new ArrayList<>();
//			 for (CartItem cartItem : map.values()) {
//			     purchasedItems.add(cartItem);
//			 }
//			 session.setAttribute("purchasedItems", purchasedItems);
		
			// Lấy danh sách sản phẩm đã mua từ session (nếu đã tồn tại)
            List<CartItem> purchasedItems = (List<CartItem>) session.getAttribute("purchasedItems");
            if (purchasedItems == null) {
                purchasedItems = new ArrayList<>();
            }

            // Thêm sản phẩm mới vào danh sách đã mua
            purchasedItems.addAll(map.values());

            // Lưu danh sách sản phẩm đã mua vào session
            session.setAttribute("purchasedItems", purchasedItems);
            
            // In thông báo hoặc thực hiện các bước cần thiết khi đặt hàng thành công
            System.out.println("**********inserted order to db***********");
            resp.getWriter().write("Payment success! Mua Hàng Thành Công!");
			
		}
		session.removeAttribute("cart");
		resp.sendRedirect(req.getContextPath());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
