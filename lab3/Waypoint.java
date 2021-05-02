package com.labs.lab3;

/**
 * Этот класс представляет один шаг в пути
 *   Вершины состоят из местоположения, предыдущей вершины
 *   и некоторых значений стоимости, используемых для определения наилучшего пути.
 **/
public class Waypoint
{
    /** Расположение вершины. **/
    Location loc;

    /**
     * Предыдущая вершина в этом пути или null , если это
     *       * корень поиска A *.
     **/
    Waypoint prevWaypoint;

    /**
     * В этом поле хранится общая стоимость, получения от начального
     *       * местоположения до данной вершины через цепочку вершин. Это
     *       * фактическая стоимость следования по пути.
     **/
    private float prevCost;

    /**
     * В этом поле хранится оценка оставшейся стоимости пути из
     *       * данной вершины до конечной вершины.
     **/
    private float remainingCost;


    /**
     * новая вершины для указанного местоположения.
     **/
    public Waypoint(Location loc, Waypoint prevWaypoint)
    {
        this.loc = loc;
        this.prevWaypoint = prevWaypoint;
    }

    /** Возвращает местоположение вершины. **/
    public Location getLocation()
    {
        return loc;
    }
    
    /**
     * Возвращает предыдущую вершину в пути или null , если это
     *       * начало пути.
     **/
    public Waypoint getPrevious()
    {
        return prevWaypoint;
    }
    


    public void setCosts(float prevCost, float remainingCost)
    {
        this.prevCost = prevCost;
        this.remainingCost = remainingCost;
    }

    /**
     * Возвращает фактическую стоимость достижения этой точки с начала
     *       * пути.
     **/
    public float getPreviousCost()
    {
        return prevCost;
    }

    /**
     * Возвращает оценку оставшейся стоимости пути от данной вершины
     *       * до конечной.
     **/
    public float getRemainingCost()
    {
        return remainingCost;
    }

    /**
     * Возвращает оценку общей стоимости для данной вершины
     **/
    public float getTotalCost()
    {
        return prevCost + remainingCost;
    }
}
