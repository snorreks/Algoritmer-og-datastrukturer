package oving12;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String text = "wabbawabba";
        LZWEncode lzwEncode = new LZWEncode(text);
        lzwEncode.initDictionary();
        lzwEncode.compile();
        System.out.println(lzwEncode.indexes);
    }
}

class LZWEncode {
    ArrayList<String> dictionary = new ArrayList<>();
    char[] allChars;
    ArrayList<Integer> indexes = new ArrayList<>();
    private String text;

    LZWEncode(String text) {
        this.text = text;
    }

    void initDictionary() {
        String tempDictonary = "";
        for (char d : text.toCharArray()) {
            if (tempDictonary.indexOf(d) == -1) {
                tempDictonary += d;
            }
        }
        allChars = tempDictonary.toCharArray();
        for (char c : allChars) {
            dictionary.add(String.valueOf(c));
        }
    }

    void compile() {
        String p = "";
        for (int i = 0; i < text.length(); i++) {
            String c = String.valueOf(text.charAt(i));
            String pc = p + c;
            if (dictionary.contains(pc)) {
                p = pc;
            } else {
                dictionary.add(pc);
                indexes.add(dictionary.indexOf(p));
                p = c;
            }
        }
    }
}

class LZWDecode {
    ArrayList<String> dictionary;
    ArrayList<Integer> indexes;

    public LZWDecode(char[] allChars, ArrayList<Integer> indexes) {
        this.dictionary = new ArrayList<>();
        for (char c : allChars) {
            dictionary.add(String.valueOf(c));
        }
        this.indexes = indexes;
    }

    void decode() {
        String pw;
        String cW;
        String p;
        String c;
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i) < dictionary.size()) {

            }
        }
    }
}