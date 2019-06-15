package supermarket.pricing;

import supermarket.Quantity;

/**
 * A complex price represents a fixed price for a number of items
 * 
 * @author ABDESSAMIE
 *
 */
public class ComplexPrice extends Price {

	private int number;

	public ComplexPrice(int number, double amount) {
		super(amount);
		this.number = number;
	}

	@Override
	public double calculatePriceFor(Quantity quantity) {
		int n = quantity.getIntegerQuantity();

		return (double) (n / number) * amount;
	}

}
