package config

object Constants {

  val ROOT_API = "http://localhost:8080";
  val SHOPIZER_API =  ROOT_API + "/api/v1";
  val STORE = "${store}"
  val STORE_VARIABLE = "store"
  val LANG = "en";
  val COUNT = 20;
  val PAGE = 0;
  val TABLES_ID = 50;
  val CHAIRS_ID = 51;
  val VAR_PRODUCT_CODE = "productCode"
  val PRODUCT_CODE = "${productCode}"
  val CATEGORY_TABLE_ID = "CATEGORY_TABLE_ID"
  val CATEGORY_TABLE_NAME = "CATEGORY_TABLE_NAME"
  val CATEGORY_TABLE_ALIAS = "CATEGORY_TABLE_ALIAS"
  val CATEGORY_CHAIR_ID = "CATEGORY_CHAIR_ID"
  val CATEGORY_CHAIR_NAME = "CATEGORY_CHAIR_NAME"
  val CATEGORY_CHAIR_ALIAS = "CATEGORY_CHAIR_ALIAS"

  /**
   *  SCENARIO AND GROUP NAMES
   */
  val COMMON_SCENARIO = "Common Scenario"
  val OPEN_APP = "Open application"
  val NAVIGATE_TO_TABLES = "Navigate to 'Tables' tab"
  val OPEN_TABLE = "Open table"
  val ADD_TABLE_TO_CART = "Add table to cart"
  val FIFITY_PERCENT_USERS = "50% of users"
  val GO_TO_CHAIR = "Go to Chair"
  val OPEN_RANDOM_CHAIR = "Open random chair"
  val ADD_CHAIR_TO_CHART = "Add chair to cart"
  val THIRTY_PERCENT_USERS = "30% of all users"
  val OPEN_CART = "Open Cart"
  val CHECKOUT = "Checkout"

  /**
   * URL'S
   */
  val CATEGORY_URL = SHOPIZER_API + s"/products/?&store=$STORE&lang=$LANG&page=$PAGE&count=$COUNT&category="
  val TABLES_URL = CATEGORY_URL + TABLES_ID
  val CHAIRS_URL = CATEGORY_URL + CHAIRS_ID
  val STORE_URL = SHOPIZER_API + s"/store/$STORE"
  val CATEGORY_PAGE_COUNT_URL = SHOPIZER_API + s"/category/?count=$COUNT&page=$PAGE&store=$STORE&lang=$LANG"
  val TOTAL_PAGES_URL =  SHOPIZER_API + s"/content/pages/?page=$PAGE&count=$COUNT&store=$STORE&lang=$LANG"
  val PRODUCTS_BY_GROUP_URL = SHOPIZER_API + s"/products/group/FEATURED_ITEM?store=DEFAULT&lang=$LANG"


  def postProductPriceUrl(productId: String): String = {
    SHOPIZER_API + s"/product/$productId/price/"
  }

  def postProductToCartUrl(): String = {
    SHOPIZER_API + s"/cart/?store=$STORE"
  }

  def getCategoryUrlByIdStoreLang(categoryId: String): String = {
    SHOPIZER_API + s"/category/$categoryId?store=$STORE&lang=$LANG"
  }

  def getCategoryManufacturesUrlById(categoryId: String): String = {
    SHOPIZER_API + s"/category/$categoryId/manufacturers/?store=$STORE&lang=$LANG"
  }

  def getProductUrlById(productId: Int): String = {
    SHOPIZER_API + s"/products/$productId?lang=$LANG&store=$STORE"
  }

  def getProductReviewsByIdUrl(productId: Int): String = {
    SHOPIZER_API + s"/products/$productId/reviews?store=$STORE"
  }

  def geCartProductByCodeUlr(productCode: String): String = {
    SHOPIZER_API + s"/cart/$productCode?lang=$LANG"
  }

  def geCartByProductCodeStoreUlr(productCode: String): String = {
    SHOPIZER_API + s"/cart/$productCode?store=$STORE"
  }

  def getCartTotalByProductCodeUrl(productCode: String): String = {
    SHOPIZER_API + s"/cart/$productCode/total/"
  }

  def getZonesUlr(): String = {
    SHOPIZER_API + s"/zones/?code="
  }

  def getConfigUrl(): String = {
    SHOPIZER_API + s"/config/"
  }

  def getShippingCountryUrl(): String = {
    SHOPIZER_API + s"/shipping/country?store=$STORE&lang=$LANG"
  }
}
