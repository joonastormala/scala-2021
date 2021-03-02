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
 * Note: This exercise is super-easy.
 *       Read through the code, run it, and record the solution 
 *       to 'clusterParallelSolutions.scala'. Then submit both this file and 
 *       'clusterParallelSolutions.scala' for grading.
 */

package clusterParallel

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object run {

  def main(args: Array[String]): Unit = {

    /* 
     * Here is some work that we want to run in parallel using Spark.
     * Each work item takes as input an Int and returns a Long. 
     *
     */

    def work(batchNumber: Int) = {
      val modulus   = (0L /: Array(0,5,8,18,22,60).map(1L << _)){_ | _}
      val hi        = 0x4000000000000000L
      val mask      = 0x7FFFFFFFFFFFFFFFL
      val length    = 2000000000
      val startVal  = batchNumber.toLong & mask
      var current   = startVal
      var i = 0
      while(i < length) {
        if((current & hi) != 0L) {
          current = ((current << 1) & mask) ^ modulus
        } else {
          current = current << 1
        }
        i = i + 1
      }
      current
    }

    /*
     * Let us start by setting up a Spark context which runs locally
     * using eight worker threads.
     *
     * Here we go:
     *
     */

    val sc = new SparkContext("local[8]", "clusterParallel")

    /*
    * The following setting controls how ``verbose'' Spark is.
    * Comment this out to see all debug messages.
    * Warning: doing so may generate massive amount of debug info,
    * and normal program output can be overwhelmed!
    */
    sc.setLogLevel("WARN") 

    /*
     * Now let us set up Spark to do the work consisting of the
     * function calls work(1), work(2), ..., work(8) in parallel
     * across the nodes of the compute cluster. (In our case we 
     * of course run locally on one node, but you should use your 
     * imagination that we are, say, rendering a full-length movie 
     * using a thousand nodes.)
     *
     */

    val workInputs = Array(1,2,3,4,5,6,7,8) 
      // set up a regular Scala collection with the individual inputs to 'work'

    val parWorkInputs: RDD[Int] = sc.parallelize(workInputs)
      // set up a Spark RDD (resilient distributed dataset) to
      // hold the inputs

    val parWorkOutputs: RDD[Long] = parWorkInputs.map(input => work(input))
      // instruct Spark to do the work on each input, 
      // in parallel across the cluster
      // (note: the work does not yet start -- Spark will start computing
      // only when we request the output data)

    val workOutputs: Seq[Long] = parWorkOutputs.collect()
      // collect the results from the output RDD (which is distributed 
      // across the cluster!) to the driver node 
      // -- this will start the computation
      // (alternatively we could save the results to a file -- this
      // is typical if there is too much output to be collected to
      // one node; say, if we were actually rendering a full-length movie)


    // the results are now available in our driver node (program), 
    // so let us print them out:

    println("OUTPUT: val result: Array[Long] = Array[Long]("
            ++ workOutputs.map(_.toString ++ "L").reduce(_ ++ ", " ++ _)
            ++ ")")

    // you should save the output array to "clusterParallelSolutions.scala" 

    /* That's it! */

  }
}


