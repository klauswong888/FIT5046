package com.example.assignment1.ui.theme

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

@RequiresApi(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourScreen() {
    var birth by remember { mutableStateOf("") } // 日期字符串

    // 声明一个用于保存日期选择器是否显示的状态
    var showDatePicker by remember { mutableStateOf(false) }

    // 定义一个函数，用于在点击 KeyboardArrowDown 图标时显示日期选择器
    val onShowDatePickerClick: () -> Unit = {
        showDatePicker = true
    }

    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1) // month (0) is January
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    // 将选定的日期传递给 onBirthSelected 回调函数，更新 birth 的值
                    birth = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(Date(selectedDate))
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) //end of dialog
        { //still column scope
            DatePicker(
                state = datePickerState
            )
        }
    }

    // 将 OutlinedTextField 和 Button 放在同一行的 Row 中
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { showDatePicker = true }) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            // 点击 KeyboardArrowDown 图标时显示日期选择器
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = birth,
            onValueChange = { /* Do nothing */ },
            label = {},
            modifier = Modifier.weight(1f)
        )

        // 使用 LaunchedEffect 确保在选择日期后立即更新 birth 的值
        LaunchedEffect(selectedDate) {
            birth = "Date of Birth: ${formatter.format(Date(selectedDate))}"
        }
    }
}




@Preview(showBackground = true)
@Composable
fun Preview() {
    YourScreen()
}


