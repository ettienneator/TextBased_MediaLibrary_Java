package project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Ettienne Martinez
 */
public class Store {
    
    private List<Item> library;
    
    public Store() throws IOException {
        library = loadLibrary();
    }
    
    private List<Item> loadLibrary() throws IOException{
        File file = new File ("Items.txt");
        //StringBuilder line = new StringBuilder();
        BufferedReader reader = null;
        
        List<Item> library = new ArrayList<>();
        List<List<String>> tokensList = new ArrayList();
        
            reader = new BufferedReader (new FileReader(file));
            String line = null;
            
        try {
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "-");
                List<String> tokenList = new ArrayList<>();
                while(tokenizer.hasMoreTokens()){
                    tokenList.add(tokenizer.nextToken().trim());
                    
                }
                tokensList.add(tokenList);
            }
           library = populateItems(tokensList);
           
        }catch (Exception e) {
            System.err.println(e);
        }finally{
            if (reader !=null){
                reader.close();
            }
        }
        
        return library;
    }
    
    public void saveLibrary() throws Exception{
        File file = new File ("Items.txt");
        
        BufferedWriter writer = null;
        
        try{
            writer = new BufferedWriter (new FileWriter(file));
            for(Item item: library){
                writer.write(item.writeToString() + "\r\n");
            }
        }catch(Exception e){
            throw new Exception("Unable to save the collection.");
        }finally{
            if(writer != null){
                writer.close();
            }
        }
    }
    
    private List<Item> populateItems(List<List<String>> tokensList) throws Exception{
        List<Item> library = new ArrayList();
        
        try{
        for(List<String> tokens : tokensList){
            
            library.add(toItems(tokens));
        }
        }catch(Exception e){
            throw new Exception("Unable to populate List");
        }
        return library;
        
    }
    
    public Item toItems(List<String> tokens){
        
        Item item = new Item(tokens.get(0), tokens.get(1));
        boolean isAvailable = Boolean.valueOf(tokens.get(2).trim());
        
        try{
        if(!isAvailable){
            item.setIsAvailable(isAvailable);
            item.setNameLoaning(tokens.get(3));
            item.setDate(tokens.get(4));
        }
                }catch(Exception e){
            
        }
        return item;
    }
    
    public void listLibrary(){
        for (Item item : library){
            System.out.println(item.toString());
        }
        System.out.println("");
    }
    
    public void loanItem(String title, String name, String date) throws Exception {
        if(!isExist(title)){
            throw new Exception("The title you are trying to loan does not exist.");
        }
        
        try{
            //ITEM EXISTS
            Iterator<Item> iterator = library.iterator();
            while (iterator.hasNext()) {
                Item media = iterator.next();
                if(media.getTitle().equalsIgnoreCase(title)){
                    if(!media.isAvailable()){
                        System.out.println("The title you are trying to loan is already Loaned out.");
                    }
                    media.setIsAvailable(false);
                    media.setNameLoaning(name);
                    media.setDate(date);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void returnItem(String title) throws Exception {
        if(!isExist(title)){
            throw new Exception("The title you are trying to loan does not exist.");
        }
        
        try{
            //ITEM EXISTS
            Iterator<Item> iterator = library.iterator();
            while (iterator.hasNext()) {
                Item media = iterator.next();
                if(media.getTitle().equalsIgnoreCase(title)){
                    if(media.isAvailable()){
                        System.out.println("The title you are trying to return is already returned.");
                        System.out.println("");
                    }
                    media.setIsAvailable(true);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void showItems(Store store) {
        for (Item item : library){
            System.out.println(item.niceToString());
        }
    }

    public void addMedia(Item newItem) throws Exception {
        
        if(isExist(newItem.getTitle())){
            throw new Exception("An item with the same title already exists: " + newItem.getTitle());
        }
        
        library.add(newItem);
    }
    
    public boolean isExist(String title){
        for(Item media : library){
            if(media.getTitle().equalsIgnoreCase(title)){
                return true;
            }
        }
        return false;
    }

    public void removeMedia(String title) throws Exception {
        
        if(!isExist(title)){
            throw new Exception("The Item you requested to remove does not exist: " + title);
        }
        //ITEM EXISTS
        Iterator<Item> iterator = library.iterator();
        while (iterator.hasNext()) {
            Item media = iterator.next();
            if(media.getTitle().equalsIgnoreCase(title)){
                iterator.remove();
            }
        }
    }
}