package day3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args){

        String url = "src/main/java/day3/input.txt";

        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";

        int counter =  loadData(url,regex);

        System.out.println("First part:");
        System.out.println("Value: "+ counter);


    }


    public static int loadData(String filePath, String regex){

        int counter = 0;

        try(Scanner sc = new Scanner(new File(filePath))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                List<String> segments = new ArrayList<>();

                while (matcher.find()){
                    segments.add(matcher.group());
                }

                String foundNumbersRegex ="\\d{1,3}(?:,\\d{1,3})*";

                List<String> numberList = new ArrayList<>();

                pattern = Pattern.compile(foundNumbersRegex);
                for (String segment : segments) {
                    matcher = pattern.matcher(segment);

                    while (matcher.find()) {
                        numberList.add(matcher.group());
                    }
                }

                //numberList.forEach(System.out::println);

                for (int i = 0; i < numberList.size(); i++) {
                    String[] splitNumber = numberList.get(i).split(",");

                  counter +=  Integer.parseInt(splitNumber[0] )* Integer.parseInt(splitNumber[1]);
                }

            }

        }catch (IOException e){
            System.out.println("ERROR: "+e);
        }
        return counter;
    }
}
