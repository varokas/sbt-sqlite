import java.sql.{DriverManager, ResultSet}

object Main {
  val conf = Config.instance();

  def main(args: Array[String]): Unit = {
    println("Running Liquibase")
    SchemaMigration.run()

    println("Running App")
    val url = conf.getString("jdbc.url");
    Class.forName(conf.getString("jdbc.class"))
    val connection = DriverManager.getConnection(url)

    connection.createStatement().execute(
      """INSERT INTO person (firstname, lastname, state)
         VALUES ('first', 'last', 'AK')
      """
    )
    val resultSet = connection.createStatement().executeQuery("SELECT * from person")

    while ( resultSet.next() ) {
      println(Person.apply(resultSet))
    }
  }
}

case class Person(id: Int, firstname: String, lastname: String, state:String) {

}
object Person {
  def apply(rs: ResultSet) = new Person(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("state"))
}