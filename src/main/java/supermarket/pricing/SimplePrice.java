package supermarket.pricing;

import supermarket.Quantity;

/**
 * Simple price represent a basic price for one item
 * 
 * @author ABDESSAMIE
 *
 */
public class SimplePrice extends Price {

	public SimplePrice(double amount) {
		super(amount);
	}

	@Override
	public double calculatePriceFor(Quantity quantity) {
		return quantity.getIntegerQuantity() * amount;
	}

}
