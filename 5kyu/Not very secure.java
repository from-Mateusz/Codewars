import java.util.regex.Pattern;

public class SecureTester{
  
  private static final Pattern alphanumeric = Pattern.compile("^[a-zA-Z0-9]+$");
  
  public static boolean alphanumeric(String s){
    return alphanumeric.matcher(s).matches();
  }
}