package com.example.practice_1.screens

import android.service.autofill.UserData
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practice_1.R
import com.example.practice_1.ui.theme.CreamLight
import com.example.practice_1.ui.theme.ForestDark
import com.example.practice_1.ui.theme.OliveDark
import com.example.practice_1.ui.theme.SandMedium

@Composable
fun FormScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CreamLight
    ){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.look_cat),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = SandMedium
            )
        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Text(
                    text = "Анкета котика",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = ForestDark,
                    modifier = Modifier.padding(bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
                )

                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordVisible by remember { mutableStateOf(true) }
                var name by remember { mutableStateOf("") }
                var nameCat  by remember {  mutableStateOf("")}
                var breedCat by remember { mutableStateOf("") }
                var years by remember { mutableStateOf("") }
                var description by remember { mutableStateOf("") }
                var usersList by remember { mutableStateOf(listOf<String>()) }

                TextField(
                    value = email,
                    onValueChange = { it -> email = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите email")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = password,
                    onValueChange = { it -> password = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    placeholder = {Text("Введите пароль")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (!passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.AccountCircle
                        else Icons.Filled.Close

                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, "")
                        }
                    }
                )

                TextField(
                    value = name,
                    onValueChange = { it -> name = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите ваше имя")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = nameCat,
                    onValueChange = { it -> nameCat = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите имя котика")},
                        colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = breedCat,
                    onValueChange = { it -> breedCat = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите породу котика")},
                    colors = TextFieldDefaults.colors(
                    focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = years,
                    onValueChange = { it -> years = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите ваше имя")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = description,
                    onValueChange = { it -> description = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    placeholder = {Text("Введите ваше имя")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = ForestDark,
                        unfocusedTextColor = ForestDark,
                        focusedIndicatorColor = OliveDark,
                        focusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        if (email.isNotBlank() && password.isNotBlank() &&
                            nameCat.isNotBlank() && breedCat.isNotBlank()) {

                            val newUser = "$email;$name;$nameCat;$breedCat;$years;$description"
                            usersList = usersList + newUser


                            email = ""
                            password = ""
                            name = ""
                            nameCat = ""
                            breedCat = ""
                            years = ""
                            description = ""
                            passwordVisible = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OliveDark,
                        contentColor = CreamLight,

                    )
                ) {
                    Text("Отправить")
                }

            } }
        }
    }
}