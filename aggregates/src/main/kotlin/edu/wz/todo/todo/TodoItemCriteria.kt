package edu.wz.todo.todo

import java.util.function.Predicate

class TodoItemCriteria private constructor(private val criteria: List<Predicate<TodoItem>>) {

    fun satisfies(item: TodoItem): Boolean {
        return criteria.all{ criterion -> criterion.test(item) }
    }
    class Builder(private val criteria: MutableList<Predicate<TodoItem>> = arrayListOf()) {

        fun withCriterion(criterion: Predicate<TodoItem>): Builder {
            criteria.add(criterion)
            return this
        }

        fun build(): TodoItemCriteria {
            return TodoItemCriteria(criteria)
        }
    }
}