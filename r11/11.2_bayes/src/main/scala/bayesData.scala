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

import java.io.File

object Data {
  private lazy val electionData: (Seq[(String, Array[Int])], Seq[Map[Int, String]], IndexedSeq[String]) = {
    val src = scala.io.Source.fromFile(new File("r11/bayes/data", "HS-vaalikone2011_modified.csv"), "utf-8").getLines
    // the first line contains the field names 
    val allFieldNames = src.next.split(";")

    // take the party (puolue) as the class
    val classIndex = allFieldNames.zipWithIndex.filter(x => x._1 == "Puolue").head._2

    // these are the fields we are interested in
    // we leave out (amongst others) the names, the electoral districts, the age (too many values), comments and weights assigned to different issues
    val featureFields: Array[Int] = Array(
      5, // sex
      8 // level of education
      // the ones below are the actual questions, we omit the last question (which parties should form the government)
      // and the facebook question (too many choices)
      ) ++ (16 until 76 by 3) ++ (79 until allFieldNames.length - 3 by 3)

    // these are the parties we are interested in (we leave out non-affiliated candidates)    
    val classLabels: Array[String] = Array(
      "IPU", // itsenäisyyspuolue, independence party
      "KA", // köyhien asialla, for the poor 
      "KD", // kristillisdemokraatit, christian democrats
      "KESK", // keskusta, the centre party
      "KOK", // kokoomus, national coalition
      "KTP", // kommunistinen työväenpuolue, communist workers' party 
      "M2011", // muutos 2011, change 2011
      "PIR", // piraattipuolue, the pirate party
      "PS", // perussuomalaiset, true finns
      "RKP", // ruotsalainen kansanpuolue, swedish people's party
      "SDP", // sosiaalidemokraattinen puolue, social democrat party
      "SEN", // senioripuolue, senior citizens' party
      "SKP", // suomen kommunistinen puolue, communist party
      "STP", // suomen työväenpuolue, workers' party
      "VAS", // vasemmistoliitto, left alliance
      "VIHR", // vihreät, green league
      "VP" // vapauspuolue, freedom party
      )

    // in some cases the comments of the candidates contain semicolons which breaks this
    // so for simplicity just omit those
    val lines = src.toArray

    // these are the raw input vectors (a matrix of strings)
    // filter out vectors with missing values and non-affiliated candidates
    val inputVectors: Array[Array[String]] =
      (for (line <- lines) yield line.split(";")).toArray
        .filter(x => classLabels.contains(x(classIndex)))
        .map(x => Array(x(classIndex)) ++ featureFields.map(i => x(i)))
        .filter(x => !x.contains("-"))

    val fieldNames = featureFields.map(i => allFieldNames(i))

    // scan through the file and locate all possible values each field can have, then enumerate the values
    val possibleValues: Array[Set[String]] =
      (for (i <- 0 until inputVectors.head.size)
        yield (for (v <- inputVectors)
        yield v(i)).toSet)
        .toArray

    val fieldToNumber: Array[Map[String, Int]] = possibleValues.map(x => x.zipWithIndex.toMap)

    // finally, convert to a numeric representation
    val numericVectors: Array[Array[Int]] =
      (for (v <- inputVectors)
        yield (for (i <- 0 until v.size)
        yield fieldToNumber(i)(v(i))).toArray).toArray

    // reverse field value correspondence
    val valuesToStrings: Array[Map[Int, String]] = fieldToNumber.map(x => x.map(_.swap))

    val labeledObservations = numericVectors.map(vector => (valuesToStrings(0)(vector(0)), vector.tail))
    (labeledObservations, valuesToStrings.tail, fieldNames.toIndexedSeq)
  }

  def labeledObservations: Seq[(String, Array[Int])] = electionData._1
  def fieldValuesToStrings: Seq[Map[Int, String]] = electionData._2
  def fieldNames: IndexedSeq[String] = electionData._3
}

