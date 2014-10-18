package is.ru.stringcalculator;

import java.util.ArrayList;
import  java.util.regex.Pattern;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		else if(text.startsWith("//[")){
			String delim = text.substring(text.indexOf("[") + 1, text.lastIndexOf("]"));
			delim = delim.replace("[", "");
			String[] delimarr = delim.split("]");
			StringBuilder builder = new StringBuilder();
			for(String s : delimarr) {
			    builder.append(Pattern.quote(s));
			    builder.append("|");
			}
			String delimiter = builder.toString();
			delimiter = delimiter.substring(0, delimiter.length() - 1);
			String numbers = text.substring(text.indexOf("\n") + 1, text.length());
			return sum(splitNumbers(numbers, delimiter));
		}
		else if(text.startsWith("//")){
			char delim = text.charAt(2);
			String delimiter = Character.toString(delim);
			String numbers = text.substring(4);
			return sum(splitNumbers(numbers, Pattern.quote(delimiter)));
		}
		else if(text.contains(",") || text.contains ("\n")){
			return sum(splitNumbers(text));
		}
		else if(isInt(text)){
			if(toInt(text) > 1000){
				return 0;
			}
			return toInt(text);
		}
		else{
			throw new RuntimeException("Illegal input");
		}
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
        	else if(num > 1000){
        		continue; // so that the number isn't added to the total
        	}
		    total += num;
		}
        if(list.isEmpty()){
        	return total;
        }
        else{
        	String negatives = list.toString();
        	negatives = negatives.substring(1, negatives.length() - 1);
        	throw new RuntimeException("Negatives not allowed: " + negatives);
        }
    }
    
    private static boolean isInt(String str){
    	try{
    		toInt(str);
    	}catch(NumberFormatException e){
    		return false;
    	}
    	return true;
    }
}
