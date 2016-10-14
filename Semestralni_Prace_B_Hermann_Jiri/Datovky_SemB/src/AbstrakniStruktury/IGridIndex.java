/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import Data.IMesto;
import java.awt.Point;
import java.util.LinkedList;
import objekty.IKlic;
import objekty.IKlic;

/**
 *
 * @author George
 */
public interface IGridIndex<D> {

   

    IMesto deleteVrchol(Point vrchol);

    IMesto findVrchol(Point vrchol);

    IMesto updateVrchol(Point bod, D data);

    LinkedList<IMesto> intervalFindVrchol(double minBoundX, double minBoundY, double maxBoundX, double maxBoundY);

    boolean jePrazdny();

    void addPrvni(Point bod, D data);

    void addVrcholFinal(Point bod, D data);

    void addMesto(Point bod, D data);

    LinkedList<Double> listRezuX();

    LinkedList<Double> listRezuY();
    void Zrus();
}
