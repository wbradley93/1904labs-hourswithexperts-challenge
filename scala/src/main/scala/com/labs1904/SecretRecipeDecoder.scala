package com.labs1904

import scala.collection.immutable.HashMap

/**
 * An ingredient has an amount and a description.
 * @param amount For example, "1 cup"
 * @param description For example, "butter"
 */
case class Ingredient(amount: String, description: String)

object SecretRecipeDecoder {
  val ENCODING: Map[String, String] = HashMap[String, String](
    "y" -> "a",
    "h" -> "b",
    "v" -> "c",
    "x" -> "d",
    "k" -> "e",
    "p" -> "f",
    "z" -> "g",
    "s" -> "h",
    "a" -> "i",
    "b" -> "j",
    "e" -> "k",
    "w" -> "l",
    "u" -> "m",
    "q" -> "n",
    "n" -> "o",
    "l" -> "p",
    "m" -> "q",
    "f" -> "r",
    "o" -> "s",
    "i" -> "t",
    "g" -> "u",
    "j" -> "v",
    "t" -> "w",
    "d" -> "x",
    "r" -> "y",
    "c" -> "z",
    "3" -> "0",
    "8" -> "1",
    "4" -> "2",
    "0" -> "3",
    "2" -> "4",
    "7" -> "5",
    "5" -> "6",
    "9" -> "7",
    "1" -> "8",
    "6" -> "9"
  )

  /**
   * Given a string named str, use the Caeser encoding above to return the decoded string.
   * @param str A caesar-encoded string.
   * @return
   */
  def decodeString(str: String): String = {
    str.flatMap(c => ENCODING.getOrElse(c.toString, c.toString))
  }

  /**
   * Given an ingredient, decode the amount and description, and return a new Ingredient
   * @param line An encoded ingredient.
   * @return
   */
  def decodeIngredient(line: String): Ingredient = {
    val split = line.split('#').map(s => decodeString(s))
    Ingredient(split(0), split(1))
  }

  /**
   * A program that decodes a secret recipe
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val infile = "src/main/resources/secret_recipe.txt"
    val recipeSource = scala.io.Source.fromFile(infile)

    val recipe = recipeSource.getLines()
    recipeSource.close()
    
    val decodedIngredients = recipe.map(i => decodeIngredient(i))

    val pw = new java.io.PrintWriter(new java.io.File("src/main/resources/decoded_recipe.txt"))
    decodedIngredients.foreach(i => pw.write(i.amount + " " + i.description + "\n"))
    pw.close()
  }
}
