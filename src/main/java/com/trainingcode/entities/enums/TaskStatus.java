package com.trainingcode.entities.enums;

public enum TaskStatus {
    Open(1),
    InProgress(2),
    OnHold(3),
    Closed(4);

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
