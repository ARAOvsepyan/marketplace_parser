import parser.{AliexpressParser, WildberriesParser}
import parser.dto.StartDto



object Main extends App {
  val resultset = "catalog"
  val query = "женщинам"
  val sort = "popular"

  val data =  StartDto(resultset, query, sort)

  WildberriesParser.start(data)

  AliexpressParser.parseCategoriesPage()
}
