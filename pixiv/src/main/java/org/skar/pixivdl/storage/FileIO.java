package org.skar.pixivdl.storage;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    static final Logger logger = LoggerFactory.getLogger(FileIO.class);

    public static void writeJson(String json, String path) {
        Path p = Paths.get(path).toAbsolutePath();
        try {
            Writer writer = new FileWriter(p.toFile());
            writer.write(json);
        }  catch(IOException e) {
            logger.error("Cannot write to %s", p.toString());
            return;
        }

        logger.info("Json written successfully to %s", p.toString());
    }

    public static String readJson(String path) {
        Path p = Paths.get(path).toAbsolutePath();
        if (p.toFile().exists()) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(path));

                logger.info("Read success: %s ", p.toString());

                return new String(bytes, "utf-8");
            } catch(IOException e) {

                logger.error("fail to read file %s", p.toString());
                return null;
            }
        }

        logger.info("File does not exists at %s.", p.toString());
        return null;
    }
}
