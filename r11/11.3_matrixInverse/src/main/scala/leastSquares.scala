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

/* 
 * This is 'leastSquares.scala'. 
 * See "Task 2" below for your task.
 * (Task 1 is in 'matrix.scala' -- you should solve Task 1 first)
 *
 */

package matrixInverse

/*
 * Task 2:
 * This task will put your matrix inversion routine into action
 * with least squares estimation. 
 * Here we ask you to implement a method that fits a polynomial 
 *
 *   p(x) = a(0) + a(1)x + a(2)x^2 + a(3)x^3 + ... + a(d)x^d 
 *
 * with degree d to n data points 
 *
 *   (x(0),y(0)),(x(1),y(1)),...,(x(n-1),y(n-1))
 *
 * so that the sum of squares of errors to data is minimized.
 * That is, our goal is to minimize the sum of (p(x(i))-y(i))^2 over 
 * i=0,1,...,n-1. 
 *
 * Hint: 
 * If you have implemented the matrix inversion method, essentially
 * all you need to do is to set up two matrices, X and y, where y
 * is an n-by-1 matrix (a vector) with the y-coordinates y(i)
 * and X is an n-by-(d+1) matrix whose ith row is 
 * x(i)^0,x(i)^1,x(i)^2,...,x(i)^d. 
 * Then the polynomial can be found with a few basic matrix
 * operations. See here:
 *
 * http://en.wikipedia.org/wiki/Polynomial_regression
 * http://en.wikipedia.org/wiki/Ordinary_least_squares
 *
 */

object leastSquares {
  def fitPolynomial(d: Int, x: Array[Double], y: Array[Double]) = {
    require(d > 0 && x.length == y.length)
    val n = x.length
    val a = new Array[Double](d+1)

    ??? // your code here

    a // return the coefficients a(0),a(1),...,a(d) of the polynomial
  }
}

