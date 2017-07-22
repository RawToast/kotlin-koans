package ii_collections

fun example4() {
    val max = listOf(1, 42, 4).max()
    val longestString = listOf("a", "b").maxBy { it.length }
}

fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? {
    return customers.maxBy { c -> c.orders.size }
}

fun Customer.getMostExpensiveOrderedProduct(): Product? {
    return orders.flatMap { o -> o.products }
            .maxBy { p -> p.price }
}
