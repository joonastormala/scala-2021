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
package oscillator

/*
 * Run this object to play with oscillators in Trigger.
 */
object play {
  def main(args: Array[String]): Unit = {

    /** Create new Circuit and Trigger instances. */
    val circuit = new Circuit()
    val trigger = new Trigger(circuit)
    
    /** Oscillator with period 2 (full circuit state is shown). */
    val stateP2: Bus = factory.buildOscillatorPeriod2(circuit)
    trigger.watch("State of an oscillator with period 2", stateP2.reverse)
    
    /** Oscillator with period 3 (full circuit state is shown). */
    val stateP3: Bus = factory.buildOscillatorPeriod3(circuit)
    trigger.watch("State of an oscillator with period 3", stateP3.reverse)
    
    /** Oscillator with period 4 (only the output is shown). */
    // val outputP4: Gate = factory.buildOscillatorPeriod4(circuit)
    // trigger.watch("Output of an oscillator with period 4", outputP4)

    /** Oscillator with a user-defined period (only the output is shown). */
    // val period = 3
    // val output: Gate = factory.buildOscillator(circuit, period)
    // trigger.watch("Output of an oscillator with period %d".format(period), output)
 	
    /** Go Trigger. */
    trigger.go()
  }
}


