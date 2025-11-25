package app;

import java.util.Scanner;
import graph.Graph;
import graph.VertexNotFoundException;
import collections.MyList;

public class GraphConsole {
    private Graph<String> graph;
    private Scanner scanner;

    public GraphConsole() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Создание графа:");
        boolean directed = askDirected();
        graph = new Graph<>(directed);

        while (true) {
            printMenu();
            int option = readInt("Ваш выбор: ");
            if (option == 0) {
                System.out.println("Выход");
                break;
            }
            switch (option) {
                case 1:
                    String v = readNonEmptyLine("Введите вершину: ");
                    graph.addVertex(v);
                    System.out.println("Вершина добавлена: " + v);
                    break;
                case 2:
                    String from = readNonEmptyLine("Введите начальную вершину: ");
                    String to = readNonEmptyLine("Введите конечную вершину: ");
                    int weight = readInt("Введите вес ребра: ");
                    try {
                        graph.addEdge(from, to, weight);
                        System.out.println("Ребро добавлено: " + from + " -> " + to + " (" + weight + ")");
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    String rv = readNonEmptyLine("Введите вершину для удаления: ");
                    try {
                        graph.removeVertex(rv);
                        System.out.println("Вершина удалена: " + rv);
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    String rf = readNonEmptyLine("Введите начальную вершину ребра для удаления: ");
                    String rt = readNonEmptyLine("Введите конечную вершину ребра для удаления: ");
                    try {
                        graph.removeEdge(rf, rt);
                        System.out.println("Ребро удалено: " + rf + " -> " + rt);
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    String av = readNonEmptyLine("Введите вершину для получения смежных: ");
                    try {
                        MyList<String> adj = graph.getAdjacent(av);
                        System.out.println("Смежные вершины: " + adj);
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    String sd = readNonEmptyLine("Введите стартовую вершину для DFS: ");
                    try {
                        System.out.print("DFS: ");
                        graph.dfs(sd);
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    String sb = readNonEmptyLine("Введите стартовую вершину для BFS: ");
                    try {
                        System.out.print("BFS: ");
                        graph.bfs(sb);
                    } catch (VertexNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Неверная опция");
            }
        }
    }

    private boolean askDirected() {
        while (true) {
            System.out.print("Введите 1 для ориентированного, 2 для неориентированного: ");
            String line = scanner.nextLine().trim();
            if (line.equals("1")) return true;
            if (line.equals("2")) return false;
            System.out.println("Неверный ввод. Введите 1 или 2.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Ожидается число. Повторите ввод.");
            }
        }
    }

    private String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null) {
                line = line.trim();
                if (!line.isEmpty()) return line;
            }
            System.out.println("Пустая строка - введите корректное значение.");
        }
    }

    private void printMenu() {
        System.out.println("\nВыберите операцию:");
        System.out.println("1 - Добавить вершину");
        System.out.println("2 - Добавить ребро");
        System.out.println("3 - Удалить вершину");
        System.out.println("4 - Удалить ребро");
        System.out.println("5 - Показать смежные вершины");
        System.out.println("6 - DFS");
        System.out.println("7 - BFS");
        System.out.println("0 - Выход");
    }
}

