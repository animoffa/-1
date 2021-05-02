package com.labs.lab3;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Этот класс хранит базовое состояние, необходимое алгоритму A * для вычисления пути по карте.
 * Это состояние включает в себя коллекцию «открытых путевых точек»
 * и другую коллекцию «закрытых путевых точек».

 **/
public class AStarState
{
    /** Это ссылка на карту, по которой движется алгоритм A *. **/
    private Map2D map;


   private HashMap<Location, Waypoint> open;
   private HashMap<Location, Waypoint> close;


    /**
     * Инициализация нового объекта состояния для алгоритма поиска пути A *.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        open=new HashMap<Location, Waypoint>();
        close=new HashMap<Location, Waypoint>();
    }

    /** Возвращает карту. **/
    public Map2D getMap()
    {
        return map;
    }



    /** Возвращает текущее количество открытых вершин. **/
    public int numOpenWaypoints()
    {
        return open.size();
    }

    /**
     * просматривает все открытые путевые точки
     * и возвращает путевую точку с минимальной общей стоимостью.
     **/

    public Waypoint getMinOpenWaypoint()
    {
        if (open.isEmpty())
            return null;


        float min=999999999;
        Waypoint way = null;

        ArrayList<Waypoint> values=new ArrayList<Waypoint>(open.values());

        for (Waypoint element: values) {
            if (element.getTotalCost() < min) {
                min=element.getTotalCost();
                way=element;

            }
        }
        return way;

    }
    /**
     * Этот метод добавляет вершину
     * в коллекцию открытых вершин.

     **/

    public boolean addOpenWaypoint(Waypoint newWP) {
        ArrayList<Location> locations=new ArrayList<Location>(open.keySet());
        Location newLoc=newWP.getLocation();
        for (Location index: locations){
            if (newLoc.equals(index)){
                Waypoint oldWP=open.get(index);
                double oldCost=oldWP.getPreviousCost();
                double newCost= newWP.getPreviousCost();
                if (newCost<oldCost){
                    open.put(newLoc, newWP);
                    return true;
                }
                return false;
            }
        }
        open.put(newLoc, newWP);
        return true;

    }

    /**
     * Возвращает true, если коллекция закрытых вершин содержит вершину
     * для указанного местоположения.
     **/

    public boolean isLocationClosed(Location loc)
    {
        return close.containsKey(loc);

    }

    /**
     * Этот метод перемещает вершину из списка открытых вершин в список закрытых вершин.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint newWay=open.get(loc);
        open.remove(loc);
        close.put(loc, newWay);
    }


}
