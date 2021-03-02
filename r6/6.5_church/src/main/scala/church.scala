/*
  * This program text file is part of the CS-A1120 Programming 2 course
  * materials at Aalto University in Spring 2021. The programming exercises
  * at CS-A1120 are individual and confidential assignments---this means
  * that as a student taking the course you are allowed to individually
  * and confidentially work with the material, to discuss and review the
  * material with course staff, as well as to submit the material for grading
  * on course infrastructure. All other use, including in particular
  * distribution of the material or exercise solutions, is forbidden and
  * constitutes a violation of the code of conduct at this course.
  *
  */

package object church {
  type X = Any
  type Y = X => X
  type Z = Y => Y

  val zero: Z = y => x => x
  val succ: Z => Z = z => y => z(y) compose y
  val add: (Z, Z) => Z = ???
  val mul: (Z, Z) => Z = ???
  val asX: (Y, X) => (Z => X) = (y, x) => z => z(y)(x)

  val asInt = asX({ case x: Int => x + 1 }, 0)
}


