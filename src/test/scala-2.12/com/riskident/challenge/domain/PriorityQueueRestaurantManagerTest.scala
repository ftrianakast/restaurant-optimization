package com.riskident.challenge.domain

import org.scalatest._

class PriorityQueueRestaurantManagerTest extends FlatSpec with Matchers {

  "priority Queue Restaurant manager" should "find minimum average waiting time using first example" in {
    val customerOrders: List[CustomerOrder] = List(CustomerOrder(0, 3), CustomerOrder(1, 9), CustomerOrder(2, 6))
    PriorityQueueRestaurantManager.calculateMinimumOrderAverageTime(customerOrders) should be(9)
  }

  "priority Queue Restaurant manager" should "find minimum average waiting time using second example" in {
    val customerOrders: List[CustomerOrder] = List(CustomerOrder(0, 3), CustomerOrder(1, 9), CustomerOrder(2, 5))
    PriorityQueueRestaurantManager.calculateMinimumOrderAverageTime(customerOrders) should be(8)
  }

}
