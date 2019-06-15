package supermarket.promotion;

import supermarket.Item;
import supermarket.Quantity;

/**
 * Represent a promotion to be applied to a {@link Quantity} of {@link Item}
 * 
 * @author ABDESSAMIE
 *
 */
public interface Promotion {

	/**
	 * Define the rule of discount calculation based on the original price of the
	 * {@link Item} and its {@link Quantity}
	 * 
	 * @param item     the item to which the discount is applied
	 * @param quantity of item
	 * @return the amount of the discount
	 */
	double calculateDiscount(Item item, Quantity quantity);

}
