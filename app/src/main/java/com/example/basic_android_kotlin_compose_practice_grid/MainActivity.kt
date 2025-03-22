package com.example.basic_android_kotlin_compose_practice_grid

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basic_android_kotlin_compose_practice_grid.dataSource.DataSource
import com.example.basic_android_kotlin_compose_practice_grid.model.Topic
import com.example.basic_android_kotlin_compose_practice_grid.ui.theme.BasicandroidkotlincomposepracticegridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicandroidkotlincomposepracticegridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicsApp()
                }
            }
        }
    }
}


@Composable
fun TopicsApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )

    ) {
        CardList(
            DataSource.topics
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    BasicandroidkotlincomposepracticegridTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun CardItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(topic.imageId),
                contentDescription = stringResource(topic.stringId),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    stringResource(topic.stringId),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier

                        .padding(
                            top = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        ),

                )
                Row(
                    modifier = Modifier.padding(bottom = 1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.dot_icon),
                        contentDescription = "dot",
                        modifier = Modifier
                    )
                    Text(
                        topic.courseCountId.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun CardList(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topicList) { topic ->
            CardItem(topic)
                Modifier.padding(8.dp)
        }
    }
}

@Preview
@Composable
fun PreviewCardItem() {
    CardItem(
        Topic(R.string.photography, 100, R.drawable.photography)
    )
}
@Preview
@Composable
fun PreviewCardList() {
    val topicList = DataSource.topics

    CardList(
        topicList,
        )
}
