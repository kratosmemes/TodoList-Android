package com.krts.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.krts.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoListApp()
                }
            }
        }
    }
}

@Composable
fun TodoList() {
    val todos = remember { mutableStateListOf<String>() }
    val newTodoField = remember { mutableStateOf(TextFieldValue()) }

    Column {
        TextField(
            value = newTodoField.value,
            onValueChange = { newTodoField.value = it },
            label = { Text("New Todo") }
        )
        Button(
            onClick = {
                todos.add(newTodoField.value.text)
                newTodoField.value = TextFieldValue("")
            }
        ) {
            Text("Add Todo")
        }
        LazyColumn {
            items(todos.size) { todo ->
                Row {
                    Text(todo.toString())
                    Button(
                        onClick = { todos.remove(todo.toString()) }
                    ) {
                        Text("Remove")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoList()
}


