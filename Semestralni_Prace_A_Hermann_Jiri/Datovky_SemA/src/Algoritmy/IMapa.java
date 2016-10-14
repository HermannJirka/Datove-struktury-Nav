/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmy;

import Data.IMesto;
import Data.ISilnice;
import Data.Silnice;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import objekty.IKlic;

/**
 *
 * @author George
 */
public interface IMapa extends Serializable{
    void Zrus();
    boolean pridejHranu(IMesto mestoOd,IMesto mestoDo,ISilnice silnice);
    boolean pridejVrchol(IMesto mesto);
    IMesto najdiMesto(IKlic klic);
    LinkedList<ISilnice> vratSeznamSilnic(IMesto mesto);
    LinkedList<ISilnice> dejSeznamVsechSilnic();
    ISilnice najdiSilnici(IMesto mestoOd, IMesto mestoDo);
    ISilnice upravSilnic(IMesto mestoOd,IMesto mestoDo,ISilnice silnice);
    Iterator<IMesto> dejIteratorMest();
    ISilnice smazSilnici(IMesto mestoOd, IMesto mestoDo);
    IMesto smazMesto(IKlic mesto);
    IMesto upravMesto();
    int getSize();
    IMesto upravMesto(IMesto klic,IMesto mesto);
}
