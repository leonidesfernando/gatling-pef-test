package api

import config.BaseHelper._
import config.Constants._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure._


object shopizerEndpoints {

  def goToCategory(reqName: String, catUrl:String, categoryAlias: String): ChainBuilder = {
    exec(
      get(s"Category $reqName" , catUrl, "$.products")
        .check(jsonPath("$.products[0].refSku").saveAs(categoryAlias))
    )
  }

  def getStore(reqName: String = "Default Store"): ChainBuilder = {
    exec(get(reqName, STORE_URL).check(jsonPath("$.code").saveAs(STORE)))
  }

  def getCategoryPageCount(reqName: String = "Category Page Count"): ChainBuilder = {
    exec(
      get(reqName, CATEGORY_PAGE_COUNT_URL, "$.categories")
        .check(jsonPath("$.categories[1].id").saveAs(CATEGORY_TABLE_ID))
        .check(jsonPath("$.categories[1].code").saveAs(CATEGORY_TABLE_NAME))
        .check(jsonPath("$.categories[2].id").saveAs(CATEGORY_CHAIR_ID))
        .check(jsonPath("$.categories[2].code").saveAs(CATEGORY_CHAIR_NAME))
    )
  }

  def getTotalPages(reqName: String = "Total pages"): ChainBuilder = {
    exec(get(reqName, TOTAL_PAGES_URL, "$.totalPages", "0"))
  }

  def postProductPrice(productId: Int, reqName: String = "Product price"): ChainBuilder = {
    exec(post(reqName, """{"options":[]}""", postProductPriceUrl(productId.toString)))
  }

  def postProductToCart(product:String, reqName: String = "Post product to cart"): ChainBuilder = {
    val body = s"""{"attributes":[],"product":"$product","quantity":1}"""
    exec(
      post(reqName, body, postProductToCartUrl(), "$.code")
        .check(jsonPath("$.code").saveAs(VAR_PRODUCT_CODE))
    )
  }

  def geCartProductByCode(reqName:String = "Cart product"): ChainBuilder = {
    exec(get(reqName, geCartProductByCodeUlr(PRODUCT_CODE), "$.code", PRODUCT_CODE))
  }

  def geCartByProductCodeStore(reqName: String = "Cart"): ChainBuilder = {
    exec(get(reqName, geCartByProductCodeStoreUlr(PRODUCT_CODE), "$.code", PRODUCT_CODE))
  }

  def getCategoryByIdStoreLang(categoryId: String, jsonPathValue:String = null, reqName:String = "Category by Id Store Lang"): ChainBuilder = {
    exec(get(reqName, getCategoryUrlByIdStoreLang(categoryId), "$.code", jsonPathValue))
  }

  def getCategoryManufacturesById(categoryId: String, jsonPathValue: String = null, reqName: String = "Category by Manufactures"): ChainBuilder = {
    exec(get(reqName, getCategoryManufacturesUrlById(categoryId), "$[0].code", jsonPathValue))
  }

  def getProductsByGroup(reqName: String = "Products by group"):ChainBuilder  = {
    exec(
       get(reqName, PRODUCTS_BY_GROUP_URL)
         .check(jsonPath("$.products[0].manufacturer.code").saveAs(STORE_VARIABLE))
         .check(jmesPath("products[*].id").saveAs("PRODUCTS_ID_HOME"))
    )
  }

  def getProductById(productId: Int, reqName:String = "Product by Id"): ChainBuilder = {
    exec(get(reqName, getProductUrlById(productId), "$.available", "true"))
  }

  def getProductReviewsById(productId: Int, reqName: String = "Product reviews"): ChainBuilder = {
    exec(get(reqName, getProductReviewsByIdUrl(productId)))
  }

  def getZones(reqName:String = "Get zone"): ChainBuilder = {
    exec(get(reqName, getZonesUlr()))
  }

  def getConfig(reqName: String = "Get config"): ChainBuilder = {
    exec(get(reqName, getConfigUrl(), "$.allowOnlinePurchase", "true"))
  }

  def getCartTotalByProductCode(reqName: String = "Get Total"): ChainBuilder = {
    exec(get(reqName, getCartTotalByProductCodeUrl(PRODUCT_CODE), "$.totals[0].title", "Subtotal"))
  }

  def getShippingCountry(reqName: String = "Get shipping countries"): ChainBuilder = {
    exec(get(reqName, getShippingCountryUrl(),"$[0].supported", "true"))
  }
}
