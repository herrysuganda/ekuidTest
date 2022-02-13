package ekuid;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DEV_MASTER
 */
public class Ekuid2 {

    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {

        String inputData = "[{\"amount\":15000.0,\"currency\":\"IDR\"},{\"amount\":3.1,\"currency\":\"EUR\"}]";
        JsonArray dataArray = new JsonParser().parse(inputData).getAsJsonArray();
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject o = dataArray.get(i).getAsJsonObject();

            Map<String, String> parameters = new HashMap<>();
            parameters.put("amount", o.get("amount").getAsString());
            parameters.put("from", o.get("currency").getAsString());
            parameters.put("to", "USD");

            String result = Ekuid2.sendToFrankfurter(parameters);
            JsonObject dataResult = new JsonParser().parse(result).getAsJsonObject();
            JsonObject rates = dataResult.get("rates").getAsJsonObject();
            System.out.println(rates.get("USD").getAsBigDecimal());
        }

    }

    public static String sendToFrankfurter(Map<String, String> parameters) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL("https://api.frankfurter.app/latest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

}

class ParameterStringBuilder {

    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
