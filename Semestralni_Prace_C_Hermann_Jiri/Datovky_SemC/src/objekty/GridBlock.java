/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekty;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author George
 */
public class GridBlock<E extends IKlicGrid> implements IGridBlock<E>,Serializable {

    private LinkedList<E> block;
    private int velikost;
    private int indexBlock;
    private boolean jeUzavren;

    public LinkedList<E> getBlock() {
        return block;
    }
    
    
    @Override
    public boolean isJeUzavren() {
        return jeUzavren;
    }

    public void setJeUzavren(boolean jeUzavren) {
        this.jeUzavren = jeUzavren;
    }

    public GridBlock(int velikost) {
        this.block = new LinkedList<>();
        this.velikost = velikost;
        
    }

    @Override
    public boolean vlozDataDoBlocku(E data) {
        if (block.size() < velikost) {
            block.add(data);
            return true;
        } else {
            this.jeUzavren = true;
            return false;
        }
    }

    @Override
    public E odeberZBloku(E data) {
        if (block != null) {
            for (int i = 0; i < block.size(); i++) {
                if (data == block.get(i)) {
                    jeUzavren = false;
                    return block.remove(i);

                }
            }

        }
        return null;
    }

    @Override
    public int getIndexBlock() {
        return indexBlock;
    }

    @Override
    public void setIndexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
    }

    @Override
    public String toString() {
        return "GridBlock{" + " velikost=" + velikost + ", indexBlock=" + indexBlock + ", jeUzavren=" + jeUzavren + ", maxPoint=" + '}';
    }
    public static byte[] prevedNaBajty(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object prevedNaObjekt(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    
    public int getVelikostZaznamu() throws IOException{
        return prevedNaBajty(block.get(0)).length;
    }

    public int getVelikost() {
        return velikost;
    }

    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }
    

}
