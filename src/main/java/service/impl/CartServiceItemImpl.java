package service.impl;

import java.util.List;

import dao.CartItemDao;
import dao.impl.CartItemDaoImpl;
import model.CartItem;
import service.CartItemService;

public class CartServiceItemImpl implements CartItemService {
	CartItemDao cartItemDao = new CartItemDaoImpl();

	@Override
	public void insert(CartItem cartItem) {
		cartItemDao.insert(cartItem);
		
	}

	@Override
	public void edit(CartItem newCartItem) {
		CartItem oldCartItem = cartItemDao.get(newCartItem.getId());
		oldCartItem.setCart(newCartItem.getCart());
		oldCartItem.setProduct(newCartItem.getProduct());
		oldCartItem.setQuantity(newCartItem.getQuantity());
		oldCartItem.setUnitPrice(newCartItem.getUnitPrice());
		
		cartItemDao.edit(oldCartItem);
	}

	@Override
	public void delete(String id) {
		cartItemDao.delete(id);
	}

	@Override
	public CartItem get(int id) {
		return cartItemDao.get(id);
	}

	@Override
	public List<CartItem> getAll() {
		return cartItemDao.getAll();
	}

	@Override
	public List<CartItem> search(String keyword) {
		return cartItemDao.search(keyword);
	}

	@Override
	public List<CartItem> getOrderHistoryByUser(int userId) {
	    return cartItemDao.getOrderHistoryByUser(userId);
	}

	@Override
	public CartItem getCartItemById(String orderId) {
		// TODO Auto-generated method stub
		return null;
	
}
	
//	@Override
//    public void deleteOrderById(String orderId) {
//        cartItemDao.deleteOrderById(orderId);
//}
}


