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
        taskManager.load(".\\resources\\Diary1.json");
        taskManager.save(".\\resources\\Diary2.json");
        /*
         * Must write what objects we are going
         * to compare. What are the exact objects in assertEquals()?
         * Assertions.assertEquals(expected, actual);
         */
    }

    /**
     * Test the failed outcome that the loaded file is badly formatted or
     * there are duplicate ids (requires refactoring the TaskManager).
     */

    @Test
    public void shouldSaveStatus() {
        taskManager = new TaskManager();
        taskManager.load(".\\resources\\Diary1.json");
        taskManager.updateTaskStatus(1, "DONE");
        Assertions.assertEquals("DONE", taskManager.getTasks().get(1).getStatus());
    }

    @Test
    @DisplayName("It should throw a FileNotFoundException when the file" +
            " to be loaded does not exist in the specified path.")
    public void shouldThrowFileNotFoundException() {
        taskManager = new TaskManager();
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            taskManager.load(".\\resources\\Diary0.json");
        });
    }

}
