package controller;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEditServlet", urlPatterns = {"/member/editUser"})
public class UserEditServlet extends HttpServlet {

    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDao.get(userId);

        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/client/view/editUser.jsp");
            dispatcher.forward(request, response);
        } else {
        	response.sendRedirect(request.getContextPath() +"/member/myaccount");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        
        // Handle password update
        String newPassword = request.getParameter("password");
        if (newPassword != null && !newPassword.isEmpty()) {
            // Update password only if a new one is provided
            user.setPassword(newPassword);
        } else {
            // If no new password, retrieve and set the old password
            User existingUser = userDao.get(user.getId());
            user.setPassword(existingUser.getPassword());
        }

        user.setAddress(request.getParameter("address"));
        user.setPhone(request.getParameter("phone"));
//        user.setRoleId(Integer.parseInt(request.getParameter("role_id")));

        userDao.edit(user);
     // Fetch the updated user data
        User updatedUser = userDao.get(user.getId());

        // Update the session with the new user data
        if (updatedUser != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", updatedUser);
        }
        response.sendRedirect(request.getContextPath() + "/member/myaccount");
    }

}
