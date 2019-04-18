import org.scalatest.{FlatSpec, FunSuite}

class Question3 extends FunSuite {

  def increment(seq:Seq[Int]) : Seq[Int] = seq match {
    case Nil                       => Seq(1)
    case head :: tail if head < 9  => Seq(head + 1) ++ tail
    case _ :: tail                 => Seq(0) ++ increment(tail)
  }

  def incrementByOne(seq: Seq[Int]) : Seq[Int] = seq match {
    case Nil => Nil
    case _ => increment(seq.reverse).reverse
  }

  test("Given a list Seq(1, 2, 3) increment it by one without converting types.") {

    assert(incrementByOne(Nil) == Nil)
    assert(incrementByOne(Seq(0)) == Seq(1))
    assert(incrementByOne(Seq ( 1 , 2 , 3 )) == Seq ( 1 , 2 , 4 ))
    assert(incrementByOne(Seq ( 9 , 9 , 9 )) == Seq ( 1 , 0 , 0 , 0 ))
  }

}
