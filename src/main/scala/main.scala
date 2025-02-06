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
//  val DayOfWeekGenerator = List["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

  def generateClasses(): List[FitnessClass] = {
    val classTypes = List("Spinning", "Rumba", "Stretching", "Functional")
    val professors = List("Alice", "Bob", "Charlie")

    List.fill(5)(
      FitnessClass(
        professor = professors(Random.nextInt(professors.length)),
        date = LocalDate.now().plusDays(Random.nextInt(30) - 15), // Dates around today
        description = "Generic fitness class",
        classType = classTypes(Random.nextInt(classTypes.length)),
        price = 20.0 + Random.nextDouble() * 30.0 // Prices between 20 and
        // 50
      )
    )
  }

  def segmentAndPrintClasses(classes: List[FitnessClass]): Unit = {
    val today = LocalDate.now()
    classes
      .filter(_.date.isAfter(today)) // Remove past classes
      .groupBy(_.classType)
      .foreach {
        case (classType, classesOfType) =>
          println(s"Classes of type: $classType")
          classesOfType
            .sortBy(_.date)
            .foreach(c => println(s"  $c"))
      }
  }

  def findClassesAtOpening(
                            classes: List[FitnessClass],
                            openingTime: Int,
                            userBudget: Double
                          ): List[FitnessClass] = {
    // Simulate opening time (e.g., 9 AM).  For simplicity, just filter
    // based on price.
    val availableClasses = classes.filter(_.price <= userBudget)
    println(
      s"Classes available that fit the budget of $$${userBudget}:"
    ) // Corrected
    availableClasses.foreach(println)
    availableClasses
  }

  def sortClassesByPriceDescending(classes: List[FitnessClass]): List[FitnessClass] = {
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

  def sortByDateAscending(classes: List[FitnessClass]): List[FitnessClass] = {
    classes.sortBy(_.date)
  }

  def calculateDiscount(dayOfWeek: DayOfWeek, isWeekend: Boolean): Double = {
    val baseDiscount =
      if (dayOfWeek == DayOfWeek.MONDAY) 0.30 else 0.0 // 30% on Mondays
    val weekendBonus = if (isWeekend) 0.10 else 0.0 // Additional 10% on
    // weekends
    math.round(baseDiscount + weekendBonus)
  }

  def applyMondayDiscount(classes: List[FitnessClass]): List[FitnessClass] = {
    val today = LocalDate.now()
    val dayOfWeek = today.getDayOfWeek
    val isWeekend =
      dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
    val discount = calculateDiscount(dayOfWeek, isWeekend)

    classes.map(c =>
      if (c.date.getDayOfWeek == DayOfWeek.MONDAY) {
        c.copy(price = c.price * (1 - discount))
      } else {
        c
      }
    )
  }

  def main(args: Array[String]): Unit = {
    val allClasses = generateClasses()
    
    println(LocalDate.now())
    println("All Classes:")
    allClasses.foreach(println)

    println("\nSegmented and Sorted Classes:")
    segmentAndPrintClasses( allClasses)

    val openingTime = 9 // Simulate 9 AM
    val userBudget = 35.0
    println(
      s"\nClasses available at opening time ($openingTime:00) within budget:"
    )
    findClassesAtOpening( allClasses, openingTime, userBudget)

    println("\nClasses sorted by price (descending):")
    val sortedByPrice = sortClassesByPriceDescending( allClasses)
    sortedByPrice.foreach(println)

    println("\nClasses sorted by date (ascending):")
    val sortedByDate = sortByDateAscending( allClasses)
    sortedByDate.foreach(println)

    println("\nClasses with Monday discount applied:")
    val discountedClasses = applyMondayDiscount( allClasses)
    discountedClasses.foreach(println)
  }
}
