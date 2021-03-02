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

package polynomials

/*
 * Your tasks (indicated with 'Task X' below in the source code proper):
 *
 * Task 1:
 * Implement mutable internal state (=current value) for Variables. 
 * Complete the 'set' and 'value' methods in class Variable,
 * and the 'value' method in class Constant.
 *
 * Task 2:
 * Implement polynomial value computation using structural recursion.
 * Complete the 'value' methods in all the classes extending Polynomial.
 * Hint: you may want to take a look at how the method 'asString' 
 * is implemented. 
 *
 * Task 3:
 * Implement programmer-defined operators to ease working with Polynomial 
 * objects. That is, complete the methods '*' and '+' in class Polynomial 
 * so that you may use the operators '*' and '+' to construct Product and 
 * Sum objects from Polynomial objects. For more on operators in Scala, 
 * see here
 *
 *    http://docs.scala-lang.org/tutorials/tour/operators.html
 *
 * and Chapter 6.12 in the scala language specification
 *
 *    https://www.scala-lang.org/files/archive/spec/2.12/06-expressions.html#prefix-infix-and-postfix-operations
 *
 */

/** The abstract base class for polynomials. */

abstract class Polynomial() {
  def value: Int        // implemented by extending classes
  def asString: String  // implemented by extending classes

  def +(right: Polynomial): Polynomial = ??? // Task 3
    // sum of left (this) and right

  def *(right: Polynomial): Polynomial = ??? // Task 3
    // product of left (this) and right

}

/** An extending class that implements polynomial variables. */

class Variable(name: String) extends Polynomial() {
   // Task 1
   // ??? record current value of this variable somewhere here
   
   def set(new_val: Int) { ??? }  // set the value of this variable
   def value = ???                // return current value of this variable
   def asString = name
}

/** An extending class that implements polynomial constants. */

class Constant(v: Int) extends Polynomial() {
   def value = ???   // Task 1
   def asString = v.toString
}

/** An extending class that implements polynomials that are the sum of two polynomials. */

class Sum(left: Polynomial, right: Polynomial) extends Polynomial() {
   def value = ???   // Task 2
   def asString = "(" ++ left.asString ++ "+" ++ right.asString ++ ")"
}

/** An extending class that implements polynomials that are the product of two polynomials. */

class Product(left: Polynomial, right: Polynomial) extends Polynomial() {
   def value = ???   // Task 2
   def asString = "(" ++ left.asString ++ "*" ++ right.asString ++ ")"
}


