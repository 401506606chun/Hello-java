
import java.security.MessageDigest;
import java.util.UUID;
public class Demo {



	/** 
    * 生成要签名的字符串 
    *   
    */ 
   public String getSignature(String channel, String mobile, String identityCard, String noncestr) throws Exception {
       String sKey = "channel=" + channel + "&mobile=" + mobile + "&identityCard=" + identityCard
               + "&noncestr=" + noncestr;
       return getSignature(sKey);
   }


   /** 
    * 生成签名 
    *   
    */ 
   public String getSignature(String sKey) throws Exception {
       String ciphertext = null;
       MessageDigest md = MessageDigest.getInstance("SHA-1");
       byte[] digest = md.digest(sKey.toString().getBytes());
       ciphertext = byteToStr(digest);
       return ciphertext.toLowerCase();
   }
   

   /** 
    * 将字节数组转换为十六进制字符串 
    *  
    * @param byteArray 
    * @return 
    */ 
   private String byteToStr(byte[] byteArray) {  
       String strDigest = "";  
       for (int i = 0; i < byteArray.length; i++) {  
           strDigest += byteToHexStr(byteArray[i]);  
       }  
       return strDigest;  
   }
   
   
   /** 
    * 将字节转换为十六进制字符串 
    *  
    * @param mByte 
    * @return 
    */ 
   private String byteToHexStr(byte mByte) {  
       char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
       char[] tempArr = new char[2];  
       tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
       tempArr[1] = Digit[mByte & 0X0F];  

       String s = new String(tempArr);  
       return s;  
   }
   
   /** 
    * 生成签名所需的秘钥，即 noncestr
    *   
    */ 
   public String getNoncestr() throws Exception {
	   String nonceStr = UUID.randomUUID().toString().replace("-", "");
       return nonceStr;
   }
   
   
   public static void main(String[] args) {
		try {
			Demo demo = new Demo();
			//合作方上送的参数
			String channel = "600003"; //考拉分配给客户渠道号
			String mobile = "13311250357";
			String identityCard = "340826198309220012";
			
			//客户生成的加密字符串
			String noncestr = "7815bdf071d34bfcab0655d92e965308";
			
			//根据上送参数和随机字符串得到签名
			String  sign = demo.getSignature(channel,mobile,identityCard,noncestr);
			System.out.println("根据上送参数得到的签名:"+sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
