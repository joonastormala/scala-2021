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

package election2011

import bayes.NaiveBayesClassifier

object validate {
  /**
   * Divide the data vectors into two sets: a training set (n% of the data) and a test set ((100-n)% of the data).
   */
  def partitionIntoTrainingAndTestSets[ClassLabel](data: Seq[(ClassLabel, Array[Int])],
    n: Int,
    seed: Long = System.nanoTime()): (Seq[(ClassLabel, Array[Int])], Seq[(ClassLabel, Array[Int])]) = {
	require(10 <= n && n <= 90)
    require(data.nonEmpty, "The data set cannot be empty")
    val dim = data.head._2.size
    require(dim > 0, "The vectors in the data set must contain elements")
    require(data.forall(d => d._2.size == dim), "The vectors in the data set must be of the same dimension")
    val rand = new scala.util.Random(seed)
    val shuffled = rand.shuffle(data)
    shuffled.splitAt(shuffled.size * 30 / 100)
  }

  def main(args: Array[String]): Unit = {
    val p = 30
    val (trainSet, testSet) = partitionIntoTrainingAndTestSets(Data.labeledObservations, p)
    // test to see if it works
    val classifier = new NaiveBayesClassifier(trainSet)
    println("Overall accuracy ("+p+"% training set): " + NaiveBayesClassifier.evaluateClassifier(classifier, testSet))
    println("Accuracy per party:")
    for (classLabel <- Data.labeledObservations.map(p => p._1).distinct) {
      println(classLabel + ": " +
        NaiveBayesClassifier.evaluateClassifier(classifier, testSet.filter(x => x._1 == classLabel)))
    }

    val n = 100
    println("")
    println("Choosing only parties with more than " + n + " candidates")
    val classesWithOverN = Data.labeledObservations.map(x => x._1).toSet[String].map(x => (x, Data.labeledObservations.filter(y => y._1 == x).size)).filter(_._2 > n).map(x => x._1)
    val (trainSetN, testSetN) = partitionIntoTrainingAndTestSets(Data.labeledObservations.filter(x => classesWithOverN.contains(x._1)), p)
    val classifierN = new NaiveBayesClassifier(trainSetN)
    println("Overall accuracy ("+p+"% training set): " + NaiveBayesClassifier.evaluateClassifier(classifierN, testSetN))
    println("Accuracy per party:")
    for (classLabel <- classesWithOverN) {
      println(classLabel + ": " +
        NaiveBayesClassifier.evaluateClassifier(classifierN, testSetN.filter(x => x._1 == classLabel)))
    }
  }

}
