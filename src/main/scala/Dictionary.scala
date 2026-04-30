import scala.io.Source

// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   * Ejercicio 2: Implementar loadFromFile.
   *
   *   Pasos sugeridos:
   *     1. Leer las líneas del archivo
   *     2. Para cada línea, crear la instancia de la clase correcta
   *     3. Retornar la lista de entidades creadas
   *
   *   Para crear la clase correcta según el tipo se puede usar match:
   *
   */

  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    val source = scala.io.Source.fromFile(filePath)
    val fileContent: String = try source.mkString finally source.close()
    //println(fileContent) // Uncomment to see...file content

    val wordArray: Array[String] = fileContent.split("\n") // Make it Array[String], deleting "\n"s in the process

    // Remove first line, since it's just a comment. "if" statement just in case
    val notHead:Array[String] =
      if (wordArray.nonEmpty) wordArray.tail
      else wordArray
    val wordList: List[String] = notHead.toList // Now make it a List.

    // Finally, instance objects for each word and return.
    val myList: List[NamedEntity] = entityType match {
    case "people"         => wordList.map(word => new Person(word))
    case "organizations"  => wordList.map(word => new Organization(word))
    case "universities"   => wordList.map(word => new University(word))
    case "places"         => wordList.map(word => new Place(word))
    case "technology"     => wordList.map(word => new Technology(word))
    case "languages"      => wordList.map(word => new ProgrammingLanguage(word))
    case _                => List.empty[NamedEntity]
    }

    return myList
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   * Ejercicio 2: Implementar este método.
   *
   */
  def loadAll(): List[NamedEntity] = {
    val filesInData: List[java.io.File] = new java.io.File("data").listFiles().toList // listFiles = ls
    //println(filesInData.mkString(", ")) // Without .mkString it prints pointer value (e.g: [Ljava.io.File;@482b3875)
    //System.exit(0)
    val listOfLists: List[List[NamedEntity]] = filesInData.map(file => loadFromFile(file.getPath, quitTxt(file.getName)))
    //getName() got rid of "data/" section. ".getPath" keeps it.
    //flatten los mete todo en una sola lista
    val notListofLists: List[NamedEntity] = listOfLists.flatten

    //return List.empty[NamedEntity] // Used this to check if file compiled. Kind of a "fake" return.
    return notListofLists // Without this return, it doesn't work.
  }

  //Funcion auxiliar
  def quitTxt(file: String): String = {
    file.replaceAll(".txt","")
  }

  // Main. Used for testing. Execute "sbt run" and select
  // this file's name (Dictionary) to run it.
  def main(args: Array[String]): Unit = {
    // Verificación dada en enunciado
    val dict = Dictionary.loadAll()
    println(s"Total de entidades: ${dict.size}")
    dict.filter(_.entityType == "Person").foreach(p => println(p.describe))
  }
}

