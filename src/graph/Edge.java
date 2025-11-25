package graph;

public class Edge<V> {
    private V from;
    private V to;
    private int weight;

    public Edge(V from, V to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public V getFrom() { return from; }
    public V getTo()   { return to; }
    public int getWeight() { return weight; }

    @Override
    public String toString() {
        return from + " -> " + to + " (weight " + weight + ")";
    }
}
