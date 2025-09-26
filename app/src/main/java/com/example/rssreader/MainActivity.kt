package com.example.rssreader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rssreader.ui.theme.RssReaderTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val people = listOf(
//            Person("Andy", "Li", 49),
//            Person("Joanna", "Wang", 50),
//            Person("John", "Doe", 25),
//            Person("Jack", "Ma", 70),
//            Person("Matt", "Wang", 30),
//            Person("Luke", "Green", 35),
//            Person("Steven", "Li", 16),
//            Person("Bob", "Johnson", 35),
//            Person("Alice", "Williams", 40),
//        )
//        val peopleFiltered = people.filter { it.age > 15 }
        val rssItems = listOf(
            RSSItem( "Welcome to Austin, Texas, We have music!", "There are many like it but this one's mine", RSSType.TEXT),
            RSSItem( "Welcome to my photo gallery,view photos!", "Click here for gallery", RSSType.IMAGE, R.drawable.photo_pizza01),
            RSSItem( "Press conference happening right now", "Watch live", RSSType.VIDEO, R.drawable.photo_pizza02),
        )
        setContent {
            RssReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        LazyColumn {
                            items(rssItems) {
                                when (it.type) {
                                    RSSType.TEXT -> {
                                        RSSItemText(it)
                                    }

                                    RSSType.VIDEO -> {
                                        RSSItemVideo(it)
                                    }

                                    RSSType.IMAGE -> {
                                        RSSItemImage(it)
                                    }
                                }
                            }
                        }
                        SearchBox()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBox() {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
        },
        singleLine = true,
        modifier = Modifier
            .padding(top = 26.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Kitty $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RssReaderTheme {
        Greeting("Android")
    }
}

@Composable
fun CardView(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_26),
                contentDescription = "Photo of Person",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Column {
                Text(
                    text = person.firstName,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = person.lastName,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "Age:" + person.age,
                    modifier = Modifier.padding(0.dp)
                )
            }

        }
    }
}

@Composable
fun RSSItemText(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = rssItem.title,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = rssItem.text,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun RSSItemVideo(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp)
    ) {
        Text(
            text = rssItem.title,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 12.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.photo_pizza02),
            contentDescription = "Photo of Person",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun RSSItemImage(rssItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = rssItem.title,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(top = 12.dp)
                .clickable{
                    Log.d("RSSItemImage", "Photo tapped!")
                }
        )
        rssItem.media?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Photo of Person",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}