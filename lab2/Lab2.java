package com.labs.lab2;

import java.util.Scanner;

//Класс формирует трехмерный треугольник и вычисляет его площадь
public class Lab2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("X1:");
        double x1 = s.nextDouble();
        System.out.println("X2:");
        double x2 = s.nextDouble();
        System.out.println("X3:");
        double x3 = s.nextDouble();
        System.out.println("Y1:");
        double y1 = s.nextDouble();
        System.out.println("Y2:");
        double y2 = s.nextDouble();
        System.out.println("Y3:");
        double y3 = s.nextDouble();
        System.out.println("Z1:");
        double z1 = s.nextDouble();
        System.out.println("Z2:");
        double z2 = s.nextDouble();
        System.out.println("Z3:");
        double z3 = s.nextDouble();


        Point3d Point1 = new Point3d(x1, y1, z1);
        Point3d Point2 = new Point3d(x2, y2, z2);
        Point3d Point3 = new Point3d(x3, y3, z3);


        if ((!Point1.equals(Point2)) || (!Point2.equals(Point3)) || (!Point3.equals(Point1)))
            System.out.println("Некоторые точки равны, невозможно вычислить площадь");

        else
            System.out.println("Площадь треугольника: " + computeArea(Point1, Point2, Point3));

    }
//Метод вычисляет площадь трехмерного треугольника
    public static double computeArea(Point3d obj1, Point3d obj2, Point3d obj3) {
        double a = obj2.distanceTo(obj1);
        double b = obj3.distanceTo(obj2);
        double c = obj1.distanceTo(obj3);
        double p = (a + b + c) / 2;
        return Math.round((Math.sqrt(p * (p - a) * (p - b) * (p - c))) * 100.00) / 100.00;
    }
}
