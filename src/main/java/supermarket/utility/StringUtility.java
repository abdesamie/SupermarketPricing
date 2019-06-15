package supermarket.utility;

public class StringUtility {

	public static String formatInvoiceLine(int cellLength, Object s1, Object s2, Object s3) {
		return String.format("%-" + cellLength + "s%-" + cellLength + "s%-" + cellLength + "s%n", s1, s2, s3);
	}
}
