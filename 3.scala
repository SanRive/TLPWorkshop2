val numeros = List(1, 2, 3, 4, 5, 6, 7, 8)
val sumaCuadradosPares = numeros
  .filter(_ % 2 == 0) // Filtrar los números pares
  .fold(0)((acc, n) => acc + n * n) // Calcular la suma de los cuadrados

println(s"La suma de los cuadrados de los números pares es: $sumaCuadradosPares")