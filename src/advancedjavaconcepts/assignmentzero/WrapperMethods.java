package advancedjavaconcepts.assignmentzero;

import advancedjavaconcepts.ColorCode;


public class WrapperMethods {
    public static void main(String[] args) {
        Integer a = 10;
        System.out.println(a.byteValue());
        System.out.println(ColorCode.boxDouble("   Integer.numberOfTrailingZeros(10000) -> " + ColorCode.colored("green", ""+Integer.numberOfTrailingZeros(10000) + "    ")));

        System.out.println(ColorCode.separator(50));

        System.out.println(ColorCode.boxDouble("  Integer.bitCount()  "));
        System.out.println(ColorCode.colored("green", "It returns integer the number of digits 1000000000 -> "+Integer.bitCount(1000000000)));

        System.out.println(ColorCode.boxDouble("  Long.bitCount()  "));
        System.out.println(ColorCode.colored("green", "It returns integer the number of digits 1000000000000000000L -> "+Long.bitCount(1000000000000000000L)));

        System.out.println(ColorCode.boxDouble("  Float.floatToIntBits()  "));
        System.out.println("It also returns Integer.");
        System.out.println("This method converts a float value into the IEEE-754 binary representation stored inside an int.");
        System.out.println(ColorCode.colored("green", "10 -> " + Float.floatToIntBits(10)));

        System.out.println(ColorCode.boxDouble("  Float.intBitsToFloat()  "));
        System.out.println("It returns float.");
        System.out.println("This method does the opposite:\n" +
                "It takes an integer containing the 32-bit IEEE-754 pattern.\n" +
                "It converts that pattern back into the actual float value.");
        System.out.println(ColorCode.colored("green", "0x7FA12345 -> " + Float.intBitsToFloat(0x7FA12345)));

        System.out.println(ColorCode.boxDouble("  Float.floatToRawIntBits()  "));
        System.out.println("It also returns Integer.");
        System.out.println("Float.floatToRawIntBits(float value)\n" +
                "This gives you the raw 32-bit IEEE-754 representation of the float exactly as it is stored in memory.");
        System.out.println(ColorCode.colored("green", "0x7FA12345 -> " + Float.floatToRawIntBits(0x7FA12345)));

        System.out.println(ColorCode.boxDouble("   Max value of int: " + ColorCode.colored("green", ""+Integer.MAX_VALUE) + "  "));
        System.out.println(ColorCode.boxDouble("   Min value of int: " + ColorCode.colored("green", ""+Integer.MAX_VALUE) + "  "));

        System.setProperty("myAge", "25");
        System.out.println(ColorCode.boxDouble("   Integer.getInteger()   "));
        System.out.println("getInteger(): " + ColorCode.colored("green", Integer.getInteger("myAge") + ""));

        System.out.println(a.intValue());

        System.out.println(ColorCode.boxDouble("   Integer.valueOf()   "));
        System.out.println("Converts a string to an Integer object (not primitive");
        System.out.println("valueOf(): " + ColorCode.colored("green", Integer.valueOf("123") + ""));

        System.out.println(ColorCode.boxDouble("   Integer.parseInt(String s)   "));
        System.out.println("Converts a string to a primitive int");
        System.out.println("parseInt(): " + ColorCode.colored("green", Integer.parseInt("12324") + ""));
        System.out.println(ColorCode.error("If the string is not a number → throws NumberFormatException."));

        System.out.println(ColorCode.boxDouble("   Integer.compare()   "));
        System.out.println(ColorCode.boxDouble("   Integer.compareTo()   "));
        System.out.println("0 → if both numbers are equal\n" +
                "negative value → if x is smaller than y\n" +
                "positive value → if x is greater than y");
        System.out.println("Integer.compare(-10, 5) -> " + ColorCode.colored("green", "" + Integer.compare(-10, 5)));
        System.out.println("Integer.valueOf(\"10\").compareTo(1) -> " + ColorCode.colored("green", "" + Integer.valueOf("10").compareTo(1)));

//        System.out.println(Integer.compareUnsigned(-10, 5));
        System.out.println(ColorCode.boxDouble("   Double.isNaN()   " + ColorCode.colored("green", ""+Double.isNaN(Math.sqrt(-1)) + "   ")));
        System.out.println(ColorCode.boxDouble("   Double.isFinite()   " + ColorCode.colored("green", ""+Double.isFinite(25/5) + "   ")));
        System.out.println(ColorCode.boxDouble("   Double.isInFinite()   " + ColorCode.colored("green", ""+Double.isInfinite(1/0.0) + "    ")));

//        System.out.println(Integer.reverse(10));

        System.out.println(ColorCode.boxDouble("   Boolean.parseBoolean()   " + ColorCode.colored("green", ""+Boolean.parseBoolean("true") + "    ")));
        System.out.println(ColorCode.boxDouble("   Integer.decode()   " + ColorCode.colored("green", ""+Integer.decode("-#0f0f0f") + "    ")));

        System.out.println(ColorCode.boxDouble("   Character.digit()   " + ColorCode.colored("green", ""+Character.digit('f',10) + "    ")));
        System.out.println(ColorCode.boxDouble("   Character.isMirrored()   " + ColorCode.colored("green", ""+Character.isMirrored(')') + "    ")));

        System.out.println(ColorCode.boxDouble("   Character.isLetter('d')   " + ColorCode.colored("green", ""+Character.isLetter('d') + "    ")));
        System.out.println(ColorCode.boxDouble("   Character.isAlphabetic('~')   " + ColorCode.colored("green", ""+Character.isAlphabetic('~') + "    ")));
        System.out.println(ColorCode.boxDouble("   Character.isDigit('2')   " + ColorCode.colored("green", ""+Character.isDigit('2') + "    ")));
        System.out.println(ColorCode.boxDouble("   Character.isLowerCase('a')   " + ColorCode.colored("green", ""+Character.isLowerCase('a') + "    ")));
        System.out.println(ColorCode.boxDouble("   Character.isUpperCase('a')   " + ColorCode.colored("green", ""+Character.isUpperCase('a') + "    ")));
    }
}
