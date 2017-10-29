// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

    package mc.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Comment=1, BOOLIT=2, BOOLEAN=3, BREAK=4, CONTINUE=5, ELSE=6, FOR=7, FLOAT=8, 
		IF=9, INT=10, RETURN=11, VOID=12, DO=13, WHILE=14, TRUE=15, FALSE=16, 
		STRING=17, FLOATLIT=18, INTLIT=19, STRINGLIT=20, ID=21, ADD=22, MUL=23, 
		LNOT=24, LOR=25, NOTEQ=26, LESS=27, LESSEQ=28, ASSIGN=29, SUB=30, DIV=31, 
		MOD=32, LAND=33, EQ=34, GREAT=35, GREATEQ=36, COMMA=37, SEMI=38, LSB=39, 
		RSB=40, LB=41, RB=42, LP=43, RP=44, WS=45, UNCLOSE_STRING=46, ILLEGAL_ESCAPE=47, 
		ERROR_CHAR=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"Comment", "BLOCK_COMMENT", "LINE_COMMENT", "BOOLIT", "BOOLEAN", "BREAK", 
		"CONTINUE", "ELSE", "FOR", "FLOAT", "IF", "INT", "RETURN", "VOID", "DO", 
		"WHILE", "TRUE", "FALSE", "STRING", "FLOATLIT", "Floatnumbers", "ExponentPart", 
		"StartDigit", "Digit", "ZeroDigit", "INTLIT", "STRINGLIT", "StringCharacters", 
		"ESC", "ID", "NameStartChar", "NameChar", "ADD", "MUL", "LNOT", "LOR", 
		"NOTEQ", "LESS", "LESSEQ", "ASSIGN", "SUB", "DIV", "MOD", "LAND", "EQ", 
		"GREAT", "GREATEQ", "COMMA", "SEMI", "LSB", "RSB", "LB", "RB", "LP", "RP", 
		"WS", "UNCLOSE_STRING", "ILLEGAL_ESCAPE", "ERROR_CHAR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'boolean'", "'break'", "'continue'", "'else'", "'for'", 
		"'float'", "'if'", "'int'", "'return'", "'void'", "'do'", "'while'", "'true'", 
		"'false'", "'string'", null, null, null, null, "'+'", "'*'", "'!'", "'||'", 
		"'!='", "'<'", "'<='", "'='", "'-'", "'/'", "'%'", "'&&'", "'=='", "'>'", 
		"'>='", "','", "';'", "'['", "']'", "'('", "')'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Comment", "BOOLIT", "BOOLEAN", "BREAK", "CONTINUE", "ELSE", "FOR", 
		"FLOAT", "IF", "INT", "RETURN", "VOID", "DO", "WHILE", "TRUE", "FALSE", 
		"STRING", "FLOATLIT", "INTLIT", "STRINGLIT", "ID", "ADD", "MUL", "LNOT", 
		"LOR", "NOTEQ", "LESS", "LESSEQ", "ASSIGN", "SUB", "DIV", "MOD", "LAND", 
		"EQ", "GREAT", "GREATEQ", "COMMA", "SEMI", "LSB", "RSB", "LB", "RB", "LP", 
		"RP", "WS", "UNCLOSE_STRING", "ILLEGAL_ESCAPE", "ERROR_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	@Override
	public Token emit() {
	    switch (getType()) {
	    case UNCLOSE_STRING:
	        Token result = super.emit();
	        // you'll need to define this method
	        throw new UncloseString(result.getText());

	    case ILLEGAL_ESCAPE:
	        result = super.emit();
	        throw new IllegalEscape(result.getText());

	    case ERROR_CHAR:
	        result = super.emit();
	        throw new ErrorToken(result.getText());

	    default:
	        return super.emit();
	    }
	}


	public MCLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 26:
			STRINGLIT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRINGLIT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			setText(getText().substring(1, getText().length()-1));
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u01b8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\3\2"+
		"\3\2\5\2|\n\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u0084\n\3\f\3\16\3\u0087\13"+
		"\3\3\3\3\3\3\3\5\3\u008c\n\3\3\4\3\4\3\4\3\4\7\4\u0092\n\4\f\4\16\4\u0095"+
		"\13\4\3\5\3\5\5\5\u0099\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\5\25\u00f2\n\25\3\25\5\25\u00f5\n\25\3\25\3\25\3\25\5\25\u00fa\n"+
		"\25\3\25\3\25\3\25\5\25\u00ff\n\25\3\26\6\26\u0102\n\26\r\26\16\26\u0103"+
		"\3\27\3\27\5\27\u0108\n\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\6\33\u0113\n\33\r\33\16\33\u0114\3\34\3\34\7\34\u0119\n\34\f\34\16\34"+
		"\u011c\13\34\3\34\3\34\3\34\3\35\3\35\5\35\u0123\n\35\3\36\3\36\3\36\3"+
		"\37\3\37\7\37\u012a\n\37\f\37\16\37\u012d\13\37\3 \5 \u0130\n \3!\3!\5"+
		"!\u0134\n!\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3(\3)\3"+
		")\3*\3*\3+\3+\3,\3,\3-\3-\3-\3.\3.\3.\3/\3/\3\60\3\60\3\60\3\61\3\61\3"+
		"\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\6"+
		"9\u016b\n9\r9\169\u016c\39\39\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3:\3"+
		":\3:\3:\7:\u0181\n:\f:\16:\u0184\13:\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;"+
		"\3;\3;\3;\3;\3;\7;\u0196\n;\f;\16;\u0199\13;\3;\3;\3;\3;\3;\3;\3;\3;\3"+
		";\3;\3;\3;\3;\3;\3;\3;\3;\7;\u01ac\n;\f;\16;\u01af\13;\6;\u01b1\n;\r;"+
		"\16;\u01b2\3;\3;\3<\3<\3\u0085\2=\3\3\5\2\7\2\t\4\13\5\r\6\17\7\21\b\23"+
		"\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24+\2-\2/\2\61\2"+
		"\63\2\65\25\67\269\2;\2=\27?\2A\2C\30E\31G\32I\33K\34M\35O\36Q\37S U!"+
		"W\"Y#[$]%_&a\'c(e)g*i+k,m-o.q/s\60u\61w\62\3\2\f\4\2\f\f\17\17\4\2GGg"+
		"g\4\2--//\3\2\63;\3\2\62;\6\2\f\f\17\17$$^^\n\2$$))^^ddhhppttvv\5\2C\\"+
		"aac|\5\2\13\f\17\17\"\"\t\2$$^^ddhhppttvv\u01d7\2\3\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\2=\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3"+
		"\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2"+
		"\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\3{\3\2\2\2\5\177\3\2\2"+
		"\2\7\u008d\3\2\2\2\t\u0098\3\2\2\2\13\u009a\3\2\2\2\r\u00a2\3\2\2\2\17"+
		"\u00a8\3\2\2\2\21\u00b1\3\2\2\2\23\u00b6\3\2\2\2\25\u00ba\3\2\2\2\27\u00c0"+
		"\3\2\2\2\31\u00c3\3\2\2\2\33\u00c7\3\2\2\2\35\u00ce\3\2\2\2\37\u00d3\3"+
		"\2\2\2!\u00d6\3\2\2\2#\u00dc\3\2\2\2%\u00e1\3\2\2\2\'\u00e7\3\2\2\2)\u00fe"+
		"\3\2\2\2+\u0101\3\2\2\2-\u0105\3\2\2\2/\u010b\3\2\2\2\61\u010d\3\2\2\2"+
		"\63\u010f\3\2\2\2\65\u0112\3\2\2\2\67\u0116\3\2\2\29\u0122\3\2\2\2;\u0124"+
		"\3\2\2\2=\u0127\3\2\2\2?\u012f\3\2\2\2A\u0133\3\2\2\2C\u0135\3\2\2\2E"+
		"\u0137\3\2\2\2G\u0139\3\2\2\2I\u013b\3\2\2\2K\u013e\3\2\2\2M\u0141\3\2"+
		"\2\2O\u0143\3\2\2\2Q\u0146\3\2\2\2S\u0148\3\2\2\2U\u014a\3\2\2\2W\u014c"+
		"\3\2\2\2Y\u014e\3\2\2\2[\u0151\3\2\2\2]\u0154\3\2\2\2_\u0156\3\2\2\2a"+
		"\u0159\3\2\2\2c\u015b\3\2\2\2e\u015d\3\2\2\2g\u015f\3\2\2\2i\u0161\3\2"+
		"\2\2k\u0163\3\2\2\2m\u0165\3\2\2\2o\u0167\3\2\2\2q\u016a\3\2\2\2s\u0170"+
		"\3\2\2\2u\u0185\3\2\2\2w\u01b6\3\2\2\2y|\5\5\3\2z|\5\7\4\2{y\3\2\2\2{"+
		"z\3\2\2\2|}\3\2\2\2}~\b\2\2\2~\4\3\2\2\2\177\u0080\7\61\2\2\u0080\u0081"+
		"\7,\2\2\u0081\u0085\3\2\2\2\u0082\u0084\13\2\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u008b\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0088\u0089\7,\2\2\u0089\u008c\7\61\2\2\u008a"+
		"\u008c\7\2\2\3\u008b\u0088\3\2\2\2\u008b\u008a\3\2\2\2\u008c\6\3\2\2\2"+
		"\u008d\u008e\7\61\2\2\u008e\u008f\7\61\2\2\u008f\u0093\3\2\2\2\u0090\u0092"+
		"\n\2\2\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\b\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0099\5#\22\2"+
		"\u0097\u0099\5%\23\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099\n\3"+
		"\2\2\2\u009a\u009b\7d\2\2\u009b\u009c\7q\2\2\u009c\u009d\7q\2\2\u009d"+
		"\u009e\7n\2\2\u009e\u009f\7g\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7p\2\2"+
		"\u00a1\f\3\2\2\2\u00a2\u00a3\7d\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7g"+
		"\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7m\2\2\u00a7\16\3\2\2\2\u00a8\u00a9"+
		"\7e\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7v\2\2\u00ac"+
		"\u00ad\7k\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af\7w\2\2\u00af\u00b0\7g\2\2"+
		"\u00b0\20\3\2\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7"+
		"u\2\2\u00b4\u00b5\7g\2\2\u00b5\22\3\2\2\2\u00b6\u00b7\7h\2\2\u00b7\u00b8"+
		"\7q\2\2\u00b8\u00b9\7t\2\2\u00b9\24\3\2\2\2\u00ba\u00bb\7h\2\2\u00bb\u00bc"+
		"\7n\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7v\2\2\u00bf"+
		"\26\3\2\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7h\2\2\u00c2\30\3\2\2\2\u00c3"+
		"\u00c4\7k\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7v\2\2\u00c6\32\3\2\2\2\u00c7"+
		"\u00c8\7t\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7w\2\2"+
		"\u00cb\u00cc\7t\2\2\u00cc\u00cd\7p\2\2\u00cd\34\3\2\2\2\u00ce\u00cf\7"+
		"x\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1\7k\2\2\u00d1\u00d2\7f\2\2\u00d2\36"+
		"\3\2\2\2\u00d3\u00d4\7f\2\2\u00d4\u00d5\7q\2\2\u00d5 \3\2\2\2\u00d6\u00d7"+
		"\7y\2\2\u00d7\u00d8\7j\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7n\2\2\u00da"+
		"\u00db\7g\2\2\u00db\"\3\2\2\2\u00dc\u00dd\7v\2\2\u00dd\u00de\7t\2\2\u00de"+
		"\u00df\7w\2\2\u00df\u00e0\7g\2\2\u00e0$\3\2\2\2\u00e1\u00e2\7h\2\2\u00e2"+
		"\u00e3\7c\2\2\u00e3\u00e4\7n\2\2\u00e4\u00e5\7u\2\2\u00e5\u00e6\7g\2\2"+
		"\u00e6&\3\2\2\2\u00e7\u00e8\7u\2\2\u00e8\u00e9\7v\2\2\u00e9\u00ea\7t\2"+
		"\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7i\2\2\u00ed(\3\2"+
		"\2\2\u00ee\u00ef\5+\26\2\u00ef\u00f1\7\60\2\2\u00f0\u00f2\5+\26\2\u00f1"+
		"\u00f0\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\5-"+
		"\27\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00ff\3\2\2\2\u00f6"+
		"\u00f7\7\60\2\2\u00f7\u00f9\5+\26\2\u00f8\u00fa\5-\27\2\u00f9\u00f8\3"+
		"\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00ff\3\2\2\2\u00fb\u00fc\5+\26\2\u00fc"+
		"\u00fd\5-\27\2\u00fd\u00ff\3\2\2\2\u00fe\u00ee\3\2\2\2\u00fe\u00f6\3\2"+
		"\2\2\u00fe\u00fb\3\2\2\2\u00ff*\3\2\2\2\u0100\u0102\5\61\31\2\u0101\u0100"+
		"\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		",\3\2\2\2\u0105\u0107\t\3\2\2\u0106\u0108\t\4\2\2\u0107\u0106\3\2\2\2"+
		"\u0107\u0108\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\5+\26\2\u010a.\3"+
		"\2\2\2\u010b\u010c\t\5\2\2\u010c\60\3\2\2\2\u010d\u010e\t\6\2\2\u010e"+
		"\62\3\2\2\2\u010f\u0110\7\62\2\2\u0110\64\3\2\2\2\u0111\u0113\5\61\31"+
		"\2\u0112\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115"+
		"\3\2\2\2\u0115\66\3\2\2\2\u0116\u011a\7$\2\2\u0117\u0119\59\35\2\u0118"+
		"\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2"+
		"\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e\7$\2\2\u011e"+
		"\u011f\b\34\3\2\u011f8\3\2\2\2\u0120\u0123\n\7\2\2\u0121\u0123\5;\36\2"+
		"\u0122\u0120\3\2\2\2\u0122\u0121\3\2\2\2\u0123:\3\2\2\2\u0124\u0125\7"+
		"^\2\2\u0125\u0126\t\b\2\2\u0126<\3\2\2\2\u0127\u012b\5? \2\u0128\u012a"+
		"\5A!\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012c>\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\t\t\2\2"+
		"\u012f\u012e\3\2\2\2\u0130@\3\2\2\2\u0131\u0134\5? \2\u0132\u0134\t\6"+
		"\2\2\u0133\u0131\3\2\2\2\u0133\u0132\3\2\2\2\u0134B\3\2\2\2\u0135\u0136"+
		"\7-\2\2\u0136D\3\2\2\2\u0137\u0138\7,\2\2\u0138F\3\2\2\2\u0139\u013a\7"+
		"#\2\2\u013aH\3\2\2\2\u013b\u013c\7~\2\2\u013c\u013d\7~\2\2\u013dJ\3\2"+
		"\2\2\u013e\u013f\7#\2\2\u013f\u0140\7?\2\2\u0140L\3\2\2\2\u0141\u0142"+
		"\7>\2\2\u0142N\3\2\2\2\u0143\u0144\7>\2\2\u0144\u0145\7?\2\2\u0145P\3"+
		"\2\2\2\u0146\u0147\7?\2\2\u0147R\3\2\2\2\u0148\u0149\7/\2\2\u0149T\3\2"+
		"\2\2\u014a\u014b\7\61\2\2\u014bV\3\2\2\2\u014c\u014d\7\'\2\2\u014dX\3"+
		"\2\2\2\u014e\u014f\7(\2\2\u014f\u0150\7(\2\2\u0150Z\3\2\2\2\u0151\u0152"+
		"\7?\2\2\u0152\u0153\7?\2\2\u0153\\\3\2\2\2\u0154\u0155\7@\2\2\u0155^\3"+
		"\2\2\2\u0156\u0157\7@\2\2\u0157\u0158\7?\2\2\u0158`\3\2\2\2\u0159\u015a"+
		"\7.\2\2\u015ab\3\2\2\2\u015b\u015c\7=\2\2\u015cd\3\2\2\2\u015d\u015e\7"+
		"]\2\2\u015ef\3\2\2\2\u015f\u0160\7_\2\2\u0160h\3\2\2\2\u0161\u0162\7*"+
		"\2\2\u0162j\3\2\2\2\u0163\u0164\7+\2\2\u0164l\3\2\2\2\u0165\u0166\7}\2"+
		"\2\u0166n\3\2\2\2\u0167\u0168\7\177\2\2\u0168p\3\2\2\2\u0169\u016b\t\n"+
		"\2\2\u016a\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016a\3\2\2\2\u016c"+
		"\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\b9\2\2\u016fr\3\2\2\2\u0170"+
		"\u0182\7$\2\2\u0171\u0181\n\7\2\2\u0172\u0173\7^\2\2\u0173\u0181\7d\2"+
		"\2\u0174\u0175\7^\2\2\u0175\u0181\7h\2\2\u0176\u0177\7^\2\2\u0177\u0181"+
		"\7t\2\2\u0178\u0179\7^\2\2\u0179\u0181\7p\2\2\u017a\u017b\7^\2\2\u017b"+
		"\u0181\7v\2\2\u017c\u017d\7^\2\2\u017d\u0181\7$\2\2\u017e\u017f\7^\2\2"+
		"\u017f\u0181\7^\2\2\u0180\u0171\3\2\2\2\u0180\u0172\3\2\2\2\u0180\u0174"+
		"\3\2\2\2\u0180\u0176\3\2\2\2\u0180\u0178\3\2\2\2\u0180\u017a\3\2\2\2\u0180"+
		"\u017c\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0184\3\2\2\2\u0182\u0180\3\2"+
		"\2\2\u0182\u0183\3\2\2\2\u0183t\3\2\2\2\u0184\u0182\3\2\2\2\u0185\u01b0"+
		"\7$\2\2\u0186\u0196\n\7\2\2\u0187\u0188\7^\2\2\u0188\u0196\7d\2\2\u0189"+
		"\u018a\7^\2\2\u018a\u0196\7h\2\2\u018b\u018c\7^\2\2\u018c\u0196\7t\2\2"+
		"\u018d\u018e\7^\2\2\u018e\u0196\7p\2\2\u018f\u0190\7^\2\2\u0190\u0196"+
		"\7v\2\2\u0191\u0192\7^\2\2\u0192\u0196\7$\2\2\u0193\u0194\7^\2\2\u0194"+
		"\u0196\7^\2\2\u0195\u0186\3\2\2\2\u0195\u0187\3\2\2\2\u0195\u0189\3\2"+
		"\2\2\u0195\u018b\3\2\2\2\u0195\u018d\3\2\2\2\u0195\u018f\3\2\2\2\u0195"+
		"\u0191\3\2\2\2\u0195\u0193\3\2\2\2\u0196\u0199\3\2\2\2\u0197\u0195\3\2"+
		"\2\2\u0197\u0198\3\2\2\2\u0198\u019a\3\2\2\2\u0199\u0197\3\2\2\2\u019a"+
		"\u019b\7^\2\2\u019b\u01ad\n\13\2\2\u019c\u01ac\n\7\2\2\u019d\u019e\7^"+
		"\2\2\u019e\u01ac\7d\2\2\u019f\u01a0\7^\2\2\u01a0\u01ac\7h\2\2\u01a1\u01a2"+
		"\7^\2\2\u01a2\u01ac\7t\2\2\u01a3\u01a4\7^\2\2\u01a4\u01ac\7p\2\2\u01a5"+
		"\u01a6\7^\2\2\u01a6\u01ac\7v\2\2\u01a7\u01a8\7^\2\2\u01a8\u01ac\7$\2\2"+
		"\u01a9\u01aa\7^\2\2\u01aa\u01ac\7^\2\2\u01ab\u019c\3\2\2\2\u01ab\u019d"+
		"\3\2\2\2\u01ab\u019f\3\2\2\2\u01ab\u01a1\3\2\2\2\u01ab\u01a3\3\2\2\2\u01ab"+
		"\u01a5\3\2\2\2\u01ab\u01a7\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ac\u01af\3\2"+
		"\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af"+
		"\u01ad\3\2\2\2\u01b0\u0197\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b0\3\2"+
		"\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b5\7$\2\2\u01b5"+
		"v\3\2\2\2\u01b6\u01b7\13\2\2\2\u01b7x\3\2\2\2\34\2{\u0085\u008b\u0093"+
		"\u0098\u00f1\u00f4\u00f9\u00fe\u0103\u0107\u0114\u011a\u0122\u012b\u012f"+
		"\u0133\u016c\u0180\u0182\u0195\u0197\u01ab\u01ad\u01b2\4\b\2\2\3\34\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}