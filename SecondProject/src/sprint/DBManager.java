package sprint;

import java.util.ArrayList;

public class DBManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static long count = 0;

    public static void addTask(Task task) {
        task.setId(1 + count++); // id's start from 1, for the sake of being the same as in task
        tasks.add(task);
    }

    public static void editTask(Task task) {
        Task dbTask = findTaskById(task.getId());
        if (dbTask != null) {
            dbTask.setName(task.getName());
            dbTask.setDescription(task.getDescription());
            dbTask.setDueDate(task.getDueDate());
            dbTask.setDone(task.isDone());
        }
    }

    public static Task findTaskById(Long id) {
        if (id != null) {
            for (Task task : tasks) {
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        }

        return null;
    }
    public static ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
