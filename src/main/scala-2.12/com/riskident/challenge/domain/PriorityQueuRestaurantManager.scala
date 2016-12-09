package com.riskident.challenge.domain

import scala.collection.mutable

trait RestaurantManager {
  def calculateMinimumOrderAverageTime(customerOrders: List[CustomerOrder]): Int
}

object PriorityQueueRestaurantManager extends RestaurantManager {

  def calculateMinimumOrderAverageTime(customerOrders: List[CustomerOrder]): Int = {
    val customerOrdersByArrivalOrder: List[CustomerOrder] = customerOrders.sortWith((c1, c2) => c1.requestTime < c2.requestTime)
    implicit val ordering = Ordering[Int].on[CustomerOrder](order => order.requestTime + order.cookingTime).reverse
    calculateMinimumOrderAverageTime(0, 0, 0, customerOrdersByArrivalOrder, new mutable.PriorityQueue[CustomerOrder]())
  }

  private def calculateMinimumOrderAverageTime(time: Int, ordersDelivered: Int,
                                               aggregation: Int, customerOrders: List[CustomerOrder],
                                               queue: mutable.PriorityQueue[CustomerOrder]): Int = {
    val (waiting, others) = customerOrders.span(order => order.requestTime <= time)
    queue.enqueue(waiting: _*)
    if (queue.isEmpty) {
      if (ordersDelivered == 0) 0 else aggregation / ordersDelivered
    }
    else {
      val clientOrder = queue.dequeue()
      val aggregationTime = aggregation + time - clientOrder.requestTime + clientOrder.cookingTime
      calculateMinimumOrderAverageTime(time + clientOrder.cookingTime, ordersDelivered + 1, aggregationTime, others, queue)
    }
  }

}
