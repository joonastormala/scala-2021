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

import scala.language.implicitConversions

package object expressions {
  /*
   * These implicit definitions allow us to write "2.0*x" instead of Num(2.0)*x,
   * where x is a Var object.
   */
  implicit def doubleToNum(v: Double) = Num(v)
  implicit def intToNum(v: Int) = Num(v)
}


