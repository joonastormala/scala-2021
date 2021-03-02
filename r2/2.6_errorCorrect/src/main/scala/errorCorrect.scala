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
 * Assignment:  Single bit-error correction
 *
 * Motivation:
 * While individual computers are fairly reliable devices, hardware 
 * failures become a regular occurrence as the system size is scaled up 
 * from an individual computer to, say, a warehouse-scale compute cluster.
 * When designing such systems, careful attention needs to be paid to
 * __fault tolerance__. That is, the system must be engineered to 
 * automatically and transparently recover from small hardware failures. 
 * Individual bit errors in DRAM (dynamic random access memory) modules 
 * are among such failures, as you can discover from the following 
 * large-scale field study available at Google Research:
 * 
 * http://research.google.com/pubs/pub35162.html
 *
 * Description:
 * This assignment asks you to study the principles of error-correcting
 * codes, after which you get to design your own error-correction scheme
 * to protect data stored in a (hypothetical) DRAM module from bit-errors.
 * (We only give you the specification that your design needs to meet.)
 *
 * Hint: 
 * To successfully solve this assignment, you may want to study, for 
 * example, the Hamming codes.
 *
 * http://en.wikipedia.org/wiki/Hamming_code
 *
 */

package object errorCorrect {

  /*
   * Task: Single bit-error correction
   *
   * Suppose you have available a memory module whose storage elements are 
   * 40-bit words. Each storage element is subject to bit errors, that is,
   * each bit in the 40-bit word may change its state from 0 to 1 or vice 
   * versa in a manner that we as system designers cannot control. 
   *
   * Using the 40-bit storage elements, we want implement a storage
   * scheme for 32-bit data words that is able to always recover the 
   * original data word if __at most one bit error__ has occurred during 
   * storage. That is, any individual bit in the storage element may 
   * experience a bit error, and we must still be able to recover the 
   * original data word from the bits stored in the storage element.
   * In particular, this requires that we __encode__ the 32-bit word
   * into a 40-bit word that gets saved into the storage element,
   * and __decode__ the 40-bit word loaded from the storage element 
   * back into the 32-bit word. Decoding must always succeed if at most
   * one bit error has occurred during storage. (The result of decoding
   * may be arbitrary if there has been more than one bit error.) 
   *
   * This task asks you to design encoding and decoding functions
   * that implement the previous specification.
   *
   */

  /** Returns, in the least significant 40 bits of the return value,
   *  the 40-bit encoded form of the 32-bit data word given as parameter. */

  def SECEncode(d: Int): Long = ???

  /** Returns the 32-bit data word encoded in the least significant 40 bits
   *  of the parameter s. Decoding must always succeed if at most one
   *  bit error has occurred in the least significant 40 bits of the 
   *  parameter s. */

  def SECDecode(s: Long): Int = ???
  
}


