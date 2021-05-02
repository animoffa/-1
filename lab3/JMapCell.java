package com.labs.lab3;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


/**
 * Этот класс является пользовательским компонентом Swing
 * для представления отдельной ячейки карты на 2D-карте.
 * Ячейка имеет несколько различных состояний,
 * но основное состояние: является ли ячейка проходимой или нет.
 */
public class JMapCell extends JComponent
{
    private static final Dimension CELL_SIZE = new Dimension(12, 12);
    
    /** ячейка является конечной точкой**/
    boolean endpoint = false;
    
    
    /** ячейка проходима**/
    boolean passable = true;
    
    /**
     * ячейка является частью пути
     **/
    boolean path = false;
    
    /**
     * Построить новую ячейку карты с указанной «проходимостью».
     * Ввод истины означает, что ячейка проходима.
     **/
    public JMapCell(boolean pass)
    {
        // Set the preferred cell size, to drive the initial window size.
        setPreferredSize(CELL_SIZE);
        
        setPassable(pass);
    }
    
    /** Создайте новую ячейку карты, которая будет доступна по умолчанию. **/
    public JMapCell()
    {
        // Call the other constructor, specifying true for "passable".
        this(true);
    }
    
    /** Помечает эту ячейку как начальную или конечную. **/
    public void setEndpoint(boolean end)
    {
        endpoint = end;
        updateAppearance();
    }
    
    /**
     * Устанавливает данную ячейку как доступную или нет.
     **/
    public void setPassable(boolean pass)
    {
        passable = pass;
        updateAppearance();
    }
    
    /** Возвращает true, если эта ячейка доступна, false - в противном случае. **/
    public boolean isPassable()
    {
        return passable;
    }
    
    /** Переключает текущее «проходимое» состояние ячейки карты. **/
    public void togglePassable()
    {
        setPassable(!isPassable());
    }
    
    /** Помечает эту ячейку как часть пути, обнаруженного алгоритмом A *. **/
    public void setPath(boolean path)
    {
        this.path = path;
        updateAppearance();
    }
    
    /**
     * Этот вспомогательный метод обновляет цвет фона,
     * чтобы соответствовать текущему внутреннему состоянию ячейки.
     **/
    private void updateAppearance()
    {
        if (passable)
        {
            // Passable cell.  Indicate its state with a border.
            setBackground(Color.WHITE);

            if (endpoint)
                setBackground(Color.CYAN);
            else if (path)
                setBackground(Color.GREEN);
        }
        else
        {
            // Impassable cell.  Make it all red.
            setBackground(Color.RED);
        }
    }

    /**
     * Реализация метода рисования для отображения цвета фона в ячейке карты.
     **/
    protected void paintComponent(Graphics g)
    {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}