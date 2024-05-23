package org.bonus;

import java.util.HashMap;
import java.util.Scanner;

public class CharCounter {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.print("Inserisci una parola: ");
        String word= scanner.nextLine();
        HashMap<Character, Integer> charCounter= new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
//            if(!charCounter.containsKey(word.charAt(i))){
//                charCounter.put(word.charAt(i), 1);
//            } else {
//                charCounter.put(word.charAt(i), charCounter.get(word.charAt(i)) + 1);
//            }
            charCounter.put(word.charAt(i), charCounter.containsKey(word.charAt(i)) ? charCounter.get(word.charAt(i)) + 1 : 1);
        }
        System.out.println(charCounter);
    }
}
