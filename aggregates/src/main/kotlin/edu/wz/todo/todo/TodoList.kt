package edu.wz.todo.todo

import edu.wz.todo.shared.Identifier
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class TodoList private constructor(private val items: MutableList<TodoItem> = arrayListOf()) {

    fun addTodoItem(item: TodoItem) {
        this.items.add(item)
    }

    fun removeTodoItem(id: Identifier) {
        val item = findByCriteria(TodoItemCriteria.Builder().withCriterion { e -> e.id == id }.build())
        item ?: throw IllegalArgumentException("Item with id $id was not found")
        items.removeIf { e -> e === item }
    }

    fun changeName(id: Identifier, newName: String) {
        val item = findByCriteria(TodoItemCriteria.Builder().withCriterion { e -> e.id == id }.build())
        item ?: throw IllegalArgumentException("Item with id $id was not found")
        item.changeName(newName)
    }

    fun moveDeadline(id: Identifier, deadline: LocalDateTime) {
        val item = findByCriteria(TodoItemCriteria.Builder().withCriterion { e -> e.id == id }.build())
        item ?: throw IllegalArgumentException("Item with id $id was not found")
        item.moveDeadline(deadline)
    }

    private fun findByCriteria(criteria: TodoItemCriteria): TodoItem? {
        return items .find { i -> criteria.satisfies(i) }
    }

    public class Builder (private val items: MutableList<TodoItem> = arrayListOf()) {

        fun withItem(item: TodoItem): Builder {
            items.add(item)
            return this
        }

        fun build(): TodoList {
            return TodoList(items)
        }
    }
}