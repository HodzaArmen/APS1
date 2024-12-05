public class CompleteBinaryTreeDraw {
    private int size;
    private char[] elements;
    private int[] x;
    private int[] y;
    private int num;

    public CompleteBinaryTreeDraw(int size) {
        this.size = size;
        this.elements = new char[size];
        this.x = new int[size];
        this.y = new int[size];
        this.num = 0;
        for (int i = 0; i < size; i++) {
            elements[i] = (char) (i + 65);
            y[i] = (int) (Math.log(i + 1) / Math.log(2)); // lg(i+1)
        }
        fillX(0);
    }

    private void fillX(int i) {
        if (2 * i + 1 < size)
            fillX(2 * i + 1);
        x[i] = num++;
        if (2 * i + 2 < size)
            fillX(2 * i + 2);
    }

    public void drawInOrder() {
        drawEdges();
        drawInOrderNodes(0);
    }

    public void drawPreOrder() {
        drawEdges();
        drawPreOrderNodes(0);
    }

    public void drawPostOrder() {
        drawEdges();
        drawPostOrderNodes(0);
    }

    public void drawLevelOrder() {
        drawEdges();
        for (int i = 0; i < size; i++) {
            drawNode(i);
        }
    }

    private void drawEdges() {
        for (int i = 0; i <= x[size - 1]; i++) { // zadnji element v x tabeli, je najvecja globina
            if (2 * i + 1 < size)
                drawLine(i, 2 * i + 1);
            if (2 * i + 2 < size)
                drawLine(i, 2 * i + 2);
        }
    }

    private void drawNode(int i) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(x[i], -y[i], 0.4);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x[i], -y[i], String.valueOf(elements[i]));
    }

    private void drawLine(int parent, int child) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(x[parent], -y[parent], x[child], -y[child]);
    }

    private void drawInOrderNodes(int i) {
        if (2 * i + 1 < size)
            drawInOrderNodes(2 * i + 1);
        drawNode(i);
        if (2 * i + 2 < size)
            drawInOrderNodes(2 * i + 2);
    }

    private void drawPreOrderNodes(int i) {
        drawNode(i);
        if (2 * i + 1 < size)
            drawPreOrderNodes(2 * i + 1);
        if (2 * i + 2 < size)
            drawPreOrderNodes(2 * i + 2);
    }

    private void drawPostOrderNodes(int i) {
        if (2 * i + 1 < size)
            drawPostOrderNodes(2 * i + 1);
        if (2 * i + 2 < size)
            drawPostOrderNodes(2 * i + 2);
        drawNode(i);
    }
}
