package AbstrakniStruktury;

import Data.ByteFile;
import Data.IMesto;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import objekty.GridBlock;
import objekty.IGridBlock;
import objekty.IKlicGrid;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author George
 */
public class GridFile<E extends IKlicGrid> implements IGridFile<E> {

    private LinkedList<Double> vektorX;
    private LinkedList<Double> vektorY;
    private int pocitadloBloku, indexBlocku;
    private LinkedList<GridBlock> listBlock;
    private int velikostBloku;
    private boolean jePrvniBlock = true, vytvoreno = false, jeX = true;
    private HashMap<Point, Integer> gridFileHash;
    private ByteFile byteFile;

    public GridFile(int velikostBloku) throws FileNotFoundException {
        this.vektorX = new LinkedList<>();
        this.vektorY = new LinkedList<>();
        this.pocitadloBloku = 0;
        this.indexBlocku = 0;
        this.velikostBloku = velikostBloku;
        this.listBlock = new LinkedList<>();
        this.gridFileHash = new HashMap<>();
        this.byteFile = new ByteFile();
    }

    @Override
    public void addDataToGrid(E data) throws IOException, FileNotFoundException, ClassNotFoundException {
        int pomVekX = 0, pomVekY = 0, indexGB;
        double rezX, rezY;
        GridBlock aktulniBlock = null;
        Point pomocPoint = new Point(), aktPoint = new Point();
        IKlicGrid pomocna = null;

        if (data != null) {
            if (jePrvniBlock) {
                GridBlock pomBlok = new GridBlock(velikostBloku);
                pomBlok.setIndexBlock(indexBlocku);
                pomBlok.vlozDataDoBlocku(data);
                pomocPoint.setLocation(0, 0);
                vektorX.add(0.0);
                vektorY.add(0.0);

                gridFileHash.put(pomocPoint, 0);
                pocitadloBloku++;
//                listBlock.add(pomBlok);
                byteFile.ulozDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);

                byteFile.ulozBlockDoSouboru(pomBlok, pomBlok.getIndexBlock());
                jePrvniBlock = false;

            } else {
                GridControllBlock cgb = byteFile.nactiZeSouboruControllBlock();
                this.gridFileHash = cgb.getGridFile();
                this.pocitadloBloku = cgb.getPocetBloku();
                this.vektorX = cgb.getVektorX();
                this.vektorY = cgb.getVektorY();
                for (int i = 0; i < vektorX.size(); i++) {
                    if (vektorX.get(i) < data.getSouradniceKlice().getX()) {
                        pomVekX = i;
                    }
                }
                for (int i = 0; i < vektorY.size(); i++) {
                    if (vektorY.get(i) < data.getSouradniceKlice().getY()) {
                        pomVekY = i;
                    }
                }
                aktPoint.setLocation(pomVekX, pomVekY);
                indexGB = gridFileHash.get(aktPoint);
                // TODO: for testing and debugging load
//                for (Iterator<GridBlock> iterator = listBlock.iterator(); iterator.hasNext();) {
//                    GridBlock next = iterator.next();
//                    if (indexGB == next.getIndexBlock()) {
//                        aktulniBlock = next;
//                    }
//                }
                aktulniBlock = byteFile.nactiBlokZeSouboru(indexGB);
                if (aktulniBlock != null) {
                    if (aktulniBlock.getBlock().size() == velikostBloku) {
                        Point p1 = new Point(), p2 = new Point();
                        p1.setLocation(pomVekX + 1, pomVekY);
                        p2.setLocation(pomVekX, pomVekY + 1);

                        if (gridFileHash.get(p1) != null && gridFileHash.get(p1) == indexGB) {
                            Point p = new Point();
                            p.setLocation(vektorX.get(pomVekX + 1), vektorY.get(pomVekY));
                            GridBlock pom = new GridBlock(velikostBloku);

                            indexBlocku++;
                            pom.setIndexBlock(indexBlocku);
                            pocitadloBloku++;
                            gridFileHash.put(p1, pom.getIndexBlock());
                            byteFile.updateDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);

                            LinkedList listAktBl = aktulniBlock.getBlock();
                            for (int i = 0; i < listAktBl.size(); i++) {
                                E vrchol = (E) listAktBl.get(i);
                                if (vrchol.getSouradniceKlice().getX() > p.getX() && vrchol.getSouradniceKlice().getY() > p.getY()) {
                                    E vrchP = (E) listAktBl.remove(i);
                                    pom.getBlock().add(vrchP);
                                }
                            }
                            if (data.getSouradniceKlice().getX() > p.getX() && data.getSouradniceKlice().getY() > p.getY()) {
                                pom.getBlock().add(data);
                            } else {
                                aktulniBlock.getBlock().add(data);
                            }

                            // TODO : Ukladani do souboru
//                            listBlock.add(aktulniBlock);
                            //update controll block 
                            byteFile.ulozBlockDoSouboru(pom, pom.getIndexBlock());
                            byteFile.ulozBlockDoSouboru(aktulniBlock, aktulniBlock.getIndexBlock());
                        } else if (gridFileHash.get(p2) != null && gridFileHash.get(p2) == indexGB) {
                            Point p = new Point();
                            p.setLocation(vektorX.get(pomVekX), vektorY.get(pomVekY+1));
                            GridBlock pom = new GridBlock(velikostBloku);
                            indexBlocku++;
                            pom.setIndexBlock(indexBlocku);
                            pocitadloBloku++;

                            gridFileHash.put(p2, pom.getIndexBlock());
                            byteFile.updateDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);

                            LinkedList listAktBl = aktulniBlock.getBlock();
                            for (int i = 0; i < listAktBl.size(); i++) {
                                E vrchol = (E) listAktBl.get(i);
                                if ( vrchol.getSouradniceKlice().getX() > p.getX() &&   vrchol.getSouradniceKlice().getY() > p.getY()) {
                                    E vrchP = (E) listAktBl.remove(i);
                                    pom.getBlock().add(vrchP);
                                }
                            }
                            if (data.getSouradniceKlice().getX() > p.getX() && data.getSouradniceKlice().getY() > p.getY()) {
                                pom.getBlock().add(data);
                            } else {
                                aktulniBlock.getBlock().add(data);
                            }
                            // TODO : Ukladani do souboru
//                            listBlock.add(aktulniBlock);
                            //update controll block 

                            byteFile.ulozBlockDoSouboru(pom, pom.getIndexBlock());
                            byteFile.ulozBlockDoSouboru(aktulniBlock, aktulniBlock.getIndexBlock());

                        } else {
                            aktulniBlock.setJeUzavren(true);
                            if (jeX) {
                                GridBlock newBlock;
                                double pomSou = 0;
                                int poc = 0, indexRezX = 0;
                                // secteni souradnicX pro spocitani rezu
                                for (Iterator iterator = aktulniBlock.getBlock().iterator(); iterator.hasNext();) {
                                    IKlicGrid next = (IKlicGrid) iterator.next();
                                    pomSou += next.getSouradniceKlice().getX();
                                    poc++;
                                }
                                // vytroreni rezu pro x
                                rezX = pomSou / poc;
                                rezX = (rezX + data.getSouradniceKlice().getX()) / 2;
                                newBlock = addNewBlockByX();
                                vektorX.add(rezX);
                                Collections.sort(vektorX);
                                // k nalezeni indexu rezu X
                                for (int i = 0; i < vektorX.size(); i++) {
                                    if (vektorX.get(i) == rezX) {
                                        indexRezX = i;
                                    }
                                }
                                Point p = new Point();
                                // predelat klice cele hasmapy na danem radku sloupci 
                                p.setLocation(indexRezX, pomVekY);
                                if (gridFileHash.get(p) != null) {
                                    int prvek = gridFileHash.get(p);
                                    Point prvN = new Point();
                                    prvN.setLocation(indexRezX, pomVekY - 1);
                                    if (gridFileHash.get(prvN) != null) {
                                        int in = gridFileHash.remove(prvN);
                                        Point prvN1 = new Point();
                                        prvN1.setLocation(indexRezX + 1, pomVekY - 1);
                                        gridFileHash.put(prvN1, in);
                                    }
                                    for (int i = indexRezX; i < vektorX.size() - 1; i++) {
                                        Point pc1 = new Point();
                                        pc1.setLocation(i, pomVekY);
                                        int pom = gridFileHash.remove(pc1);
                                        gridFileHash.put(pc1, newBlock.getIndexBlock());
                                        Point pc2 = new Point();
                                        pc2.setLocation(i + 1, pomVekY);
                                        gridFileHash.put(pc2, pom);
                                    }

                                } else {
                                    gridFileHash.put(p, newBlock.getIndexBlock());
                                }
                                //Prehozeni prvku z bloku aktualniho do noveho
//                                for (Iterator iterator = aktulniBlock.getBlock().iterator(); iterator.hasNext();) {
//                                    IKlicGrid next = (IKlicGrid) iterator.next();
//                                    if (next.getSouradniceKlice().getX() > rezX) {
//                                        aktulniBlock.odeberZBloku(next);
//                                        newBlock.vlozDataDoBlocku(next);
//                                    }
//                                }
                                LinkedList list = aktulniBlock.getBlock();
                                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                                    IKlicGrid next = (IKlicGrid) iterator.next();
                                    if (next.getSouradniceKlice().getX() > rezX) {
                                        aktulniBlock.odeberZBloku(next);
                                        newBlock.vlozDataDoBlocku(next);
                                        iterator = list.iterator();
                                    }
                                }
                                if (data.getSouradniceKlice().getX() > rezX) {
                                    newBlock.vlozDataDoBlocku(data);

                                } else {
                                    aktulniBlock.vlozDataDoBlocku(data);
                                }

                                // pri vytvoreni noveho rezu se muze stat ze se vytvori nova prazda mista - tim padem nakopiruji predesli blok na toto misto jeho index
                                for (int i = 0; i < vektorY.size(); i++) {
                                    int index;
                                    Point actKey = new Point(), preKey = new Point();
                                    actKey.setLocation(indexRezX, i);
                                    preKey.setLocation(indexRezX - 1, i);
                                    if (gridFileHash.get(actKey) == null) {
                                        if (gridFileHash.get(preKey) != null) {
                                            gridFileHash.put(actKey, gridFileHash.get(preKey));
                                        } else {
                                            System.out.println("chyba pri vimene podle y ");
                                        }
                                    }
                                }
                                jeX = false;
                                // TODO : ulozeni  
//                                listBlock.add(newBlock);
                                byteFile.updateDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);
                                byteFile.ulozBlockDoSouboru(newBlock, newBlock.getIndexBlock());
                            } else { // podle Y
                                GridBlock newBlock;
                                double pomSou = 0;
                                int poc = 0, indexRezY = 0;
                                // secteni souradnicX pro spocitani rezu
                                for (Iterator iterator = aktulniBlock.getBlock().iterator(); iterator.hasNext();) {
                                    IKlicGrid next = (IKlicGrid) iterator.next();
                                    pomSou += next.getSouradniceKlice().getY();
                                    poc++;
                                }
                                // vytroreni rezu pro x
                                rezY = pomSou / poc;
                                rezY = (rezY + data.getSouradniceKlice().getY()) / 2;
                                newBlock = addNewBlockByY(rezY);
                                vektorY.add(rezY);
                                Collections.sort(vektorY);
                                // k nalezeni indexu rezu X
                                for (int i = 0; i < vektorY.size(); i++) {
                                    if (vektorY.get(i) == rezY) {
                                        indexRezY = i;
                                    }
                                }
                                Point p = new Point();
                                // predelat klice cele hasmapy na danem radku sloupci 
                                p.setLocation(pomVekX, indexRezY);
                                if (gridFileHash.get(p) != null) {
                                    int prvek = gridFileHash.get(p);
                                    Point prvN = new Point();
                                    prvN.setLocation(pomVekX - 1, indexRezY);
                                    if (gridFileHash.get(prvN) != null) {
                                        int in = gridFileHash.remove(prvN);
                                        Point prvN1 = new Point();
                                        prvN1.setLocation(pomVekX - 1, indexRezY + 1);
                                        gridFileHash.put(prvN1, in);
                                    }
                                    for (int i = indexRezY; i < vektorY.size() - 1; i++) {
                                        Point pc1 = new Point();
                                        Point pc2 = new Point();
                                        pc1.setLocation(pomVekX, i);
                                        int pom = gridFileHash.remove(pc1);
                                        gridFileHash.put(pc1, newBlock.getIndexBlock());
                                        pc2.setLocation(pomVekX, i + 1);
                                        gridFileHash.put(pc2, pom);
                                    }

                                } else {
                                    gridFileHash.put(p, newBlock.getIndexBlock());
                                }

                                //Prehozeni prvku z bloku aktualniho do noveho
                                LinkedList list = aktulniBlock.getBlock();
                                for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                                    IKlicGrid next = (IKlicGrid) iterator.next();
                                    if (next.getSouradniceKlice().getY() > rezY) {
                                        aktulniBlock.odeberZBloku(next);
                                        newBlock.vlozDataDoBlocku(next);
                                        iterator = list.iterator();
                                    }
                                }
                                if (data.getSouradniceKlice().getY() > rezY) {
                                    newBlock.vlozDataDoBlocku(data);

                                } else {
                                    aktulniBlock.vlozDataDoBlocku(data);
                                }

                                // pri vytvoreni noveho rezu se muze stat ze se vytvori nova prazda mista - tim padem nakopiruji predesli blok na toto misto jeho index
                                for (int i = 0; i < vektorX.size(); i++) {
                                    int index;
                                    Point actKey = new Point(), preKey = new Point();
                                    actKey.setLocation(i, indexRezY);
                                    preKey.setLocation(i, indexRezY - 1);
                                    if (gridFileHash.get(actKey) == null) {
                                        if (gridFileHash.get(preKey) != null) {
                                            gridFileHash.put(actKey, gridFileHash.get(preKey));
                                        } else {
                                            System.out.println("chyba pri vimene podle y ");
                                        }
                                    }
                                }
                                jeX = true;
                                // TODO : ulozeni  
//                                listBlock.add(newBlock);
                                byteFile.updateDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);
                                byteFile.ulozBlockDoSouboru(newBlock, newBlock.getIndexBlock());
                            }
                        }
                    } else { //Aktualni blok neni uzavren vkladame data
                        aktulniBlock.vlozDataDoBlocku(data);
                        byteFile.updateDoSouboruControlniBlock(gridFileHash, vektorX, vektorY, pocitadloBloku);
                        byteFile.ulozBlockDoSouboru(aktulniBlock, indexGB);
                        // TODO : ulozit ho zpet do souboru 
                    }
                }
            }
        }
    }

    private GridBlock addNewBlockByX() {
        GridBlock block = new GridBlock(velikostBloku);
        indexBlocku++;
        block.setIndexBlock(indexBlocku);
        pocitadloBloku++;
        return block;
    }

    private GridBlock addNewBlockByY(double rezY) {
        GridBlock block = new GridBlock(velikostBloku);
        indexBlocku++;
        block.setIndexBlock(indexBlocku);
        pocitadloBloku++;
        return block;
    }

    private GridBlock loadBlock(int pomVekX, int pomVekY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<Double> getVektorX() {
        return vektorX;
    }

    @Override
    public LinkedList<Double> getVektorY() {
        return vektorY;
    }

    @Override
    public int getPocitadloBloku() {
        return pocitadloBloku;
    }

    @Override
    public HashMap<Point, Integer> getGridFileHash() {
        return gridFileHash;
    }

    @Override
    public LinkedList<E> intervalFindVrchol(double minBoundX, double minBoundY, double maxBoundX, double maxBoundY) throws IOException, FileNotFoundException, ClassNotFoundException {
        GridControllBlock f = byteFile.nactiZeSouboruControllBlock();
        this.gridFileHash = f.getGridFile();
        this.vektorX = f.getVektorX();
        this.vektorY = f.getVektorY();
        this.pocitadloBloku = f.getPocetBloku();

        Point min = new Point(), max = new Point();
        LinkedList<E> listVrcholu = new LinkedList<>();
        LinkedList<Double> pomVektorX = new LinkedList<>();
        LinkedList<Double> pomVektorY = new LinkedList<>();

        min.setLocation(minBoundX, minBoundY);
        max.setLocation(maxBoundX, maxBoundY);

        for (Iterator<Double> iterator = vektorX.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (next >= minBoundX && next <= maxBoundX) {
                pomVektorX.add(next);
            }
        }
        for (Iterator<Double> iterator = vektorY.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (next >= minBoundY && next <= maxBoundY) {
                pomVektorY.add(next);
            }
        }
        Collections.sort(pomVektorX);
        Collections.sort(pomVektorY);

        GridBlock p;
        for (Iterator<Double> iterator = pomVektorX.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            for (Iterator<Double> iterator1 = pomVektorY.iterator(); iterator1.hasNext();) {
                Double next1 = iterator1.next();
                Point pKlic = new Point();
                pKlic.setLocation(next, next1);
                int indexGB;
                if (gridFileHash.get(pKlic) != null) {
                    indexGB = gridFileHash.get(pKlic);
                    p = byteFile.nactiBlokZeSouboru(indexGB);
                    LinkedList pomListVr = p.getBlock();
                    for (Iterator iterator2 = pomListVr.iterator(); iterator2.hasNext();) {
                        E next2 = (E) iterator2.next();
                        if (next2.getSouradniceKlice().getX() >= minBoundX && next2.getSouradniceKlice().getX() <= maxBoundX && next2.getSouradniceKlice().getY() >= minBoundY && next2.getSouradniceKlice().getY() <= maxBoundY) {
                            listVrcholu.add(next2);
                        }
                    }
                }
            }
        }

        return listVrcholu;
    }

    @Override
    public E najdiVrchol(double x, double y) throws IOException, FileNotFoundException, ClassNotFoundException {
        E pom = null;
        double pomX = 0, pomY = 0;
        GridControllBlock gcb = byteFile.nactiZeSouboruControllBlock();
        GridBlock g;
        this.gridFileHash = gcb.getGridFile();
        this.vektorX = gcb.getVektorX();
        this.vektorY = gcb.getVektorY();
        this.pocitadloBloku = gcb.getPocetBloku();

        for (Iterator<Double> iterator = vektorX.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (x > next) {
                pomX = next;
            }
        }
        for (Iterator<Double> iterator = vektorY.iterator(); iterator.hasNext();) {
            Double next = iterator.next();
            if (y > next) {
                pomY = next;
            }
        }
        Point p = new Point();
        p.setLocation(pomX, pomY);
        if (gridFileHash.get(p) != null) {
            int index = gridFileHash.get(p);
            g = byteFile.nactiBlokZeSouboru(index);
            LinkedList listV = g.getBlock();
            for (Iterator iterator = listV.iterator(); iterator.hasNext();) {
                E next = (E) iterator.next();
                if (next.getSouradniceKlice().getX() == p.getX() && next.getSouradniceKlice().getY() == p.getY()) {
                    pom = next;
                }
            }
        }
        return pom;
    }

}
