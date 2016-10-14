/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import objekty.IKlic;

/**
 *
 * @author George
 */
public interface IGraf<V extends IKlic, H> extends Serializable {

    void Zrus();

    boolean addHranu(IKlic zVrcholu, IKlic doVrcholu, H data);

    boolean addVrchol(IKlic data);

    int getSize();

    Iterator<V> iteratorVrcholu();

    H najdiHranu(IKlic zVrcholu, IKlic doVrcholu);

    IKlic najdiVrchol(IKlic v);

    H smazaniHran(IKlic zVrcholu, IKlic doVrcholu);

    V smazaniVrcholu(IKlic v);

    H upravHranu(IKlic zVrcholu, IKlic doVrcholu, H data);

    V upravVrchol(IKlic klic, V data);

    LinkedList<H> vratSeznamHran(IKlic klic);

    LinkedList vratSeznamVsechHran();
    
}
