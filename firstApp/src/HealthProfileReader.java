import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HealthProfileReader {
          Document doc;
		  XPath xpath;
		  
		  public void loadXML() throws ParserConfigurationException, SAXException, IOException {
		        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		        domFactory.setNamespaceAware(true);
		        DocumentBuilder builder = domFactory.newDocumentBuilder();
		        doc = builder.parse("people.xml");
		        //creating xpath object
		        getXPathObj();
		    }
		  
		  public XPath getXPathObj() {
		        XPathFactory factory = XPathFactory.newInstance();
		        xpath = factory.newXPath();
		        return xpath;
		    }
		  
		 //get Healt profile for a speceifc person
		  public void printHealthProfileById(String id) throws XPathExpressionException {
			    XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile/*/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        System.out.println(id+" health profile:");
		        for (int i = 0; i < nodes.getLength(); i++) {
		      	  if(i==0){
		      		  System.out.print("Last update: ");
		      	  }
		      	  if(i==1){
		      		  System.out.print("Heigth: ");
		      	  }
		      	  if(i==2){
		      		  System.out.print("Weight: ");
		      	  }
		      	  if(i==3){
		      		  System.out.print("IBM: ");
		      	  }
		  	        System.out.println(nodes.item(i).getNodeValue());
		  	    }
		       
		    }
		  
		  //method to get Height by firstName and lastName
		  public double getHeight(String personName,String personSurname) throws XPathExpressionException {

			    XPathExpression expr = xpath.compile("/people/person[firstname='" + personName + "' and lastname='" + personSurname + "']/healthprofile/height/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        double height=Double.parseDouble((nodes.item(0).getNodeValue()));
		        return height;
		    }
		  
		  //method to get Weight by firstName and lastName
		  public double getWeight(String personName,String personSurname) throws XPathExpressionException {
			    XPathExpression expr = xpath.compile("/people/person[firstname='" + personName + "' and lastname='" + personSurname + "']/healthprofile/weight/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        double height=Double.parseDouble((nodes.item(0).getNodeValue()));
		        return height;
		    }
		  
		  //method to get Height by id
		  public double getHeightById(String id) throws XPathExpressionException {
			    XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile/height/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        double height=Double.parseDouble((nodes.item(0).getNodeValue()));
		        return height;
		    }
		  
		  //method to get Weight by id
		  public double getWeightById(String id) throws XPathExpressionException {
			    XPathExpression expr = xpath.compile("/people/person[@id='"+id+"']/healthprofile/weight/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        double height=Double.parseDouble((nodes.item(0).getNodeValue()));
		        return height;
		    }
		  
		  //method to get all detail of all people
		   public void printAll()throws XPathExpressionException {
			    XPathExpression expr = xpath.compile("/people/person/*/text()");
		        Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		        NodeList nodes=(NodeList) node;
		        String firstName;
		        String lastName;
		       
		        int count=0;
		        
		        for (int i = 0; i < nodes.getLength(); i++) { 
		        	if(count<3){
		        		if(count==0){
		        			System.out.print("FirstName: ");
		        		}
		        		if(count==1){
		        			System.out.print("LastName: ");
		        		}
		        		if(count==2){
		        			System.out.print("Birthdate: ");
		        		}
		        	    System.out.println(nodes.item(i).getNodeValue());	
		        	}
		        	if(count==7){
		        		    firstName=nodes.item(i-7).getNodeValue();
		        		    lastName=nodes.item(i-6).getNodeValue();

		        		    System.out.println("");
			        		System.out.println("Healt Profile: ");
			        		
		        		    XPathExpression expr2 = xpath.compile("/people/person[firstname='" + firstName + "' and lastname='" + lastName + "']/healthprofile/*/text()");
				            Object node2 =  expr2.evaluate(doc, XPathConstants.NODESET);
				            NodeList nodes2=(NodeList) node2;
				            for (int j = 0; j < nodes2.getLength(); j++) { 
				            	if(j==0){
				        			System.out.print("Last update: ");
				        		}
				            	if(j==1){
				        			System.out.print("Weight: ");
				        		}
				            	if(j==2){
				        			System.out.print("Heigth: ");
				        		}
				            	if(j==3){
				        			System.out.print("IBM: ");
				        		}
				            	 System.out.println(nodes2.item(j).getNodeValue());
				            }
				            System.out.println("=====================");
		        	   count=-1;
		        	}
		        	count++;
		        }
		   }
		   
		   //print Person with fulfill weight condition
		   public void printPersonByCondition(int weight,String operator) throws XPathExpressionException { 
			   
			   if(operator.equals(">")){
			       
			         XPathExpression expr = xpath.compile("//healthprofile[weight>'"+weight+"']/../*/text()");
			         Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		             NodeList nodes=(NodeList) node; 
			   
		             int count=0;
		             for (int i = 0; i < nodes.getLength(); i++) {
			             if(count<3){
			        		if(count==0){
			        			System.out.print("FirstName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==1){
			        			System.out.print("LastName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==2){
			        			System.out.println("=========================");
			        		}
			        	   
			        	 }
			        	 if(count==7){
			        	    	count=-1;
			        	 }
			        	 count++;
			         }
			   }
			   
			   if(operator.equals("<")){
			         XPathExpression expr = xpath.compile("//healthprofile[weight<'"+weight+"']/../*/text()");
			         Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		             NodeList nodes=(NodeList) node; 
			   
		             int count=0;
		             for (int i = 0; i < nodes.getLength(); i++) {
			             if(count<3){
			        		if(count==0){
			        			System.out.print("FirstName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==1){
			        			System.out.print("LastName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==2){
			        			System.out.println("=========================");
			        		}
			        	   
			        	 }
			        	 if(count==7){
			        	    	count=-1;
			        	 }
			        	 count++;
			         }
			   }
			   if(operator.equals("=")){
			       
			         XPathExpression expr = xpath.compile("//healthprofile[weight='"+weight+"']/../*/text()");
			         Object node =  expr.evaluate(doc, XPathConstants.NODESET);
		             NodeList nodes=(NodeList) node; 
			   
		             int count=0;
		             for (int i = 0; i < nodes.getLength(); i++) {
			             if(count<3){
			        		if(count==0){
			        			System.out.print("FirstName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==1){
			        			System.out.print("LastName: ");
			        			 System.out.println(nodes.item(i).getNodeValue());	
			        		}
			        		if(count==2){
			        			System.out.println("=========================");
			        		}
			        	   
			        	 }
			        	 if(count==7){
			        	    	count=-1;
			        	 }
			        	 count++;
			         }
			   }  	   
		    	  
		   }
			   
			   
	
		   
public static void main(String[] args) throws ParserConfigurationException, SAXException,
         IOException, XPathExpressionException {
    
	String id;
	String op;
    int weight;
	HealthProfileReader test = new HealthProfileReader();
	test.loadXML();
	
	double height=test.getHeight("Paul","Pogba");
	
    height=test.getHeightById("0002");
 

	int count=0;
	if(args[0].equals("-p")){
		if(args[1].equals("-a")){
			if(args.length==2){
			test.printAll();
			}
			if(args.length==5){
				if(args[2].equals("-w")){
					weight=Integer.parseInt(args[3].substring(1));
					op=new String(""+args[4].substring(1));
					test.printPersonByCondition(weight, op);
					
					
				}
				else{
					System.out.println("Syntax Error !!");
					System.out.println("Syntax is:");
					System.out.println("-p -a to print all");
					System.out.println("-p -h -'insertId' note: id is between 0001 and 9999");
					System.out.println("-p -a -w -'insertWeight' -'insertOperator' ");
				}
			}
			
			
		}
		if(args[1].equals("-h")){
			if(args.length==3){
				id=new String(""+args[2].substring(0));
				
				if(id.length()==1){
					id=new String("000"+id);
					//System.out.println("id: "+id);
				}
				else if(id.length()==2){
					id=new String("00"+id);
					//System.out.println("id: "+id);
				}
				else if(id.length()==3){
					id=new String("0"+id);
					//System.out.println("id: "+id);
				}
				else if(id.length()==4){
					id=new String(""+id);
					//System.out.println("id: "+id);
				}
			
				
				test.printHealthProfileById(id);
				
			}
			else{
				System.out.println("Syntax Error !!");
				System.out.println("Syntax is:");
				System.out.println("-p -a to print all");
				System.out.println("-p -h -'insertId' note: id is between 0001 and 9999");
				System.out.println("-p -a -w -'insertWeight' -'insertOperator' ");
			}
		}
		
			
	}
	else{
		System.out.println("Syntax Error !!");
		System.out.println("Syntax is:");
		System.out.println("-p -a to print all");
		System.out.println("-p -h 'insertId' note: id is between 0001 and 9999");
		System.out.println("-p -a -w -'insertWeight' -'insertOperator' ");
	
	}

  }
}

/*Extend the example above to include at least 20 people (maybe your friends with fake names, extra points if you find a bigger datasource)

1)Use xpath to implement methods like getWeight and getHeight
2)Make a function that prints all people in the list with detail (if >20, paginated)
3)A function that accepts id as parameter and prints the HealthProfile of the person with that id
4)A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition (i.e., >80Kg, =75Kg, etc.).
5)Create the XML schema XSD file for the example XML document of people.
6)Write a java application that does the marshalling and un-marshalling using JAXB and generated classes with JAXB XJC.
7)Make your java application to convert also JSON*/
		    



