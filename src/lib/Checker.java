package lib;


public class Checker {
	public static boolean isNull(String... values){
		for(String value : values){
			if(value == null || value.isEmpty())
				return true;
		}
		return false;
	}
}
