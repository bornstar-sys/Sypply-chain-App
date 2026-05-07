package com.cardano.prototype1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .background(Color.White)
    ) {

        // Background circles (reuse your style)
        DecorativeCircle(
            Modifier.size(140.dp)
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = (0).dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // 🔹 Header
            HeaderSection()

            Spacer(modifier = Modifier.height(30.dp))

            // 🔹 Action Cards
            ActionCard(
                title = "Add Medicine",
                subtitle = "Register new medicine batch",
                onClick = {
                    navController.navigate("add_medicine")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionCard(
                title = "Scan QR",
                subtitle = "Verify medicine authenticity",
                onClick = {
                    navController.navigate("scan")
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 🔹 Recent Activity
            Text(
                text = "Recent Activity",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActivityItem("Paracetamol", "Verified ✔")
            ActivityItem("Dolo 650", "Added ✔")

        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {
            Text("Welcome,", fontSize = 18.sp, color = Color.Gray)
            Text(
                "Roshan 👋",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(GradientBrush),
            contentAlignment = Alignment.Center
        ) {
            Text("R", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun ActionCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(GradientBrush),
                contentAlignment = Alignment.Center
            ) {
                Text("→", color = Color.White, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(subtitle, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun ActivityItem(name: String, status: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(name, fontWeight = FontWeight.Medium)
        Text(status, color = Color.Gray)
    }
}
@Preview(showSystemUi = true)
@Composable
fun PreviewDashboard(){
    val navController = androidx.navigation.compose.rememberNavController()
    DashboardScreen(navController = navController)
}