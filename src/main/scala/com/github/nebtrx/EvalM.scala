package com.github.nebtrx


object EvalM extends App {
  import cats.Eval

  val ans = for {
    a <- Eval.now {
      println("Calculating A"); 40
    }
    b <- Eval.always {
      println("Calculating B"); 2
    }
  }  yield {
    println("Adding A and B")
    a + b
  }
  // Calculating A
  // ans: cats.Eval[Int] = cats.Eval$$anon$8@2d96144d

  println(ans.value) // first access
  // Calculating B
  // Adding A and B
  // res16: Int = 42

  println(ans.value) // second access
  // Calculating B
  // Adding A and B
  // res17: Int = 42

  def factorial(n: BigInt): Eval[BigInt] =
    if(n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }

  println(factorial(50000).value)

  // res20: BigInt = 334732050959714483691547609407148647791277322381045480773010032199016802214436564169738123107191693087984804381902082998936163847430666937426305728453637840383257562821233599872682440782359723560408538544413733837535685655363711683274051660761551659214061560754612942017905674796654986292422200225415535107181598016154764518106166749702179965374749725411393381916388235006303076442568748572713946510819098749096
}
