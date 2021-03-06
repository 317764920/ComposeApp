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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.androiddevchallenge.model.Dog
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                List(viewModel.dogsLiveData, onItemClick = { dog -> toDetail(dog) })
            }
        }
        viewModel.readData()
    }

    private fun toDetail(dog: Dog) {
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("item", dog)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}

@Composable
@Preview
fun List(dogsLiveData: LiveData<List<Dog>>, onItemClick: (dog: Dog) -> Unit) {
    Column(Modifier.background(color = White)) {
        TopAppBar(
            title = {
                Text("PetHome")
            }
        )
        PetList(
            dogsLiveData,
            onItemClick
        )
    }
}

@Composable
fun PetList(dogsLiveData: LiveData<List<Dog>>, onClick: (dog: Dog) -> Unit) {
    val pets by dogsLiveData.observeAsState()
    pets?.let {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            items(it) { dog ->
                PetCard(dog, onClick)
            }
        }
    }
}

@Composable
fun PetCard(dog: Dog, onClick: (dog: Dog) -> Unit) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(3.dp)
            .padding(3.dp)
    ) {
        Column(
            Modifier
                .clickable(onClick = { onClick(dog) })
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            NetworkImage(
                url = dog.imgUrl,
                modifier = Modifier
                    .clip(RoundedCornerShape(6))
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = dog.title,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun NetworkImage(url: String, modifier: Modifier) {
    CoilImage(
        data = url,
        contentDescription = null,
        modifier = modifier
    )
}
