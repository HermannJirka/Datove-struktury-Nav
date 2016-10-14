/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import AbstrakniStruktury.GridIndex;
import AbstrakniStruktury.IGridIndex;
import Algoritmy.AlgoDjiskra;
import Algoritmy.IMapa;
import Algoritmy.Mapa;
import Data.IMesto;
import Data.ISilnice;
import Data.Mesto;
import Data.Silnice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author George
 */
public class GUISemA extends javax.swing.JFrame {

    public static IMapa mapa;

    private LinkedList<ISilnice> silniceList;
    public String[][] maticeMest;
    private int pocetMest;
    private Mesto pocatecniMesto;
    public LinkedList<String> strom;
    private final JFileChooser fc = new JFileChooser();
    public static IGridIndex gridIndex;
    private Random gen = new Random(4545345);

    public GUISemA() {
        initComponents();
        jPanelHlavni.setBackground(Color.white);
        mapa = new Mapa();
        setTitle("Mesta");
        setSize(1050, 600);
        this.pocatecniMesto = null;
        gridIndex = new GridIndex();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g = jPanelHlavni.getGraphics();
        for (Iterator iterator = mapa.dejIteratorMest(); iterator.hasNext();) {
            Mesto mesto = (Mesto) iterator.next();
            silniceList = new LinkedList<>();
            silniceList = mapa.vratSeznamSilnic(mesto);
            if (mesto == null) {
                return;
            }

            g.setColor(Color.red);
            g.drawString(mesto.getNazev(), mesto.getSouradnice().x - 10, mesto.getSouradnice().y - 25);
            String string = mesto.getPredchudce() == null ? "" : mesto.getPredchudce().getNazev();
            String naslednici = "";

            for (String s : mesto.getNasledniky()) {
                naslednici += s + ",";
            }

            g.drawString(mesto.getSouradnice().x + ":" + mesto.getSouradnice().y + " - " + mesto.getOhodnoceni() + " || " + string + " | " + naslednici, mesto.getSouradnice().x - 10, mesto.getSouradnice().y - 10);
            g.setColor(Color.black);
            g.fillOval(mesto.getSouradnice().x - 5, mesto.getSouradnice().y - 5, 10, 10);

            for (Iterator<ISilnice> iterator1 = silniceList.iterator(); iterator1.hasNext();) {
                Silnice next = (Silnice) iterator1.next();
                Mesto mestoDo = (Mesto) next.getDoMesta();

                g.setColor(Color.black);
                g.drawLine(mesto.getSouradnice().x, mesto.getSouradnice().y, mestoDo.getSouradnice().x, mestoDo.getSouradnice().y);

                Mesto mestoOd = (Mesto) next.getzMesta();
                int vx, vy;
                vx = mestoDo.getSouradnice().x + mestoOd.getSouradnice().x;
                vy = mestoOd.getSouradnice().y + mestoDo.getSouradnice().y;
                g.setColor(Color.blue);
                g.drawString(next.getOhodnoceni() + " , uzavreno :  " + next.getUzavreno(), vx / 2, vy / 2);

//                g.drawString(next.getOhodnoceni()+" = "+next.getNazevSilnice(), mesto.getSouradnice().x + Math.abs((mesto.getSouradnice().x-mestoDo.getSouradnice().x)/2), 
//                        mesto.getSouradnice().y + Math.abs((mesto.getSouradnice().y-mestoDo.getSouradnice().y)/2));
            }

        }

        LinkedList<Double> rezyX = new LinkedList<>();
        LinkedList<Double> rezyY = new LinkedList<>();

        rezyX = gridIndex.listRezuX();
        rezyY = gridIndex.listRezuY();
        if (rezyX != null && rezyY != null) {
            for (Iterator<Double> iterator = rezyY.iterator(); iterator.hasNext();) {
                Double next = iterator.next();
                g.setColor(Color.green);
                int i = next.intValue();
                g.drawLine(0, i, jPanelHlavni.getWidth(), i);
                g.drawString(" " + i, 0 + 10, i + 10);
            }
            for (Iterator<Double> iterator = rezyX.iterator(); iterator.hasNext();) {
                Double next = iterator.next();
                int i = next.intValue();
                g.setColor(Color.green);
                g.drawString(" " + i, i + 10, 0 + 10);
                g.drawLine(i, 0, i, jPanelHlavni.getHeight());
            }
        }

    }

    private void kresliBod(Point bod) {
        Graphics g = jPanelHlavni.getGraphics();
        g.setColor(Color.black);
        g.fillOval(bod.x - 5, bod.y - 5, 10, 10);
    }

    private void kresliHranu(Point odV, Point doV) {
        Graphics g = jPanelHlavni.getGraphics();
        g.setColor(Color.green);
        g.drawLine(odV.x, odV.y, doV.x, doV.y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelVyhledavani = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPocMesto = new javax.swing.JLabel();
        txtPocMesto = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        panelGenerovani = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPocetMest = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtOhodnoceniSilnice = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        btnNahraj = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        lblNacteno = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUlozeno = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSmazano = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFMestoOd = new javax.swing.JTextField();
        txtFMestoDo = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanelHlavni = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        panelVyhledavani.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Pocatecni mesto : ");

        jButton7.setText("Vyhledat");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVyhledavaniLayout = new javax.swing.GroupLayout(panelVyhledavani);
        panelVyhledavani.setLayout(panelVyhledavaniLayout);
        panelVyhledavaniLayout.setHorizontalGroup(
            panelVyhledavaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVyhledavaniLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelVyhledavaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVyhledavaniLayout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelVyhledavaniLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPocMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPocMesto))
                .addContainerGap())
        );
        panelVyhledavaniLayout.setVerticalGroup(
            panelVyhledavaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVyhledavaniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVyhledavaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblPocMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPocMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGenerovani.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Generator");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Pocet mest : ");

        txtPocetMest.setText("10");
        txtPocetMest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPocetMestActionPerformed(evt);
            }
        });

        jLabel3.setText("Rozpeti ohodnoceni : ");

        txtOhodnoceniSilnice.setText("10");

        javax.swing.GroupLayout panelGenerovaniLayout = new javax.swing.GroupLayout(panelGenerovani);
        panelGenerovani.setLayout(panelGenerovaniLayout);
        panelGenerovaniLayout.setHorizontalGroup(
            panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4)
            .addGroup(panelGenerovaniLayout.createSequentialGroup()
                .addGroup(panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPocetMest)
                    .addComponent(txtOhodnoceniSilnice, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
        );
        panelGenerovaniLayout.setVerticalGroup(
            panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenerovaniLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPocetMest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGenerovaniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOhodnoceniSilnice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("Silnice");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Mesto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton5.setText("Algo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Repaint");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton10.setText("Zobraz Matici");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Zobraz Strom");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton8.setText("Uloz");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btnNahraj.setText("Nahraj");
        btnNahraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNahrajActionPerformed(evt);
            }
        });

        jButton9.setText("Smaz");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        label.setText("Nenacteno :");

        jLabel4.setText("Ulozeno : ");

        jLabel5.setText("Smazano : ");

        jButton12.setText("NahrajZCSV");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("UlozDoCSV");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNahraj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNacteno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUlozeno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSmazano)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(label)
                    .addComponent(lblNacteno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNahraj)
                    .addComponent(jLabel4)
                    .addComponent(lblUlozeno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jLabel5)
                    .addComponent(lblSmazano))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Mesto od : ");

        jLabel7.setText("Mesto do : ");

        jButton6.setText("Najdi");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFMestoOd))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFMestoDo, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFMestoOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFMestoDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelVyhledavani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGenerovani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGenerovani, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelVyhledavani, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanelHlavni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelHlavniMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHlavniLayout = new javax.swing.GroupLayout(jPanelHlavni);
        jPanelHlavni.setLayout(jPanelHlavniLayout);
        jPanelHlavniLayout.setHorizontalGroup(
            jPanelHlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1108, Short.MAX_VALUE)
        );
        jPanelHlavniLayout.setVerticalGroup(
            jPanelHlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelHlavni, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelHlavniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelHlavniMousePressed
        kresliBod(evt.getPoint());
        JFrame frame = new JFrame("Panel pro mesta");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(new PridejMesto());
        PridejMesto.bod = evt.getPoint();
        frame.pack();
        frame.setVisible(true);
        repaint();


    }//GEN-LAST:event_jPanelHlavniMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFrame frame = new JFrame("Pridej hranu");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(new PridejSilnici());
        frame.pack();
        frame.setVisible(true);
        repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            Mesto m1 = this.pocatecniMesto;
            AlgoDjiskra alg = new AlgoDjiskra(mapa, (Mesto) mapa.najdiMesto(m1));
            alg.prepocitejVzdalenost();
            this.maticeMest = alg.maticeMest();
            System.out.println(alg.vypisMest(m1));
            repaint();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Neni zadano pocatecni mesto : " + e.getMessage(), "Pocatecni mesto", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.pocatecniMesto = (Mesto) mapa.najdiMesto(new Mesto(txtPocMesto.getText().toString(), null));
        if (pocatecniMesto != null) {
            lblPocMesto.setText(this.pocatecniMesto.getNazev());
        } else {
            lblPocMesto.setText(" Nenalezeno ! ");
            JOptionPane.showMessageDialog(this, "Mesto neni na mape");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtPocetMestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPocetMestActionPerformed

    }//GEN-LAST:event_txtPocetMestActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        final int width = jPanelHlavni.getWidth();
        final int height = jPanelHlavni.getHeight();
        final int tolerance = 20;
        int maxRoadLenght = Integer.valueOf(txtOhodnoceniSilnice.getText().toString());
        this.pocetMest = Integer.valueOf(txtPocetMest.getText().toString());
        for (int i = 0; i < pocetMest; i++) {
            mapa.pridejVrchol(new Mesto("Mesto" + i, new Point(tolerance + gen.nextInt(width - tolerance), tolerance + gen.nextInt(height - tolerance))));
//           gridIndex.addVrcholFinal(new Point(tolerance + gen.nextInt(width - tolerance), tolerance + gen.nextInt(height - tolerance)), new Mesto("Mesto" + i, new Point(tolerance + gen.nextInt(width - tolerance), tolerance + gen.nextInt(height - tolerance))));
        }

        ArrayList<IMesto> mesta = new ArrayList<>();
        for (Iterator<IMesto> it = mapa.dejIteratorMest(); it.hasNext();) {
            mesta.add(it.next());
        }
        LinkedList<Mesto> mestoGrid = new LinkedList<>();
        for (Iterator iterator = mapa.dejIteratorMest(); iterator.hasNext();) {
            Mesto next = (Mesto) iterator.next();
            mestoGrid.add(next);
        }
        Collections.sort(mestoGrid);
        for (Iterator<Mesto> iterator = mestoGrid.iterator(); iterator.hasNext();) {
            Mesto next = iterator.next();
            gridIndex.addVrcholFinal(next.getSouradnice(), next);
        }

        for (Iterator<IMesto> it = mapa.dejIteratorMest(); it.hasNext();) {
            IMesto zMesto = it.next();
            IMesto doMesto = zMesto;
            while (doMesto == zMesto) {
                doMesto = mesta.get(gen.nextInt(mesta.size() - 1));
            }
            mapa.pridejHranu(zMesto, doMesto, new Silnice(gen.nextInt(maxRoadLenght) + 5, false, "CESTA", zMesto, doMesto));
            IMesto doMesto2 = zMesto;
            while (doMesto2 == zMesto) {
                doMesto2 = mesta.get(gen.nextInt(mesta.size() - 1));
            }
            mapa.pridejHranu(zMesto, doMesto, new Silnice(gen.nextInt(maxRoadLenght) + 5, false, "CESTA", zMesto, doMesto2));
        }

        repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        if (maticeMest != null) {
            JFrame f = new JFrame("Matice vypis");
            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.getContentPane().add(new MaticeVypis(maticeMest));
            f.pack();
            f.setVisible(true);
            repaint();
        }

    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        JFrame f = new JFrame("Matice vypis");
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.getContentPane().add(new StromVypis(this.pocatecniMesto));
        f.pack();
        f.setVisible(true);
        repaint();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String fileName;

        int returnValue = this.fc.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileName = this.fc.getSelectedFile().getAbsolutePath();
            try {
                Uloz(fileName, GUISemA.mapa);
                lblUlozeno.setText("Uložení proběhlo úspěšně.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }

        }
    }//GEN-LAST:event_jButton8ActionPerformed
    private static IMapa Nacti(String cesta) {
        IMapa map;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(cesta));

            map = (IMapa) in.readObject();
            if (map != null) {
                for (Iterator iterator = map.dejIteratorMest(); iterator.hasNext();) {
                    Mesto next = (Mesto) iterator.next();
                    gridIndex.addVrcholFinal(next.getSouradnice(), next);
                }
            }
            return map;
        } catch (EOFException ex) {
            JOptionPane.showMessageDialog(null, "Chyba v nacteni", "Chyba", JOptionPane.ERROR_MESSAGE);

            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Chyba v nacteni", "Chyba", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Chyba v nacteni", "Chyba", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Chyba v nacteni", "Chyba", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }

        return null;
    }
    private void btnNahrajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNahrajActionPerformed
        String fileName;
        int returnValue = this.fc.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileName = this.fc.getSelectedFile().getAbsolutePath();
            GUISemA.mapa = Nacti(fileName);
            repaint();
            if (GUISemA.mapa != null) {
                lblNacteno.setText("Načtení proběhlo úspěšně");
            }
        }
        repaint();

    }//GEN-LAST:event_btnNahrajActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        GUISemA.mapa.Zrus();
        GUISemA.gridIndex.Zrus();
        lblSmazano.setText("Smazano");
        repaint();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        try {
            nactiSouborCSV();
            jPanelHlavni.repaint();
        } catch (Exception ex) {
            Logger.getLogger(GUISemA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            ulozDoSouboruCSV(GUISemA.mapa);
//            JVystup.setText("Uložení proběhlo úspěšně.");
        } catch (Exception ex) {
            Logger.getLogger(GUISemA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFrame frame = new JFrame("Panel pro mesta");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(new PridejMesto());

        frame.pack();
        frame.setVisible(true);
        repaint();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Graphics g = getGraphics();
        LinkedList<IMesto> listMest = new LinkedList<>();
        if (txtFMestoOd != null && txtFMestoDo != null) {
            String mOd = txtFMestoOd.getText();
            String mDo = txtFMestoDo.getText();
            Mesto mestoOd = (Mesto) mapa.najdiMesto(new Mesto(mOd, null));
            Mesto mestoDo = (Mesto) mapa.najdiMesto(new Mesto(mDo, null));
            if (!gridIndex.jePrazdny()) {
                listMest = gridIndex.intervalFindVrchol(mestoOd.getSouradnice().getX(), mestoOd.getSouradnice().getY(), mestoDo.getSouradnice().getX(), mestoDo.getSouradnice().getY());
                for (Iterator<IMesto> iterator = listMest.iterator(); iterator.hasNext();) {
                    Mesto next = (Mesto) iterator.next();
                    System.out.println(next.toString());
                }

            }
        }
        for (Iterator iterator = listMest.iterator(); iterator.hasNext();) {
            Mesto mesto = (Mesto) iterator.next();
            silniceList = new LinkedList<>();
            silniceList = mapa.vratSeznamSilnic(mesto);

            g.setColor(Color.blue);
            g.fillOval(mesto.getSouradnice().x + 3, mesto.getSouradnice().y + 25, 10, 10);

        }
        txtFMestoOd.setText(null);
        txtFMestoDo.setText(null);
        listMest.clear();

    }//GEN-LAST:event_jButton6ActionPerformed
    private static void Uloz(String cesta, IMapa map) {
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cesta))) {
                out.writeObject(map);

            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Chyba pri ulozeni", "Chyba", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Chyba pri ulozeni", "Chyba", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUISemA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUISemA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUISemA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUISemA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUISemA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNahraj;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelHlavni;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblNacteno;
    private javax.swing.JLabel lblPocMesto;
    private javax.swing.JLabel lblSmazano;
    private javax.swing.JLabel lblUlozeno;
    private javax.swing.JPanel panelGenerovani;
    private javax.swing.JPanel panelVyhledavani;
    private javax.swing.JTextField txtFMestoDo;
    private javax.swing.JTextField txtFMestoOd;
    private javax.swing.JTextField txtOhodnoceniSilnice;
    private javax.swing.JTextField txtPocMesto;
    private javax.swing.JTextField txtPocetMest;
    // End of variables declaration//GEN-END:variables

    private void nactiSouborCSV() {
        try {
            BufferedReader readV = new BufferedReader(new FileReader("vrcholy.csv"));
            BufferedReader readH = new BufferedReader(new FileReader("hrany.csv"));
            mapa.Zrus();
            ArrayList poleMest = new ArrayList();
            String radek, radekHran;
            while ((radek = readV.readLine()) != null) {

                String[] pom = radek.split(";");
                Mesto pomM = new Mesto(pom[0], new Point(Integer.parseInt(pom[1]), Integer.parseInt(pom[2])));
                mapa.pridejVrchol(pomM);

                poleMest.add(pomM);

            }
            readV.close();

            while ((radekHran = readH.readLine()) != null) {
                String[] pom = radekHran.split(";");
                Mesto m1 = new Mesto(pom[0], new Point(Integer.parseInt(pom[1]), Integer.parseInt(pom[2])));
                Mesto m2 = new Mesto(pom[4], new Point(Integer.parseInt(pom[5]), Integer.parseInt(pom[6])));
                ISilnice sil = new Silnice(Integer.parseInt(pom[7]), Boolean.parseBoolean(pom[8]), pom[9], m1, m2);
                mapa.pridejHranu(m1, m2, sil);
            }
            readH.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Chyba v nacteni", "Chyba", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ulozDoSouboruCSV(IMapa mapa) throws IOException {
        FileWriter writerVrcholy = new FileWriter("vrcholy.csv");
        FileWriter writerHrany = new FileWriter("hrany.csv");
        Iterator it = mapa.dejIteratorMest();

        while (it.hasNext()) {
            Mesto pom = (Mesto) it.next();
            writerVrcholy.write(pom.getNazev() + ";" + pom.getSouradnice().x + ";" + pom.getSouradnice().y + "\n");
        }
        writerVrcholy.close();
        LinkedList<ISilnice> silnice = mapa.dejSeznamVsechSilnic();
        Iterator<ISilnice> itS = silnice.iterator();
        while (itS.hasNext()) {
            Silnice pom = (Silnice) itS.next();
            writerHrany.write(pom.getOhodnoceni() + ";" + pom.getUzavreno() + ";" + pom.getNazevSilnice() + ";" + pom.getzMesta() + ";" + pom.getDoMesta() + "\n");
        }

        writerHrany.close();
    }
}
