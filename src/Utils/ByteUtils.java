package Utils;

import java.nio.ByteBuffer;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Byte转换工具
 *
 * @author yangle
 */
public class ByteUtils {
    /**
     * 十六进制字符串转byte[]
     *
     * @param hex
     *            十六进制字符串
     * @return byte[]
     */

    public static byte[] hexStr2Byte(String hex) {

        if (hex == null) {
            return new byte[] {};
        }
        // 奇数位补0
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        int length = hex.length();
        ByteBuffer buffer = ByteBuffer.allocate(length / 2);
        for (int i = 0; i < length; i++) {
            String hexStr = hex.charAt(i) + "";
            i++;
            hexStr += hex.charAt(i);
            byte b = (byte) Integer.parseInt(hexStr, 16);
            buffer.put(b);
        }

        return buffer.array();
    }
    /**
     * byte[]转十六进制字符串
     *
     * @param array
     *            byte[]
     * @return 十六进制字符串
     */
    public static String byteArrayToHexString(byte[] array) {
        if (array == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(byteToHex(array[i]));
        }
        return buffer.toString();
    }
    /**
     * byte转十六进制字符
     *
     * @param b
     *            byte
     * @return 十六进制字符
     */
    public static String byteToHex(byte b) {

        String hex = Integer.toHexString(b & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        return hex.toUpperCase(Locale.getDefault());
    }

    /**
     * byte[]转字符串
     *
     * @param array
     *            byte[]
     * @return 十六进制字符串
     */
    public static String byteArrayToString(byte[] array) {
        if (array == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append((char) array[i]);
        }
        return buffer.toString();
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static char[] hex2ascll_1bit(byte[] bytes){
        char[] result = new char[bytes.length/2];
        for(int i=0,j=0; i<bytes.length; ){
            int temp = ((int)bytes[i++]) - 48;
            int temp2 = ((int)bytes[i++]) - 48;
            result[j++] = (char)(temp*16 + temp2);
        }
        return result;
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static char[] hex2ascll_2bit(byte[] bytes){
        char[] result = new char[bytes.length];
        for(int i=0; i<bytes.length;i++){
            result[i] = (char)(bytes[i]);
        }
        return result;
    }

    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes).flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

//    public static byte[] getBytes(char[] chars) {
//        Charset cs = Charset.forName("UTF-8");
//        CharBuffer cb = CharBuffer.allocate(chars.length);
//        cb.put(chars);
//        cb.flip();
//        ByteBuffer bb = cs.encode(cb);
//        return bb.array();
//    }
//
//    public static char byteToChar(byte[] b) {
//        int hi = (b[0] & 0xFF) << 8;
//        int lo = b[1] & 0xFF;
//        return (char) (hi | lo);
//    }
//
//    public static byte[] charToByte(char c) {
//        byte[] b = new byte[2];
//        b[0] = (byte) ((c & 0xFF00) >> 8);
//        b[1] = (byte) (c & 0xFF);
//        return b;
//    }
}