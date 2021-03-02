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
 * This module cleans the data in 'data/corpus.png' for this exercise.
 * The code is not particularly high-quality, but you are welcome
 * to take a look.
 *
 * You should not edit this file, with one exception.
 * You can change the random seed '123' to 'g' if you want.
 * (However, you should not assume in 'binaryVision.scala' that
 * the objects in this file are available.
 * This will not be the case when you submit the code for grading.)
 *
 */

import java.io.File
import java.lang.ExceptionInInitializerError
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

object data {
  val mainImage = ImageIO.read(new File("r11/binaryVision/data", "corpus.png"))
  val mx = 3
  val my = 5
  val stepx = 165
  val stepy = 163
  val sizex = 160
  val sizey = 160
  val dx = 26
  val dy = 36
  val n = 200
  val m = sizex*sizey
  val digits = new Array[Array[Double]](n)
  val g = new util.Random(123)
  val s = g.shuffle((0 until dx*dy).toList)
  var k = 0
  while(k < n) {
    val j = s(k)
    val xc = j%dx
    val yc = j/dx
    val grayAlpha = mainImage.getSubimage(mx+xc*stepx,
                                          my+yc*stepy,
                                          sizex,
                                          sizey)
                             .getRaster()
                             .getPixels(0, 0, sizex, sizey,
                                        new Array[Double](2*m))
    val digit = new Array[Double](m)
    var i = 0; while(i < m) { digit(i) = grayAlpha(2*i)/255.0; i = i+1 }
    digits(k) = digit
    k = k + 1
  }

  val ref = Array(0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,0,1,1,0,0,1,0,1,0,1,0,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,1,0,1,0,1,1,0,1,1,0,1,0,0,1,1,0,1,1,0,0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,1,0,1,0,1,1,0,1,1,0,1,0,1,0,0,1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,1,0,1,1,0,1,1,0,1,0,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,1,0,1,0,0,1,1,0,0,0,1,1,0,1,1,0,1,0,0,0,1,1,1,0,1,1,0,0,0,0,1,0,1,1,1,1,0,1,0,0,1,1,1,1,0,1,0,0,1,0,1,0,1,0,1,1,0,1,1,0,0,1,1,1,0,1,1,1,0,0,1,0,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,0,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,1,0,1,1,0,1,0,1,1,0,1,0,1,0,0,1,0,1,1,0,1,1,1,0,1,0,1,1,0,0,1,0,1,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,0,1,1,1,1,1,0,0,0,1,0,1,1,1,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,0,0,0,1,0,1,0,0,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1,0,0,1,1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,1,1,0,0,0,1,1,1,0,0,1,0,1,0,0,1,1,0,0,1,1,0,0,1,0,1,1,0,0,0,1,0,1,0,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,0,1,1,1,0,0,0,1,0,1,0,0,0,1,1,0,1,1,0,0,0,1,1,0,1,1,0,1,0,1,0,1,1,0,0,1,0,1,1,0,1,1,0,1,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1,0,1,1,0,0,1,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,1,0,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,0,1,0,1,1,0,1,1,0,1,0,0,1,1,0,0,1,0,1,0,1,0,1,1,0,1,1,0,1,0,1,1,0,0,1,0,1,1,0,0,1,0,1,0,1,1,0,1,1,0,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,1,0,1,1,0,0,1,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,1,0,1,0,1,0,1,0,0,0,0,1,1,1,0,1,1,0,1,1,0,1,0,1,0,1,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,1,1,1,0,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,1,0)
  val labels = s.take(n).map(ref(_))

}

