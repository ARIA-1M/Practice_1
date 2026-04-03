
package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
    //onEdit: () -> Unit,
    onDeleteCat: (Cat) -> Unit
) {
    val currentUser = userViewModel.selectedUser
    val cats by catViewModel.allCat.collectAsState(initial = emptyList())
    val userCats = cats.filter { it.userId == currentUser?.id }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Карточка питомца",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SandMedium
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ava_cat),
                        contentDescription = "",
                        modifier = Modifier.size(120.dp)
                            .clip(RoundedCornerShape(60.dp)),
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

                    Text(
                        text = "Мои питомцы",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestDark,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

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
                                    onDelete = { onDeleteCat(cat)}
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onBack,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OliveDark,
                                contentColor = CreamLight,
                            )
                        ) {
                            Text("Вернуться назад")
                        }

                        Button(
                            onClick = onBack,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrownWarm,
                                contentColor = CreamLight,
                            )
                        ) {
                            Text("Добавить")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetCard(cat: com.example.practice_1.data.entity.Cat,
    onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = CreamLight
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                modifier = Modifier.size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = cat.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestDark
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Редактировать",
                            tint = OliveDark,
                            modifier = Modifier
                                .size(24.dp)
                            //.clickable { onEdit() }
                        )


                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Удалить",
                            tint = BrownWarm,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { onDelete() }
                        )
                    }
                }

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
        }
    }
}
