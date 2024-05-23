package org.learning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WishList {
    public static void main(String[] args) {
        File file= new File("./resources/data.txt"); // Inizializzo il file su cui è salvata la mia wishlist
        ArrayList<String> wishList= readWishListFromFile(file); // Dichiaro una wishlist in cui inserisco la wishlist presa dal mio file(può essere vuota)
        String choice= null;    // variabile che raccoglie la scelta dell'utente
        Scanner scanner= new Scanner(System.in);
        do {    // Finchè l'utente sceglie y (vuole aggiungere un altro regaalo)
            System.out.println("Vuoi aggiungere un regalo alla tua lista desideri? y/n");
            choice= scanner.nextLine();
            if (choice.equalsIgnoreCase("y")){
                System.out.print("Cosa ti piacerebbe ricevere? ");
                wishList.add(scanner.nextLine());   // Metto il regalo dell'utente nella wishlist
                System.out.println("Al momento ci sono " + wishList.size() + " regali nella tua lista\n**********");    // Stampo la lunghezza della wishlist
            }
        }while(choice.equalsIgnoreCase( "y"));
        Collections.sort(wishList); // Riordino la wishlist (ordine alfabetico)
        System.out.println("Ecco la tua lista dei desideri ordinata:"); // Stampo la wishlist ordinata
        for (String wish: wishList){
            System.out.println(wish);
        }
        writeWishListOnFile(wishList, file);    // Scrivo la wishlist sul file (sovrascrivo la lista precedente)
    }

    // Metodo che legge da file la mia wishlist, la mostra in console e mi restituisce la wishlist
    static ArrayList readWishListFromFile(File file){
        Scanner fileReader= null;
        ArrayList<String> wishList= new ArrayList<>();
        try{
            System.out.println("La tua lista desideri attuale:");
            fileReader= new Scanner(file);
            if (fileReader.hasNextLine()){
                while (fileReader.hasNextLine()){
                    String line= fileReader.nextLine();
                    System.out.println(line);
                    wishList.add(line);
                }
            } else{
                System.out.println("Non ci sono elementi nella tua lista");
            }
        }catch (FileNotFoundException e){
            System.out.println("Impossibile leggere il file");
        } finally {
            if(fileReader != null){
                fileReader.close();
            }
                return wishList;
        }

    }

    // Metodo che scrive la wishlist passata come parametro dentro un file passato come parametro
    static void writeWishListOnFile(ArrayList<String> wishlist, File file){
        try(FileWriter fileWriter= new FileWriter(file)){
            for(String wish: wishlist){
                fileWriter.write(wish + "\n");
            }
        } catch (IOException e){
            System.out.println("Impossibile scrivere sul file " + e.getMessage());
        }
    }
}
