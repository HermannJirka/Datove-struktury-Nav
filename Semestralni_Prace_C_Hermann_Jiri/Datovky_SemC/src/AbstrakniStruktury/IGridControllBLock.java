/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author George
 */
public interface IGridControllBLock extends Serializable {

    void addAdressToArray(Long adresa);

    void addVelikostBloku(int velikost, long adresa);

    Long getBlockAdress(int pozice);

    HashMap<Point, Integer> getGridFile();

    int getPocetBloku();

    LinkedList<Long> getPoleAdresBlocku();

    LinkedList<Integer> getPoleVelikostiBlocku();

    int getSizeOfBlock(Long adress);

    LinkedList<Double> getVektorX();

    LinkedList<Double> getVektorY();

    int getVelikostBlocku(Long adresa);

    void setGridFile(HashMap<Point, Integer> gridFile);

    void setPocetBloku(int pocetBloku);

    void setPoleAdresBlocku(LinkedList<Long> poleAdresBlocku);

    void setVektorX(LinkedList<Double> vektorX);

    void setVektorY(LinkedList<Double> vektorY);
    
}
