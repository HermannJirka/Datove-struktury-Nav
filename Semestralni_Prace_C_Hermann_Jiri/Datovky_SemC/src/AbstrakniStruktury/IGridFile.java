/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import objekty.IKlicGrid;

/**
 *
 * @author George
 */
public interface IGridFile<E extends IKlicGrid> {

    void addDataToGrid(E data) throws IOException, FileNotFoundException, ClassNotFoundException;

    HashMap<Point, Integer> getGridFileHash();

    int getPocitadloBloku();

    LinkedList<Double> getVektorX();

    LinkedList<Double> getVektorY();

    LinkedList<E> intervalFindVrchol(double minBoundX, double minBoundY, double maxBoundX, double maxBoundY) throws IOException, FileNotFoundException, ClassNotFoundException;

    E najdiVrchol(double x, double y) throws IOException, FileNotFoundException, ClassNotFoundException;
    
}
