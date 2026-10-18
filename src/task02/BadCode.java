package task02;

public class BadCode {

    private final String value;

    public BadCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
