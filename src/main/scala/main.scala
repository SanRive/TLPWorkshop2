import java.time.{LocalDate, LocalTime, DayOfWeek}

// Definición de la clase ClaseAcondicionamiento
case class ClaseAcondicionamiento(
                                   profesor: String,
                                   fecha: LocalDate,
                                   hora: LocalTime,
                                   descripcion: String,
                                   tipo: String,
                                   precio: Double
                                 )

object Gimnasio {

  // Lista de clases ofrecidas por el gimnasio
  // Mostrar los precios en dólares para evitar cifras con muchos ceros
  private val clases = List(
    ClaseAcondicionamiento("Carlos", LocalDate.of(2025, 2, 22), LocalTime.of(8, 0), "Spinning intenso", "Spinning", 30.0),
    ClaseAcondicionamiento("Ana", LocalDate.of(2025, 2, 14), LocalTime.of(9, 0), "Rumba divertida", "Rumba", 25.0),
    ClaseAcondicionamiento("Luis", LocalDate.of(2025, 2, 17), LocalTime.of(8, 0), "Stretching avanzado", "Stretching", 20.0),
    ClaseAcondicionamiento("Marta", LocalDate.of(2025, 2, 10), LocalTime.of(10, 0), "Funcional extremo", "Funcional", 35.0),
    ClaseAcondicionamiento("Pedro", LocalDate.of(2025, 2, 16), LocalTime.of(8, 0), "Spinning suave", "Spinning", 28.0)
  )

  // Filtrado de clases por tipología y ordenadas por fecha ascendente
  def clasesPorTipo(tipo: String): List[ClaseAcondicionamiento] = {
    clases.filter(_.tipo == tipo)
      .filter(_.fecha.isAfter(LocalDate.now()))
      .sortBy(_.fecha)
  }

  // Buscar clases al abrir el gimnasio y validar el presupuesto
  def clasesAlAbrir(horaApertura: Int, presupuesto: Double): List[ClaseAcondicionamiento] = {
    clases.filter(clase => clase.hora.getHour == horaApertura && clase.precio <= presupuesto)
  }

  // Ordenar la lista por precio de manera recursiva
  def ordenarPorPrecio(lista: List[ClaseAcondicionamiento]): List[ClaseAcondicionamiento] = lista match {
    case Nil => Nil
    case head :: tail =>
      val (mayores, menores) = tail.partition(_.precio >= head.precio)
      ordenarPorPrecio(mayores) ::: head :: ordenarPorPrecio(menores)
  }

  // Ordenar por fecha utilizando programación funcional
  def ordenarPorFecha(lista: List[ClaseAcondicionamiento]): List[ClaseAcondicionamiento] = {
    lista.sortBy(_.fecha)
  }

  // Calcular descuento basado en el día y la categoría
  def calcularDescuento(clase: ClaseAcondicionamiento): Double = {
    val day = clase.fecha.getDayOfWeek
    val descuentoBase = if (day == DayOfWeek.MONDAY) 0.3 else 0.0
    // println(clase.fecha.getDayOfWeek)
    val descuentoAdicional = if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) 0.1 else 0.0
    clase.precio * (1 - descuentoBase - descuentoAdicional)
  }


  // Ejecución de ejemplo
  def main(args: Array[String]): Unit = {
    println("Clases de Spinning:")
    clasesPorTipo("Spinning").foreach(println)

    println("\nClases al abrir el gimnasio (8 AM) con presupuesto de 30:")
    clasesAlAbrir(8, 30).foreach(println)

    println("\nClases ordenadas por precio:")
    ordenarPorPrecio(clases).foreach(println)

    println("\nClases ordenadas por fecha:")
    ordenarPorFecha(clases).foreach(println)

    println("\nPrecios con descuento si es fin de semana:")
    clases.map(clase => (clase.descripcion, calcularDescuento(clase))).foreach(println)
  }
}