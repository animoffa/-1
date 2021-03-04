package com.labs.lab2;//package lr2;
import java.util.Objects;
//Класс для представления точки в трехмерном пространстве
public class Point3d extends Point2d {

	private double zCoord;
	public Point3d(double x, double y, double z) {
	super(x, y);
	zCoord=z;
	}
	// Конструктор инициализации для z (для х и y уже прописан в Point2d)
	public Point3d() {
		super();
		this.zCoord=0.0;
	}
	//Возвращение координаты z
	public double getZ() {
		return zCoord;
	}
	//Установка координаты z
	public void setZ(double val) {
		zCoord=val;
	}
	//перезаписываем с помощью @Override метод для сравнения значений двух объектов Point3d
	@Override
	public boolean equals(Object obj) {

	if(this.getClass()!=obj.getClass())
		return false;

	Point3d p3 = (Point3d) obj;
	if((Objects.equals(this.getX(), p3.getX()))||(Objects.equals(this.getY(), p3.getY())) ||(Objects.equals(this.getZ(), p3.getZ()))){

		return false;
	}
		return true;
	}
	//Метод вычисляет расстояние между двумя точками
	public double distanceTo(Point3d obj) {
		return Math.round((Math.sqrt(Math.pow(obj.getX()-this.getX(),2)+Math.pow(obj.getY()-this.getY(),2)+Math.pow(obj.getZ()-this.getZ(),2)))*100.0)/100.0;
	}
}
