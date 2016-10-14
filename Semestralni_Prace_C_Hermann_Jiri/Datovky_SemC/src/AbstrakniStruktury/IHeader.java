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
public interface IHeader extends Serializable {

    int getPocetBloku();

    int getVelikostBloku();

    void setPocetBloku(int pocetBloku);

    void setVelikostBloku(int velikostBloku);
    
}
