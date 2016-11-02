import java.io.*;
import java.net.*;
import java.net.HttpURLConnection; 

public class Registration
{
    public static void sendPost (URL url, String string) throws Exception 
    {
    	
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setDoOutput(true);


		connect.setRequestMethod("POST");
		//connect.setRequestProperty("Content-Type" , "application/json");

		OutputStreamWriter output = new OutputStreamWriter(connect.getOutputStream());
		output.write(string);
		output.flush();
		output.close();

		BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String collect;
		while((collect = input.readLine())!= null)
		{
			System.out.println(collect);
		}
		input.close();
    }


	public static void main (String[] args) throws Exception
	{
		String token = "{\"token\":\"4ddf6e752cd8b60989a2db2d87b0438e\"";
		String githubURL = "\"github\":\"https://github.com/crj2121/Code2040\"}";
		String combine = token + " , " + githubURL;
		URL registEndpoint = new URL("http://challenge.code2040.org/api/register");
		sendPost(registEndpoint, combine);
	}

}