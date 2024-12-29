class Node<T> {
    T value;
    Node<T> levi, desni, stars;

    Node(T value) {
        this.value = value;
    }
}

public class LHPQ<T extends Comparable<T>> implements PriorityQueue<T> {
    private Node<T> koren;
    private int size;
    public int steviloPremikov = 0;
    public int steviloPrimerjav = 0;

    public LHPQ() {
        koren = null;
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
        return koren.value;
    }

    @Override
    public void enqueue(T x) {
        Node<T> node = new Node<>(x);
        if (koren == null) {
            koren = node;
        } else {
            // 0 = levo, 1 = desno
            // Velikost 4 = 100, substringana vrednost 00 -> levo, levo
            String vrednost = Integer.toBinaryString(size + 1).substring(1);
            Node<T> stars = koren;
            for (int i = 0; i < vrednost.length() - 1; i++) { // iscemo zadnjo vozlisce
                stars = (vrednost.charAt(i) == '0') ? stars.levi : stars.desni;
            }
            if (vrednost.charAt(vrednost.length() - 1) == '0') {
                stars.levi = node;
            } else {
                stars.desni = node;
            }
            node.stars = stars;
        }
        size++;
        steviloPremikov++;
        siftUp(node);
    }

    @Override
    public T dequeue() throws CollectionException {
        if (isEmpty())
            throw new CollectionException(ERR_MSG_EMPTY);
        T najvecji = koren.value;
        if (size == 1) {
            koren = null;
        } else {
            String vrednost = Integer.toBinaryString(size).substring(1);
            Node<T> stars = koren;
            for (int i = 0; i < vrednost.length() - 1; i++) {
                stars = (vrednost.charAt(i) == '0') ? stars.levi : stars.desni;
            }
            // zadnjo vrednost (list) damo v koren
            if (vrednost.charAt(vrednost.length() - 1) == '0') {
                koren.value = stars.levi.value;
                stars.levi = null;
            } else {
                koren.value = stars.desni.value;
                stars.desni = null;
            }
            steviloPremikov++;
            siftDown(koren);
        }
        size--;
        return najvecji;
    }

    private void siftUp(Node<T> node) {
        while (node.stars != null && node.value.compareTo(node.stars.value) > 0) {
            swap(node, node.stars);
            steviloPrimerjav++;
            steviloPremikov += 3;
            node = node.stars;
        }
    }

    private void siftDown(Node<T> node) {
        while (true) {
            Node<T> max = node;
            if (node.levi != null && node.levi.value.compareTo(max.value) > 0) {
                max = node.levi;
            }
            if (node.desni != null && node.desni.value.compareTo(max.value) > 0) {
                max = node.desni;
            }
            if (max == node) {
                break;
            }
            swap(node, max);
            steviloPremikov += 3;
            node = max;
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