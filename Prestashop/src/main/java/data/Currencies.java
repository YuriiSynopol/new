package data;

public enum Currencies {
    EURO("Euro"),
    UAH("Ukrainian Hryvnia"),
    USD("US Dollar");
    //
    private String name;

    Currencies(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
