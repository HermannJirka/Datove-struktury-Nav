/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import AbstrakniStruktury.GridIndex;
import Data.IMesto;
import Data.Mesto;
import java.awt.Point;
import java.util.LinkedList;

/**
 *
 * @author George
 */
public class demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IMesto mesto = new Mesto("Z", new Point(5, 5));
        IMesto mesto2 = new Mesto("K", new Point(10, 10));
        IMesto mesto3 = new Mesto("U", new Point(15, 45));
        IMesto mesto4 = new Mesto("A", new Point(20, 25));
        IMesto mesto5 = new Mesto("W", new Point(22, 60));
        IMesto mesto6 = new Mesto("P", new Point(25, 55));
        IMesto mesto7 = new Mesto("X", new Point(30, 30));
        IMesto mesto8 = new Mesto("R", new Point(28, 50));
        IMesto mesto9 = new Mesto("N", new Point(33, 62));
        IMesto mesto10 = new Mesto("S", new Point(35, 12));
        IMesto mesto11 = new Mesto("T", new Point(42, 65));
        IMesto mesto12 = new Mesto("F", new Point(38, 52));
        IMesto mesto13 = new Mesto("G", new Point(45, 40));
        IMesto mesto14 = new Mesto("M", new Point(47, 36));
        IMesto mesto15 = new Mesto("I", new Point(40, 20));
        LinkedList<IMesto> listMest = new LinkedList<>();
        // TODO code application logic here
        Point bod1 = new Point(), bod2 = new Point(), bod3 = new Point(), bod4 = new Point(), bod5 = new Point(), bod6 = new Point(), bod7 = new Point(), bod8 = new Point();
        Point bod9 = new Point(), bod10 = new Point(), bod11 = new Point(), bod12 = new Point(), bod13 = new Point(), bod14 = new Point(), bod15 = new Point(), bod16 = new Point();
        bod1.setLocation(5, 5);
        bod2.setLocation(10, 10);
        bod3.setLocation(15, 45);
        bod4.setLocation(20, 25);
        bod5.setLocation(22, 60);
        bod6.setLocation(25, 55);
        bod7.setLocation(30, 30);
        bod8.setLocation(28, 50);
        bod9.setLocation(33, 62);
        bod10.setLocation(35, 12);
        bod11.setLocation(42, 65);
        bod12.setLocation(38, 52);
        bod13.setLocation(45, 40);
        bod14.setLocation(47, 36);
        bod15.setLocation(40, 20);
        GridIndex<IMesto> grid = new GridIndex<>();
        grid.addVrcholFinal(bod1, mesto);
        grid.addVrcholFinal(bod2, mesto2);
        grid.addVrcholFinal(bod3, mesto3);
        grid.addVrcholFinal(bod4, mesto4);
        grid.addVrcholFinal(bod5, mesto5);
        grid.addVrcholFinal(bod6, mesto6);
        grid.addVrcholFinal(bod7, mesto7);
        grid.addVrcholFinal(bod8, mesto8);
        grid.addVrcholFinal(bod9, mesto9);
        grid.addVrcholFinal(bod10, mesto10);
        grid.addVrcholFinal(bod11, mesto11);
        grid.addVrcholFinal(bod12, mesto12);
        grid.addVrcholFinal(bod13, mesto13);
        grid.addVrcholFinal(bod14, mesto14);
        grid.addVrcholFinal(bod15, mesto15);
        
        
//        grid.addVrchol(bod1, mesto);
//        grid.addVrchol(bod2, mesto2);
//        grid.addVrchol(bod3, mesto3);
//        grid.addVrchol(bod4, mesto4);
//        grid.addVrchol(bod5, mesto5);
//        grid.addVrchol(bod6, mesto6);
//        grid.addVrchol(bod7, mesto7);
//        grid.addVrchol(bod8, mesto8);
//        grid.addVrchol(bod9, mesto9);
//        grid.addVrchol(bod10, mesto10);
//        grid.addVrchol(bod11, mesto11);
//        grid.addVrchol(bod12, mesto12);
//        grid.addVrchol(bod13, mesto13);
//        grid.addVrchol(bod14, mesto14);
//        grid.addVrchol(bod15, mesto15);
        Point b7 = new Point();
        b7.setLocation(10, 25);
        Point b8 = new Point();
        b8.setLocation(20, 50);
        listMest = grid.intervalFindVrchol(b7.getX(), b7.getY(), b8.getX(), b8.getY());

        System.out.println();
    }

}
