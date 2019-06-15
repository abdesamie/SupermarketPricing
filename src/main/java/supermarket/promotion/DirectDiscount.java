package supermarket.promotion;

import supermarket.Item;
import supermarket.Quantity;
import supermarket.pricing.Price;

/**
 * Direct discount represent a promotion with a defined percentage such as 20%
 * the discount is deduced directly from the original {@link Price } of an item
 * 
 * @author ABDESSAMIE
 *
 */
public class DirectDiscount implements Promotion {

	private double percentage;

	public DirectDiscount(double percentage) {
		this.percentage = percentage;
	}

	public double calculateDiscount(Item item, Quantity quantity) {

		return item.calculatePriceFor(quantity) * percentage

		;
	}

}
