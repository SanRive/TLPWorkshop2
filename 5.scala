import scala.util.matching.Regex

object RegexExamples {
  def main(args: Array[String]): Unit = {
    // 1. Correo electrónico
    val correoRegex: Regex = "([a-zA-Z0-9.]+)@(gmail\\.com|unal\\.edu\\.co|med\\.gov\\.co)".r
    val correos = List("usuario@gmail.com", "nombre.unal@unal.edu.co", "persona@med.gov.co")
    correos.foreach {
      case correoRegex(usuario, dominio) =>
        println(s"Correo válido -> Usuario: $usuario, Dominio: $dominio")
      case correo =>
        println(s"Correo inválido: $correo")
    }

    // 2. Dirección IP
    val ipRegex: Regex = "((?:\\d{1,3}\\.){3}\\d{1,3})".r
    val ips = List("192.105.32.34", "300.123.456.789")
    ips.foreach {
      case ipRegex(ip) => println(s"Dirección IP válida: $ip")
      case ip => println(s"Dirección IP inválida: $ip")
    }

    // 3. Ubicación en coordenadas (lat, long)
    val coordenadasRegex: Regex = "\\((-?\\d{1,3}\\.\\d+),\\s*(-?\\d{1,3}\\.\\d+)\\)".r
    val coordenadas = List("(6.235, -75.575)", "(-12.3456, 123.4567)", "(91.000, 200.000)")
    coordenadas.foreach {
      case coordenadasRegex(lat, long) =>
        println(s"Coordenadas válidas -> Latitud: $lat, Longitud: $long")
      case coord => println(s"Coordenadas inválidas: $coord")
    }

    // 4. Ciudad (formato Medellín – Colombia)
    val ciudadRegex: Regex = "([a-zA-ZáéíóúÁÉÍÓÚñÑ]+)\\s*–\\s*([a-zA-ZáéíóúÁÉÍÓÚñÑ]+)".r
    val ciudades = List("Medellín – Colombia", "Bogotá – Colombia", "CiudadInvalida-Colombia")
    ciudades.foreach {
      case ciudadRegex(ciudad, pais) =>
        println(s"Ciudad válida -> Ciudad: $ciudad, País: $pais")
      case ciudad => println(s"Ciudad inválida: $ciudad")
    }

    // 5. Documento de identidad
    val documentoRegex: Regex = "(\\d{7,10}|\\d{1,3}(\\.\\d{3}){1,3})".r
    val documentos = List("1162378567", "1.234.567.899", "1234567", "12.34.567")
    documentos.foreach {
      case documentoRegex(doc) => println(s"Documento válido: $doc")
      case doc => println(s"Documento inválido: $doc")
    }
  }
}
