package supermarket;

import java.util.Objects;

import supermarket.pricing.Price;

/**
 * Represent an item sold in the {@link SuperMarket}
 * 
 * @author ABDESSAMIE
 *
 */

public class Item {

	private final String id;
	private final String name;
	private final String description;
	private final String sku;
	private final Price price;

	/**
	 * The Builder Design Pattern is recommended in such cases where we have many
	 * optional attributes in a class an {@link Item} have required attributes which
	 * we instantiate the {@link Item} with and other to append them if necessary
	 * 
	 * @author ABDESSAMIE
	 *
	 */
	public static class Builder {
		private final String id;
		private final String name;
		private String description;
		private String sku;
		private final Price price;

		public Builder(String id, String name, Price price) {
			this.id = id;
			this.name = name;
			this.price = price;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder sku(String sku) {
			this.sku = sku;
			return this;
		}

		public Item build() {
			return new Item(this);
		}

	}

	private Item(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.sku = builder.sku;
	}

	public double calculatePriceFor(Quantity quantity) {
		return this.price.calculatePriceFor(quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Item) {
			return this.id.equals(((Item) obj).id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getSku() {
		return sku;
	}

}
