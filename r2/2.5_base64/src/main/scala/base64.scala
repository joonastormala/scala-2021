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
 * Assignment:  Base64 data encoding (RFC 4648)
 * 
 * Motivation:
 * Suppose you can communicate with your friend only by means of text.
 * For example, suppose you can send e-mail to your friend, but an
 * e-mail message may consist only of printable characters of ASCII text.
 * You would nevertheless like to transmit binary data (an array of bytes) 
 * to your friend. This requires that you send your data to your 
 * friend __encoded as text__. A common binary-to-text encoding scheme 
 * is __Base64 data encoding__, as specified in RFC 4648.
 *
 * Reference to Base64 data encoding (RFC 4648):
 *
 *   http://www.rfc-editor.org/info/rfc4648
 *
 * (see also http://en.wikipedia.org/wiki/Base64 )
 *
 * Description:
 * This assignment asks you to implement an __encoding function__ for plain
 * Base64 encoding. The intent is to practice your skills at working with
 * bits and strings. We give you a __decoding function__ for reference.
 *
 * Remark:
 * The RFC ("Request for Comments") series is the publication vehicle for 
 * technical specifications and policy documents produced by the 
 * IETF (Internet Engineering Task Force), the IAB (Internet Architecture 
 * Board), or the IRTF (Internet Research Task Force).
 * http://www.rfc-editor.org/RFCoverview.html
 *
 */

package object base64 {

  val B64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

  /* Here is a complete Base64 decoder for reference. */

  def decode(s: String): Array[Byte] = {
    val a = s.toCharArray.takeWhile(_ != '=')
    val r = a.length%4
    val pp = if(r != 0) 4-r else 0
    val l = (6*a.length)/8
    (a ++ Array.fill(pp)('A')) // pad to an encoded multiple of 24 bits
      .map(B64.indexOf(_))     // from characters to 6-bit words (0,1,...,63)
      .grouped(4)              // group to blocks of four of 6-bit words
      .toArray                 // (24 bits total in each block)
      .map(z => (0 until 4).map(j => z(j) << (6*(3-j))) // make a 24-bit word
                           .reduceLeft(_ | _))          // out of each block
      .map(z => (0 until 3).map(j => (z >> (8*(2-j)))&0xFF)) // split to 3 words
      .flatten                                               
      .map(_.toByte)      // truncate each word to byte-length
      .take(l)            // drop padding
  }

  /* 
   * Task 1: 
   * Let us build the encoder in steps so that we can unit-test each step
   * separately. First, write an encoding function that takes a sequence 
   * of three 8-bit words P,Q,R (instead of an 8-bit word we actually use
   * the least significant 8 bits of an Int) and returns a 24-bit word U 
   * with the following content:
   * 
   * PPPPPPPPQQQQQQQQRRRRRRR
   * 765432107654321076543210 (bit positions in words P,Q,R)
   *
    Make sure that you take only the least significant 8 bits of each argument P,Q,R into account.
   */
  def to24Bits(p: Int, q: Int, r: Int): Int = {
    var (a,b,c) = (p&0xFF,q&0xFF,r&0xFF)
    ((a) << 16) + ((b) << 8)+ c
  }
  
  /* 
   * Task 2: 
   * Next, write an encoding function that breaks the previous 24-bit word U 
   * into a sequence of four 6-bit words X,Y,Z,W as follows:
   * 
   * XXXXXXYYYYYYZZZZZZWWWWWW
   * 543210543210543210543210 (bit positions in words X,Y,Z,W)
   *
   * The return value r should have (in the least significant 6 bits of an
   * Int in each case): r(0) == X, r(1) == Y, r(2) == Z, and r(3) == W.
   *
   */
  def to6BitWords(w: Int): Array[Int] = Array((w >> 18 & 63), (w>>12 & 63), (w>>6&63), w&63)

  
  /* 
   * Task 3: 
   * We now ask you to write a restricted Base64 encoder that 
   * assumes that the length of the input (in bytes) is a multiple 
   * of 3. That is, the encoder needs not to worry about padding.
   *
   * Hints: 
   * You may use the "grouped" method in class Array[Byte] to split 
   * the input array b into blocks of three 8-bit words. Then use
   * the previous functions and the string "B64" defined above to
   * build an array of characters, which you can turn into a string
   * by a calling a constructor for class String with the array as parameter.
   *
   * See http://en.wikipedia.org/wiki/Base64 for examples.
   *
   */
  def restrictedEncode(b: Array[Byte]): String = {
    require(b.length % 3 == 0)
    // ... your solution starts here ...
    new String(b.grouped(3).toArray.map(x => to6BitWords( to24Bits(x(0),x(1),x(2)))).flatMap(x => x.map(B64(_))))
    // ... and ends here ...
  }
  
  /* 
   * Task 4: 
   * Finally, we ask you to implement a complete Base64 encoder that uses 
   * padding with '='-symbols for inputs whose length in bytes is not
   * a multiple of 3.
   *
   */
  def encode(b: Array[Byte]): String = {
    b.length % 3 match {
      case 0 => restrictedEncode(b)
      case 1 => restrictedEncode(b:+ 0.toByte :+ 0.toByte).dropRight(2) + "=="
      case _ => restrictedEncode(b :+ 0.toByte).dropRight(1) + "="
    }
  }
}


