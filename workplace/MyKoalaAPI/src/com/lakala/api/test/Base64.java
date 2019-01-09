package com.lakala.api.test;

import java.util.Arrays;

public class Base64 {
	
	 private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	  private static final int[] INV = new int[256];
	  private static final char[] CHARS_URL;
	  private static final int[] INV_URL;

	  static
	  {

	    CHARS_URL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
	    INV_URL = new int[256];

	    Arrays.fill(INV_URL, -1);
	    int i = 0; for (int iS = CHARS_URL.length; i < iS; i++) {
	      INV_URL[CHARS_URL[i]] = i;
	    }
	    INV_URL[61] = 0;
	  }

	  public static byte[] encode(byte[] arr) {
	    return encode(arr, false, CHARS, false);
	  }

	  public static byte[] encodeURL(byte[] arr) {
	    return encode(arr, false, CHARS_URL, true);
	  }

	  public static byte[] encode(byte[] arr, boolean lineSep) {
	    return encode(arr, lineSep, CHARS, false);
	  }

	  public static byte[] encodeURL(byte[] arr, boolean lineSep) {
	    return encode(arr, lineSep, CHARS_URL, true);
	  }

	  public static String encodeToString(byte[] arr) {
	    return new String(encode(arr, false, CHARS, false));
	  }

	  public static String encodeURLToString(byte[] arr) {
	    return new String(encode(arr, false, CHARS_URL, true));
	  }

	  public static String encodeToString(byte[] arr, boolean lineSep) {
	    return new String(encode(arr, lineSep, CHARS, false));
	  }

	  public static String encodeURLToString(byte[] arr, boolean lineSep) {
	    return new String(encode(arr, lineSep, CHARS_URL, true));
	  }

	  private static byte[] encode(byte[] arr, boolean lineSep, char[] CHARS, boolean url)
	  {
	    int len = arr != null ? arr.length : 0;
	    if (len == 0) {
	      return new byte[0];
	    }

	    int evenlen = len / 3 * 3;
	    int cnt = (len - 1) / 3 + 1 << 2;
	    int destlen = cnt + (lineSep ? (cnt - 1) / 76 << 1 : 0);
	    int left = len - evenlen;
	    byte[] dest;
	    if ((url) && (left > 0))
	      dest = new byte[destlen - (3 - left)];
	    else {
	      dest = new byte[destlen];
	    }

	    int s = 0; int d = 0; for (int cc = 0; s < evenlen; ) {
	      int i = (arr[(s++)] & 0xFF) << 16 | (arr[(s++)] & 0xFF) << 8 | arr[(s++)] & 0xFF;

	      dest[(d++)] = (byte)CHARS[(i >>> 18 & 0x3F)];
	      dest[(d++)] = (byte)CHARS[(i >>> 12 & 0x3F)];
	      dest[(d++)] = (byte)CHARS[(i >>> 6 & 0x3F)];
	      dest[(d++)] = (byte)CHARS[(i & 0x3F)];

	      if (!lineSep) continue; cc++; if ((cc == 19) && (d < destlen - 2)) {
	        dest[(d++)] = 13;
	        dest[(d++)] = 10;
	        cc = 0;
	      }
	    }

	    if (left > 0) {
	      int i = (arr[evenlen] & 0xFF) << 10 | (left == 2 ? (arr[(len - 1)] & 0xFF) << 2 : 0);

	      dest[(destlen - 4)] = (byte)CHARS[(i >> 12)];
	      dest[(destlen - 3)] = (byte)CHARS[(i >>> 6 & 0x3F)];
	      if (!url) {
	        dest[(destlen - 2)] = (left == 2 ? (byte)CHARS[(i & 0x3F)] : 61);
	        dest[(destlen - 1)] = 61;
	      } else if (left == 2) {
	        dest[(destlen - 2)] = (byte)CHARS[(i & 0x3F)];
	      }
	    }
	    return dest;
	  }

	  public static byte[] decode(byte[] arr) {
	    return decode(arr, INV);
	  }

	  public static byte[] decodeURL(byte[] arr) {
	    return decode(arr, INV_URL);
	  }

	  public static byte[] decode(String arr) {
	    return decode(arr.getBytes(), INV);
	  }

	  public static byte[] decodeURL(String arr) {
	    return decode(arr.getBytes(), INV_URL);
	  }

	  private static byte[] decode(byte[] arr, int[] INV)
	  {
	    int length = arr.length;
	    if (length == 0) {
	      return new byte[0];
	    }

	    int sndx = 0; int endx = length - 1;
	    do
	    {
	      sndx++;

	      if (sndx >= endx) break; 
	    }while (INV[(arr[sndx] & 0xFF)] < 0);

	    while ((endx > 0) && (INV[(arr[endx] & 0xFF)] < 0)) {
	      endx--;
	    }
	    int pad = arr[endx] == 61 ? 1 : arr[(endx - 1)] == 61 ? 2 : 0;
	    int cnt = endx - sndx + 1;
	    int sepCnt = 0; int ll = 0; int ss = 0;

	    if ((cnt > 76) && 
	      (arr[76] == 13)) {
	      if ((cnt > 77) && (arr[77] == 10)) {
	        sepCnt = cnt / 78 << 1;
	        ss = 2;
	      } else {
	        sepCnt = cnt / 77 << 1;
	        ss = 1;
	      }
	      ll = 19;
	    }

	    if ((sepCnt == 0) && (cnt > 64) && 
	      (arr[64] == 13)) {
	      if ((cnt > 65) && (arr[65] == 10)) {
	        sepCnt = cnt / 66 << 1;
	        ss = 2;
	      } else {
	        sepCnt = cnt / 65 << 1;
	        ss = 1;
	      }
	      ll = 16;
	    }

	    int len = ((cnt - sepCnt) * 6 >> 3) - pad;
	    byte[] dest = new byte[len];

	    int d = 0;
	    int cc = 0; for (int eLen = len / 3 * 3; d < eLen; ) {
	      int i = INV[arr[(sndx++)]] << 18 | INV[arr[(sndx++)]] << 12 | INV[arr[(sndx++)]] << 6 | INV[arr[(sndx++)]];

	      dest[(d++)] = (byte)(i >> 16);
	      dest[(d++)] = (byte)(i >> 8);
	      dest[(d++)] = (byte)i;

	      if (sepCnt <= 0) continue; cc++; if (cc == ll) {
	        sndx += ss;
	        cc = 0;
	      }
	    }

	    if (d < len) {
	      int i = 0;
	      for (int j = 0; sndx <= endx - pad; j++) {
	        i |= INV[arr[(sndx++)]] << 18 - j * 6;
	      }
	      for (int r = 16; d < len; r -= 8) {
	        dest[(d++)] = (byte)(i >> r);
	      }
	    }

	    return dest;
	  }

}
