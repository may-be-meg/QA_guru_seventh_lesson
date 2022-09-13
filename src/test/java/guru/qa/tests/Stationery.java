package guru.qa.tests;

public enum Stationery {

    Paper("Бумажные изделия"),

    Haberdashery("Галантерея");

    public final String title;

    Stationery(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }
}
