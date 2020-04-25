public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size = 0;
    private final static int INITIAL_CAPACITY = 8;
    private int nextFirstIdx = 0;
    private int nextLastIdx = 1;

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addFirst(T item) {
        if(isFull()) {
           resize(size * 2);
        }
        array[nextFirstIdx] = item;
        nextFirstIdx = minusOne(nextFirstIdx);
        size++;
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, array.length);
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        int first = addOne(nextFirstIdx);
        for (int i = 0; i < size; i++) {
            newArr[i] = array[first];
        }
        array = newArr;
        nextFirstIdx = array.length - 1;
        nextLastIdx = size;
    }

    private int addOne(int index) {
        return Math.floorMod(index + 1, array.length);
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public void addLast(T item) {
        if(isFull()) {
            resize(size * 2);
        }
        array[nextLastIdx] = item;
        nextLastIdx = addOne(nextLastIdx);
        size++;
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
        StringBuilder str = new StringBuilder();
        int first = addOne(nextFirstIdx);
        for (int i = 0; i < size; i++) {
            str.append(array[first]).append(" ");
            first = addOne(first);
        }
        System.out.println(str);
    }

    @Override
    public T removeFirst() {
        T removed = array[addOne(nextFirstIdx)];
        nextFirstIdx = addOne(nextFirstIdx);
        size--;
        shrink();
        return removed;
    }

    private void shrink() {
       if(size > 16 && (array.length / 4 > size)) {
           resize(array.length / 2);
       }
    }

    @Override
    public T removeLast() {
        T removed = array[minusOne(nextLastIdx)];
        nextLastIdx = minusOne(nextLastIdx);
        size--;
        shrink();
        return removed;
    }

    @Override
    public T get(int index) {
        return array[index];
    }
}
