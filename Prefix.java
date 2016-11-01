//import java.lang.*;
import java.io.*;
import java.net.*;
import org.json.*;
//import java.util.*;

public class Prefix
{
	public static void main(String[] args) throws Exception 
	{
		URL endpoint1 = new URL("http://challenge.code2040.org/api/prefix");
		String token = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"}";
		JSONObject dictionary = new JSONObject(); 
		dictionary = getToken(endpoint1, token);
		System.out.println(dictionary);
		JSONArray hay = dictionary.getJSONArray("array");
		String pref = dictionary.getString("prefix");
		JSONArray changed = new JSONArray();  
		for(int i=0; i<hay.length(); i++)
		{
			if(hay.getString(i).startsWith(pref) == false)
			{
				changed.put(hay.getString(i));
			}
		}
		//dictionary.put("token","4ddf6e752cd8b60989a2db2d87b0438"); 
		
		String token2 = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"";
		String giveBack = "\"array\":" + changed + "}";
		String combined =  token2 + " , " + giveBack;
		System.out.println(combined);
		URL endpoint2 = new URL("http://challenge.code2040.org/api/prefix/validate");
		sendBack(endpoint2, combined);
	}
	
	public static JSONObject getToken(URL url, String string) throws Exception 
	{
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setDoOutput(true);


		connect.setRequestMethod("POST");
		connect.setRequestProperty("Content-Type" , "application/json");

		OutputStreamWriter output = new OutputStreamWriter(connect.getOutputStream());
		output.write(string);
		output.flush();
		output.close();


		BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String collect;
		StringBuffer str = new StringBuffer(); 
		while((collect = input.readLine())!= null)
		{
			str.append(collect);
		
		}
		input.close();

		String result = str.toString(); 
		JSONObject object = new JSONObject(result); 
		//object = getJsonObject(result);

		return object; 
	}

	public static void sendBack(URL url, String string) throws Exception 
	{

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);


		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type" , "application/json");

		OutputStreamWriter output = new OutputStreamWriter(con.getOutputStream());
		output.write(string);
		output.flush();
		output.close();

		BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String collect;
		while((collect = input.readLine())!= null)
		{
			System.out.println(collect);
		}
		input.close();

	}

}