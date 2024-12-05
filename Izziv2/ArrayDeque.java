class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front = 0;
    private int back = 0;
    private int size = 0;

    public ArrayDeque() {
        a = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (size > 0) {
            sb.append(a[front].toString());
        }
        ;
        for (int i = 1; i < size(); i++) {
            sb.append(", " + a[next(front + i)].toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return size;
    }

    private int next(int i) {
        return (i + 1) % DEFAULT_CAPACITY;
    }

    private int prev(int i) {
        return (DEFAULT_CAPACITY + i - 1) % DEFAULT_CAPACITY;
    }

    // Sklad
    @Override
    public T top() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }

    @Override
    public void push(T x) throws CollectionException {
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);
        a[back] = x;
        back = next(back);
        size++;
    }

    @Override
    public T pop() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T o = a[back];
        a[back] = null;
        size--;
        return o;
    }

    // Vrsta z dvema koncema
    @Override
    public T front() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[front];
    }

    @Override
    public T back() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        return a[prev(back)];
    }

    @Override
    public void enqueue(T x) throws CollectionException {
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);
        a[back] = x;
        back = next(back);
        size++;
    }

    @Override
    public void enqueueFront(T x) throws CollectionException {
        if (isFull())
            throw new CollectionException(ERR_MSG_FULL);
        front = prev(front);
        a[front] = x;
        size++;
    }

    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T o = a[front];
        a[front] = null;
        front = next(front);
        size--;
        return o;
    }

    @Override
    public T dequeueBack() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        back = prev(back);
        T o = a[back];
        a[back] = null;
        size--;
        return o;
    }

    // Zaporedje
    private int index(int i) {
        return (front + i) % DEFAULT_CAPACITY;
    }

    public T get(int i) throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        if (i < 0 || i >= size)
            throw new CollectionException(ERR_MSG_INDEX);
        return a[index(i)];
    }

    @Override
    public void add(T x) throws CollectionException {
        enqueue(x);
    }
}
