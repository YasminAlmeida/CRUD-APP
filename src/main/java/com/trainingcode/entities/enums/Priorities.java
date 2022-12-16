package com.trainingcode.entities.enums;

public enum Priorities {
    URGENT(1),
    HIGH(2),
    NORMAL(3),
    LOW(4);

    private int codes;

    private Priorities(int codes) {
        this.codes = codes;
    }

    public int getCodes() {
        return codes;
    }

    public static com.trainingcode.entities.enums.Priorities valueOf(int codes)
            throws IllegalAccessException {
        for (com.trainingcode.entities.enums.Priorities value : com.trainingcode.entities.enums.Priorities.values()) {
            if (value.getCodes() == codes) {
                return value;
            }
        }
        throw new IllegalAccessException("Invalid Priorities Code");
    }

}