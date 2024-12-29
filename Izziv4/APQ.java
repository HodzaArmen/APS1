public class APQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] polje;
    private int size;
    public long steviloPremikov = 0;
    public long steviloPrimerjav = 0;

    @SuppressWarnings("unchecked")
    public APQ() {
        polje = (T[]) new Comparable[64];
        size = 0;
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
    public T front() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        int maxIndex = maxIndex();
        return polje[maxIndex];
    }

    @Override
    public void enqueue(T x) {
        if (size == polje.length)
            resize();
        polje[size++] = x;
        steviloPremikov++;
    }

    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        int maxIndex = maxIndex();
        T max = polje[maxIndex];
        polje[maxIndex] = polje[--size];
        steviloPremikov++;
        return max;
    }

    private int maxIndex() {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            steviloPrimerjav++;
            if (polje[i].compareTo(polje[maxIndex]) > 0)
                maxIndex = i;
        }
        return maxIndex;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] novoPolje = (T[]) new Comparable[polje.length * 2];
        System.arraycopy(polje, 0, novoPolje, 0, polje.length);
        polje = novoPolje;
    }

    public void resetiraj() {
        steviloPremikov = 0;
        steviloPrimerjav = 0;
    }
}