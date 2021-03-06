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

import scala.collection.immutable.Set

object Finder {
  /**
   * Find one "important" element in the domain of the tester.
   * If the domain contains no important elements, return None.
   * Use binary search to implement this.
   * Sets can be split with splitAt method (see http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Set).
   * Must make at most ceil(log(tester.domain)*2)+1 calls to tester.contains.
   */
  def findOne[T](tester: Tester[T]): Option[T] = ???
 
  /**
   * Find all the k "important" elements in the domain of the tester
   * (the value k is not known in advance!).
   * If the domain contains no important elements, return an empty set.
   * Use binary search to implement this.
   * Must make at most k*ceil(log(tester.domain)*2)+1 calls to tester.contains.
   */
  def findAll[T](tester: Tester[T]): Set[T] = ???
}

