package APIFETCH2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class APIFETCH2 {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			String lat = sc.nextLine();
			String lon = sc.nextLine();
			String key =keyvaluestorage.KEYVALUE;
			getApi(lat,lon,key);
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
	public static void getApi(String lati,String longi,String keyvalue) throws URISyntaxException, ClientProtocolException, IOException, APINOTFOUNDEXCEPTION {
		URIBuilder url = new URIBuilder("https://api.weatherbit.io/v2.0/current");
		url.addParameter("lat", lati);
		url.addParameter("lon", longi);
		url.addParameter("key", keyvalue);
		HttpGet detdata = new HttpGet(url.build());
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(detdata);
		if(response.getStatusLine().getStatusCode()==200) {
			HttpEntity enti = response.getEntity();
			String s = EntityUtils.toString(enti);
			System.out.println(s);
//			JSONObject j = new JSONObject(s);
//			System.out.println(j);
		}else {
			throw new APINOTFOUNDEXCEPTION("NOT FOUND");
		}
	}
}
