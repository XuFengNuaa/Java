/**
 * 
 */
package com.nuaa.sys.util.base;

/**
 * @author mahao
 *
 */

import java.io.*;
import java.util.*;

public class StringUtil {
	public static final byte SPACE_TAB[] =
		{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, /* 0x00 - 0x0f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x10 - 0x1f */
		1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x20 - 0x2f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x30 - 0x3f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x40 - 0x4f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x50 - 0x5f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x60 - 0x8f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x70 - 0x7f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x80 - 0x8f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0x90 - 0x9f */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0xa0 - 0xaf */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0xb0 - 0xbf */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0xc0 - 0xcf */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0xd0 - 0xdf */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, /* 0xe0 - 0xef */
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 /* 0xf0 - 0xff */
	};

	public static final String SLASH = getSlash();
	public static final String BACKSLASH = getBackslash();
	public static final String URL_SLASH = getURLSlash();

	/* base64 functions, added by xmsong 2002-12-29 */
	public static final String BASE64_TABLE =
		"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	/* base64 functions, added by xmsong 2002-12-29 */

	/**
	 * File separator
	 */
	public static final String FILE_SEPARATOR = SLASH;

	public static String getSlash() {
		return "/";
	}

	public static String getBackslash() {
		return "\\";
	}

	public static String getURLSlash() {
		return "//";
	}

	public static String getHTMLBlank() {
		return ("&nbsp;");
	}

	public static boolean isEmpty(String string) {
		return ((string == null) || (string.length() == 0));
	}

	public static boolean compare(String a, String b) {
		if (isEmpty(a) && isEmpty(b)) {
			return true;
		} else if (!isEmpty(a) && !isEmpty(b)) {
			return (a.equals(b));
		} else {
			return false;
		}
	}

	public static boolean compareIgnoreCase(String a, String b) {
		if (isEmpty(a) && isEmpty(b)) {
			return true;
		} else if (!isEmpty(a) && !isEmpty(b)) {
			return (a.equalsIgnoreCase(b));
		} else {
			return false;
		}
	}

	public static String copy(String src, int len) {
		if (src == null) {
			return null;
		}

		if (src.length() > len) {
			return (len > 0) ? src.substring(0, len) : null;
		} else {
			return src;
		}
	}

	public static String delete(String src, String delStr) {
		int index;
		StringBuffer buffer;

		if (isEmpty(src) || isEmpty(delStr)) {
			return src;
		}

		buffer = new StringBuffer(src);
		index = src.length();

		while ((index = src.lastIndexOf(delStr, index)) >= 0) {
			buffer.delete(index, index + delStr.length());

			index = index - delStr.length();
		}

		return buffer.toString();
	}

	public static String insert(String src, String anotherStr, int offset) {
		StringBuffer buffer;

		if (isEmpty(src) || isEmpty(anotherStr)) {
			return src;
		}

		buffer = new StringBuffer(src);

		if ((offset >= 0) && (offset <= src.length())) {
			buffer.insert(offset, anotherStr);
		}

		return buffer.toString();
	}

	public static String append(String src, String appendStr) {
		StringBuffer buffer;

		if (isEmpty(src) || isEmpty(appendStr)) {
			return src;
		}

		buffer = new StringBuffer(src);

		buffer.append(appendStr);

		return buffer.toString();
	}

	public static String replace(
		String src,
		String oldStr,
		String newStr,
		boolean isCaseSensitive) {
		int index;
		StringBuffer buffer;
		String s, o;

		if (isEmpty(src) || isEmpty(oldStr) || (newStr == null)) {
			return src;
		}

		s = isCaseSensitive ? src : src.toLowerCase();
		o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		buffer = new StringBuffer(src);
		index = s.length();

		while ((index = s.lastIndexOf(o, index)) >= 0) {
			buffer.replace(index, index + o.length(), newStr);

			index = index - o.length();
		}

		return buffer.toString();
	}

	public static String reverse(String str) {
		StringBuffer buffer;

		if (isEmpty(str)) {
			return str;
		}

		buffer = new StringBuffer(str);

		buffer.reverse();

		return buffer.toString();
	}

	public static String[] split(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0, end;
		String[] result = new String[0];

		if (isEmpty(str)) {
			/* modified by xmsong, 2002-12-10 */
			//			return new String[0];
			return result;
		}

		while (true) {
			index = str.indexOf(delimiter, begin);

			if (index == begin) {
				if (index >= 0) {
					array.add("");
				}
				begin += delimiter.length();
			} else if (index > begin) {
				end = index;

				array.add(str.substring(begin, end));

				begin = index + delimiter.length();
			} else {
				if ((begin >= 0) && (begin < str.length())) {
					array.add(str.substring(begin));
				}

				break;
			}
		}

		if (str.endsWith(delimiter)) {
			array.add("");
		}
		if (array.size() > 0) {
			result = new String[array.size()];

			array.toArray(result);
		}

		return result;
	}

	public static String[] splitWithoutEmpty(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0, end;
		String[] result = new String[0];

		if (isEmpty(str)) {
			return new String[0];
		}

		while (true) {
			index = str.indexOf(delimiter, begin);

			if (index == begin) {
				if (index > 0) {
					array.add("");
				}
				begin += delimiter.length();
			} else if (index > begin) {
				end = index;

				array.add(str.substring(begin, end));

				begin = index + delimiter.length();
			} else {
				if ((begin >= 0) && (begin < str.length())) {
					array.add(str.substring(begin));
				}

				break;
			}
		}

		if (array.size() > 0) {
			result = new String[array.size()];

			array.toArray(result);
		}

		return result;
	}

	public static String warp(String str, int len) {
		StringBuffer buffer;

		if (isEmpty(str)) {
			return str;
		}

		if ((len <= 0) || (len >= str.length())) {
			return str;
		}

		buffer = new StringBuffer();
		StringReader reader = new StringReader(str);
		BufferedReader r = new BufferedReader(reader);
		String line;
		try {
			while ((line = r.readLine()) != null) {
				for (int i = 0; i < line.length(); i = i + len) {
					if (i + len < line.length()) {
						buffer.append(line.substring(i, i + len));
					} else {
						buffer.append(line.substring(i, line.length()));
					}

					buffer.append("\n");
				}
			}
		} catch (IOException e) {
		} finally {
			try {
				r.close();
			} catch (IOException e) {
			}
		}
		return buffer.toString();
	}

	public static String adjustBreakLine(String str) {
		if (isEmpty(str)) {
			return str;
		}

		delete(str, "\r");

		return replace(str, "\n", "\r\n", false);
	}

	public static String capitalize(String str) {
		StringBuffer buffer;

		if (isEmpty(str)) {
			return str;
		}

		buffer = new StringBuffer(str);

		buffer.setCharAt(0, str.substring(0, 1).toUpperCase().charAt(0));

		return buffer.toString();
	}

	public static String deCapitalize(String str) {
		StringBuffer buffer;

		if (isEmpty(str)) {
			return str;
		}

		buffer = new StringBuffer(str);

		buffer.setCharAt(0, str.substring(0, 1).toLowerCase().charAt(0));

		return buffer.toString();
	}

	public static String quote(String str) {
		StringBuffer buffer;

		if (isEmpty(str)) {
			return "\"\"";
		}

		buffer = new StringBuffer(str);

		if (!str.startsWith("\"")) {
			buffer.insert(0, "\"");
		}

		if (!str.endsWith("\"")) {
			buffer.append("\"");
		}

		return buffer.toString();
	}

	public static String extractQuotedString(String str) {
		StringBuffer buffer;
		int index;

		if (isEmpty(str)) {
			return str;
		}

		buffer = new StringBuffer(str);
		index = str.length();

		while (buffer.charAt(buffer.length() - 1) == '\"') {
			buffer.deleteCharAt(buffer.length() - 1);

			index = buffer.length();

			if (index <= 0) {
				break;
			}
		}

		if (buffer.length() == 0) {
			return "";
		}

		while (buffer.charAt(0) == '\"') {
			buffer.deleteCharAt(0);

			index = buffer.length();

			if (index <= 0) {
				break;
			}
		}

		return buffer.toString();
	}

	public static String strChar(String str, char c) {
		if (str == null || str.length() == 0) {
			return null;
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				return str.substring(i);
			}
		}

		return null;
	}

	public static String strRChar(String str, char c) {
		if (str == null || str.length() == 0) {
			return null;
		}

		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) == c) {
				return str.substring(i);
			}
		}

		return null;
	}

	public static String doubleChar(String str, char c) {
		String oldStr = String.valueOf(c);
		String newStr = String.valueOf(c) + String.valueOf(c);

		return replace(str, oldStr, newStr, true);
	}

	public static String[] sort(String[] array) {
		if ((array == null) || (array.length == 0)) {
			return null;
		}

		Arrays.sort(array);

		return array;
	}

	public static List sort(List list) {
		if ((list == null) || (list.size() == 0)) {
			return null;
		}

		Collections.sort(list);

		return list;
	}

	public static String[] toArray(Object[] array) {
		String[] result;

		if ((array == null) || (array.length == 0)) {
			return null;
		}

		result = new String[array.length];

		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				result[i] = array[i].toString();
			}
		}

		return result;
	}

	public static String[] toArray(Vector list) {
		String[] result;
		Object obj;

		if ((list == null) || (list.size() == 0)) {
			return null;
		}

		result = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			obj = list.get(i);

			if (obj != null) {
				result[i] = list.get(i).toString();
			}
		}

		return result;
	}

	public static List copyToList(String[] array, List list, int index) {
		if ((array == null) || (array.length == 0)) {
			return list;
		}

		if ((list == null) || (index < 0)) {
			return list;
		}

		for (int i = 0; i < array.length; i++) {
			if (list.size() <= i + index) {
				list.add(index + i, array[i]);
			} else {
				list.set(index + i, array[i]);
			}
		}

		return list;
	}

	public static boolean contains(Object[] array, String str) {
		Object obj;

		if ((array == null) || (array.length == 0)) {
			return false;
		}

		if (str == null) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			obj = array[i];

			if ((obj != null) && (str.equals(obj.toString()))) {
				return true;
			}
		}

		return false;
	}

	public static int indexOf(Object[] array, String str) {
		Object obj;

		if ((array == null) || (array.length == 0)) {
			return -1;
		}

		if (str == null) {
			return -1;
		}

		for (int i = 0; i < array.length; i++) {
			obj = array[i];

			if ((obj != null) && (str.equals(obj.toString()))) {
				return i;
			}
		}

		return -1;
	}

	public static String deleteHead(String source, String head) {
		if (isEmpty(source) || isEmpty(head)) {
			return source;
		}

		if (source.indexOf(head) == 0) {
			return source.substring(head.length());
		} else {
			return source;
		}
	}

	/**
	 * To judge if a char is a space char.
	 */
	public static boolean isWhiteSpace(char c) {
		if (c >= 256) {
			return false;
		} else {
			return SPACE_TAB[(byte) c] == 1;
		}
	}

	public static String lengthToFit(String str, int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length is too small");
		}

		StringBuffer buffer = new StringBuffer(str);
		buffer.setLength(length);
		for (int i = str.length(); i < length; i++) {
			buffer.setCharAt(i, ' ');
		}

		return buffer.toString();
	}

	/**
	 * Returns true if the email address is valid.  These are of the
	 * form: "local-part@domain"
	 * @param theEmail the email address
	 * @return true if the email address is valid.*/
	public static boolean isValidEmail(String theEmail) {
		if (theEmail == null) {
			return false;
		} else if (theEmail.length() == 0) {
			return false;
		} else if (
			theEmail.length() > 78) { // email addresses must fit on one line
			return false;
		} else {
			// if last character is a period, remove it
			if (theEmail.charAt(theEmail.length() - 1) == '.') {
				theEmail = theEmail.substring(0, theEmail.length() - 1);
				//check for presence of "at" sign
			}
			int atIndex = theEmail.indexOf('@');
			if (atIndex < 0) {
				return false;
			}

			//check for valid characters in the mailbox portion of the address
			String mailbox = theEmail.substring(0, atIndex);
			if (mailbox == null || mailbox.length() == 0) {
				return false;
			} else {
				int oldDotIndex = 0;
				int dotEnd = mailbox.indexOf('.', oldDotIndex);
				boolean done = false;
				do {
					//cannot have 2 dots in a row
					if (dotEnd == oldDotIndex) {
						return false;
					}
					if (dotEnd < 0) {
						dotEnd = mailbox.length();
						done = true;
					}
					for (int i = oldDotIndex; i < dotEnd; i++) {
						char cur = mailbox.charAt(i);
						if (!Character.isLetter(cur)
							&& !Character.isDigit(cur)
							&& cur != '!'
							&& cur != '#'
							&& cur != '$'
							&& cur != '%'
							&& cur != '&'
							&& cur != '\''
							&& cur != '*'
							&& cur != '+'
							&& cur != '-'
							&& cur != '/'
							&& cur != '='
							&& cur != '?'
							&& cur != '^'
							&& cur != '_'
							&& cur != '`'
							&& cur != '{'
							&& cur != '|'
							&& cur != '}'
							&& cur != '~') {
							return false;
						}
					}
					oldDotIndex = dotEnd + 1;
					dotEnd = mailbox.indexOf('.', oldDotIndex + 1);
				} while (!done);
			}

			//check for a valid domain name
			String domain = theEmail.substring(atIndex + 1);
			if (domain == null || domain.length() == 0) {
				return false;
			} else {
				//domains are divided into groups separated by dots
				int oldDotIndex = 0;
				int dotEnd = domain.indexOf('.', oldDotIndex);
				if (dotEnd < 0) {
					return false;
				}
				do {
					//cannot have 2 dots in a row
					if (oldDotIndex == dotEnd) {
						return false;
					}
					//first character must be letter or digit
					if (!Character.isLetter(domain.charAt(oldDotIndex))
						&& !Character.isDigit(domain.charAt(oldDotIndex))) {
						return false;
					}
					//middle characters can be digits, letters, or hyphens
					for (int i = oldDotIndex + 1; i < dotEnd - 1; i++) {
						char cur = domain.charAt(i);
						if (!Character.isLetter(cur)
							&& !Character.isDigit(cur)
							&& cur != '-') {
							return false;
						}
					}
					//last character must be a letter or digit
					if (!Character.isLetter(domain.charAt(dotEnd - 1))
						&& !Character.isDigit(domain.charAt(dotEnd - 1))) {
						return false;
					}
					oldDotIndex = dotEnd + 1;
					dotEnd = domain.indexOf('.', oldDotIndex + 1);
				} while (dotEnd > 0);

				//check the last part of the domain
				String ext = domain.substring(oldDotIndex);
				if (ext == null || ext.length() == 0) {
					return false;
				} else if (
					// the standard TLDs
				// see RFC1591
				!ext.equalsIgnoreCase(
					"com")
						&& !ext.equalsIgnoreCase("edu")
						&& !ext.equalsIgnoreCase("gov")
						&& !ext.equalsIgnoreCase("int")
						&& !ext.equalsIgnoreCase("mil")
						&& !ext.equalsIgnoreCase("net")
						&& !ext.equalsIgnoreCase("org")
						&& !ext.equalsIgnoreCase("arpa")
					&& // the new TLDs
				// http://www.icann.org/tlds/
				!ext
					.equalsIgnoreCase(
						"aero")
						&& !ext.equalsIgnoreCase("biz")
						&& !ext.equalsIgnoreCase("coop")
						&& !ext.equalsIgnoreCase("info")
						&& !ext.equalsIgnoreCase("museum")
						&& !ext.equalsIgnoreCase("name")
						&& !ext.equalsIgnoreCase("pro")
					&& // the country codes
				// http://www.iana.org/cctld/cctld-whois.htm
				// Ascension Island
				!ext
					.equalsIgnoreCase(
						"ac")
					&& // Andorra
				!ext.equalsIgnoreCase("ad")
					&& // United Arab Emirates
				!ext.equalsIgnoreCase("ae")
					&& // Afghanistan
				!ext.equalsIgnoreCase("af")
					&& // Antigua and Barbuda
				!ext.equalsIgnoreCase("ag")
					&& // Anguilla
				!ext.equalsIgnoreCase("ai")
					&& // Albania
				!ext.equalsIgnoreCase("al")
					&& // Armenia
				!ext.equalsIgnoreCase("am")
					&& // Netherlands Antilles
				!ext.equalsIgnoreCase("an")
					&& // Angola
				!ext.equalsIgnoreCase("ao")
					&& // Antartica
				!ext.equalsIgnoreCase("aq")
					&& // Argentina
				!ext.equalsIgnoreCase("ar")
					&& // American Samoa
				!ext.equalsIgnoreCase("as")
					&& // Austria
				!ext.equalsIgnoreCase("at")
					&& // Australia
				!ext.equalsIgnoreCase("au")
					&& // Aruba
				!ext.equalsIgnoreCase("aw")
					&& // Azerbaijan
				!ext.equalsIgnoreCase("az")
					&& // Bosnia and Herzegovina
				!ext.equalsIgnoreCase("ba")
					&& // Barbados
				!ext.equalsIgnoreCase("bb")
					&& // Bangladesh
				!ext.equalsIgnoreCase("bd")
					&& // Belgium
				!ext.equalsIgnoreCase("be")
					&& // Burkina Faso
				!ext.equalsIgnoreCase("bf")
					&& // Bulgaria
				!ext.equalsIgnoreCase("bg")
					&& // Bahrain
				!ext.equalsIgnoreCase("bh")
					&& // Burundi
				!ext.equalsIgnoreCase("bi")
					&& // Benin
				!ext.equalsIgnoreCase("bj")
					&& // Bermuda
				!ext.equalsIgnoreCase("bm")
					&& // Brunei Darussalam
				!ext.equalsIgnoreCase("bn")
					&& // Bolivia
				!ext.equalsIgnoreCase("bo")
					&& // Brazil
				!ext.equalsIgnoreCase("br")
					&& // Bahamas
				!ext.equalsIgnoreCase("bs")
					&& // Bhutan
				!ext.equalsIgnoreCase("bt")
					&& // Bouvet Island
				!ext.equalsIgnoreCase("bv")
					&& // Botswana
				!ext.equalsIgnoreCase("bw")
					&& // Belarus
				!ext.equalsIgnoreCase("by")
					&& // Belize
				!ext.equalsIgnoreCase("bz")
					&& // Canada
				!ext.equalsIgnoreCase("ca")
					&& // Cocos (Keeling) Islands
				!ext.equalsIgnoreCase("cc")
					&& // Congo, Democratic People's Republic
				!ext.equalsIgnoreCase(
						"cd")
					&& // Central African Republic
				!ext.equalsIgnoreCase("cf")
					&& // Congo, Republic of
				!ext.equalsIgnoreCase("cg")
					&& // Switzerland
				!ext.equalsIgnoreCase("ch")
					&& // Cote d'Ivoire
				!ext.equalsIgnoreCase("ci")
					&& // Cook Islands
				!ext.equalsIgnoreCase("ck")
					&& // Chile
				!ext.equalsIgnoreCase("cl")
					&& // Cameroon
				!ext.equalsIgnoreCase("cm")
					&& // China
				!ext.equalsIgnoreCase("cn")
					&& // Colombia
				!ext.equalsIgnoreCase("co")
					&& // Costa Rica
				!ext.equalsIgnoreCase("cr")
					&& // Cuba
				!ext.equalsIgnoreCase("cu")
					&& // Cap Verde
				!ext.equalsIgnoreCase("cv")
					&& // Christmas Island
				!ext.equalsIgnoreCase("cx")
					&& // Cyprus
				!ext.equalsIgnoreCase("cy")
					&& // Czech Republic
				!ext.equalsIgnoreCase("cz")
					&& // Germany
				!ext.equalsIgnoreCase("de")
					&& // Djibouti
				!ext.equalsIgnoreCase("dj")
					&& // Denmark
				!ext.equalsIgnoreCase("dk")
					&& // Dominica
				!ext.equalsIgnoreCase("dm")
					&& // Dominican Republic
				!ext.equalsIgnoreCase("do")
					&& // Algeria
				!ext.equalsIgnoreCase("dz")
					&& // Ecuador
				!ext.equalsIgnoreCase("ec")
					&& // Estonia
				!ext.equalsIgnoreCase("ee")
					&& // Egypt
				!ext.equalsIgnoreCase("eg")
					&& // Western Sahara
				!ext.equalsIgnoreCase("eh")
					&& // Eritrea
				!ext.equalsIgnoreCase("er")
					&& // Spain
				!ext.equalsIgnoreCase("es")
					&& // Ethiopia
				!ext.equalsIgnoreCase("et")
					&& // Finland
				!ext.equalsIgnoreCase("fi")
					&& // Fiji
				!ext.equalsIgnoreCase("fj")
					&& // Falkland Islands (Malvina)
				!ext.equalsIgnoreCase("fk")
					&& // Micronesia, Federal State of
				!ext.equalsIgnoreCase(
						"fm")
					&& // Faroe Islands
				!ext.equalsIgnoreCase("fo")
					&& // France
				!ext.equalsIgnoreCase("fr")
					&& // Gabon
				!ext.equalsIgnoreCase("ga")
					&& // Grenada
				!ext.equalsIgnoreCase("gd")
					&& // Georgia
				!ext.equalsIgnoreCase("ge")
					&& // French Guiana
				!ext.equalsIgnoreCase("gf")
					&& // Guernsey
				!ext.equalsIgnoreCase("gg")
					&& // Ghana
				!ext.equalsIgnoreCase("gh")
					&& // Gibraltar
				!ext.equalsIgnoreCase("gi")
					&& // Greenland
				!ext.equalsIgnoreCase("gl")
					&& // Gambia
				!ext.equalsIgnoreCase("gm")
					&& // Guinea
				!ext.equalsIgnoreCase("gn")
					&& // Guadeloupe
				!ext.equalsIgnoreCase("gp")
					&& // Equatorial Guinea
				!ext.equalsIgnoreCase("gq")
					&& // Greece
				!ext.equalsIgnoreCase("gr")
					&& // South Georgia and the South Sandwich Islands
				!ext
					.equalsIgnoreCase(
						"gs")
					&& // Guatemala
				!ext.equalsIgnoreCase("gt")
					&& // Guam
				!ext.equalsIgnoreCase("gu")
					&& // Guinea-Bissau
				!ext.equalsIgnoreCase("gw")
					&& // Guyana
				!ext.equalsIgnoreCase("gy")
					&& // Hong Kong
				!ext.equalsIgnoreCase("hk")
					&& // Heard and McDonald Islands
				!ext.equalsIgnoreCase("hm")
					&& // Honduras
				!ext.equalsIgnoreCase("hn")
					&& // Croatia/Hrvatska
				!ext.equalsIgnoreCase("hr")
					&& // Haiti
				!ext.equalsIgnoreCase("ht")
					&& // Hungary
				!ext.equalsIgnoreCase("hu")
					&& // Indonesia
				!ext.equalsIgnoreCase("id")
					&& // Ireland
				!ext.equalsIgnoreCase("ie")
					&& // Israel
				!ext.equalsIgnoreCase("il")
					&& // Isle of Man
				!ext.equalsIgnoreCase("im")
					&& // India
				!ext.equalsIgnoreCase("in")
					&& // British Indian Ocean Territory
				!ext.equalsIgnoreCase(
						"io")
					&& // Iraq
				!ext.equalsIgnoreCase("iq")
					&& // Iran (Islamic Republic of)
				!ext.equalsIgnoreCase("ir")
					&& // Iceland
				!ext.equalsIgnoreCase("is")
					&& // Italy
				!ext.equalsIgnoreCase("it")
					&& // Jersey
				!ext.equalsIgnoreCase("je")
					&& // Jamaica
				!ext.equalsIgnoreCase("jm")
					&& // Jordan
				!ext.equalsIgnoreCase("jo")
					&& // Japan
				!ext.equalsIgnoreCase("jp")
					&& // Kenya
				!ext.equalsIgnoreCase("ke")
					&& // Kyrgyzstan
				!ext.equalsIgnoreCase("kg")
					&& // Cambodia
				!ext.equalsIgnoreCase("kh")
					&& // Kiribati
				!ext.equalsIgnoreCase("ki")
					&& // Comoros
				!ext.equalsIgnoreCase("km")
					&& // Saint Kitts and Nevis
				!ext.equalsIgnoreCase("kn")
					&& // Korea, Democratic People's Republic
				!ext.equalsIgnoreCase(
						"kp")
					&& // Korea, Republic of
				!ext.equalsIgnoreCase("kr")
					&& // Kuwait
				!ext.equalsIgnoreCase("kw")
					&& // Cayman Islands
				!ext.equalsIgnoreCase("ky")
					&& // Kazakhstan
				!ext.equalsIgnoreCase("kz")
					&& // Lao People's Democratic Republic
				!ext.equalsIgnoreCase(
						"la")
					&& // Lebanon
				!ext.equalsIgnoreCase("lb")
					&& // Saint Lucia
				!ext.equalsIgnoreCase("lc")
					&& // Liechtenstein
				!ext.equalsIgnoreCase("li")
					&& // Sri Lanka
				!ext.equalsIgnoreCase("lk")
					&& // Liberia
				!ext.equalsIgnoreCase("lr")
					&& // Lesotho
				!ext.equalsIgnoreCase("ls")
					&& // Lithuania
				!ext.equalsIgnoreCase("lt")
					&& // Luxembourg
				!ext.equalsIgnoreCase("lu")
					&& // Latvia
				!ext.equalsIgnoreCase("lv")
					&& // Libyan Arab Jamahiriya
				!ext.equalsIgnoreCase("ly")
					&& // Morocco
				!ext.equalsIgnoreCase("ma")
					&& // Monaco
				!ext.equalsIgnoreCase("mc")
					&& // Moldova, Republic of
				!ext.equalsIgnoreCase("md")
					&& // Madagascar
				!ext.equalsIgnoreCase("mg")
					&& // Marshall Islands
				!ext.equalsIgnoreCase("mh")
					&& // Macedonia, Former Yugoslav Republic
				!ext.equalsIgnoreCase(
						"mk")
					&& // Mali
				!ext.equalsIgnoreCase("ml")
					&& // Myanmar
				!ext.equalsIgnoreCase("mm")
					&& // Mongolia
				!ext.equalsIgnoreCase("mn")
					&& // Macau
				!ext.equalsIgnoreCase("mo")
					&& // Northern Mariana Islands
				!ext.equalsIgnoreCase("mp")
					&& // Martinique
				!ext.equalsIgnoreCase("mq")
					&& // Mauritania
				!ext.equalsIgnoreCase("mr")
					&& // Montserrat
				!ext.equalsIgnoreCase("ms")
					&& // Malta
				!ext.equalsIgnoreCase("mt")
					&& // Mauritius
				!ext.equalsIgnoreCase("mu")
					&& // Maldives
				!ext.equalsIgnoreCase("mv")
					&& // Malawi
				!ext.equalsIgnoreCase("mw")
					&& // Mexico
				!ext.equalsIgnoreCase("mx")
					&& // Malaysia
				!ext.equalsIgnoreCase("my")
					&& // Mozambique
				!ext.equalsIgnoreCase("mz")
					&& // Namibia
				!ext.equalsIgnoreCase("na")
					&& // New Caledonia
				!ext.equalsIgnoreCase("nc")
					&& // Niger
				!ext.equalsIgnoreCase("ne")
					&& // Norfolk Island
				!ext.equalsIgnoreCase("nf")
					&& // Nigeria
				!ext.equalsIgnoreCase("ng")
					&& // Nicaragua
				!ext.equalsIgnoreCase("ni")
					&& // Netherlands
				!ext.equalsIgnoreCase("nl")
					&& // Norway
				!ext.equalsIgnoreCase("no")
					&& // Nepal
				!ext.equalsIgnoreCase("np")
					&& // Nauru
				!ext.equalsIgnoreCase("nr")
					&& // Niue
				!ext.equalsIgnoreCase("nu")
					&& // New Zealand
				!ext.equalsIgnoreCase("nz")
					&& // Oman
				!ext.equalsIgnoreCase("om")
					&& // Panama
				!ext.equalsIgnoreCase("pa")
					&& // Peru
				!ext.equalsIgnoreCase("pe")
					&& // French Polynesia
				!ext.equalsIgnoreCase("pf")
					&& // Papua New Guinea
				!ext.equalsIgnoreCase("pg")
					&& // Philippines
				!ext.equalsIgnoreCase("ph")
					&& // Pakistan
				!ext.equalsIgnoreCase("pk")
					&& // Poland
				!ext.equalsIgnoreCase("pl")
					&& // St. Pierre and Miquelon
				!ext.equalsIgnoreCase("pm")
					&& // Pitcairn Island
				!ext.equalsIgnoreCase("pn")
					&& // Puerto Rico
				!ext.equalsIgnoreCase("pr")
					&& // Palestinian Territories
				!ext.equalsIgnoreCase("ps")
					&& // Portugal
				!ext.equalsIgnoreCase("pt")
					&& // Palau
				!ext.equalsIgnoreCase("pw")
					&& // Paraguay
				!ext.equalsIgnoreCase("py")
					&& // Qatar
				!ext.equalsIgnoreCase("qa")
					&& // Reunion Island
				!ext.equalsIgnoreCase("re")
					&& // Romania
				!ext.equalsIgnoreCase("ro")
					&& // Russian Federation
				!ext.equalsIgnoreCase("ru")
					&& // Rwanda
				!ext.equalsIgnoreCase("rw")
					&& // Saudi Arabia
				!ext.equalsIgnoreCase("sa")
					&& // Solomon Islands
				!ext.equalsIgnoreCase("sb")
					&& // Seychelles
				!ext.equalsIgnoreCase("sc")
					&& // Sudan
				!ext.equalsIgnoreCase("sd")
					&& // Sweden
				!ext.equalsIgnoreCase("se")
					&& // Singapore
				!ext.equalsIgnoreCase("sg")
					&& // St. Helena
				!ext.equalsIgnoreCase("sh")
					&& // Slovenia
				!ext.equalsIgnoreCase("si")
					&& // Svalbard and Jan Mayen Islands
				!ext.equalsIgnoreCase(
						"sj")
					&& // Slovak Republic
				!ext.equalsIgnoreCase("sk")
					&& // Sierra Leone
				!ext.equalsIgnoreCase("sl")
					&& // San Marino
				!ext.equalsIgnoreCase("sm")
					&& // Senegal
				!ext.equalsIgnoreCase("sn")
					&& // Somalia
				!ext.equalsIgnoreCase("so")
					&& // Suriname
				!ext.equalsIgnoreCase("sr")
					&& // Sao Tome and Principe
				!ext.equalsIgnoreCase("st")
					&& // El Salvador
				!ext.equalsIgnoreCase("sv")
					&& // Syrian Arab Republic
				!ext.equalsIgnoreCase("sy")
					&& // Swaziland
				!ext.equalsIgnoreCase("sz")
					&& // Turks and Caicos Islands
				!ext.equalsIgnoreCase("tc")
					&& // Chad
				!ext.equalsIgnoreCase("td")
					&& // French Southern Territories
				!ext.equalsIgnoreCase("tf")
					&& // Togo
				!ext.equalsIgnoreCase("tg")
					&& // Thailand
				!ext.equalsIgnoreCase("th")
					&& // Tajikistan
				!ext.equalsIgnoreCase("tj")
					&& // Tokelau
				!ext.equalsIgnoreCase("tk")
					&& // Turkmenistan
				!ext.equalsIgnoreCase("tm")
					&& // Tunisia
				!ext.equalsIgnoreCase("tn")
					&& // Tonga
				!ext.equalsIgnoreCase("to")
					&& // East Timor
				!ext.equalsIgnoreCase("tp")
					&& // Turkey
				!ext.equalsIgnoreCase("tr")
					&& // Trinidad and Tobago
				!ext.equalsIgnoreCase("tt")
					&& // Tuvalu
				!ext.equalsIgnoreCase("tv")
					&& // Taiwan
				!ext.equalsIgnoreCase("tw")
					&& // Tanzania
				!ext.equalsIgnoreCase("tz")
					&& // Ukraine
				!ext.equalsIgnoreCase("ua")
					&& // Uganda
				!ext.equalsIgnoreCase("ug")
					&& // United Kingdom
				!ext.equalsIgnoreCase("uk")
					&& // US Minor Outlying Islands
				!ext.equalsIgnoreCase("um")
					&& // United States
				!ext.equalsIgnoreCase("us")
					&& // Uruguay
				!ext.equalsIgnoreCase("uy")
					&& // Uzbekistan
				!ext.equalsIgnoreCase("uz")
					&& // Holy See (City Vatican State)
				!ext.equalsIgnoreCase(
						"va")
					&& // Saint Vincent and the Grenadines
				!ext.equalsIgnoreCase(
						"vc")
					&& // Venezuela
				!ext.equalsIgnoreCase("ve")
					&& // Virgin Islands (British)
				!ext.equalsIgnoreCase("vg")
					&& // Virgin Islands (USA)
				!ext.equalsIgnoreCase("vi")
					&& // Vietnam
				!ext.equalsIgnoreCase("vn")
					&& // Vanuatu
				!ext.equalsIgnoreCase("vu")
					&& // Wallis and Futuna Islands
				!ext.equalsIgnoreCase("wf")
					&& // Western Samoa
				!ext.equalsIgnoreCase("ws")
					&& // Yemen
				!ext.equalsIgnoreCase("ye")
					&& // Mayotte
				!ext.equalsIgnoreCase("yt")
					&& // Yugoslavia
				!ext.equalsIgnoreCase("yu")
					&& // South Africa
				!ext.equalsIgnoreCase("za")
					&& // Zambia
				!ext.equalsIgnoreCase("zm")
					&& // Zaire
				!ext.equalsIgnoreCase("zr")
					&& // Zimbabwe
				!ext.equalsIgnoreCase("zw")) {
					return false;
				}
			}
		}
		return true;
	} // end isValidEmail

	public static String trimLeft(String str) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		if (length == 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer(str);

		int index;

		for (index = 0;
			index < length && buffer.charAt(index) == ' ';
			index++) {
			;
		}

		if (index == length) {
			return "";
		}

		return buffer.substring(index);
	}

	public static String trimRight(String str) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		if (length == 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer(str);

		int index;
		for (index = length - 1;
			index >= 0 && buffer.charAt(index) == ' ';
			index--) {
			;
		}

		if (index < 0 && buffer.charAt(0) == ' ') {
			return "";
		}

		return buffer.substring(0, index + 1);
	}

	public static boolean strInArray(
		String[] strs,
		String str,
		boolean caseSensitive) {
		if (strs == null || str == null) {
			return false;
		}

		if (caseSensitive) {
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].equals(str)) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].equalsIgnoreCase(str)) {
					return true;
				}
			}
		}

		return false;
	}

	public static String[] split2(String str, String delimiter) {
		ArrayList array = new ArrayList();
		int index = 0;
		int begin = 0, end;

		if (isEmpty(str)) {
			return null;
		}

		while (true) {
			index = str.indexOf(delimiter, begin);

			if (index == begin) {
				if (index >= 0) {
					//					array.add("");
				}
				begin += delimiter.length();
			} else if (index > begin) {
				end = index;

				array.add(str.substring(begin, end));

				begin = index + delimiter.length();
			} else {
				if ((begin >= 0) && (begin < str.length())) {
					array.add(str.substring(begin));
				}

				break;
			}
		}

		if (str.endsWith(delimiter)) {
			//			array.add("");
		}
		if (array.size() > 0) {
			String[] result = new String[array.size()];

			array.toArray(result);

			return result;
		}

		return null;
	}

	public static byte base64Char(int n) {
		return (byte) BASE64_TABLE.charAt(n & 0x3f);
	}

	public static byte base64Value(int c) {
		return (byte) (BASE64_TABLE.indexOf(c) & 0x3f);
	}

	public static int base64EncodeSize(int length) {
		return length / 3 * 4 + (length % 3 != 0 ? 4 : 0);
	}

	public static int base64DecodeSize(int length) {
		if (length % 4 == 2) {
			return length / 4 * 3 + 1;
		} else if (length % 4 == 3) {
			return length / 4 * 3 + 2;
		} else {
			return length / 4 * 3;
		}
	}

	public static byte[] base64Encode(byte[] byteData) {
		int length = byteData.length;
		if (length == 0) {
			return null;
		}

		int i, j;
		byte c = 0, t = 0;
		byte[] bstr = new byte[base64EncodeSize(length)];
		for (i = j = 0; i < length; i++) {
			c = byteData[i];
			if (i % 3 == 0) {
				bstr[j++] = base64Char(c >> 2);
				t = (byte) ((c << 4) & 0x3f);
			} else if (i % 3 == 1) {
				bstr[j++] = base64Char(t | (c >> 4));
				t = (byte) ((c << 2) & 0x3f);
			} else {
				bstr[j++] = base64Char(t | (c >> 6));
				bstr[j++] = base64Char(c);
			}
		}

		if (i % 3 != 0) {
			bstr[j++] = base64Char(t);
			bstr[j++] = '=';
			if (i % 3 == 1) {
				bstr[j++] = '=';
			}
		}

		return bstr;
	}

	public static byte[] base64Decode(byte[] bstr) {
		int i, j;
		int length = bstr.length / 4 * 4;
		for (i = 0; i < length && bstr[i] != (byte) '='; i++) {
			;
		}
		if (i % 4 == 1) {
			return null;
		}
		length = i;
		int blength = base64DecodeSize(length);

		byte c;
		byte[] byteData = new byte[blength];
		for (i = j = 0; i < length; i++) {
			c = base64Value(bstr[i]);
			if (i % 4 == 0) {
				byteData[j] = (byte) (c << 2);
			} else if (i % 4 == 1) {
				byteData[j++] |= (c >> 4);
				if (j == blength) {
					break;
				}
				byteData[j] = (byte) (c << 4);
			} else if (i % 4 == 2) {
				byteData[j++] |= (c >> 2);
				if (j == blength) {
					break;
				}
				byteData[j] = (byte) (c << 6);
			} else {
				byteData[j++] |= c;
			}
		}

		return byteData;
	}


	public static final int O2S_OPT_BIN = 1;
	public static final int O2S_OPT_OCT = 2;
	public static final int O2S_OPT_HEX = 3;

	class _STRTOI {
		public int value = 0;
		public int position = 0;
		public char lastc = 0;
	}

	public static String o2s(
		char cs,
		char cm,
		int width,
		int prec,
		int option,
		Object obj) {
		StringBuffer buf = new StringBuffer();

		if (obj == null) {
			buf.append("(null)");
		} else if (obj instanceof Short || obj instanceof Integer) {
			int value;
			if (obj instanceof Short) {
				value = ((Short) obj).intValue();
			} else {
				value = ((Integer) obj).intValue();
			}
			if (option == O2S_OPT_BIN) {
				buf.append(Integer.toBinaryString(value));
			} else if (option == O2S_OPT_OCT) {
				buf.append(Integer.toOctalString(value));
			} else if (option == O2S_OPT_HEX) {
				buf.append(Integer.toHexString(value));
			} else {
				buf.append(Integer.toString(value));
			}
		} else if (obj instanceof Long) {
			long value = ((Long) obj).intValue();
			if (option == O2S_OPT_BIN) {
				buf.append(Long.toBinaryString(value));
			} else if (option == O2S_OPT_OCT) {
				buf.append(Long.toOctalString(value));
			} else if (option == O2S_OPT_HEX) {
				buf.append(Long.toHexString(value));
			} else {
				buf.append(Long.toString(value));
			}
		} else if (obj instanceof Float) {
			float value = ((Float) obj).floatValue();
			buf.append(Float.toString(value));
			if (prec >= 0) {
				int l1 = buf.length();
				int pos = -1;
				for (int i1 = 0; i1 < l1; i1++) {
					if (buf.charAt(i1) == '.') {
						pos = i1;
					}
				}
				if (pos > 0) {
					int l2 = l1 - pos - 1;
					if (l2 > prec) {
						String tmp;
						if (prec == 0) {
							tmp = buf.substring(0, pos + prec);
						} else {
							tmp = buf.substring(0, pos + 1 + prec);
						}
						buf = new StringBuffer(tmp);
					} else if (l2 < prec) {
						buf.append(strMake('0', prec - l2));
					}
				} else {
					buf.append(strMake('0', prec));
				}
			}
		} else if (obj instanceof Double) {
			double value = ((Double) obj).doubleValue();
			buf.append(Double.toString(value));
			if (prec >= 0) {
				int l1 = buf.length();
				int pos = -1;
				for (int i1 = 0; i1 < l1; i1++) {
					if (buf.charAt(i1) == '.') {
						pos = i1;
					}
				}
				if (pos > 0) {
					int l2 = l1 - pos - 1;
					if (l2 > prec) {
						String tmp;
						if (prec == 0) {
							tmp = buf.substring(0, pos + prec);
						} else {
							tmp = buf.substring(0, pos + 1 + prec);
						}
						buf = new StringBuffer(tmp);
					} else if (l2 < prec) {
						buf.append(strMake('0', prec - l2));
					}
				} else {
					buf.append(strMake('0', prec));
				}
			}
		} else {
			buf.append(obj.toString());

		}
		int length = buf.length();
		char c0 = 0;
		if (length > 0) {
			c0 = buf.charAt(0);
			if (c0 == '+' || c0 == '-' || c0 == '.') {
				buf.deleteCharAt(0);
			} else {
				c0 = 0;
			}
		}

		if (length < width) {
			if (cs == '-' || cs == 0) {
				buf.insert(0, strMake(cm, width - length));
			} else {
				buf.append(strMake(cm, width - length));
			}
		}

		if (c0 != 0) {
			buf.insert(0, c0);

		}
		return buf.toString();
	}

	protected static _STRTOI _strtoi(String str) {
		StringBuffer buf = new StringBuffer(str);

		_STRTOI r = new StringUtil().new _STRTOI();

		int length = buf.length();
		int i;
		for (i = 0; i < length; i++) {
			char c = buf.charAt(i);
			if (c < '0' || c > '9') {
				r.lastc = c;
				break;
			}
		}

		if (i > 0) {
			r.value = Integer.parseInt(str.substring(0, i));
			r.position = i;
		}

		return r;
	}

	public static String strMake(char c, int length) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < length; i++) {
			buf.append(c);

		}
		return buf.toString();
	}

	public static String strFormat(String str, Object[] params) {
		StringBuffer r = new StringBuffer();

		StringBuffer buf = new StringBuffer(str);

		int length = buf.length();
		int olength = params == null ? 0 : params.length;
		int i, j;
		char cs = 0, cm = 0;
		int width = -1, prec = -1, option = 0;
		boolean parse = false;
		_STRTOI rtmp;
		for (i = j = 0; i < length; i++) {
			if (j >= olength) {
				break;
			}

			char c = buf.charAt(i);
			if (c == '%') {
				i++;
				if (i < length) {
					char c1 = buf.charAt(i);
					if (c1 == c) {
						r = r.append(c);
						continue;
					} else {
						parse = true;
						cs = cm = 0;
						width = prec = -1;
						option = 0;
						c = c1;
					}
				} else {
					break;
				}
			}

			if (!parse) {
				r.append(c);
				continue;
			}

			if (cs == 0 && (c == '+' || c == '-')) {
				cs = c;
				continue;
			}

			if (cs != 0 && cm == 0) {
				cm = c;
				continue;
			}

			if (width < 0) {
				rtmp = _strtoi(buf.substring(i));
				width = rtmp.value;
				if (rtmp.lastc == '.') {
					i += rtmp.position;
				} else {
					i += rtmp.position - 1;
				}
				continue;
			}

			if (prec == -1) {
				rtmp = _strtoi(buf.substring(i));
				prec = rtmp.position == 0 ? -2 : rtmp.value;
				i += rtmp.position - 1;
				continue;
			}

			parse = false;
			boolean bo2s = true;
			boolean uc = false, lc = false;
			switch (c) {
				case 's' :
				case 'S' :
					break;
				case 'v' :
					lc = true;
					break;
				case 'V' :
					uc = true;
					break;
				case 'b' :
				case 'B' :
					option = O2S_OPT_BIN;
					break;
				case 'o' :
				case 'O' :
					option = O2S_OPT_OCT;
					break;
				case 'x' :
					lc = true;
					option = O2S_OPT_HEX;
					break;
				case 'X' :
					uc = true;
					option = O2S_OPT_HEX;
					break;
				default :
					}

			if (bo2s) {
				String tmp =
					o2s(
						cs,
						cm,
						width,
						prec < 0 ? -1 : prec,
						option,
						params[j++]);
				if (uc) {
					tmp = tmp.toUpperCase();
				} else if (lc) {
					tmp = tmp.toLowerCase();
				}
				r.append(tmp);
			}
		}

		if (i < length) {
			r = r.append(StringUtil.replace(buf.substring(i), "%%", "%", true));

		}
		return r.toString();
	}


	public static String replace(
		String src,
		String oldStr,
		String newStr,
		boolean isCaseSensitive,
		int index) {
		if (src == null
			|| src.length() == 0
			|| oldStr == null
			|| oldStr.length() == 0
			|| index <= 0) {
			return src;
		}

		if (newStr == null) {
			newStr = "";

		}
		String s = isCaseSensitive ? src : src.toLowerCase();
		String o = isCaseSensitive ? oldStr : oldStr.toLowerCase();
		StringBuffer buffer = new StringBuffer(src);
		int length = o.length();

		int pos = s.indexOf(o, 0);
		while (pos >= 0) {
			index--;
			if (index == 0) {
				break;
			}
			pos = s.indexOf(o, pos + length);
		}
		if (pos >= 0 && index == 0) {
			buffer.replace(pos, pos + length, newStr);

		}
		return buffer.toString();
	}


	public static String trimall(String str) {
		return str.trim();
		/*
		String sStr = null;
		sStr = trimLeft(str);
		return trimRight(sStr);
		*/
	}
	

	public static String join(String[] list, String add) {
		StringBuffer sb = new StringBuffer();
		int len = list.length;
		for (int i = 0; i < len; i++) {
			sb.append(list[i]);
			if (i != (len - 1))sb.append(add);
		}
		return sb.toString();
	}
}
