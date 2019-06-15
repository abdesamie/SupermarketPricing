package supermarket.promotion;

import supermarket.Item;
import supermarket.Quantity;

/**
 * A class representing promotions of type Buy X {@link Item} and get Y items
 * free
 * 
 * @author ABDESSAMIE
 *
 */
public class XBoughtYFree implements Promotion {

	private int x;
	private int y;

	public XBoughtYFree(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double calculateDiscount(Item item, Quantity quantity) {
		int integerQuantity = quantity.getIntegerQuantity();
		int discountTimes = integerQuantity / (x + y);
		int restItems = integerQuantity % (x + y);
		return item.calculatePriceFor(Quantity.of(x)) * discountTimes + item.calculatePriceFor(Quantity.of(restItems));
	}

}
