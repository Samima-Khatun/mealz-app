package com.example.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mealzapp.model.response.MealsResponse
import com.example.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
/*    val rememberedMeals: MutableState<List<MealsResponse>> =
        remember { mutableStateOf(emptyList<MealsResponse>()) }*/

/*    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "GET_MEALS") {
        coroutineScope.launch(Dispatchers.IO) {
            val meals = viewModel.getMeals()
            rememberedMeals.value = meals
        }
    }*/

    val meals = viewModel.mealsState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealCategory(meal = meal)
        }
    }
}

@Composable
fun MealCategory(meal: MealsResponse) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.animateContentSize()
        ) {
            Image(
                painter = rememberImagePainter(data = meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(.8f)
                    .padding(16.dp)
            ) {
                Text(
                    meal.name,
                    style = MaterialTheme.typography.h6
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        meal.description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.subtitle2,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 4
                    )
                }
            }
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded)
                            Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {

    }
}