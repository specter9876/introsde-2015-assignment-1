

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;


public class JSONExample {
	  
    
 private static FileWriter f;

public static void main(String[] args) throws org.json.JSONException {
	
	 //controll which kind of option is needed
	    int opt=10;
        File file=null;
        //select the convert Json to Xml function
	    if ((args[0].equals("-JtX"))&&(args.length==2)){
	    	System.out.println("json to xml");
	    	file=new File("./"+args[1]);
	    	opt=0;
	    	
	    }
	    //select the convert Xml to Json function
	    else if ((args[0].equals("-XtJ"))&&(args.length==2)){
	    	System.out.println("xml to Json");
	    	file=new File("./"+args[1]);
	    	opt=1;
	    	
	    
	    }
	    else if (args[0].equals("-JtJSON")){
	    	System.out.println("java to Json");
	    	opt=2;
	    }
	    else{
	    	System.out.println("input error:");
	    	System.out.println("Only -XtJ file.xml or");
	    	System.out.println("-JtX file.json");
	    	System.out.println("-JavatJSON  are accept");
	    }
	    //convert Xml to Json case
	    if(opt==1){
    	    try  
              {
    	       FileWriter file2 = new FileWriter ("./file.json");
    	       InputStream inputStream = new FileInputStream(file);  
               StringBuilder builder =  new StringBuilder();  
               int ptr = 0;  
               while ((ptr = inputStream.read()) != -1 ){  
                            builder.append((char) ptr);  
               }  
               String xml  = builder.toString();  
               //convert XML string to JSON object
               org.json.JSONObject json = XML.toJSONObject(xml);
               //save json object inside the file.json
        
               for(int i= 0 ;i < json.toString().split(",").length; i ++){
                 
        	              file2.write(json.toString().split(",")[i]);
        	              if(json.toString().split(",").length-1!=i){
        	            	  file2.write(",");  
        	              }
        	              file2.write("\n");
        	              file2.flush();
               }
            }
    	    catch(Exception e)                
    	    {                   	  
    	    	e.printStackTrace();               
    	    } 
             
	    }
	    //convert Json to XML case
    	if(opt==0){
    		try{

    			 File f2=new File("./"+file);
    			 //read file.json inside a string
    			 String source=FileUtils.readFileToString(f2,"UTF-8");
    			 //convert  strint to JSONObject
    			 org.json.JSONObject json=new org.json.JSONObject(source); 
       	         FileWriter file3 = new FileWriter ("./filefromJSON.xml");
       	         //save json object inside the file.json with human readable tabulation
                file3.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                file3.write("\n");
                file3.flush();
       	         for(int i= 0 ;i < XML.toString(json).split(",").length; i ++){      	    	
       	    	      String w=XML.toString(json).split(",")[i];
       	    	      String e []=w.split("><");
	       	    	  int k=0;
		       	      for (String tmp: e){
		       	    	if(k==0){
		       	    		  file3.write(tmp+">"); 
		       	    		  file3.write("\n");
		       	    	}
	       	    		else if(k==e.length-1){
		       	    			file3.write("<"+tmp); 
		       	    			file3.write("\n");
       	    		    }
	       	    		else{
	       	    			file3.write("<"+tmp+">");
	         	    	    file3.write("\n");
	       	    			 
	       	    		}
	       	    	    file3.flush();
	       	    	    k++;
       	    	      }
       	         }
       	      
              } catch(Exception e)        
              {             
            	  e.printStackTrace();       
              }  
    	}
    
	 if(opt==2){
		
         //from JAVA object to JSON
         HealthProfile hp = new HealthProfile(68.0, 1.72);
         People pallo = new People(new Long(1), "Pallo", "Pinco", "1984-06-21",hp);
         
         
         HealthProfile hp0 = new HealthProfile(93.0, 1.10);
         People doctor = new People(new Long(3), "Doctor", "Web", "2001-03-29",hp0);
         
         HealthProfile hp1=new HealthProfile(800,2.75);
         People john = new People(new Long(2), "John", "Doe", "1985-03-20", hp1);
         
         
         Gson gson = new Gson();
         // convert java object to JSON format,
         // and returned as JSON formatted string
         String json = gson.toJson(pallo);
         String json2 = gson.toJson(doctor);
         String json3 = gson.toJson(john);
         
         
         try {
             
             FileWriter writer = new FileWriter("javatofile.json");
             writer.write(json);
             writer.write(json2);
             writer.write(json3);
             writer.close();
             
         } catch (IOException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	 }

	
 }
}