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

package bayes

/* See YOUR TASK below to fast-forward to your task. */

import scala.util.Random

/**
 *
 * We adopt here a generalized Bernoulli model for classifying data into
 * classes under a naïve Bayesian assumption.
 *
 */

/*
 * BACKGROUND:
 *
 * The background theory (with a running example) is as follows:
 *
 * Suppose we have
 *
 *  1.   k __observables__, denoted by X_1, X_2, ..., X_k, and
 *  2.   a __class variable__, C.
 *
 * All observables and the class variable are random variables that each
 * take values in their respective finite sets. Let us call the values
 * of C __labels__.
 *
 * Before proceeding further, let us consider an example.
 * Let us think politics and candidates running for, say, the parliament.
 * Suppose each candidate is affiliated with exactly one of the political
 * parties. Suppose each observable X_i is a multiple-choice question
 * (say, with two choices, "yes" and "no"). Suppose the class variable C
 * is the party affiliation (that is, each outcome of C is a political party).
 *
 * Now back to theory and terminology.
 * An __observation__ x is an outcome of the joint random
 * variable X=(X_1,X_2,...,X_k). That is, an observation x is simply
 * a k-vector x=(x_1,x_2,...,x_k) that supplies an outcome for each observable.
 * Associated with each observation there is a probability distribution for
 * the outcomes of the class variable C (that is, for the labels).
 * We seek to __estimate__ this distribution using __data__.
 *
 * Let us return to our example. Suppose we have submitted our questionnaire
 * (the k multiple-choice questions) to n candidates running for parliament,
 * and all n candidates have been kind enough to give us their answers to
 * all k questions in
 * the questionnaire. Suppose furthermore that we know the party affiliation
 * of each of the n candidates. Intuitively this means that we have __data__
 * (n observations and n associated labels) that we can use when preparing
 * our estimate.
 *
 * Back to theory.
 * Suppose now we get an observation x, but we have no idea about the
 * corresponding outcome c of the class variable C. Since we have data,
 * we can make a principled guess (estimate the probability distribution
 * over the labels c __given__ that we observe x). That is, we would like
 * to determine the conditional probability
 *
 *     P(C=c|X=x)   "probability that the class is c given that we observe x".
 *
 * Let us use our running example to reflect __why__ we want to make
 * a principled guess about c given x. Suppose I am a voter looking for
 * a candidate to receive my vote. I am not too picky about individual
 * candidates, but I would like to vote for a party whose views roughly
 * agree with my own. In this case if I have the data on the candidates,
 * I can complete the questionnaire myself to get the observation x consisting
 * of my answers, and then using the data figure out the probability of
 * each c given x. Perhaps a reasonable strategy for me would be to vote
 * for a candidate that belongs to a party c that gets the maximum
 * probability given x. So far so good.
 *
 * Back to theory.
 * Namely, how do we get principled probabilities P(C=c|X=x) from our data?
 * Recalling that conditional and joint probability are related by
 *
 *     P(C=c|X=x)P(X=x) = P(C=c,X=x) = P(X=x|C=c)P(C=c) ,
 *
 * we recover the Bayes formula by solving for P(C=c|X=x) :
 *
 *     P(C=c|X=x) = P(X=x|C=c)P(C=c)/P(X=x).
 *
 * [[The divisor P(X=x), which is often called "the normalization constant",
 * is the same for all possible values of c and can be disregarded if
 * we are only interested in comparing the probabilities
 * P(C=c|X=x) with each other to find a choice c with the maximum probability.]]
 *
 * Let us seek a more intuitive interpretation for the term P(X=x|C=c)P(C=c).
 * The probability P(C=c) is the probability of the label c __prior__ to seeing
 * the observation. The probability P(X=x|C=c) is the __likelihood__ of the
 * observation x given c. The (normalized) product of these is then
 * our desired probability P(C=c|X=x), that is, the __posterior__ probability,
 * or the probability of c __after__ we are given the __evidence__ x.
 * That is, the posterior is the likelihood times the prior (normalized).
 *
 * All right, so now we know how to turn the posterior (which is what we want)
 * into a likelihood and prior. But how do we get the likelihood and prior?
 *
 * Recall that in terms of our example we only have n completed questionnaires,
 * each labeled with the party of the candidate who submitted the responses.
 * On the surface of it, there are no probabilities whatsoever in the data.
 *
 * Here is where __modeling__ comes in. In short, we make principled
 * __assumptions__ and see how those assumptions lead us to likelihoods and
 * priors.
 *
 * Let us start by being "naïve" about the likelihood. (This is the gist of
 * naïvety in "naïve Bayesian" classification.) Let us recall that
 * each observation consists of k outcomes, one outcome for each observable.
 * That is, when we write
 *
 *   P(X=x|C=c)
 *
 * we are in fact condensing with notation the joint probability
 *
 *   P(X_1=x_1,X_2=x_2,...,X_k=x_k|C=c) .
 *
 * Let us now ("naïvely") __assume__ that the k events X_i=x_i are
 * __mutually independent__ of each other (given c).
 * (Note that this assumption may not be valid in practice -- for example,
 * we could have X_1=X_2 because questions 1 and 2 are the same question in
 * the questionnaire!)
 *
 * __Assuming__ mutual independence, the joint likelihood factors into
 * a simple product of k likelihoods, one for each observable:
 *
 *   P(X_1=x_1,X_2=x_2,...,X_k=x_k|C=c)
 *
 *          = P(X_1=x_1|C=c) * P(X_2=x_2|C=c) * ... * P(X_k=x_k|C=c)
 *
 * So, if we take our assumption for granted, all we have to do is
 * to assign values for the likelihoods of each individual observable
 * given c.
 *
 * So what is a principled value for the likelihood P(X_i=x_i|C=c) ?
 * ... and what is a principled value the prior P(C=c) ?
 *
 * Here we will follow the principle of __maximum likelihood estimation__
 * (see here http://en.wikipedia.org/wiki/Maximum_likelihood_estimation ), and assign
 * each likelihood and prior based on the number of occurrences in the
 * data. That is, our estimates are
 *
 *           (number of observations in our data from c)    n_c
 *  P(C=c) = ------------------------------------------- = -----             (1)
 *            (total number of observations in our data)     n
 *
 * and
 *                   (num. of obs. from c with response x_i to question X_i)
 *  P(X_i=x_i|C=c) = ------------------------------------------------------- (2)
 *                                  (num. of observations from c)
 *
 * This choice for estimates should be intuitively clear.
 *
 */

/*
 * SUMMARY:
 *
 * So, to sum up, when we observe x, the naïve Bayes estimate (that is,
 * estimate subject to the assumption that the k observables are independent)
 * for c given x is:
 *
 * P(C=c|X=x) = P(X_1=x_1|C=c)P(X_2=x_2|C=c)...P(X_k=x_k|C=c)P(C=c)/P(X=x).
 *
 * If we are only interested in selecting a label c with the maximum probability
 * (that is, a label c that maximizes P(C=c|X=x)), then we can
 * ignore P(X=x) (that is, we can leave the division with P(X=x) out of
 * the formula since the divisor is the same for all c) and use the
 * estimates (1) and (2) for the prior P(C=c) and the likelihoods
 * P(X_i=x_i|C=c), respectively. This is what you are asked to implement below.
 *
 * Remark:
 * Although in practice it is rarely the case that the k observables are
 * independent, the naïve Bayes estimate is a good starting point
 * and gives a good baseline for comparing against more intricate classifiers.
 *
 */

/* YOUR TASK is to implement a naïve Bayesian classifier by
 * completing the parts marked with '???' below.
 *
 * The training data is given in the format specified in 'bayesData.scala'.
 * That is, the training data is a sequence of pairs where in each pair
 *
 * 1. the first component is the class label ('c' above), and
 * 2. the second component is the observation vector ('x' above)
 *
 */

class NaiveBayesClassifier[ClassLabel](trainingData: Seq[(ClassLabel, Array[Int])]) {
  require(trainingData.nonEmpty, "The training data cannot be empty")

  // The dimension of the observation vectors
  val dim = trainingData.head._2.size

  require(trainingData.forall(p => p._2.size == dim), "All vectors in the training data must have the same dimension")

  /*
   * For convenience, the class labels occurring in the training set are stored here.
   */
  private val classLabels: Set[ClassLabel] = trainingData.map(x => x._1).toSet

  /**
   * Mapping from class labels to prior probabilities,
   * i.e. the probability P(C=c).
   */
  private lazy val priorProbabilitiesMap: Map[ClassLabel, Double] = {
    ???
  }

  /**
   * Get the prior probability P(C=c).
   */
  def priorProbability(classLabel: ClassLabel): Double = {
    priorProbabilitiesMap.getOrElse(classLabel, 0.0)
  }

  /**
   * Mapping from (class label c,observable index i,observal value v)
   * to the probability P(X_i=v|C=c).
   * The mapping should only include entries where P(X_i=v|C=c) > 0.0.
   *
   * Hints: you may want to
   * - recall the groupBy method and "Frequencies" assignment in Round 6
   * - study the for+yield construction
   *   (see e.g. http://www.artima.com/pins1ed/for-expressions-revisited.html )
   */
  private lazy val probGivenClassMap: Map[(ClassLabel, Int, Int), Double] = {
    ???
  }

  /**
   * Returns the probability given observable index, observable value, and
   * class label. If the value has not been seen, the value is naturally zero.
   * That is, return the probability
   * P(X_index=value|class=classLabel) in the training set.
   */
  def probGivenClass(classLabel: ClassLabel, index: Int, value: Int): Double = {
    require(0 <= index && index < dim)
    val p = probGivenClassMap.getOrElse((classLabel, index, value), 0.0)
    assert(0.0 <= p && p <= 1.0)
    p
  }

  /**
   * Computes the posterior probability (without the constant) for the
   * given class label c and observation vector=(v_1,...,v_dim),
   * i.e. the value
   * P(X_1=v_1|C=c)*P(X_2=v_2|C=c)*...*P(X_dim=v_dim|C=c) * P(C=c)
   */
  def computeAPosteriori(classLabel: ClassLabel, vector: Array[Int]): Double = {
    require(vector.size == dim)
    ???
  }

  /**
   * Returns the class label c that maximizes the posterior probability,
   * that is, argmax_c P(C=c|X=vector). Ties are broken arbitrarily.
   */
  def predict(vector: Array[Int]): ClassLabel = {
    require(vector.size == dim)
    classLabels.maxBy(classLabel => computeAPosteriori(classLabel, vector))
  }
}

/**
 * Companion object with some evaluation methods for the classifier.
 */
object NaiveBayesClassifier {

  /**
   * Evaluates the accuracy of the classifier, ie. returns a value in [0,1]
   * showing the fraction of input vectors that were correctly classified
   */
  def evaluateClassifier[ClassLabel](classifier: NaiveBayesClassifier[ClassLabel], data: Seq[(ClassLabel, Array[Int])]): Double = {
    data.count(p => classifier.predict(p._2) == p._1).toDouble / data.size
  }
}

