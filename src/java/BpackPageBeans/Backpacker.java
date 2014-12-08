/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPageBeans;

import java.util.ArrayList;
/**
 *Backpackers are essentially just to hold data. Doesnt have any function besides storing and returning data
 * @author eric
 */
public class Backpacker {
    
    private ArrayList<String> items;
    private String name;
    private int currIndex = 0;
    
    public Backpacker(){
        name = "unnamed";
        items = new ArrayList<String>();
        
    }
    
    public ArrayList<String> getItems(){
        return items;
    }
    
    public void setItems(String s){
        
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void addItem(String item){
       items.add(item);
    }
    
    public boolean removeItem(String item){
      return true;
    }
}
