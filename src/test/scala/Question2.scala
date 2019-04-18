import org.scalatest.FunSuite

class Question2 extends FunSuite {

  val f1: ( Int , Int ) => Int = (a, b) => a + b
  val f2: Int => String = _.toString
  val f3: ( Int , Int ) => String = (a,b) => f2(f1(a,b))

  test("Given two functions f1 and f2, implement f3 by composing f1 and f2") {
    val sum = f3(1,2)
    assert(sum == "3")
  }
}
