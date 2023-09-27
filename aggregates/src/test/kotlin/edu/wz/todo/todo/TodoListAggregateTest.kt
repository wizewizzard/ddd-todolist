package edu.wz.todo.todo

import edu.wz.todo.shared.Identifier
import edu.wz.todo.shared.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.UUID

fun generateId(): Identifier = UUID.randomUUID().toString()

internal class TodoListAggregateTest {
    @Test
    internal fun shouldUpdateTodoItemStatus() {
        val id: Identifier = generateId()
        val todo = TodoItem.Builder(id, "My task")
            .withStatus(Status.NEW)
            .build()

        todo.changeStatus(Status.COMPLETED)

        assertEquals(todo.status, Status.COMPLETED)
    }

    @Test
    internal fun shouldMoveDeadline() {
        val id: Identifier = generateId()
        val now = LocalDateTime.now()
        val todo = TodoItem.Builder(id, "My task")
            .withStatus(Status.NEW)
            .withCreationDate(now)
            .build()

        todo.moveDeadline(now.plusDays(2))

        assertEquals(todo.deadline, now.plusDays(2))
    }

    @Test
    internal fun shouldNotSetDeadline() {
        val id: Identifier = generateId()
        val now = LocalDateTime.now()
        val todo = TodoItem.Builder(id, "My task")
            .withStatus(Status.NEW)
            .withCreationDate(now)
            .build()

        assertThrows<IllegalArgumentException>{ -> todo.moveDeadline(now.minusDays(2))  }
    }
}