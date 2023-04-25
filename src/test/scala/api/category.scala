package api

import api.shopizerEndpoints._
import config.Category
import io.gatling.core.structure._

import scala.collection.mutable.ListBuffer

object category {

  def goTo(cat: Category): ListBuffer[ChainBuilder] = {
    val requests = new ListBuffer[ChainBuilder]()
    requests += goToCategory(cat.name(), cat.getUrl(), cat.alias())
    requests += getStore()
    requests += getCategoryPageCount()
    requests += getTotalPages()

    requests += getCategoryByIdStoreLang(cat.id(), cat.getJsonPathValue())
    requests += getCategoryManufacturesById(cat.id(), "DEFAULT")

    for (id <- cat.getIds()) {
      requests += postProductPrice(id)
    }
    requests
  }
}
