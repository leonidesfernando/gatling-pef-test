package config

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder._

object BaseHelper {

  def get(requestName: String, url: String, jsonPathAssertion: String = "", jsonPathValue: String = null):  HttpRequestBuilder = {
    if("".eq(jsonPathAssertion)) {
        http(requestName)
          .get(url)
    }else if(jsonPathValue == null){
        http(requestName)
          .get(url)
          .check(jsonPath(jsonPathAssertion))
    }else{
        http(requestName)
          .get(url)
          .check(jsonPath(jsonPathAssertion).is(jsonPathValue))
    }
  }

  def post(requestName: String, body: String, url: String, jsonPathAssertion: String = "", jsonPathValue: String = null):  HttpRequestBuilder = {
    if ("".eq(jsonPathAssertion)) {
        http(requestName)
          .post(url)
          .body(StringBody(body)).asJson
    } else if (jsonPathValue == null) {
        http(requestName)
          .post(url)
          .body(StringBody(body)).asJson
          .check(jsonPath(jsonPathAssertion))
    } else {
        http(requestName)
          .post(url)
          .body(StringBody(body)).asJson
          .check(jsonPath(jsonPathAssertion).is(jsonPathValue))
    }
  }

  def thinkTimer(min: Int = 4, max: Int = 20): ChainBuilder = {
    pause(min, max)
  }

  def cleanScenario(): ChainBuilder = {
    exec(flushHttpCache)
      .exec(flushCookieJar)
      .exec(flushSessionCookies)
  }

  def httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")
}
