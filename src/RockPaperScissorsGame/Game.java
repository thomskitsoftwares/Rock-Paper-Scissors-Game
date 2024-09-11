package RockPaperScissorsGame;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Game {
    String userMove;
    String computerMove;
    String results;
    private static final String SCORE_FILE = "scores.txt";
    private static int wins = 0;
    private static int draws =0;
    private static int losses = 0;

    //constructor
    Game(){
       //call user move method

    }
    void startGame(){
        loadScores();
        setUserMove();
        setComputerMove();
        getResults();
        displayResults();
        updateScore();
        saveScores();

    }
  public void loadScores(){
        File file = new File(SCORE_FILE);
        if(file.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                wins = Integer.parseInt(br.readLine());
                draws = Integer.parseInt(br.readLine());
                losses = Integer.parseInt(br.readLine());
            }catch(IOException | NumberFormatException e){
                System.err.println("Error reading scores: " + e.getMessage());

            }
        }
    }

    private static void saveScores(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(SCORE_FILE))){
            bw.write(String.valueOf(wins));
            bw.newLine();
            bw.write(String.valueOf(draws));
            bw.newLine();

            bw.write(String.valueOf(losses));
        }catch(IOException e){
            System.err.println("Error saving scores: " + e.getMessage());
        }

    }

    String setUserMove(){
        System.out.println("Enter a move: ");
        Scanner in = new Scanner(System.in);
        userMove = in.nextLine();


       return userMove;
    }

    String setComputerMove(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        switch(randomNumber){
            case 0:
                computerMove = "Rock";
                break;
            case 1:
                computerMove = "Paper";
                break;
            case 2:
                computerMove = "Scissors";
                break;
        }
        return computerMove;
    }

    String getResults(){
        if(userMove.equals(computerMove)){
            results = "Draw";
        }else if(userMove.equals("Rock")&& computerMove.equals("Paper")){
            results = "Lost";
        }else if(userMove.equals("Rock")&& computerMove.equals("Scissors")){
            results = "Win";
        }else if(userMove.equals("Paper") && computerMove.equals("Scissors")){
            results = "Lost";
        }else if(userMove.equals("Paper")&& computerMove.equals("Rock")){
            results = "Win";
        }else if(userMove.equals("Scissors")&& computerMove.equals("Rock")){
            results = "Lost";
        }else if(userMove.equals("Scissors") && computerMove.equals("Paper")){
            results = "Win";

        }

        return results;
    }

    void displayResults(){
        System.out.println("You picked: " + " " + userMove +"\n" + "Computer picked: "
        + " "+ computerMove + "\n" + "YOU: " + " " + results);
    }

    void updateScore(){
        switch(results){
            case "Draw":
                draws++;
                break;
            case "Win":
                wins++;
                break;
            case "Lost":
                losses++;
                break;
        }
        System.out.println( "SCORES\n" + "WINS: " + " " + wins + "\n" + "LOSSES: " + " "+ losses + "\n"
                + "DRAWS: " + " " + draws);
    }



}
