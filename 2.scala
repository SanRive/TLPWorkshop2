object PalabraMasExtensa {

  // Función para encontrar la palabra más extensa en una lista de frases utilizando reduceLeft
  def palabraMasExtensa(frases: List[String]): String = {
    frases.flatMap(_.split(" ")).reduceLeft((a, b) => if (a.length >= b.length) a else b)
  }

  // Ejecución de ejemplo
  def main(args: Array[String]): Unit = {
    val frases = List("Esta es una prueba", "Buscando la palabra más extensa", "Scala es poderoso")
    println("Palabra más extensa:")
    println(palabraMasExtensa(frases))
  }
}