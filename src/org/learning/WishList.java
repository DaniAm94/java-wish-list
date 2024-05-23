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

        // Inizializzo il file su cui è salvata la mia wishlist
        File file= new File("./resources/data.txt");

        // Dichiaro una wishlist in cui inserisco la wishlist presa dal mio file(può essere vuota)
        ArrayList<String> wishList= readWishListFromFile(file);

        // variabile che raccoglie la scelta dell'utente
        String choice= null;

        Scanner scanner= new Scanner(System.in);

        // Finchè l'utente sceglie y (vuole aggiungere un altro regalo)
        do {

            // Chiedo all'utente se vuole aggiungere un regalo alla wishlist
            System.out.println("Vuoi aggiungere un regalo alla tua lista desideri? y/n");
            choice= scanner.nextLine();
            if (choice.equalsIgnoreCase("y")){

                // Chiedo all'utente cosa vuole ricevere
                System.out.print("Cosa ti piacerebbe ricevere? ");

                // Metto il regalo dell'utente nella wishlist
                wishList.add(scanner.nextLine());

                // Stampo la lunghezza della wishlist
                System.out.println("Al momento ci sono " + wishList.size() + " regali nella tua lista\n**********");
            }
        }while(choice.equalsIgnoreCase( "y"));

        // Riordino la wishlist (ordine alfabetico)
        Collections.sort(wishList);

        // Stampo la wishlist ordinata
        System.out.println("Ecco la tua lista dei desideri ordinata:");
        for (String wish: wishList){
            System.out.println(wish);
        }

        // Scrivo la wishlist sul file (sovrascrivo la lista precedente)
        writeWishListOnFile(wishList, file);
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
        }
                return wishList;
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
