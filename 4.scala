def distanciaOptima(
                     puntos: List[(Double, Double)],
                     distancia: ((Double, Double), (Double, Double)) => Double
                   ): Double = {
  puntos.combinations(2) // Generar todas las combinaciones de pares de puntos
    .map { case List(p1, p2) => distancia(p1, p2) } // Calcular la distancia para cada par
    .min // Encontrar la distancia mínima
}

// Función para calcular la distancia Euclidiana entre dos puntos
val distanciaEuclidiana: ((Double, Double), (Double, Double)) => Double = {
  case ((x1, y1), (x2, y2)) => Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
}

val puntos = List((1.0, 2.0), (4.0, 6.0), (7.0, 8.0))
val distanciaMinima = distanciaOptima(puntos, distanciaEuclidiana)

println(s"La distancia mínima entre los puntos es: $distanciaMinima")
