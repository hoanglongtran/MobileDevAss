package com.example.nex_.mobiledev_assignment1.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class JSONGetterAsyncTask extends AsyncTask<String, Void, Void> {
    private static final String LOG_TAG = "JSONGetterAsyncTask";
    private static final String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
    private static String origin = "-37.807425,144.963814";
    private static final String destinatinURL = "&destinations=";
    private static String destination = "-37.820666,144.958277";
    private static final String walkingModeAndKey = "&mode=walking&key=";
    private static final String APIKey = "AIzaSyCLgWPZq17ieziKSyQ_Rcej1V7J2g9q3t4";
    public static String distanceText = "";
    public static int distanceValue = 0;
    public static String durationText = "";
    public static int durationValue = 0;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(String... locations) {
        String jsonString;
        //origin = locations[0];
        destination = locations[0];
        URL url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            url = new URL(URL + origin + destinatinURL + destination + walkingModeAndKey + APIKey);

            urlConnection = (HttpURLConnection) url
                    .openConnection();


            urlConnection.setRequestMethod("GET");
            // see http://en.wikipedia.org/wiki/List_of_HTTP_header_fields
            urlConnection.setRequestProperty("Accept", "application/json");

            InputStream in = urlConnection.getInputStream();


            reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

            }
            jsonString = buffer.toString();

            testJSON(jsonString);
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    public static void testJSON(String jsonString) throws Exception
    {
        JSONObject travelTime = new JSONObject(jsonString);
        JSONArray rows = travelTime.getJSONArray("rows");
        JSONObject rows2 = rows.getJSONObject(0);

        JSONArray elements = rows2.getJSONArray("elements");
        JSONObject elements2 = elements.getJSONObject(0);
        JSONObject distance = elements2.getJSONObject("distance");
        JSONObject duration = elements2.getJSONObject("duration");

        /*SuggestionManager suggestionManager = new SuggestionManager();
        suggestionManager.setDistanceText(distance.getString("text"));
        suggestionManager.setDistanceValue(distance.getInt("value"));
        suggestionManager.setTravelTimeText(duration.getString("text"));
        suggestionManager.setTravelTimeValue(duration.getInt("value"));*/
        distanceText = distance.getString("text");
        distanceValue = distance.getInt("value");

        durationText = duration.getString("text");
        durationValue = duration.getInt("value");


        Log.d("Response: ", "> distance Text " + distanceText);
        Log.d("Response: ", "> distance Value " + distanceValue);
        Log.d("Response: ", "> duration Text " + durationText);
        Log.d("Response: ", "> duration Value " + durationValue);

        /*for (int i = 0; i < phoneNums.length(); i++)
            Log.i(LOG_TAG,
                    String.format(Locale.getDefault(), "phone[%d]: %s", i, phoneNums.getJSONObject(i)
                            .getString("number")));*/
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        int currentTrackableID = SuggestionManager.currentTrackableID;
        Suggestion suggestion = new Suggestion(currentTrackableID,distanceText, distanceValue, durationText, durationValue);
        SuggestionManager.suggestionsList.add(suggestion);
    }

    protected void onPostExecute(Boolean result) {


    }

    public static String getDistanceText() {
        return distanceText;
    }

    public static int getDistanceValue() {
        return distanceValue;
    }

    public static String getDurationText() {
        return durationText;
    }

    public static int getDurationValue() {
        return durationValue;
    }
}
