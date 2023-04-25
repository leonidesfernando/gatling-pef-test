package scenarios

import api.shopizerEndpoints.postProductPrice
import config.Category
import config.Constants._
import io.gatling.core.structure.ChainBuilder

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class BaseScenario() {
  val TABLE = new Category(CATEGORY_TABLE_ID, "Table", CATEGORY_TABLE_ALIAS, CATEGORY_TABLE_NAME, TABLES_URL, Array(1))
  val CHAIR = new Category(CATEGORY_CHAIR_ID, "Chair", CATEGORY_CHAIR_ALIAS, CATEGORY_CHAIR_NAME, CHAIRS_URL, Array(50, 51, 52))

  val ids = Array.concat(CHAIR.getIds(), TABLE.getIds())
  val prodsPrice = new ListBuffer[ChainBuilder]()
  for (id <- ids) {
    prodsPrice += postProductPrice(id)
  }

  val chairId = CHAIR.getIds()(Random.nextInt(CHAIR.getIds().length))
}
