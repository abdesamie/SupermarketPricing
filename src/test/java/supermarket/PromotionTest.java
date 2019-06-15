package supermarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import supermarket.pricing.SimplePrice;
import supermarket.pricing.WeightPrice;
import supermarket.promotion.DirectDiscount;
import supermarket.promotion.Promotion;
import supermarket.promotion.XBoughtYDiscount;
import supermarket.promotion.XBoughtYFree;

public class PromotionTest {
	Cart cart;

	@Before
	public void setUp() {
		SuperMarket superMarket = new SuperMarket();
		cart = superMarket.getCart();

	}

	/**
	 * Ensure correct calculation of direct discount
	 */
	@Test
	public void directDiscountTest() {

		Item sugar = new Item.Builder("1", "sugar", new WeightPrice(1, 15)).build();
		cart.addToCart(sugar, Quantity.of(2.5));
		Promotion promotion = new DirectDiscount(0.25);
		cart.getSuperMarket().applyPromotion(sugar, promotion);
		assertEquals(9.375, cart.calculateDiscount(), 0);
	}

	/**
	 * Assert correctness of {@link XBoughtYFree} discount counting
	 */
	@Test
	public void buyXgetYFreeTest() {
		Item chocolate = new Item.Builder("1", "chocolate", new SimplePrice(2.8)).build();
		cart.addToCart(chocolate, Quantity.of(9));
		Promotion promotion = new XBoughtYFree(2, 1);
		cart.getSuperMarket().applyPromotion(chocolate, promotion);
		assertEquals(6 * 2.8, cart.calculateDiscount(), 0);
	}

	/**
	 * Assert correctness of {@link XBoughtYDiscount} discount counting
	 */
	@Test
	public void buyXgetDiscountOnYTest() {
		Item juice = new Item.Builder("1", "juice", new SimplePrice(3)).build();
		cart.addToCart(juice, Quantity.of(7));
		Promotion promotion = new XBoughtYDiscount(2, 1, 0.5);
		cart.getSuperMarket().applyPromotion(juice, promotion);
		assertEquals(18, cart.calculateDiscount(), 0);
	}
}
