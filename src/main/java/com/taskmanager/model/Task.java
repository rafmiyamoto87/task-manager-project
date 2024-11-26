package com.taskmanager.model;

import java.time.LocalDate;

// Represents an individual task with attributes like ID, description, priority, deadline, and category
public class Task {
    private final int id;                 // Unique identifier for the task
    private final String description;     // Short description of the task
    private final int priority;           // Priority level (higher number = higher priority)
    private final LocalDate deadline;     // Deadline for completing the task
    private final String category;        // Category of the task (e.g., "Work", "Personal")

    // Constructor to initialize a Task object with all attributes
    public Task(int id, String description, int priority, LocalDate deadline, String category) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.category = category;
    }

    // Getter for task ID
    public int getId() {
        return id;
    }

    // Getter for task description
    public String getDescription() {
        return description;
    }

    // Getter for task priority
    public int getPriority() {
        return priority;
    }

    // Getter for task deadline
    public LocalDate getDeadline() {
        return deadline;
    }

    // Getter for task category
    public String getCategory() {
        return category;
    }

    // Overrides the default toString() method to provide a readable format for a Task object
    @Override
    public String toString() {
        return String.format("Task[ID: %d, Desc: %s, Priority: %d, Deadline: %s, Category: %s]",
                id, description, priority, deadline, category);
    }
}

