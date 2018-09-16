/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booleanmodel;

import java.io.*;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author toshiba
 */
public class bool 
{
    private String query;
    private String exp;//boolean vector of query after its excution
    private String file1_name="doc1.txt";
    private String file2_name="doc2.txt";
    private String file3_name="doc3.txt";
    private String file4_name="doc4.txt";
    private List<String> splited_file1;
    private List<String> splited_file2;
    private List<String> splited_file3;
    private List<String> splited_file4;
    private ArrayList<String> boolean_vector=new ArrayList();
    private ArrayList<String> splited_query;
    private List<String> terms=new ArrayList();
    private HashMap<String,String> comined_vector_with_term=new HashMap<String,String>();
    private String res="";
   // private String result="";
public bool()
    {
        this.fillTermsList();
        this.file1();
        this.file2();
        this.file3();
        this.file4();
        this.setboolean();
        this.fillMap();//all the previous called function are required for filling the hashmap
        this.scanQuery();
        this.splitQuery();
        this.excuteOp(splited_query);
//        this.result=this.calc(this.splited_query,new ArrayList<String>(),0);
        //System.out.println("result :- "+res);
        this.matchDocuments();
    /* scanQuery();  
     file1();
     file2();
     file3();
     file4();
     fillTermsList();
     setboolean();
     fillMap();
     and("information","retrieval");
     or("information","retrieval");
     not("information");
     excuteOp();*/
     
    }
private void fillTermsList()
      {
          this.terms.addAll(Arrays.asList("information","retrieval","mining","data"));
      }
private void fillMap()
    {
        int i;
        for(i=0;i<this.terms.size();i++)
        {
            this.comined_vector_with_term.put(this.terms.get(i),this.boolean_vector.get(i));
            
        }
        System.out.println(this.comined_vector_with_term);
    }
      
private void file1()//split file 1
    {
       scanFile(this.file1_name);
    }
private void file2()//split file 2
    {
       scanFile(this.file2_name); 
    }
private void file3()//split file 3
    {
       scanFile(this.file3_name); 

    }
private void file4()//split file 4
    {
       scanFile(this.file4_name); 

    }
    
//this function scan a file into a string and split string into terms
private void scanFile(String file_name)
    {
       
        
        String line="";
        try
        {
        FileReader file_reader= new FileReader(file_name);
        BufferedReader bufferedReader = 
                new BufferedReader(file_reader);

            while((line = bufferedReader.readLine()) != null) {
               // splitFile(line);
               if(file_name.equals(this.file1_name))
                   this.splited_file1=splitFile(line);
               if(file_name.equals(this.file2_name))
                   this.splited_file2=splitFile(line);
               if(file_name.equals(this.file3_name))
                   this.splited_file3=splitFile(line);
               if(file_name.equals(this.file4_name))
                   this.splited_file4=splitFile(line);
            }   

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("file is not available");
        }
        catch(IOException e)
        {
            System.out.println("error reading file '"+file_name+"'");
        }
    }
    
//this function scans user query then call the function that split the entered query
private void scanQuery()
    {
            
        Scanner input=new Scanner(System.in);
        this.query=input.nextLine();
        if(!(this.query.contains("and")||this.query.contains("or")||this.query.contains("not")))
        {
            System.out.print("invalid query");
        }
        splitQuery();
        
    }
    
private void splitQuery()
    {
        ArrayList<String> temp=new ArrayList<String>(Arrays.asList(this.query.split("\\s+")));
                this.splited_query = new ArrayList<String>();

        for(int i=0;i<temp.size();i++){
            if(i!=0){
               // this.splited_query.add(temp.get(i).substring(0,temp.get(i).length() ));
                  this.splited_query.add(temp.get(i));
            }else
                this.splited_query.add(temp.get(i));
        }
    }
    //( data and ( information or data ) )
private List<String> splitFile(String tokenizer_input)
    {
        List<String> splited_file= new ArrayList<String>(Arrays.asList(tokenizer_input.split("\\s+")));
        return splited_file;
    }
    
private void setboolean()
    {
        this.boolean_vector=new ArrayList();
        int i;
        for (i=0;i<this.terms.size();i++)
        {
                    String vector="";
            if(this.splited_file1.contains(this.terms.get(i)))
                vector+="1";
            else
                vector+="0";
            
            if(this.splited_file2.contains(this.terms.get(i)))
                vector+="1";
            else
                vector+="0";
            
            if(this.splited_file3.contains(this.terms.get(i)))
                vector+="1";
            else
                vector+="0";
            
            if(this.splited_file4.contains(this.terms.get(i)))
                vector+="1";
            else
                vector+="0";
            
            this.boolean_vector.add(vector);
        }
        } 
public boolean checkIfBoolean(String term){
    
    for(int i=0;i<term.length();i++){
        if(term.charAt(i)=='0'||term.charAt(i)=='1')
            return true;
    }
    
    return false;
}
private String and(String prev_term, String next_term)
  
  {
      String prev_vector=prev_term;
      String next_vector=next_term;
      if(!this.checkIfBoolean(prev_term))
      prev_vector=this.comined_vector_with_term.get(prev_term);
      if(!this.checkIfBoolean(next_term))
       next_vector=this.comined_vector_with_term.get(next_term);
       String result="";
       int i;
       
      for(i=0;i<4;i++)
       {
           if(prev_vector.charAt(i)=='1'&&next_vector.charAt(i)=='1')
               result+="1";
           else
               result+="0";
       }
     
  
 
       return result;
     }
private String or(String prev_term, String next_term)
  {  
      String prev_vector=prev_term;
      String next_vector=next_term;
      if(!this.checkIfBoolean(prev_term))
      prev_vector=this.comined_vector_with_term.get(prev_term);
      if(!this.checkIfBoolean(next_term))
       next_vector=this.comined_vector_with_term.get(next_term);
       String result="";
       int i;
       for(i=0;i<prev_vector.length()&&i<next_vector.length();i++)
       {
           if(prev_vector.charAt(i)=='1'||next_vector.charAt(i)=='1')
               result+="1";
           else
               result+="0";
       }
       System.out.println(result);
       return result;
     }
private String not(String term)
  {  
      String vector=term;
      
      if(!this.checkIfBoolean(term))
      vector=this.comined_vector_with_term.get(term);
     
      
       String result="";
       int i;
       for(i=0;i<4;i++)
       {
           if(vector.charAt(i)=='0')
               result+="1";
           else
               result+="0";
       }
       System.out.println(result);
       return result;
     }
private String excuteOp(ArrayList<String> input)
  {
      try
      {
        
      int i;
      for(i=0;i<input.size();i++)
      {
          
          if(input.get(i).equalsIgnoreCase("NOT")&& !input.get(i+1).equals(""))
          {
              input.remove(i);
              res=not(input.get(i)).trim();
              input.set(i,res);
          }
      }
      for(i=0;i<input.size();i++)
      {
          if(input.get(i).equalsIgnoreCase("AND")&&
            !input.get(i-1).equals("")&&
            !input.get(i+1).equals(""))
          {
              input.remove(i);
              String prev_term=input.get(--i).trim();
              input.remove(i);
              String next_term=input.get(i).trim();
              res=this.and(prev_term, next_term).trim();             
              input.set(i,res);
          }
       
      }
       for(i=0;i<input.size();i++)
      {
          if(input.get(i).equalsIgnoreCase("OR")&&
            !input.get(i-1).equals("")&&
            !input.get(i+1).equals(""))
          {
              input.remove(i);
              String prev_term=input.get(--i).trim();
              input.remove(i);
              String next_term=input.get(i).trim();
              res=this.or(prev_term, next_term).trim();
              input.set(i,res);

          }
       
      }
             
      }
      catch(NullPointerException e)
      {
          System.out.println();
          
      }
     
     return res;
  }

/*public  String  calc(List<String> input, ArrayList<String> newInput,int i){
        
        if(input.size()==0){
           String res=this.excuteOp(newInput);
                   
            return res;
        }
       
       
        if(input.get(i).equals(")")){
            
           String res=this.excuteOp(newInput);
           input.remove(i);
           input.add(i, res);
           
         
           return calc(input,new ArrayList<String>(),i++);
        }
        
        if(input.get(i).equals("("))
        {
            input.remove(i);
            ArrayList<String> res=new ArrayList<String>();
            return calc(input,res,i);
        }
        else
        {
            newInput.add(input.get(i));
            input.remove(i);
            return calc(input,newInput,i);
        }
     //  return "error";
    }*/
private void matchDocuments()
{
    int i;
    for(i=0;i<this.res.length();i++)
    {
        if(this.res.charAt(i)=='1')
        {
            System.out.println("doc"+(i+1));
        }
    }
    if(this.res.contains("0000"))
            System.out.println("no matched document");
    
}
    }
    
