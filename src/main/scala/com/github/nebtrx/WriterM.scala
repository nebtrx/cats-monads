package com.github.nebtrx


object WriterM extends App {
  import cats.instances.vector._     // for Monoid
  import cats.syntax.applicative._   // for pure
  import cats.data.Writer
  import cats.syntax.writer._


  type Logged[A] = Writer[Vector[String], A]

  val writer1 = for {
    a <- 10.pure[Logged]
    _ <- Vector("a", "b", "c").tell
    b <- 32.writer(Vector("x", "y", "z"))
  } yield a + b
  // writer1: cats.data.WriterT[cats.Id,Vector[String],Int] = WriterT((Vector(a, b, c, x, y, z),42))

  println(writer1.run)
  // res4: cats.Id[(Vector[String], Int)] = (Vector(a, b, c, x, y, z),42)
}
