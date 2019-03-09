package com.sofyntelligen.hackerrank.problemsolving.solutions.medium;

import java.io.IOException;
import java.util.*;

public class CrosswordPuzzle {

    public static Map<String, List<String>> patterCrossWord = new HashMap<String, List<String>>();
    public static Character letter = 'A';

    public static void main(String[] args) throws IOException {

        String crossword[] = {
                "+-++++++++",
                "+-++++++++",
                "+-------++",
                "+-++++++++",
                "+-----++++",
                "+-+++-++++",
                "+++-----++",
                "+++++-++++",
                "+++++-++++",
                "+++++-++++"
        };

        String words = "SYDNEY;TURKEY;DETROIT;EGYPT;PARIS";

        String[] result = crosswordPuzzle(crossword, words);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);

            if (i != result.length - 1) {
                System.out.println();
            }
        }
    }

    // Complete the crosswordPuzzle function below.
    static String[] crosswordPuzzle(String[] crossword, String words) {

        List<String> listWord = new ArrayList<String>(Arrays.asList(words.split(";")));

        crossword = getPositionCrossword(crossword);

        System.out.println(patterCrossWord);

        listWord = identifiPositionWord(listWord);

        insertWord(crossword, listWord);

        return crossword;

    }

    static void insertWord(String[] crossword, List<String> listWord) {

        System.out.println(listWord);

        letter = 'A';

        for (String word : listWord) {
            Integer count = 0;

            for (int x = 0; x < word.length(); x++) {
                String character = String.valueOf(word.charAt(x));
                count++;

                for (int i = 0; i < crossword.length; i++) {
                    String wordPosition = crossword[i];

                    if (wordPosition.indexOf(String.valueOf(letter) + count) != -1) {
                        wordPosition = wordPosition.replaceFirst(String.valueOf(letter) + count, character);
                        crossword[i] = wordPosition;
                        break;
                    }
                }
            }

            letter++;
        }

    }

    static List<String> identifiPositionWord(List<String> listWord) {

        Integer count = 0;

        INIT:
        while (count != patterCrossWord.size()) {
            count = 0;
            letter = 'A';

            for (String word : listWord) {

                if (patterCrossWord.get(String.valueOf(letter)).size() == word.length()) {

                    if (!validWordInCrossWord(word, listWord)) {
                        Integer position = listWord.indexOf(word);
                        listWord.remove(word);
                        listWord.add(position + 1, word);
                        count = 0;
                        continue INIT;
                    }

                } else {

                    Integer tamaño = patterCrossWord.get(String.valueOf(letter)).size();

                    String wordRem = "";
                    Integer positionDos = 0;

                    for (int x = listWord.size() - 1; x >= 0; x--) {
                        if (tamaño == listWord.get(x).length()) {
                            wordRem = listWord.get(x);
                            positionDos = x;
                            break;
                        }
                    }

                    Integer positionUne = listWord.indexOf(word);

                    listWord.remove(wordRem);
                    listWord.remove(word);

                    listWord.add(positionUne, wordRem);
                    listWord.add(positionDos, word);

                    count = 0;
                    continue INIT;
                }

                letter++;
                count++;
            }
        }

        return listWord;
    }

    static boolean validWordInCrossWord(String word, List<String> listWord) {

        char wordList[] = word.toCharArray();
        List<String> patterList = patterCrossWord.get(String.valueOf(letter));

        Boolean result = true;

        for (int x = 0; x < patterList.size(); x++) {

            if (patterList.get(x).indexOf("-") != -1) {

                System.out.println("###");
                ArrayList<String> listWordTemp = new ArrayList<String>(listWord);
                result = false;

                for (Map.Entry<String, List<String>> entry : patterCrossWord.entrySet()) {

                    System.out.println("## " + listWordTemp);
                    if (letter < entry.getKey().toCharArray()[0]) {

                        int countletter = 0;
                        for (String patterTemp : entry.getValue()) {

                            if (patterTemp.indexOf(String.valueOf(letter) + (x + 1)) != -1) {

                                for (String wordTemp : listWordTemp) {

                                    if (entry.getValue().size() == wordTemp.length() && wordTemp.charAt(countletter) == wordList[x]) {
                                        result = true;
                                        break;
                                    }
                                }
                            }

                            countletter++;
                        }
                    } else {
                        System.out.println("eliminado");
                        listWordTemp.remove(listWordTemp.get(0).toString());
                    }
                }
                if (!result) {
                    break;
                }

            }

        }

        return result;
    }

    static String[] getPositionCrossword(String[] crossword) {

        String matrixCrossWord[][] = new String[crossword.length][crossword[0].length()];

        for (int x = 0; x < crossword.length; x++) {
            String lineCrossWord = crossword[x];

            for (int y = 0; y < lineCrossWord.length(); y++) {
                matrixCrossWord[x][y] = String.valueOf(lineCrossWord.charAt(y));
            }

        }

        return insertPatterCrossWord(matrixCrossWord);

    }

    static String[] insertPatterCrossWord(String matrixCrossWord[][]) {

        for (int x = 0; x < matrixCrossWord.length; x++) {
            for (int y = 0; y < matrixCrossWord[x].length; y++) {

                if (matrixCrossWord[x][y].indexOf("-") != -1) {

                    if (y < 9 && matrixCrossWord[x][y + 1].indexOf("-") != -1) {
                        insertVertical(matrixCrossWord, x, y);
                        letter++;
                    }

                    if (x < 9 && matrixCrossWord[x + 1][y].indexOf("-") != -1) {
                        insertHorizontal(matrixCrossWord, x, y);
                        letter++;
                    }

                }

            }
        }

        return resturnPosition(matrixCrossWord);
    }

    static void insertHorizontal(String matrixCrossWord[][], int x, int y) {
        System.out.println("Horizontal " + x + " " + y);

        Integer count = 1;
        String intersection = "";
        List<String> position = new ArrayList<String>();

        while (x < 10 && matrixCrossWord[x][y].indexOf("-") != -1) {

            if (y > 0) {
                if (matrixCrossWord[x][y - 1].indexOf("-") != -1) {
                    intersection = "-";
                }
            }

            if (y < 9) {
                if (matrixCrossWord[x][y + 1].indexOf("-") != -1) {
                    intersection = "-";
                }
            }

            position.add(matrixCrossWord[x][y].replace("-", String.valueOf(letter) + count + intersection));
            matrixCrossWord[x][y] = String.valueOf(letter) + count + intersection;
            intersection = "";
            count++;
            x++;

        }

        patterCrossWord.put(String.valueOf(letter), position);

    }

    static void insertVertical(String matrixCrossWord[][], int x, int y) {
        System.out.println("Vertical " + x + " " + y);

        Integer count = 1;
        String intersection = "";
        List<String> position = new ArrayList<String>();

        while (y < 10 && matrixCrossWord[x][y].indexOf("-") != -1) {

            if (x > 0) {
                if (matrixCrossWord[x - 1][y].indexOf("-") != -1) {
                    intersection = "-";
                }
            }

            if (x < 9) {
                if (matrixCrossWord[x + 1][y].indexOf("-") != -1) {
                    intersection = "-";
                }
            }

            position.add(matrixCrossWord[x][y].replace("-", String.valueOf(letter) + count + intersection));
            matrixCrossWord[x][y] = String.valueOf(letter) + count + intersection;
            intersection = "";
            count++;
            y++;

        }

        patterCrossWord.put(String.valueOf(letter), position);

    }

    static String[] resturnPosition(String matrixCrossWord[][]) {

        String crossword[] = new String[matrixCrossWord.length];
        String word = "";

        for (int x = 0; x < matrixCrossWord.length; x++) {
            for (int y = 0; y < matrixCrossWord[x].length; y++) {
                word += matrixCrossWord[x][y];
            }

            crossword[x] = word;
            word = "";
        }

        return crossword;
    }

}
