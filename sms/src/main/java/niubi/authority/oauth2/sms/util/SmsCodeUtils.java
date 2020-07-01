package niubi.authority.oauth2.sms.util;

/**
 * @author congcong
 * @className SmsCodeUtils
 * @date 2019/3/10 14:03
 * 类描述
 */
public class SmsCodeUtils {

    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public static String getSmsCode(){
        String code="";
        for(int i=0; i<4;i++){
            code+=codeSequence[(int)(Math.random()*codeSequence.length)];
        }
        return code;
    }

    public static void sendSmsCode(String phone,String code){
        System.out.println("向phone: "+phone+" 发送验证码"+code);
    }
}
