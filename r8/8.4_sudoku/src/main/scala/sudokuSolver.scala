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
 * Author: Tommi Junttila
 */

package sudoku

/**
 * The sudoku solver object.
 * Your task is to implement the "solve" and "getBestCellGreedy" methods.
 * You are allowed to use local variables (var declarations) and mutable data structures
 * inside the methods but may not introduce any variables in the object itself or any new objects.
 * That is, the interface of the object should be "functional" but the internals can use
 * whatever technique is considered most convenient/efficient/...
 * (if you are curious, see, e.g., how the "find" method is implemented in scala.Iterator)
 */
object Solver {
  /**
   * The most trivial cell selection heuristics.
   * Note that this is a function, not a method.
   * Given a grid, the function returns the coordinate of the first cell having no value.
   * If all the cells already have a value, returns None.
   */
  val getBestCellSimple: Grid => Option[(Int, Int)] = g => {
    val n = g.n
    var r = 0
    var bestCell: Option[(Int, Int)] = None
    while (bestCell.isEmpty && r < n) {
      var c = 0
      while (bestCell.isEmpty && c < n) {
        if (!g.defined(r, c)) bestCell = Some((r, c))
        c = c + 1
      }
      r = r + 1
    }
    bestCell
  }

  /**
   * Greedy cell selection heuristics.
   * Note that this is a function, not a method.
   * Given a grid, returns the coordinate of a cell with no value yet and having fewest candidate values,
   * where a candidate value for a cell is a number in {1,2,...,n} that
   * - does not occur in the same row,
   * - does not occur in the same column, and
   * - does not occur in the same sub-grid.
   * If all the cells already have a value, returns None.
   */
  val getBestCellGreedy: Grid => Option[(Int, Int)] = g => ???
  
  /**
   * Solve the given sudoku grid.
   * @parameter g the grid to be solved
   * @parameter getBestCell the cell selection heuristics, i.e. a function that returns
   *            a cell with no value yet (or None if all cells already have values)
   * @returns a pair (sol, nofCalls), where
   * * sol is the solution (None if the grid has no solution), and
   * * nofCalls is the number of recursive calls made
   */
  def solve(g: Grid, getBestCell: Grid => Option[(Int, Int)]): (Option[Grid], Int) = ???

}


