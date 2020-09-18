// Hangman TEMPLATE
// Homework Assignment 2 2ip90 
/**
 * @name(s) //Habeeb Mohammed and Chetan Laungani
 * @id(s)   // 1582143 and 1562274
 * @group   //Group 120
 * @date    //12 September 2020
 */

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Hangman {
  Scanner scanner = new Scanner(System.in);
  
  void play(){
    //Secret words
    String[] bagOfWords = new String[]{"the", "walrus", "and", "carpenter", "were", 
    "walking", "close", "at", "hand"};
    
    //Initialize the random number generator
    System.out.println("Type an arbitrary number");
    int seed = scanner.nextInt();
    Random random = new Random(seed);
    
    //Select a secret word
    String secret = bagOfWords[random.nextInt(bagOfWords.length)];
    
    // TODO Insert your code here
    int lives = 10;
    char guess;
    int correctGuess=0;
    ArrayList<Character> word = new ArrayList<Character>();
    //variable to store secret as char
    

    //to make the blank underscores
    for(int a=0;a<secret.length();a++){
      word.add('_');      
    }
    
    ArrayList<Character> holder= new ArrayList<Character>();
    for (char c : secret.toCharArray()) {
      holder.add(c);
    }
    
    //this printer checks if i successfully transferred secret to holder
    //System.out.println(holder.get(6));

    while(lives>0 && correctGuess<holder.size()){
      for(int b=0; b<secret.length(); b++){
        System.out.print(word.get(b));
    
      }
      System.out.println();
      guess=scanner.next().charAt(0);

      for(int c=0; c<holder.size();c++){
        if(guess==(holder.get(c))){
          word.set(c, holder.get(c));
          correctGuess++;

        }        

      }
      if(secret.indexOf(guess)==-1){
        lives--;      
      }
      
      
      //else{
        //System.out.println("Wrong guess!");
        //lives--;
      //}

    }
    if(lives==0){
      System.out.println("Unlucky, you lost!\nThe secret word was: "+ secret);
    
    }
    else{
      for(int z=0; z<secret.length(); z++){
        System.out.print(word.get(z));
      }
      System.out.println();
      System.out.println("Well done, you won!");
    
    }
    
    
  }
  
  public static void main(String[] args) { 
    new Hangman().play();
  }
}


