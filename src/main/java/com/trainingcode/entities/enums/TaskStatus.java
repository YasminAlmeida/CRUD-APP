package com.trainingcode.entities.enums;

public enum TaskStatus {
    TASK_OPEN(1),
    TASK_INPROGRESS(2),
    TASK_ON_HOLD(3),
    TASK_CLOSED(4);

    private int code;

    private TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TaskStatus valueOf(int code)
            throws IllegalAccessException {
        for (TaskStatus value : TaskStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalAccessException("Invalid Task Status Code");
    }

}
