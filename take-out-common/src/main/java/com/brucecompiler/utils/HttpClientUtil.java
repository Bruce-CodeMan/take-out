package com.brucecompiler.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Map;

@Slf4j
public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> paramMap) {

        String result = "";

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uriBuilder = new URIBuilder(url);
            if(paramMap != null) {
                paramMap.forEach(uriBuilder::addParameter);
            }

            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);

            try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES){
                  result = EntityUtils.toString(response.getEntity(), "UTF-8");
                } else{
                    log.error("Unexpected response status: {}", statusCode);
                }
            }
        } catch(Exception e) {
            log.error("Exception: {}, {}", url, e.getMessage());
        }

        return result;
    }
}
