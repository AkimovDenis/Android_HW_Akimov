package com.example.hwlesson14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Task1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val age: Int = 39
        val height: Double = 1.77
        val name: String = "денис"
        val isStudent: Boolean = true

        val textView = findViewById<TextView>(R.id.textView)

        val category = getCategoryByAge(age)   // Task2
        val multiplicationTable = getMultiplicationTable(5) // Task3
        val sumResult = sum(7, 8) // Task4
        val user = User("Denis", 39) // Task5

        // Task6:
        val nullableName: String? = "Denis"
        val lengthMessage = if (nullableName != null) {
            "Длина строки: ${nullableName.length}"
        } else {
            "Переменная nullableName равна null"
        }

        // Task7
        val stringsList = listOf("Привет", "Мир", "Как дела?", "Хорошо", "Погода", "Отлично")
        val filteredList = stringsList.filter { it.length > 5 }

        val filteredStrings = filteredList.joinToString("\n")

        // Task8
        val today = "суббота"
        val dayType = getDayType(today)

        // Task9
        val capitalizedName = name.capitalizeFirstLetter()

        // Task10
        val product = Product("Ноутбук", 695000.0)
        val (productName, productPrice) = product
        val productInfo = "Продукт: $productName, Цена: $productPrice"

        textView.text = "Task1\nВозраст: $age\n" +
                "Рост: $height\n" +
                "Имя: $capitalizedName\n" +
                "Студент: $isStudent\n" +
                "\nTask2\nКатегория: $category\n" +
                "\nTask3\nТаблица умножения на 5:\n$multiplicationTable" +
                "\nTask4\nСумма чисел 7 и 8: $sumResult\n" +
                "\nTask5\nДанные пользователя:\nИмя: ${user.name}, Возраст: ${user.age}\n" +
                "\nTask6\nДлина переменной: $lengthMessage\n" +
                "\nTask7\nСтроки длиной больше 5 символов:\n$filteredStrings\n" +
                "\nTask8\nТип дня ($today): $dayType\n" +
                "\nTask10\nИнфо: $productInfo\n"
    }

    // Task2
    private fun getCategoryByAge(age: Int): String {
        return when {
            age < 13 -> "Ребёнок"
            age < 18 -> "Подросток"
            else -> "Взрослый"
        }
    }

    // Task3
    private fun getMultiplicationTable(number: Int): String {
        var result = ""
        for (i in 1..10) {
            result += "$number x $i = ${number * i}\n"
        }
        return result
    }

    // Task4
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // Task5:
    class User(val name: String, val age: Int)

    // Task8:
    private fun getDayType(day: String): String {
        return when (day.lowercase()) {
            "понедельник", "вторник", "среда", "четверг", "пятница" -> "Рабочий день"
            "суббота", "воскресенье" -> "Выходной"
            else -> "Неизвестный день"
        }
    }

    // Task9
    private fun String.capitalizeFirstLetter(): String {
        if (this.isEmpty()) return this
        return this[0].uppercaseChar() + this.substring(1)
    }

    // Task10
    data class Product(val name: String, val price: Double)
}
