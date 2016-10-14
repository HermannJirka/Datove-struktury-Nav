/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmy;

import AbstrakniStruktury.Graf;
import Data.IMesto;
import Data.ISilnice;
import Data.Mesto;
import Data.Silnice;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import objekty.IKlic;

/**
 *
 * @author George
 */
public class Mapa implements IMapa, Serializable {

    private Graf<IMesto, ISilnice> graf;

    public Mapa() {
        graf = new Graf<>();
    }

    @Override
    public int getSize() {
        return graf.getSize();
    }

    @Override
    public void Zrus() {
        graf.Zrus();
    }

    @Override
    public boolean pridejHranu(IMesto mestoOd, IMesto mestoDo, ISilnice silnice) {
        if (silnice != null) {
            boolean vlozeno;
            vlozeno = graf.addHranu(mestoOd, mestoDo, silnice);
            if (vlozeno == true) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean pridejVrchol(IMesto mesto) {
        if (mesto != null) {
            graf.addVrchol(mesto);
            return true;
        }
        return false;
    }

    @Override
    public IMesto najdiMesto(IKlic klic) {
        IMesto mesto = null;
        if (klic != null) {
            mesto = (IMesto) graf.najdiVrchol(klic);
            return mesto;
        }
        return null;
    }

    @Override
    public LinkedList<ISilnice> vratSeznamSilnic(IMesto mesto) {
        if (mesto != null) {
            return graf.vratSeznamHran(mesto);
        }
        return null;
    }

    @Override
    public LinkedList<ISilnice> dejSeznamVsechSilnic() {
        Iterator<IMesto> itM = graf.iteratorVrcholu();
        LinkedList<ISilnice> seznamSilnic;
        Set set = new HashSet();
        while (itM.hasNext()) {
            Mesto m = (Mesto) itM.next();
            seznamSilnic = graf.vratSeznamHran(m);
            while (!seznamSilnic.isEmpty()) {
                set.add(seznamSilnic.removeFirst());
            }
        }
        return new LinkedList(set);
    }

    @Override
    public ISilnice najdiSilnici(IMesto mestoOd, IMesto mestoDo) {
        ISilnice silnice = null;
        if (mestoDo != null && mestoOd != null) {
            silnice = graf.najdiHranu(mestoOd, mestoDo);
            return silnice;
        }
        return null;
    }

    @Override
    public Iterator<IMesto> dejIteratorMest() {
        return graf.iteratorVrcholu();
    }

    @Override
    public ISilnice smazSilnici(IMesto mestoOd, IMesto mestoDo) {
        ISilnice silnice = null;
        if (mestoDo != null && mestoOd != null) {
            silnice = graf.smazaniHran(mestoOd, mestoDo);
            return silnice;
        }
        return null;
    }

    @Override
    public IMesto smazMesto(IKlic mesto) {
        return graf.smazaniVrcholu(mesto);
    }

    @Override
    public IMesto upravMesto(IMesto mesto, IMesto data) {
        return graf.upravVrchol(mesto,data);
    }

    @Override
    public ISilnice upravSilnic(IMesto mestoOd, IMesto mestoDo, ISilnice silnice) {
        Silnice pom;
        if (mestoDo != null && mestoOd != null) {
            pom = (Silnice) graf.upravHranu(mestoOd, mestoDo, silnice);
            return pom;
        }
        return null;
    }

    @Override
    public IMesto upravMesto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
