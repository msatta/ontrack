import java.util.InputMismatchException

import org.scalatest.{FlatSpec, FunSuite}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Try

class Question4 extends FunSuite {

  def f [ A ](a: A ): Future [ A ] =
    a match {
      case _: Int => Future.successful(a)
      case _: Double => Future {
        Thread.sleep(6000); a
      }
      case _ => Future.failed(new InputMismatchException())
    }

  def g[B](b:B):Try[B] = Try {
    val future = f(b)
    Await.result(future, 5 seconds)
  }

  test("The function `g` should safely handle calling f") {

    val resultInt = g(10)
    val resultDouble = g(10.0)
    val resultString = g("hello")

    assert(resultInt.isSuccess)
    assert(resultDouble.isFailure)
    assert(resultString.isFailure)
  }

}
