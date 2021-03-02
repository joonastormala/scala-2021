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

package binaryVision

/*
 * This module implements the data browsers.
 * The code is not particularly high-quality, but you are welcome
 * to take a look.
 *
 * You can edit this file if you like.
 * (However, you should not assume in 'binaryVision.scala' that
 * the classes and objects in this file are available.
 * This will not be the case when you submit the code for grading.)
 *
 */

object TrainBrowser {
  def main(args: Array[String]) {
    val g = new util.Random(123)
    val shuffle = g.shuffle((0 until data.n).toList).toArray
    val trainSize = 100
    val trainIdx = shuffle.take(trainSize)
    val trainDigits = trainIdx.map(data.digits(_))
    val trainLabels = trainIdx.map(data.labels(_))
    new Browser(trainDigits, trainLabels)
  }
}

object ResultBrowser {
  def main(args: Array[String]) {
    val g = new util.Random(123)
    val shuffle = g.shuffle((0 until data.n).toList).toArray
    val trainSize = 100
    val testSize  = 100
    val trainIdx = shuffle.take(trainSize)
    val testIdx  = shuffle.drop(trainSize).take(testSize)
    val trainDigits = trainIdx.map(data.digits(_))
    val trainLabels = trainIdx.map(data.labels(_))
    val testDigits = testIdx.map(data.digits(_))
    val testLabels = testIdx.map(data.labels(_))

    val trainClassifier = classifier.train(trainDigits, trainLabels)
    new Browser(testDigits, testLabels, trainClassifier)
  }
}

/* The GUI code starts from here. Let us do the imports first. */

import java.awt.image.BufferedImage
import scala.swing._
import scala.swing.event._
import java.lang.NumberFormatException

/* A poor man's numerical spinner component. */

class Spin(min: Int, max: Int, start: Int, step: Int) {
   require(min <= start && start <= max)
   val tf = new TextField(10) {
     text = start.toString
     horizontalAlignment = Alignment.Right
     editable = false
   }

   val ib = new Button { text = "+" }
   val db = new Button { text = "-" }
   val comp = new FlowPanel {  contents += tf; contents += ib; contents += db }
   def set(v: Int) {
     if(v >= min && v <= max) { tf.text = v.toString; tf.publish(EditDone(tf)) }
   }
   def v = tf.text.toInt
   tf.listenTo(ib)
   tf.listenTo(db)
   tf.reactions += {
     case ButtonClicked(b) =>
       if(b == ib) { set(tf.text.toInt + step) }
       if(b == db) { set(tf.text.toInt - step) }
   }
}

/* A data browser frame. */

class Browser(digits: Array[Array[Double]],
              labels: Array[Int],
              classifier: (Array[Double]) => Int = null) extends SimpleSwingApplication {
  require(digits.length == labels.length)
  val idxSpin = new Spin(0,digits.length-1,0,1)
  val fontLabel  = new Font("Courier", java.awt.Font.BOLD, 150)
  val labelText  = new Label {
    text = labels(idxSpin.v).toString
    font = fontLabel
    foreground = new Color(0,255,0)
  }
  val classText  = new Label {
    if(classifier != null) {
      text = classifier(digits(idxSpin.v)).toString
    }
    font = fontLabel
    foreground = new Color(255,0,255)
  }

  object dataImage extends Component {
    preferredSize = new Dimension(data.sizex,data.sizey)
    override def paintComponent(g: Graphics2D) {
      val buf = new BufferedImage(data.sizex,
                                  data.sizey,
                                  BufferedImage.TYPE_BYTE_GRAY)
      val gray = digits(idxSpin.v).map(z => z*255.0)
      buf.getRaster()
         .setPixels(0,0,data.sizex,data.sizey,gray)
      g.drawImage(buf, 0, 0, null)
    }
  }

  object featureImage extends Component {
    preferredSize = new Dimension(feature.sizex,feature.sizey)
    override def paintComponent(g: Graphics2D) {
      val buf = new BufferedImage(feature.sizex,
                                  feature.sizey,
                                  BufferedImage.TYPE_BYTE_GRAY)
      val gray = feature.get(digits(idxSpin.v)).map(z => z*255.0)
      buf.getRaster()
         .setPixels(0,0,feature.sizex,feature.sizey,gray)
      g.drawImage(buf, 0, 0, null)
    }
  }

  def top = new Frame { frame =>
    title = "Browser (%d items)".format(digits.length)
    override def closeOperation() { dispose() }
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new FlowPanel {
        background = new Color(100,100,255)
        contents += dataImage
        if(classifier == null) { contents += featureImage }
        if(classifier != null) { contents += classText}
        contents += labelText
      }
      contents += idxSpin.comp
    }
    listenTo(idxSpin.tf)
    listenTo(idxSpin.ib.keys)
    listenTo(idxSpin.db.keys)
    reactions += {
      case EditDone(e) =>
        dataImage.repaint()
        featureImage.repaint()
        labelText.text = labels(idxSpin.v).toString
        if(classifier != null) {
          classText.text = classifier(digits(idxSpin.v)).toString
        }
    }
  }

  val t = top
  t.pack()
  t.centerOnScreen()
  t.visible = true
}


