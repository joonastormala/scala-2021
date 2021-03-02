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

package tinylog
package shallowOps

/*
 * Run this object to play with your design in Toggler.
 * 
 */ 
object play {
  def main(args: Array[String]): Unit = {
    val toggler = new Toggler()
    val n = 4
    val aa = Bus.inputs(n)
    val bb = Bus.inputs(n)
    val cc = factory.buildAdder(aa, bb)
// Uncomment here to work with your design
//    val cc = factory.buildShallowAdder(aa, bb)
    toggler.watch("aa", aa.reverse)
    toggler.watch("bb", bb.reverse)
    toggler.watch("cc", cc.reverse)
    toggler.go()
  }

}


