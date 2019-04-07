package hackerrank;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;


public class Solution {
    /*
     * Complete the function below.
     */
    static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {
        firstDate = "1-January-2000";
        lastDate = "22-February-2000";
        weekDay="Monday";
        JsonObject data = callApiByDate(firstDate, lastDate);
        try {
            List<String> result = filterByWeek(firstDate, lastDate, weekDay, (JsonArray) data.get("data"));
            System.out.println(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static List<String> filterByWeek(String firstDate, String lastDate, String weekDay, JsonArray dataArr) throws ParseException {
        int intourWeek=weekDay==null?0:getDaysOfWeek(weekDay);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        Date startDate = simpleDateFormat.parse(firstDate);
        Date endDate = simpleDateFormat.parse(lastDate);
        List<String> result =new ArrayList<>();
        for (int i = 0; i < dataArr.size(); i++) {
            JsonObject event = (JsonObject) dataArr.get(i);
            String dateStr=event.get("date").getAsString();
            Date date = simpleDateFormat.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            if((intourWeek==dayOfWeek) && (startDate.compareTo(date)!=1 && endDate.compareTo(date)!=-1) ){
                result.add(dateStr+" "+event.get("high").getAsString()+" "+event.get("low").getAsString());
            }

        }


        return result;
    }

    private static int getDaysOfWeek(String weekDay) throws ParseException {
        switch (weekDay.toUpperCase()) {
            case "SUNDAY":
                return 1;
            case "MONDAY":
                return 2;
            case "TUESDAY":
                return 3;
            case "WEDNESDAY":
                return 4;
            case "THURSDAY":
                return 5;
            case "FRIDAY":
                return 6;
            case "SATURDA":
                return 7;
        }
        return 0;
    }

    public static JsonObject callApiByDate(String firstDate, String lastDate) {
        HttpURLConnection conn = null;
        try {

            URL url = new URL("https://jsonmock.hackerrank.com/api/stocks/search?firstDate>=" + firstDate + "&lastDate<=" + lastDate);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            String output;
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            while ((output = br.readLine()) != null) {
                json = gson.fromJson(output.toString(), JsonObject.class);
            }
            return json;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String _firstDate = null;
        /*try {
            _firstDate = in.nextLine();
        } catch (Exception e) {
            _firstDate = null;
        }
*/
        String _lastDate = null;
        /*try {
            _lastDate = in.nextLine();
        } catch (Exception e) {
            _lastDate = null;
        }*/

        String _weekDay = null;
       /* try {
            _weekDay = in.nextLine();
        } catch (Exception e) {
            _weekDay = null;
        }*/

        openAndClosePrices(_firstDate, _lastDate, _weekDay);

    }
}