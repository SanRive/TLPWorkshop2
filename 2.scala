val frases = List("Scala es genial", "Programar es divertido", "La palabra más larga")
val palabraMasLarga = frases
  .flatMap(_.split(" ")) // Dividir cada frase en palabras
  .reduceLeft((a, b) => if (a.length > b.length) a else b) // Reducir seleccionando la palabra más larga

println(s"La palabra más larga es: $palabraMasLarga")2.scala