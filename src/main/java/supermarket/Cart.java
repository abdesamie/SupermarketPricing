package supermarket;

import java.util.HashMap;
import java.util.Map;

import supermarket.exceptions.EmptyCartException;
import supermarket.promotion.Promotion;
import supermarket.utility.CartInvoiceFormatter;

/**
 * Cart is the class representing a cart in a supermarket it holds items with
 * thier corresponding quantities a cart is depending in a {@link SuperMarket}
 * 
 * @author ABDESSAMIE
 *
 */
public final class Cart {

	private final Map<Item, Quantity> items = new HashMap<Item, Quantity>();
	private final SuperMarket superMarket;

	public Cart(SuperMarket superMarket) {
		this.superMarket = superMarket;
		superMarket.attachCart(this);

	}

	public double calculateTotal() {
		if (items.isEmpty())
			throw new EmptyCartException();
		return items.entrySet().stream().mapToDouble(x -> x.getKey().calculatePriceFor(x.getValue())).sum();
	}

	public double calculateDiscount() {
		if (items.isEmpty())
			throw new EmptyCartException();
		double discount = 0;
		for (Map.Entry<Item, Quantity> itemQuantity : items.entrySet()) {
			Map<Item, Promotion> promotions = superMarket.getPromotions();
			if (promotions.containsKey(itemQuantity.getKey())) {
				Promotion promotion = promotions.get(itemQuantity.getKey());
				discount += promotion.calculateDiscount(itemQuantity.getKey(), itemQuantity.getValue());
			}
		}
		return discount;
	}

	public void addToCart(Item item, Quantity quantity) {
		if (items.containsKey(item)) {
			Quantity quantityOfItem = items.get(item);
			quantity.add(quantityOfItem);

		}
		items.put(item, quantity);
	}

	public SuperMarket getSuperMarket() {
		return superMarket;
	}

	public Map<Item, Quantity> getItems() {
		return items;
	}

	public String generateInvoice() {
		return CartInvoiceFormatter.getInstance(this).generateInvoice();
	}

}
