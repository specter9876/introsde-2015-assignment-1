Assignment1_KS
My work is divided into two app and one directory: 
-firstApp contains all works made on Lab03 
-secondApp regards Lab04 point 2 and 3 (marshalling/un-marshalling XML and JSON)
-XML_and_XML_schema directory contains the first point of Lab04 (Create the XML schema XSD file for the example XML (extended))
In order to test:
-download repository
-using terminal go to firstApp or secondApp/MarshallUnmarshalltoXMLandJSON 
-type ant execute.evaluation
The results will be print on screen.

In detail
In firstApp  execute.evaluation will do:
-download dependences and compile
-call target printAll: all xml file will be printed
-call target printHealthProfileById: in build.xml the Id of person is set to 5. So it will be printed the health profile of person id=5
-call target printNameByWeightOperator: it will by print name and surname of people who have weight >80
In secondApp execute.evaluation will do:
-download dependences and compile
-call target JavatoXml: create an XML file using java object (3 person). The result will be put into file.xml 
-call target XmltoJava: convert person in file.xml (created before) into java class.
-call target XmltoJSON: convert  file.xml (created before) to  file.json
-call target JSONtoXML: convert file.json (created before) to filefromJSON.xml
-call target JAVAtoJSON: convert java class to javatofile.json