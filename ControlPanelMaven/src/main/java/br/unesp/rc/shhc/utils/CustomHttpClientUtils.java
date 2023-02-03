/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.rc.shhc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author gabri
 */
public class CustomHttpClientUtils {

    private CustomHttpClientUtils() {
    }

    public static void setValue(String json) {
        String url = "http://localhost:8080/shhc/Temperature/update";
        System.out.println("URL: " + url);

        int cont = 0;
        while (true) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(url);
                CloseableHttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();

                InputStream inputStream = entity.getContent();

                Scanner s = new Scanner(inputStream).useDelimiter("\\A");
                String result = s.hasNext() ? s.next() : "";

                System.out.println("Result[" + cont + "]: " + result);
                //System.out.println("Response: " + response.toString());

                Thread.sleep(3000);
                client.close();
                cont++;
            } catch (IOException | InterruptedException ex) {
                System.out.println("Message: " + ex.getMessage());
            }
        }
    }
}
