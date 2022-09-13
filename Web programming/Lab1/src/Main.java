import ctrl.TriangleUtil;
import model.Triangle;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        Triangle[] triangles = new Triangle[n];
        int[][] abc = {{3, 4, 5}, {4, 4, 4}, {2, 2, 3}, {6, 8, 10}, {7, 7, 7},
                {7, 7, 12}, {3, 8, 9}, {1, 1, 1}, {9, 9, 9}, {4, 5, 7}};
        for (int i = 0; i < n; i++) {
            triangles[i] = new Triangle(abc[i][0], abc[i][1], abc[i][2]);
        }
        int cntE = 0, cntI = 0, cntR = 0, cntA = 0;
        ArrayList<Triangle> rect = new ArrayList<>();
        ArrayList<Triangle> iso = new ArrayList<>();
        ArrayList<Triangle> equ = new ArrayList<>();
        ArrayList<Triangle> arb = new ArrayList<>();
        for (var tr : triangles) {
            if (tr.isRectangular()) {
                cntR++;
                rect.add(tr);
            } else if (tr.isIsosceles()) {
                cntI++;
                iso.add(tr);
            } else if (tr.isEquilateral()) {
                cntE++;
                equ.add(tr);
            } else {
                cntA++;
                arb.add(tr);
            }
        }
        System.out.println(getMinPerimeter(iso).toString());
        System.out.println(getMinPerimeter(equ).toString());
        System.out.println(getMinPerimeter(rect).toString());
        System.out.println(getMinPerimeter(arb).toString());
        System.out.println("Произвольных треугольников: " + cntA +
                "\nРавносторонних треугольников: " + cntE +
                "\nРавнобедренных треугольников: " + cntI +
                "\nПрямоугольных треугольников: " + cntR);
        model.Pyramid p = new model.Pyramid(triangles[0], 15);
        System.out.println(p);
    }

    /**
     * Searches for a triangle with a maximal square in a list
     * @param triangles -- a list to search in
     * @return a triangle with a maximal square
     */
    public static Triangle getMaxSquare(ArrayList<Triangle> triangles) {
        double maxS = 0;
        Triangle trg = null;
        for (var tr : triangles) {
            if (TriangleUtil.calcSquare(tr) > maxS) {
                maxS = TriangleUtil.calcSquare(tr);
                trg = tr;
            }
        }
        return trg;
    }

    /**
     * Searches for a triangle with a minimal square in a list
     * @param triangles -- a list to search in
     * @return a triangle with a minimal square
     */
    public static Triangle getMinSquare(ArrayList<Triangle> triangles) {
        double minS = TriangleUtil.calcSquare(triangles.get(0));
        Triangle trg = triangles.get(0);
        for (var tr : triangles) {
            if (TriangleUtil.calcSquare(tr) < minS) {
                minS = TriangleUtil.calcSquare(tr);
                trg = tr;
            }
        }
        return trg;
    }

    /**
     * Searches for a triangle with a maximal perimeter in a list
     * @param triangles -- a list to search in
     * @return a triangle with a maximal perimeter
     */
    public static Triangle getMaxPerimeter(ArrayList<Triangle> triangles) {
        double maxP = 0;
        Triangle trg = null;
        for (var tr : triangles) {
            if (TriangleUtil.calcPerimeter(tr) > maxP) {
                maxP = TriangleUtil.calcPerimeter(tr);
                trg = tr;
            }
        }
        return trg;
    }

    /**
     * Searches for a triangle with a minimal perimeter in a list
     * @param triangles -- a list to search in
     * @return a triangle with a minimal perimeter
     */
    public static Triangle getMinPerimeter(ArrayList<Triangle> triangles) {
        double minP = TriangleUtil.calcPerimeter(triangles.get(0));
        Triangle trg = triangles.get(0);
        for (var tr : triangles) {
            if (TriangleUtil.calcPerimeter(tr) < minP) {
                minP = TriangleUtil.calcPerimeter(tr);
                trg = tr;
            }
        }
        return trg;
    }
}
