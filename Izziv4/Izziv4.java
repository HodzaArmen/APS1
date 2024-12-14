import java.util.Random;

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";

    boolean isEmpty();

    int size();

    String toString();
}

interface Queue<T> extends Collection {
    T front() throws CollectionException;

    void enqueue(T x);

    T dequeue() throws CollectionException;
}

@SuppressWarnings("rawtypes")
interface PriorityQueue<T extends Comparable> extends Queue<T> {
}

class APQ<T extends Comparable<T>> implements PriorityQueue<T> {
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

class AHPQ<T extends Comparable<T>> implements PriorityQueue<T> {
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

class LHPQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private static class Node<T> {
        T value;
        Node<T> levi, desni, stars;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;
    public int steviloPremikov = 0;
    public int steviloPrimerjav = 0;

    public LHPQ() {
        root = null;
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
        return root.value;
    }

    @Override
    public void enqueue(T x) {
        Node<T> newNode = new Node<>(x);
        if (root == null) {
            root = newNode;
        } else {
            String path = Integer.toBinaryString(size + 1).substring(1); // Pot do vozlišča
            Node<T> current = root;
            for (int i = 0; i < path.length() - 1; i++) {
                current = (path.charAt(i) == '0') ? current.levi : current.desni;
            }
            if (path.charAt(path.length() - 1) == '0') {
                current.levi = newNode;
            } else {
                current.desni = newNode;
            }
            newNode.stars = current;
        }
        size++;
        steviloPremikov++;
        siftUp(newNode);
    }

    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T maxValue = root.value;
        if (size == 1) {
            root = null;
        } else {
            // Najdi zadnji vozel
            String path = Integer.toBinaryString(size).substring(1);
            Node<T> lastNode = root;
            for (int i = 0; i < path.length() - 1; i++) {
                lastNode = (path.charAt(i) == '0') ? lastNode.levi : lastNode.desni;
            }
            // Premakni zadnji vozel na koren in odstrani
            if (path.charAt(path.length() - 1) == '0') {
                root.value = lastNode.levi.value;
                lastNode.levi = null;
            } else {
                root.value = lastNode.desni.value;
                lastNode.desni = null;
            }
            steviloPremikov++;
            siftDown(root);
        }
        size--;
        return maxValue;
    }

    private void siftUp(Node<T> node) {
        while (node.stars != null && node.value.compareTo(node.stars.value) > 0) {
            swap(node, node.stars);
            steviloPrimerjav++;
            steviloPremikov += 3; // swap
            node = node.stars;
        }
    }

    private void siftDown(Node<T> node) {
        while (true) {
            Node<T> largest = node;
            if (node.levi != null && node.levi.value.compareTo(largest.value) > 0) {
                largest = node.levi;
            }
            if (node.desni != null && node.desni.value.compareTo(largest.value) > 0) {
                largest = node.desni;
            }
            if (largest == node) {
                break;
            }
            swap(node, largest);
            steviloPremikov += 3; // swap
            node = largest;
        }
    }

    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    public void resetiraj() {
        steviloPremikov = 0;
        steviloPrimerjav = 0;
    }
}

public class Izziv4 {
    public static void main(String[] args) {
        int stOperacij = 100000;
        Random random = new Random();
        APQ<Integer> APQ = new APQ<>();
        AHPQ<Integer> AHPQ = new AHPQ<>();
        LHPQ<Integer> LHPQ = new LHPQ<>();

        System.out.println("Implementacija\t\t\tCas[ms]\t\tPremiki\t\tPrimerjave");
        System.out.println("----------------------------------------------------------------------------");

        // APQ
        long zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            APQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                APQ.front();
                APQ.dequeue();
                APQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        long konec = System.currentTimeMillis();
        System.out.printf("Neurejeno polje (64,2x):\t%d\t\t%d\t\t%d\n", konec - zacetek, APQ.steviloPremikov,
                APQ.steviloPrimerjav);

        // AHPQ
        zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            AHPQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                AHPQ.front();
                AHPQ.dequeue();
                AHPQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        konec = System.currentTimeMillis();
        System.out.printf("Implicitna kopica (64,2x):\t%d\t\t%d\t\t%d\n", konec - zacetek, AHPQ.steviloPremikov,
                AHPQ.steviloPrimerjav);
        // LHPQ
        zacetek = System.currentTimeMillis();
        for (int i = 0; i < stOperacij; i++) {
            LHPQ.enqueue(random.nextInt(1000));
        }
        for (int i = 0; i < stOperacij; i++) {
            try {
                LHPQ.front();
                LHPQ.dequeue();
                LHPQ.enqueue(random.nextInt(1000));
            } catch (CollectionException e) {
                e.printStackTrace();
            }
        }
        konec = System.currentTimeMillis();
        System.out.printf("Eksplicitna kopica:\t\t%d\t\t%d\t\t%d\n", konec - zacetek, LHPQ.steviloPremikov,
                LHPQ.steviloPrimerjav);
    }
}
