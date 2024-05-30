package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import jdbc.JDBCConnection;
import model.User;

public class UserDaoImpl extends JDBCConnection implements UserDao {

	@Override
	public void insert(User user) {
		int roleId=0;
		String sql = "INSERT INTO User (email, username, password,address, phone, avatar, role_id) VALUES (?, ?, ?, ?, ?,?,?)";


		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getAvatar());
			try {
				if(user.getRoleId()==1) {
					roleId=1;
				}else {
					roleId=2;
				}

			} catch (Exception e) {
				roleId=2;
			}
			ps.setInt(7, roleId);
			;
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(User user) {
		String sql = "UPDATE User SET email = ?, username = ?, password = ?,address = ?, phone = ?, avatar = ?, role_id = ? WHERE id = ?";

		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getAvatar());
			ps.setInt(7, user.getRoleId());
			ps.setInt(8, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM User WHERE id = ?";

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
	public User get(String username) {
		String sql = "SELECT * FROM User WHERE username = ?";


		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				return user;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User get(int id) {
		String sql = "SELECT * FROM User WHERE id = ?";

		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				return user;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM User";


		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	@Override
	public List<User> search(String keyword) {
		List<User> userList = new ArrayList<User>();
		String sql = "SELECT * FROM User WHERE name LIKE ?";

		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();

				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(Integer.parseInt(rs.getString("role_id")));

				userList.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		Connection conn = JDBCConnection.getJDBCConnection();
		try {
			String query = "SELECT * FROM user WHERE email = ?";



			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, email);

			ResultSet resultSet = psmt.executeQuery();

			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
			conn.close();
		} catch (SQLException ex) {
		}
		return duplicate;
	}

	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		Connection conn = JDBCConnection.getJDBCConnection();
		try {
			String query = "SELECT * FROM User WHERE username = ?";


			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setString(1, username);

			ResultSet resultSet = psmt.executeQuery();

			if (resultSet.next()) {
				duplicate = true;
			}
			psmt.close();
			conn.close();
		} catch (SQLException ex) {
		}
		return duplicate;
	}

}
