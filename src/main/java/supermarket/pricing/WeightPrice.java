package supermarket.pricing;

import supermarket.Quantity;

/**
 * Weight price represents a price for articles whose the price is based on
 * weight the unit used is the Pound
 * 
 * @author ABDESSAMIE
 *
 */
public class WeightPrice extends Price {

	private double refQuantity;

	/**
	 * Consructor of a weight based price
	 * 
	 * @param refQuantity reference {@link Quantity} of item to which the amount is
	 *                    applied
	 * @param amount      the amount applied to the referenced {@link Quantity}
	 */
	public WeightPrice(double refQuantity, double amount) {
		super(amount);
		this.refQuantity = refQuantity;
	}

	@Override
	public double calculatePriceFor(Quantity quantity) {
		double doubleQuantity = quantity.getDoubleQuantity();
		return (doubleQuantity / refQuantity) * amount;
	}

}
