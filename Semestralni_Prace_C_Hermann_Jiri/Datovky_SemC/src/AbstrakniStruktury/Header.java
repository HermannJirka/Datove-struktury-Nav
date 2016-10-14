/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.io.Serializable;

/**
 *
 * @author George
 */
public class Header implements IHeader{
   private int pocetBloku;
   private int velikostBloku;

    public Header(int pocetBloku, int velikostBloku) {
        this.pocetBloku = pocetBloku;
        this.velikostBloku = velikostBloku;
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
    public int getVelikostBloku() {
        return velikostBloku;
    }

    @Override
    public void setVelikostBloku(int velikostBloku) {
        this.velikostBloku = velikostBloku;
    }
   
   
}
