package simulations

import config.BaseHelper._
import io.gatling.core.Predef._
import scenarios.ShopizerScenario.scnShopizer

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.{FiniteDuration, HOURS, MINUTES, SECONDS}

class PerfTestSimulation extends Simulation{

  var users = 0

  def init() ={
    println("Initializing ... ")
    users = Integer.getInteger("users", 500)

    println("Running perf test with these configurations:")
    println("Total users: " + users)
    println("")
  }

  init()
/********************************
* To run:/> mvn gatling:test
*/

  def unitTime = TimeUnit.valueOf("SECONDS")

  setUp(
    scnShopizer.inject(
      rampUsers(users).during(new FiniteDuration(200, unitTime)),
      //rampConcurrentUsers(200).to(300).during(new FiniteDuration(40, SECONDS)),
    )
  ).protocols(httpProtocol)
}
