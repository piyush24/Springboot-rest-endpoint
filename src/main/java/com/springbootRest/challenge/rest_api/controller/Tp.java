package com.springbootRest.challenge.rest_api.controller;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

public class Tp {
    public static void main(String args[]) {
        Integer[] intArray = new Integer[]{-1, 4, -1, 3, 2};
        int[] A = new int[]{60, 80, 40};

        int[] B = new int[]{2, 3, 5};

        String[] stringArray = {"a", "b", "c"};
        Character[] charArray = new Character[]{'a', 'b', 'c'};
        List<Integer> intList = Arrays.asList(intArray);
        List<String> stringList = Arrays.asList(stringArray);
        List<Character> charList = Arrays.asList(charArray);
        String s = "555-372-654";
//        stringMethod(s);


        System.out.println(solution(A, B, 5, 2, 200));
//        stringArrayMethod(stringArray);
        //  intArrayMethod(intArray);
//        charArrayMethod(charArray);
//        stringArrayListMethod(stringList);
//        intArrayListMethod(intList);
//        charArrayListMethod(charList);
    }

    static int solution(int[] A, int[] B, int M, int X, int Y) {
        int count = 1;
        int currentWeight = 0;
        int currentPersons = 0;
        int endIndex = 0;
        Set<Integer> currentFloorStop = new HashSet<>();
        for (int startIndex = 0; startIndex < A.length; ) {
            if (endIndex != A.length) {
                currentWeight = currentWeight + A[endIndex];
                currentPersons = currentPersons + 1;
            }
            if (currentWeight > Y || currentPersons > X || endIndex == A.length) {
                for (int j = startIndex; j < endIndex; j++) {
                    currentFloorStop.add(B[j]);
                }
                count = count + currentFloorStop.size();
                if (endIndex != A.length) {
                    count = count + 1;
                }
                startIndex = endIndex;
                currentPersons = 0;
                currentWeight = 0;
                currentFloorStop.clear();
            } else {
                endIndex++;
            }
        }
        return count;
    }

    static void stringMethod(String S) {
        String outputString = "";
        String stringWithoutSpacesOrDash = S.replaceAll(" ", "").replaceAll("-", "");
        for (int i = 0; i < stringWithoutSpacesOrDash.length(); ) {
            if ((i + 2) == stringWithoutSpacesOrDash.length()) {
                if (outputString.length() == 0) {
                    outputString = stringWithoutSpacesOrDash.substring(i, i + 2);
                } else {
                    outputString = outputString + "-" + stringWithoutSpacesOrDash.substring(i, i + 2);
                }
                break;
            } else {
                if ((i + 4) == stringWithoutSpacesOrDash.length()) {
                    if (outputString.length() == 0) {
                        outputString = stringWithoutSpacesOrDash.substring(i, i + 2);
                    } else {
                        outputString = outputString + "-" + stringWithoutSpacesOrDash.substring(i, i + 2);
                    }
                    i = i + 2;
                } else {
                    if (outputString.length() == 0) {
                        outputString = stringWithoutSpacesOrDash.substring(i, i + 3);
                    } else {
                        outputString = outputString + "-" + stringWithoutSpacesOrDash.substring(i, i + 3);
                    }
                    i = i + 3;
                }
            }
        }
        System.out.println("Code is working ok  : " + outputString);
    }


    String updateOutputStringWithSeperatortConditional(String output, String input, int startIndex, int endIndex) {
        if (output.length() == 0) {
            return input.substring(startIndex, endIndex);
        } else {
            return "-" + input.substring(startIndex, endIndex);
        }
    }

    static void stringArrayMethod(String[] stringArray) {

    }

    static void intArrayMethod(Integer[] intArray) {
        int i = 0, count = 0;
        while (true) {
            count++;
            if (intArray[i] == -1) {
                break;
            } else {
                i = intArray[i];
            }
        }
        System.out.println("Code is working : " + count);
        return;

    }

    static void charArrayMethod(Character[] charArray) {

    }

    static void stringArrayListMethod(Collection<String> stringArray) {

    }

    static void intArrayListMethod(Collection<Integer> stringArray) {

    }

    static void charArrayListMethod(Collection<Character> stringArray) {

    }
}
