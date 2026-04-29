// I made this file to make some unit testing =)
// (i.e, testing file by file)
// (Also it could provide some usage examples and
// help understading how each one works)

object NamedEntityTest {
  def testNamedEntity(): Int = {
    println("Testing NamedEntity.scala")
    val lista: List[String] = List("a", "b", "c")

    //  val name: Type
    val miAbuelo: Person = new Person("Francisco")

    println("Declarado val miAbuelo = new Persona(\"Francisco\")")
    println("miAbuelo.describe devuelve:")

    println(miAbuelo.describe)
    // methods with no parameters are called without parentheses

    println()

    val famaf: University = new University("Famaf")

    println("Declarado val famaf = new University(\"Famaf\")")
    println("famaf.describe devuelve:")
    println(famaf.describe)

    println(s"famaf.entityType devuelve: ${famaf.entityType}")
    println()

    println("Ejemplo dado en la consigna:")
    val entities: List[NamedEntity] = List(
      new Person("Alan Turing"),
      new University("MIT"),
      new ProgrammingLanguage("Scala"),
      new Place("San Francisco")
    )
    entities.foreach(e => println(e.describe))

    // Para la reflexión dejada en la consigna;
    // funciona sin necesidad de redeclararlo porque se hereda.

    return 1
  }

  def main(args: Array[String]): Unit = {
    println()
    testNamedEntity()
    println()
    //testOtherFile()
  }
}
