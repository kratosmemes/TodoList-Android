package com.krts.todolist.constraintsDecoupled

import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet{
        val lcTaskList = createRefFor("lcTaskList")
        val btnAddTask = createRefFor("btnAddTask")

        constrain(lcTaskList){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(btnAddTask.top)
            height = Dimension.matchParent
            width = Dimension.matchParent
        }

        constrain(btnAddTask){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.matchParent
        }
    }
}