package edu.wz.todo.todo

import edu.wz.todo.shared.Identifier
import java.time.LocalDateTime

class TodoListAggregate (private val root: TodoList){

    class Builder {

    }

    fun addTodoItem(item: TodoItem) {
        root.addTodoItem(item)
    }

    fun deleteTodoItem(id : Identifier) {
        root.removeTodoItem(id)
    }

    fun changeName(id: Identifier, newName: String) {

    }

    fun moveDeadline(id: Identifier, deadline: LocalDateTime) {

    }

}