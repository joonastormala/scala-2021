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

package object roots {
  import scala.math.pow

  /**
   * Find a zero points of a continuous function f (i.e., a point x such that f(x)=0.0)
   * when we know two points, lower and upper, such that f(lower) <= 0.0 and f(upper) >= 0.0.
   * Implement this with an algorithm using binary search.
   * Recall the limited precision of Doubles! That is, the new "middle point" can (and will)
   * at some point be equal to either to lower or upper point. You can stop the search when
   * this happens and return the better point (lower or upper limit point).
   *
   * The recommended programming style for this exercise is to use recursion
   * (please see the section "Binary Search" of Round 7 and Round 8 of the course material
   * if you are not familiar with recursion yet).
   */
  def zero(f: Double => Double, lower: Double, upper: Double): Double = {
    require(lower <= upper)
    require(f(lower) <= 0.0)
    require(f(upper) >= 0.0)
    ???
  }

  /**
   * Compute the n:th root of a non-negative Double x, i.e. the value v such that v to the n:th power equals to x.
   * Implement this by using the "zero" method above (hint: v^n = x if and only if ....... = 0.0)
   * but don't copy any code from there (this method can be implemented in one short line).
   */
  def nthRoot(n: Int)(x: Double): Double = ???
  
  /** Compute the square root of a non-negative Double x. */
  def squareRoot(x: Double): Double = nthRoot(2)(x)

  /** Compute the cube root of a non-negative Double x. */
  def cubeRoot(x: Double): Double = nthRoot(3)(x)
}


