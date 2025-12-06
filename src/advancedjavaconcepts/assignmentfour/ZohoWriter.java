package advancedjavaconcepts.assignmentfour;

import advancedjavaconcepts.ColorCode;

public class ZohoWriter {
    private String word;

    ZohoWriter(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public String bold() {
        if (word.contains(ColorCode.BOLD))
            return word.replace(ColorCode.BOLD, "");
        return ColorCode.BOLD + word + ColorCode.RESET;
    }

    public String italic() {
        if (word.contains(ColorCode.ITALIC))
            return word.replace(ColorCode.ITALIC, "");
        return ColorCode.ITALIC + word + ColorCode.RESET;
    }

    public String underline() {
        if (word.contains(ColorCode.UNDERLINE))
            return word.replace(ColorCode.UNDERLINE,"");
        return ColorCode.UNDERLINE + word + ColorCode.RESET;
    }

    @Override
    public String toString() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
