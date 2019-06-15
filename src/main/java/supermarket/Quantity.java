package supermarket;

import supermarket.exceptions.InconsistentQuantityException;

/**
 * A generic representation of a quantity a quantity could be a numeric value
 * like 1,2,3 .. etc and it also can be a decimal value that represent a certain
 * measure like weight
 * 
 * @author ABDESSAMIE
 *
 */
public class Quantity {

	private double quantity;

	private Quantity() {

	}

	public static Quantity of(double quantity) {
		Quantity instance = new Quantity();
		instance.quantity = quantity;
		return instance;
	}

	public static Quantity of(int quantity) {
		Quantity instance = new Quantity();
		instance.quantity = quantity;
		return instance;
	}

	public boolean isPureInteger() {
		return Math.floor(quantity) == quantity;
	}

	public int getIntegerQuantity() {
		if (!isPureInteger())
			throw new InconsistentQuantityException();
		return (int) quantity;

	}

	public double getDoubleQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		if (isPureInteger())
			return String.valueOf((int) quantity);
		else
			return String.valueOf(quantity);
	}

	public void add(Quantity quant) {
		this.quantity += quant.quantity;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Quantity) {
			return this.quantity == ((Quantity) obj).quantity;
		}
		return false;
	}
}
