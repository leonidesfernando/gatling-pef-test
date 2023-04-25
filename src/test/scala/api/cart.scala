package api

import api.shopizerEndpoints._
import io.gatling.core.structure._

import scala.collection.mutable.ListBuffer

object cart {

  def open(): ListBuffer[ChainBuilder] = {
    val requests = new ListBuffer[ChainBuilder]()
    requests += geCartByProductCodeStore()
    requests += getStore()
    requests += getCategoryPageCount()
    requests += getTotalPages()
    requests += geCartByProductCodeStore()// optional
  }

  def checkOut(): ListBuffer[ChainBuilder] = {
    val requests = new ListBuffer[ChainBuilder]()
    requests += geCartByProductCodeStore()
    requests += getZones() // optional
    requests += getZones() // optional
    requests += getConfig() // optional
    requests += getCartTotalByProductCode()
    requests += getShippingCountry()
    requests += getStore()
    requests += getCategoryPageCount()
    requests += getTotalPages()
  }
}
