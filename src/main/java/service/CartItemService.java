package service;

import java.util.List;

import model.CartItem;

public interface CartItemService {
	void insert(CartItem cartItem);

	void edit(CartItem cartItem);

	void delete(String id);
	
//	  void deleteOrderById(String orderId);

	CartItem get(int id);
	
	List<CartItem> getAll();

	List<CartItem> search(String keyword);
	

List<CartItem> getOrderHistoryByUser(int userId);

CartItem getCartItemById(String orderId);
}


