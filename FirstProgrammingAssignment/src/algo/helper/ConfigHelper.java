package algo.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Mahdiye on 10/3/2017.
 */
public class ConfigHelper {

    private List<String> vocab;
    private String sentence;
    private int maxDistance;
    private String distanceFile, outputFile, sentenceFile, vocabFilename;

    InputStream inputStream;

    // this function was taken from a site and customized according to my requirement
    public void getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            distanceFile = prop.getProperty("MaxDistance_file_path");
            outputFile = prop.getProperty("MisspelledWords_file_path");
            sentenceFile = prop.getProperty("sentence_file_path");
            vocabFilename = prop.getProperty("vocab_file_path");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public ConfigHelper() {
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readVocabsFromFile(vocabFilename);
        readSentenceFromConfig(sentenceFile);
        fetchMaxDistance(distanceFile);
    }

    void readVocabsFromFile(String file) {
        List vocabList = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();

            if (line == null)
                throw new IOException(file + " is an empty file");
            else
                vocabList.add(line);

            for (line = in.readLine().trim(); line != null; line = in.readLine()) {
                if (line.length() > 0) {
                    vocabList.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        }
        setVocab(vocabList);
    }

    void readSentenceFromConfig(String file) {
        String sentenceToinvestigate = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();

            if (line == null)
                throw new IOException(file + " is an empty file");
            else
                sentenceToinvestigate = line.trim();
        } catch (IOException e) {
            System.out.println("Error when reading " + file);
            e.printStackTrace();
        }
        setSentence(sentenceToinvestigate);
    }

    void fetchMaxDistance(String distanceFile) {
        int tolerance = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(distanceFile));
            String line = in.readLine();

            if (line == null)
                throw new IOException(distanceFile + " is an empty file");
            else
                tolerance = Integer.parseInt(line.trim());
        } catch (IOException e) {
            System.out.println("Error when reading " + distanceFile);
            e.printStackTrace();
        }
        setMaxDistance(tolerance);
    }

    public List<String> getVocab() {
        return vocab;
    }

    public void setVocab(List<String> vocab) {
        this.vocab = vocab;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}