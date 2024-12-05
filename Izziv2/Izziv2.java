public class Izziv2 {
    public static void main(String[] args) throws CollectionException {
        Stack<String> stack = new ArrayDeque<>();
        Deque<String> deque = new ArrayDeque<>();
        Sequence<String> sequence = new ArrayDeque<>();
        stack.push("ABC");
        stack.push("DEF");
        stack.push("GHI");
        System.out.print("Stack: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.top() + " ");
            deque.enqueueFront(stack.pop());
        }
        System.out.print("\nDeque: ");
        while (!deque.isEmpty()) {
            System.out.print(deque.back() + " ");
            sequence.add(deque.dequeueBack());
        }
        System.out.print("\nSequence: ");
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print((i + 1) + "." + sequence.get(i) + " ");
        }
        System.out.println();
    }
}