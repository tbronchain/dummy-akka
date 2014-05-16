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
      Thread.sleep(1000)
  }
}

object Main extends App {
  val system = ActorSystem("ActorSystem")

  val hello1 = system.actorOf(Props[HelloActor], "hello1")
  val hello2 = system.actorOf(Props[HelloActor], "hello2")

  hello1 ! HelloActor.SayHello("Hello!")
  hello1 ! HelloActor.SayHello("Hello World1!")
  hello2 ! HelloActor.SayHello("Hello World2!")
}
