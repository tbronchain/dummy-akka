import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinRouter

object HelloActor {
  case class SayHello(message: String)
}

class HelloActor extends Actor {
  def receive = {
    case HelloActor.SayHello(message) =>
      println(message)
  }
}

object Main extends App {
  val system = ActorSystem("ActorSystem")

  val hello = system.actorOf(Props[HelloActor])

  hello ! HelloActor.SayHello("Hello!")
}
