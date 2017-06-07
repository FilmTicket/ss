package easybuy.server.comm;

public class Util {
	
	/**
     * StringUtil.isBlank(null)      = true 
     * StringUtil.isBlank("")        = true 
     * StringUtil.isBlank(" ")       = true 
     * StringUtil.isBlank("bob")     = false 
     * StringUtil.isBlank("  bob  ") = false 
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
