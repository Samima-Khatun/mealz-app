package com.example.mealzapp.ui.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.MealsCategoriesResponse
import com.example.mealzapp.model.response.MealsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {

    //private val mealsJob = Job()

    init {
        //val scope = CoroutineScope(mealsJob + Dispatchers.IO)
/*        scope.launch() {
            val meals = getMeals()
            mealsState.value = meals
        }*/
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<MealsResponse>> =
        mutableStateOf(emptyList<MealsResponse>())

/*    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }*/

    private suspend fun getMeals(): List<MealsResponse> {
        return repository.getMeals().categories
    }
}