import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class ExpenseTracker{
    public static void main(String[] args){
        
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> trackerList = new ArrayList<Expense>();

        System.out.println("Welcome to Expense Tracker!");

        while(run){
            System.out.print("Choose an option: [add, view, remove, exit]: ");
            String userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("add")){
                double amount = 0;
                while(true){
                    System.out.print("Enter expense amount: ");
                    String userAmount = scanner.nextLine();
                    try{
                        amount = Double.parseDouble(userAmount);
                        if(amount <= 0){
                            System.out.println("Value must be greater than $0. Try Again!");
                        }
                        else{
                            break;
                        }
                    }
                    catch (NumberFormatException e){
                        System.out.println("Invalid input. Please input a valid number!");
                    }

                }
               String userCat = "";
               while(true){
                    System.out.print("Enter expense category: ");
                    userCat = scanner.nextLine().trim().toLowerCase();
                    if(!userCat.isEmpty()){
                        break;
                    }
                    else{
                        System.out.println("Please input a valid category");
                    }
               }
                Expense temp = new Expense(amount,userCat);
                trackerList.add(temp);

            }
            else if(userInput.equalsIgnoreCase("view")){
                if(trackerList.size() < 1){
                    System.out.println("List is empty please try again!");
                }
                else{
                    System.out.println("------------------------------------");
                    for(Expense e : trackerList){System.out.println(e);}
                    System.out.println("------------------------------------");
                }
            }
            else if(userInput.equalsIgnoreCase("remove")){
                if(trackerList.size() < 1){
                    System.out.println("List is empty please add items before removing them.");
                }
                else{
                    ArrayList<Expense> sameTemp = new ArrayList<Expense>();
                    int multi = 0;
                    System.out.println("Please select which expense you want to remove: ");
                    System.out.println("------------------------------------");
                    for(Expense e : trackerList){System.out.println(e);}
                    System.out.println("------------------------------------");
                    String remover = scanner.nextLine();
                    remover = remover.toLowerCase().trim();
                    for(int i = 0; i < trackerList.size(); i++){
                        if(trackerList.get(i).getCategory().equalsIgnoreCase(remover)){
                            multi++;
                        }
                    }
                    if(multi > 1){
                        for(int i = 0; i < trackerList.size(); i++){
                            if(trackerList.get(i).getCategory().equalsIgnoreCase(remover)){
                                sameTemp.add(trackerList.get(i));
                            }
                        }
                        System.out.println("You have multiple items in the same cateogy please select which you'd like to delete by inputting the price: ");
    
                        System.out.println("------------------------------------");
                        for(Expense e : sameTemp){System.out.println(e);}
                        System.out.println("------------------------------------");
    
                        double multiSwitch = 0.0;
    
                        while(true){
                            System.out.print("Please select what you'd like to remove: ");
                            String multiUser = scanner.nextLine();
    
                            try{
                                multiSwitch = Double.parseDouble(multiUser);
                                if(multiSwitch <= 0){
                                    System.out.println("Value must be greater than $0. Try Again!");
                                }
                                else{
                                    break;
                                }
                            }
                            catch (NumberFormatException e){
                                System.out.println("Please input a valid response.");
                            }
                        }
    
                        for(int i = trackerList.size() - 1; i >= 0; i--){
                            if(trackerList.get(i).getCategory().equalsIgnoreCase(remover) && Math.abs(trackerList.get(i).getAmount() - multiSwitch) < 0.001){
                                trackerList.remove(i);
                            }
                        }
                        
                    }
                    else{
                        for(int i = trackerList.size() - 1; i >= 0; i--){
                            if (trackerList.get(i).getCategory().equalsIgnoreCase(remover)) {
                                trackerList.remove(i);
                            }
                        }
                    }
                }

            }
            else if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Saving expenses to file...");

                try(PrintWriter writer = new PrintWriter(new FileWriter("Expenses.txt"))){
                    for(Expense e : trackerList){
                        writer.println(e);
                    }
                    System.out.println("Expenses saved! Goodbye!");
                }
                catch(IOException e){
                    System.out.println("An error occured when saving the file");
                }

                run = false;
            }
            else{
                System.out.println("Invalid input please try again!");
            }
        }
    }
}


class Expense{
    public double amount;
    public String category;

    public Expense(double amount, String category){
        this.amount = amount;
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public double getAmount(){
        return amount;
    }

    public String toString(){
        String formattedAmount = String.format("%.2f", amount);
        return "Expense: " + "$" + formattedAmount + " | " + "Category: " + category;
    }
}