import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int balance = 100;
        int bet;
        int payout;
        String[] row;
        String playAgain;

        System.out.println("*************************");
        System.out.println("  Welcome to JAVA Slots  ");
        System.out.println("Symbols : 🍒 🍉 🍋 🔔 🌟");
        System.out.println("*************************");

        while(balance > 0){
            System.out.println("Current Balance : " + balance + " Rs.");
            System.out.print("Place your bet amount : ");
            bet = sc.nextInt();
            sc.nextLine();

            if(bet > balance){
                System.out.println("INSUFFICIENT FUNDS!");
                continue;
            }
            else if(bet<=0){
                System.out.println("Bet should be greater than 0");
                continue;
            }
            else{
                balance -= bet;
            }

            System.out.println("Spinning...");
            row = spinrow();
            printrow(row);
            payout = getPayout(row,bet);

            if(payout>0){
                System.out.println("You won " + payout + " Rs.");
                balance += payout;
            }
            else{
                System.out.println("Sorry! You lost this round!");
            }

            System.out.println("Do you want to play again ? (Y/N)");
            playAgain = sc.nextLine().toUpperCase();

            if(playAgain.equals("N")) break;
        }

        System.out.println("GAME OVER! Your final balance is : "+ balance + " Rs.");
    }
    static String[] spinrow(){
        Random random = new Random();

        String[] symbols = {"🍒","🍉", "🍋", "🔔" ,"🌟"};
        String[] row = new String[3];

        for(int i=0;i<3;i++){
            row[i] = symbols[random.nextInt(symbols.length)];
        }

        return row;
    }
    static void printrow(String[] row){
        System.out.println("**************");
        System.out.println(" " + String.join(" | ",row));
        System.out.println("**************");
    }
    static int getPayout(String[] row, int bet){
        if(row[0].equals(row[1])&&row[0].equals(row[2])){
            return switch (row[0]){
                case "🍒" -> bet*3;
                case "🍉" -> bet*4;
                case "🍋" -> bet*5;
                case "🔔" -> bet*10;
                case "🌟" -> bet*20;
                default -> 0;
            };
        }
        else if(row[0].equals(row[1])){
            return switch (row[0]){
                case "🍒" -> bet*2;
                case "🍉" -> bet*3;
                case "🍋" -> bet*4;
                case "🔔" -> bet*5;
                case "🌟" -> bet*10;
                default -> 0;
            };
        }
        else if(row[1].equals(row[2])){
            return switch (row[1]){
                case "🍒" -> bet*2;
                case "🍉" -> bet*3;
                case "🍋" -> bet*4;
                case "🔔" -> bet*5;
                case "🌟" -> bet*10;
                default -> 0;
            };
        }

        return 0;
    }
}
