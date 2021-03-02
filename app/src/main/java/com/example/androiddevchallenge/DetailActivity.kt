package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Dog

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog = intent.extras?.getSerializable("item") as Dog
        setContent {
            MaterialTheme {
                Detail(dog, onClick = { finish() })
            }
        }
    }
}

@Composable
@Preview
fun Detail(dog: Dog, onClick: () -> Unit) {
    Column(Modifier.background(color = Color.White)) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }
            },
            title = {
                Text("Detail")
            })
        PetCardDetail(dog)
    }
}

@Composable
fun PetCardDetail(dog: Dog) {
    val stateDog by remember { mutableStateOf(dog) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NetworkImage(
            url = stateDog.imgUrl,
            modifier = Modifier
                .clip(RoundedCornerShape(6))
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(
            text = stateDog.title,
            color = Color.Red,
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(top = 4.dp)
        )
        Text(
            text = stateDog.description,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(top = 4.dp)
        )
    }
}