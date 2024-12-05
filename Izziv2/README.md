# Sklad, vrsta z dvema koncema in zaporedje

Pri tem izzivu se boste pozabavali z osnovnimi podatkovnimi strukturami. S (statičnim) poljem boste implementirali 3 APT-je: 
<ul>
  <li>sklad,</li>
  <li>vrsto z dvema koncema in</li>
  <li>zaporedje.</li>
</ul>

Če se naloge lotite pametno, potem je dovolj sprogramirati le izvedbo vrste z dvema koncema.

Na voljo so naslednji razred in vmesniki:
```
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

interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
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
```

Pri tem je:
<ul>
  <li>CollectionException - izjema, ki jo vrnete ob težavah (preliv oz. podliv sklada oz. vrste),</li>
  <li>Collection - osnovni vmesnik za zbirko elementov,</li>
  <li>Stack<T> - generični vmesnik (abstraktni podatkovni tip) za sklad,</li>
  <li>Deque<T> - generični vmesnik za vrsto z dvema koncema in</li>
  <li>Sequence<T> - generični vmesnik za zaporedje. </li>
  <li>Stack<String> torej predstavlja sklad nizov, Deque<Double> vrsto števil v plavajoči vejici itd..</li>
</ul>

Preden se lotiš programiranja, dobro poglej vmesnike: vsi trije (Stack<T>, Deque<T> in Sequence<T>) razširjajo Collection, prav tako večina metod lahko povzroči izjemo.

## Vrsta z dvema koncema
S pomočjo (krožnega) polja implementiraj razred ArrayDeque<T>, ki implementira vse tri APT-je. Njegova definicija naj bo naslednja:
```
class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
    private static final int DEFAULT_CAPACITY = 64;

    // Tukaj napiši svojo kodo.
}
```

a) Implementiraj vse potrebne metode.

b) Metode naj v primeru težav vržejo izjemo CollectionException. V ta namen imate že definirana sporočila o napakah:

ERR_MSG_EMPTY - kadar pride do odstanjevanja ali povpraševanja po elementu v že prazni zbirki in
ERR_MSG_FULL - kadar pride do dodajanja elementa v že polno zbirko,
ERR_MSG_INDEX - kadar pride do napačnega indeksa v zaporedju.

c) Vse metode, ki odstranjujejo elemente, naj preprečujejo postopanje (angl. loitering).

## Testni razred
Implementiraj testni razred Izziv2.java, ki prikaže (pravilno) izvedbo vsake operacije za vse tri APT-je.
