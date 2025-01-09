package com.example.travail_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { Navbar() },
        bottomBar = { BottomBar() },
        content = { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    GreetingSection()
                    Spacer(modifier = Modifier.height(16.dp))
                    SearchBar()
                    Spacer(modifier = Modifier.height(16.dp))
                    IconScrollSection()
                }
            }
        }
    )
}

@Composable
fun Navbar() {
    Row(
        modifier = Modifier.statusBarsPadding()
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu",
            tint = Color(0xFF8E44AD),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {}

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "Profile",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun GreetingSection() {
    Column {
        Text(
            text = "Good Morning, Shreya...",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Text(
            text = "Make plan for weekend",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(end = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.li_search),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(12.dp)
                    .size(20.dp),
                tint = Color.Gray
            )
            BasicTextField(
                value = "",
                onValueChange = {},
                textStyle = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                decorationBox = { innerTextField ->
                    if (true) {
                        Text(
                            text = "Search Places",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(40.dp)
                .background(Color(0xFF674DEE), shape = MaterialTheme.shapes.medium)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.li_settings),
                contentDescription = "Settings Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}



@Composable
fun IconScrollSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconItem(R.drawable.red_fort, "Places")
        IconItem(R.drawable.plane, "Flights")
        IconItem(R.drawable.train, "Trains")
        IconItem(R.drawable.bus_stop, "Buses")
        IconItem(R.drawable.taxi, "Profile")
        IconItem(R.drawable.red_fort, "Places")
        IconItem(R.drawable.plane, "Flights")
        IconItem(R.drawable.train, "Trains")
        IconItem(R.drawable.bus_stop, "Buses")
        IconItem(R.drawable.taxi, "Profile")
    }
}

@Composable
fun IconItem(iconRes: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color(0xFF8E44AD),
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = Color.White,
        contentPadding = PaddingValues(8.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BottomBarItem(
                    icon = R.drawable.li_home,
                    contentDescription = "Home",
                    selected = true
                )
                BottomBarItem(
                    icon = R.drawable.li_briefcase,
                    contentDescription = "Bag",
                    selected = false
                )
                BottomBarItem(
                    icon = R.drawable.li_bookmark,
                    contentDescription = "Bookmark",
                    selected = false
                )
                BottomBarItem(
                    icon = R.drawable.li_user,
                    contentDescription = "Profile",
                    selected = false
                )
            }
        }
    )
}

@Composable
fun BottomBarItem(icon: Int, contentDescription: String, selected: Boolean) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        tint = if (selected) Color(0xFF8E44AD) else Color.Gray,
        modifier = Modifier.size(24.dp)
    )
}
