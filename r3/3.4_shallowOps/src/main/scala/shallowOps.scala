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

object factory {

  /**
   * A helper method implementing a half adder.
   * @return a pair (c_out, s), where
   * (i)  c_out evaluates to true iff both argument gates are true, and
   * (ii) s evaluates to true iff an odd number of inputs are true.
   *
   */
  protected def buildHalfAdder(a: Gate, c_in: Gate): (Gate, Gate) = {
    val c_out = a && c_in
    val s = (a || c_in) && !c_out
    (c_out, s)
  }

  /**
   * A helper method implementing a full adder.
   * @return a pair (c_out, s), where
   * (i)  c_out is a gate evaluating to true iff at least two of the 
   *      argument gates are true, and
   * (ii) s is a gate evaluating to true iff an odd number of inputs are true.
   *
   */
  protected def buildFullAdder(a: Gate, b: Gate, c_in: Gate): (Gate, Gate) = {
    val c_out = (!a && b && c_in) || (a && !b && c_in) || (a && b && !c_in) || (a && b && c_in)
    val s = (!a && !b && c_in) || (!a && b && !c_in) || (a && !b && !c_in) || (a && b && c_in)
    (c_out, s)
  }

  /**
   * A simple ripple carry adder with modulo semantics.
   * Returns a bus that evaluates to the sum of values of the argument buses 
   * (modulo 2^n, where n is the bus length).
   *
   * For instance, assume that aa and bb are buses with 3 bits.
   * When aa evaluates to 011 (3 in decimal) and bb to 110 (6 in decimal),
   * then the bus adder(aa, bb) should evaluate to 001 (9 mod 8 in decimal).
   *
   */
  def buildAdder(a: Bus, b: Bus): Bus = {
    require(a.length == b.length, "The buses must be of the same length")
    require(a.length > 0, "Cannot build an adder on an empty bus")
    val n = a.length
    var carry_in: Gate = Gate.False // no initial carry
    var ss = new Array[Gate](n)
    for (i <- 0 until n) {
      val (carry_out, sum) = buildFullAdder(a(i), b(i), carry_in)
      carry_in = carry_out // carry from bit i propagates to bit i+1
      ss(i) = sum
    }
    new Bus(ss)
  }

  /**
   * Task 1: Logarithmic-depth OR
   *
   * This task initiates you to the design and implementation of circuits
   * with constrained depth and size. More precisely, you are to design
   * a circuit that computes the OR of an n-bit input bus. That is, you
   * are to design a circuit that outputs a single bit, which must be
   * false if and only if all the n input bits are false; otherwise
   * the output must be true.
   *
   * The design constraints are that, for all n=1,2,3,..., your circuit
   * must have:
   *
   * (i)  Depth at most the base-2 logarithm of n, rounded up.
   *      For example, for n=1 the depth must be 0, for n=2 the depth
   *      must be 1, for n=4 the depth must be 2, for n=1024 the depth
   *      must be 10, and so forth.
   *
   * and
   *
   * (ii) Size at most 2*n-1.
   *
   * Hint:
   * Structure your circuit as a perfect binary tree when n is equal
   * to a power of 2.
   *
   */
  def buildShallowOr(a: Bus): Gate = {
    require(a.length > 0, "The bus cannot be empty")
    ???
  }

  /**
   * Task 2: Logarithmic-depth incrementer.
   *
   * This task asks you to design an incrementer circuit with constrained
   * depth and size. That is, a circuit that takes an n-bit bus as input, and
   * outputs an n-bit bus whose value is equal to the least significant
   * n bits of the input plus one.
   *
   * The design constraints are that, for all n=2,3,4,..., your circuit
   * must have:
   *
   * (i) Depth at most 4*log2(n), where log2(n) is the base-2 logarithm of n.
   *
   * and
   *
   * (ii) Size at most 10*n*log2(n).
   *
   */
  def buildShallowIncrementer(in: Bus): Bus = {
    require(in.length > 0, "Cannot build an incrementer on an empty bus")
    ???
  }

  /** 
   * Task 3: Logarithmic-depth adder. 
   *
   * This task asks you to design an adder circuit with constrained
   * depth and size. That is, a circuit that takes two n-bit buses as input, 
   * and outputs an n-bit bus whose value is equal to the least significant
   * n bits of the sum of the two inputs.
   *
   * The design constraints are that, for all n=2,3,4,..., your circuit 
   * must have:
   *
   * (i)  Depth at most 6*log2(n), where log2(n) is the base-2 logarithm of n.
   *
   * and
   *
   * (ii) Size at most 30*n*log2(n).
   *
   */
  def buildShallowAdder(a: Bus, b: Bus): Bus = {
    require(a.length == b.length, "The buses must be of the same length")
    require(a.length > 0, "Cannot build an adder on an empty bus")
    ???
  }
}


