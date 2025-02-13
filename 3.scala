object SumaCuadradosPares {

  // Función para calcular la suma de los cuadrados de los números pares
  def sumaCuadradosPares(lista: List[Int]): Int = {
    lista
      .filter(_ % 2 == 0) // Filtrar los números pares
      .foldLeft(0)((acc, n) => acc + n * n) // Sumar los cuadrados
  }
  // Ejecución de ejemplo
  def main(args: Array[String]): Unit = {
    val numeros = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Suma de los cuadrados de los números pares:")
    println(sumaCuadradosPares(numeros))
  }
}