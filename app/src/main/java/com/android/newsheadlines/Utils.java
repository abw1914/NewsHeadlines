package com.android.newsheadlines;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public final class Utils {

    private Utils() {

    }

    /**
     * Use this TAG for debugging
     */
    public static final String LOG_TAG = Utils.class.getSimpleName();


    public static List<NewsData> getNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with input stream", e);
        }

        List<NewsData> newsData = (List<NewsData>) extractDataFromJson(jsonResponse);
        return newsData;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with URL", e);
        }
        return url;

    }

    /**
     * this method establishes an https connection with the Guardian API
     * @param url
     * @return
     * @throws IOException
     */

    public static String makeHttpRequest(URL url) throws IOException {
        /**
         * Create an empty jsonResponse string
         */
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }
        HttpsURLConnection httpsURLConnection = null;
        InputStream inputStream = null;
        try {
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();

            /**
             * this is the return response code from the API and 200 is valid connection
             */
            if(httpsURLConnection.getResponseCode() == 200) {
                inputStream = httpsURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Response Code Error:  " + httpsURLConnection.getResponseCode());

                }
            } catch (IOException e) {
            Log.e(LOG_TAG, "Data Retrieving Error", e);
                e.printStackTrace();
            } finally {
            if(httpsURLConnection != null) {
                httpsURLConnection.disconnect();
            }
            if(inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;


    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    private static List<NewsData> extractDataFromJson (String newsDataJson) {
        /**
         * First create String resources for all the Strings we need to pull from the Guardian API
         */

        String title;
        String author;
        String date;
        String urlSource;

        if(TextUtils.isEmpty(newsDataJson)) {
            return null;
        }

        List<NewsData> newsDataList = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsDataJson);
            JSONObject newsResutls = baseJsonResponse.getJSONObject("response");
            JSONArray currentResultsArray = newsResutls.getJSONArray("results");

            if(currentResultsArray.length() > 0) {
                JSONObject currentArticle = currentResultsArray.getJSONObject(0);

                title = currentArticle.getString("webTitle");
                author = currentArticle.getString("id");
                date = currentArticle.getString("webPublicationDate");
                urlSource = currentArticle.getString("webUrl");

                /**
                 * Note if article author is needed - need to write another JsonArray for the tags id
                 */

                /*JSONArray authorArray = currentArticle.getJSONArray("tage");
                if(authorArray.length() > 0) {
                    JSONObject authorFirstName = authorArray.getJSONObject(0);
                    String authorFirst = authorFirstName.getString("firstName");
                    String authorLast = authorFirstName.getString("lastName");*/


                NewsData newsData = new NewsData(title, author, date, urlSource);
                newsDataList.add(newsData);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Parsing Problem", e);
        }
        return null;
    }

    }


