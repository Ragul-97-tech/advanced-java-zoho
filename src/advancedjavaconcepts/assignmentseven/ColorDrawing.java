package advancedjavaconcepts.assignmentseven;

import java.util.HashSet;
import java.util.Objects;

class Color {
    String colorCode;
    String colorName;
    Color(String colorCode, String colorName) {
        this.colorCode = colorCode;
        this.colorName = rgb(colorCode, colorName);
    }

    public String rgb(String colorCode, String txt) {
        int red = Integer.parseInt(colorCode.substring(1,3),16);
        int green = Integer.parseInt(colorCode.substring(3,5),16);
        int blue = Integer.parseInt(colorCode.substring(5,7),16);
        return "\u001B[38;2;" + red + ";" + green + ";" + blue + "m" + txt + "\u001B[0m";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof  Color)) return false;
        Color c = (Color) obj;
        return this.colorCode.equals(c.colorCode) && this.colorName.equals(c.colorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.colorCode, this.colorName);
    }

    @Override
    public String toString() {
        return "\nColor Code: " + colorCode + "\nColor Name: " + colorName;
    }
}

public class ColorDrawing {
    public static void main(String[] args) {
        HashSet<Color> colors = new HashSet<>();
        colors.add(new Color("#ffffff", "white"));
        colors.add(new Color("#ffffff", "white"));
        colors.add(new Color("#000000", "black"));
        colors.add(new Color("#d13dad", "dark bright magenta"));
        colors.add(new Color("#000000", "black"));
        System.out.println(colors);
    }
}
