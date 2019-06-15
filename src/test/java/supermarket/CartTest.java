package supermarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import supermarket.exceptions.EmptyCartException;
import supermarket.pricing.ComplexPrice;
import supermarket.pricing.SimplePrice;
import supermarket.pricing.WeightPrice;
import supermarket.promotion.DirectDiscount;

public class CartTest {

	private Cart cart;

	@Before
	public void setUp() {
		SuperMarket superMarket = new SuperMarket();
		cart = superMarket.getCart();
	}

	/**
	 * Ensure twp characteristics of add to cart 1 - add differnt items 2 - add two
	 * similar items separetly and ensure their quantity update
	 */
	@Test
	public void addToCartTest() {
		Item water = new Item.Builder("1", "water", new SimplePrice(2)).build();
		Item bread = new Item.Builder("2", "bread", new SimplePrice(3)).build();
		Item tomatoes = new Item.Builder("3", "tomatoes", new WeightPrice(5, 2.5)).build();
		Item water2 = new Item.Builder("1", "water", new SimplePrice(2)).build();

		cart.addToCart(water, Quantity.of(2));
		cart.addToCart(bread, Quantity.of(1));
		cart.addToCart(tomatoes, Quantity.of(2));
		cart.addToCart(water2, Quantity.of(2));

		assertEquals(3, cart.getItems().size());
		assertEquals(Quantity.of(4), cart.getItems().entrySet().stream().filter(entry -> entry.getKey().equals(water))
				.findFirst().get().getValue());
	}

	/**
	 * Verify the correctness of cart total calculation
	 */
	@Test
	public void calculateTotalTest() {
		Item milk = new Item.Builder("1", "milk", new SimplePrice(2)).build();
		Item potatoes = new Item.Builder("2", "potatoes", new WeightPrice(1, 1.99)).build();
		Item chocoltes = new Item.Builder("3", "chocolates", new ComplexPrice(3, 2)).build();

		cart.addToCart(milk, Quantity.of(1));
		cart.addToCart(potatoes, Quantity.of(1));
		cart.addToCart(chocoltes, Quantity.of(3));

		assertEquals(5.99, cart.calculateTotal(), 0);
	}

	@Test(expected = EmptyCartException.class)
	public void calculateTotalExceptionTest() {
		cart.calculateTotal();
	}

	@Test(expected = EmptyCartException.class)
	public void calculateDiscountExceptionTest() {
		cart.calculateTotal();
	}

	/**
	 * Ensure successful invoice generation
	 */
	@Test
	public void generateInvoiceTest() {
		Item water = new Item.Builder("2", "water", new SimplePrice(2)).build();
		Item potatoes = new Item.Builder("3", "potatoes", new WeightPrice(1, 2)).build();
		Item milk = new Item.Builder("4", "milk", new SimplePrice(3)).build();
		cart.addToCart(water, Quantity.of(2));
		cart.addToCart(potatoes, Quantity.of(1.5));
		cart.addToCart(milk, Quantity.of(2));
		cart.getSuperMarket().applyPromotion(milk, new DirectDiscount(0.25));
		String invoice = "Invoice                                      \r\n"
				+ "Quantity       Item           Price          \r\n"
				+ "2              water          4.0            \r\n"
				+ "1.5            potatoes       3.0            \r\n"
				+ "2              milk           6.0            \r\n"
				+ "Total                         13.0           \r\n"
				+ "Discounts:                                   \r\n"
				+ "Discount on    milk           -1.5           \r\n"
				+ "Amount to pay                 11.5           \r\n";
		assertEquals(invoice, cart.generateInvoice());
	}
}
