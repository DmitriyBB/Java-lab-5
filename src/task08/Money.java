package task08;

public record Money(long cents) implements Comparable<Money> {

    @Override
    public int compareTo(Money other) {
        return Long.compare(cents, other.cents);
    }
}
