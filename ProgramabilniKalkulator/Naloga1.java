import java.util.Scanner;

class CollectionException extends Exception {
    public CollectionException(String msg) {
        super(msg);
    }
}

interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";

    boolean isEmpty();

    boolean isFull();

    int size();

    String toString();
}

interface Deque<T> extends Collection {
    T front() throws CollectionException;

    T back() throws CollectionException;

    void enqueue(T x) throws CollectionException;

    void enqueueFront(T x) throws CollectionException;

    T dequeue() throws CollectionException;

    T dequeueBack() throws CollectionException;
}

interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";

    T get(int i) throws CollectionException;

    void add(T x) throws CollectionException;
}

interface Stack<T> extends Collection {
    T top() throws CollectionException;

    void push(T x) throws CollectionException;

    T pop() throws CollectionException;

    void dup2() throws CollectionException;

    void swap() throws CollectionException;

    void print();
}

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;
    private T[] a;
    private int front = 0;
    private int back = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
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

    public void dup2() throws CollectionException {
        T st1 = pop();
        T st2 = pop();
        push(st2);
        push(st1);
        push(st2);
        push(st1);
    }

    public void swap() throws CollectionException {
        T prvi = pop();
        T drugi = pop();
        push(prvi);
        push(drugi);
    }

    public void print() {
        if (isEmpty()) {
            System.out.println();
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(a[index(i)]);
            if (i != size - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

public class Naloga1 {
    private static boolean pogoj = false;
    private static Sequence<Stack<String>> ss;
    private static Stack<String> glavniSklad;
    private static boolean pisiNaSklad = false;
    private static int stUkazovZaNaSklad = 0;
    private static int stNapisanih = 0;
    private static int naKateriSklad = 0;

    public static void main(String[] args) {
        try (Scanner sc_v = new Scanner(System.in)) {
            while (sc_v.hasNextLine()) {
                ss = new ArrayDeque<>();
                for (int i = 0; i < 42; i++) {
                    ss.add(new ArrayDeque<>());
                }
                glavniSklad = ss.get(0);
                String vrstica = sc_v.nextLine();
                pogoj = false;
                try (Scanner sc_n = new Scanner(vrstica)) {
                    while (sc_n.hasNext()) {
                        String ukaz = sc_n.next();
                        if (pisiNaSklad) {
                            if (stNapisanih != stUkazovZaNaSklad) {
                                ss.get(naKateriSklad).push(ukaz);
                                stNapisanih++;
                            } else {
                                pisiNaSklad = false;
                                izvrsiUkaz(ukaz);
                            }
                        } else {
                            izvrsiUkaz(ukaz);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void izvrsiUkaz(String ukaz) throws CollectionException {
        if (ukaz.startsWith("?")) {
            ukaz = ukaz.substring(1);
            if (!pogoj) {
                return;
            }
        }
        int st1, st2;
        String n1, n2;
        switch (ukaz) {
            case "echo":
                System.out.println(glavniSklad.isEmpty() ? "" : glavniSklad.top());
                break;
            case "pop":
                if (!glavniSklad.isEmpty())
                    glavniSklad.pop();
                break;
            case "dup":
                if (!glavniSklad.isEmpty())
                    glavniSklad.push(glavniSklad.top());
                break;
            case "dup2":
                glavniSklad.dup2();
                break;
            case "swap":
                glavniSklad.swap();
                break;
            case "char":
                st1 = Integer.parseInt(glavniSklad.pop());
                glavniSklad.push(String.valueOf(Character.toChars(st1)));
                break;
            case "even":
                glavniSklad.push(Integer.parseInt(glavniSklad.pop()) % 2 == 0 ? "1" : "0");
                break;
            case "odd":
                glavniSklad.push(Integer.parseInt(glavniSklad.pop()) % 2 == 0 ? "0" : "1");
                break;
            case "!":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = 1;
                for (int i = st1; i >= 1; i--) {
                    st2 *= i;
                }
                glavniSklad.push(st2 + "");
                break;
            case "len":
                glavniSklad.push(glavniSklad.pop().length() + "");
                break;
            case "<>":
                if (Integer.parseInt(glavniSklad.pop()) != Integer.parseInt(glavniSklad.pop())) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case "<":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                if (st2 < st1) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case "<=":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                if (st2 <= st1) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case "==":
                if (Integer.parseInt(glavniSklad.pop()) == Integer.parseInt(glavniSklad.pop())) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case ">":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                if (st2 > st1) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case ">=":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                if (st2 >= st1) {
                    glavniSklad.push(1 + "");
                } else {
                    glavniSklad.push(0 + "");
                }
                break;
            case "+":
                glavniSklad.push(Integer.parseInt(glavniSklad.pop()) + Integer.parseInt(glavniSklad.pop()) + "");
                break;
            case "-":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                glavniSklad.push(st2 - st1 + "");
                break;
            case "*":
                glavniSklad.push(Integer.parseInt(glavniSklad.pop()) * Integer.parseInt(glavniSklad.pop()) + "");
                break;
            case "/":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                glavniSklad.push(st2 / st1 + "");
                break;
            case "%":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                glavniSklad.push(st2 % st1 + "");
                break;
            case ".":
                n1 = glavniSklad.pop();
                n2 = glavniSklad.pop();
                glavniSklad.push(n2 + n1);
                break;
            case "rnd":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                glavniSklad.push((int) (Math.random() * ((st1 - st2) + 1)) + st2 + "");
                break;
            case "then":
                pogoj = Integer.parseInt(glavniSklad.pop()) != 0;
                break;
            case "else":
                pogoj = !pogoj;
                break;
            case "print":
                ss.get(Integer.parseInt(glavniSklad.pop())).print();
                break;
            case "clear":
                int stSklada = Integer.parseInt(glavniSklad.pop());
                while (!ss.get(stSklada).isEmpty()) {
                    ss.get(stSklada).pop();
                }
                break;
            case "run":
                zazeniIzbraniSklad(Integer.parseInt(glavniSklad.pop()));
                break;
            case "loop":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                for (int j = 1; j <= st2; j++) {
                    zazeniIzbraniSklad(st1);
                }
                break;
            case "fun":
                naKateriSklad = Integer.parseInt(glavniSklad.pop());
                stUkazovZaNaSklad = Integer.parseInt(glavniSklad.pop());
                stNapisanih = 0;
                pisiNaSklad = true;
                break;
            case "move":
                st1 = Integer.parseInt(glavniSklad.pop());
                st2 = Integer.parseInt(glavniSklad.pop());
                for (int i = 1; i <= st2; i++) {
                    ss.get(st1).push(glavniSklad.pop());
                }
                break;
            case "reverse":
                obrniSklad();
                break;
            default:
                glavniSklad.push(ukaz);
        }
    }

    private static void obrniSklad() throws CollectionException {
        int number = Integer.parseInt(glavniSklad.pop());
        Stack<String> stack = ss.get(number);
        String[] array = new String[stack.size()];
        int counter = 0;
        while (!stack.isEmpty()) {
            array[counter++] = stack.pop();
        }
        for (int i = 0; i < counter; i++) {
            stack.push(array[i]);
        }
    }

    private static void zazeniIzbraniSklad(int stSklada) throws CollectionException {
        Stack<String> izbraniSklad = ss.get(stSklada);
        String[] a = new String[izbraniSklad.size()];
        int j = 0;
        while (!izbraniSklad.isEmpty()) {
            a[j++] = izbraniSklad.pop();
        }
        for (int i = a.length - 1; i >= 0; i--) {
            izbraniSklad.push(a[i]);
            izvrsiUkaz(a[i]);
        }
    }
}