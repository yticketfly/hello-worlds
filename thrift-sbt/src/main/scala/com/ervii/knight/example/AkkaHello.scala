package com.ervii.knight.example

import akka.actor.{PoisonPill, Actor, ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class HelloActor extends Actor {
  Thread.sleep(10000)
  def receive = {
    case s: String => println(s.reverse)
    case _         => println("huh? what?!")
  }
}

object AkkaHello extends App {

  val system = ActorSystem("HelloSystem")

  val fhello = Future {
    println("hello *************** start")

    Thread.sleep(5000)

    import akka.pattern.ask
    implicit val timeout = Timeout(7.seconds)

    val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
    helloActor ! "Just say hi!"
    helloActor ? "future [hello]"
    helloActor ? "fture [buenos dias]"

    println("hello *************** end")
  }

  Await.result(fhello, 2.seconds)


  println("sleep 9999999")
  Thread.sleep(99999999)

  println("============ never ever ============ never ever")


}