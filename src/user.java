
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




@WebServlet("/user")
public class user extends HttpServlet 
{
	
	
	//ArrayList<String> user_id_list = new ArrayList<>();
	ArrayList<String> user_first_name_list = new ArrayList<>();
	ArrayList<String> user_last_name_list = new ArrayList<>();
	//ArrayList<String> user_city_list = new ArrayList<>();
	//ArrayList<String> user_country_list = new ArrayList<>();
	
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
        User person = gson.fromJson(temp, User.class);
        
        
       
       
        user_first_name_list.add(person.firstName );
        user_last_name_list.add(person.lastName );
        System.out.println("User " + person.firstName + " " + person.lastName + " Added to data base!");
        
     
        
    }
	
    


protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
	
	response.setContentType("application/json"); 
	PrintWriter writer = response.getWriter();
	int us_id = Integer.parseInt(request.getParameter("userid"));
	//System.out.print(us_name);
	//String us_last = request.getParameter("lastName");
	//System.out.print(us_last);
	
		/*
		for (int i = 0; i < user_first_name_list.size(); i++)
		{
			if user_first_name_list
            
        }   */
	 
	GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    User search = new User(user_first_name_list.get(us_id), user_last_name_list.get(us_id));
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
    User person = gson.fromJson(temp, User.class);
    
    
   
   
    user_first_name_list.add(person.firstName );
    user_last_name_list.add(person.lastName );
    System.out.println("User " + person.firstName + " " + person.lastName + " Added to data base!");
    
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
    User delete= gson.fromJson(temp, User.class);
    int saved  = 0;
    
    for(int i = 0; i < user_first_name_list.size(); i++)
    {
    	if (user_first_name_list.get(i)== delete.firstName)
    	{
    		if (user_last_name_list.get(i)== delete.lastName)
        	{
        		
        		saved = i;
        		break;
        	}
    	}
    }
    user_first_name_list.remove(saved);
	user_last_name_list.remove(saved);
}
        
}


class User 
{
    public String firstName;
    public String lastName;   
    
    
    public User()
    {
        
    }
    
    public User(String name, String surname)
    {
        this.firstName = name;
        this.lastName = surname;      
        
    }
    


}
  
   
    
    




 

