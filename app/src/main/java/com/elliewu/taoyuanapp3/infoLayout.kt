package com.elliewu.taoyuanapp3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            modifier = Modifier.size(width = 100.dp, height = 50.dp),
            onClick = {}
        ){
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.size(width = 150.dp, height = 200.dp).fillMaxWidth().fillMaxHeight()) {
                Image(
                    painterResource(id = R.drawable.list),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    colorFilter = ColorFilter.tint(Color(255,255,255))
                )
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "巡檢資訊",
                    color = Color(255,255,255),
                    fontSize = 10.sp,
                    )
            }
        }
    }
}