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

package object pascal {
  /**
   * Returns the k:th value in the n:th row of the Pascal's triangle
   * (that is, the binomial coefficient (n k), or the number of how many ways
   *  we can select k items from a set of n items).
   * For more information, see e.g.
   * - http://en.wikipedia.org/wiki/Pascal%27s_triangle
   * - http://en.wikipedia.org/wiki/Binomial_coefficient
   *
   * As this is a basic exercise on recursion,
   * you are not allowed to use var-declarations, Arrays or
   * any scala.collection.mutable data types.
   */
  def getCoefficient(n: Int, k: Int): Int = {
    require(n >= 0)
    require(0 <= k && k <= n)
    if(k==0||k==n) return 1
    getCoefficient(n-1,k-1)+getCoefficient(n-1,k)
  }
}


