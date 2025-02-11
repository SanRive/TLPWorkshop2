import java.time.{DayOfWeek, LocalDate}
import java.time.format.DateTimeFormatter
import scala.math
import scala.util.Random

case class FitnessClass(
                         professor: String,
                         date: LocalDate,
                         description: String,
                         classType: String,
                         price: Double
                       )

object FitnessClassManager {

  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  private def generateClasses(): List[FitnessClass] = {
    val classTypes = List("Spinning", "Rumba", "Stretching", "Functional")
    val professors = List("Alice", "Bob", "Charlie")

    List.fill(5)(
      FitnessClass(
        professor = professors(Random.nextInt(professors.length)),
        date = LocalDate.now().plusDays(Random.nextInt(30) - 15), // Dates around today
        description = "Generic fitness class",
        classType = classTypes(Random.nextInt(classTypes.length)),
        price = 20.0 + Random.nextDouble() * 30.0 // Prices between 20 and 50
      )
    )
  }

  private def segmentAndPrintClasses(classes: List[FitnessClass]): List[FitnessClass] = {
    val today = LocalDate.now()

    classes
      .filter(_.date.isAfter(today)) // Remove past classes
      .groupBy(_.classType) // Group by class type
      .map { case (classType, classesOfType) =>
        // Print the header for this class type
        println(s"Classes of type: $classType")
        // Print each class, sorted by date
        classesOfType.sortBy(_.date).foreach(c => println(s"$c"))
      }

    classes.filter(_.date.isAfter(today)) // Return the valid classes
  }


  private def findClassesAtOpening(
                            classes: List[FitnessClass],
                            openingTime: Int,
                            userBudget: Double
                          ): List[FitnessClass] = {
    val availableClasses = classes.filter(_.price <= userBudget)
    println(s"Classes available that fit the budget of $$$userBudget:")
    availableClasses.foreach(println)
    availableClasses
  }

  private def sortClassesByPriceDescending(classes: List[FitnessClass]): List[FitnessClass] = {
    if (classes.isEmpty) {
      classes
    } else {
      val pivot = classes.head
      val smaller = classes.tail.filter(_.price > pivot.price)
      val larger = classes.tail.filter(_.price <= pivot.price)
      sortClassesByPriceDescending(smaller) ++ List(pivot) ++
        sortClassesByPriceDescending(larger)
    }
  }

  private def sortByDateAscending(classes: List[FitnessClass]): List[FitnessClass] = {
    classes.sortBy(_.date)
  }

  private def calculateDiscount(dayOfWeek: DayOfWeek, isWeekend: Boolean): Double = {
    val baseDiscount =
      if (dayOfWeek == DayOfWeek.MONDAY) 0.30 else 0.0 // 30% on Mondays
    val weekendBonus = if (isWeekend) 0.10 else 0.0 // Additional 10% on weekends
    baseDiscount + weekendBonus
  }

  private def applyMondayDiscount(classes: List[FitnessClass]): List[FitnessClass] = {
    val discountForToday = calculateDiscount(LocalDate.now().getDayOfWeek, isWeekend = false)

    classes.map { c =>
      if (c.date.getDayOfWeek == DayOfWeek.MONDAY) c.copy(price = c.price * (1 - discountForToday))
      else c
    }
  }

  def main(args: Array[String]): Unit = {
    val allClasses = generateClasses()

    println(LocalDate.now())
    println("All Classes:")
    allClasses.foreach(println)

    println("\nSegmented and Sorted Classes:")
    val validClasses = segmentAndPrintClasses(allClasses)

    val openingTime = 9 // Simulate 9 AM
    val userBudget = 35.0
    println(
      s"\nClasses available at opening time ($openingTime:00) within budget:"
    )
    findClassesAtOpening(validClasses, openingTime, userBudget)

    println("\nClasses sorted by price (descending):")
    val sortedByPrice = sortClassesByPriceDescending(validClasses)
    sortedByPrice.foreach(println)

    println("\nClasses sorted by date (ascending):")
    val sortedByDate = sortByDateAscending(validClasses)
    sortedByDate.foreach(println)

    println("\nClasses with Monday discount applied:")
    val discountedClasses = applyMondayDiscount(validClasses)
    discountedClasses.foreach(println)
  }
}
