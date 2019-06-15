package supermarket.utility;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import supermarket.Cart;
import supermarket.Item;
import supermarket.Quantity;
import supermarket.promotion.Promotion;

public class CartInvoiceFormatter {

	private static final int CELL_LENGTH = 15;
	private Cart cart;
	private static CartInvoiceFormatter cartInvoiceFormatter;

	private CartInvoiceFormatter(Cart cart) {
		this.cart = cart;
	}

	public static CartInvoiceFormatter getInstance(Cart cart) {
		if (cartInvoiceFormatter == null)
			cartInvoiceFormatter = new CartInvoiceFormatter(cart);
		else
			cartInvoiceFormatter.cart = cart;
		return cartInvoiceFormatter;
	}

	public String generateInvoice() {
		StringBuilder invoiceText = new StringBuilder();
		generateHeadings(invoiceText);
		generateItemsOrders(invoiceText);
		generateTotal(invoiceText);
		generateDiscounts(invoiceText);
		generateAmountToPay(invoiceText);
		return invoiceText.toString();
	}

	private void generateTotal(StringBuilder invoiceText) {
		invoiceText.append(StringUtility.formatInvoiceLine(CELL_LENGTH, "Total", "", cart.calculateTotal()));
	}

	private void generateDiscounts(StringBuilder invoiceText) {
		invoiceText.append(StringUtility.formatInvoiceLine(CELL_LENGTH, "Discounts:", "", ""));

		for (Map.Entry<Item, Quantity> itemQuantity : cart.getItems().entrySet()) {
			Map<Item, Promotion> promotions = cart.getSuperMarket().getPromotions();
			if (promotions.containsKey(itemQuantity.getKey())) {
				Promotion promotion = promotions.get(itemQuantity.getKey());
				invoiceText.append(displayDiscount(itemQuantity.getKey(), promotion, itemQuantity.getValue()));
			}
		}
	}

	private String displayDiscount(Item item, Promotion promotion, Quantity quantity) {
		return StringUtility.formatInvoiceLine(CELL_LENGTH, "Discount on", item.getName(),
				-promotion.calculateDiscount(item, quantity));
	}

	private void generateAmountToPay(StringBuilder invoiceText) {
		invoiceText.append(StringUtility.formatInvoiceLine(CELL_LENGTH, "Amount to pay", "",
				cart.calculateTotal() - cart.calculateDiscount()));
	}

	private void generateHeadings(StringBuilder invoiceText) {
		invoiceText.append(StringUtility.formatInvoiceLine(CELL_LENGTH, "Invoice", "", ""));
		invoiceText.append(StringUtility.formatInvoiceLine(CELL_LENGTH, "Quantity", "Item", "Price"));
	}

	private void generateItemsOrders(StringBuilder invoiceText) {
		getItemsStream().forEach(entry -> invoiceText.append(displayItemQuantity(entry.getKey(), entry.getValue())));
	}

	private String displayItemQuantity(Item item, Quantity quantity) {
		return StringUtility.formatInvoiceLine(CELL_LENGTH, quantity.toString(), item.getName(),
				item.calculatePriceFor(quantity));
	}

	private Stream<Entry<Item, Quantity>> getItemsStream() {
		return cart.getItems().entrySet().stream();
	}
}