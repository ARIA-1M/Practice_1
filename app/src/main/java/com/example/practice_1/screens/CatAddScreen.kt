package com.example.practice_1.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.*
import com.example.practice_1.viewmodel.CatViewModel
import com.example.practice_1.viewmodel.UserViewModel

@Composable
fun CatAddScreen(
    catViewModel: CatViewModel,
    userViewModel: UserViewModel,
    catId: Int? = null,     // null это добавление, число это редактирование
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    // Получаем данные для редактирования
    val existingCat by catViewModel.getCatById(catId).collectAsState(initial = null)
    val isLoading = catId != null && existingCat == null
    val currentUser = userViewModel.selectedUser

    // 👇 ЛОГ 2: загрузился ли кот
    Log.d("CatAddScreen", "existingCat = $existingCat")

    // 👇 ЛОГ 3: текущий пользователь
    Log.d("CatAddScreen", "currentUser = ${currentUser?.email}")


    // Состояния полей
    var name by remember { mutableStateOf(existingCat?.name ?: "") }
    var breed by remember { mutableStateOf(existingCat?.breed ?: "") }
    var years by remember { mutableStateOf(existingCat?.years?.toString() ?: "") }
    var description by remember { mutableStateOf(existingCat?.description ?: "") }
    var selectedImage by remember { mutableStateOf(existingCat?.imageRes ?: R.drawable.ava_cat) }

    // 👇 ЛОГ 4: какие значения полей после remember
    LaunchedEffect(existingCat) {
        Log.d("CatAddScreen", "name = $name, breed = $breed, years = $years")
    }
    val isEditing = catId != null

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(60.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = SandMedium
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ava_cat),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Имя питомца") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = breed,
                onValueChange = { breed = it },
                label = { Text("Порода") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = years,
                onValueChange = { if (it.all { c -> c.isDigit() } || it.isEmpty()) years = it },
                label = { Text("Возраст (лет)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val yearsInt = years.toIntOrNull() ?: 0
                        if (name.isNotBlank() && breed.isNotBlank()) {
                            if (isEditing) {
                                existingCat?.let { cat ->
                                    catViewModel.update(
                                        id = cat.id,
                                        name = name,
                                        breed = breed,
                                        years = yearsInt,
                                        imageRes = selectedImage,
                                        description = description,
                                        userId = currentUser?.id ?: 1
                                    )
                                    onSave()
                                }
                            } else {
                                catViewModel.insertCat(
                                    name = name,
                                    breed = breed,
                                    years = yearsInt,
                                    imageRes = selectedImage,
                                    description = description,
                                    userId = currentUser?.id ?: 1
                                )
                                onSave()
                            }
                        }
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OliveDark, contentColor = CreamLight),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Сохранить")
                }

                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SandMedium, contentColor = ForestDark),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Отмена")
                }
            }
        }
    }
}