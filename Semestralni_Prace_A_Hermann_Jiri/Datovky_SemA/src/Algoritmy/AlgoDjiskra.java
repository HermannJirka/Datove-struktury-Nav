/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmy;

import Data.IMesto;
import Data.ISilnice;
import Data.Mesto;
import Data.Silnice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import javafx.print.Collation;
import objekty.IKlic;

/**
 *
 * @author George
 */
public class AlgoDjiskra {

    PriorityQueue<IMesto> fronta = new PriorityQueue<>();
    private IMapa mapa;
    private int pocetMest;

    public AlgoDjiskra(IMapa mapa, Mesto a) {
        this.mapa = mapa;
        a.setOhodnoceni(0);
        fronta.add(a);
        this.pocetMest = mapa.getSize();
    }

//    public AlgoDjiskra(IMapa mapa, Mesto a, Mesto b, int ohodA, int ohodB) {
//        this.mapa = mapa;
//        a.setOhodnoceni(ohodA);
//        b.setOhodnoceni(ohodB);
//        fronta.add(a);
//        fronta.add(b);
//    }
    public void prepocitejVzdalenost() {
        while (!fronta.isEmpty()) {
            Mesto mesto = (Mesto) fronta.poll();
            Silnice silnice;
            for (Iterator<ISilnice> iterator = mapa.vratSeznamSilnic(mesto).iterator(); iterator.hasNext();) {
                silnice = (Silnice) iterator.next();
                if (silnice.getUzavreno() == false) {
                    Mesto pomocna = (Mesto) silnice.getDoMesta();
                    if (pomocna == mesto) {
                        pomocna = (Mesto) silnice.getzMesta();
                    }

                    int ohodnoceniSilnice = silnice.getOhodnoceni();
                    int ohodMesto = mesto.getOhodnoceni() + ohodnoceniSilnice;
                    if (ohodMesto < pomocna.getOhodnoceni()) {
                        fronta.remove(pomocna);
                        pomocna.setOhodnoceni(ohodMesto);

                        if (pomocna.getPredchudce() != null) {
                            pomocna.getPredchudce().odeberNaslednika(pomocna.getKey());
                        }
                        pomocna.setPredchudce(mesto);
                        mesto.vlozNaslednika(pomocna.getKey());

                        fronta.add(pomocna);
                    }
                }
            }

        }
    }

    public String vypisMest(Mesto vychozi) {
        String ret = "";
        Queue<String> seznam = new LinkedList();
        seznam.add(mapa.najdiMesto(vychozi).getKey());
        seznam.add("|");
        seznam.add("\n");
        while (!seznam.isEmpty()) {
            String s = seznam.poll();
            switch (s) {
                case "|":
                    ret += s;
                    break;
                case "\n":
                    ret += s;
                    if (seznam.size() > 1) {
                        seznam.add(s);
                    }
                    break;
                default:
                    ret += s + ", ";
                    Mesto pom = (Mesto) mapa.najdiMesto(new Mesto(s, null));
                    for (String str : pom.getNasledniky()) {
                        seznam.add(str);
                    }
                    seznam.add("|");
                    break;
            }
        }
        return ret;
    }

    public String[][] maticeMest() {
        String[][] maticeMesta = new String[pocetMest + 1][pocetMest + 1];
        List<Mesto> mesta = new ArrayList<>();
        for (Iterator<IMesto> iterator = mapa.dejIteratorMest(); iterator.hasNext();) {
            mesta.add((Mesto) iterator.next());
        }
        Collections.sort(mesta, new MyComparer());

        int i = 1;
        for (Mesto m : mesta) {
            maticeMesta[0][i] = m.getKey();
            maticeMesta[i][0] = m.getKey();
            i++;
        }

//        i = 1;
//        int j = 1;
//        for (Mesto m : mesta) {
//            for (String s : m.getNasledniky()) {
//                while (true) {
//                    if (s.equalsIgnoreCase(maticeMesta[0][i])) {
//                        maticeMesta[j][i] = s;
//                        break;
//                    }
//                    i++;
//                }
//            }
//            i = 1;
//            j++;
//        }
        // vypis predchudcu
        for (Mesto m : mesta) {
            if (m.getPredchudce() != null) {
                for (int k = 1; k < maticeMesta.length; k++) {
                    if (maticeMesta[0][k].equals(m.getNazev())) {
                        for (int l = 1; l < maticeMesta.length; l++) {
                            if (maticeMesta[l][0].equals(m.getPredchudce().getNazev())) {
                                maticeMesta[k][l] = m.getPredchudce().getNazev();
                            }
                        }
                    }
                }
            }
        }
        // vypis nasledniku 
//        Set<String> pom;
//        for (Mesto m : mesta) {
//            if (!m.getNasledniky().isEmpty()) {
//                pom = m.getNasledniky();
//                for (int j = 1; j < maticeMesta.length; j++) {
//                    if (maticeMesta[j][0].equals(m.getNazev())) {
//                        for (int k = 1; k < maticeMesta.length; k++) {
//                            for (Iterator iterator = pom.iterator(); iterator.hasNext();) {
//                                String next = (String) iterator.next();
//                                String p = maticeMesta[j][k];
//                                if (maticeMesta[0][k].equals(next)) {
//                                    maticeMesta[j][k] = next;
//                                }
//
//                            }
//                        }
//                    }
//                }
//            }
//        }

        //od koncovyho dobu pres predchudce se dostat k pozadovanymu bodu(redchudce null), v podstate vzdy budes fillovat sloupec
        return maticeMesta;
    }

    public class MyComparer implements Comparator<IKlic> {

        @Override
        public int compare(IKlic o1, IKlic o2) {
            return o1.CompareString(o2);
        }
    }

    public void spocitejVektory() {

    }
}
