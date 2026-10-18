package task15;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public final class Graph<T> {

    public record Schedule<T>(List<T> order, List<T> cycle) {

        public boolean hasCycle() {
            return !cycle.isEmpty();
        }
    }

    private final Map<T, Set<T>> dependents = new LinkedHashMap<>();

    public void addVertex(T vertex) {
        Objects.requireNonNull(vertex, "vertex");
        dependents.computeIfAbsent(vertex, v -> new LinkedHashSet<>());
    }

    public void addDependency(T prerequisite, T course) {
        addVertex(prerequisite);
        addVertex(course);
        dependents.get(prerequisite).add(course);
    }

    public Set<T> dependentsOf(T vertex) {
        Set<T> result = dependents.get(vertex);
        if (result == null) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }
        return Collections.unmodifiableSet(result);
    }

    public Schedule<T> schedule() {
        Map<T, Integer> inDegree = new HashMap<>();
        for (T vertex : dependents.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (Set<T> targets : dependents.values()) {
            for (T target : targets) {
                inDegree.merge(target, 1, Integer::sum);
            }
        }

        Queue<T> ready = new ArrayDeque<>();
        for (T vertex : dependents.keySet()) {
            if (inDegree.get(vertex) == 0) {
                ready.offer(vertex);
            }
        }

        List<T> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            T vertex = ready.poll();
            order.add(vertex);
            for (T next : dependents.get(vertex)) {
                if (inDegree.merge(next, -1, Integer::sum) == 0) {
                    ready.offer(next);
                }
            }
        }

        if (order.size() == dependents.size()) {
            return new Schedule<>(order, List.of());
        }
        return new Schedule<>(List.of(), findCycle());
    }

    private List<T> findCycle() {
        Set<T> finished = new LinkedHashSet<>();
        List<T> path = new ArrayList<>();
        for (T start : dependents.keySet()) {
            List<T> cycle = visit(start, finished, path);
            if (!cycle.isEmpty()) {
                return cycle;
            }
        }
        return List.of();
    }

    private List<T> visit(T vertex, Set<T> finished, List<T> path) {
        int onPath = path.indexOf(vertex);
        if (onPath >= 0) {
            List<T> cycle = new ArrayList<>(path.subList(onPath, path.size()));
            cycle.add(vertex);
            return cycle;
        }
        if (finished.contains(vertex)) {
            return List.of();
        }
        path.add(vertex);
        for (T next : dependents.get(vertex)) {
            List<T> cycle = visit(next, finished, path);
            if (!cycle.isEmpty()) {
                return cycle;
            }
        }
        path.remove(path.size() - 1);
        finished.add(vertex);
        return List.of();
    }
}
