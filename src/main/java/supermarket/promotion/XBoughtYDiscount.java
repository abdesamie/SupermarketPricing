package supermarket.promotion;

import supermarket.Item;
import supermarket.Quantity;

/**
 * A class representing promotions of type Buy X {@link Item} and get discount
 * on next bought Y
 * 
 * @author ABDESSAMIE
 *
 */
public class XBoughtYDiscount implements Promotion {

	private int x;
	private int y;
	private double percentage;

	/**
	 * Construct a promotion that consists on getting a discount for an y next items
	 * after buying x number of items
	 * 
	 * @param x
	 * @param y
	 * @param percentage the percentage of the discount
	 * @throws IllegalArgumentException if percentage is not included in range [0,1]
	 */
	public XBoughtYDiscount(int x, int y, double percentage) {
		if (percentage < 0 || percentage > 1)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
		this.percentage = percentage;
	}

	public double calculateDiscount(Item item, Quantity quantity) {
		int integerQuantity = quantity.getIntegerQuantity();
		int discountTimes = integerQuantity / (x + y);
		int restItems = integerQuantity % (x + y);
		return item.calculatePriceFor(Quantity.of(x)) * discountTimes
				+ item.calculatePriceFor(Quantity.of(y)) * percentage * discountTimes
				+ item.calculatePriceFor(Quantity.of(restItems));

	}

}
