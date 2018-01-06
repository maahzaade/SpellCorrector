package algo;

import algo.helper.ConfigHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Mahdiye on 10/5/2017.
 */
public class FinalMain {

    public static void main(String[] args) {

        ConfigHelper configHelper = new ConfigHelper();
        List<String> stringList = new ArrayList<String>();

        List<String> dictionary = new ArrayList<String>();
        dictionary = configHelper.getVocab();
        int TOL = configHelper.getMaxDistance();

        BufferedWriter outBuffer = null;
        try {
            File file = new File(configHelper.getOutputFile());
            outBuffer = new BufferedWriter(new FileWriter(file));


            BKTree tempBKTree = new BKTree(dictionary.get(0));
            LinearSpellingCorrector linearSpellingCorrector = new LinearSpellingCorrector();

            final long startCreationTime = System.currentTimeMillis();
            for (int i = 1; i <= dictionary.size() - 1; i++) {
                Node newNode = new Node(tempBKTree.getRoot().toString());
                tempBKTree.add(tempBKTree.getRoot(), dictionary.get(i));
            }
            final long endCreationTime = System.currentTimeMillis();
            System.out.println("Total BKTree Creation time: " + (endCreationTime - startCreationTime));
            System.out.println();

            StringTokenizer st = new StringTokenizer(configHelper.getSentence());
            while (st.hasMoreTokens()) {
                stringList.add(st.nextToken());
            }

            final long startTotalBKTreeSearchTime = System.currentTimeMillis();
            for (String item : stringList) {
                ArrayList<String> match = new ArrayList<>();
                match = tempBKTree.listMisspelledNodes(item, TOL, tempBKTree.getRoot(), match);
                if (!match.contains(item.toLowerCase())) {
                    outBuffer.write(item + " : ");
                    System.out.print(item + " : ");
                    for (String element : match) {
                        System.out.print(element + ", ");
                        outBuffer.write(element + ", ");
                    }
                    System.out.println();
                    outBuffer.write("\n");
                } else {
                    outBuffer.write(" 0 ");
                }
            }

            final long endTotalBkTreeSearchTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("Total BKTree search time to check Sentence : " + configHelper.getSentence() + " is " + (endTotalBkTreeSearchTime - startTotalBKTreeSearchTime));
            System.out.println();
            System.out.println("=======================================================================================================================");
            final long startTotalLinearSearchTime = System.currentTimeMillis();
            for (String item : stringList) {
                ArrayList<String> match = new ArrayList<>();
                match = new ArrayList<>();
                final long startLinearSearchTime = System.currentTimeMillis();
                match = linearSpellingCorrector.listMisspelledNodes(item.toLowerCase(), dictionary, TOL);
                final long endLinearSearchTime = System.currentTimeMillis();
                if (!match.isEmpty()) {
//                    System.out.println("Linear search time to find word : " + item + " is " + (endLinearSearchTime - startLinearSearchTime));
                    System.out.print(item + " : ");
                    for (String element : match) {
                        System.out.print(element + ", ");
                    }
                    System.out.println();
                }
            }
            final long endTotalLinearSearchTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("Total Linear search time to check Sentence : " + configHelper.getSentence() + " is " + (endTotalLinearSearchTime - startTotalLinearSearchTime));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outBuffer != null) {
                try {
                    outBuffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
