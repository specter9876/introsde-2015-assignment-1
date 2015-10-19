
 
import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
 
public class JAXBExample {
	public static void main(String[] args) {
		int op=10;
 		
		if((args[0].equals("-XtJava"))&&(args.length==2)){
			op=1;

		}
		else if((args[0].equals("-JavatX"))&&(args.length==1)){
			op=0;
		}
		else{
			System.out.println("Syntax Error:");
			System.out.println("-XtJava file.xml ");
			System.out.println("-JavatX");
			
		}
	if(op==0){
	   Person person=new Person();
	   person.setPerson(new ArrayList<People>());
	  
	  
	  HealthProfile hp = new HealthProfile(68.0, 1.72);
	  People pallo = new People(new Long(1), "Pallo", "Pinco", "1984-06-21",hp);
	  
        
      HealthProfile hp0 = new HealthProfile(93.0, 1.10);
      People doctor = new People(new Long(3), "Doctor", "Web", "2001-03-29",hp0);
        
	  HealthProfile hp1=new HealthProfile(800,2.75);
	  People john = new People(new Long(2), "John", "Doe", "1985-03-20", hp1);
	  
	  person.getPerson().add(pallo);
	  person.getPerson().add(john);
      person.getPerson().add(doctor);

	  try {
 
		File file = new File("file.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
       // System.out.println("size: "+person.getPerson().size());
    
        
		jaxbMarshaller.marshal(person, file);
		jaxbMarshaller.marshal(person, System.out);
 
	      } catch (JAXBException e) {
		e.printStackTrace();
	      }
	}
	if(op==1){

	  try {
		  
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Person person2 = (Person) jaxbUnmarshaller.unmarshal(file);
			System.out.print(person2.getPerson().get(0).getFirstname()+" ");
			System.out.println(person2.getPerson().get(0).getLastname());
			System.out.print(person2.getPerson().get(1).getFirstname()+" ");
			System.out.println(person2.getPerson().get(1).getLastname());
          System.out.print(person2.getPerson().get(2).getFirstname()+" ");
          System.out.println(person2.getPerson().get(2).getLastname());
			
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	}

	}
}