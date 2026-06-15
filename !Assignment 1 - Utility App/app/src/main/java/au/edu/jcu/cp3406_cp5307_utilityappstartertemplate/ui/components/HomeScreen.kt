package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.components

import android.R.attr.left
import android.R.attr.right
import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.animation.R
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data.Datasource
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.Task


/*home screen that displays the list of tasks*/
@Composable
fun HomeScreen() {
    Column {
        TaskList(
            taskList = Datasource().loadTasks(),
        )
        AddingSection()
    }
}

/*Draw the tasks in a column*/
@Composable
fun TaskList(taskList: List<Task>, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .border(
                width = 1.dp,
                color = colorResource(android.R.color.holo_purple),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        LazyColumn(modifier = modifier) {
            items(taskList) { task ->
                TaskAppear(
                    task = task,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}

/*Make tasks appear, pulled from local file*/
@Composable
fun TaskAppear(task: Task, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column() {
            Row {
                Text(
                    text = LocalContext.current.getString(task.stringResourceId),
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {/*remove*/},
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Remove"
                    )
                }
            }
        }
    }
}

/*Adding items to the list*/
@Composable
fun AddingSection() {

    val userTaskName = rememberSaveable { mutableStateOf("")}
    val priorityValue = rememberSaveable { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        Text(
            text = "Add an Item"
        )

        TextField(
            label = { Text("Task: ") },
            value = userTaskName.value,
            onValueChange = { userTaskName.value = it },
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            label = { Text("Priority ( 1 - 10 ):") },
            value = priorityValue.value,
            onValueChange = { priorityValue.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}