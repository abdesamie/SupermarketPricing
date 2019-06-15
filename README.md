# SupermarketPricing


The problem domain is something seemingly simple: pricing goods at
supermarkets.
 
Some things in supermarkets have simple prices: this can of beans
costs $0.65. Other things have more complex prices. For example:
•     three for a dollar (so what’s the price if I buy 4, or 5?)
•     $1.99/pound (so what does 4 ounces cost?)
•     buy two, get one free (so does the third item have a price?)
 
The exercise is to experiment with a model that is flexible enough
to deal with these (and other) pricing schemes, and at the same time
are generally usable how do you keep an audit trail of pricing
decisions.

#Design
The design of the solution was based on the following points :

 
1 - separation of concerns -> separation of price calculation from the item original indicative price,
	and the separation of discount application from the item in order to apply or do not apply promotion 
	on item independently, and at the supermarket level.

2 - the abstraction is a key concept in OOP, it allow as to obtain an extensible system, the abstraction
	was applied to define promotions (as the definition of a new promotion consist on implementing the interface)
	the abstraction was applied similarly at the price level 	
	
3 - secondly, one other issue was the quantity concept, which can vary depending on the nature of the item
	(sometimes the quantity is enumerated quantity sometimes is decimal) one solution was to abstract this
	concept into Quantity class.
	
4 - the third (and optional concept) is the creation of an item (a product), as we know any product consists
	of some mandatory characteristics such as price name and a lot of other optional complementary characteristics
	which should not be applied in a specific product, the solution was invoked in Effective Java of Joshua Blosh
	and has been implemented using Builder DP 
