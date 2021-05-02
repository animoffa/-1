package com.labs.lab4;

import com.labs.lab4.Complex;
import com.labs.lab4.FractalGenerator;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

//    метод позволяет генератору
//    фракталов определить наиболее «интересную» область комплексной плоскости
//    для конкретного фрактала
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;

    }

    //Метод реализует итеративную
//функцию для фрактала Мандельброта
    @Override
    public int numIterations(double x, double y) {
        int iter = 0;

        Complex com = new Complex();
        Complex a;
        Complex c = new Complex(x, y);
        while ((iter < MAX_ITERATIONS) && (com.modul() < 4)) {
            iter += 1;
            a = com.Pow().Plus(c);
            com = a;

        }
        if (iter == MAX_ITERATIONS)
            return -1;
        else
            return iter;

    }


}
