package simulations

import config.BaseHelper._
import io.gatling.core.Predef._
import scenarios.ShopizerScenario.scnShopizer

import java.util.concurrent.TimeUnit
import scala.concurrent.duration.{FiniteDuration, HOURS, MINUTES, SECONDS}

class PerfTestSimulation extends Simulation{

  var users = 0
  var unitTime:TimeUnit = null;
  var timeDuration = 0;
  def init() ={
    println("Initializing ... ")
    users = Integer.getInteger("users", 100)
    timeDuration = Integer.getInteger("TIME_DURATION", 30)

    if(System.getProperty("TIME_UNIT") == null){
      unitTime = TimeUnit.valueOf("SECONDS")
    }else{
      unitTime = TimeUnit.valueOf(System.getProperty("TIME_UNIT"))
    }

    println("Running perf test with these configurations:")
    println("Total users: " + users)
    println("Time unit: " + unitTime)
    println("Time duration: " + timeDuration)
    println("")
  }

  init()
/********************************
* To run:/> mvn gatling:test
 * rampUsers(100).during(new FiniteDuration(30, SECONDS)),
*/

  setUp(
    scnShopizer.inject(
      rampUsers(users).during(new FiniteDuration(timeDuration, unitTime)),
    )
  ).protocols(httpProtocol)
}
