public class Izziv3 {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        CompleteBinaryTreeDraw tree = new CompleteBinaryTreeDraw(size);
        StdDraw.setCanvasSize(1300, 400);
        StdDraw.setXscale(-1, size); // n
        StdDraw.setYscale(-(int) (Math.log(size) / Math.log(2)) - 1, 1); // lg(n)

        tree.drawPostOrder();
        // tree.drawLevelOrder();
        // tree.drawInOrder();
        // tree.drawPreOrder();
    }
}