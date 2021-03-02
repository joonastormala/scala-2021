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
 * See "YOUR TASK" below for your task.
 *
 */

package svd

/* A simple class for matrix arithmetic. */

class Matrix(val m: Int, val n: Int) {
  require(m > 0, "The dimension m must be positive")
  require(n > 0, "The dimension n must be positive")
  protected[Matrix] val entries = new Array[Double](m * n)

  /* Convenience constructor for square matrices */
  def this(n: Int) = this(n, n)

  /* Access the elements of a matrix Z by writing Z(i,j) */
  def apply(row: Int, column: Int) = {
    require(0 <= row && row < m)
    require(0 <= column && column < n)
    entries(row * n + column)
  }

  /* Set the elements by writing Z(i,j) = v */
  def update(row: Int, column: Int, value: Double) {
    require(0 <= row && row < m)
    require(0 <= column && column < n)
    entries(row * n + column) = value
  }

  /* Gives a string representation */
  override def toString = {
    val s = new StringBuilder()
    for (row <- 0 until m) {
      for (column <- 0 until n) {
        s ++= " %f".format(this(row,column))
      }
      s ++= "\n"
    }
    s.toString
  }

  /* Returns the transpose of this matrix */
  def transpose = {
    val result = new Matrix(n,m)
    for (row <- 0 until m; column <- 0 until n) {
      result(column, row) = this(row, column)
    }
    result
  }
    
  /* Returns a new matrix that is the sum of this and that */
  def +(that: Matrix) = {
    require(m == that.m && n == that.n)
    val result = new Matrix(m,n)
    (0 until m*n).foreach(i => { 
      result.entries(i) = this.entries(i) + that.entries(i) 
    })
    result
  }

  /* Returns a new matrix that negates the entries of this */
  def unary_- = {
    val result = new Matrix(m,n)
    (0 until m*n).foreach(i => { 
      result.entries(i) = -entries(i)
    })
    result
  }

  /* Returns a new matrix that is the difference of this and that */
  def -(that: Matrix) = this + -that

  /* Returns a new matrix that is the product of 'this' and 'that' */
  def *(that: Matrix): Matrix = {
    require(n == that.m)
    val thatT = that.transpose  // transpose 'that' to get better cache-locality
    def inner(r1: Int, r2: Int) = {
      var s = 0.0
      var i = 0
      while(i < n) {
        // the inner loop -- here is where transposing 'that' pays off
        s = s + entries(r1+i)*thatT.entries(r2+i) 
        i = i+1
      }
      s
    }
    val result = new Matrix(m,that.n)
    (0 until m*that.n).foreach(i => { result.entries(i) = inner((i/that.n)*n, (i%that.n)*n) })
    result
  }

  /* 
   * YOUR TASK:  
   * Implement the following method that returns the singular value
   * decomposition of this matrix.
   *
   */
  def svd: (Matrix, Matrix, Matrix) = {
    ??? // your code here
  }
}

object Matrix {
  val r = new util.Random(1234567)

  def rand(m: Int, n: Int) = {
    val a = new Matrix(m,n)
    for (i <- 0 until m; j <- 0 until n) { a(i,j) = r.nextDouble() }
    a
  }

  def randGaussian(m: Int, n: Int) = {
    val a = new Matrix(m,n)
    for (i <- 0 until m; j <- 0 until n) { a(i,j) = r.nextGaussian() }
    a
  }

  def identity(n: Int) = {
    val a = new Matrix(n)
    (0 until n).foreach(i => a(i,i) = 1)
    a
  }
}

