package com.trainings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Tigra
 * Date: 2/8/12
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordCount {
    private int words = 0;
    private int chars = 0;
    private int lines = 0;
    private File exampleFile;

    WordCount(String fileName) {
        setExampleFile(fileName);
        try {
            countWords();
            countChars();
            countLines();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    private void countChars() throws FileNotFoundException {
        int charCounter = 0;
        String line;
        FileReader fileReader = new FileReader(getExampleFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            while ((line = bufferedReader.readLine()) != null) {
                CharacterIterator ct = new StringCharacterIterator(line);
                for (char ch = ct.first(); ch != CharacterIterator.DONE; ch = ct.next()) {
                    charCounter++;
                }

            }
            setChars(charCounter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void countWords() throws FileNotFoundException {
        int wordCounter = 0;
        Scanner wordScanner = new Scanner(getExampleFile());
        try {
            while (wordScanner.hasNext()) {
                String word = wordScanner.next();
                int i = word.charAt(0);
                if (word.contains("'")) {
                    wordCounter += 2;
                } else if (Character.isDigit(i)) {
                    wordCounter+=0;
                }
                else {
                    wordCounter++;
                }
            }
            setWords(wordCounter);
        } finally {
            wordScanner.close();
        }
    }

    private void countLines() throws FileNotFoundException {
        int linesCounter = 0;
        Scanner linesScanner = new Scanner(getExampleFile());
        try {
            while (linesScanner.hasNextLine()) {
                linesScanner.nextLine();
                linesCounter++;
            }
            setLines(linesCounter);
        } finally {
            linesScanner.close();
        }
    }

    public void setWords(int words) {
        this.words = words;
    }
    public int getWords() {
        return words;
    }
    public void setChars(int chars) {
        this.chars = chars;
    }
    public int getChars() {
        return chars;
    }
    public void setLines(int lines) {
        this.lines = lines;
    }
    public int getLines() {
        return lines;
    }
    public void setExampleFile(String fileName) {
        this.exampleFile = new File(fileName);
    }
    public File getExampleFile() {
        return exampleFile;
    }

    public static void main(String[] args) {
        WordCount counter = new WordCount("files\\lear.txt");

        System.out.println("File: " + counter.getExampleFile().getName());
        System.out.println("Lines = " + counter.getLines());
        System.out.println("Words = " + counter.getWords());
        System.out.println("Chars = " + counter.getChars());

    }
}

