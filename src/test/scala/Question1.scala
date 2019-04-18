import org.scalatest.FunSuite

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class Question1 extends FunSuite {

  val timeout = 10 seconds

  def f1: Future[Unit] = Future {
    Thread.sleep(400)
    println("f1 done")
  }

  def f2: Future[Unit] = Future{
    Thread.sleep(300)
    println("f2 done")
  }

  def f3: Future[Unit] = Future{
    Thread.sleep(200)
    println("f3 done")
  }
  def f4: Future[Unit] = Future{
    Thread.sleep(100)
    println("f4 done")
  }

  // no dependencies between the functions
  def noDependencies() : Unit = {
    val f1Val: Future[Unit] = f1
    val f2Val: Future[Unit] = f2
    val f3Val: Future[Unit] = f3
    val f4Val: Future[Unit] = f4

    Await.result(f1Val, timeout)
    Await.result(f2Val, timeout)
    Await.result(f3Val, timeout)
    Await.result(f4Val, timeout)
  }

  def dependencies() = {
    val result = for {
      f1Val <- f1
      f2Val <- f2
      f3Val <- f3
      f4Val <- f4
    } yield {
      f4Val
    }

    Await.result(result, timeout)
  }

  def crossDependencies():Unit = {
    val result = for {
      f1Val <- f1
      (f2Val,f3Val) <- f2.zip(f3)
      f4Val <- f4
    } yield {
      f4Val
    }

    Await.result(result, timeout)
  }


  test("no dependencies between the functions"){
    info("Execute the functions in parallel. The function will terminate from the fastest to the slower")
    noDependencies()
  }

  test("f4 depends on f3 which depends on f2 which depends on f1"){
    info("Execute the functions according to the order from the first to the forth")
    dependencies()
  }

  test("f4 depends on f3 and f2, and f3 and f2 both depend on f1"){
    info("Execute the functions according to the order: f1 for first, than f2 and f3 in parallel and f4 once the f2 and f3 are done")
    crossDependencies()
  }
}
