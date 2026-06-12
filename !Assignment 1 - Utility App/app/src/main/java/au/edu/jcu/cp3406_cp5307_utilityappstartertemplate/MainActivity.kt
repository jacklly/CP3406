package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.theme.CP3406_CP5603UtilityAppStarterTemplateTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data.Datasource
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.Task

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CP3406_CP5603UtilityAppStarterTemplateTheme {
                UtilityApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UtilityAppPreview() {
    CP3406_CP5603UtilityAppStarterTemplateTheme {
        UtilityApp()
    }
}

/*App default layout*/
@Composable
fun UtilityApp() {
    var selectedTab by remember { mutableStateOf("Utility") }

    Scaffold(
        floatingActionButton = {
            /*Only show the FAB if on the home screen*/
            if (selectedTab == "Utility") {
                ExtendedFloatingActionButton(
                    onClick = { selectedTab = "Adding" },
                    icon = { Icon(Icons.Default.Add, null) },
                    text = { Text("Add an Item") }
                )
            }
        },

        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Utility") },
                    label = { Text("Utility") },
                    selected = selectedTab == "Utility",
                    onClick = { selectedTab = "Utility" }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedTab == "Settings",
                    onClick = { selectedTab = "Settings" }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "Utility" -> UtilityScreen()
                "Settings" -> SettingsScreen()
                "Adding" -> AddingScreen()
            }
        }
    }
}

/*home screen that displays the list of tasks*/
@Composable
fun UtilityScreen() {
    TaskList(
        taskList = Datasource().loadTasks(),
    )
}

/*Screen for settings*/
@Composable
fun SettingsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp), Arrangement.spacedBy(16.dp)
    ) {
        Text("Settings Screen", style = MaterialTheme.typography.headlineMedium)
        Text("This is where you can add toggles or preferences.")
    }
}

/*Screen for adding new tasks*/
@Composable
fun AddingScreen() {

    val userTaskName = rememberSaveable { mutableStateOf("")}
    val noOfTimes = rememberSaveable { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            label = { Text("Task: ") },
            value = userTaskName.value,
            onValueChange = { userTaskName.value = it }
        )
        TextField(
            label = { Text("Times per day: ") },
            value = noOfTimes.value,
            onValueChange = { noOfTimes.value = it }
        )

    }
}

/*Make tasks appear, pulled from local file*/
@Composable
fun TaskAppear(task: Task, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column() {
            Text(
                text = LocalContext.current.getString(task.stringResourceId),
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

/*Draw the tasks in a column*/
@Composable
fun TaskList(taskList: List<Task>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(taskList) {task ->
            TaskAppear(
                task = task,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}
