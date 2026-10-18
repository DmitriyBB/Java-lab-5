package task08;

public class Animal implements Comparable<Animal> {

    private final int weight;

    public Animal(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Animal other) {
        return Integer.compare(weight, other.weight);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + weight + ")";
    }
}
