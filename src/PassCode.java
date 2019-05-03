import java.util.zip.CRC32;

public class PassCode {

    public String finalShape(String str){
        String s1 = tablePass(str);
        String s2 = runLengthEncoding(s1);
        String s3 = getCode(s2);
        return s3;
    }
    public  String tablePass(String str){
        String pass = "";
        int a = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0+a; j < str.length(); j += 4) {
                pass = pass + str.charAt(j);
            }
            a = a + 1;
        }
        return pass;
    }
    public String runLengthEncoding(String text) {
        String encodedString = "";
        for (int i = 0, count = 1; i < text.length(); i++) {
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1))
                count++;
            else {
                encodedString = encodedString.concat(Integer.toString(count))
                        .concat(Character.toString(text.charAt(i)));
                count = 1;
            }
        }
        return encodedString;
    }
    public  String getCode(String data) {
        CRC32 myCRC = new CRC32( ) ;
        myCRC.update( data.getBytes( ) ) ;
        String str = Long.toString(myCRC.getValue());
        return str;
   }
}
