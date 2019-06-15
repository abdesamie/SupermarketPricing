package supermarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import supermarket.promotion.Promotion;

/**
 * The SuperMarket the main class representing a supermarket that holds a
 * collection of {@link Cart} and into whom we can define a set of promotions on
 * each {@link Item}
 * 
 * @author ABDESSAMIE
 *
 */
public class SuperMarket {

	private List<Cart> carts;
	private Map<Item, Promotion> promotions;

	public SuperMarket() {
		carts = new ArrayList<>();
		promotions = new HashMap<Item, Promotion>();
	}

	/**
	 * Apply a promotion on certain product or item
	 * 
	 * @param item      to apply promotion to
	 * @param promotion the promotion to be applied
	 */
	public void applyPromotion(Item item, Promotion promotion) {
		promotions.put(item, promotion);
	}

	/**
	 * Get a {@link Cart} from a {@link SuperMarket} to start the checkout
	 * 
	 * @return the cart provided
	 */
	public Cart getCart() {
		Cart cart = new Cart(this);
		carts.add(cart);
		return cart;
	}

	/**
	 * Attach a cart to this {@link SuperMarket}
	 * 
	 * @param cart
	 */
	public void attachCart(Cart cart) {
		carts.add(cart);
	}

	public Map<Item, Promotion> getPromotions() {
		return promotions;
	}

}
