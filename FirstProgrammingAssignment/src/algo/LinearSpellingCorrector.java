package algo;

import algo.helper.ConfigHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahdiye on 10/7/2017.
 */
public class LinearSpellingCorrector {

    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public ArrayList<String> listMisspelledNodes(String inputNode, List<String> referenceWords, int maxDist) {

        ArrayList<String> match = new ArrayList<>();
        for (String voc : referenceWords) {
            int editDistance = levenshteinDistance.measureLevenDist(inputNode, voc);
            if (editDistance == 0) {
                return new ArrayList<>();
            }

            if (editDistance <= maxDist)
                match.add(voc);
        }
        if (match.isEmpty())
            match.add("");
        return match;
    }


}
