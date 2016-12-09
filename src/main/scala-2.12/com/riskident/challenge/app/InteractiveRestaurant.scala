package com.riskident.challenge.app

import com.riskident.challenge.domain.{CustomerOrder, PriorityQueueRestaurantManager}

object InteractiveRestaurant extends App {

  val programInstructions = io.Source.stdin.getLines()
  val n = programInstructions.next().toInt

  val customerOrders = for (i <- 0 until n) yield {
    val components = programInstructions.next().split(" ")
    val requestTime = components(0).toInt
    val cookingTime = components(1).toInt
    CustomerOrder(requestTime, cookingTime)
  }

  println(PriorityQueueRestaurantManager.calculateMinimumOrderAverageTime(customerOrders.toList))

}
