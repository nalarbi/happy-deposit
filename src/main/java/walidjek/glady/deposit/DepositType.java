package walidjek.glady.deposit;

public enum DepositType {
    GIFT("gift"),
    MEAL("meal");

    private final String value;

    DepositType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
