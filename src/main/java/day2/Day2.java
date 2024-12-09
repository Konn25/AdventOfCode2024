package day2;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day2 {

    public static void main(String[] args){


        int safeReports = 0;

        String path = "src/main/java/day2/inputs.txt";

        Map<Integer,Integer[]> loadedData = new HashMap<>();

        loadData(path,loadedData);

        for (int i = 0; i <loadedData.size() ; i++) {

            List<Integer> dataList = Arrays.stream(loadedData.get(i)).toList();

            if(checkData(dataList)){
                //System.out.println("Index: " + i +" Values: " + Arrays.stream(loadedData.get(i)).toList());
                safeReports++;
            }
        }

        System.out.println("First part ");
        System.out.println("Safe reports: "+ safeReports);


        int reformedSafeReports = 0;

        for (int i = 0; i <loadedData.size() ; i++) {

            List<Integer> dataList = Arrays.stream(loadedData.get(i)).toList();

            if(checkData2(dataList)){
                //System.out.println("Index: " + i +" Values: " + Arrays.stream(loadedData.get(i)).toList());
                reformedSafeReports++;
            }
        }



        System.out.println("Second part ");
        System.out.println("New safety reports: "+reformedSafeReports);

    }


    public static void loadData(String filePath, Map<Integer,Integer[]> firstList){

        int countIndex = 0;

        try(Scanner sc = new Scanner(new File(filePath))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] separateList = line.split(" ");

                Integer[] convertedList = new Integer[separateList.length];

                for (int i = 0; i <separateList.length ; i++) {
                    convertedList[i] = Integer.parseInt(separateList[i]);
                }

                firstList.put(countIndex,convertedList);

                countIndex++;
            }

        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }


    public static boolean checkData(List<Integer> list) {

        int direction = 0;

        for (int i = 0; i < list.size() - 1; i++) {

            int diff = list.get(i + 1) - list.get(i);

            if (diff == 0 || Math.abs(diff) > 3 || Math.abs(diff) < 1) {
                return false;
            }

            if (direction == 0) {
                if(diff > 0){
                    direction = 1;
                }else{
                    direction = -1;
                }
            } else if ((direction == 1 && diff < 0) || (direction == -1 && diff > 0)) {
                return false;
            }
        }

        return true;
    }


    public static boolean checkData2(List<Integer> list) {

        if (checkData(list)) {
            return true;
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(list);
            modifiedList.remove(i);

            if (checkData(modifiedList)) {
                return true;
            }
        }

        return false;
    }
}

