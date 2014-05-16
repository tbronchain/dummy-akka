import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinRouter

object Messages {
  case class SayHello(message: String)
  case class Answer(message: String)
}

class HelloActor extends Actor {
  def receive = {
    case Messages.SayHello(message) =>
      val helloSon = context.actorOf(Props[HelloSon])
      helloSon ! Messages.SayHello(message)
    case Messages.Answer(message) =>
      println("Father: "+message)
      Thread.sleep(2000)
  }
}

class HelloSon extends Actor {
  def receive = {
    case Messages.SayHello(message) =>
      println("Son: "+message)
      Thread.sleep(1000)
      sender() ! Messages.Answer("Done.")
  }
}

object Main extends App {
  val system = ActorSystem("ActorSystem")

  val hello1 = system.actorOf(Props[HelloActor], "hello1")
  val hello2 = system.actorOf(Props[HelloActor], "hello2")

  hello1 ! Messages.SayHello("Hello!")
  hello1 ! Messages.SayHello("Hello World1!")
  hello2 ! Messages.SayHello("Hello World2!")
}
