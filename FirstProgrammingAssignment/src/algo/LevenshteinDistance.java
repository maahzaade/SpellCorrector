package algo;

/**
 * Created by Mahdiye on 10/1/2017.
 */
public class LevenshteinDistance {


 /*   static int tripletMin(int x, int y, int z) {

        int result = 0;
        if (x <= y && x <= z)
            result = x;
        if (y <= x && y <= z)
            result = y;
        if (z <= x && z <= y)
            result = z;
        return result;

    }

    static int measureLevenDist(String firstStr, String secondStr) {
        int firstStrLen = firstStr.length();
        int secondStrLen = secondStr.length();

        if (firstStrLen == 0)
            return secondStrLen;

        if (secondStrLen == 0)
            return firstStrLen;

        if (firstStr.charAt(firstStrLen - 1) == secondStr.charAt(secondStrLen - 1))
            return eval(firstStr.substring(0, firstStrLen - 1), secondStr.substring(0, secondStrLen - 1));

        return 1 + tripletMin(eval(firstStr.substring(0, firstStrLen), secondStr.substring(0, secondStrLen - 1)),
                eval(firstStr.substring(0, firstStrLen - 1), secondStr.substring(0, secondStrLen)),
                eval(firstStr.substring(0, firstStrLen - 1), secondStr.substring(0, secondStrLen - 1)));
    }*/

    //LevenshteinDistance is calculated based on LCS
    public int measureLevenDist(String firstStr, String secondStr) {
        int firstStrLen = firstStr.length(), secondStrLen = secondStr.length();
        int[][] row = new int[firstStrLen + 1][secondStrLen + 1];
        int counter, anotherCounter;
        int result;

        for (counter = 0; counter < firstStrLen + 1; counter++)
            row[counter][0] = counter;
        for (counter = 0; counter < secondStrLen + 1; counter++)
            row[0][counter] = counter;
        for (counter = 1; counter <= firstStrLen; ++counter) {
            for (anotherCounter = 1; anotherCounter <= secondStrLen; ++anotherCounter) {
                int cost = 0;
                if (firstStr.substring(counter - 1, counter).equals(secondStr.substring(anotherCounter - 1, anotherCounter))) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                int replace = row[counter - 1][anotherCounter - 1] + cost;// replace
                int delete = row[counter][anotherCounter - 1] + 1;// deletion
                int insert = row[counter - 1][anotherCounter] + 1;// insertion
                row[counter][anotherCounter] = Math.min(Math.min(replace, delete), insert);
            }
        }
        result = (row[firstStrLen][secondStrLen]);

        return result;
    }
}



