package com.labs.lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Простое приложение Swing для демонстрации алгоритма поиска пути A *.
 * Пользователю предоставляется карта, содержащая начальное и конечное местоположение.
 * Пользователь может нарисовать или очистить препятствия на карте,
 * а затем нажать кнопку, чтобы вычислить путь от начала до конца,
 * используя алгоритм поиска путей A *. Если путь найден, он отображается зеленым цветом.
 **/
public class AStarApp {

    /** Количество ячеек сетки по оси X. **/
    private int width;
    
    /** Количество ячеек сетки по оси Y. **/
    private int height;
    
    /** Место, откуда начинается путь. **/
    private Location startLoc;
    
    /** Место, где путь должен закончиться. **/
    private Location finishLoc;
    
    /**
     * Это двумерный массив компонентов пользовательского интерфейса,
     * которые обеспечивают отображение и манипулирование ячейками на карте.
     ***/
    private JMapCell[][] mapCells;

    
    /**
     * Этот внутренний класс обрабатывает события мыши.
     **/
    private class MapCellHandler implements MouseListener
    {

        private boolean modifying;
        
        /**
         * Это значение указывает, делаем ли мы ячейки проходимыми или непроходимыми.
         **/
        private boolean makePassable;
        
        /** Инициирует операцию модификации. **/
        public void mousePressed(MouseEvent e)
        {
            modifying = true;
            
            JMapCell cell = (JMapCell) e.getSource();
            

            makePassable = !cell.isPassable();
            
            cell.setPassable(makePassable);
        }

        /** Завершает операцию модификации. **/
        public void mouseReleased(MouseEvent e)
        {
            modifying = false;
        }
        

        public void mouseEntered(MouseEvent e)
        {
            if (modifying)
            {
                JMapCell cell = (JMapCell) e.getSource();
                cell.setPassable(makePassable);
            }
        }


        public void mouseExited(MouseEvent e)
        {
            // This one we ignore.
        }
        

        public void mouseClicked(MouseEvent e)
        {
            // And this one too.
        }
    }
    
    
    /**
     * Создает новый экземпляр AStarApp с указанными шириной и высотой карты.
     **/
    public AStarApp(int w, int h) {
        if (w <= 0)
            throw new IllegalArgumentException("w must be > 0; got " + w);
        
        if (h <= 0)
            throw new IllegalArgumentException("h must be > 0; got " + h);
        
        width = w;
        height = h;
        
        startLoc = new Location(2, h / 2);
        finishLoc = new Location(w - 3, h / 2);
    }
    
    
    /**
     * простой вспомогательный метод для настройки пользовательского интерфейса Swing.

     **/
    private void initGUI()
    {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();

        contentPane.setLayout(new BorderLayout());

        // Use GridBagLayout because it actually respects the preferred size
        // specified by the components it lays out.
        
        GridBagLayout gbLayout = new GridBagLayout();
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.weightx = 1;
        gbConstraints.weighty = 1;
        gbConstraints.insets.set(0, 0, 1, 1);

        JPanel mapPanel = new JPanel(gbLayout);
        mapPanel.setBackground(Color.GRAY);
        
        mapCells = new JMapCell[width][height];
        
        MapCellHandler cellHandler = new MapCellHandler();
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                mapCells[x][y] = new JMapCell();

                gbConstraints.gridx = x;
                gbConstraints.gridy = y;

                gbLayout.setConstraints(mapCells[x][y], gbConstraints);
                
                mapPanel.add(mapCells[x][y]);
                mapCells[x][y].addMouseListener(cellHandler);
            }
        }
        
        contentPane.add(mapPanel, BorderLayout.CENTER);
        
        JButton findPathButton = new JButton("Find Path");
        findPathButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { findAndShowPath(); }
        });
        
        contentPane.add(findPathButton, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setVisible(true);

        mapCells[startLoc.xCoord][startLoc.yCoord].setEndpoint(true);
        mapCells[finishLoc.xCoord][finishLoc.yCoord].setEndpoint(true);
    }

    
    /** Запуск приложения. **/
    private void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { initGUI(); }
        });
    }
    

    /**
     * Этот вспомогательный метод вычисляет путь, используя текущее состояние карты.
     * создается новый объект Map2D  и
     * инициализируется из текущего состояния приложения.
     * Затем вызывается указатель пути A *, и, если путь найден,
     * дисплей обновляется, чтобы показать найденный путь.
     * (Лучшее решение - использовать шаблон проектирования Model View Controller.)
     **/
    private void findAndShowPath()
    {
        // Create a Map2D object containing the current state of the user input.

        Map2D map = new Map2D(width, height);
        map.setStart(startLoc);
        map.setFinish(finishLoc);
        
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                mapCells[x][y].setPath(false);

                if (mapCells[x][y].isPassable())
                    map.setCellValue(x, y, 0);
                else
                    map.setCellValue(x, y, Integer.MAX_VALUE);
            }
        }
        
        // Try to compute a path.  If one can be computed, mark all cells in the
        // path.
        
        Waypoint wp = AStarPathfinder.computePath(map);
        
        while (wp != null)
        {
            Location loc = wp.getLocation();
            mapCells[loc.xCoord][loc.yCoord].setPath(true);
            
            wp = wp.getPrevious();
        }
    }
    
    
    /**
     * Точка входа в приложение. В настоящее время аргументы командной строки не распознаются.
     **/
    public static void main(String[] args) {
        AStarApp app = new AStarApp(40, 30);
        app.start();
    }   
}