// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   *
   * (Ejercicio 3): Implementar este método.
   *
   *   Para cada entidad en el diccionario, verificar si su texto aparece en el
   *   texto del post. Retornar únicamente las entidades que aparecen.
   *
   *   Ejemplo:
   *     text       = "Scala fue creado en EPFL por Martin Odersky"
   *     dictionary = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky"),
   *                    Person("Ada Lovelace")   ← no aparece en el texto
   *                  )
   *     resultado  = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky")
   *                  )
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    // Filtramos dictionary quedandonos con las entidades que esten en text.
    dictionary.filter(entity => text.contains(entity.text))
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   *
   * TODO (Ejercicio 5): Implementar este método.
   *
   *   Ejemplo:
   *     entities = List(
   *                  Person("Alan Turing"),
   *                  ProgrammingLanguage("Scala"),
   *                  Person("Ada Lovelace"),
   *                  University("MIT")
   *                )
   *     resultado = Map(
   *                   "Person"              -> 2,
   *                   "ProgrammingLanguage" -> 1,
   *                   "University"          -> 1
   *                 )
   */
  //def countByType(entities: List[NamedEntity]): Map[String, Int] = {
  //  ???
  //}

  // Main. Used for testing. Execute "sbt run" and select
  // this file's name (Analyzer) to run it.
  def main(args: Array[String]): Unit = {
    val text: String = "Alan Turin esta en London"
    val dict: List[NamedEntity] = Dictionary.loadAll()
    val resultado: List[NamedEntity] = detectEntities(text, dict)
    println("---------------------------------------------------------")
    println("Ejemplo donde no toma el nombre de persona, ya que esta mal escrito\n")
    println(s"Texto: ${text}\n")
    println(s"Resultado: ${resultado}\n")
    println("---------------------------------------------------------")
    val text2: String = "Scala fue creado en EPFL por Martin Odersky"
    val dict2: List[NamedEntity] = Dictionary.loadAll()
    val resultado2: List[NamedEntity] = detectEntities(text2, dict2)
    println("Ejemplo 2, ejecucion normal")
    println(s"Texto: ${text2}\n")
    println(s"Resultado: ${resultado2}\n")
    println("---------------------------------------------------------")
  }
}
