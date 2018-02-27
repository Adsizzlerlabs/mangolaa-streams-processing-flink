package com.adsizzler.mangolaa.streams.utils;

import java.util.Objects;

/**
 * Created by Ankush on 17/07/17.
 */
public class Assert {


    private Assert(){}

    public static <T> void notNull(final T t , final String errorMsg) {
        if(Objects.isNull(t)){
            throw new IllegalArgumentException(errorMsg);
        }
    }

}
