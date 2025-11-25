package graph;

import collections.MyList;
import collections.MyMap;
import collections.MyQueue;

public class Graph<V> {
    private MyMap<V, MyList<Entry<V>>> adjacencyList;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyList = new MyMap<>();
    }

    public void addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new MyList<Entry<V>>());
        }
    }

    public void addEdge(V from, V to, int weight) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new VertexNotFoundException(from + " or " + to);
        }
        adjacencyList.get(from).add(new Entry<>(to, weight));
        if (!directed) {
            adjacencyList.get(to).add(new Entry<>(from, weight));
        }
    }

    public void removeVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            throw new VertexNotFoundException(v);
        }
        // Удаляем вершину из карты
        adjacencyList.remove(v);
        // Удаляем все ребра, ведущие в вершину v
        MyList<V> keys = adjacencyList.keys();
        for (int i = 0; i < keys.size(); i++) {
            V key = keys.get(i);
            MyList<Entry<V>> list = adjacencyList.get(key);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).vertex.equals(v)) {
                    list.remove(j);
                    j--; // скорректировать индекс после удаления
                }
            }
        }
    }

    public void removeEdge(V from, V to) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new VertexNotFoundException(from + " or " + to);
        }
        MyList<Entry<V>> fromList = adjacencyList.get(from);
        for (int i = 0; i < fromList.size(); i++) {
            if (fromList.get(i).vertex.equals(to)) {
                fromList.remove(i);
                break;
            }
        }
        if (!directed) {
            MyList<Entry<V>> toList = adjacencyList.get(to);
            for (int i = 0; i < toList.size(); i++) {
                if (toList.get(i).vertex.equals(from)) {
                    toList.remove(i);
                    break;
                }
            }
        }
    }

    public MyList<V> getAdjacent(V v) {
        if (!adjacencyList.containsKey(v)) {
            throw new VertexNotFoundException(v);
        }
        MyList<V> neighbors = new MyList<>();
        MyList<Entry<V>> list = adjacencyList.get(v);
        for (int i = 0; i < list.size(); i++) {
            neighbors.add(list.get(i).vertex);
        }
        return neighbors;
    }

    public void dfs(V start) {
        if (!adjacencyList.containsKey(start)) {
            throw new VertexNotFoundException(start);
        }
        MyList<V> visited = new MyList<>();
        dfsHelper(start, visited);
        System.out.println(); // перевод строки после обхода
    }
    private void dfsHelper(V current, MyList<V> visited) {
        visited.add(current);
        System.out.print(current + " ");
        MyList<Entry<V>> list = adjacencyList.get(current);
        for (int i = 0; i < list.size(); i++) {
            V neighbor = list.get(i).vertex;
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void bfs(V start) {
        if (!adjacencyList.containsKey(start)) {
            throw new VertexNotFoundException(start);
        }
        MyList<V> visited = new MyList<>();
        MyQueue<V> queue = new MyQueue<>();
        visited.add(start);
        queue.enqueue(start);
        while (!queue.isEmpty()) {
            V current = queue.dequeue();
            System.out.print(current + " ");
            MyList<Entry<V>> list = adjacencyList.get(current);
            for (int i = 0; i < list.size(); i++) {
                V neighbor = list.get(i).vertex;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }
        System.out.println();
    }
}
