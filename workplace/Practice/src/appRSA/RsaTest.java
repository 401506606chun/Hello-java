package appRSA;

import java.util.HashMap;
import java.util.Map;
import appRSA.RSAUtil;



public class RsaTest {
	public static void main(String args[]) throws Exception {
		String rsaPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPEEEi+8JkGSAlKpD1wFiIq0QKzPls156755TJnohQ+Uqg7wFaC0jtnvnMZciymgMy9T6I2zdU/E4XK0U4KuS33Rz65cK4Ccl9dNpO+Vd37pdXGcqzaKjkLzrGOXJdA/4zKL5Lqp04OzTVue0ZEX0cS4HZwgb5woOhJF4Zh3T0FwIDAQAB";
		String rsaPriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI8QQSL7wmQZICUqkPXAWIirRArM+WzXnrvnlMmeiFD5SqDvAVoLSO2e+cxlyLKaAzL1PojbN1T8ThcrRTgq5LfdHPrlwrgJyX102k75V3ful1cZyrNoqOQvOsY5cl0D/jMovkuqnTg7NNW57RkRfRxLgdnCBvnCg6EkXhmHdPQXAgMBAAECgYAK+310mQ0QMBmWoNDl/LEPNM6RYbDYGSlXVWzHmYLvbGO9PPvGlM0xaJ3Wgg3JICeUttzpFqMFxCSehLTYnfpWwLokamsYbuLB8X446JKUMNVD2L29gGuzYCf03OPZPKwlKrXIBr9H/1GehMdB5224LEmHvKgPG0KAZs0tQVI3eQJBAPUeU+D/JsBv86JiQQkYUYNnf/SaKRxKil2fM3vWuCz+v/N3STk2tWTKt+6uX9fXuRkHPPd/5K8aaCX8HV/4//UCQQCVahz9xEmXWJSLyC1YOtZBLxfrwxNFcB6ywToOh2+wnIno9AfCSomgI+dt1Rp7QV5p6ERyM9jqAs8weX2hRxhbAkEA3Ee0+a5TFP2G5ZeG+rVwC7cntpoT9+jFD/3SNqhwO3904UeB9njD6yCCy2zJlR+4qFVo5MN6sCH7FqfLfkQ3QQJANgs4y01pdPXUXS8dv7EgeFlpZJyd5trkL7IWptS0QISOmZVTDSLa3vK9rguL1ZNSbCpYP5qjVhmICbeYCnLlywJAImhPjrWG60odtU4q6cZQGr8CEJzP3zyS/iQEMAofEfpde6MaXvrhorgRZCXvnpBemvwgZwCAceR6Ac4px7owKQ==";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", "201601070000000002");
		param.put("repaymentStatus", "2");
		param.put("peroidNum", "2cb24c1aafea3674b704849253ff82c04c742d1ca67afd8d7748a9cbcc6a78a8");
//		param.put("repaymentTime", "2");
		String params = "{\"loanId\":\"201601070000000002\",\"repaymentStatus\":\"2\",\"repaymentStatus\":\"2cb24c1aafea3674b704849253ff82c04c742d1ca67afd8d7748a9cbcc6a78a8\"}";
		System.out.println("params=" + RSAUtil.encryptByRSAPub(params, rsaPubKey));
		System.out.println("params=" + new String (RSAUtil.decryptByRSA(RSAUtil.encryptByRSAPub(params, rsaPubKey), rsaPriKey)));
//		System.out.println("params=" + new String (RSAUtil.decryptByRSA("rT6TXurgctnVsZyVJwQAd4vj0pukrepv0VXITGi1C+oYsT7CJz9VyuEJT4IMvhcB\r\nKhKwBf5LphuxQtx7HP7cqXVRZC1iSPCYjfOn9+Oh5QVYOvQvoB+wmiHcdyXEEjdu\r\neebab+z5KBVk5w2Qy86M0bQDzmsDJ35ZF7YpZCS\/xI6uTKkAi3MpHEHa6JvQswmW\r\nBMpfHRv3pLJJ4ucqnLeB80D48rWc441ep61f\/VK3zMa3zEuQhp8LkXXzA1ekBy0O\r\nmVfE556nF0lbeKRbnozXZjSFa2g9TI0PCyly1Idpb7OcUdzbR5fswBPW1I8sQqUH\r\nghfSXunCawG9K6opcRz378x53wspRWgp9BkVv5hIv\/\/tbYeiFxTSypVmUspdiDwz\r\n8TrE4Mu3yDfDRZ\/IVmUCbZdRp5f\/1igZE\/m5McbADX0S\/X+wPZKaHV6WrorYobFP\r\nTIF3vckqaWwu3gkgoB15SfABMSuj0Ajj9Zre+IDnmVR6+Fy4\/pEhaT9KWQJ598Jp\r\n1AgAIb\/sIRTKgaRbOZ7gxlLHuO\/IItktYQDCrtI\/TYmBDjiAs0WFTY6Ads34GuQH\r\nfjGJsGMrbdAlyyZ0hYb8ISwiUSNYCSW8od\/43GDiSDEIyOC+3dppWmwW9uJdGilH\r\nKVvxDxSFNRSk\/JVJrGO4OQ7Y\/iZpgecxSb6en\/yUqd+dlfXMMuQze+1O3vB5Ex7Q\r\nW2skUghBXIG\/QhaF0K5dwf39Z0J+druoRuzU3MmK7Zz0wsVDKbT8xQu8rBiCgVdl\r\nDM0+16bgHjChWYayMrkLWeRu6hK1Zhy7C9xUlhgATR7+dD3n8vfyeNsNUsbRqv09\r\nvKYxVael31EfOSskFHncp2t6SaYjiE+Qz7wbXY8uqEFUU\/EPY30nCaJIMWM5kfaK\r\nwGwie60fNGk1LncFwfA56jRkAHCVf2TT5Jd9WVZ0gPLF7csNEp9A8FXy45o++Ec6\r\nJUlJXAfZhUKRroBqh4WctQ==",)));
	}
}

