package easybuy.server.comm;

public class Util {
	
	/** 
     * 检查字符串是否是空白：null、空字符串""或只有空白字符。 
     * StringUtil.isBlank(null)      = true 
     * StringUtil.isBlank("")        = true 
     * StringUtil.isBlank(" ")       = true 
     * StringUtil.isBlank("bob")     = false 
     * StringUtil.isBlank("  bob  ") = false 
     * 
     * @param str 要检查的字符串 
     * 
     * @return 如果为空白, 则返回true
     */ 
	public static boolean isBlank(String str) {  
        int length;  
        if ((str == null) || ((length = str.length()) == 0)) {  
            return true;  
        }
        for (int i = 0; i < length; i++) {  
            if (!Character.isWhitespace(str.charAt(i))) {  
                return false;  
            }  
        }  
        return true;
    }
}
