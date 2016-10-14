/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import Data.IMesto;
import Data.Mesto;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import objekty.IKlic;

/**
 *
 * @author George
 */
public class GridIndex<D> implements IGridIndex<D> {

    private HashMap<Point, IMesto> gridIndex;
    private LinkedList<Double> radkovyVektor;
    private LinkedList<Double> sloupcovyVektor;
    private boolean jeX;
    private Point vrchol;
    private Point bodKlic;

    public GridIndex() {
        this.gridIndex = new HashMap<>();
        this.radkovyVektor = new LinkedList<>();
        this.sloupcovyVektor = new LinkedList<>();
        this.vrchol = null;
        this.jeX = true;
        this.bodKlic = new Point();

    }

   
//    public void addVrchol(Point bod, D data) {
//        double x, y, pomDoubleX = 0.0, pomDoubleY = 0.0;
//        Point bodPom, bodKlic;
//        D dataPom;
//        boolean jePrehozeno = false;
//
//        if (bod != null && data != null) {
//            if (gridIndex.isEmpty()) {
//                addPrvni(bod, data);
//            } else {
//                for (Iterator<Double> iterator = radkovyVektor.iterator(); iterator.hasNext();) {
//                    Double next = iterator.next();
//                    if (bod.getX() >= next) {
//                        pomDoubleX = next;
//                    }
//                }
//                for (Iterator<Double> iterator = sloupcovyVektor.iterator(); iterator.hasNext();) {
//                    Double next = iterator.next();
//                    if (bod.getY() >= next) {
//                        pomDoubleY = next;
//                    }
//                }
//                bodKlic = new Point();
//                bodKlic.setLocation(pomDoubleX, pomDoubleY);
//                Mesto mes = (Mesto) gridIndex.get(bodKlic);
//
//                if (mes != null) { // rez podle x
//                    Mesto pomM = (Mesto) gridIndex.get(bodKlic);
//                    if (jeX) {
//                        double rezX = (double) ((pomM.getSouradnice().getX() + bod.x) / 2);
//                        bodKlic.setLocation(rezX, pomDoubleY);
//                        for (Iterator<Double> iterator = sloupcovyVektor.iterator(); iterator.hasNext();) {
//                            Double next = iterator.next();
//                            Mesto m;
//                            Point pomXY = new Point();
//                            pomXY.setLocation(pomDoubleX, next);
//                            m = (Mesto) gridIndex.get(pomXY);
//                            if (m != null) {
//                                m = (Mesto) gridIndex.get(pomXY);
//                                if (m.getSouradnice().getX() > rezX) {
//                                    m = (Mesto) gridIndex.remove(pomXY);
//                                    Point newPom = new Point();
//                                    newPom.setLocation(rezX, next);
//                                    gridIndex.put(newPom, m);
////                                    bodKlic = pomXY;
//
//                                }
//
//                            }
//
//                        }
//                        gridIndex.put(bodKlic, (IMesto) data);
//                        radkovyVektor.add(rezX);
//                        Collections.sort(radkovyVektor);
//                        jeX = false;
//                    } else { // rez podle y
//                        double rezY = (double) ((pomM.getSouradnice().getY() + bod.y) / 2);
//                        bodKlic.setLocation(pomDoubleX, rezY);
//                        for (Iterator<Double> iterator = radkovyVektor.iterator(); iterator.hasNext();) {
//                            Double next = iterator.next();
//                            Mesto m;
//                            Point pomXY = new Point();
//                            pomXY.setLocation(next, pomDoubleY);
//                            m = (Mesto) gridIndex.get(pomXY);
//                            if (m != null) {
//                                //  m = (Mesto) mapa.get(pomXY);
//                                if (m.getSouradnice().getY() > rezY) {
//                                    System.out.println(m.toString());
//                                    m = (Mesto) gridIndex.remove(pomXY);
//                                    Point newPom = new Point();
//                                    newPom.setLocation(next, rezY);
//                                    gridIndex.put(newPom, m);
////                                    bodKlic = pomXY;
//                                }
//
//                            }
//
//                        }
//                        sloupcovyVektor.add(rezY);
//                        Collections.sort(sloupcovyVektor);
//                        gridIndex.put(bodKlic, (IMesto) data);
//                        this.jeX = true;
//
//                    }
//
//                } else {
//                    gridIndex.put(bodKlic, (IMesto) data);
//                }
//            }
//        }
//    }

  //  @Override
    
         private void addPrvni(Point bod, D data) {
        Point bodPom = null;
        radkovyVektor.add(0.0);
        sloupcovyVektor.add(0.0);
        bodPom = new Point(0, 0);
        gridIndex.put(bodPom, (IMesto) data);

    }

    @Override
    public void addVrcholFinal(Point bod, D data) {
        double radekX = 0, sloupecY = 0, rezX, rezY;
        Mesto nalezenoMesto = null, pomocnaMesto = null;
        Point nalezenKlic = new Point(), bodKLic = new Point();
        if (bod != null && data != null) {
            if (gridIndex.isEmpty()) {
                addPrvni(bod, data);
            } else {
                for (Iterator<Double> iterator = radkovyVektor.iterator(); iterator.hasNext();) {
                    double next = iterator.next();
                    if (bod.getX() > next) {
                        radekX = next;
                    }
                }
                for (Iterator<Double> iterator = sloupcovyVektor.iterator(); iterator.hasNext();) {
                    double next = iterator.next();
                    if (bod.getY() > next) {
                        sloupecY = next;
                    }
                }
                nalezenKlic.setLocation(radekX, sloupecY);
                nalezenoMesto = (Mesto) gridIndex.get(nalezenKlic);
                if (nalezenoMesto != null) {
                    if (jeX) { // rez podle x
                        rezX = ((bod.getX() + nalezenoMesto.getSouradnice().getX()) / 2);
                        bodKlic.setLocation(rezX, sloupecY); // vytvoreni souradnice rezu
                        for (Iterator<Double> iterator = sloupcovyVektor.iterator(); iterator.hasNext();) {
                            double next = iterator.next();
                            Point pomocnaXY = new Point();
                            pomocnaXY.setLocation(radekX, next);
                            pomocnaMesto = (Mesto) gridIndex.get(pomocnaXY);
                            if (pomocnaMesto != null && pomocnaMesto.getSouradnice().getX() > rezX) {
                                pomocnaMesto = (Mesto) gridIndex.remove(pomocnaXY);
                                pomocnaXY.setLocation(rezX, next);
                                gridIndex.put(pomocnaXY, pomocnaMesto);
                            }
                        }
                        if (bod.getX() > rezX) {
                            bodKLic.setLocation(rezX, sloupecY);
                            gridIndex.put(bodKLic, (IMesto) data);
                            radkovyVektor.add(rezX);
                        } else {

                            Point pomocna = new Point();
                            pomocna.setLocation(radekX, sloupecY);
                            gridIndex.put(pomocna, (IMesto) data);
                            radkovyVektor.add(rezX);
                        }
                        Collections.sort(radkovyVektor);
                        jeX = false;
                    } else { // rez podle y
                        rezY = ((bod.getY() + nalezenoMesto.getSouradnice().getY()) / 2);
                        bodKLic.setLocation(radekX, rezY);
                        for (Iterator<Double> iterator = radkovyVektor.iterator(); iterator.hasNext();) {
                            double next = iterator.next();
                            Point pomocnaXY = new Point();
                            pomocnaXY.setLocation(next, sloupecY);
                            pomocnaMesto = (Mesto) gridIndex.get(pomocnaXY);
                            if (pomocnaMesto != null && pomocnaMesto.getSouradnice().getY() > rezY) {
                                pomocnaMesto = (Mesto) gridIndex.remove(pomocnaXY);
                                pomocnaXY.setLocation(next, rezY);
                                gridIndex.put(pomocnaXY, pomocnaMesto);
                            }
                        }
                        if (bod.getY() > rezY) {
                            bodKLic.setLocation(radekX, rezY);
                            gridIndex.put(bodKLic, (IMesto) data);
                            sloupcovyVektor.add(rezY);
                        } else {
                            Point pomocna = new Point();
                            pomocna.setLocation(radekX, sloupecY);

                            gridIndex.put(pomocna, (IMesto) data);
                            sloupcovyVektor.add(rezY);
                        }
                        Collections.sort(sloupcovyVektor);

                        jeX = true;
                    }
                } else {
                    addMesto(nalezenKlic, data);
                }
            }
        }
    }

    @Override
    public void addMesto(Point bod, D data) {
        if (bod != null && data != null) {
            gridIndex.put(bod, (IMesto) data);
        }
    }

    @Override
    public IMesto findVrchol(Point vrchol) {
        double pomDoubleX = 0, pomDoubleY = 0;
        Point bodKlic;
        for (Iterator<Double> iterator = radkovyVektor.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (vrchol.getX() > next) {
                pomDoubleX = next;
            }
        }
        for (Iterator<Double> iterator = sloupcovyVektor.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (vrchol.getY() > next) {
                pomDoubleY = next;
            }
        }
        bodKlic = new Point();
        bodKlic.setLocation(pomDoubleX, pomDoubleY);
        Mesto mes = (Mesto) gridIndex.get(bodKlic);
        return (IMesto) mes;
    }

    @Override
    public IMesto updateVrchol(Point bod, D data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IMesto deleteVrchol(Point vrchol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<IMesto> intervalFindVrchol(double minBoundX, double minBoundY, double maxBoundX, double maxBoundY) {
        ArrayList<Double> vyrezX = new ArrayList<>();
        ArrayList<Double> vyrezY = new ArrayList<>();
        LinkedList<IMesto> mestaVIntervalu = new LinkedList<>();

        boolean prvniX = false;
        for (int i = 0; i < radkovyVektor.size(); i++) {
            Double next = radkovyVektor.get(i);

            if (next >= minBoundX && next <= maxBoundX) {
                vyrezX.add(next);
                if (!prvniX) {
                    if (i > 0) {
                        vyrezX.add(radkovyVektor.get(i - 1));
                    }
                    prvniX = true;
                }
            }
        }
        boolean prvniY = false;
        for (int i = 0; i < sloupcovyVektor.size(); i++) {
            Double next = sloupcovyVektor.get(i);

            if (next >= minBoundY && next <= maxBoundY) {
                vyrezY.add(next);
                if (!prvniY) {
                    if (i > 0) {
                        vyrezY.add(radkovyVektor.get(i - 1));
                    }
                    prvniY = true;
                }
            }
        }
        Collections.sort(vyrezX);
        Collections.sort(vyrezY);
        Point bodKlic = new Point(), bodMesto;
        Mesto pomMesto;
        for (int i = 0; i < vyrezX.size(); i++) {
            for (int j = 0; j < vyrezY.size(); j++) {
                bodKlic.setLocation(vyrezX.get(i), vyrezY.get(j));
                if (gridIndex.get(bodKlic) != null) {
                    pomMesto = (Mesto) gridIndex.get(bodKlic);
                    bodMesto = pomMesto.getSouradnice();
                    if (bodMesto.getX() >= minBoundX && bodMesto.getX() <= maxBoundX && bodMesto.getY() >= minBoundY && bodMesto.getY() <= maxBoundY) {
                        mestaVIntervalu.add(pomMesto);
                    }
                }
            }
        }
        return mestaVIntervalu;
    }

    @Override
    public boolean jePrazdny() {
        return gridIndex.isEmpty();
    }

    @Override
    public LinkedList<Double> listRezuX() {
        return this.radkovyVektor;
    }

    @Override
    public LinkedList<Double> listRezuY() {
        return this.sloupcovyVektor;
    }

    @Override
    public void Zrus() {
        gridIndex.clear();
    }

}
