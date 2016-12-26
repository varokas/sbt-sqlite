import com.typesafe.config.ConfigFactory

object Config {
  val config = ConfigFactory.load()

  def instance():com.typesafe.config.Config = {
    return config
  }
}
