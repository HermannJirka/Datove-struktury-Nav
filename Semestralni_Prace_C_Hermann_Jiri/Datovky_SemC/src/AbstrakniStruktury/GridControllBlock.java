/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author George
 */
public class GridControllBlock implements IGridControllBLock {

    private HashMap<Point, Integer> gridFile;
    private LinkedList<Double> vektorX;
    private LinkedList<Double> vektorY;
    private int pocetBloku;
    private LinkedList<Long> poleAdresBlocku;
    private LinkedList<Integer> poleVelikostiBlocku;

    public GridControllBlock(HashMap<Point, Integer> gridFile, LinkedList<Double> vektorX, LinkedList<Double> vektorY, int pocetBloku) {
        this.gridFile = gridFile;
        this.vektorX = vektorX;
        this.vektorY = vektorY;
        this.pocetBloku = pocetBloku;
        this.poleAdresBlocku = new LinkedList<>();
        this.poleVelikostiBlocku = new LinkedList<>();
    }

    @Override
    public LinkedList<Long> getPoleAdresBlocku() {
        return poleAdresBlocku;
    }

    @Override
    public void addAdressToArray(Long adresa) {
        poleAdresBlocku.add(adresa);
    }

    @Override
    public Long getBlockAdress(int pozice) {
        return poleAdresBlocku.get(pozice);
    }

    @Override
    public int getSizeOfBlock(Long adress) {
        for (int i = 0; i < poleAdresBlocku.size(); i++) {
            Long pom = poleAdresBlocku.get(i);
            if (adress.equals(pom)) {
                return poleVelikostiBlocku.get(i);
            }
        }
        return 0;
    }

    @Override
    public void setPoleAdresBlocku(LinkedList<Long> poleAdresBlocku) {
        this.poleAdresBlocku = poleAdresBlocku;
    }

    @Override
    public HashMap<Point, Integer> getGridFile() {
        return gridFile;
    }

    @Override
    public void setGridFile(HashMap<Point, Integer> gridFile) {
        this.gridFile = gridFile;
    }

    @Override
    public LinkedList<Double> getVektorX() {
        return vektorX;
    }

    @Override
    public void setVektorX(LinkedList<Double> vektorX) {
        this.vektorX = vektorX;
    }

    @Override
    public LinkedList<Double> getVektorY() {
        return vektorY;
    }

    @Override
    public void setVektorY(LinkedList<Double> vektorY) {
        this.vektorY = vektorY;
    }

    @Override
    public int getPocetBloku() {
        return pocetBloku;
    }

    @Override
    public void setPocetBloku(int pocetBloku) {
        this.pocetBloku = pocetBloku;
    }

    @Override
    public LinkedList<Integer> getPoleVelikostiBlocku() {
        return poleVelikostiBlocku;
    }

    @Override
    public void addVelikostBloku(int velikost, long adresa) {

        for (int i = 0; i < poleAdresBlocku.size(); i++) {
            if (poleAdresBlocku.get(i) == adresa) {
                poleVelikostiBlocku.add(i, velikost);
            }
        }

    }

    @Override
    public int getVelikostBlocku(Long adresa) {
        if (adresa != null) {
            for (int i = 0; i < poleAdresBlocku.size(); i++) {
                if (poleAdresBlocku.get(i).equals(adresa)) {
                    return poleVelikostiBlocku.get(i);
                }
            }
        }
        return 0;
    }

}
