package scenarios.obsolete

import config.BaseHelper.{cleanScenario, thinkTimer}
import config.Constants._
import io.gatling.core.Predef.{exec, group, scenario}
import io.gatling.core.structure.ScenarioBuilder
import scenarios.BaseScenario

object ThirtyPercentOfUsersScenario extends BaseScenario {

  def scnThityPercentOfUsers: ScenarioBuilder = {


    scenario(THIRTY_PERCENT_USERS)
      .exec(cleanScenario())
      .exitBlockOnFail(

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
            exec(api.product.addToCart(TABLE.alias()))
          }

          .group(GO_TO_CHAIR) {
            exec(api.category.goTo(CHAIR))
              .exec(thinkTimer())
          }

          .group(OPEN_RANDOM_CHAIR) {
            exec(api.product.click(chairId))
          }

          .group(ADD_CHAIR_TO_CHART) {
            exec(api.product.addToCart(CHAIR.alias()))
          }

          .group(OPEN_CART) {
            exec(api.cart.open())
          }

          .group(CHECKOUT) {
            exec(api.cart.checkOut())
          }
      )
  }
}
