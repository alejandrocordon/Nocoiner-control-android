package com.natio21.nocoiner_control.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.natio21.nocoiner_control.MainViewModel
import com.natio21.nocoiner_control.ui.theme.NatioOrange44

@Composable
fun SchedulerScreen(viewModel: MainViewModel, navController: NavController) {
    //xval schedule by viewModel.schedule.collectAsState()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(text = "Scheduler", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
        }

        //items(schedule) { daySchedule ->
        //    Text(text = daySchedule.day, style = MaterialTheme.typography.titleMedium)
        //    OutlinedTextField(
        //        value = daySchedule.onTime,
        //        onValueChange = { viewModel.updateSchedule(daySchedule.day, it, daySchedule.offTime) },
        //        label = { Text("On Time") },
        //        placeholder = { Text("Enter On Time (HH:mm)") },
        //        modifier = Modifier.fillMaxWidth()
        //    )
        //    Spacer(modifier = Modifier.height(8.dp))
        //    OutlinedTextField(
        //        value = daySchedule.offTime,
        //        onValueChange = { viewModel.updateSchedule(daySchedule.day, daySchedule.onTime, it) },
        //        label = { Text("Off Time") },
        //        placeholder = { Text("Enter Off Time (HH:mm)") },
        //        modifier = Modifier.fillMaxWidth()
        //    )
        //    Spacer(modifier = Modifier.height(16.dp))
        //}
//
        //item {
        //    Button(
        //        onClick = {
        //            viewModel.saveSchedule()
        //            navController.popBackStack()
        //        },
        //        shape = RoundedCornerShape(8.dp),
        //        colors = ButtonDefaults.buttonColors(NatioOrange40),
        //        modifier = Modifier.fillMaxWidth()
        //    ) {
        //        Text("Save")
        //    }
        //    Spacer(modifier = Modifier.height(16.dp))
        //    Button(
        //        onClick = { navController.popBackStack() },
        //        shape = RoundedCornerShape(8.dp),
        //        colors = ButtonDefaults.buttonColors(NatioOrange40),
        //        modifier = Modifier.fillMaxWidth()
        //    ) {
        //        Text("Cancel")
        //    }
        //}
    }
}