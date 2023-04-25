package scenarios

import api.shopizerEndpoints.postProductPrice
import config.BaseHelper.{cleanScenario, thinkTimer}
import config.Category
import config.Constants._
import io.gatling.core.Predef._
import io.gatling.core.structure._

import scala.collection.mutable.ListBuffer
import scala.util.Random

object ShopizerScenario {
  val TABLE = new Category(CATEGORY_TABLE_ID, "Table", CATEGORY_TABLE_ALIAS, CATEGORY_TABLE_NAME, TABLES_URL, Array(1))
  val CHAIR = new Category(CATEGORY_CHAIR_ID, "Chair", CATEGORY_CHAIR_ALIAS, CATEGORY_CHAIR_NAME, CHAIRS_URL, Array(50, 51, 52))

  val ids = Array.concat(CHAIR.getIds(), TABLE.getIds())
  val prodsPrice = new ListBuffer[ChainBuilder]()
  for (id <- ids) {
    prodsPrice += postProductPrice(id)
  }

  val chairId = CHAIR.getIds()(Random.nextInt(CHAIR.getIds().length))


  def scnShopizer: ScenarioBuilder = {
    scenario(COMMON_SCENARIO)
      .exec(cleanScenario())
      .exitBlockOnFail(

        randomSwitch(
          20.0 -> exec(
            group(OPEN_APP) {
              exec(api.application.open())
                .exec(thinkTimer())
            }
              .group(NAVIGATE_TO_TABLES) {
                exec(api.category.goTo(TABLE))
                  .exec(thinkTimer())
              }
              .group(OPEN_TABLE) {
                exec(api.product.click(TABLE.getIds().apply(0)))
              }
              .group(ADD_TABLE_TO_CART) {
                exec(api.product.addToCart("${"+TABLE.alias()+"}"))
              }
          ),
          50.00 -> exec(
            group(OPEN_APP) {
              exec(api.application.open())
                .exec(thinkTimer())
            }
              .group(NAVIGATE_TO_TABLES) {
                exec(api.category.goTo(TABLE))
                  .exec(thinkTimer())
              }
              .group(OPEN_TABLE) {
                exec(api.product.click(TABLE.getIds().apply(0)))
              }
              .group(ADD_TABLE_TO_CART) {
                exec(api.product.addToCart("${"+TABLE.alias()+"}"))
              }
              .group(GO_TO_CHAIR) {
                exec(api.category.goTo(CHAIR))
                  .exec(thinkTimer())
              }
              .group(OPEN_RANDOM_CHAIR) {
                exec(api.product.click(chairId))
              }
              .group(ADD_CHAIR_TO_CHART) {
                exec(api.product.addToCart("${"+CHAIR.alias()+"}"))
              }
          ),
          30.00-> exec(
            group(OPEN_APP) {
              exec(api.application.open())
                .exec(thinkTimer())
            }
              .group(NAVIGATE_TO_TABLES) {
                exec(api.category.goTo(TABLE))
                  .exec(thinkTimer())
              }
              .group(OPEN_TABLE) {
                exec(api.product.click(TABLE.getIds().apply(0)))
              }
              .group(ADD_TABLE_TO_CART) {
                exec(api.product.addToCart("${"+TABLE.alias()+"}"))
              }
              .group(GO_TO_CHAIR) {
                exec(api.category.goTo(CHAIR))
                  .exec(thinkTimer())
              }
              .group(OPEN_RANDOM_CHAIR) {
                exec(api.product.click(chairId))
              }
              .group(ADD_CHAIR_TO_CHART) {
                exec(api.product.addToCart("${"+CHAIR.alias()+"}"))
              }
              .group(OPEN_CART) {
                exec(api.cart.open())
              }
              .group(CHECKOUT) {
                exec(api.cart.checkOut())
              }
          )
        )
      )
  }
}
