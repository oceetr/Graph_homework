package graph;

public class VertexNotFoundException extends RuntimeException {
    public VertexNotFoundException(Object v) {
        super("Vertex " + v + " not found");
    }
}
