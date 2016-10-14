/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import AbstrakniStruktury.GridControllBlock;
import AbstrakniStruktury.GridFile;
import Data.ByteFile;
import Data.Mesto;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import objekty.GridBlock;
import objekty.IGridBlock;
import objekty.IKlicGrid;

/**
 *
 * @author George
 */
public class MainBlockTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Mesto m = new Mesto("A", new Point(500, 500));
        Mesto m1 = new Mesto("B", new Point(400, 202));
        Mesto m2 = new Mesto("C", new Point(300, 535));
        Mesto m3 = new Mesto("D", new Point(200, 432));
        Mesto m4 = new Mesto("E", new Point(350, 862));
        Mesto m5 = new Mesto("F", new Point(650, 152));
        Mesto m6 = new Mesto("G", new Point(230, 300));
        Mesto m7 = new Mesto("D", new Point(563, 268));
       
        GridFile gf = new GridFile(2);
        gf.addDataToGrid(m);
        gf.addDataToGrid(m1);
        gf.addDataToGrid(m2);
        gf.addDataToGrid(m3);
        gf.addDataToGrid(m4);
        gf.addDataToGrid(m5);
        gf.addDataToGrid(m6);
        gf.addDataToGrid(m7);
        
//        bt.ulozDoSouboruControlniBlock(gf.getGridFileHash(), gf.getVektorX(), gf.getVektorY(), gf.getPocitadlo());
//        GridControllBlock gcb = bt.nactiZeSouboruControllBlock();
//        bt.ulozBlockDoSouboru(gb1, 0);
//        bt.ulozBlockDoSouboru(gb, 1);
//        GridBlock gbsaa = bt.nactiBlokZeSouboru(1);
//    
        
        LinkedList list = gf.intervalFindVrchol(0, 0, 700, 700);
        IKlicGrid klic = gf.najdiVrchol(400, 202);
    }
}
