package model;

import lombok.Getter;

public class Triangle {
    @Getter
    double a;
    @Getter
    double b;
    @Getter
    double c;

    /**
     * Constructs an equilateral triangle with a side
     * @param a -- a side of a triangle
     */
    public Triangle(double a) {
        this.a = a;
        this.b = a;
        this.c = a;
    }

    /**
     * Constructs a triangle by its sides
     * @param a - the first side
     * @param b - the second side
     * @param c - the third side
     */
    public Triangle(double a, double b, double c) {
        assert a < b + c;
        assert b < a + c;
        assert c < a + b;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Determines if a triangle is equilateral
     * @return true if a triangle is equilateral, else returns false
     */
    public boolean isEquilateral() { //равносторонний
        return a == b && b == c;
    }

    /**
     * Determines if a triangle is isosceles
     * @return true if a triangle is isosceles, else returns false
     */
    public boolean isIsosceles() { //равнобедренный
        return !isEquilateral() && (a == b || b == c || a == c);
    }

    /**
     * Determines if a triangle is rectangular
     * @return true if a triangle is rectangular, else returns false
     */
    public boolean isRectangular() { //прямоугольный
        return (a * a + b * b == c * c) || (b * b + c * c == a * a) || (a * a + c * c == b * b);
    }

    /**
     * Determines if a triangle is arbitrary
     * @return true if a triangle is arbitrary, else returns false
     */
    public boolean isArbitrary() {
        return !isEquilateral() && !isIsosceles() && !isRectangular();
    }

    /**
     * @return a string representing a triangle
     */
    @Override
    public String toString() {
        return "model.Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c + '}';
    }
}
