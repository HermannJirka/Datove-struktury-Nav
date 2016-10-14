/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class Silnice implements ISilnice, Serializable {

    private int ohodnoceni;
    private boolean uzavreno;
    private String nazevSilnice;
    private IMesto zMesta, doMesta;

    public Silnice(int ohodnoceni, boolean uzavreno, String nazevSilnice, IMesto mestoOD, IMesto mestoDo) {
        this.ohodnoceni = ohodnoceni;
        this.uzavreno = uzavreno;
        this.nazevSilnice = nazevSilnice;
        this.zMesta = mestoOD;
        this.doMesta = mestoDo;
    }

    public int getOhodnoceni() {
        return ohodnoceni;
    }

    public void setOhodnoceni(int ohodnoceni) {
        this.ohodnoceni = ohodnoceni;
    }

    public boolean getUzavreno() {
        return uzavreno;
    }

    public void setUzavreno(boolean uzavreno) {
        this.uzavreno = uzavreno;
    }

    public String getNazevSilnice() {
        return nazevSilnice;
    }

    public void setNazevSilnice(String nazevSilnice) {
        this.nazevSilnice = nazevSilnice;
    }

    public IMesto getzMesta() {
        return zMesta;
    }

    public void setzMesta(IMesto zMesta) {
        this.zMesta = zMesta;
    }

    public IMesto getDoMesta() {
        return doMesta;
    }

    public void setDoMesta(IMesto doMesta) {
        this.doMesta = doMesta;
    }

    @Override
    public String toString() {
        return "Silnice{" + "ohodnoceni=" + ohodnoceni + ", uzavreno=" + uzavreno + ", nazevSilnice=" + nazevSilnice + '}';
    }

    

    

}
