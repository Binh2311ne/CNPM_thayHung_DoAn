package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CartDao;
import jdbc.JDBCConnection;
import model.Cart;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class CartDaoImpl extends JDBCConnection implements CartDao {
	UserService userS = new UserServiceImpl();

	@Override
	public void insert(Cart cart) {
		String sql = "INSERT INTO Cart(id,u_id, buyDate, oder_address, oder_phone) VALUES (?,?,?,?,?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cart.getId());
			ps.setInt(2, cart.getBuyer().getId());
			ps.setDate(3, new Date(cart.getBuyDate().getTime()));
			ps.setString(4, cart.getOder_address());
			ps.setString(5, cart.getOder_phone());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Cart cart) {
		String sql = "UPDATE cart SET id_user = ?, buyDate = ?, oder_address = ?, oder_phone = ? WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getBuyer().getId());
			ps.setDate(2, new Date(cart.getBuyDate().getTime()));
			ps.setString(3, cart.getId());
			ps.setString(4, cart.getOder_address());
			ps.setString(5, cart.getOder_phone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM cart WHERE id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Cart get(int id) {
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id, user.address, user.phone AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id, cart.oder_address = user.address, cart.oder_phone = user.phone WHERE cart.id=?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				return cart;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> getAll() {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id, user.address, user.phone AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id, cart.oder_address = user.address, cart.oder_phone = user.phone";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				cartList.add(cart);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	public List<Cart> search(String name) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT cart.id, cart.buyDate, User.email, user.username, user.id, user.address, user.phone AS user_id "
				+ "FROM cart INNER JOIN user " + "ON cart.id_user = user.id, cart.oder_address = user.address, cart.oder_phone = user.phone LIKE User.email = ?";

		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));

				Cart cart = new Cart();
				cart.setId(rs.getString("id"));
				cart.setBuyDate(rs.getDate("buyDate"));
				cart.setBuyer(user);

				cartList.add(cart);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	@Override
	public Cart get(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}



