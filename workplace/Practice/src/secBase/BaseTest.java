package secBase;

public class BaseTest {
	static String str = "********************";
    public static void main(String[] argus){
    	testBase64Util encode =new testBase64Util();
            String auth =encode.encodeStr(str);
            System.out.println(auth);
           
    }

}
