package com.example.practice_1.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

class CatFact(
    val id: Int,
    val fact: String,
    val icon: Int
)
@Composable
fun DetailScreen(onBack: () -> Unit) {

    val facts = remember {
        listOf(
            CatFact(1, "Кошки не мяукают друг с другом — это только для людей", R.drawable.cat_meow),
            CatFact(2, "Рисунок на носу кошки уникален, как отпечаток пальца", R.drawable.cat_nose),
            CatFact(3, "На лбу у кошек есть рисунок в виде буквы М", R.drawable.cat_m),
            CatFact(4, "Усы кошки показывают её настроение", R.drawable.cat_whiskers),
            CatFact(5, "Кошка может иметь более 100 котят за жизнь", R.drawable.cat_kitten),
            CatFact(6, "Кошки всегда приземляются на лапы", R.drawable.cat_fall),
            CatFact(7, "Кошки спят около 16 часов в день", R.drawable.cat_sleep),
            CatFact(8, "У кошек потеют только подушечки лап", R.drawable.cat_paws),
            CatFact(9, "Кошки издают около 100 звуков (собаки только 10)", R.drawable.cat_sound),
            CatFact(10, "Кошка прыгает в 5 раз выше своего роста", R.drawable.cat_jump)
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
                text = "Топ 10 фактов о котах",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(facts) { fact ->
                    FactGrid(fact = fact)
                }
            }
        }
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = OliveDark,
                contentColor = CreamLight,)
        ) {
            Text("Вернутся назад")
        }
    }
}

@Composable
fun FactGrid(fact: CatFact) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SandMedium
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = fact.icon),
                contentDescription = "",
                modifier = Modifier.size(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = fact.fact,
                fontSize = 16.sp,
                color = ForestDark,
                lineHeight = 16.sp
            )
        }
    }
}