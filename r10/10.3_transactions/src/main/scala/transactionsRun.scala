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

package transactions

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.text.SimpleDateFormat

object run {

  def main(args: Array[String]): Unit = {

    /*
     * Let us start by setting up a Spark context which runs locally
     * using two worker threads.
     *
     * Here we go:
     *
     */

    val sc = new SparkContext("local[2]", "transactions")

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

    val path = "r10/transactions/data/"
    

    /*
     * After the path is configured, we need to set up the input
     * files. This exercise has two input files.
     *
     */

    val filename_balances     = path ++ "ACMEBank-simulated-balances-2013-12-31-end-of-day.txt"
    val filename_transactions = path ++ "ACMEBank-simulated-transactions-from-2014-01-01-to-2014-12-31-inclusive.txt"

    /*
     * Let us now set up RDDs for the balances and transactions.
     *
     */

    val lines_balances     : RDD[String] = sc.textFile(filename_balances)
    val lines_transactions : RDD[String] = sc.textFile(filename_transactions)

    /* The following requirement sanity-checks the number of lines in the files 
     * -- if this requirement fails you are in trouble.
     */

    require(lines_balances.count() == 10000 &&
            lines_transactions.count() == 1000000)

    /*
     * Now parse the balances to a more convenient RDD
     * consisting of pairs (i,b) where i is an account identifier
     * and b is the start-of-year balance (in euro cents). 
     *
     */

    val balances : RDD[(String,Long)] = 
       lines_balances.map(line => { val ib = line.split(" +"); (ib(0),ib(1).toLong) } )

    /*
     * Next let us set up an RDD for the transcations. This requires 
     * a somewhat more intricate parser because we are converting the
     * __timestamps__ given in UTC (Coordinated Universal Time) in the data
     * into an internal binary representation as a Long, namely the number 
     * of milliseconds since January 1, 1970, 00:00:00:000 UTC.
     *
     */

    /* 
     * Set up a time stamp formatter using the Java date formatters.
     * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/text/SimpleDateFormat.html
     */

    val stamp_fmt = new SimpleDateFormat("EEE dd MMM yyyy HH:mm:ss:SSS zzz", Locale.UK)
    stamp_fmt.setTimeZone(TimeZone.getTimeZone("UTC"))
    val start_inclusive_d = stamp_fmt.parse("Wed 01 Jan 2014 00:00:00:000 UTC")
    val end_exclusive_d   = stamp_fmt.parse("Thu 01 Jan 2015 00:00:00:000 UTC")
    val start_inclusive = start_inclusive_d.getTime() // first millisec in 2014
    val end_exclusive   = end_exclusive_d.getTime()   // first millisec in 2015
    val millis_2014 = end_exclusive - start_inclusive // # millisecs in 2014

    /*
     * Let us create a transaction RDD consisting of 4-tuples with the following
     * structure:
     *
     * 1) the time stamp (a Long between 'start_inclusive' and 'end_exclusive')
     * 2) the source account identifier (e.g. "A1234")
     * 3) the target account identifier (e.g. "A1234")
     * 4) the transferred amount (in euro cents)
     *
     */

    val stamp_len = "Wed 01 Jan 2014 00:00:00:000 UTC".length

    val transactions : RDD[(Long,String,String,Long)] = 
       lines_transactions.map(line => { 
         val stamp = line.take(stamp_len)
         val rest = line.drop(stamp_len+1).split(" +")
         (stamp_fmt.parse(stamp).getTime(), // timestamp
          rest(0),                          // source id
          rest(1),                          // target id
          rest(2).toLong                    // amount transferred
         ) 
       } )

    /* 
     * Our input is now set up. Now it is time for you to step in!
     *
     */

    /*
     * Task 1:
     * Find the identifier of the account with the largest balance at 
     * end-of-day, December 31, 2013. 
     *
     * Write the CODE that finds the identifier below. 
     * Once you have the VALUE of the identifier, save it in "transactionsSolutions.scala".
     *
     */

    val task1 : String = ??? // your CODE here

    println("OUTPUT: The identifier for Task 1 is \"%s\"".format(task1))


    /*
     * Task 2:
     * Find the total volume (total amount of money
     * transferred in all the transactions), in euro cents.
     *
     * Write the CODE that finds the volume below. 
     * Once you have the VALUE of the volume, save it in "transactionsSolutions.scala".
     *
     */

    val task2 : Long = ??? // your CODE here

    println("OUTPUT: The volume for Task 2 is %d".format(task2))


    /*
     * Task 3:
     * Find the identifier of the account with the largest balance at 
     * end-of-day, December 31, 2014. 
     *
     * Write the CODE that finds the identifier below. 
     * Once you have the VALUE of the identifier, save it in "transactionsSolutions.scala".
     *
     * Hints: Key with account identifies, take union of initial balances
     *        and transactions. Reduce by key. Each transaction decreases 
     *        the balance of the source account and increases the balance 
     *        of the target account. All transactions have distinct time 
     *        stamps. 
     * 
     *
     */

    val task3 : String = ??? // your CODE here

    println("OUTPUT: The identifier for Task 3 is \"%s\"".format(task3))


    /*
     * Task 4:
     * Find the number of idle accounts (that is, accounts that do
     * not take part in any transaction) in the year 2014.
     *
     * Write the CODE that finds the number of accounts below.
     * Once you have the VALUE, save it in "transactionsSolutions.scala".
     *
     */

    val task4 : Long = ??? // your CODE here

    println("OUTPUT: The number of accounts for Task 4 is %d".format(task4))


    /*
     * Task 5:
     * The year 2014 can be partitioned into exactly 365*24 = 8760 
     * consecutive hours. Each hour lasts 3600000 milliseconds. 
     * That is, in terms of time stamps, the first hour of 2014 starts 
     * with the time stamp 1388534400000 and ends with the time 
     * stamp 1388537999999. The second hour of 2014 starts with the time 
     * stamp 1388538000000 and ends with the time stamp 1388541599999. 
     * And so forth, until we are at the last hour of 2014, which starts 
     * with the time stamp 1420066800000 and ends with the time stamp 
     * 1420070399999. Find the number of hours in 2014 during which the
     * total volume of transactions is at least euro ten million
     * (at least one billion euro cents). 
     *
     * Write the CODE that finds the number of hours below.
     * Once you have the VALUE, save it in "transactionsSolutions.scala".
     * 
     */ 

    val task5 : Long = ??? // your CODE here

    println("OUTPUT: The number of hours for Task 5 is %d".format(task5))


    /*
     * Task 6 (more challenging):
     *
     * Find the number of accounts whose balance is at least 
     * euro one million (one hundred million euro cents) 
     * at any time instant during the year 2014.
     * Note that these time instants may be different for 
     * different accounts.
     *
     * Write the CODE that finds the number of accounts below.
     * Once you have the VALUE, save it in "transactionsSolutions.scala".
     *
     * Hints: group by identifier (mind your memory!), 
     *        order by timestamp. All transactions have distinct 
     *        timestamps. 
     *
     */

    val task6 : Long = ??? // your CODE here

    println("OUTPUT: The number of accounts for Task 6 is %d".format(task6))


    /*
     * Task 7 (more challenging):
     *
     * ACME Bank pays interest to its 10000 accounts using a 
     * fixed interest rate of 0.1% per annum. The basis 
     * for the annual interest calculation is the daily
     * __minimum__ balance on each account. 
     * (The year 2014 partitions into exactly 365 consecutive
     * days, each 86400000 milliseconds long; cf. Task 5.) 
     * That is, for each day, we find the minimum balance on each 
     * account during that day, and then sum these minimum balances 
     * over accounts to get the interest payment basis for the day. 
     * Finally we sum over the 365 days in 2014 to get the total 
     * annual basis for interest payment, in euro cents. 
     * (The actual payment that ACME Bank makes then, in aggregate, 
     * at end-of-year to its accounts is the annual basis times 0.1% 
     * divided by 365; this, however, need not concern us.) 
     * In determining the daily minimum balance at an account, 
     * observe that every transaction is timestamped so that it takes 
     * place during a unique day. That is, the accounts affected by 
     * the transaction both have two different balances during the day
     * of the transaction. Namely, the balance before the transaction 
     * and after the transaction. The minimum of these two balances is 
     * the balance that contributes in the calculation of the daily minimum
     * balance for the day of the transaction.
     * 
     * Your task is to compute the annual basis for interest payment 
     * for the year 2014, in euro cents. 
     *
     * Write the CODE that computes the basis below.
     * Once you have the VALUE, save it in "transactionsSolutions.scala".
     *
     * Hint: all transactions have distinct timestamps. 
     *
     */

    val task7 : Long = ??? // your CODE here

    println("OUTPUT: The basis for Task 7 is %d".format(task7))

  }
}


