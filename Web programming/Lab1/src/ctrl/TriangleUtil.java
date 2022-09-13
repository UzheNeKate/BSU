package ctrl;

import model.Triangle;

public class TriangleUtil {
    /**
     * Calculates a perimeter of a triangle
     * @param t -- a triangle which perimeter you want to calculate
     * @return a perimeter of the passed triangle
     */
    public static double calcPerimeter(Triangle t) {
        return t.getA() + t.getB() + t.getC();
    }

    /**
     * Calculates a square of a triangle
     * @param t -- a triangle which square you want to calculate
     * @return a square of the passed triangle
     */
    public static double calcSquare(Triangle t) {
        var p = TriangleUtil.calcPerimeter(t) / 2;
        return Math.sqrt(p * (p - t.getA()) * (p - t.getB()) * (p - t.getC()));
    }
}
