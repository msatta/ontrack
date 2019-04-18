import cats.FlatMap
import cats.implicits._
import org.scalatest.FunSuite

trait MyAlg[F[_]]{
  def insertItSomewhere (someInt: Int ): F [ Unit ]
  def doSomething (someInt: Int ): F [ Int ]
}

abstract class MyProg[F[_] : FlatMap] extends MyAlg[F] {
  def checkThenAddIt (someInt: Int ) = doSomething(someInt).flatMap(int => insertItSomewhere(int))
}

class Question6Test extends FunSuite {

  test("create a class that extends MyProg") {
    class MyClass extends MyProg[Option] {
      override def insertItSomewhere(someInt: Int): Option[Unit] = if (someInt > 1) Some() else None
      override def doSomething(someInt: Int): Option[Int] = Some(someInt + 1)
    }

    val myObj = new MyClass
    assert(myObj.checkThenAddIt(1) == Some())
    assert(myObj.checkThenAddIt(0).isEmpty)
  }
}
