package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.CreamLight
import com.example.practice_1.ui.theme.ForestDark
import com.example.practice_1.ui.theme.OliveDark
import com.example.practice_1.ui.theme.SandMedium
import com.example.practice_1.ui.theme.White

@Composable
fun HomeScreen(){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Все о кошках",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = ForestDark,
                modifier = Modifier.padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ImageCard(
                text = "Домашние кошки",
                image = R.drawable.cat_1
            )

            InfoText(
                text = "Кошки — домашние животные, одни из самых популярных питомцев. Они млекопитающие и хищники, живут с людьми около 10 000 лет. В мире 600 млн домашних кошек и 256 пород — от персов до сфинксов. Первые кошки появились в Египте."
            )

            ImageCard(
                text = "Психология кошек",
                image = R.drawable.cat_2
            )

            InfoText(
                text = "Кошки не похожи на собак: они не считают хозяина вожаком. Но они любят по-своему и могут быть хорошими друзьями. Кошки сохранили природную гордость и независимость."
            )

            ImageCard(
                text = "С другими животными",
                image = R.drawable.cat_3
            )

            InfoText(
                text = "Кошки могут дружить с другими животными, если выросли вместе. Но охотничий инстинкт может проснуться в любой момент. С другими кошками обычно уживаются хорошо."
            )

            ImageCard(
                text = "С человеком",
                image = R.drawable.cat_4
            )

            InfoText(
                text = "Кошки привязываются к людям и даже перенимают привычки. Могут приносить добычу, делясь едой. Их одомашнивание — предмет споров, но они точно любят хозяев."
            )
        }
    }
}

@Composable
fun ImageCard(text: String, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = OliveDark,
        modifier = Modifier.padding(top = 20.dp)
            .align(Alignment.CenterHorizontally)
    )}
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp)

    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = text,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun InfoText(text: String) {
    Box(modifier = Modifier.background(SandMedium,
        shape = RoundedCornerShape(12.dp)),
        ){
        Text(
            text = text,
            fontSize = 20.sp,
            color = ForestDark,
            textAlign = TextAlign.Center,
            lineHeight = 30.sp,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }

}