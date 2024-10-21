package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PizzaPartyViewModel : ViewModel() {   //ViewModel for Todo 7

    var numPeopleInput = mutableStateOf("")
    var hungerLevel = mutableStateOf("Medium")
    var totalPizzas = mutableIntStateOf(0)

    fun calculateNumPizzas() {
        val numPeople = numPeopleInput.value.toIntOrNull() ?: 0
        totalPizzas.value = calculatePizzaCount(numPeople, hungerLevel.value)
    }
}


fun calculatePizzaCount(numPeople: Int, hungerLevel: String): Int {
    val slicesPerPizza = 8
    val slicesPerPerson = when (hungerLevel) {
        "Light" -> 2
        "Medium" -> 3
        "Hungry" -> 4
        "Very hungry" -> 5
        else -> 5
    }
    return kotlin.math.ceil(numPeople * slicesPerPerson / slicesPerPizza.toDouble()).toInt()
}