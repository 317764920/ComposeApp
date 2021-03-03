/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            }
        )
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
