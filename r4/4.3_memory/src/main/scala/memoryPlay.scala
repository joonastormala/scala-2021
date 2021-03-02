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
package memory

/*
 * Run this object to play with memory units in Trigger.
 */
object play {
  def main(args: Array[String]): Unit = {

    /** Create new Circuit and Trigger instances. */
    val circuit = new Circuit()
    val trigger = new Trigger(circuit)
    
    /** Address and data word lengths in bits. */
    val addressLength = 4
    val dataLength = 16

    /** Create the parameters for the memory units. The address parameter is not 
        needed for the one-word memory unit. */
    val readEnable  = circuit.input()
    val writeEnable = circuit.input()
    val address = circuit.inputs(addressLength)
    val data = circuit.inputs(dataLength)
    
    /** Build the one-word memory unit. */
    // val readOutput = factory.buildOneWordMemory(readEnable, writeEnable, data)

    /** Build the multiple-word memory unit. */
    // val readOutput = factory.buildMemory(readEnable, writeEnable, address, data)

    /** Set up Trigger. */
    // trigger.watch("readEnable", readEnable)
    // trigger.watch("writeEnable", writeEnable)
    // trigger.watch("address", address.reverse)   // Used only in a multiple-word memory
    // trigger.watch("data", data.reverse)
    // trigger.watch("readOutput", readOutput.reverse)    
    
    /** Go Trigger. */
    // trigger.go()
  }
}


