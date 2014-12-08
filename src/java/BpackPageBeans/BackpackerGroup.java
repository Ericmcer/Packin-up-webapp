/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BpackPageBeans;

/**
 *
 * @author eric
 */
public class BackpackerGroup {
    private Backpacker[] group;
    private int tripDays;
    private int groupSize;
    String nameList;
    
    public BackpackerGroup(int size, int days){
       group = new Backpacker[size];
       tripDays = days;
       groupSize = size;
       for(int i=0;i<size;i++){
           group[i] = new Backpacker();
       }
    }
    
    public int getTripDays(){
        return tripDays;
    }
    public int getGroupSize(){
        return groupSize;
    }
    
    public Backpacker[] getGroup(){
        return group;
    }
    
    public void setGroup(String contents){
        String [] contentParts = contents.split(";");
        String currentContents;
        String [] currentContentParts;
        for(int i = 0; i < groupSize; i++){
            
            if(contentParts.length > 0){
                currentContents = contentParts[i];
                currentContentParts = currentContents.split(",");
            
                for(int j = 0; j < currentContentParts.length; j++ ){
                    group[i].addItem(currentContentParts[j]);
                }
            }
        }   
    }
    public void setNameList(String s){
        nameList = s;
        String [] nameParts = nameList.split(",");
        for(int i = 0; i < groupSize;i++){
            group[i].setName(nameParts[i]);
        }
    }
    public String getNameList(){
        return nameList;
    }
   
    
    public void addBackpackContents(String contents){
        String [] contentParts = contents.split(";");
        String currentContents;
        String [] currentContentParts;
        for(int i = 0; i < groupSize; i++){
            currentContents = contentParts[i];
            currentContentParts = currentContents.split(",");
            
            for(int j = 0; j < currentContentParts.length; j++ ){
                group[i].addItem(currentContentParts[j]);
            }
        }
    }
}
