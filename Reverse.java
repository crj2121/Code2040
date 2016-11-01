import java.lang.*;
import java.io.*;
import java.net.*;
public class Reverse
{
	public static String reverseString(String string)
	{
		StringBuffer sb = new StringBuffer(); 
		for(int i=string.length()-1; i>=0; i--)
		{
			sb.append(string.charAt(i));
		}
		String reversed = sb.toString(); 
		return reversed;

	}

	public static String getToken(URL url, String string) throws Exception 
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
			break;
		}
		input.close();
		return str.toString();
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

	public static void main (String[] args) throws Exception
	{
		URL endpoint1 = new URL("http://challenge.code2040.org/api/reverse");
		String token = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"}";
		String reversedStr = getToken(endpoint1, token);
		String changed = reverseString(reversedStr);
		//System.out.println(changed);
		String token2 = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"";
		String giveBack = "\"string\":"+"\""+ changed + "\"}";
		String combine = token2 + " , " + giveBack;
		URL endpoint2 = new URL("http://challenge.code2040.org/api/reverse/validate");
		sendBack(endpoint2, combine);

	}
}