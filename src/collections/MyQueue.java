package collections;

public class MyQueue<E> {
    private MyList<E> list;

    public MyQueue() {
        list = new MyList<>();
    }

    public void enqueue(E e) {
        list.add(e);
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}

