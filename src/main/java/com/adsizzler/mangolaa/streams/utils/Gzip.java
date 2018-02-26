package com.adsizzler.mangolaa.streams.utils;


import lombok.val;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;


/**
 * Created by Ankush on 19/07/17.
 */
public class Gzip {

    private Gzip(){}

    public static String decompress(final byte[] compressed) {

        if ((compressed == null) || (compressed.length == 0)) {
            throw new IllegalArgumentException("Cannot unzip null or empty bytes");
        }
        if (!isZipped(compressed)) {
            return new String(compressed);
        }

        try (val byteArrayInputStream = new ByteArrayInputStream(compressed)) {
            try (val gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                try (val inputStreamReader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
                    try (val bufferedReader = new BufferedReader(inputStreamReader)) {
                        val output = new StringBuilder();
                        String line;
                        while((line = bufferedReader.readLine()) != null){
                            output.append(line);
                        }
                        return output.toString();
                    }
                }
            }
        }
        catch(final IOException e) {
            throw new RuntimeException("Failed to unzip content");
        }
    }

    private static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }

}
