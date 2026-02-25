package com.example.practice_1.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.CreamLight
import com.example.practice_1.ui.theme.ForestDark
import com.example.practice_1.ui.theme.OliveDark
import com.example.practice_1.ui.theme.SandMedium

class Profile(
    val ownerName: String = "Перевезенцева Мария",
    val email: String = "perevezencevamaria@gmail.com",
    val petName: String = "Ричард",
    val breed: String = "Шотланский вислоухий",
    val avatarRes: Int = R.drawable.ava_cat,
    val years: Int = 1,
    val description: String = "Самый милый кот на свете"
)
@Composable
fun PersonalCardScreen(profile: Profile = Profile(), onSave: (Profile) -> Unit = {} ) {
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
                        painter = painterResource(id = profile.avatarRes),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                            .clip(RoundedCornerShape(35.dp))
                    )
                    Spacer(modifier = Modifier.padding(bottom = 20.dp, top = 20.dp))
                    Column {
                        Text(
                            text = profile.ownerName,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = ForestDark
                        )
                        Text(
                            text = profile.email,
                            fontSize = 18.sp,
                            color = OliveDark
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Column (
                        modifier = Modifier.padding( 10.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp))
                    {
                        Text(
                            text = "Имя питомца: " + profile.petName,
                            fontSize = 20.sp,
                            color = ForestDark
                        )
                        Text(
                            text = "Порода: " + profile.breed,
                            fontSize = 18.sp,
                            color = ForestDark
                        )
                        Text(
                            text = "Количество лет: " + profile.years,
                            fontSize = 18.sp,
                            color = ForestDark
                        )
                        Text(
                            text = "Описание: " + profile.description,
                            fontSize = 18.sp,
                            color = ForestDark
                        )
                    }
                }
            }
        }
        }
}
