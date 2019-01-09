//import org.apache.commons.codec.binary.Base64;  
import com.lakala.amber.util.Base64;

public class testCase {

	public static class Base64Util {
	        
	    public String encodeStr(String plainText){  
	        byte[] b=plainText.getBytes();  
//	        Base64 base64=new Base64();  
	        b=Base64.encode(b);  
	        String s=new String(b);  
	        return s;  
	    }  

	    public String decodeStr(String encodeStr){  
	        byte[] b=encodeStr.getBytes();  
//	        Base64 base64=new Base64();  
	        b=Base64.decode(b);  
	        String s=new String(b);  
	        return s;  
	    }  
	    public static void main(String[] argus){
	            Base64Util encode =new Base64Util();
	            String str = "********************";
	            String Auth =encode.encodeStr(str);
	            System.out.println(Auth);
//	            vars.put("str", str);
//	            vars.put("Auth", Auth);
	    }
	}

}
