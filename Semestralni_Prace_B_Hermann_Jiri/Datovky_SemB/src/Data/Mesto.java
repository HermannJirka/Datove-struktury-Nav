/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import objekty.IKlic;

/**
 *
 * @author George
 */
public class Mesto implements IMesto, Comparable<Mesto>,Serializable {

    private String nazev;
    private Point souradnice;
    private int ohodnoceni = Integer.MAX_VALUE;
    private Mesto predchudce;
    private Set<String> mapaNasledniku;

    public Mesto(String nazev, Point souradnice) {
        this.nazev = nazev;
        this.souradnice = souradnice;
        this.mapaNasledniku = new HashSet<>();
    }

    @Override
    public int CompareString(IKlic klic1) {
        return nazev.compareToIgnoreCase(((Mesto) klic1).getNazev());
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public Point getSouradnice() {
        return souradnice;
    }

    public void setSouradnice(Point souradnice) {
        this.souradnice = souradnice;
    }

    public int getOhodnoceni() {
        return ohodnoceni;
    }

    public void setOhodnoceni(int ohodnoceni) {
        this.ohodnoceni = ohodnoceni;
    }

    @Override
    public int CompareXY(IKlic klic1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Mesto getPredchudce() {
        return predchudce;
    }

    public void setPredchudce(Mesto predchudce) {
        this.predchudce = predchudce;
    }

    @Override
    public String toString() {
        return "Mesto{" + "nazev=" + nazev + ", souradnice=" + souradnice + '}';
    }

    @Override
    public String getKey() {
        return this.getNazev();
    }

    @Override
    public int compareTo(Mesto o) {
        return Integer.compare(ohodnoceni, o.getOhodnoceni());
    }

    public void vlozNaslednika(String nazev) {
       mapaNasledniku.add(nazev);
    }

    public boolean odeberNaslednika(String nazev) {
       return mapaNasledniku.remove(nazev);
    }
    
    public Set<String> getNasledniky(){
        return mapaNasledniku;
    }

   
    
   
}
