package api

import api.shopizerEndpoints._
import io.gatling.core.structure.ChainBuilder

import scala.collection.mutable.ListBuffer

object product {

  def click(productId:Int): ListBuffer[ChainBuilder] = {
    val requests = new ListBuffer[ChainBuilder]()
    requests += getProductById(productId)
    requests += getProductReviewsById(productId)
    requests += getStore()
    requests += getTotalPages()
    requests += getCategoryPageCount()
    requests += postProductPrice(productId)
  }

  def addToCart(product:String): ListBuffer[ChainBuilder] = {
    val requests = new ListBuffer[ChainBuilder]()
    requests += postProductToCart(product)
    requests += geCartProductByCode()
  }
}
