# Notes

Please add here any notes, assumptions and design decisions that might help us understand your thought process.

I have used TDD approach to drive the implementation of DiscountCalculator class.
At the moment, the system only allows for `DiscountType.NONE` and `DiscountType.BUY_ONE_GET_ONE_FREE`

With having more time, I would add more tests to `BasketTest` that would cover edge cases like single item 
with Promotion, 3 items with implemented Promotion.

I would also change the name of `DiscountCalculator.calculateDiscount()` method to reflect the fact (for the time being) 
it is only applicable for calculating discounts for Items by Unit.

The next steps would be adding another type of Promotion first by including it into `DiscountType` enum. And 
then most likely separating code in `DiscountCalculator.calculateDiscount` as it grows to be more readable.