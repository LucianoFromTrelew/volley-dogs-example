package com.example.dogs;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DogService {
    private static final String DOGS_URL = "https://dog.ceo/api/breed/%s/images/random/%d";
    public static LiveData<List<Dog>> getDogs(String breed) {
        return getDogs(breed, 5);
    }
    public static LiveData<List<Dog>> getDogs(String breed, int qty) {
        MutableLiveData<List<Dog>> dogsData = new MutableLiveData<>();
        Log.i("Dogs", "*** GETTING DOGS ***");
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                String.format(DOGS_URL, breed.toLowerCase(), qty),
                null,
                (JSONObject response) -> {
                    try {
                        List<Dog> dogs = new ArrayList<>();
                        JSONArray dogsJson = response.getJSONArray("message");
                        for (int i=0; i < dogsJson.length(); i++) {
                            dogs.add(new Dog(breed, dogsJson.getString(i)));
                        }
                        dogsData.postValue(dogs);
                    } catch (JSONException e) {
                        Log.e("Dogs", e.toString());
                    }
                },
                (VolleyError error) -> {
                    Log.e("Dogs", error.toString());
                    dogsData.postValue(null);
                }
        );
        MyApplication.getInstance().addToRequestQueue(request, "GET_DOGS");
        return dogsData;
    }
}
