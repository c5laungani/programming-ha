// Cellulitis TEMPLATE
// Homework Assignment 3 2ip90 
/**
 * @name(s) //Habeeb Mohammed and Chetan Laungani
 * @id(s)   //1582143 and 1562274
 * @group   //Group 120
 * @date    //20 September 2020
 */

import java.util.Scanner;
import java.util.ArrayList;

class Cellulitis {
  Scanner scanner = new Scanner(System.in);
  int rowLength; // determines the number of cells in each row
  boolean[] nextGen;   //holds the outcome for the next generation based on the current generation
  boolean[] currentGeneration; //a boolean array containing the current generation.
  
    void run(){

    // TODO Insert your code here

    ArrayList<Integer> initialSequence = new ArrayList<Integer>(); // array list to store the numbers of the cells that should be initially occupied

    String automationType=scanner.next(); 
    rowLength=scanner.nextInt();
    int generationNum=scanner.nextInt(); 
    String inStart=scanner.next(); 
    

    //loop to potentially accept multiple initially occupied cells
    while(scanner.hasNextInt()){
      initialSequence.add(scanner.nextInt()); 
    }

    String endStart=scanner.next();    
    
    currentGeneration=new boolean[rowLength+2];//initializing the length of the cells with two extra, one on each side always false

    //set the boolean value of the initial generation
    for(int a=0; a<initialSequence.size(); a++){
      currentGeneration[initialSequence.get(a)]=true;
    }
    draw(currentGeneration);
    System.out.println("");
    for(int b=0; b<generationNum-1; b++) {
      if(automationType.equals("A")){
        draw(nextGenerationA(currentGeneration));
      }      
      if(automationType.equals("B")){
        draw(nextGenerationB(currentGeneration));
      }       
      System.out.println();
    }
    }
    
    
    

    
    
  


  //print the state of the provided generation
  void draw(boolean[] generation) {
    for(int x=1;x<generation.length-1;x++){
      if(generation[x]==true){
        System.out.print("*");
      }
      else{
        System.out.print(" ");
      }
    }
  }
  
  
  //computes values of next generation on rule A
  boolean[] nextGenerationA(boolean[] generation) {
    nextGen=new boolean[rowLength+2];
    
      
      for(int x=1;x<generation.length-1;x++){
      if(generation[x]== true && ((generation[x+1] == true && generation[x-1] == false) || (generation[x-1] == true && generation[x+1] == false))) {
        nextGen[x]=true;
      }
      else if(generation[x]== false && (generation[x-1]==true||generation[x+1]==true)){
        nextGen[x] = true;
        
      } else {
        nextGen[x] = false;
        
      }
    }
    currentGeneration=nextGen.clone();
      return nextGen;
    }
  
  
  //sets values of next generation based on set of rules by B
  boolean[] nextGenerationB(boolean[] generation) {
    nextGen=new boolean[rowLength+2];
    
      
      for(int x=1;x<generation.length-1;x++){
      if(generation[x]== true && generation[x+1] == false) {
        nextGen[x]=true;
      }
      else if(generation[x]== false && ((generation[x+1] == true && generation[x-1] == false) || (generation[x-1] == true && generation[x+1] == false))){
        nextGen[x] = true;
        
      } else {
        nextGen[x] = false;
        
      }
    }
    currentGeneration=nextGen.clone();
      return nextGen;
    
  }

  public static void main(String[] args) { 
    new Cellulitis().run();
    
  }
}
