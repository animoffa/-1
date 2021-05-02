package com.labs.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private int width;
    private int height;
    private BufferedImage img;
    private Graphics g;
// Метод должен инициализировать объект BufferedImage новым
//изображением
    public JImageDisplay(int width, int heigth){
        this.width=width;
        this.height=heigth;
//        инициализировать объект BufferedImage новым
//        изображением с этой шириной и высотой, и типом изображения
//        TYPE_INT_RGB
        img=new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        g=img.getGraphics();
        this.setStartImage();
        this.repaint();
    }
    // Метод выводит на экран данные изображения
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawImage (img, 0, 0, null);

    }
    // Метод устанавливает все пиксели
    //изображения в черный цвет
    public void clearImage(){
        g.setColor(Color.black);
        g.fillRect(0,0,img.getWidth(), img.getHeight());
        this.repaint();
    }
    // Метод устанавливает определенный пиксель в определенный цвет
    public void drawPixel(int x, int y, Color color){
        g.setColor(color);
        g.fillRect(x, y, 1, 1);
        this.repaint();
    }
    public void setStartImage() {
        g.setColor(Color.orange);
        g.fillRect(img.getWidth() / 2 - 51, img.getHeight() / 2 - 51, 100, 100);
        g.setColor(Color.white);
        g.fillRect(img.getWidth() / 2 - 25, img.getHeight() / 2 - 25, 50, 50);

        g.setColor(Color.orange);
        g.fillRect(img.getWidth() / 2 - 13, img.getHeight() / 2 - 13, 25, 25);
        g.setColor(Color.white);
        g.fillRect(img.getWidth() / 2 - 7, img.getHeight() / 2 - 7, 13, 13);
        this.repaint();
    }

}
