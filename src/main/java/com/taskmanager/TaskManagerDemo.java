package com.taskmanager;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskManager;
import java.time.LocalDate;
import java.util.Scanner;

// Main class to interact with the TaskManager system
public class TaskManagerDemo {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager(); // Create an instance of TaskManager
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) { // Menu-driven program
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks by Deadline");
            System.out.println("3. View Tasks by Priority");
            System.out.println("4. View Tasks by Category");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt(); // Read user input
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add a task
                    System.out.print("Enter Task ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Priority (1-10): ");
                    int priority = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Deadline (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();
                    Task task = new Task(id, description, priority, deadline, category);
                    manager.addTask(task);
                    break;
                case 2:
                    manager.viewTasksByDeadline();
                    break;
                case 3:
                    manager.viewTasksByPriority();
                    break;
                case 4:
                    System.out.print("Enter Category: ");
                    category = scanner.nextLine();
                    manager.viewTasksByCategory(category);
                    break;
                case 5:
                    System.out.print("Enter Task ID to mark as completed: ");
                    id = scanner.nextInt();
                    manager.markTaskAsCompleted(id);
                    break;
                case 6:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

