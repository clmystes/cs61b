public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        public T value;
        public Node prev;
        public Node next;

        public Node(T value, Node prev, Node next) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    public LinkedListDeque() {
       size = 0;
       sentinel = new Node(null, null, null);
       sentinel.next = sentinel;
       sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node oldFirst = sentinel.next;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        oldFirst.prev = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node oldLast = sentinel.prev;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        oldLast.next = sentinel.prev;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node cur = sentinel.next;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(cur.value).append(" ");
            cur = cur.next;
        }
        System.out.println(str);
    }

    @Override
    public T removeFirst() {
        Node prevFirst = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return prevFirst.value;
    }

    @Override
    public T removeLast() {
        Node prevLast = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return prevLast.value;
    }

    @Override
    public T get(int index) {
        Node item = sentinel;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item.value;
    }

    private T getRecursive(int index, Node node) {
        if(index == 0) return node.value;
        return getRecursive(--index, node.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    };
}
