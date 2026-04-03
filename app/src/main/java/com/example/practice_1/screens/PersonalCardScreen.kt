package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.data.entity.Cat
import com.example.practice_1.ui.theme.*
import com.example.practice_1.viewmodel.CatViewModel
import com.example.practice_1.viewmodel.UserViewModel


@Composable
fun PersonalCardScreen(
    userViewModel: UserViewModel,
    catViewModel: CatViewModel,
    onBack: () -> Unit,
    onAdd: () -> Unit,
    onDelete: (Cat) -> Unit
) {
    val currentUser = userViewModel.selectedUser
    val cats by catViewModel.allCat.collectAsState(initial = emptyList())
    val userCats = cats.filter { it.userId == currentUser?.id }

    // Диалог редактирования
    var showEditDialog by remember { mutableStateOf(false) }
    val selectedCat = catViewModel.selectedCat

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Карточка питомца",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SandMedium)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ava_cat),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp).clip(RoundedCornerShape(60.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = currentUser?.email ?: "email не указан",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = OliveDark
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (userCats.isEmpty()) {
                        Text(
                            text = "У вас пока нет питомцев",
                            fontSize = 14.sp,
                            color = OliveDark,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.heightIn(max = 400.dp)
                        ) {
                            items(userCats) { cat ->
                                PetCard(
                                    cat = cat,
                                    onDelete = { onDelete(cat) },
                                    onEdit = {
                                        catViewModel.selectCat(cat)
                                        showEditDialog = true
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = onBack,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OliveDark,
                                contentColor = CreamLight
                            )
                        ) {
                            Text("Вернуться назад")
                        }

                        Button(
                            onClick = onAdd,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrownWarm,
                                contentColor = CreamLight
                            )
                        ) {
                            Text("Добавить")
                        }
                    }
                }
            }
        }
    }

    if (showEditDialog && selectedCat != null) {
        val cat = selectedCat

        var name by remember { mutableStateOf(cat.name) }
        var breed by remember { mutableStateOf(cat.breed) }
        var years by remember { mutableStateOf(cat.years.toString()) }
        var description by remember { mutableStateOf(cat.description) }

        AlertDialog(
            onDismissRequest = {
                showEditDialog = false
                catViewModel.clearSelectedCat()
            },
            title = {
                Text(
                    "Редактировать питомца",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Имя питомца") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    OutlinedTextField(
                        value = breed,
                        onValueChange = { breed = it },
                        label = { Text("Порода") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    OutlinedTextField(
                        value = years,
                        onValueChange = {
                            if (it.all { c -> c.isDigit() } || it.isEmpty()) years = it
                        },
                        label = { Text("Возраст (лет)") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Описание") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val yearsInt = years.toIntOrNull() ?: 0
                        if (name.isNotBlank() && breed.isNotBlank()) {
                            catViewModel.update(
                                id = cat.id,
                                name = name,
                                breed = breed,
                                years = yearsInt,
                                imageRes = cat.imageRes,
                                description = description,
                                userId = currentUser?.id ?: 1
                            )
                            showEditDialog = false
                            catViewModel.clearSelectedCat()
                        }
                    }
                ) {
                    Text("Сохранить", color = OliveDark, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showEditDialog = false
                    catViewModel.clearSelectedCat()
                }) {
                    Text("Отмена", color = BrownWarm)
                }
            }
        )
    }
}

@Composable
fun PetCard(
    cat: Cat,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CreamLight)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cat.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestDark
                )
                Text(
                    text = "Порода: ${cat.breed}",
                    fontSize = 14.sp,
                    color = OliveDark
                )
                Text(
                    text = "Возраст: ${cat.years} лет",
                    fontSize = 14.sp,
                    color = OliveDark
                )
                Text(
                    text = cat.description,
                    fontSize = 12.sp,
                    color = ForestDark.copy(alpha = 0.7f),
                    maxLines = 2
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Редактировать",
                    tint = OliveDark,
                    modifier = Modifier.size(24.dp).clickable { onEdit() }
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Удалить",
                    tint = BrownWarm,
                    modifier = Modifier.size(24.dp).clickable { onDelete() }
                )
            }
        }
    }
}