package com.elliewu.taoyuanapp3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(device = Devices.PIXEL_C)
@Preview(device = Devices.PIXEL_3A)
@Preview(showBackground = true)

@Composable
fun infoLayout(){
    Row(modifier = Modifier
        .size(width = 1000.dp, height = 30.dp)
        .fillMaxWidth()
        .background(Color(255, 255, 255))){
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(147,146,146)
            ),
            modifier = Modifier.size(width = 100.dp, height = 30.dp),
            onClick = {}
        ){
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Image(
                    painterResource(id = R.drawable.list),
                    contentDescription = "",
                    //modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color(255,255,255))
                )
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "巡檢資訊",
                    color = Color(255,255,255))
            }
        }
    }
}