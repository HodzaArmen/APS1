public class AHPQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private T[] kopica;
    private int size;
    public long steviloPremikov = 0;
    public long steviloPrimerjav = 0;

    @SuppressWarnings("unchecked")
    public AHPQ() {
        kopica = (T[]) new Comparable[64];
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
        return kopica[0];
    }

    @Override
    public void enqueue(T x) {
        if (size == kopica.length)
            resize();
        kopica[size] = x;
        steviloPremikov++;
        siftUp(size++);
    }

    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T max = kopica[0];
        kopica[0] = kopica[--size];
        steviloPremikov++;
        siftDown(0);
        return max;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int stars = (index - 1) / 2;
            steviloPrimerjav++;
            if (kopica[index].compareTo(kopica[stars]) <= 0)
                break;
            swap(index, stars);
            steviloPremikov += 3;
            index = stars;
        }
    }

    private void siftDown(int index) {
        while (true) {
            int levi = 2 * index + 1;
            int desni = 2 * index + 2;
            int max = index;

            // gre v vecjega sina
            steviloPrimerjav++;
            if (levi < size && kopica[levi].compareTo(kopica[max]) > 0 && kopica[levi].compareTo(kopica[desni]) >= 0)
                max = levi;
            steviloPrimerjav++;
            if (desni < size && kopica[desni].compareTo(kopica[max]) > 0)
                max = desni;
            if (max == index)
                break;
            swap(index, max);
            steviloPremikov += 3;
            index = max;
        }
    }

    private void swap(int i, int j) {
        T temp = kopica[i];
        kopica[i] = kopica[j];
        kopica[j] = temp;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] novaKopica = (T[]) new Comparable[kopica.length * 2];
        System.arraycopy(kopica, 0, novaKopica, 0, kopica.length);
        kopica = novaKopica;
    }

    public void resetiraj() {
        steviloPremikov = 0;
        steviloPrimerjav = 0;
    }
}