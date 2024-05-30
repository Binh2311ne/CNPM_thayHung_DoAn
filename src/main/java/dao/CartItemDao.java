package dao;

import java.util.List;

import model.CartItem;

public interface CartItemDao {
	void insert(CartItem cartItem);

	void edit(CartItem cartItem);

	void delete(String id);
	
//	  void deleteOrderById(String orderId);

	CartItem get(String name);

	CartItem get(int id);

	List<CartItem> getAll();

	List<CartItem> search(String name);
	List<CartItem> getOrderHistoryByUser(int userId);
}


