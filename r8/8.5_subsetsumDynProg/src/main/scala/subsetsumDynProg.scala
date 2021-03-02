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

package object subsetsumDynProg {
  
  /**
   * Solve the subset sum problem with dynamic programming.
   * Dynamic programming works in cases where the amount of sums that can be formed is
   * still reasonable (in the order of millions).
   */
  def solve(set: Set[Int], target: Int): Option[Set[Int]] = ???
 
  
  /*
   * The rest of the code includes the recursive backtracking search version
   * given in the course material.
   * This is only for reference, you don't need to use or modify it in any way.
   */
  /** Select an arbitrary element in s */
  def selectElementSimple(s: Set[Int], t: Int) = {
    require(!s.isEmpty)
    s.head
  }
  /** Select an element in s in a greedy way */
  def selectElementGreedy(s: Set[Int], t: Int) = {
    require(!s.isEmpty)
    if (t > 0) s.max
    else s.min
  }

  /**
   * Solve the subset sum problem with recursion.
   * The argument function heuristics is a function that, when called with
   * a non-empty set s and value t, returns an element in s.
   */
  def solveBacktrackingSearch(set: Set[Int], target: Int, elementSelector: (Set[Int], Int) => Int = selectElementSimple): Option[Set[Int]] = {
    def inner(s: Set[Int], t: Int): Option[Set[Int]] = {
      if (t == 0)
        // An empty set sums up to t when t = 0
        return Some(Set[Int]())
      else if (s.isEmpty)
        // An empty set cannot sum up to t when t != 0
        return None
      else if (s.filter(_ > 0).sum < t || s.filter(_ < 0).sum > t)
        // The positive (negative) number cannot add up (down) to t
        return None
      // Select one element in the set
      val e = elementSelector(s, t)
      val rest = s - e
      // Search for a solution without e
      val solNotIn = inner(rest, t)
      if (solNotIn.nonEmpty) return solNotIn
      // Search for a solution with e
      val solIn = inner(rest, t - e)
      if (solIn.nonEmpty) return Some(solIn.get + e)
      // No solution found here, backtrack
      return None
    }
    inner(set, target)
  }
}


