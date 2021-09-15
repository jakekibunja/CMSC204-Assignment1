import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordCheckerUtility {
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		boolean samePassword = password.equals(passwordConfirm);
		try {
			if (samePassword == false) {
				throw new UnmatchedException("The passwords do not match.");
			}
		}
		catch (UnmatchedException exception) {
			exception.printStackTrace();
		}
	}
	
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm){
		boolean samePassword = password.equals(passwordConfirm);
		return samePassword;
	}
	
	public static boolean isValidLength(String password) throws LengthException{
		try {
			if (password.length() < 6) {
				throw new LengthException("The password must be at least 6 characters long");
			}
		}
		catch (LengthException exception) {
			exception.printStackTrace();
		}
		return true;
	}
	
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() > 5 && password.length() < 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean hasLowerAlpha(String password)throws NoLowerAlphaException{
		String lowerPassword = password.toLowerCase();
		boolean hasLower = false;
		
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == lowerPassword.charAt(i)) {
				hasLower = true;
			}
		}
		try {
			if (hasLower == false) {
				throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
			}
		}
		catch (NoLowerAlphaException exception) {
			exception.printStackTrace();
		}
		
		return hasLower;
	}


	public static boolean hasUpperAlpha(String password)throws NoUpperAlphaException{
		String upperPassword = password.toUpperCase();
		boolean hasUpper = false;
		
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == upperPassword.charAt(i)) {
				hasUpper = true;;
			}
		}
		try {
			if (hasUpper == false) {
				throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
			}
		}
		catch (NoUpperAlphaException exception) {
			exception.printStackTrace();
		}
		
		return hasUpper;
	}

	public static boolean hasDigit(String password)throws NoDigitException {
		boolean hasDigit = false;
		hasDigit = password.matches(".*\\d.*");
		try {
			if (hasDigit == false) {
				throw new NoDigitException("The password must contain at least one digit");
			}
		}
		catch (NoDigitException exception) {
			exception.printStackTrace();
		}
		return hasDigit;
	}
	
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException{
		boolean sameChar = false;
		for (int i = 0; i < (password.length() - 2); i++) {
			if (password.charAt(i) == password.charAt(i+1)) {
				if (password.charAt(i) == password.charAt(i+2))
					sameChar = true;
			}
		}
		try {
			if (sameChar == true) {
				throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence.");
			}
		}
		catch (InvalidSequenceException exception) {
			exception.printStackTrace();
		}
		return sameChar;
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		boolean hasSpecial = false;
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		hasSpecial = !matcher.matches();
		
		try {
			if (hasSpecial == false) {
				throw new NoSpecialCharacterException("The password must contain at least one special character.");
			}
		}
		catch (NoSpecialCharacterException exception) {
			exception.printStackTrace();
		}

		return hasSpecial;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		
		for (int i = 0; i < passwords.size(); i++) {
			if (!isValidPassword(passwords.get(i))) {
				invalidPasswords.add(passwords.get(i));
			}
		}
		return invalidPasswords;
	}
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException,LengthException,
    NoUpperAlphaException,NoLowerAlphaException,NoDigitException,NoSpecialCharacterException,InvalidSequenceException{
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		hasSameCharInSequence(password);
		hasLowerAlpha(password);
		try {
			if (password.length() > 5 && password.length() < 10) {
				throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
			}	
		}
			catch (WeakPasswordException exception) {
				exception.printStackTrace();
			}
		return true;
	}
	
	public static boolean isValidPassword(String password) throws WeakPasswordException,LengthException,
    NoUpperAlphaException,NoLowerAlphaException,NoDigitException,NoSpecialCharacterException,InvalidSequenceException{
		boolean isValid = true;
		
		isValidLength(password);
		hasUpperAlpha(password);
		hasLowerAlpha(password);
		hasDigit(password);
		hasSpecialChar(password);
		hasSameCharInSequence(password);
		hasLowerAlpha(password);
		isWeakPassword(password);
		
		return true;
	}
	
}

