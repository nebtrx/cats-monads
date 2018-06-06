package com.github.nebtrx

import cats.syntax.either._

object EitherM extends App {

  // Errors
  sealed trait LoginError extends Product with Serializable

  final case class UserNotFound(username: String) extends LoginError

  final case class PasswordIncorrect(username: String) extends LoginError

  case object UnexpectedError extends LoginError

  // User
  case class User(username: String, password: String)

  // Result
  type LoginResult = Either[LoginError, User]

  def handleError(error: LoginError): Unit =
    error match {
      case UserNotFound(u) =>
        println(s"User not found: $u")

      case PasswordIncorrect(u) =>
        println(s"Password incorrect: $u")

      case UnexpectedError =>
        println(s"Unexpected error")
    }


  // Assign
  val result1: LoginResult = User("dave", "passw0rd").asRight
  // result1: LoginResult = Right(User(dave,passw0rd))

  val result2: LoginResult = UserNotFound("dave").asLeft
  // result2: LoginResult = Left(UserNotFound(dave))

  // Execute
  result1.fold(handleError, println)
  // User(dave,passw0rd)

  result2.fold(handleError, println)
  // User not found: dave”
}
