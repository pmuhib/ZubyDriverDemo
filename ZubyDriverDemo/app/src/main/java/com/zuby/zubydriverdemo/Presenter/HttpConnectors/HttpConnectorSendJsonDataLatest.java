package com.zuby.zubydriverdemo.Presenter.HttpConnectors;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by citymapper-pc5 on 17/4/18.
 */

public class HttpConnectorSendJsonDataLatest
{
    private static String url_param;
    private static HttpURLConnection conn;

    public HttpConnectorSendJsonDataLatest(String url_param) {
        this.url_param = url_param;
    }

    public static String fetchData(Context cn) {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.d("EM", "The url is: " + url_param);

        try {
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Content-Type","application/json");

            conn.setRequestProperty("charset", "utf-8");
            Log.e("EM", "11111111111111:::::");
            // conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response_code = conn.getResponseCode();
            Log.e("EM", "get data::::::"+response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK)
            {
                is = conn.getInputStream();
                Log.e("EM", "get data::::::"+response_code);
                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1)
                {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            }
            else {
                throw new Exception();
            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch (Exception e) {
            Log.d("EM", "Exception : " + e);
            return null;
        }
        finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                }
                catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                }
                catch (Exception e) {

                }
            }
            sb = null;
        }
    }



    public static String fetchTokenData(Context cn) {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.d("EM", "The url is: " + url_param);

        try {
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(4000 /* milliseconds */);
            conn.setConnectTimeout(4000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("x-openam-username", "cabindia_android");
            conn.setRequestProperty("x-openam-password", "cabindia@android");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");


            conn.setRequestProperty("charset", "utf-8");
            Log.e("EM", "11111111111111:::::");
            // conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response_code = conn.getResponseCode();
            Log.e("EM", "get data::::::"+response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK)
            {
                is = conn.getInputStream();
                Log.e("EM", "get data::::::"+response_code);
                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1)
                {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            }
            else {
                throw new Exception();
            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch (Exception e) {
            Log.d("EM", "Exception : " + e);
            return null;
        }
        finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                }
                catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                }
                catch (Exception e) {

                }
            }
            sb = null;
        }
    }

    public static String postData(JSONObject jsonObject)
    {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url_param);

            String json = "";

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin
            // Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the
            // content
            httpPost.setHeader("x-openam-username", "cabindia_android");
            httpPost.setHeader("x-openam-password", "cabindia@android");
            httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            Log.e("EM", "result :::" + result);
        }
        catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }


    /*public static String postNewData(JSONObject jsonObject) {
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url_param);

            String json = "";

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin
            // Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the
            // content
//            httpPost.setHeader("Accept", "application/json");

            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
            Log.e("EM", "result :::" + result);
        }
        catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }*/


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }



    public String postNewData(String postData)
    {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.e("EM", "The url is: " + url_param);

        try {

            byte[] postdata = postData.getBytes( StandardCharsets.UTF_8 );
            int postDataLength = postdata.length;
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
            conn.setUseCaches(false);

            Log.e("EM", "The url is222222222: " + url_param);
            // conn.setDoInput(true);
            // Starts the query
            conn.connect();
            Log.e("EM", "The url is3333333332: " + url_param);
            // Send request
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            int response_code = conn.getResponseCode();
            Log.e("EM", "Response code: " + response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();

                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1) {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            }
            else {
                throw new Exception();
            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch (Exception e) {
            Log.e("EM", "Exception : " + e);
            return null;
        }
        finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                }
                catch (Exception e) {

                }
            }
            sb = null;
        }
    }


    public String postDataForUpdate(String postData)
    {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.e("EM", "The url is: " + url_param);

        try
        {
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(20000 /* milliseconds */);
            conn.setConnectTimeout(20000 /* milliseconds */);
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestMethod("POST");

            Log.e("EM", "The url is222222222: " + url_param);
            // conn.setDoInput(true);
            // Starts the query


            conn.connect();
            Log.e("EM", "The url is3333333332: " + url_param);
            // Send request
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            int response_code = conn.getResponseCode();
            Log.e("EM", "Response code: " + response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();

                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1) {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            } else {
                Log.e("Em","exception"+" "+new Exception());
                throw new Exception();

            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }

        catch (Exception e) {
            Log.e("EM", "Exception : " + e);
            return null;
        } finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {

                }
            }
            sb = null;
        }
    }



    public String postData(String postData)
    {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.e("EM", "The url is: " + url_param);

        try
        {
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestMethod("POST");

            Log.e("EM", "The url is222222222: " + url_param);
            // conn.setDoInput(true);
            // Starts the query


            conn.connect();
            Log.e("EM", "The url is3333333332: " + url_param);
            // Send request
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            int response_code = conn.getResponseCode();
            Log.e("EM", "Response code: " + response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();

                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1) {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            } else {
                Log.e("Em","exception"+" "+new Exception());
                throw new Exception();

            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }

         catch (Exception e) {
            Log.e("EM", "Exception : " + e);
            return null;
        } finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {

                }
            }
            sb = null;
        }
    }


    public String postDataForLogout(String postData)
    {
        InputStream is = null;
        InputStreamReader isr = null;
        StringBuffer sb = null; // To read data
        Log.e("EM", "The url is: " + url_param);

        try
        {
            URL url = new URL(url_param);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(3000 /* milliseconds */);
            conn.setConnectTimeout(3000 /* milliseconds */);
            conn.setRequestProperty("content-type", "application/json");
            conn.setRequestMethod("POST");

            Log.e("EM", "The url is222222222: " + url_param);
            // conn.setDoInput(true);
            // Starts the query


            conn.connect();
            Log.e("EM", "The url is3333333332: " + url_param);
            // Send request
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();

            int response_code = conn.getResponseCode();
            Log.e("EM", "Response code: " + response_code);
            // Read data from network
            if (response_code == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();

                // Add is to Reader for buffered data reading from server
                isr = new InputStreamReader(is);
                sb = new StringBuffer();
                char[] readBuffer = new char[1024];
                int count = 0;
                while ((count = isr.read(readBuffer)) != -1) {
                    sb.append(readBuffer, 0, count);
                }
                return sb.toString();
            } else {
                Log.e("Em","exception"+" "+new Exception());
                throw new Exception();

            }

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }

        catch (Exception e) {
            Log.e("EM", "Exception : " + e);
            return null;
        } finally {
            // Close inner stream.
            if (is != null) {
                try {
                    conn.disconnect();
                    is.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {

                }
            }
            sb = null;
        }
    }





    // Reads an InputStream and converts it to a String.
    private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        Log.e("EM", "Response string :::::" + new String(buffer));
        return new String(buffer);
    }
}
