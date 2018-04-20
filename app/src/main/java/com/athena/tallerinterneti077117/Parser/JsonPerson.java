package com.athena.tallerinterneti077117.Parser;

import com.athena.tallerinterneti077117.Models.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 17/04/18.
 */

public class JsonPerson {

    public static List<Person> getDataJson(String s) throws JSONException {
        //
        JSONArray jsonArray = new JSONArray(s);
        List<Person> personList = new ArrayList<>();

        for (int i =0; i< jsonArray.length(); i++)
        {
            JSONObject item = jsonArray.getJSONObject(i);

            Person person = new Person();

            person.setCodigo(item.getString("codigo"));
            person.setNombre(item.getString("nombre"));
            person.setCorreo(item.getString("correo"));
            person.setEdad(item.getString("edad"));
            person.setPass(item.getString("pass"));



            personList.add(person);
        }

        return personList;
    }

}
