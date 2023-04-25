package scenarios.obsolete

import config.BaseHelper.{cleanScenario, thinkTimer}
import config.Constants._
import io.gatling.core.Predef.{exec, group, scenario}
import io.gatling.core.structure.ScenarioBuilder
import scenarios.BaseScenario

object CommonScenario extends BaseScenario {

  def scnCommon: ScenarioBuilder = {

    scenario(COMMON_SCENARIO)
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
      )
  }
}
