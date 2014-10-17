package is.ru.stringcalculator;

import java.util.ArrayList;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		/*else if(text.contains("-")){
			throw new RuntimeException(("Negatives not allowed:" + ))
		}*/
		else if(text.startsWith("//")){
			char delim = text.charAt(2);
			String delimiter = Character.toString(delim);
			String numbers = text.substring(4);
			return sum(splitNumbers(numbers, delimiter));
		}
		else if(text.contains(",") || text.contains ("\n")){
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",|\n");
	}
	
	private static String[] splitNumbers(String numbers, String delimiter){
		return numbers.split(delimiter);
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
 	    ArrayList<Integer> list = new ArrayList<Integer>();
        for(String number : numbers){
        	Integer num = toInt(number);
        	if(num < 0){
        		list.add(num);
        	}
		    total += num;
		}
        if(list.isEmpty()){
        	return total;
        }
        else{
        	String negatives = "";
        	for(Integer i: list){
        		negatives = list.toString();
        	}
        	String neg = negatives.substring(1, negatives.length() - 1);
        	throw new RuntimeException("Negatives not allowed: " + neg);
        }
    }



}
