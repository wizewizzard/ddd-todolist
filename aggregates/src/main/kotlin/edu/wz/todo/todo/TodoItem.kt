package edu.wz.todo.todo

import edu.wz.todo.shared.Identifier
import edu.wz.todo.shared.Priority
import edu.wz.todo.shared.Status
import java.time.LocalDateTime

class TodoItem (val id: Identifier,
                name: String,
                description: String,
                priority: Priority,
                status: Status,
                creationDate: LocalDateTime,
                deadline: LocalDateTime?,
            ) {
    var name: String = name
        private set
    var description: String = description
        private set
    var priority: Priority = priority
        private set
    var status: Status = status
        private set
    var creationDate: LocalDateTime = creationDate
        private set
    var deadline: LocalDateTime? = deadline
        private set

    fun changeName(name: String) {
        this.name = name
    }

    fun changeStatus(status: Status) {
        this.status = status
    }

    fun moveDeadline(deadline: LocalDateTime) {
        if (deadline.isBefore(creationDate)) {
            throw IllegalArgumentException()
        }
        this.deadline = deadline
    }

    class Builder public constructor(
        private val id: Identifier,
        var name: String,
        var description: String = "",
        var status: Status = Status.NEW,
        var priority: Priority = Priority.LOW,
        var creationDate: LocalDateTime = LocalDateTime.now(),
        var deadline: LocalDateTime? = null,
    ) {
        fun withDescription(description: String): Builder {
            this.description = description
            return this
        }
        fun withPriority(priority: Priority): Builder {
            this.priority = priority
            return this
        }
        fun withCreationDate(createdAt: LocalDateTime): Builder {
            this.creationDate = createdAt
            return this
        }
        fun withDeadline(deadline: LocalDateTime): Builder {
            if (deadline.isBefore(creationDate)) {
                throw IllegalArgumentException()
            }
            this.deadline = deadline
            return this
        }
        fun withStatus(status: Status): Builder {
            this.status = status
            return this
        }
        fun build(): TodoItem = TodoItem(id, name, description, priority, status, creationDate, deadline)
    }
}