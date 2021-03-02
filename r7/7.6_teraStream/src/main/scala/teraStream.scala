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
 * Description:
 * This assignment asks you to study a stream of blocks, each of
 * which is an array of 64-bit words. The stream is implemented 
 * in the object inputStream, which has been instantiated
 * from class BlockStream (see below). Access to the stream is by
 * means of the Iterator interface in Scala, whose two methods
 * hasNext() and next() tell you whether there are more blocks
 * available in the stream, and if yes, return the next block in 
 * the stream, respectively. It is also possible to rewind() 
 * the stream back to its start. 
 *
 * The stream in this assignment is a long one, exactly one terabyte
 * (1099511627776 bytes) of data, divided into 1048576 blocks of 131072 
 * 64-bit words each. That is, 1048576 one-megabyte blocks. 
 *
 * Remark:
 * Observe that one terabyte of data is too much to store in main memory
 * on most computers. Thus, whatever you do, keep in mind that it is perhaps
 * __not__ wise to try to store all the data in memory, or save the data 
 * to disk.
 *
 * Hints:
 * Tasks 1 and 2 should not be extremely challenging. Think how to scan
 * through the blocks. Task 3 is the most challenging one, most likely
 * requiring multiple scans through the blocks, using the possibility 
 * to rewind(). Say, what if you looked at the most significant bits to 
 * identify what the values of those bits should be for the value you are 
 * looking for? What you should also observe is that scanning through 
 * one terabyte of data takes time, so perhaps it is a good idea to plan 
 * a bit and test your code with a somewhat smaller stream first. You
 * should probably reserve at least one hour of computer time to execute 
 * the computations for Task 3. 
 *
 */
package object teraStream {

  class BlockStream(numblocks: Int, blocklength: Int) extends Iterator[Array[Long]] {
    val modulus = (0L /: Array(0, 5, 8, 18, 22, 60).map(1L << _)) { _ | _ }
    val hi = 0x4000000000000000L
    val mask = 0x7FFFFFFFFFFFFFFFL
    val startval = 0xA3A3A3A3A3A3A3A3L & mask
    var current = startval
    var blockno = 0

    def rewind() {
      current = startval
      blockno = 0
    }

    def hasNext(): Boolean = blockno < numblocks

    def next(): Array[Long] = {
      require(blockno < numblocks)
      val blk = new Array[Long](blocklength)
      var i = 0
      while (i < blocklength) {
        blk(i) = current
        if ((current & hi) != 0L) {
          current = ((current << 1) & mask) ^ modulus
        } else {
          current = current << 1
        }
        i = i + 1
      }
      blockno = blockno + 1
      blk
    }
  }

  val inputStream = new BlockStream(1048576, 131072) // A one-terabyte stream
  val practiceStream = new BlockStream(1024, 128) // A one-megabyte stream

  /*
   * Task 1: 
   * Compute the minimum value in inputStream.
   * 
   */
  def minStream(s: BlockStream) = ???
  // your CODE for computing the minimum value  

  lazy val minValue = ???
  // the minimum VALUE that you have computed, e.g. 0x1234567890ABCDEFL

  /*
   * Task 2: 
   * Compute the maximum value in inputStream.
   * 
   */
  def maxStream(s: BlockStream) = ???
  // your CODE for computing the maximum value  

  lazy val maxValue = ???
  // the maximum VALUE that you have computed, e.g. 0x1234567890ABCDEFL

  /*
   * Task 3: 
   * Assume that all the blocks in inputStream have been concatenated,
   * and the resulting array has been sorted to increasing order, 
   * producing a sorted array of length 137438953472L. Compute the value at
   * position 68719476736L in the sorted array. (The minimum is at position 0L 
   * in the sorted array, the maximum at position 137438953471L.)
   *
   * (See the remarks and hints above before attempting this task!)
   * 
   */
  def posStream(s: BlockStream, pos: Long) = ???
  // your CODE for computing the value at SORTED position "pos" 
  // in the array obtained by concatenating the blocks of "s"
  // sorting the resulting array into increasing order

  lazy val posValue = ???
  // the VALUE at SORTED position 68719476736L that you have computed, 
  // e.g. 0x1234567890ABCDEFL

  
}


