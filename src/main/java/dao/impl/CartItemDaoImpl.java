package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartItemDao;
import dao.UserDao;
import jdbc.JDBCConnection;
import model.Cart;
import model.CartItem;
import model.Product;
import model.User;
import service.CartService;
import service.ProductService;
import service.impl.CartServiceImpl;
import service.impl.ProductServiceImpl;

public class CartItemDaoImpl extends JDBCConnection implements CartItemDao {
	CartService cartService = new CartServiceImpl();
	ProductService productService = new ProductServiceImpl();
	UserDao userDao = new UserDaoImpl();
	
	
	@Override
	public void insert(CartItem cartItem) {
		String sql = "INSERT INTO CartItem(id, cat_id, pro_id, quantity, unitPrice) VALUES (?,?,?,?,?)";

		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, cartItem.getId());
			ps.setString(2, cartItem.getCart().getId());
			ps.setInt(3, cartItem.getProduct().getId());
			ps.setInt(4, cartItem.getQuantity());
			ps.setLong(5, cartItem.getUnitPrice());

			ps.executeUpdate();

//			ResultSet generatedKeys = ps.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				int id = generatedKeys.getInt(1);
//				cartItem.setId(id);
//			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(CartItem cartItem) {
		String sql = "UPDATE CartItem SET cat_id = ?, pro_id = ?, quantity = ?, unitPrice=? WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, cartItem.getCart().getId());
			ps.setInt(2, cartItem.getProduct().getId());
			ps.setInt(3, cartItem.getQuantity());
			ps.setLong(4, cartItem.getUnitPrice());
			ps.setString(5, cartItem.getId());
			
			
			ps.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM CartItem WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public CartItem get(int id) {
		String sql = "SELECT " + 
				"CartItem.id, " + 
				"CartItem.quantity, " + 
				"CartItem.unitPrice, " + 
				"cart.u_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM CartItem " + 
				"INNER JOIN Cart " + 
				"ON CartItem.cart_id = cart.id " + 
				"INNER JOIN Product " + 
				"ON CartItem.pro_id = Product.id " +
				"WHERE CartItem.id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("u_id"));
				
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unitPrice"));
				
				
				return cartItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CartItem> getAll() {
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		String sql = "SELECT " + 
				"CartItem.id, " + 
				"CartItem.quantity, " + 
				"CartItem.unitPrice, " + 
				"cart.u_id, " + 
				"cart.buyDate, " + 
				"product.name, " + 
				"product.price " + 
				"FROM CartItem " + 
				"INNER JOIN Cart " + 
				"ON CartItem.cat_id = Cart.id " + 
				"INNER JOIN Product " + 
				"ON CartItem.pro_id = Product.id ";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userDao.get(rs.getInt("u_id"));
				
				Cart cart = new Cart();
				cart.setBuyer(user);
				cart.setBuyDate(rs.getDate("buyDate"));
				
				Product product = new Product();
				product.setName(rs.getString("name"));
				product.setPrice(rs.getLong("price"));
				
				CartItem cartItem = new CartItem();
				cartItem.setId(rs.getString("id"));
				cartItem.setCart(cart);
				cartItem.setProduct(product);
				cartItem.setQuantity(rs.getInt("quantity"));
				cartItem.setUnitPrice(rs.getLong("unitPrice"));

				cartItemList.add(cartItem);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartItemList;
	}

	public List<CartItem> search(String name) {
		return null;
	}

	@Override
	public CartItem get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CartItem> getOrderHistoryByUser(int userId) {
	    List<CartItem> cartItemList = new ArrayList<CartItem>();
	    String sql = "SELECT " +
	            "CartItem.id, " +
	            "CartItem.quantity, " +
	            "CartItem.unitPrice, " +
	            "cart.u_id, " +
	            "cart.buyDate, " +
	            "product.id AS pro_id, " +
	            "product.name, " +
	            "product.price " +
	            "FROM CartItem " +
	            "INNER JOIN Cart " +
	            "ON CartItem.cat_id = Cart.id " +
	            "INNER JOIN Product " +
	            "ON CartItem.pro_id = Product.id " +
	            "WHERE cart.u_id = ?";  // Lọc theo userId

	    Connection con = super.getJDBCConnection();

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            User user = userDao.get(rs.getInt("u_id"));

	            Cart cart = new Cart();
	            cart.setBuyer(user);
	            cart.setBuyDate(rs.getDate("buyDate"));

	            Product product = new Product();
	            product.setId(rs.getInt("pro_id"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getLong("price"));

	            CartItem cartItem = new CartItem();
	            cartItem.setId(rs.getString("id"));
	            cartItem.setCart(cart);
	            cartItem.setProduct(product);
	            cartItem.setQuantity(rs.getInt("quantity"));
	            cartItem.setUnitPrice(rs.getLong("unitPrice"));

	            cartItemList.add(cartItem);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cartItemList;
	}
	
}



