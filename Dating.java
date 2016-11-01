import java.lang.Object;
import java.io.*;
import java.net.*;
import java.time.Instant;
import org.joda.time.*;

import org.json.*;
import java.text.*;
import java.util.Date;


public class Dating
{
	public static void main(String[] args) throws Exception 
	{
		URL endpoint1 = new URL("http://challenge.code2040.org/api/dating");
		String token = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"}";
		JSONObject dictionary = new JSONObject(); 
		dictionary = getToken(endpoint1, token);
		int seconds = dictionary.getInt("interval");
		String date = dictionary.getString("datestamp");
		DateTime new_date = DateTime.parse(date);
		new_date = new_date.plusSeconds(seconds);
		String stamp = Instant.parse(new_date.toString()).toString();
		
		
		
		String token2 = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"";
		String giveBack = "\"datestamp\":" + "\"" + stamp + "\"}";
		String combine = token2 + " , " + giveBack;
	    URL endpoint2 = new URL("http://challenge.code2040.org/api/dating/validate");
	 	sendBack(endpoint2, combine); 
	}
	
	public static JSONObject getToken(URL url, String string) throws Exception 
	{
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setDoOutput(true);


		connect.setRequestMethod("POST");
		connect.setRequestProperty("Content-Type", "application/json");

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