/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import AbstrakniStruktury.GridControllBlock;
import AbstrakniStruktury.Header;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import objekty.GridBlock;
import objekty.IKlicGrid;

/**
 *
 * @author George
 */
public class ByteFile {

    private int velikostBlocku, velikostControlBlocku;
    private static String cesta = "C:\\Users\\George\\Documents\\NetBeansProjects\\Semestralni_Prace_C_Hermann_Jiri\\Datovky_SemC\\semC.txt";
    private RandomAccessFile file;
    private GridControllBlock controllBlock;
    private int pocitadlo = 0;
    private int velikostHlavicky, velikostBloku;
    private boolean prvniblock = true;

    public ByteFile() throws FileNotFoundException {
        file = new RandomAccessFile(cesta, "rw");
    }

    public int getVelikostControlBlocku() {
        return velikostControlBlocku;
    }

    public void setVelikostControlBlocku(int velikostControlBlocku) {
        this.velikostControlBlocku = velikostControlBlocku;
    }

    public int getVelikostHlavicky() {
        return velikostHlavicky;
    }

    public void setVelikostHlavicky(int velikostHlavicky) {
        this.velikostHlavicky = velikostHlavicky;
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

    public Header nactiHlavickuZeSouboru() throws FileNotFoundException, IOException, ClassNotFoundException {
        file = new RandomAccessFile(cesta, "rw");
        Header pomHl = null;
        byte[] hl = new byte[getVelikostHlavicky()];
        file.seek(0);
        System.out.println(file.getFilePointer() + " nacti hlavicka ");
        file.read(hl);
        pomHl = (Header) prevedNaObjekt(hl);
        file.close();
        return pomHl;
    }

    public void ulozBlockDoSouboru(GridBlock blok, int pointer) throws IOException, FileNotFoundException, ClassNotFoundException {
        GridControllBlock gcb;
        Header header;
        
        header = nactiHlavickuZeSouboru();
        gcb = nactiZeSouboruControllBlock();
        byte[] poleBlock = prevedNaBajty(blok);

        velikostBlocku = blok.getVelikostZaznamu() * blok.getVelikost();
        // nastaveni jen pri novem bloku
        header.setPocetBloku(gcb.getPocetBloku());
        header.setVelikostBloku(velikostBlocku);

       

        byte[] poleGCB = prevedNaBajty(gcb);
        byte[] poleHead = prevedNaBajty(header);

        setVelikostControlBlocku(poleGCB.length);
      

        file = new RandomAccessFile(cesta, "rw");
        file.seek(0);
        System.out.println(file.getFilePointer() + " uloz block");
        file.write(poleHead);
        System.out.println(file.getFilePointer() + " uloz block");
        file.seek(0);
        file.seek(getVelikostHlavicky() + velikostBlocku * pointer);
        System.out.println(file.getFilePointer() + " uloz block");
        file.write(poleBlock);
        file.seek(0);
        file.seek(getVelikostHlavicky() + velikostBlocku * header.getPocetBloku());
        System.out.println(file.getFilePointer() + " uloz block");
        file.write(poleGCB);
        file.close();
    }

    public GridBlock nactiBlokZeSouboru(int index) throws IOException, FileNotFoundException, ClassNotFoundException {
        GridBlock gb = null;
        Header head = nactiHlavickuZeSouboru();
        GridControllBlock cgb = nactiZeSouboruControllBlock();

        byte[] poleGb = new byte[head.getVelikostBloku()];
        file = new RandomAccessFile(cesta, "rw");
        file.seek(0);
        System.out.println(file.getFilePointer() + " nacti GB block");
        file.seek(getVelikostHlavicky() + index * head.getVelikostBloku());
        System.out.println(file.getFilePointer() + " nacti GB block");
        file.read(poleGb);
        file.close();
        gb = (GridBlock) prevedNaObjekt(poleGb);

        return gb;
    }

    public void ulozDoSouboruControlniBlock(HashMap<Point, Integer> gridFile, LinkedList<Double> x, LinkedList<Double> y, int pocetBlocku) throws IOException {
        GridControllBlock gcb = new GridControllBlock(gridFile, x, y, pocetBlocku);
        Header header = new Header(pocetBlocku, 0);
        byte[] poleGCB = prevedNaBajty(gcb);
        byte[] poleHead = prevedNaBajty(header);
        setVelikostHlavicky(poleHead.length);
        setVelikostControlBlocku(poleGCB.length);
        file = new RandomAccessFile(cesta, "rw");
        file.write(poleHead);
        System.out.println(file.getFilePointer() + " uloz GCB a Head");
        file.write(poleGCB);
        file.close();
    }
    public void updateDoSouboruControlniBlock(HashMap<Point, Integer> gridFile, LinkedList<Double> x, LinkedList<Double> y, int pocetBlocku) throws IOException, FileNotFoundException, ClassNotFoundException {
        GridControllBlock gcb = nactiZeSouboruControllBlock();
        gcb.setGridFile(gridFile);
        gcb.setVektorX(x);
        gcb.setVektorY(y);
        gcb.setPocetBloku(pocetBlocku);
        
        Header header = nactiHlavickuZeSouboru();
        header.setPocetBloku(pocetBlocku);
        byte[] poleGCB = prevedNaBajty(gcb);
        byte[] poleHead = prevedNaBajty(header);
        setVelikostControlBlocku(poleGCB.length);
        file = new RandomAccessFile(cesta, "rw");
        file.seek(0);
        System.out.println(file.getFilePointer() + " uloz update GCB a Head");
        file.write(poleHead);
        System.out.println(file.getFilePointer() + " uloz update GCB a Head");
        file.seek(0);
        System.out.println(file.getFilePointer() + " uloz update GCB a Head");
        file.seek(getVelikostHlavicky()+header.getPocetBloku()*header.getVelikostBloku());
        System.out.println(file.getFilePointer() + " uloz update GCB a Head");
        file.write(poleGCB);
        file.close();
    }

    public GridControllBlock nactiZeSouboruControllBlock() throws FileNotFoundException, IOException, ClassNotFoundException {
        GridControllBlock gcb;
        Header head = nactiHlavickuZeSouboru();

        byte[] poleGCB = new byte[getVelikostControlBlocku()];
        file = new RandomAccessFile(cesta, "rw");
        int pocetBloku = head.getPocetBloku();
        int velikostBloku = head.getVelikostBloku();
        file.seek(getVelikostHlavicky() + velikostBloku * pocetBloku);
        System.out.println(file.getFilePointer());
        file.read(poleGCB);
        file.close();
        gcb = (GridControllBlock) prevedNaObjekt(poleGCB);

        return gcb;
    }
  
   

//    public void ulozDoSouboruBlock(GridBlock block) throws IOException, ClassNotFoundException {
//
//        controllBlock = nactiZeSouboruControlBlock();
//
//        if (controllBlock != null) {
//            if (controllBlock.getPoleAdresBlocku().isEmpty()) {
//                file = new RandomAccessFile(cesta, "rw");
//                byte[] poleBytu = prevedNaBajty(block);
//                long adresaC = 0;
//                controllBlock.addAdressToArray(adresaC);
//                controllBlock.addVelikostBloku(poleBytu.length, adresaC);
//                pocitadlo++;
//
//                byte[] conBlok = prevedNaBajty(controllBlock);
//                setVelikostControlBlocku(conBlok.length);
//                // prepsat starej control za novy
//                System.out.println(file.getFilePointer());
//                file.seek(0);
//                System.out.println(file.getFilePointer());
//                file.write(conBlok);
//                file.seek(getVelikostControlBlocku());
//                System.out.println(file.getFilePointer());
//                file.write(poleBytu);
//                System.out.println(file.getFilePointer());
//                file.close();
//            } else {
//                file = new RandomAccessFile(cesta, "rw");
//                byte[] poleBytu = prevedNaBajty(block);
//                long adresaNewBlock = controllBlock.getBlockAdress(pocitadlo - 1);
//                int velikost = controllBlock.getVelikostBlocku(adresaNewBlock);
//                long staraAdr = getVelikostControlBlocku();
//                adresaNewBlock += velikost;
//                controllBlock.addAdressToArray(adresaNewBlock);
//                controllBlock.addVelikostBloku(poleBytu.length, adresaNewBlock);
//                pocitadlo++;
//
//                byte[] controlBlok = prevedNaBajty(controllBlock);
//                setVelikostControlBlocku(controlBlok.length);
//                // prepsani 
//                for (int i = 0; i < controllBlock.getPoleAdresBlocku().size() - 1; i++) {
//                    GridBlock gb = nactiZeSouboruBlockAdd(i, staraAdr);
//                    long adr = controllBlock.getBlockAdress(i);
//                    int vel = controllBlock.getVelikostBlocku(adr);
//                    byte[] bl = new byte[vel];
//                    adr += getVelikostControlBlocku();
//                    file = new RandomAccessFile(cesta, "rw");
//                    file.seek(adr);
//                    file.write(bl);
//                }
//                adresaNewBlock += getVelikostControlBlocku();
//                file.seek(adresaNewBlock);
//                file.write(poleBytu);
//                file.seek(0);
//                file.write(controlBlok);
//                file.close();
//            }
//        }
//    }
//    public GridControllBlock nactiZeSouboruControlBlock() throws IOException, ClassNotFoundException {
//        file = new RandomAccessFile(cesta, "r");
//        byte[] b = new byte[getVelikostControlBlocku()];
//        file.read(b);
//        ByteArrayInputStream in = new ByteArrayInputStream(b);
//        ObjectInputStream is = new ObjectInputStream(in);
//        file.close();
//        return (GridControllBlock) is.readObject();
//
//    }
//
//    public GridBlock nactiZeSouboruBlock(int pozice) throws IOException, ClassNotFoundException {
//
//        GridControllBlock gb = nactiZeSouboruControlBlock();
//        long adress = gb.getBlockAdress(pozice);
//        int velikost = gb.getSizeOfBlock(adress);
//        byte[] block = new byte[velikost];
//        adress += getVelikostControlBlocku();
//        file = new RandomAccessFile(cesta, "r");
//        file.seek(adress);
//        file.read(block);
//        ByteArrayInputStream in = new ByteArrayInputStream(block);
//        ObjectInputStream is = new ObjectInputStream(in);
//        file.close();
//        return (GridBlock) is.readObject();
//    }
//    public GridBlock nactiZeSouboruBlockAdd(int pozice,Long staraPozice) throws IOException, ClassNotFoundException {
//
//        GridControllBlock gb = nactiZeSouboruControlBlock();
//        long adress = gb.getBlockAdress(pozice);
//        int velikost = gb.getSizeOfBlock(adress);
//        byte[] block = new byte[velikost];
//        adress += staraPozice;
//        file = new RandomAccessFile(cesta, "r");
//        file.seek(adress);
//        file.read(block);
//        ByteArrayInputStream in = new ByteArrayInputStream(block);
//        ObjectInputStream is = new ObjectInputStream(in);
//        file.close();
//        return (GridBlock) is.readObject();
//    }
}
