package signTest;
import signTest.Demo;
public class Test {
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
