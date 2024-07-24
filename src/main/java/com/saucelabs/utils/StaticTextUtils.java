package com.saucelabs.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.saucelabs.constants.FrameworkConstants;
import com.saucelabs.exceptions.KeyNotFoundException;
import com.saucelabs.exceptions.XMLParsingFailedException;

public class StaticTextUtils {
	
	private StaticTextUtils() {}
	
	private static final Map<String,String> MYMAP= new HashMap<>();
	
	
    static {
	
	try {
		
    	//Get Document Builder
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(new File(FrameworkConstants.getStatictext()));
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		
		//Get all elements
		NodeList nList = document.getElementsByTagName("string");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    Element eElement = (Element) node;
		    // Store each element key value in map
		    MYMAP.put(eElement.getAttribute("name"), eElement.getTextContent());
		 }
		
		}
	
	
	}
		catch(Exception e)
		{
			throw new XMLParsingFailedException("Failed to parse XML");
		}
		
		}
		

      public static synchronized  String getStaticText(String key)
      {
    	  if(Objects.isNull(key))
    	  {
    		  throw new KeyNotFoundException("key not found : "+key);
    	  }
     
         return MYMAP.get(key);
      
      
      }
    	  
      }
   
    
	
	   
	 
