package com.github.snowdream.android.apps.jokes;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hui.yang on 2014/12/7.
 */
public class JokeManager {

    public static void getJokes(final CallBack callBack){
        List<Joke> list = null;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://raw.githubusercontent.com/snowdream/snowdream-jokes-android/master/docs/test/jokes.json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                List<Joke> list =  parseJokes(response);
                if (callBack != null){
                    callBack.callback(list);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private static List<Joke> parseJokes(JSONObject obj){
        List<Joke> list = null;

        if (obj == null){
            return list;
        }

        try {
            list = new ArrayList<Joke>();
            JSONArray array = obj.getJSONArray("jokes");
            int length = array.length();
            for(int i = 0; i < length; i++){
                JSONObject oj = array.getJSONObject(i);

                if (oj == null){
                    continue;
                }
                Joke joke = new Joke();
                joke.setContent(oj.getString("content"));
                joke.setAuthor(oj.getString("author"));
                joke.setCreatetime(oj.getString("createtime"));
                joke.setUp(oj.getInt("up"));
                joke.setDown(oj.getInt("down"));

                JSONArray images = obj.getJSONArray("images");
                int size = images.length();
                List<String> imgs = new ArrayList<String>();
                for(int j = 0; j < size; j++){
                    imgs.add(images.getString(j));
                }
                joke.setImages(imgs);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
