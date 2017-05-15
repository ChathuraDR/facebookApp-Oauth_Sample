package com.chathura.oauth.servlet;


import com.chathura.oauth.data.storeData;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Created by ChathuraDR on 5/12/2017.
 */
public class oauthCallback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Detect the presence of an authorization code
        String authorizationCode = req.getParameter("code");
        if (authorizationCode != null && authorizationCode.length() > 0) {
            final String TOKEN_ENDPOINT = "https://graph.facebook.com/oauth/access_token";
            final String GRANT_TYPE = "authorization_code";
            final String REDIRECT_URI = "http://localhost:8080/facebookFunApp/callback";
            final String CLIENT_ID = "1443129495732404";
            final String CLIENT_SECRET = "c9960f3704d2b5edad0b955e070ced37";
            //instead of hardcoding these client id,client secret you can add a configure file and read form it.
            // Generate POST request
            HttpPost httpPost = new HttpPost(TOKEN_ENDPOINT + "?grant_type=" +
                    URLEncoder.encode(GRANT_TYPE, "UTF-8" ) +
                    "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                    "&client_id=" + URLEncoder.encode(CLIENT_ID, "UTF-8") +
                    "&code=" + authorizationCode);
            // Add "Authorization" header with encoded client credentials
            String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
            httpPost.setHeader("Authorization", "Basic " + encodedClientCredentials);
            // Make the access token request
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // Handle access token response
            Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            //resp.getWriter().write(line);
            // Isolate access token
            String accessToken = null;
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(line);
                JSONObject jsonObj = (JSONObject) obj;
                accessToken = jsonObj.get("access_token").toString();
                //resp.getWriter().write(accessToken);
            } catch (ParseException e) {
                System.out.println(e);
            }
            // Request profile data with access token
            String requestUrl = "https://graph.facebook.com/v2.8/me?fields=name,gender";
            httpPost = new HttpPost(requestUrl);
            httpPost.addHeader("Authorization", "Bearer " + accessToken);
            List<NameValuePair> urlPara = new ArrayList<NameValuePair>();
            urlPara.add(new BasicNameValuePair("method", "get"));
            httpPost.setEntity(new UrlEncodedFormEntity(urlPara));
            HttpResponse httpResponse1 = httpClient.execute(httpPost);
            reader = new InputStreamReader(httpResponse1.getEntity().getContent());
            bufferedReader = new BufferedReader(reader);
            String line1 = bufferedReader.readLine();
            String respnse = addPicUrl(accessToken,line1);
            httpClient.close();

            //generate a random key
            Random rnd = new Random();
            int randomNum = rnd.nextInt(9999999);
            //get result json string and put them in a map along with the random number(key)
            storeData.getInstance().addResource(Integer.toString(randomNum),respnse);
            //redirect the user with url parameter so that result.jsp can get that ajax data and process it.
            resp.sendRedirect("result.jsp?key=" + String.valueOf(randomNum));

        }else{
            resp.sendRedirect("error.jsp");
        }


    }
    public String addPicUrl(String accessToken,String json) throws IOException {
        String line1 = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String requestUrl = "https://graph.facebook.com/v2.8/me/picture?height=200&width=200";
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.addHeader("Authorization", "Bearer " + accessToken);
        List<NameValuePair> urlPara = new ArrayList<NameValuePair>();
        urlPara.add(new BasicNameValuePair("method", "get"));
        httpPost.setEntity(new UrlEncodedFormEntity(urlPara));
        HttpResponse httpResponse1 = httpClient.execute(httpPost);
        //extract location from the http response
        String location = httpResponse1.getLastHeader("Location").getValue();
        //append it with previous data return by facebook
        return json.substring(0,json.length()-1) +",\"location\":\""+location+"\"}";
    }
}
