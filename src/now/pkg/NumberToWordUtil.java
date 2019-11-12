package now.pkg;

import java.util.ArrayList;
import java.util.List;

public class NumberToWordUtil {
	
	public static String numberToWord(Integer number) throws Exception {

		List<String> word = new ArrayList<String>();
		String numberType = null;
		String finalWord = "Number in Words: ";

		if (number < 100 & number > 1) {
			numberType = "TENS";
			word = tensToWord(number, numberType);	
		} else if (number >= 100 & number < 1000) {
			word = hundredsToWords(number, "HUNDREDS");
		} else if (number>= 1000 && number < 100000)			
            word =thousandsToWords(number,"THOUSANDS",false);
          else if (number>= 100000 && number < 1000000)			
              word = lakhsToWords(number,"LAKH",false);
          else if (number>= 1000000 && number < 10000000)			
              word = millionToWords(number,"MILLION");
          else if (number>= 10000000 && number < 100000000)			
              word = millionToWords(number,"TENMILLION");
          else if (number>= 100000000 && number < 1000000000)			
              word = millionToWords(number,"HUNDREDMILLION");
          else 
        	  throw new Exception("Invalid Value"); 
		
		for (String s : word) {
			finalWord = finalWord + ' ' + s;
		}
		System.out.println("*********************************************************************");
		System.out.println(finalWord);
		System.out.println("*********************************************************************");
		
		return finalWord;

	}


	
	public static List<String> tensToWord(Integer number, String numberType) {
		String[] ones = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
		String[] tens = { "Ten", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy", "Eigty", "Ninty" };
		int index = 0;
		int oneIndex = 0;
		List<String> word = new ArrayList<String>();
		if (numberType.equals("TENS")) {
			index = number / 10;		
			word.add(tens[index - 1]);
		}

		if (number % 10 != 0) {
			oneIndex = number % 10;			
			word.add(ones[oneIndex - 1]);
		}
		System.out.println("InWords tensToWord" + word.toString());
		
		
		return word;
	}

	private static List<String> hundredsToWords(Integer number, String numberType) {
		String[] hundreds = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
		int index = 0;
		final String HUNDRED = " Hundred";
		final String AND = " and";
		List<String> word = new ArrayList<String>();
		List<String> tensWord = new ArrayList<String>();

		if (numberType.equals("HUNDREDS")) {
			index = number / 100;			
			word.add(hundreds[index - 1] + HUNDRED);
		}

		if (number % 100 != 0) {
			int tensNumber = number % 100;
			tensWord = tensToWord(tensNumber, "TENS");		
			word.add(AND);
			word.addAll(tensWord);
		}
		System.out.println("InWords hundredsToWords" + word.toString());
		return word;
	}
	
	private static List<String> thousandsToWords(Integer number, String numberType,Boolean and) {
		String[] thousands = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten"};

		int index = 0;
		final String THOUSAND = " Thousand";
		List<String> word = new ArrayList<String>();
		List<String> hundredsWord = new ArrayList<String>();
		List<String> tensNumber = new ArrayList<String>();
		int hundresNumber = 0;
		int tNumber =0;
		final String AND = "and";

		if (numberType.equals("THOUSANDS") & number>= 1000) {
			index = number / 1000;
			if (index > 10 & index <100) {
				tensNumber = tensToWord(index,"TENS");
			    word.addAll(tensNumber);
			    word.add(THOUSAND);
			}
			else if (index < 10) 
				word.add(thousands[index - 1] + THOUSAND);	
			else {
				tNumber = number/1000;
				word = hundredsToWords(tNumber, "HUNDREDS");
			    word.add(THOUSAND);				
			}
			
			if (and)
				word.add(AND);			

		}

		if (number % 1000 != 0) {
			hundresNumber = number % 1000;
			hundredsWord = hundredsToWords(hundresNumber, "HUNDREDS");
			word.addAll(hundredsWord);
		}
		System.out.println("thousands" + word.toString());
		
		return word;
	}
	
	private static List<String> lakhsToWords(Integer number, String numberType,Boolean and) {
		String[] lakhs = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten"};

		int index = 0;
		final String LAKHS = " Lakh";
		final String AND = " and";
		List<String> word = new ArrayList<String>();
		List<String> thousandsWord = new ArrayList<String>();
		List<String> tensNumber = new ArrayList<String>();

		if (numberType.equals("LAKH")) {
			index = number / 10000;
			if (index > 10) {
				tensNumber = tensToWord(index,"TENS");
			    word.addAll(tensNumber);
			    word.add(LAKHS);
			}
			else
				word.add(lakhs[index - 1] + LAKHS);					
			System.out.println("thousands" + word.toString());
		}		

		if (number % 10000 != 0) {
			int thousandsNumber = number % 10000;
			thousandsWord = thousandsToWords(thousandsNumber, "THOUSANDS",true);		
			word.addAll(thousandsWord);
		}
		System.out.println("InWords thousandsToWords" + word.toString());
		return word;
	}
	
	private static List<String> millionToWords(Integer number, String numberType) {
		String[] millions = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten"};

		int index = 0;
		final String MILLION = " Million";
		final String AND = " and";
		List<String> word = new ArrayList<String>();
		List<String> lakhsWord = new ArrayList<String>();
		List<String> tensNumber = new ArrayList<String>();
		List<String> hundredsNumber = new ArrayList<String>();
		List<String> thousandsWord = new ArrayList<String>();
		int thousandsNumber = 0;
		int lakhsNumber =0;

		index = number / 1000000;
			if (index > 10 & numberType.equals("TENMILLION")) {
				tensNumber = tensToWord(index,"TENS");
			    word.addAll(tensNumber);
			    word.add(MILLION);
			}
			else if (numberType.contentEquals("HUNDREDMILLION")) {
				hundredsNumber = hundredsToWords(index,"HUNDREDS");
			    word.addAll(hundredsNumber);
			    word.add(MILLION);
			}
			else
				word.add(millions[index - 1] + MILLION);					
 
			System.out.println("MILLION" + word.toString());		  

	    lakhsNumber = number % 1000000;

		if (number % 1000000 != 0 & index < 10) {
			lakhsWord = lakhsToWords(lakhsNumber, "LAKH",true);		
			word.addAll(lakhsWord);
		}
		else if(index>10 & index <100){
			thousandsNumber = number % 1000000;
			thousandsWord = thousandsToWords(thousandsNumber, "THOUSANDS",false);
			word.addAll(thousandsWord);
		} else {
			lakhsWord = lakhsToWords(lakhsNumber, "LAKH",true);
			word.addAll(lakhsWord);
		}	
		
		System.out.println("InWords Millions" + word.toString());		
			
		return word;
	}


}
