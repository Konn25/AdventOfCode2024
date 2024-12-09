package day1;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day1 {
    public static void main(String[] args) {


        String path = "src/main/java/day1/data.txt";

        ArrayList<Integer> firstList = new ArrayList<>();

        ArrayList<Integer> secondList = new ArrayList<>();

        loadData(path,firstList,secondList);

        int value = findAndCompareMinValues(firstList,secondList);

        System.out.println("First part: ");
        System.out.println("Result: "+value);

        System.out.println("\nSecond part: ");
        loadData(path,firstList,secondList);
        int result2 = findAndCountSimilarNumber(firstList,secondList);
        System.out.println("Result: " + result2);

    }


    public static void loadData(String filePath,ArrayList<Integer> firstList, ArrayList<Integer> secondList){
        try(Scanner sc = new Scanner(new File(filePath))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] separateList = line.split("   ");

                firstList.add(Integer.parseInt(separateList[0]));
                secondList.add(Integer.parseInt(separateList[1]));
            }

        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
    }

    public static int findAndCompareMinValues(ArrayList<Integer> firstList, ArrayList<Integer> secondList){

        int value = 0;

        Optional<Integer> minValueFirst;
        Optional<Integer> minValueSecond;

        while(!firstList.isEmpty()){
            minValueFirst = firstList.stream().min(Integer::compareTo);
            minValueSecond = secondList.stream().min(Integer::compareTo);

            if(minValueSecond.isPresent()){

                //System.out.println("First: " + minValueFirst.get() + " Second: "+ minValueSecond.get());

                value += abs(minValueFirst.get()-minValueSecond.get());

              //  System.out.println("Distance: "+abs(minValueFirst.get()-minValueSecond.get()));

                firstList.remove(minValueFirst.get());
                secondList.remove(minValueSecond.get());
            }
            //System.out.println("First size: " + firstList.size() + " Second size: " + secondList.size());
        }

        return value;
    }

    public static int findAndCountSimilarNumber(ArrayList<Integer> firstList, ArrayList<Integer> secondList){

        final int[] result = {0};
        int count = 0;

        Map<Integer,Integer> map = new HashMap<>();

        for (Integer firstValue : firstList) {
            for (Integer secondValue : secondList) {
                if (Objects.equals(firstValue, secondValue)) {
                    count++;
                }
            }
            map.put(firstValue, count);
            count = 0;
        }

        Map<Integer,Integer> reducedMap = map.entrySet().stream()
                .filter(data -> data.getValue() !=0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        reducedMap.forEach((key, value) -> result[0] += (key * value));

        return result[0];
    }

}