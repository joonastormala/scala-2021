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
package lfsr

/*
 * Run this object to play with LFSRs in Trigger.
 *
 */

object play {
  def main(args: Array[String]): Unit = {

    /** Create new Circuit and Trigger instances. */
    val circuit = new Circuit()
    val trigger = new Trigger(circuit)
    
    /** Rotator unit. */
    // val state1 = circuit.inputs(4)
    // val rotator = factory.buildRotator(state1)
    // state1(0).set(true)
    // trigger.watch("Rotator", rotator.reverse)
    
    /** A maximal 5-bit LFSR. */
    // val state2 = circuit.inputs(5)
    // val fiveBitLFSR = factory.build5BitLFSR(state2)
    // state2(0).set(true)
    // trigger.watch("5-bit LFSR", fiveBitLFSR.reverse)
    
    /** LFSR unit. */
    // val state3 = circuit.inputs(4)
    // val taps = List(1)
    // val LFSR = factory.buildLFSR(taps, state3)
    // state3(0).set(true)
    // trigger.watch("LFSR", LFSR.reverse)
 	
    /** Go Trigger. */
    // trigger.go()
  }
}

