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
 * Assignment: Longest increasing subsequence
 *
 * Sequences are a natural source of computational problems. One such
 * family of problems involves finding subsequences with specified
 * properties in a given sequence. This exercise asks you to write 
 * a program that, given a sequence
 *
 *   s(0), s(1), ..., s(n-1)
 *
 * of integers as input, finds a ___longest increasing subsequence___ 
 * of the sequence s.
 * 
 * For example, suppose we are given as input the sequence
 * 
 *    72, 16, 51, 17, 6, 21, 92, 59, 54, 78, 41, 33, 94, 
 *        85, 83, 56, 2, 46, 57, 44, 73, 6, 47, 47, 0.
 *
 * In this sequence, a longest increasing subsequence has length 7. 
 * One example of such an increasing subsequence is 
 *
 *    16 < 17 < 21 < 54 < 56 < 57 < 73.
 *
 * More generally, your program must be such that 
 * given a sequence
 *
 *   s(0), s(1), ..., s(n-1)
 *
 * of integers as input, the program returns a subsequence 
 *
 *   s(i_1), s(i_2), ..., s(i_k)
 *
 * that meets all of the following three properties:
 * 
 *   1. The positions that define the subsequence are increasing:
 *
 *        0 <= i_1 < i_2 < ... < i_k <= n-1
 *
 *   2. The subsequence is increasing:
 *
 *        s(i_1) < s(i_2) < ... < s(i_k)
 *
 *   3. The length k of the subsequence is as large as possible.
 *
 *
 * Hints:
 *
 * You can solve this exercise using __dynamic programming__ as a 
 * solution strategy. That is, you __tabulate__ solutions of carefully
 * designed subproblems and use these solutions to gradually build your
 * way up to solve larger and larger subproblems until you solve 
 * the original problem. Here are some more detailed hints:
 *
 * a) Suppose that for all i = 0, 1, ..., j-1 we know L(i), the length 
 *    of a longest increasing subsequence in s(0), s(1), ..., s(i)
 *    that terminates at position i.
 * b) Suppose we have already computed in an array the values
 *    L(0), L(1), ..., L(j-1). How can we make use of these values 
 *    to compute the next value L(j) ? 
 * c) Furthermore, suppose for each i we know p(i), the position
 *    prior to the last position i in a longest increasing subsequence 
 *    that terminates at position i.
 *    (Note that such a position p(i) need not always exist, however.)
 * d) How can we make use of the values p(i) for i <= j to determine
 *    a concrete increasing subsequence of length L(j) 
 *    that terminates at j ?
 * e) Design your program to complete the arrays L and p, one position 
 *    at a time.
 * f) Use the arrays L and p to return a concrete longest increasing
 *    subsequence in the given sequence s.
 *
 */

package object longestIncreasingSubsequence {
  import scala.collection.mutable.Buffer
  /** Returns a longest increasing subsequence in the sequence s.
   *  If there are multiple such subsequences, any subsequence will do. */

  // model solution
  def longestIncreasingSubsequence(s: Seq[Int]): Seq[Int] = {
    require(s.length > 0)
    val lis = new Array[Int](s.length) // Array which records LIS length values
    val par = new Array[Int](s.length)
    for(i <- 0 until s.length){
      lis(i) = 1
      par(i) = s.length
      for(j <- 0 until i){
        if(s(j)<s(i) && lis(i) < lis(j) + 1){
          lis(i) = lis(j)+1
          par(i) = j
        }
      }
    }

    /* finds the index of max value in lis.
    *  could also be done with a loop where if(lis(i)>lis(m)) -> m = i.
    */
    var m = lis.zipWithIndex.maxBy(_._1)._2
    val ret = new Array[Int](lis(m))
    var i = lis(m)-1
    while(i >= 0){
      ret(i) = s(m)
      m = par(m)
      i -= 1
    }
    ret
  }

  // Alternative if done with buffers
  // def longestIncreasingSubsequence(s: Seq[Int]): Seq[Int] = {   
  //   var lis = Buffer.fill[Int](s.length)(1)
  //   var par = Buffer.fill[Int](s.length, 0)(0)
  //   for(i <- 0 until s.length){
  //     var best= 0;
  //     for(j <- 0 until i){
  //       if(s(j) < s(i) && best <= lis(j)){
  //         lis(i) = lis(j) + 1
  //         par(i).clear()
  //         par(j).copyToBuffer(par(i))
  //         best = lis(j)
  //       }
  //     }
  //     par(i).append(s(i))
  //   }
  //   par((lis.zipWithIndex.maxBy(_._1))._2)
  // }
}


