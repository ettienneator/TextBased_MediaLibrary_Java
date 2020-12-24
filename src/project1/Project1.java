package project1;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ettienne Martinez
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception{
        
        Store store = new Store();
        
        while(true){
            menu(store);
        }
    }
    
    private static void menu(Store store){
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Blue Box Movie and Game rentals interface:");
        System.out.println("Please choose an action:"
                + "\n(1) Loan an Item"
                + "\n(2) Return an Item"
                + "\n(3) Show Items in List"
                + "\n(4) Add a New Media Item"
                + "\n(5) Remove a Media Item"
                + "\n(6) Quit Program");
        
        int choice = input.nextInt();
        
        switch(choice){
            case 1: loanItem(store);
                    break;
            case 2: returnItem(store);
                    break;
            case 3: store.showItems(store);
                    break;
            case 4: addMedia(store);
                    break;
            case 5: removeMedia(store);
                    break;
            case 6: quit(store);
        }
    }
    
    public static void addMedia(Store store) {
        String title = null;
        try{
            Scanner input = new Scanner(System.in);

            System.out.print("\nWhat is the Title of the Item you would like to add? ");
            title = input.nextLine();

            System.out.print("What is the Format of this Item?");
            String format = input.next();
            
            Item newItem = new Item(title, format);
            
            store.addMedia(newItem);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Item Successfully Added! " + title);
    }
    
    public static void removeMedia(Store store) {
        String title = null;
        try{
            Scanner input = new Scanner(System.in);

            System.out.print("\nWhat is the Title of the Item you would like to remove? ");
            title = input.nextLine();
            
            store.removeMedia(title);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        
        System.out.println("Item Successfully Removed! " + title);
    }

    public static void loanItem(Store store){
        String title = null;
        try{
            Scanner input = new Scanner(System.in);

            System.out.print("\nWhat is the Title of the Item you would like to Loan? ");
            title = input.nextLine();
            if(!store.isExist(title.trim())){
                throw new Exception("The title you have requested does not exist!");
            }
            
            System.out.print("What is your name? ");
            String name = input.next();
            
            System.out.println("What is the date you are loaning from? MM/DD/YYYY");
            String date = input.next();
            
            store.loanItem(title, name, date);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public static void returnItem(Store store){
        Scanner input = new Scanner(System.in);

        System.out.print("\nWhat is the Title of the Item you would like to Return? ");
        String title = input.nextLine();
        if(!store.isExist(title.trim())){
            System.out.println("The title you want to return has not existed in our collection!");
            return;
        }
        try {
            store.returnItem(title);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("The title was Successfully Returned: " + title);
    }
    
    private static void quit(Store store){
        try {
            store.saveLibrary();
            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}