/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstrakniStruktury;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import objekty.IKlic;

/**
 *
 * @author George
 * @param <V>
 */
public class Graf<V extends IKlic, H> implements Serializable {

    private HashMap<Object, Vrchol> mapaVrcholu;

    public Graf() {
        this.mapaVrcholu = new HashMap<>();
    }

    public boolean addVrchol(IKlic data) {
        if (data != null) {
            mapaVrcholu.put(data.getKey(), new Vrchol(data));

            return true;
        }
        return false;
    }

    public boolean addHranu(IKlic zVrcholu, IKlic doVrcholu, H data) {
        Vrchol v1, v2;
        v1 = najdiVrcholPrivate(zVrcholu);
        v2 = najdiVrcholPrivate(doVrcholu);
        if (v1 != null && v2 != null) {
            Hrana h = new Hrana(data, v1, v2);
            v1.getSeznamHran().add(h);
            Hrana h2 = new Hrana(data, v2, v1);
            v2.getSeznamHran().add(h2);
            return true;
        }
        return false;
    }

    public V upravVrchol(IKlic klic, V data) {
       Vrchol v1;
        if (data != null) {
            v1 = (Vrchol) najdiVrcholPrivate(klic);
            v1.setData(data);
            return v1.getData();
        }
        return null;
    }

    public H smazaniHran(IKlic zVrcholu, IKlic doVrcholu) {
        Vrchol v1, v2;
        v1 = najdiVrcholPrivate(zVrcholu);
        v2 = najdiVrcholPrivate(doVrcholu);
        for (Hrana h : v1.getSeznamHran()) {
            if (h.getDoVrcholu() == (v2)) {
                v1.getSeznamHran().remove(h);
                break;
            }
        }
        for (Hrana h : v2.getSeznamHran()) {
            if (h.getDoVrcholu() == v1) {
                v2.getSeznamHran().remove(h);
                return h.getData();
            }
        }
        return null;
    }

    public V smazaniVrcholu(IKlic v) {
        Vrchol v1, v2;
        IKlic k1, k2;
        if (v != null) {
            v1 = najdiVrcholPrivate(v);
            for (int i = 0; i < v1.getSeznamHran().size(); i++) {
                v2 = v1.getSeznamHran().get(i).getDoVrcholu();
                System.out.println(v2.getData().toString());
                k1 = v1.getData();
                k2 = v2.getData();
                smazaniHran(k1, k2);

            }
            return mapaVrcholu.remove(v.getKey()).getData();
        }
        return null;
    }

   

    private Vrchol najdiVrcholPrivate(IKlic v) {
        return mapaVrcholu.get(v.getKey());
    }

    public IKlic najdiVrchol(IKlic v) {
        Vrchol vrchol = mapaVrcholu.get(v.getKey());
        if (vrchol != null) {
            return vrchol.getData();
        }
        return null;
    }

    private Hrana najdiHranuPrivate(IKlic zVrcholu, IKlic doVrcholu) {
        Vrchol v1 = najdiVrcholPrivate(zVrcholu);
        Vrchol v2 = najdiVrcholPrivate(doVrcholu);
        for (Hrana h : v1.getSeznamHran()) {
            if (h.getDoVrcholu() == v2) {
                return h;
            }
        }
        return null;
    }

    public H najdiHranu(IKlic zVrcholu, IKlic doVrcholu) {
        Hrana h = najdiHranuPrivate(zVrcholu, doVrcholu);
        if (h != null) {
            return h.getData();
        }
        return null;
    }

    public H upravHranu(IKlic zVrcholu, IKlic doVrcholu, H data) {
        Vrchol v1 = najdiVrcholPrivate(zVrcholu);
        Vrchol v2 = najdiVrcholPrivate(doVrcholu);
        Hrana hrana;
        for (Hrana h : v1.getSeznamHran()) {
            if (h.getDoVrcholu() == (v2)) {
                hrana = new Hrana(data, v1, v2);
                v1.getSeznamHran().remove(h);
                v1.getSeznamHran().add(hrana);
                break;
            }
        }
        for (Hrana h : v2.getSeznamHran()) {
            if (h.getDoVrcholu() == v1) {
                hrana = new Hrana(data, v2, v1);
                v2.getSeznamHran().remove(h);
                v2.getSeznamHran().add(hrana);
                return data;
            }
        }
        return null;

    }

    public Iterator<V> iteratorVrcholu() {
        Iterator<V> it = new Iterator<V>() {
            Iterator itV = mapaVrcholu.values().iterator();

            @Override
            public boolean hasNext() {
                return itV.hasNext();
            }

            @Override
            public V next() {
                return ((Vrchol) itV.next()).getData();
            }
        };
        return it;
    }

    public LinkedList<H> vratSeznamHran(IKlic klic) {
        Vrchol v = najdiVrcholPrivate(klic);
        LinkedList<H> ret = new LinkedList<>();
        for (Hrana h : v.getSeznamHran()) {
            ret.add(h.getData());
        }
        return ret;
    }

    public LinkedList vratSeznamVsechHran() {
        LinkedList<Hrana> ret = new LinkedList<>();
        Iterator<V> it = this.iteratorVrcholu();
        Set set = new HashSet();
        while (it.hasNext()) {
            Vrchol next = (Vrchol) it.next();
            ret = (LinkedList<Hrana>) next.getSeznamHran();
            while (!ret.isEmpty()) {
                set.add(ret.removeFirst());
            }
        }
        return new LinkedList(set);
    }

    public void Zrus() {
        mapaVrcholu.clear();
    }

    private class Vrchol implements Serializable {

        V data;
        LinkedList<Hrana> seznamHran;

        public Vrchol(IKlic data) {
            this.seznamHran = new LinkedList<Hrana>();
            this.data = (V) data;
        }

        public V getData() {
            return data;
        }

        public void setData(V data) {
            this.data = data;
        }

        public LinkedList<Hrana> getSeznamHran() {
            return seznamHran;
        }

        public void setSeznamHran(LinkedList<Hrana> seznamHran) {
            this.seznamHran = seznamHran;
        }

    }

    private class Hrana implements Serializable {

        H data;
        Vrchol zVrcholu, doVrcholu;

        public Hrana(H data, Vrchol zVrcholu, Vrchol doVrcholu) {
            this.data = data;
            this.zVrcholu = zVrcholu;
            this.doVrcholu = doVrcholu;
        }

        public H getData() {
            return data;
        }

        public void setData(H data) {
            this.data = data;
        }

        public Vrchol getzVrcholu() {
            return zVrcholu;
        }

        public void setzVrcholu(Vrchol zVrcholu) {
            this.zVrcholu = zVrcholu;
        }

        public Vrchol getDoVrcholu() {
            return doVrcholu;
        }

        public void setDoVrcholu(Vrchol doVrcholu) {
            this.doVrcholu = doVrcholu;
        }
    }

    public int getSize() {
        return mapaVrcholu.size();
    }
}
