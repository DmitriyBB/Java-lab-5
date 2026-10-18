package task15;

public class Main {

    static void print(String title, Graph<String> graph) {
        Graph.Schedule<String> schedule = graph.schedule();
        if (schedule.hasCycle()) {
            System.out.println(title + ": cycle " + String.join(" -> ", schedule.cycle()));
        } else {
            System.out.println(title + ": " + String.join(" -> ", schedule.order()));
        }
    }

    public static void main(String[] args) {
        Graph<String> ok = new Graph<>();
        ok.addDependency("Java basics", "OOP");
        ok.addDependency("Java basics", "Collections");
        ok.addDependency("OOP", "Generics");
        ok.addDependency("Collections", "Generics");
        ok.addDependency("Generics", "Streams");
        ok.addVertex("Git");
        print("valid ", ok);
        print("again ", ok);

        Graph<String> broken = new Graph<>();
        broken.addDependency("A", "B");
        broken.addDependency("B", "C");
        broken.addDependency("C", "D");
        broken.addDependency("D", "B");
        broken.addDependency("A", "E");
        print("cycle ", broken);

        Graph<String> selfLoop = new Graph<>();
        selfLoop.addDependency("X", "X");
        print("self  ", selfLoop);

        print("empty ", new Graph<>());

        System.out.println("dependentsOf(OOP): " + ok.dependentsOf("OOP"));
        try {
            ok.dependentsOf("Rust");
        } catch (IllegalArgumentException e) {
            System.out.println("dependentsOf(Rust): " + e.getMessage());
        }
    }
}
