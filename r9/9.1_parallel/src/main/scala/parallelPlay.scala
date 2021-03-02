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

package parallel

object play {

  def withWallClock[T](work: =>T) = {
    val start = System.nanoTime
    val r = work
    val stop = System.nanoTime
    (r, (stop-start)/1e9)
  }

  def randMat(n: Int) = {
    val m = new Matrix(n)
    val r = new scala.util.Random()
    for (row <- 0 until n) {
      for (column <- 0 until n) {
        m(row, column) = r.nextDouble()
      }
    }
    m
  }

  def main(args: Array[String]) {
    val n = 1000
    print("Creating two %d-by-%d matrices with random entries ...".format(n,n))
    val a = randMat(n)
    val b = randMat(n)
    println(" done")
    print("Multiplying the two matrices (with *)  ...")
    val ts = withWallClock(a*b)._2
    println(" %f seconds".format(ts))
    print("Multiplying the two matrices (with **) ...")
    val tp = withWallClock(a**b)._2
    println(" %f seconds".format(tp))
    println("Speedup = %f".format(ts/tp))
    if(ts/tp < 1.1) {
      println("[next to no speedup observed -- have you completed the assignment?]")
    }
  }

}


