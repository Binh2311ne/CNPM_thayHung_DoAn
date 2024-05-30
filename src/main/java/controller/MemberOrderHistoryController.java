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

import model.Cart;
import model.CartItem;
import model.User;
import service.CartItemService;
import service.CartService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.CartServiceItemImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/member/order/history" })
public class MemberOrderHistoryController extends HttpServlet {
    CartItemService cartItemService = new CartServiceItemImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("account");
       
     // Lấy tên đăng nhập từ session
     		if(session != null && session.getAttribute("account") != null) {
     			User u=(User) session.getAttribute("account");
     			req.setAttribute("username", u.getUsername());
     		}
     		
        if (obj != null) {
            // Lấy người dùng từ session
            User user = (User) obj;        
            // Lấy lịch sử đặt hàng cho người dùng
            List<CartItem> orderHistory = cartItemService.getOrderHistoryByUser(user.getId()); 
      
            req.setAttribute("orderHistory", orderHistory);
            req.setAttribute("userAddress", user.getAddress()); // Add this line
            req.setAttribute("userPhone", user.getPhone());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/view/client/view/order-history.jsp");
            dispatcher.forward(req, resp);
        } else {
            
        	// Chuyển hướng đến trang đăng nhập hoặc xử lý trường hợp khi người dùng chưa đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String id = req.getParameter("id");
            cartItemService.delete(id);

            // Sau khi xóa, cập nhật lại danh sách lịch sử đơn hàng
            HttpSession session = req.getSession();
            Object obj = session.getAttribute("account");

            if (obj != null) {
                User user = (User) obj;
                List<CartItem> orderHistory = cartItemService.getOrderHistoryByUser(user.getId());
                session.setAttribute("orderHistory", orderHistory);
            }

            resp.sendRedirect(req.getContextPath() + "/member/order/history");
        } 
}
}