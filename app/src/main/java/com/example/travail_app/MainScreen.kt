package com.example.travail_app

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = { Navbar() },
        bottomBar = { BottomBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
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
        modifier = Modifier
            .statusBarsPadding()
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

        Text(
            text = "Explore",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "Notifications",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun GreetingSection() {
    Column {
        Text(
            text = "Good Morning, Shreya!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Text(
            text = "Make plans for the weekend",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

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
                value = searchText,
                onValueChange = { searchText = it },
                textStyle = TextStyle(color = Color.Gray, fontSize = 14.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                decorationBox = { innerTextField ->
                    if (searchText.isEmpty()) {
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
            onClick = { /* Perform settings action */ },
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
    val items: List<Triple<Int, String, Int>> = listOf(
        Triple(R.drawable.places, "Places", R.drawable.places),
        Triple(R.drawable.plane, "Flights", R.drawable.plane),
        Triple(R.drawable.train, "Trains", R.drawable.train),
        Triple(R.drawable.buses, "Buses", R.drawable.buses),
        Triple(R.drawable.taxies, "Taxi", R.drawable.ic_launcher_foreground)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { (iconRes, label, imageRes) ->
            IconItem(iconRes, label, imageRes)
        }
    }
}



@Composable
fun IconItem(iconRes: Int, label: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        // Icône dans un cercle
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, shape = CircleShape)
                .border(1.dp, Color(0xFF8E44AD), CircleShape),
            contentAlignment = Alignment.Center
        ) {
                Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,

                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Label sous l'icône
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Image ou bannière sous le label
    }
}
@Composable
fun openDatePickerDialog(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()

    val datePickerDialog = remember {
        android.app.DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    datePickerDialog.show()
}
@Composable
fun BookingForm(
    modifier: Modifier = Modifier,
    selectedDate: String,
    onDateChange: (String) -> Unit,
    adultCount: Int = 0,
    childCount: Int = 0,
    onAdultCountChange: (Int) -> Unit = {},
    onChildCountChange: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("One Way", "Round Trip", "Multicity")
    val context = LocalContext.current
    var showDatePickerDialog by remember { mutableStateOf(false) }

    if (showDatePickerDialog) {
        openDatePickerDialog(context) { date ->
            onDateChange(date)
            showDatePickerDialog = true
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Book your Flight",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF4F4F4), RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Text(
                        text = title,
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                if (selectedTab == index) Color(0xFF5669FF) else Color.Transparent,
                                RoundedCornerShape(6.dp)
                            )
                            .padding(vertical = 8.dp)
                            .clickable { selectedTab = index },
                        color = if (selectedTab == index) Color.White else Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "From",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text("Choose Departure from") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedBorderColor = Color(0xFF5669FF)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(55.dp)
                            .background(Color(0xFF5669FF), CircleShape)
                            .padding(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.switch_vertical),
                            contentDescription = "Switch",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Text(
                text = "To",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text("Choose Arrival at") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedBorderColor = Color(0xFF5669FF)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                text = "Departure Date",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showDatePickerDialog = true },
                placeholder = { Text("Select Date") },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.primary),
                        contentDescription = "Calendar",
                        tint = Color(0xFF5669FF)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Decimal
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE8E8E8),
                    focusedBorderColor = Color(0xFF5669FF)
                ),
                shape = RoundedCornerShape(8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PassengerCounter(
                    label = "Adult (12+)",
                    count = adultCount,
                    onCountChange = onAdultCountChange,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                PassengerCounter(
                    label = "Child (2-12)",
                    count = childCount,
                    onCountChange = onChildCountChange,
                    modifier = Modifier.weight(1f)
                )
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .padding(8.dp)
                    .height(50.dp)
                    .fillMaxWidth(),
                border = BorderStroke(2.dp, Color(0xFF5669FF)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Search Flight",
                    color = Color(0xFF5669FF),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
@Composable
fun PassengerCounter(
    label: String,
    count: Int,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(10.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (count > 0) onCountChange(count - 1) },
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF5669FF), RoundedCornerShape(8.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.moi),
                    contentDescription = "Decrease",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = String.format("%02d", count),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

            IconButton(
                onClick = { onCountChange(count + 1) },
                modifier = Modifier
                    .size(32.dp)
                    .background(Color(0xFF5669FF), RoundedCornerShape(8.dp))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Increase",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
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
