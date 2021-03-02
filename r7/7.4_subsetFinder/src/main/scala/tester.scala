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

package subsetFinder

/**
 * Abstract base class for testers.
 */
abstract class Tester[T] {
  /**
   * The domain containing all the elements under consideration.
   * Some of the elements can be "important".
   */
  def domain: scala.collection.immutable.Set[T]

  /**
   * Returns true if s contains at least one "important" element.
   * The set s must be a subset of the domain.
   */
  def contains(s: Set[T]): Boolean

  /**
   * Returns the number of calls made to the "contains" method so far.
   */
  def nofCalls: Int
}

