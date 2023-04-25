package api

import api.shopizerEndpoints.{getCategoryPageCount, getProductsByGroup, getStore, getTotalPages, postProductPrice}
import io.gatling.core.structure.ChainBuilder
import scenarios.ShopizerScenario.{CHAIR, TABLE}

import scala.collection.mutable.ListBuffer

object application {

  def open(): ListBuffer[ChainBuilder] = {
    val ids = Array.concat(CHAIR.getIds(), TABLE.getIds())
    val requests = new ListBuffer[ChainBuilder]()

    requests +=getProductsByGroup()
    requests +=getStore()
    requests +=getTotalPages()
    requests +=getCategoryPageCount()

    for (id <- ids) {
      requests += postProductPrice(id)
    }
    requests
  }
}
