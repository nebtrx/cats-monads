package com.github.nebtrx

object ErrorM extends App {

  import cats.syntax.applicative._        // for pure
  import cats.instances.either._          // for MonadError
  import cats.syntax.applicativeError._   // for raiseError etc
  import cats.syntax.monadError._         // for ensure
  import scala.util.Try
  import cats.instances.try_._            // for MonadError

  type ErrorOr[A] = Either[String, A]


  val success = 42.pure[ErrorOr]
  // success: ErrorOr[Int] = Right(42)

  val failure = "Badness".raiseError[ErrorOr, Int]
  // failure: ErrorOr[Int] = Left(Badness)

  success.ensure("Number to low!")(_ > 1000)
  // res4: Either[String,Int] = Left(Number to low!)

  val exn: Throwable = new RuntimeException("It's all gone wrong")

  exn.raiseError[Try, Int]
  // res6: scala.util.Try[Int] = Failure(java.lang.RuntimeException: It's all gone wrong)

}
