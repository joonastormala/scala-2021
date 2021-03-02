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

package object pairSum {
  /**
   * Find whether there is an integer v1 in list l1 and an integer v2 in list l2
   * such that v1 + v2 == target.
   * If such a pair exists, return it [with "Some((v1,v2))"]; otherwise, return None.
   * This function is allowed to run in time O(l1.length * l2.length).
   */
  def hasPairSlow(l1: List[Int], l2: List[Int], target: Int): Option[(Int, Int)] = ???

  /**
   * A faster version of hasPairSlow, must run in time
   * O(l1.length*log(l1.length) + l2.length*log(l2.length)).
   * Find whether there is an integer v1 in list l1 and an integer v2 in list l2
   * such that v1 + v2 == target.
   * If such a pair exists, return it [with "Some(( v1,v2))"]; otherwise, return None.
   */
  def hasPair(l1: List[Int], l2: List[Int], target: Int): Option[(Int, Int)] = ???

}


