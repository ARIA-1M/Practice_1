package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun GalleryScreen(
    catViewModel: CatViewModel,
    onBack: () -> Unit
) {

    val cats by catViewModel.allCat.collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Галерея котиков",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cats) { cat ->
                    CatCard(cat = cat)
                }
            }

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OliveDark,
                    contentColor = CreamLight
                )
            ) {
                Text("Вернуться назад")
            }
        }
    }
}

@Composable
fun CatCard(cat: Cat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SandMedium
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cat.imageRes),
                contentDescription = cat.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Имя: ${cat.name}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestDark
                )
                Text(
                    text = "Порода: ${cat.breed}",
                    fontSize = 14.sp,
                    color = ForestDark
                )
                Text(
                    text = "Описание: ${cat.description}",
                    fontSize = 12.sp,
                    color = OliveDark,
                    maxLines = 3
                )
                Text(
                    text = "Возраст: ${cat.years} лет",
                    fontSize = 12.sp,
                    color = ForestDark.copy(alpha = 0.7f)
                )
            }
        }
    }
}