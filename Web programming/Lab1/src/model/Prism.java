package model;

import lombok.Getter;

public class Prism {
    @Getter
    Triangle base; //основание
    @Getter
    double height;

    /**
     * Constructs a prism with a base 'base' and height 'height'
     * @param base -- a base of a prism
     * @param height -- a height of a prism
     */
    public Prism(Triangle base, double height) {
        this.base = base;
        this.height = height;
    }

    /**
     * Constructs a prism with a base 'base' and height 'height'
     * @param a -- the first side of a prism base
     * @param b -- the second side of a prism base
     * @param c -- the third side of a prism base
     * @param height -- a height of a prism
     */
    public Prism(double height, double a, double b, double c) {
        this.height = height;
        this.base = new Triangle(a, b, c);
    }

    /**
     * @return a string representing a prism
     */
    @Override
    public String toString() {
        return "model.Prism{" +
                "height=" + height + ",\nbase:" +
                this.base.toString() + '}';
    }
}
