package graph;

public class Entry<V> {
    public V vertex;
    public int weight;

    public Entry(V vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return vertex.toString() + "(" + weight + ")";
    }
}

