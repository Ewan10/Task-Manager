package com.ewan.TaskManager;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskManagerTest {

    TaskManager taskManager;

    @BeforeEach
    public void setup() {
        taskManager = new TaskManager();
    }

    @Test
    public void compareLoadAndSave() {
        taskManager = new TaskManager();
        taskManager.load("./resources/Diary1.json");
        taskManager.save("./resources/Diary2.json");
        TaskManager taskManager2 = new TaskManager();
        taskManager2.load("./resources/Diary2.json");

        Assertions.assertEquals(taskManager2.getTasks().size(), taskManager.getTasks().size());

        for (int i = 0; i < taskManager.getTasks().size() - 1; i++) {
            Assertions.assertEquals(taskManager2.getTasks().get(i), taskManager.getTasks().get(i));
        }
    }

    @Test
    public void shouldFailForBadlyFormattedFileOrDuplicateIds() {
        taskManager = new TaskManager();

        taskManager.load("./resources/Diary1.json");
        // Condition if badly formatted.
        Assertions.assertThrows(InvalidFormatting.class, () -> {
            // The condition when the data is badly formatted.
        });

        // else if -The case of duplicate ids.
        for (int w = 0; w < taskManager.getTasks().size() - 1; w++) {
            for (int u = 0; u < taskManager.getTasks().size() - 1; u++) {
                if (w != u) {
                    Assertions.assertNotEquals(taskManager.getTasks().get(u),
                            taskManager.getTasks().get(w));
                }
            }

        }
    }

    @Test
    public void shouldSaveStatus() {
        taskManager = new TaskManager();
        taskManager.load("./resources/Diary1.json");
        taskManager.updateTaskStatus(1, "DONE");
        Assertions.assertEquals("DONE", taskManager.getTasks().get(1).getStatus());
    }

    @Test
    @DisplayName("It should throw a FileNotFoundException when the file" +
            " to be loaded does not exist in the specified path.")
    public void shouldThrowFileNotFoundException() {
        taskManager = new TaskManager();
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            taskManager.load("./resources/Diary0.json");
        });
    }

}
