package org.uva.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class IOHandler {

    private final Logger logger;

    public IOHandler() {
        this.logger = Logger.getGlobal();
    }

    public String getUserInput(String type) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir") + "/input");

        FileNameExtensionFilter filter = new FileNameExtensionFilter(type, type);
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return readFile(chooser.getSelectedFile().getPath());
        } else {
            logger.info("No file selected");
            System.exit(0);
        }
        return null;
    }

    public String readFile(String location) {
        String result = "";
        try {
            byte[] a = Files.readAllBytes(Paths.get(location));
            result = new String(a);
        } catch (IOException e) {
            logger.severe(String.format("There was an error reading the input file: %s", e));
            e.printStackTrace();
        }
        return result;
    }

    public void writeOutput(String location, JSONObject jsonObject) {
        try {
            new FileOutputStream(location, true).close();
            JSONArray jsonArray;

            try {
                jsonArray = new JSONArray(readFile(location));
            } catch (JSONException e) {
                logger.warning("JSON in existing output file cannot be parsed, the file will be overwritten");
                jsonArray = new JSONArray();
            }

            jsonArray.put(jsonObject);

            FileWriter file = new FileWriter(location);
            file.write(jsonArray.toString(4));
            file.close();
        } catch (Exception e) {
            logger.severe("Result output unsuccessful");
            e.printStackTrace();
        }
    }
}
