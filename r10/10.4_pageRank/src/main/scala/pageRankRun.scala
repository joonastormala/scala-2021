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

package pageRank

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object run {

  def main(args: Array[String]): Unit = {

    /*
     * Let us start by setting up a Spark context which runs locally
     * using as many worker threads as logical cores on your machine.
     *
     * Here we go:
     *
     */

    val sc = new SparkContext("local[*]", "pageRank")

    /*
    * The following setting controls how ``verbose'' Spark is.
    * Comment this out to see all debug messages.
    * Warning: doing so may generate massive amount of debug info,
    * and normal program output can be overwhelmed!
    */
    sc.setLogLevel("WARN") 

    /*
     * Next, let us set up our input. 
     */

    val path = "r10/pageRank/data/"
    
    

    /*
     * After the path is configured, we need to set up the input
     * file. This exercise has one input file. (Remember to decompress
     * the file with 'gunzip' if you have downloaded the compressed file
     * from the web.)
     *
     */

    val filename_arcs     = path ++ "web-Google.txt"

    /*
     * Let us now set up an RDD for the arcs.
     *
     */

    val lines_arcs : RDD[String] = sc.textFile(filename_arcs)

    /* The following requirement sanity-checks the number of lines in the file
     * -- if this requirement fails you are in trouble.
     */

    require(lines_arcs.count() == 5105043)

    /*
     * Now parse the arcs to a more convenient RDD
     * consisting of pairs (from,to) where 'from' is the identifier 
     * of the page (node) with the link (arc), and 'to' is the 
     * identifier of the page (node) where the link (arc) points to.
     *
     */

    var arcs : RDD[(Int,Int)] = 
      lines_arcs.filter(!_.contains("#"))
                .map(line => { val ft = line.split("[ \t]+"); (ft(0).toInt,ft(1).toInt) })

    /*
     * Prepare & sanity-check basic statistics.
     *
     */

    val nodes = arcs.map(x => x._1)
                    .union(arcs.map(x => x._2))
                    .distinct 
    val num_nodes = nodes.count()
    var num_arcs  = arcs.count()

    require(num_nodes == 875713 && num_arcs == 5105039)

    /*
     * Add a self-loop from every node to itself so that 
     * all out-degrees are at least 1.
     *
     */

    arcs     = arcs.union(nodes.map(x => (x,x)))
    num_arcs = num_arcs + num_nodes

    /* 
     * Our input is now set up. Now it is time for you to step in!
     *
     */

    /*
     * YOUR TASK:
     *
     * 1)
     * Study the PageRank algorithm.
     * http://en.wikipedia.org/wiki/PageRank
     * 
     * 2)
     * Implement the PageRank algorithm using Spark.
     * (You may assume that all out-degrees are at least 1.)
     *
     * 3)
     * Compute the PageRanks for our input, assuming that the
     * damping factor d = 0.85.
     *
     * 4)
     * Find the identifiers of the top 5 nodes with the highest
     * PageRanks, in order of decreasing PageRank. 
     * 
     * Write the CODE that finds the 5 nodes below. 
     * Once you have the VALUES (the identifiers), save them in 
     * "pageRankSolutions.scala".
     *
     * Hints: 
     * You may want to consider an iterative approach.
     * Make sure that the PageRanks have converged to a sufficient
     * extent before you order the nodes. 
     * The node with the highest PageRank has identifier 885605.
     *
     */

    val top5 : Seq[Int] = ??? // your CODE here

    println("OUTPUT: The solution is %s".format(top5.mkString("Array(",",",")")))

  }

}


