import java.util.Scanner;
import java.util.ArrayList;


public class ToDoList{
    public static void main(String[] args){

        boolean exit = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList <String> doList = new ArrayList<String>();

        System.out.println("");
        System.out.println("Welcome to your To-do List for today! ");
        System.out.println("");

        while(exit != false){

            System.out.println("Please enter, 'add', 'remove' or view' if you'd like to do these actions to your list");
            String userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("add")){

                boolean adder = true;
                System.out.println("");
                System.out.println("Please enter what you'd like to add to the To-do list or done to finish adding");

                while(adder != false){

                    String addInput = scanner.nextLine();

                    if(addInput.equalsIgnoreCase("done")){
                        System.out.println("");
                        adder = false;
                    }
                    else{
                        doList.add(addInput);
                    }
                  
                    
                }

                
            }

            else if(userInput.equalsIgnoreCase("remove")){

                System.out.println("");
                System.out.println("Please select which item you'd like to delete or done if finished removing items");

                boolean remover = true;

                while(remover != false){
                    String removeInput = scanner.nextLine();

                    if(removeInput.equalsIgnoreCase("done")){
                        System.out.println("");
                        remover = false;
                    }

                    else{
                        if(doList.contains(removeInput)){
                            for(int i = 0; i < doList.size(); i++){
                                if(doList.get(i).equalsIgnoreCase(removeInput)){
                                    doList.remove(i);
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("Item not found in list, please try again!");
                            System.out.println("");
                        }
                    }
                }

            }

            else if(userInput.equalsIgnoreCase("view")){

                System.out.println("");

                if(doList.isEmpty()){
                    System.out.println("Your To-do list is empty");
                    System.out.println("");
                }

                else{

                    for(int i = 0; i < doList.size(); i++){
                        System.out.println(doList.get(i));
                    }
                    System.out.println("");
                }
            }

            else if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Have a Productive Day!");
                exit = false;
            }

            else{
                System.out.println("Invalid input, please try again!");
            }
        }
    }
}