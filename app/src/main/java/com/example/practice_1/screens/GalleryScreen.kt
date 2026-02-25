package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.CreamLight
import com.example.practice_1.ui.theme.ForestDark
import com.example.practice_1.ui.theme.OliveDark
import com.example.practice_1.ui.theme.SandMedium

class Cat(
    val name: String,
    val breed: String,
    val desc: String,
    val image: Int
)
@Composable
fun GalleryScreen(){
    val cats = remember {
        listOf(
            Cat("Герц", "Сиамский", "Ласковый и разговорчивый", R.drawable.cat1),
            Cat("Мурка", "Мейн-кун", "Пушистая и спокойная", R.drawable.cat2),
            Cat("Снежок", "Британский", "Белый и пушистый", R.drawable.cat3),
            Cat("Ричард", "Шотландский", "Игривый и активный", R.drawable.cat4),
            Cat("Сфинкс", "Канадский", "Лысый и ласковый", R.drawable.cat5),
            Cat("Клеопатра", "Египетская", "Грациозная и таинственная", R.drawable.cat6),
            Cat("Лиана", "Русская голубая", "Серебристая шерсть, преданная", R.drawable.cat7),
            Cat("Симба", "Бенгальская", "Дикий окрас, очень активный и любопытный", R.drawable.cat8),
            Cat("Барон", "Невская маскарадная", "Пушистый, с голубыми глазами", R.drawable.cat9),
            Cat("Плюша", "Экзотическая", "Спокойная, с приплюснутой мордочкой", R.drawable.cat10)
        )
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Галерея котиков",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cats) { cat ->
                    CatCard(cat = cat)
                }
            }
        }}
}

@Composable
fun CatCard(cat: Cat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
        containerColor = SandMedium
    )){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = cat.image),
                contentDescription = cat.name,
                modifier = Modifier.size(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop

            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Имя: " + cat.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestDark
                )
                Text(
                    text = "Порода: " + cat.breed,
                    fontSize = 16.sp,
                    color = ForestDark
                )
                Text(
                    text = "Описание: " + cat.desc,
                    fontSize = 16.sp,
                    color = OliveDark
                )
            }
        }
    }
}