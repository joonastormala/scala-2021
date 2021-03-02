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

package minilog
package pipelining

/*
 * Run this object to play with a pipelined multiplier in Trigger.
 */
object play {
  def main(args: Array[String]): Unit = {

    /** Create new Circuit and Trigger instances. */
    val circuit = new Circuit()
    val trigger = new Trigger(circuit)
    
    /** Set the parameters and build the pipelined multiplier. */
    val n = 16
    val input1 = circuit.inputs(n)
    val input2 = circuit.inputs(n)
    // val output = factory.buildPipelinedMultiplier(input1, input2)

    /** Set up Trigger. */
    // trigger.watch("Input 1", input1.reverse)
    // trigger.watch("Input 2", input2.reverse)
    // trigger.watch("Output", output.reverse)
    
    /** Launch Trigger. */
    // trigger.go()
  }
}


