package org.example;

import org.json.JSONObject;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String myurl="https://api.chucknorris.io/jokes/random";
        HttpURLConnection connection=null;
        int responsecode=0;
        URL url=null;
        try {
            url=new URL(myurl);
        }
        catch (MalformedURLException e){
            System.out.println("Something is wrong");
        }

        try {
            connection=(HttpURLConnection)url.openConnection();
            responsecode=connection.getResponseCode();
        }
        catch (IOException e){
            throw new RuntimeException();
        }

        if(responsecode==200){
            BufferedReader bf=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder apidata=new StringBuilder();
            String readline=null;
            while ((readline=bf.readLine())!=null){
                apidata.append(readline);
            }
            try {
                bf.close();
            }
            catch (IOException e){
                throw new RuntimeException();
            }
            System.out.println(apidata);
            JSONObject api=new JSONObject(apidata.toString());

        }
        else {
            System.out.println("Api call is not successfull");
        }
    }
}