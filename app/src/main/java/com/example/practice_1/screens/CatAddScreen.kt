package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val currentUser = userViewModel.selectedUser

    var name by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var years by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf(R.drawable.ava_cat) }

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
            // Фото
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(60.dp)),
                    colors = CardDefaults.cardColors(containerColor = SandMedium)
                ) {
                    Image(
                        painter = painterResource(id = selectedImage),
                        contentDescription = "Фото питомца",
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