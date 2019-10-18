package com.phuocnguyen.LexicalAnalyzer.Version003;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TokenLibraries {
	/* keywords */
	ANDNUMBER("and"), ARRAYNUMBER("array"), BEGINNUMBER("begin"), CONSTNUMBER("constant"), DIVNUMBER("div"),
	DOWNTONUMBER("downto"), ELSENUMBER("else"), ELSIFNUMBER("elsif"), ENDNUMBER("end"), ENDIFNUMBER("endif"),
	ENDLOOPNUMBER("endloop"), ENDRECNUMBER("endrec"), EXITNUMBER("exit"), FORNUMBER("for"), FORWARDNUMBER("forward"),
	FUNCTIONNUMBER("function"), IFNUMBER("if"), ISNUMBER("is"), LOOPNUMBER("loop"), NOTnumber("not"), OFNUMBER("of"),
	ORNUMBER("or"), PROCEDURENUMBER("procedure"), PROGRAMNUMBER("program"), RECORDnumber("record"),
	REPEATNUMBER("repeat"), RETURNNUMBER("return"), THENNUMBER("then"), TONUMBER("to"), TYPENUMBER("type"),
	UNTILNUMBER("until"), VARNUMBER("var"), WHILENUMBER("while"), LETNUMBER("let"), DONUMBER("do"),
	/* special words */
	COLEQNUMBER(":="), SEMINUMBER(";"), COMMMANUMBER(","), OPEN_BRACKET("\\{"), CLOSE_BRACKET("\\}"),
	LPARENNUMBER("\\("), RPARENNUMBER("\\)"), EQNUMBER("=="), NENUMBER("<>"), GENUMBER(">="), LENUMBER("<="),
	ATTRIBUTIONNUMBER("="), GREATERNUMBER(">"), LESSERNUMBER("<"), PLUSNUMBER("\\+"), MINUSNUMBER("-"),
	TIMESNUMBER("\\*"), DIVISIONNUMBER("/"), MODULUSNUMBER("%"), COLONNUMBER(":"), DOTNUMBER("\\."),

	/* data type */
	SCONSTNUMBER("\"[^\"]+\""), ICONSTNUMBER("\\d+(\\.\\d+)?"), IDNUMBER("\\w+{20}"), CCONSTNUMBER("\\d{1}");
	/* comments */

	private final Pattern pattern;

	TokenLibraries(String regex) {
		pattern = Pattern.compile("^" + regex);
	}

	int endOfMatch(String matches) {
		Matcher matcher = pattern.matcher(matches);
		if (matcher.find()) {
			return matcher.end();
		}
		return -1;
	}
}
// all methods, variables in 'enum' is private
