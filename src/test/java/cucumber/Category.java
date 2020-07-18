package cucumber;

public enum Category {

    Транспорт("//option[@data-marker='option(1)']"),
    Автомобили("//option[@data-marker='option(9)']"),
    Квартиры("//option[@data-marker='option(24)']"),
    Вакансии("//option[@data-marker='option(111)']"),
    Ноутбуки("//option[@data-marker='option(98)']"),
    Оргтехника("//option[@data-marker='option(99)']"),
    Велосипеды("//option[@data-marker='option(34)']");
    public String value;

    public String getValue() {
        return value;
    }

    Category(String value) {
        this.value = value;
    }

}
