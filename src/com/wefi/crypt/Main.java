package com.wefi.crypt;

import java.util.Arrays;
import java.util.List;

public class Main {
	
	private static final String  GEN 	= "-genkeys";
	private static final String  BITS 	= "-bits=";
	private static final String  ENC 	= "-encrypt";
	private static final String  TXT 	= "-txt=";
	private static final String  PRIVK 	= "-privk=";
	private static final String  DEC 	= "-decrypt";
	private static final String  PUBK 	= "-pubk=";

	public static void main(String[] args) {
		System.out.println("SimpleCrypt V2.0");
		List<String> argLst = Arrays.asList(args);
		
		boolean genKeys = false;
		boolean encrypt = false;
		boolean decrypt = false;
		boolean help = false;
		int bits = -1;
		String txt = null;
		String privk = null;
		String pubk = null;
		
		if (args.length == 0) {
			help = true;
		} else {

			for (String arg : argLst) {
				if (arg.toLowerCase().equals("-?") ||
						arg.toLowerCase().equals("-help") ||
						arg.toLowerCase().equals("--help") ||
						arg.toLowerCase().equals("--?") ||
						arg.toLowerCase().equals("-usage")) {
					help = true;
				}

				if (arg.toLowerCase().equals(GEN)) {
					genKeys = true;
				}
				if (arg.toLowerCase().startsWith(BITS)) {
					bits = Integer.parseInt(arg.substring(BITS.length()));
				}

				if (arg.toLowerCase().equals(ENC)) {
					encrypt = true;
				}

				if (arg.startsWith(TXT)) {
					txt = arg.substring(TXT.length());
				}
				if (arg.startsWith(PRIVK)) {
					privk = arg.substring(PRIVK.length());
				}

				if (arg.toLowerCase().equals(DEC)) {
					decrypt = true;
				}

				if (arg.startsWith(PUBK)) {
					pubk = arg.substring(PUBK.length());
				}

			}
		}
		
		if (help) {
			System.out.println("Usage: ");
			System.out.println("java -jar SimpleCrypt.jar " + GEN + " " + BITS + "[bits size]"); 
			System.out.println("java -jar SimpleCrypt.jar " + DEC + " " + PRIVK + "[private key] " + TXT +"[text to decrypt]");
			System.out.println("java -jar SimpleCrypt.jar " + ENC + " " + PUBK + "[public key] " + TXT +"[text to encrypt]");
			
		} else
		if (genKeys) {
			if (bits != -1) {
				RSAMethods.createBase64Keys(bits);
			} else {
				System.out.println("Misssing '" + BITS + "' parameter");
			}
		} else 
		if (encrypt) {
			if (pubk == null) {
				System.out.println("Missing '" + PUBK + "' parameter");
			} else if (txt == null) {
				System.out.println("Missing '" + TXT + "' parameter");
			} else {
				System.out.println(RSAMethods.encrypt(txt, pubk));
			}
		} else {
			if (decrypt) {
				if (privk == null) {
					System.out.println("Missing '" + PRIVK + "' parameter");
				} else if (txt == null) {
					System.out.println("Missing '" + TXT + "' parameter");
				} else {
					System.out.println(RSAMethods.decrypt(txt, privk));
				}
			}
		}
	}

}
