package com.ryanharter.auto.value.moshi.example;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PersonTest {
    @Test
    public void testMoshi() throws Exception {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String birthdate = "2007-11-11";
        Date date = df.parse(birthdate);

        Moshi moshi = SampleTypeAdapterFactory.configureMoshiBuilder(new Moshi.Builder())
                .build();
        Person person = Person.builder()
                .name("Piasy")
                .gender(1)
                .age(23)
                .birthdate(date)
                .address(Address.create("street", "city"))
                .build();
        String json = "{\"name\":\"Piasy\",\"gender\":1,"
                + "\"age\":23,\"birthdate\":\"" + birthdate
                + "\",\"address\":{\"street-name\":\"street\",\"city\":\"city\"}}";

        JsonAdapter<Person> jsonAdapter = moshi.adapter(Person.class);
        String toJson = jsonAdapter.toJson(person);
        Assert.assertEquals(json, toJson);

        Person fromJson = jsonAdapter.fromJson(json);
        Assert.assertEquals("Piasy", fromJson.name());
        Assert.assertEquals(23, fromJson.age());
        Assert.assertEquals(1, fromJson.gender());
        Assert.assertEquals(date, fromJson.birthdate());
    }
}
