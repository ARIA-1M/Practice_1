package com.example.practice_1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.practice_1.R

@Composable
fun FormScreen(){

    var pName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cName by remember { mutableStateOf("") }
    var cBreed by remember { mutableStateOf("") }

    Column(
        Modifier.Companion.fillMaxSize(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(  R.drawable.hello_cat),
            contentDescription = "",
            //modifier = Modifier.scale()
        )
    }
}