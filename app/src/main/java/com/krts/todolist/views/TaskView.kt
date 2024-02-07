package com.krts.todolist.views

import android.support.v4.os.IResultReceiver2.Default
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.krts.todolist.constraintsDecoupled.decoupledConstraints
import com.krts.todolist.ui.theme.GreenAddTaskButton

@Preview(showSystemUi = true)
@Composable
fun TaskView(){
    ConstraintLayout(constraintSet = decoupledConstraints() , Modifier.fillMaxSize()){

        LazyColumn(
            Modifier.layoutId("lcTaskList")
        ) {
        }

        Button(
            onClick = {},
            Modifier
                .layoutId("btnAddTask")
                .height(60.dp),
            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp),
            colors = ButtonDefaults.buttonColors(GreenAddTaskButton)
        ) {
            Icon(Icons.Rounded.Add, "qwe")
        }
    }
}