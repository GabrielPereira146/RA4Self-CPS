package br.unesp.rc.httpclient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author gabri
 */
public class CustomHttpClientUtils {

    private CustomHttpClientUtils() {
    }

    public static void setValueByHttpPut(String url, String json) throws UnsupportedEncodingException, IOException {
        // final String URL = "http://localhost:8084/shhc/Temperature/update";
        System.out.println("URL: " + url);
        System.out.println("JSON: " + json);
        HttpPut httpPut = new HttpPut(url);
        StringEntity entity = new StringEntity(json);
        httpPut.setEntity(entity);
        httpPut.setHeader("Content-type", "application/json");

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpPut);

        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println("API CODE: " + statusCode);
    }

    public static String getValueByHttp(String url) {

            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();

                InputStream inputStream = entity.getContent();

                try (Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
                    String result = s.hasNext() ? s.next() : "";
                    client.close();
                    return result;
                }
            } catch (IOException ex) {
                System.out.println("Message: " + ex.getMessage());
                return null;
            }

    }
}
