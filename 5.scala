import scala.util.matching.Regex

object RegexValidator {
  def main(args: Array[String]): Unit = {
    val correoRegex: Regex = """^[a-zA-Z0-9.]+@(gmail\.com|unal\.edu\.co|med\.gov\.co)$""".r
    val ipRegex: Regex = """^((\d{1,3}\.){3}\d{1,3})$""".r
    val coordenadasRegex: Regex = """^\((-?\d{1,3}\.\d+),\s*(-?\d{1,3}\.\d+)\)$""".r
    val ciudadRegex: Regex = """^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+\s*–\s*[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$""".r
    val documentoRegex: Regex = """^(\d{7,10}|(\d{1,3}(\.\d{3}){1,3}))$""".r

    val ejemplos = List(
      "usuario@gmail.com", "nombre.unal@unal.edu.co", "persona@med.gov.co", "correo@incorrecto.com",
      "192.105.32.34", "300.123.456.789",
      "(6.235, -75.575)", "(-12.3456, 123.4567)", "(91.000, 200.000)",
      "Medellín – Colombia", "Bogotá – Colombia", "CiudadInvalida-Colombia",
      "1162378567", "1.234.567.899", "1234567", "12.34.567"
    )

    ejemplos.foreach { ejemplo =>
      if (correoRegex.matches(ejemplo)) println(s"Correo válido: $ejemplo")
      else if (documentoRegex.matches(ejemplo)) println(s"Documento válido: $ejemplo")
      else if (ipRegex.matches(ejemplo)) println(s"Dirección IP válida: $ejemplo")
      else if (coordenadasRegex.matches(ejemplo)) println(s"Coordenadas válidas: $ejemplo")
      else if (ciudadRegex.matches(ejemplo)) println(s"Ciudad válida: $ejemplo")
      else println(s"Inválido: $ejemplo")
    }
  }
}
