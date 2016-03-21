package com.dabizi.amm.receiver;

public class GeneralFun {
	public static String verifyFun(String str)
	{
		int verify = 0 ;
		for(int i=0; i<str.length(); i+=2)
		{
			verify += Integer.parseInt(str.substring(i, i+2), 16);
		}
		
		return (toHex(verify)).toUpperCase();
		
	}
	
	public static String toHex(int num) { 
        StringBuffer buf = new StringBuffer(2); 
            if ((num & 0xff) < 0x10) { 
                buf.append("0"); 
            } 
            buf.append(Long.toString(num & 0xff, 16)); 
        return buf.toString(); 
    }
	
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < tmp.length / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
}
