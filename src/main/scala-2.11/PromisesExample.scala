import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by abhishek on 27/01/17.
  */


//A promise can be used to successfully complete a future with a value
// using the success method.

// Conversely, a promise can also be used to complete a future with an exception,
// by failing the promise, using the failure method

object PromisesExample extends App {

  //Promise to hold string value

  //When the promise object is created, it does not contain a value or an exception.
  // To assign a value or an exception to a promise, we use the success or failure method, respectively.


  val environment = System.getProperty("environment")
  println("----------------" + environment)

  val p = Promise[String]
  val q = Promise[String]

  val f = p.future

  f foreach { case x => println(s"p succeeded with '$x'") }
  p success "assigned"

  q failure new Exception("not kept")
  q.future.failed foreach { case t => println(s"q failed with $t") }

  Thread.sleep(1000)
}
