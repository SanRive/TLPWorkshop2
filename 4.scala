object DistanciaOptima {

  // Función de orden superior para calcular la distancia mínima entre una lista de puntos
  def distanciaOptima(puntos: List[(Double, Double)], distancia: ((Double, Double), (Double, Double)) => Double): Double = {
    if (puntos.length < 2) 0.0
    else puntos.combinations(2).map(pair => distancia(pair(0), pair(1))).min
  }

  // Función para calcular la distancia euclidiana entre dos puntos
  def distanciaEuclidiana(p1: (Double, Double), p2: (Double, Double)): Double = {
    math.sqrt(math.pow(p1._1 - p2._1, 2) + math.pow(p1._2 - p2._2, 2))
  }

  def main(args: Array[String]): Unit = {
    val puntos = List((1.0, 2.0), (3.0, 4.0), (5.0, 6.0), (7.0, 8.0))
    println(s"La distancia mínima entre los puntos es: ${distanciaOptima(puntos, distanciaEuclidiana)}")
  }
}
