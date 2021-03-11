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
 * Assignment:  Caveat floating point
 * 
 * Description:
 * This assignment asks you to study the rules of floating-point 
 * arithmetic well enough so that you understand some of its 
 * caveats. 
 *
 */

package object floatCaveat {

  /* 
   * Task: 
   * Find three values x,y,z of type Double, such that
   * (x+y)+z == 1.0 and x+(y+z) == 0.0
   *
   */
  val x: Double = Math.pow(2,64)
  val y: Double = -x
  val z: Double = 1.0
  
}


