package com.cardano.prototype1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cardano.prototype1.R

// Define the brand colors from the image
val BrandTeal = Color(0xFF00E5FF)
val BrandGreen = Color(0xFF1DE9B6)
val GradientBrush = Brush.linearGradient(listOf(BrandTeal, BrandGreen))

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginClick : () -> Unit
) {

    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // --- Background Decorative Circles ---
        DecorativeCircle(Modifier.size(75.dp).offset(x = 20.dp, y = 60.dp))
        DecorativeCircle(Modifier.size(150.dp).align(Alignment.TopEnd).offset(x = 40.dp, y = 60.dp))
        DecorativeCircle(Modifier.size(40.dp).align(Alignment.TopEnd).offset(x = (-120).dp, y = 140.dp))
        DecorativeCircle(Modifier.size(160.dp).align(Alignment.CenterStart).offset(x = (-60).dp, y = 100.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))

            Text(
                text = "Login",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- The Login Card ---
            LoginCard()

            Spacer(modifier = Modifier.height(30.dp))

            // --- Login Button ---
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .align(Alignment.End)
                    .height(55.dp)
                    .width(160.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(25.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GradientBrush),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Login", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- Bottom Section ---
            SocialLoginSection()

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("New User? ", color = Color.Black, fontWeight = FontWeight.SemiBold)
                Text("Register", color = BrandGreen, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun LoginCard() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Left Accent Bar
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(6.dp)
                    .background(GradientBrush)
            )

            Column(modifier = Modifier.padding(20.dp)) {
                Text("Hello!!", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(16.dp))

                CustomInput(label = "Username", value = username, onValueChange = { username = it })

                Spacer(modifier = Modifier.height(20.dp))

                CustomInput(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )

                Text(
                    text = "I Forgot",
                    color = BrandTeal,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End).padding(top = 4.dp).clickable {
                        // click logic for password forgot?
                    }
                )
            }
        }
    }
}

@Composable
fun CustomInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        Text(label, color = Color.Gray, fontSize = 14.sp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                visualTransformation =
                    if (isPassword && !passwordVisible)
                        PasswordVisualTransformation()
                    else VisualTransformation.None,
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )

            if (isPassword) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
fun DecorativeCircle(modifier: Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(GradientBrush)
            .blur(10.dp) // Optional: Adds that soft glow feel
    )
}

@Composable
fun SocialLoginSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text("Login with!", color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            // Replace with your actual icon painters
            Icon(painter = painterResource(id = R.drawable.ic_google_logo), contentDescription = "mail icon")
            Icon(painter = painterResource(id = R.drawable.ic_microsoft_logo), contentDescription = "mail icon")
            //Icon(painter = painterResource(id = R.drawable.ic_google_logo), contentDescription = "mail icon")
            //Icon(painter = painterResource(id = R.drawable.ic_google_logo), contentDescription = "mail icon")

            //com.cardano.prototype1.ui.screens.SocialIcon(Color.White) // Google
            //com.cardano.prototype1.ui.screens.SocialIcon(Color(0xFF03A9F4)) // Twitter
            //com.cardano.prototype1.ui.screens.SocialIcon(Color(0xFF25D366)) // WhatsApp
        }
    }
}

@Composable
fun SocialIcon(color: Color) {
    Box(
        modifier = Modifier
            .size(35.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        // Icon(painter = ...) would go here
        Icon(painter = painterResource(id = R.drawable.ic_google_logo), contentDescription = "mail icon")

    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview(){
    val navController = androidx.navigation.compose.rememberNavController()
    LoginScreen(
    navController = navController,
        onLoginClick = {}
    )
}