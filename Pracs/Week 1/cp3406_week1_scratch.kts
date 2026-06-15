// Week 1 Kotlin Scratch Examples
// Designed for a Kotlin Scratch file.

// ------------------------------------------------------------
// 1. Welcome message
// ------------------------------------------------------------

var name = "Welcome to CP3406/CP5307"

name

println(name)


// ------------------------------------------------------------
// 2. Changing a variable
// ------------------------------------------------------------

name = "Welcome to Mobile App Development"

name

println(name)


// ------------------------------------------------------------
// 3. Values that do not change
// ------------------------------------------------------------

val subjectCode = "CP3406"
val subjectName = "Mobile App Development"

subjectCode
subjectName

println(subjectCode)
println(subjectName)


// ------------------------------------------------------------
// 4. Basic types
// ------------------------------------------------------------

val weekNumber = 1
val isWeekOne = true
val rating = 4.5

weekNumber
isWeekOne
rating

println(weekNumber)
println(isWeekOne)
println(rating)


// ------------------------------------------------------------
// 5. String templates
// ------------------------------------------------------------

val studentName = "Alex"

val message = "$studentName is studying $subjectCode in week $weekNumber."

message

println(message)


// ------------------------------------------------------------
// 6. Simple calculation
// ------------------------------------------------------------

var score = 0

score

score = score + 10

score

score += 5

score

println("Final score: $score")


// ------------------------------------------------------------
// 7. If expression
// ------------------------------------------------------------

val mark = 72

val result = if (mark >= 50) {
    "Pass"
} else {
    "Fail"
}

result

println("Mark: $mark")
println("Result: $result")


// ------------------------------------------------------------
// 8. List of topics
// ------------------------------------------------------------

val topics = listOf(
    "Kotlin",
    "Android Studio",
    "Functions",
    "Classes"
)

topics

topics[0]

topics.size

println(topics)
println("First topic: ${topics[0]}")
println("Number of topics: ${topics.size}")


// ------------------------------------------------------------
// 9. Loop through a list
// ------------------------------------------------------------

for (topic in topics) {
    println("This week we are learning: $topic")
}


// ------------------------------------------------------------
// 10. Mutable list
// ------------------------------------------------------------

val ingredients = mutableListOf<String>()

ingredients

ingredients.add("Rice")
ingredients.add("Beans")

ingredients

println(ingredients)

ingredients.remove("Rice")

ingredients

println(ingredients)


// ------------------------------------------------------------
// 11. Simple function
// ------------------------------------------------------------

fun greetStudent(name: String) {
    println("Hello, $name!")
}

greetStudent("Alex")
greetStudent("Sam")


// ------------------------------------------------------------
// 12. Function with a return value
// ------------------------------------------------------------

fun doubleNumber(number: Int): Int {
    return number * 2
}

val doubled = doubleNumber(10)

doubled

println(doubled)


// ------------------------------------------------------------
// 13. Nullable value
// ------------------------------------------------------------

var optionalName: String? = "Alex"

optionalName

println(optionalName)

optionalName = null

optionalName

println(optionalName)


// ------------------------------------------------------------
// 14. Safe call
// ------------------------------------------------------------

var maybeStudentName: String? = "Alex"

maybeStudentName?.length

println(maybeStudentName?.length)

maybeStudentName = null

maybeStudentName?.length

println(maybeStudentName?.length)


// ------------------------------------------------------------
// 15. Elvis operator
// ------------------------------------------------------------

val displayName = maybeStudentName ?: "Unknown student"

displayName

println(displayName)


// ------------------------------------------------------------
// 16. Simple class
// ------------------------------------------------------------

class Recipe {
    private val ingredients = mutableListOf<String>()

    fun addIngredient(name: String) {
        ingredients.add(name)
    }

    fun getIngredients(): List<String> {
        return ingredients
    }
}

val recipe = Recipe()

recipe.addIngredient("Rice")
recipe.addIngredient("Beans")

recipe.getIngredients()

println(recipe.getIngredients())