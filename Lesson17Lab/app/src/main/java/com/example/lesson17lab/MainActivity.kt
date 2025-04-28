package com.example.lesson17lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)

        val builder = StringBuilder()

        // Задание 1
        val words = listOf("кот", null, "слон", "бегемот", null, "мышь")
        val longestWord = words.filterNotNull().maxByOrNull { it.length }
        builder.append("Задание 1:\nСамое длинное слово: $longestWord\n\n")

        // Задание 2
        val rawName: String? = " аЛеКСандр "
        val formattedName = rawName.formatName()
        builder.append("Задание 2:\nФорматированное имя: $formattedName\n\n")

        // Задание 3
        val wordList = listOf("кот", "пёс", "кот", "мышь", "пёс")
        val uniqueWordsCount = wordList.toSet().size
        builder.append("Задание 3:\nКоличество уникальных слов: $uniqueWordsCount\n\n")

        // Задание 4
        val users = listOf(
            User("Аня", 20, "anya@gmail.com"),
            User("Борис", 17, null),
            User("Катя", 25, "katya@mail.com"),
            User("Миша", 17, "misha@mail.com")
        )
        val groupedByAge = users.groupBy { it.age }
        builder.append("Задание 4:\nПользователи по возрасту:\n")
        for ((age, userList) in groupedByAge) {
            builder.append("$age лет: ${userList.size} пользователь(ля/ей)\n")
        }
        builder.append("\n")

        // Задание 5
        val numbers: List<Int?> = listOf(10, null, 5, 8, null, 15)
        val nonNullSorted = numbers.filterNotNull().sortedDescending()
        val average = if (nonNullSorted.isNotEmpty()) nonNullSorted.average() else 0.0
        builder.append("Задание 5:\nОтсортированные числа: $nonNullSorted\nСреднее значение: $average\n\n")

        // Задание 6
        val adultsWithEmail = users.filter { it.isAdult() && it.email != null }
            .map { it.email!!.uppercase() }
        builder.append("Задание 6:\nEmail взрослых пользователей: $adultsWithEmail\n\n")

        // Задание 7
        val numList = listOf(2, 4, 6, 8, 10)
        val allPositive = numList.all { it > 0 }
        val anyEven = numList.any { it % 2 == 0 }
        builder.append("Задание 7:\nВсе положительные: $allPositive\nЕсть чётные: $anyEven\n\n")

        // Задание 8
        val duplicateList = listOf(5, 1, 2, 5, 3, 2, 4)
        val uniqueSortedList = duplicateList.toSet().sorted()
        builder.append("Задание 8:\nУникальные отсортированные числа: $uniqueSortedList\n\n")

        // Задание 9
        val studentScores = mapOf(
            "Иван" to 80,
            "Мария" to 90,
            "Петр" to 70,
            "Оля" to 95
        )
        val avgScore = studentScores.values.average()
        val topStudents = studentScores.filter { it.value > avgScore }.keys
        builder.append("Задание 9:\nСредний балл: $avgScore\nСтуденты выше среднего: $topStudents\n\n")

        // Задание 10
        val listA = listOf("яблоко", "банан", "груша")
        val listB = listOf("банан", "киви", "груша")
        val mergedList = (listA + listB).toSet().sorted()
        builder.append("Задание 10:\nОбъединённый список: $mergedList\n\n")

        // Задание 11
        val nullableString: String? = "HelloWorld"
        nullableString?.takeIf { it.length > 5 }?.let {
            builder.append("Задание 11:\n${it.uppercase()}\n")
        } ?: builder.append("Задание 11:\nСтрока null или короткая\n")

        textView.text = builder.toString()
    }

    data class User(val name: String, val age: Int, val email: String?)

    private fun String?.formatName(): String {
        return this?.trim()?.lowercase()?.replaceFirstChar { it.uppercase() } ?: ""
    }

    private fun User.isAdult(): Boolean {
        return this.age >= 18
    }
}
