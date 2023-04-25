package config

class Category( id:String, name: String, alias:String, jsonPathValue:String, url: String, ids:Array[Int])  {

  def id(): String = {
    "${"+id+"}"
  }

  def name(): String ={
    name
  }

  def alias(): String = {
    alias
  }

  def getUrl(): String = {
    url
  }

  def getIds(): Array[Int] = {
    ids
  }
  def getJsonPathValue():String = {
    "${"+jsonPathValue+"}"
  }
}
