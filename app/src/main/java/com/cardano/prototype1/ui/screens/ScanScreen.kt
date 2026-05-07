package com.cardano.prototype1.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ScanScreen(navController: NavController) {

    var scannedResult by remember { mutableStateOf("") }
    val launcher  = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){
        isGranted ->
    }
    LaunchedEffect(Unit){
        launcher.launch(Manifest.permission.CAMERA)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Camera Preview
        QrScannerView { result ->
            scannedResult = result

            // Navigate when scanned
            navController.navigate("result/$result")
        }

        // Overlay UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Scan QR Code",
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(250.dp)
                    .border(2.dp, Color.White, RoundedCornerShape(16.dp))
            )

            Text(
                text = "Align QR within frame",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewScan(){
    val navController = androidx.navigation.compose.rememberNavController()
    ScanScreen(navController)
}