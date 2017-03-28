package scala.basics

/**
  * Created by abhishek on 28/03/17.
  */
object TraitDiamondProblem extends App {

  trait Alphabets {
    def compute
  }

  trait A extends Alphabets{
    override def compute = println("A")
  }

  trait B extends Alphabets{
    override def compute = println("B")
  }

  class C extends A with B

  val r = new C

  r.compute
}
