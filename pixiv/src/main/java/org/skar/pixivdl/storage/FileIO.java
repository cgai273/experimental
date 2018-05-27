package org.skar.pixivdl.storage;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIO {
    static final Logger logger = LoggerFactory.getLogger(FileIO.class);

    private static final Pattern p  = Pattern.compile("[\\w+_]+\\.(jpg|jpeg|png|gif)$");

    public static void writeJson(String json, String path) {
        Path p = Paths.get(path).toAbsolutePath();
        try (PrintWriter writer = new PrintWriter(p.toString())){
            writer.println(json);
        }  catch(IOException e) {
            logger.error("Cannot write to {}", p);
            return;
        }

        logger.info("Json written successfully to {}", p);
    }

    public static String readJson(String path) {
        Path p = Paths.get(path).toAbsolutePath();
        return readJson(p);
    }

    public static String readJson(Path p) {
        if (p.toFile().exists()) {
            try {
                byte[] bytes = Files.readAllBytes(p);

                logger.info("Read success: {}", p);

                return new String(bytes, "utf-8");
            } catch(IOException e) {

                logger.error("fail to read file {}", p);
                return null;
            }
        }

        logger.info("File does not exists at {}.", p);
        return null;
    }

    public static String extractFileNameFromUrl(String url) {
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }
}
