package advancedjavaconcepts.advancedjavalearnings;

import advancedjavaconcepts.ColorCode;

public class SubArrayCreation {
    public static void main(String[] args) {
        String bold = ColorCode.UNDERLINE + ColorCode.BOLD + "Ragul" + ColorCode.RESET;
        System.out.println(bold.substring(bold.indexOf(ColorCode.BOLD), bold.length()-4));
        int a = maxSubArray(new int[]{2, 3, -8, 7, -1, 2, 3});
        System.out.println(a);

        System.out.println(KadenesAlgorithm(new int[]{1}));
        System.out.println(maxSubArray(new int[]{-8, -9, -8, -7}));
        System.out.println("hi");
    }

    public static int KadenesAlgorithm(int[] arr) {
        int currentSum = arr[0];
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (currentSum < 0) currentSum = 0;
            currentSum += arr[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int maxSubArray(int[] arr) {
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}