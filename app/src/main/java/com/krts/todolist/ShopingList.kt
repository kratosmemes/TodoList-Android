package com.krts.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ShoppingItem(
    val id:Int,
    var name: String,
    var isEditing: Boolean = false,
    var isCompleted: Boolean = false,
)

@Preview
@Composable
fun ToDoListApp(){
    var sTasks = remember{ mutableStateListOf<ShoppingItem>() }
    var showDialog by remember { mutableStateOf(false) }
    var itemName  by remember { mutableStateOf("")}

    Column(
        modifier= Modifier.fillMaxWidth(),
        //verticalArrangement = Arrangement.Center
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = { showDialog = true},
            modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
        ){
            Text("Add task")
        }

        //Uncompleted tasks
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Uncompleted tasks")
        LazyColumn(
            modifier= Modifier
                .padding(16.dp)
        ){
            items(sTasks){ item ->
                if(!item.isCompleted){
                    ShoppingListItem(item,
                        {
                            sTasks.forEach{println("$it")}
                            sTasks[item.id-1] = sTasks[item.id-1].copy(
                                isCompleted = true
                            )
                        },
                        {
                            //Remove task from list lambda function
                            sTasks.remove(item)
                        }
                    )
                }
            }
        }

        //Completed Tasks
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Completed tasks, ¡Keep going!")
        LazyColumn(
            modifier= Modifier
            .padding(16.dp)
        ){
            items(sTasks){ item ->
                if(item.isCompleted){
                    ShoppingListItem(item,
                        {
                            println(item.id)
                            sTasks.forEach{println("$it")}
                            sTasks[item.id-1] = sTasks[item.id-1].copy(
                                isCompleted = true
                            )
                        },
                        {
                            //Remove task from list lambda function
                            sTasks.remove(item)
                        }
                    )
                }
            }
        }
    }

    if(showDialog){
        AlertDialog(onDismissRequest = { showDialog=false },
            confirmButton = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = {
                        if(itemName.isNotBlank()){
                            val newItem = ShoppingItem(
                                id= sTasks.size+1,
                                name = itemName,
                            )
                            sTasks.add(newItem)
                            showDialog = false
                            itemName = ""
                        }
                    }){
                        Text("Add")
                    }
                    Button(onClick = {showDialog = false}){
                        Text("Cancel")
                    }
                }

            },
            title = { Text("Add to do task")},
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        label = { Text("Task name") },
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
    }
}



@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit,
){

    var deleteDialog by remember { mutableStateOf(false) }

    Box() { //330
        Row() {
            ElevatedCard(
                shape = RoundedCornerShape(15.dp, 0.dp, 0.dp, 15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (item.isCompleted) Color(0xFF2BEE92) else Color(0xFFF08080)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .height(70.dp)
                    .width(220.dp)
                    .padding(0.dp, 0.dp, 0.dp, 12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxWidth()
                    )
                }
            }

            //Delete Card
            ElevatedCard(
                shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
                modifier = Modifier
                    .height(70.dp)
                    .width(55.dp)
                    .padding(0.dp, 0.dp, 0.dp, 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (item.isCompleted) Color(0xFF0CD877) else Color(0xFFF66D67)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "Description",
                        modifier = Modifier.clickable {
                            deleteDialog = true
                        }
                    )
                }
            }

            //Completed Card
            ElevatedCard(
                shape = RoundedCornerShape(0.dp, 20.dp, 20.dp, 0.dp),
                modifier = Modifier
                    .height(70.dp)
                    .width(55.dp)
                    .padding(0.dp, 0.dp, 0.dp, 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (item.isCompleted) Color(0xFF0CD877) else Color(0xFFF66D67)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Rounded.Done,
                        contentDescription = "Description",
                        modifier = Modifier.clickable {

                            onCompleteClick()
                        }
                    )
                }
            }
        }

        if(deleteDialog){
            AlertDialog(
                title = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(text = "¡Warning!")
                    }},
                text = {
                    Text(text = "Are you sure you want to delete the task?")
                },
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { deleteDialog = false },
                confirmButton = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(24.dp, 28.dp)
                                .width(160.dp)
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(Color(0x90FF0505)),
                            shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 0.dp),
                            onClick = {
                                deleteDialog = false
                                onDeleteClick()
                            }
                        ) {
                            Text(text = "Delete")
                        }

                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .offset(-24.dp, 28.dp)
                                .width(160.dp)
                                .height(60.dp),
                            colors = ButtonDefaults.buttonColors(Color(0x90328BFC)),
                            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 25.dp),
                            onClick = {deleteDialog = false}
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            )
        }
    }
}








