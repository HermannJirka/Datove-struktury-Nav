///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Main;
//
//import AbstrakniStruktury.Graf;
//import Data.IMesto;
//import Data.ISilnice;
//import Data.Mesto;
//import Data.Silnice;
//import java.awt.Point;
//import java.util.Iterator;
//
///**
// *
// * @author George
// */
//public class Main {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        Graf<IMesto, ISilnice> graf = new Graf<>();
//        Mesto m1 = new Mesto("Pardubice", null);
//        Mesto m2 = new Mesto("Praha", null);
//        Mesto m3 = new Mesto("Brno", null);
//        Mesto m4 = new Mesto("Liberec", null);
//        Silnice s1 = new Silnice(5, true, "d5", m1, m2);
//        
//        graf.addVrchol(m1);
//        graf.addVrchol(m4);
//        graf.addVrchol(m3);
//        graf.addVrchol(m2);
//        for (Iterator iterator = graf.iteratorVrcholu(); iterator.hasNext();) {
//            Object next = iterator.next();
//            System.out.println(next.toString());
//        }
////        graf.smazaniVrcholu(m4);
//     //   graf.smazaniVrcholu(new Mesto("Pardubice", new Point(10, 10)));
//        System.out.println("===");
//        for (Iterator iterator = graf.iteratorVrcholu(); iterator.hasNext();) {
//            Object next = iterator.next();
//            System.out.println(next.toString());
//        }
//        System.out.println("====");
//        graf.addHranu(m1, m2, s1);
//        graf.addHranu(m2, m3, s1);
//        
//        graf.addHranu(m2, m4, s1);
////        System.out.println(graf.najdiHranu(m1, m2).toString());
////        System.out.println(graf.smazaniHran(m1, m2));
//        System.out.println(graf.smazaniVrcholu(m2));
//        
//        
//
//        // TODO code application logic here
//    }
//
//}
