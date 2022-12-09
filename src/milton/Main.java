package milton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.printf("Processing %s\n", args[0]);

        Path p = Paths.get(args[0]);
        File f = p.toFile();

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        int count = 0;
        int wordCount = 0;
        int currentLine;

        Map<String, Integer> tabulationWord = new HashMap<String, Integer>();

        while (count < 100) {
            String line = br.readLine();
            if (null == line) {
                break;
            }
            count++;
            String[] tmpArray = line.trim().split(" ");
            if ((tmpArray.length == 1) && (tmpArray[0].equals(""))) {
                currentLine = 0;
            } else {
                currentLine = tmpArray.length;
                wordCount += currentLine;
            }
            for (String w : tmpArray) {
                String word = w.toLowerCase();
                word = word.replaceAll("\\P{L}+", "");

                if (!word.equals("")) {

                    if (tabulationWord.containsKey(word)) {
                        tabulationWord.put(word, tabulationWord.get(word) + 1);
                    } else {
                        tabulationWord.put(word, 1);
                    }
                }
            }
            System.out.printf("LN %s has %d words\n", count, currentLine);
            System.out.printf(line + "\n");
            // total words
            // unique words
            // how many times each word appears

        }
        br.close();

        for (String w : tabulationWord.keySet()) {
            System.out.printf("> %s %d\n", w, tabulationWord.get(w));
        }

        System.out.println("Total word count is " + wordCount);
        System.out.println("Total unique words count is " + tabulationWord.keySet().size());

        // for (Map.Entry<String, Integer> set : tabulationWord.entrySet()) {
        // System.out.println(set.getKey() + ("*".repeat(set.getValue())) + "\n");
        // }

        Path newp = Paths.get(args[1]);
        File newfile = newp.toFile();
        FileWriter fw = new FileWriter(newfile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("word, count\n");
        for (String w : tabulationWord.keySet()) {
            bw.write(w + ", " + tabulationWord.get(w) + "\n");
            bw.flush();
        }
        bw.close();

    }
}
