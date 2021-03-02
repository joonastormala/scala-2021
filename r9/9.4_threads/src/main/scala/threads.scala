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

package object threads {
  /*
   * TASK 1:
   * Execute all work items in parallel, __until all items__ have finished.
   * When all items have finished, return the sequence Seq[Int] of return values.
   * 
   * Hints:
   * - Have the main thread construct one worker thread for each work item. 
   * - Save the return value of each work item in its own entry in a result array. 
   * - Have the main thread join with each worker thread to synchronize the results array.
   * - Remember to start the worker threads. 
   */

  def invokeAll(work: Seq[() => Int]): Seq[Int] = {
    val results = new Array[Int](work.length) // return values of work functions
    ???
    results
  }

  /*
   * TASK 2:
   * Execute all work items in parallel, __until any one item__ finishes.
   * When the first item finishes, the execution of all other work items must be terminated 
   * (their threads interrupted at which point the threads should exit immediately). 
   * The return value can be that of any finished work item [Int].
   * 
   * Hints:
   * - Have each worker thread interrupt the main thread to signal that the work item is complete. 
   * - Use a volatile (@volatile) variable shared between the main thread and all worker threads to store one valid return value from a work item. 
   * - Make sure that every worker thread saves the return value into the volatile variable before it interrupts the main thread. 
   * - When the main thread is interrupted, have the main thread interrupt all worker threads. 
   * - Make sure to catch ThreadInterruptedException appropriately. 
   * - Also make sure that invokeAny does not return before you have a result from a work item, for example, by issuing a join.
   * - Remember to interrupt the other worker threads when the first worker thread finishes.
   */

  def invokeAny(work: Seq[() => Int]): Int = {
    @volatile var result = 0 // set to return value of any work function
    val main = Thread.currentThread()
    ???
    Thread.interrupted() // clear interrupt status of current thread
    result
  }
}


