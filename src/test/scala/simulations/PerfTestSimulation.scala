package simulations

import config.BaseHelper._
import io.gatling.core.Predef._
import scenarios.ShopizerScenario.scnShopizer

import scala.concurrent.duration.{FiniteDuration, SECONDS}

class PerfTestSimulation extends Simulation{

  var users = 0

  def init() ={
    println("Initializing ... ")
    users = Integer.getInteger("users", 100)

    println("Running perf test with these configurations:")
    println("Total users: " + users)
    println("")
  }

  init()
/********************************
* To run:/> mvn gatling:test
*/

  setUp(
    scnShopizer.inject(
      rampUsers(users).during(new FiniteDuration(users, SECONDS))
    )
  ).protocols(httpProtocol)
}
