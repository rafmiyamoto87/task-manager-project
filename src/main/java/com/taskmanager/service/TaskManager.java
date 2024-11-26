package com.taskmanager.service;

import com.taskmanager.model.Task;
import java.time.LocalDate;
import java.util.*;

// Manages tasks using advanced data structures for sorting, categorizing, and prioritizing
public class TaskManager {
    private final TreeMap<LocalDate, List<Task>> tasksByDeadline; // Stores tasks sorted by their deadlines
    private final PriorityQueue<Task> taskQueue;                 // Manages tasks sorted by priority
    private final HashMap<String, List<Task>> tasksByCategory;   // Groups tasks by category

    // Constructor initializes the data structures
    public TaskManager() {
        tasksByDeadline = new TreeMap<>();
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        tasksByCategory = new HashMap<>();
    }

    // Adds a new task to all data structures
    public void addTask(Task task) {
        // Add task to TreeMap, categorized by deadline
        tasksByDeadline.putIfAbsent(task.getDeadline(), new ArrayList<>());
        tasksByDeadline.get(task.getDeadline()).add(task);

        // Add task to PriorityQueue, ordered by priority
        taskQueue.offer(task);

        // Add task to HashMap under its category
        tasksByCategory.putIfAbsent(task.getCategory(), new ArrayList<>());
        tasksByCategory.get(task.getCategory()).add(task);

        System.out.println("Task added: " + task);
    }

    // Displays all tasks sorted by deadlines
    public void viewTasksByDeadline() {
        System.out.println("Tasks sorted by deadline:");
        for (Map.Entry<LocalDate, List<Task>> entry : tasksByDeadline.entrySet()) {
            System.out.println("Deadline: " + entry.getKey());
            for (Task task : entry.getValue()) {
                System.out.println("- " + task); // Display each task for the deadline
            }
        }
    }

    // Displays tasks sorted by priority
    public void viewTasksByPriority() {
        System.out.println("Tasks sorted by priority:");
        PriorityQueue<Task> tempQueue = new PriorityQueue<>(taskQueue); // Copy queue to preserve original
        while (!tempQueue.isEmpty()) {
            System.out.println(tempQueue.poll()); // Display tasks in priority order
        }
    }

    // Displays tasks belonging to a specific category
    public void viewTasksByCategory(String category) {
        if (!tasksByCategory.containsKey(category)) {
            System.out.println("No tasks found in category: " + category);
            return;
        }
        System.out.println("Tasks in category: " + category);
        for (Task task : tasksByCategory.get(category)) {
            System.out.println("- " + task);
        }
    }

    // Searches for a task by its ID
    public Task searchTaskById(int id) {
        for (List<Task> tasks : tasksByDeadline.values()) {
            for (Task task : tasks) {
                if (task.getId() == id) { // Match found
                    return task;
                }
            }
        }
        return null; // Return null if no task matches the ID
    }

    // Marks a task as completed and removes it from all data structures
    public void markTaskAsCompleted(int id) {
        Task task = searchTaskById(id); // Find the task by ID
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        // Remove task from TreeMap
        tasksByDeadline.get(task.getDeadline()).remove(task);
        if (tasksByDeadline.get(task.getDeadline()).isEmpty()) {
            tasksByDeadline.remove(task.getDeadline());
        }

        // Remove task from PriorityQueue
        taskQueue.remove(task);

        // Remove task from HashMap
        tasksByCategory.get(task.getCategory()).remove(task);
        if (tasksByCategory.get(task.getCategory()).isEmpty()) {
            tasksByCategory.remove(task.getCategory());
        }

        System.out.println("Task marked as completed: " + task);
    }
}
