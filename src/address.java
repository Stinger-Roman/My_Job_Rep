
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Reader;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;


//import jdk.nashorn.internal.parser.JSONParser;




@WebServlet("/address")
public class address extends HttpServlet 
{
	
	
	//ArrayList<String> Addr_id_list = new ArrayList<>();
	ArrayList<String> city_list = new ArrayList<>();
	ArrayList<String> country_list = new ArrayList<>();
	//ArrayList<String> Addr_city_list = new ArrayList<>();
	//ArrayList<String> Addr_country_list = new ArrayList<>();
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
          
    	response.setContentType("application/json"); 
    	
    	StringBuilder body = new StringBuilder();
        char[] buffer = new char[1024];
        int readChars;
        try(Reader reader = request.getReader()){
            while ((readChars=reader.read(buffer))!=-1){
                body.append(buffer,0, readChars);
            }
        }
         
          
        String temp = body.toString();
        
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Addr adr = gson.fromJson(temp, Addr.class);
        
        
       
       
        city_list.add(adr.city );
        country_list.add(adr.country );
        System.out.println("Addr " + adr.city + " " + adr.country + " Added to data base!");
        
     
        
    }
	
    


protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	
	response.setContentType("application/json"); 
	PrintWriter writer = response.getWriter();
	int us_id = Integer.parseInt(request.getParameter("addressid"));
	//System.out.print(us_name);
	//String us_last = request.getParameter("lastName");
	//System.out.print(us_last);
	
		/*
		for (int i = 0; i < city_list.size(); i++)
		{
			if city_list
            
        }   */
	 
	GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    Addr search = new Addr(city_list.get(us_id), country_list.get(us_id));
    String out_str = gson.toJson(search);
    
	
	
        writer.print(out_str);
        writer.flush();
     
	
        
 }





protected void doPut(HttpServletRequest request, HttpServletResponse resp)
        throws ServletException, IOException {
	StringBuilder body = new StringBuilder();
    char[] buffer = new char[1024];
    int readChars;
    try(Reader reader = request.getReader()){
        while ((readChars=reader.read(buffer))!=-1){
            body.append(buffer,0, readChars);
        }
    }
    
    String temp = body.toString();
    
    
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    Addr adr = gson.fromJson(temp, Addr.class);
    
    
   
   
    city_list.add(adr.city );
    country_list.add(adr.country );
    System.out.println("Addr " + adr.city + " " + adr.country + " Added to data base!");
    
}





protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws IOException {
	StringBuilder body = new StringBuilder();
    char[] buffer = new char[1024];
    int readChars;
    try(Reader reader = request.getReader()){
        while ((readChars=reader.read(buffer))!=-1){
            body.append(buffer,0, readChars);
        }
    }
    
    String temp = body.toString();
    
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    Addr delete= gson.fromJson(temp, Addr.class);
    int saved  = 0;
    
    for(int i = 0; i < city_list.size(); i++)
    {
    	if (city_list.get(i)== delete.city)
    	{
    		if (country_list.get(i)== delete.country)
        	{
        		
        		saved = i;
        		break;
        	}
    	}
    }
    city_list.remove(saved);
	country_list.remove(saved);
}
        
}


class Addr 
{
    public String city;
    public String country;   
    
    
    public Addr()
    {
        
    }
    
    public Addr(String city, String country)
    {
        this.city = city;
        this.country = country;      
        
    }
    


}
  
   
    
    




 

