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

package pairSum
import timer._ // Source code can be found in include/timer
import plotter._ // Source code can be found in include/plotter
import scala.collection.mutable.ArrayBuffer
import org.jfree.chart.ChartFrame

/**
 * Run the methods hasPair and hasPairSlow with some instances, plot the results.
 */
object plotTest {
  import scala.util.Random
  val prng = new Random(2105)
  def randInt(max: Int, rem: Int, mod: Int) = (prng.nextInt(max) / mod) * mod + rem
  def randList(n: Int, rem: Int, mod: Int) = {
    require(n >= 0)
    require(mod >= 2)
    require(0 <= rem && rem < mod)
    (1 to n).map(v => randInt(1000000000, rem, mod))
  }

  def main(args: Array[String]): Unit = {
    val resultsNlogN = new ArrayBuffer[Tuple2[Double, Double]]()
    val resultsQuadratic = new ArrayBuffer[Tuple2[Double, Double]]()
    for (n <- 10000 to 100000 by 10000) {
      Console.println("Measuring with n = " + n)
      // Create two lists of n elements and a target t so that 
      // the target value is not the sum of two elements in the lists
      val l1 = randList(n, 3, 17).toList
      val l2 = randList(n, 6, 17).toList
      val t = randInt(2000000000, 8, 17)
      // Run faster method and collect running time
      val (r1, t1) = measureCpuTimeRepeated(hasPair(l1, l2, t))
      resultsNlogN += ((n, t1))
      Console.println("  n log n algorithm took " + t1 + " seconds")
      // Run slower method and collect running time
      val (r2, t2) = measureCpuTimeRepeated(hasPairSlow(l1, l2, t))
      resultsQuadratic += ((n, t2))
      Console.println("  quadratic algorithm took " + t2 + " seconds")
    }
    val dsNlogN = new plotter.DataSet("n log n", resultsNlogN.toArray)
    val dsQuadratic = new plotter.DataSet("quadratic", resultsQuadratic.toArray)
    val p = new Plotter()
    p.xLogScale = false
    p.yLogScale = true
    val chart = p.plot("n", "run time (seconds)", dsNlogN, dsQuadratic)
    val frame = new ChartFrame(
      "Plot",
      chart
    )
    frame.pack()
    frame.setAlwaysOnTop(true)
    frame.setVisible(true)
  }
}


