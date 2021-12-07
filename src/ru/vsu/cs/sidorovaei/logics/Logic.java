package ru.vsu.cs.sidorovaei.logics;

public class Logic {
    public static boolean checkIfArrIsNull(int[][] arr) {
        if (arr == null) {
            return true;
        } else return false;
    }
    public static boolean checkIfArrIsEmpty(int[][] arr) {
        if (arr.length == 0) {
            return true;
        } else return false;
    }
    public static boolean checkIfArrIsRectangle(int[][] arr) {
        boolean flag = true;
        for (int r = 1; r < arr.length; r++) {
            if (arr[r].length != arr[0].length) {
                flag = false;
            }
        }
        return flag;
    }

    public static boolean equalRows(int[] arr1, int[] arr2) {
        int count = 0;
        for (int c = 0; c < arr1.length; c++) {
            if (arr1[c] == arr2[c]) {
                count++;
            }
        }
        return count == arr1.length;
    }
    public static int[][] deleteNullRows(int[][] arr, int resultRowCount) {
        int[][] arr2 = new int[resultRowCount][arr[0].length];
        System.arraycopy(arr, 0, arr2, 0, resultRowCount);
        return arr2;
    }
    public static int[][] deleteEqualRows(int[][] arr) {
        int[] row = arr[0];
        int[][] arrResultWithNullRows = new int[arr.length][arr[0].length];
        arrResultWithNullRows[0] = row;
        int resultRowCount = 1;
        for (int i = 1; i < arr.length; i++) {
            if (!equalRows(row, arr[i])) {
                arrResultWithNullRows[resultRowCount] = arr[i];
                resultRowCount++;
                row = arr[i];
            }
        }
        return deleteNullRows(arrResultWithNullRows, resultRowCount);
    }
}