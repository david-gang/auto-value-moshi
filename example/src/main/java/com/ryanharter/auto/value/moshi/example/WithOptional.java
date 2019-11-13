package com.ryanharter.auto.value.moshi.example;

import com.google.auto.value.AutoValue;
import com.ryanharter.auto.value.moshi.AutoValueMoshiBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.Optional;

@AutoValue
public  abstract class WithOptional {
    public abstract Optional<String> property();
    @AutoValueMoshiBuilder
    public static Builder builder() {
        return new AutoValue_WithOptional.Builder();
    }

    public static JsonAdapter<WithOptional> jsonAdapter(Moshi moshi) {
        return new AutoValue_WithOptional.MoshiJsonAdapter(moshi);
    }

    @AutoValue.Builder
    interface Builder {
        Builder property(String property);
        WithOptional build();
    }
}
