package supermarket.pricing;

import supermarket.Item;
import supermarket.Quantity;

/**
 * An abstract representation of a price for an {@link Item} since there are
 * several type of pricing schemes each one must define how to calculate the
 * price for a defined quantity
 * 
 * @author ABDESSAMIE
 *
 */
public abstract class Price {

	protected double amount;

	public Price(double amount) {
		this.amount = amount;
	}

	/**
	 * Calculate the price for a {@link Quantity}
	 * 
	 * @param quantity of the item
	 * @return the price for the defined quantity
	 */
	public abstract double calculatePriceFor(Quantity quantity);

}
