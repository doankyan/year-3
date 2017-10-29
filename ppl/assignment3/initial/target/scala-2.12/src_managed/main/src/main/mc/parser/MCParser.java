// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

    package mc.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MCParser extends Parser {
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
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_listID = 2, RULE_termID = 3, RULE_funcDecl = 4, 
		RULE_listPara = 5, RULE_non_listPara = 6, RULE_paraDecl = 7, RULE_paraDecl1 = 8, 
		RULE_paraDecl2 = 9, RULE_blockStmt = 10, RULE_listCmd = 11, RULE_listVarDecl = 12, 
		RULE_listStmt = 13, RULE_stmt = 14, RULE_ifStmt = 15, RULE_dowhileStmt = 16, 
		RULE_forStmt = 17, RULE_breakStmt = 18, RULE_callStmt = 19, RULE_continueStmt = 20, 
		RULE_returnStmt = 21, RULE_expStmt = 22, RULE_varDecl = 23, RULE_mctype = 24, 
		RULE_primitiveType = 25, RULE_voidType = 26, RULE_arrayPointerType = 27, 
		RULE_listExp = 28, RULE_expList = 29, RULE_exp = 30, RULE_term1 = 31, 
		RULE_term2 = 32, RULE_term3 = 33, RULE_term4 = 34, RULE_term5 = 35, RULE_term6 = 36, 
		RULE_term7 = 37, RULE_term8 = 38, RULE_term9 = 39, RULE_funccall = 40;
	public static final String[] ruleNames = {
		"program", "decl", "listID", "termID", "funcDecl", "listPara", "non_listPara", 
		"paraDecl", "paraDecl1", "paraDecl2", "blockStmt", "listCmd", "listVarDecl", 
		"listStmt", "stmt", "ifStmt", "dowhileStmt", "forStmt", "breakStmt", "callStmt", 
		"continueStmt", "returnStmt", "expStmt", "varDecl", "mctype", "primitiveType", 
		"voidType", "arrayPointerType", "listExp", "expList", "exp", "term1", 
		"term2", "term3", "term4", "term5", "term6", "term7", "term8", "term9", 
		"funccall"
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
	public String getGrammarFileName() { return "MC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MCParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				decl();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INT) | (1L << VOID) | (1L << STRING))) != 0) );
			setState(87);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public FuncDeclContext funcDecl() {
			return getRuleContext(FuncDeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				funcDecl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListIDContext extends ParserRuleContext {
		public List<TermIDContext> termID() {
			return getRuleContexts(TermIDContext.class);
		}
		public TermIDContext termID(int i) {
			return getRuleContext(TermIDContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MCParser.COMMA, i);
		}
		public ListIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listID; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListIDContext listID() throws RecognitionException {
		ListIDContext _localctx = new ListIDContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_listID);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			termID();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(94);
				match(COMMA);
				setState(95);
				termID();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermIDContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode INTLIT() { return getToken(MCParser.INTLIT, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public TermIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termID; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTermID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermIDContext termID() throws RecognitionException {
		TermIDContext _localctx = new TermIDContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_termID);
		try {
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				match(ID);
				setState(103);
				match(LSB);
				setState(104);
				match(INTLIT);
				setState(105);
				match(RSB);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclContext extends ParserRuleContext {
		public MctypeContext mctype() {
			return getRuleContext(MctypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ListParaContext listPara() {
			return getRuleContext(ListParaContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public BlockStmtContext blockStmt() {
			return getRuleContext(BlockStmtContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funcDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			mctype();
			setState(109);
			match(ID);
			setState(110);
			match(LB);
			setState(111);
			listPara();
			setState(112);
			match(RB);
			setState(113);
			blockStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListParaContext extends ParserRuleContext {
		public Non_listParaContext non_listPara() {
			return getRuleContext(Non_listParaContext.class,0);
		}
		public ListParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listPara; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListParaContext listPara() throws RecognitionException {
		ListParaContext _localctx = new ListParaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_listPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INT) | (1L << STRING))) != 0)) {
				{
				setState(115);
				non_listPara();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_listParaContext extends ParserRuleContext {
		public List<ParaDeclContext> paraDecl() {
			return getRuleContexts(ParaDeclContext.class);
		}
		public ParaDeclContext paraDecl(int i) {
			return getRuleContext(ParaDeclContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MCParser.COMMA, i);
		}
		public Non_listParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_listPara; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitNon_listPara(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_listParaContext non_listPara() throws RecognitionException {
		Non_listParaContext _localctx = new Non_listParaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_non_listPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			paraDecl();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(119);
				match(COMMA);
				setState(120);
				paraDecl();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDeclContext extends ParserRuleContext {
		public ParaDecl1Context paraDecl1() {
			return getRuleContext(ParaDecl1Context.class,0);
		}
		public ParaDecl2Context paraDecl2() {
			return getRuleContext(ParaDecl2Context.class,0);
		}
		public ParaDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitParaDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaDeclContext paraDecl() throws RecognitionException {
		ParaDeclContext _localctx = new ParaDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paraDecl);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				paraDecl1();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				paraDecl2();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDecl1Context extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public ParaDecl1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecl1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitParaDecl1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaDecl1Context paraDecl1() throws RecognitionException {
		ParaDecl1Context _localctx = new ParaDecl1Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_paraDecl1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			primitiveType();
			setState(131);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDecl2Context extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public ParaDecl2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecl2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitParaDecl2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParaDecl2Context paraDecl2() throws RecognitionException {
		ParaDecl2Context _localctx = new ParaDecl2Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_paraDecl2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			primitiveType();
			setState(134);
			match(ID);
			setState(135);
			match(LSB);
			setState(136);
			match(RSB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStmtContext extends ParserRuleContext {
		public TerminalNode LP() { return getToken(MCParser.LP, 0); }
		public ListCmdContext listCmd() {
			return getRuleContext(ListCmdContext.class,0);
		}
		public TerminalNode RP() { return getToken(MCParser.RP, 0); }
		public BlockStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitBlockStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStmtContext blockStmt() throws RecognitionException {
		BlockStmtContext _localctx = new BlockStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_blockStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(LP);
			setState(139);
			listCmd();
			setState(140);
			match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListCmdContext extends ParserRuleContext {
		public ListVarDeclContext listVarDecl() {
			return getRuleContext(ListVarDeclContext.class,0);
		}
		public ListStmtContext listStmt() {
			return getRuleContext(ListStmtContext.class,0);
		}
		public ListCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListCmdContext listCmd() throws RecognitionException {
		ListCmdContext _localctx = new ListCmdContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_listCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			listVarDecl();
			setState(143);
			listStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListVarDeclContext extends ParserRuleContext {
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public ListVarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listVarDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListVarDeclContext listVarDecl() throws RecognitionException {
		ListVarDeclContext _localctx = new ListVarDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_listVarDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INT) | (1L << STRING))) != 0)) {
				{
				{
				setState(145);
				varDecl();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListStmtContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ListStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListStmtContext listStmt() throws RecognitionException {
		ListStmtContext _localctx = new ListStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLIT) | (1L << BREAK) | (1L << CONTINUE) | (1L << FOR) | (1L << IF) | (1L << RETURN) | (1L << DO) | (1L << FLOATLIT) | (1L << INTLIT) | (1L << STRINGLIT) | (1L << ID) | (1L << LNOT) | (1L << SUB) | (1L << LB) | (1L << LP))) != 0)) {
				{
				{
				setState(151);
				stmt();
				}
				}
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public CallStmtContext callStmt() {
			return getRuleContext(CallStmtContext.class,0);
		}
		public DowhileStmtContext dowhileStmt() {
			return getRuleContext(DowhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public ExpStmtContext expStmt() {
			return getRuleContext(ExpStmtContext.class,0);
		}
		public BlockStmtContext blockStmt() {
			return getRuleContext(BlockStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stmt);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				ifStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				callStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(159);
				dowhileStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(160);
				forStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(161);
				breakStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(162);
				continueStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(163);
				returnStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(164);
				expStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(165);
				blockStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MCParser.IF, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MCParser.ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(IF);
			setState(169);
			match(LB);
			setState(170);
			exp();
			setState(171);
			match(RB);
			setState(172);
			stmt();
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(173);
				match(ELSE);
				setState(174);
				stmt();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DowhileStmtContext extends ParserRuleContext {
		public TerminalNode DO() { return getToken(MCParser.DO, 0); }
		public ListStmtContext listStmt() {
			return getRuleContext(ListStmtContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(MCParser.WHILE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public DowhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dowhileStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitDowhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DowhileStmtContext dowhileStmt() throws RecognitionException {
		DowhileStmtContext _localctx = new DowhileStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dowhileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(DO);
			setState(178);
			listStmt();
			setState(179);
			match(WHILE);
			setState(180);
			exp();
			setState(181);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MCParser.FOR, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(MCParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(MCParser.SEMI, i);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(FOR);
			setState(184);
			match(LB);
			setState(185);
			exp();
			setState(186);
			match(SEMI);
			setState(187);
			exp();
			setState(188);
			match(SEMI);
			setState(189);
			exp();
			setState(190);
			match(RB);
			setState(191);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MCParser.BREAK, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(BREAK);
			setState(194);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallStmtContext extends ParserRuleContext {
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public CallStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitCallStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallStmtContext callStmt() throws RecognitionException {
		CallStmtContext _localctx = new CallStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_callStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			funccall();
			setState(197);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(MCParser.CONTINUE, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(CONTINUE);
			setState(200);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MCParser.RETURN, 0); }
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(RETURN);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLIT) | (1L << FLOATLIT) | (1L << INTLIT) | (1L << STRINGLIT) | (1L << ID) | (1L << LNOT) | (1L << SUB) | (1L << LB))) != 0)) {
				{
				setState(203);
				exp();
				}
			}

			setState(206);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpStmtContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public ExpStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitExpStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpStmtContext expStmt() throws RecognitionException {
		ExpStmtContext _localctx = new ExpStmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			exp();
			setState(209);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ListIDContext listID() {
			return getRuleContext(ListIDContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MCParser.SEMI, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			primitiveType();
			setState(212);
			listID();
			setState(213);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MctypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public VoidTypeContext voidType() {
			return getRuleContext(VoidTypeContext.class,0);
		}
		public ArrayPointerTypeContext arrayPointerType() {
			return getRuleContext(ArrayPointerTypeContext.class,0);
		}
		public MctypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mctype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitMctype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MctypeContext mctype() throws RecognitionException {
		MctypeContext _localctx = new MctypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mctype);
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				primitiveType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				voidType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				arrayPointerType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(MCParser.BOOLEAN, 0); }
		public TerminalNode INT() { return getToken(MCParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(MCParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(MCParser.STRING, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INT) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VoidTypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MCParser.VOID, 0); }
		public VoidTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voidType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitVoidType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoidTypeContext voidType() throws RecognitionException {
		VoidTypeContext _localctx = new VoidTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_voidType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(VOID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayPointerTypeContext extends ParserRuleContext {
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public TerminalNode BOOLEAN() { return getToken(MCParser.BOOLEAN, 0); }
		public TerminalNode INT() { return getToken(MCParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(MCParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(MCParser.STRING, 0); }
		public ArrayPointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayPointerType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitArrayPointerType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayPointerTypeContext arrayPointerType() throws RecognitionException {
		ArrayPointerTypeContext _localctx = new ArrayPointerTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_arrayPointerType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << FLOAT) | (1L << INT) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(225);
			match(LSB);
			setState(226);
			match(RSB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListExpContext extends ParserRuleContext {
		public ExpListContext expList() {
			return getRuleContext(ExpListContext.class,0);
		}
		public ListExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listExp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitListExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListExpContext listExp() throws RecognitionException {
		ListExpContext _localctx = new ListExpContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_listExp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLIT) | (1L << FLOATLIT) | (1L << INTLIT) | (1L << STRINGLIT) | (1L << ID) | (1L << LNOT) | (1L << SUB) | (1L << LB))) != 0)) {
				{
				setState(228);
				expList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpListContext extends ParserRuleContext {
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MCParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MCParser.COMMA, i);
		}
		public ExpListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitExpList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpListContext expList() throws RecognitionException {
		ExpListContext _localctx = new ExpListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			exp();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(232);
				match(COMMA);
				setState(233);
				exp();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Term1Context term1() {
			return getRuleContext(Term1Context.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MCParser.ASSIGN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_exp);
		try {
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				term1(0);
				setState(240);
				match(ASSIGN);
				setState(241);
				exp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				term1(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term1Context extends ParserRuleContext {
		public Term2Context term2() {
			return getRuleContext(Term2Context.class,0);
		}
		public Term1Context term1() {
			return getRuleContext(Term1Context.class,0);
		}
		public TerminalNode LOR() { return getToken(MCParser.LOR, 0); }
		public Term1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term1Context term1() throws RecognitionException {
		return term1(0);
	}

	private Term1Context term1(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Term1Context _localctx = new Term1Context(_ctx, _parentState);
		Term1Context _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_term1, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(247);
			term2(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Term1Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term1);
					setState(249);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(250);
					match(LOR);
					setState(251);
					term2(0);
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Term2Context extends ParserRuleContext {
		public Term3Context term3() {
			return getRuleContext(Term3Context.class,0);
		}
		public Term2Context term2() {
			return getRuleContext(Term2Context.class,0);
		}
		public TerminalNode LAND() { return getToken(MCParser.LAND, 0); }
		public Term2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term2; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term2Context term2() throws RecognitionException {
		return term2(0);
	}

	private Term2Context term2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Term2Context _localctx = new Term2Context(_ctx, _parentState);
		Term2Context _prevctx = _localctx;
		int _startState = 64;
		enterRecursionRule(_localctx, 64, RULE_term2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(258);
			term3();
			}
			_ctx.stop = _input.LT(-1);
			setState(265);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Term2Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term2);
					setState(260);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(261);
					match(LAND);
					setState(262);
					term3();
					}
					} 
				}
				setState(267);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Term3Context extends ParserRuleContext {
		public List<Term4Context> term4() {
			return getRuleContexts(Term4Context.class);
		}
		public Term4Context term4(int i) {
			return getRuleContext(Term4Context.class,i);
		}
		public TerminalNode EQ() { return getToken(MCParser.EQ, 0); }
		public TerminalNode NOTEQ() { return getToken(MCParser.NOTEQ, 0); }
		public Term3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term3; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term3Context term3() throws RecognitionException {
		Term3Context _localctx = new Term3Context(_ctx, getState());
		enterRule(_localctx, 66, RULE_term3);
		int _la;
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				term4();
				setState(269);
				_la = _input.LA(1);
				if ( !(_la==NOTEQ || _la==EQ) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(270);
				term4();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				term4();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term4Context extends ParserRuleContext {
		public List<Term5Context> term5() {
			return getRuleContexts(Term5Context.class);
		}
		public Term5Context term5(int i) {
			return getRuleContext(Term5Context.class,i);
		}
		public TerminalNode LESS() { return getToken(MCParser.LESS, 0); }
		public TerminalNode LESSEQ() { return getToken(MCParser.LESSEQ, 0); }
		public TerminalNode GREAT() { return getToken(MCParser.GREAT, 0); }
		public TerminalNode GREATEQ() { return getToken(MCParser.GREATEQ, 0); }
		public Term4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term4; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term4Context term4() throws RecognitionException {
		Term4Context _localctx = new Term4Context(_ctx, getState());
		enterRule(_localctx, 68, RULE_term4);
		int _la;
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				term5(0);
				setState(276);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS) | (1L << LESSEQ) | (1L << GREAT) | (1L << GREATEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(277);
				term5(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				term5(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term5Context extends ParserRuleContext {
		public Term6Context term6() {
			return getRuleContext(Term6Context.class,0);
		}
		public Term5Context term5() {
			return getRuleContext(Term5Context.class,0);
		}
		public TerminalNode SUB() { return getToken(MCParser.SUB, 0); }
		public TerminalNode ADD() { return getToken(MCParser.ADD, 0); }
		public Term5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term5; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term5Context term5() throws RecognitionException {
		return term5(0);
	}

	private Term5Context term5(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Term5Context _localctx = new Term5Context(_ctx, _parentState);
		Term5Context _prevctx = _localctx;
		int _startState = 70;
		enterRecursionRule(_localctx, 70, RULE_term5, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(283);
			term6(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Term5Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term5);
					setState(285);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(286);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(287);
					term6(0);
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Term6Context extends ParserRuleContext {
		public Term7Context term7() {
			return getRuleContext(Term7Context.class,0);
		}
		public Term6Context term6() {
			return getRuleContext(Term6Context.class,0);
		}
		public TerminalNode DIV() { return getToken(MCParser.DIV, 0); }
		public TerminalNode MUL() { return getToken(MCParser.MUL, 0); }
		public TerminalNode MOD() { return getToken(MCParser.MOD, 0); }
		public Term6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term6; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term6Context term6() throws RecognitionException {
		return term6(0);
	}

	private Term6Context term6(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Term6Context _localctx = new Term6Context(_ctx, _parentState);
		Term6Context _prevctx = _localctx;
		int _startState = 72;
		enterRecursionRule(_localctx, 72, RULE_term6, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(294);
			term7();
			}
			_ctx.stop = _input.LT(-1);
			setState(301);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Term6Context(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term6);
					setState(296);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(297);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(298);
					term7();
					}
					} 
				}
				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Term7Context extends ParserRuleContext {
		public Term7Context term7() {
			return getRuleContext(Term7Context.class,0);
		}
		public TerminalNode LNOT() { return getToken(MCParser.LNOT, 0); }
		public TerminalNode SUB() { return getToken(MCParser.SUB, 0); }
		public Term8Context term8() {
			return getRuleContext(Term8Context.class,0);
		}
		public Term7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term7; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term7Context term7() throws RecognitionException {
		Term7Context _localctx = new Term7Context(_ctx, getState());
		enterRule(_localctx, 74, RULE_term7);
		int _la;
		try {
			setState(307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LNOT:
			case SUB:
				enterOuterAlt(_localctx, 1);
				{
				setState(304);
				_la = _input.LA(1);
				if ( !(_la==LNOT || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(305);
				term7();
				}
				break;
			case BOOLIT:
			case FLOATLIT:
			case INTLIT:
			case STRINGLIT:
			case ID:
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(306);
				term8();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term8Context extends ParserRuleContext {
		public Term9Context term9() {
			return getRuleContext(Term9Context.class,0);
		}
		public TerminalNode LSB() { return getToken(MCParser.LSB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RSB() { return getToken(MCParser.RSB, 0); }
		public Term8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term8; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm8(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term8Context term8() throws RecognitionException {
		Term8Context _localctx = new Term8Context(_ctx, getState());
		enterRule(_localctx, 76, RULE_term8);
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				term9();
				setState(310);
				match(LSB);
				setState(311);
				exp();
				setState(312);
				match(RSB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				term9();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term9Context extends ParserRuleContext {
		public TerminalNode INTLIT() { return getToken(MCParser.INTLIT, 0); }
		public TerminalNode FLOATLIT() { return getToken(MCParser.FLOATLIT, 0); }
		public TerminalNode BOOLIT() { return getToken(MCParser.BOOLIT, 0); }
		public TerminalNode STRINGLIT() { return getToken(MCParser.STRINGLIT, 0); }
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public Term9Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term9; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitTerm9(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term9Context term9() throws RecognitionException {
		Term9Context _localctx = new Term9Context(_ctx, getState());
		enterRule(_localctx, 78, RULE_term9);
		try {
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				match(INTLIT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				match(FLOATLIT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(319);
				match(BOOLIT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(320);
				match(STRINGLIT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(321);
				funccall();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(322);
				match(ID);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(323);
				match(LB);
				setState(324);
				exp();
				setState(325);
				match(RB);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunccallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MCParser.ID, 0); }
		public TerminalNode LB() { return getToken(MCParser.LB, 0); }
		public ListExpContext listExp() {
			return getRuleContext(ListExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(MCParser.RB, 0); }
		public FunccallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funccall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MCVisitor ) return ((MCVisitor<? extends T>)visitor).visitFunccall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunccallContext funccall() throws RecognitionException {
		FunccallContext _localctx = new FunccallContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_funccall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			match(ID);
			setState(330);
			match(LB);
			setState(331);
			listExp();
			setState(332);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 31:
			return term1_sempred((Term1Context)_localctx, predIndex);
		case 32:
			return term2_sempred((Term2Context)_localctx, predIndex);
		case 35:
			return term5_sempred((Term5Context)_localctx, predIndex);
		case 36:
			return term6_sempred((Term6Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean term1_sempred(Term1Context _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term2_sempred(Term2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term5_sempred(Term5Context _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean term6_sempred(Term6Context _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\62\u0151\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\6\2"+
		"V\n\2\r\2\16\2W\3\2\3\2\3\3\3\3\5\3^\n\3\3\4\3\4\3\4\7\4c\n\4\f\4\16\4"+
		"f\13\4\3\5\3\5\3\5\3\5\3\5\5\5m\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\5"+
		"\7w\n\7\3\b\3\b\3\b\7\b|\n\b\f\b\16\b\177\13\b\3\t\3\t\5\t\u0083\n\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\7"+
		"\16\u0095\n\16\f\16\16\16\u0098\13\16\3\17\7\17\u009b\n\17\f\17\16\17"+
		"\u009e\13\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00a9\n"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00b2\n\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\5\27\u00cf\n\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\5\32\u00dd\n\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35\3\36\5\36\u00e8\n\36\3\37\3\37"+
		"\3\37\7\37\u00ed\n\37\f\37\16\37\u00f0\13\37\3 \3 \3 \3 \3 \5 \u00f7\n"+
		" \3!\3!\3!\3!\3!\3!\7!\u00ff\n!\f!\16!\u0102\13!\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\7\"\u010a\n\"\f\"\16\"\u010d\13\"\3#\3#\3#\3#\3#\5#\u0114\n#\3$\3$"+
		"\3$\3$\3$\5$\u011b\n$\3%\3%\3%\3%\3%\3%\7%\u0123\n%\f%\16%\u0126\13%\3"+
		"&\3&\3&\3&\3&\3&\7&\u012e\n&\f&\16&\u0131\13&\3\'\3\'\3\'\5\'\u0136\n"+
		"\'\3(\3(\3(\3(\3(\3(\5(\u013e\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u014a"+
		"\n)\3*\3*\3*\3*\3*\3*\2\6@BHJ+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\b\6\2\5\5\n\n\f\f\23\23\4\2\34\34"+
		"$$\4\2\35\36%&\4\2\30\30  \4\2\31\31!\"\4\2\32\32  \u014d\2U\3\2\2\2\4"+
		"]\3\2\2\2\6_\3\2\2\2\bl\3\2\2\2\nn\3\2\2\2\fv\3\2\2\2\16x\3\2\2\2\20\u0082"+
		"\3\2\2\2\22\u0084\3\2\2\2\24\u0087\3\2\2\2\26\u008c\3\2\2\2\30\u0090\3"+
		"\2\2\2\32\u0096\3\2\2\2\34\u009c\3\2\2\2\36\u00a8\3\2\2\2 \u00aa\3\2\2"+
		"\2\"\u00b3\3\2\2\2$\u00b9\3\2\2\2&\u00c3\3\2\2\2(\u00c6\3\2\2\2*\u00c9"+
		"\3\2\2\2,\u00cc\3\2\2\2.\u00d2\3\2\2\2\60\u00d5\3\2\2\2\62\u00dc\3\2\2"+
		"\2\64\u00de\3\2\2\2\66\u00e0\3\2\2\28\u00e2\3\2\2\2:\u00e7\3\2\2\2<\u00e9"+
		"\3\2\2\2>\u00f6\3\2\2\2@\u00f8\3\2\2\2B\u0103\3\2\2\2D\u0113\3\2\2\2F"+
		"\u011a\3\2\2\2H\u011c\3\2\2\2J\u0127\3\2\2\2L\u0135\3\2\2\2N\u013d\3\2"+
		"\2\2P\u0149\3\2\2\2R\u014b\3\2\2\2TV\5\4\3\2UT\3\2\2\2VW\3\2\2\2WU\3\2"+
		"\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\2\2\3Z\3\3\2\2\2[^\5\60\31\2\\^\5\n\6\2]"+
		"[\3\2\2\2]\\\3\2\2\2^\5\3\2\2\2_d\5\b\5\2`a\7\'\2\2ac\5\b\5\2b`\3\2\2"+
		"\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2e\7\3\2\2\2fd\3\2\2\2gm\7\27\2\2hi\7\27"+
		"\2\2ij\7)\2\2jk\7\25\2\2km\7*\2\2lg\3\2\2\2lh\3\2\2\2m\t\3\2\2\2no\5\62"+
		"\32\2op\7\27\2\2pq\7+\2\2qr\5\f\7\2rs\7,\2\2st\5\26\f\2t\13\3\2\2\2uw"+
		"\5\16\b\2vu\3\2\2\2vw\3\2\2\2w\r\3\2\2\2x}\5\20\t\2yz\7\'\2\2z|\5\20\t"+
		"\2{y\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\17\3\2\2\2\177}\3\2\2\2"+
		"\u0080\u0083\5\22\n\2\u0081\u0083\5\24\13\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0081\3\2\2\2\u0083\21\3\2\2\2\u0084\u0085\5\64\33\2\u0085\u0086\7\27"+
		"\2\2\u0086\23\3\2\2\2\u0087\u0088\5\64\33\2\u0088\u0089\7\27\2\2\u0089"+
		"\u008a\7)\2\2\u008a\u008b\7*\2\2\u008b\25\3\2\2\2\u008c\u008d\7-\2\2\u008d"+
		"\u008e\5\30\r\2\u008e\u008f\7.\2\2\u008f\27\3\2\2\2\u0090\u0091\5\32\16"+
		"\2\u0091\u0092\5\34\17\2\u0092\31\3\2\2\2\u0093\u0095\5\60\31\2\u0094"+
		"\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2"+
		"\2\2\u0097\33\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009b\5\36\20\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\35\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a9\5 \21\2\u00a0\u00a9"+
		"\5(\25\2\u00a1\u00a9\5\"\22\2\u00a2\u00a9\5$\23\2\u00a3\u00a9\5&\24\2"+
		"\u00a4\u00a9\5*\26\2\u00a5\u00a9\5,\27\2\u00a6\u00a9\5.\30\2\u00a7\u00a9"+
		"\5\26\f\2\u00a8\u009f\3\2\2\2\u00a8\u00a0\3\2\2\2\u00a8\u00a1\3\2\2\2"+
		"\u00a8\u00a2\3\2\2\2\u00a8\u00a3\3\2\2\2\u00a8\u00a4\3\2\2\2\u00a8\u00a5"+
		"\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\37\3\2\2\2\u00aa"+
		"\u00ab\7\13\2\2\u00ab\u00ac\7+\2\2\u00ac\u00ad\5> \2\u00ad\u00ae\7,\2"+
		"\2\u00ae\u00b1\5\36\20\2\u00af\u00b0\7\b\2\2\u00b0\u00b2\5\36\20\2\u00b1"+
		"\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2!\3\2\2\2\u00b3\u00b4\7\17\2\2"+
		"\u00b4\u00b5\5\34\17\2\u00b5\u00b6\7\20\2\2\u00b6\u00b7\5> \2\u00b7\u00b8"+
		"\7(\2\2\u00b8#\3\2\2\2\u00b9\u00ba\7\t\2\2\u00ba\u00bb\7+\2\2\u00bb\u00bc"+
		"\5> \2\u00bc\u00bd\7(\2\2\u00bd\u00be\5> \2\u00be\u00bf\7(\2\2\u00bf\u00c0"+
		"\5> \2\u00c0\u00c1\7,\2\2\u00c1\u00c2\5\36\20\2\u00c2%\3\2\2\2\u00c3\u00c4"+
		"\7\6\2\2\u00c4\u00c5\7(\2\2\u00c5\'\3\2\2\2\u00c6\u00c7\5R*\2\u00c7\u00c8"+
		"\7(\2\2\u00c8)\3\2\2\2\u00c9\u00ca\7\7\2\2\u00ca\u00cb\7(\2\2\u00cb+\3"+
		"\2\2\2\u00cc\u00ce\7\r\2\2\u00cd\u00cf\5> \2\u00ce\u00cd\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\7(\2\2\u00d1-\3\2\2\2\u00d2"+
		"\u00d3\5> \2\u00d3\u00d4\7(\2\2\u00d4/\3\2\2\2\u00d5\u00d6\5\64\33\2\u00d6"+
		"\u00d7\5\6\4\2\u00d7\u00d8\7(\2\2\u00d8\61\3\2\2\2\u00d9\u00dd\5\64\33"+
		"\2\u00da\u00dd\5\66\34\2\u00db\u00dd\58\35\2\u00dc\u00d9\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd\63\3\2\2\2\u00de\u00df\t\2\2"+
		"\2\u00df\65\3\2\2\2\u00e0\u00e1\7\16\2\2\u00e1\67\3\2\2\2\u00e2\u00e3"+
		"\t\2\2\2\u00e3\u00e4\7)\2\2\u00e4\u00e5\7*\2\2\u00e59\3\2\2\2\u00e6\u00e8"+
		"\5<\37\2\u00e7\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8;\3\2\2\2\u00e9"+
		"\u00ee\5> \2\u00ea\u00eb\7\'\2\2\u00eb\u00ed\5> \2\u00ec\u00ea\3\2\2\2"+
		"\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef=\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\5@!\2\u00f2\u00f3\7\37\2\2\u00f3"+
		"\u00f4\5> \2\u00f4\u00f7\3\2\2\2\u00f5\u00f7\5@!\2\u00f6\u00f1\3\2\2\2"+
		"\u00f6\u00f5\3\2\2\2\u00f7?\3\2\2\2\u00f8\u00f9\b!\1\2\u00f9\u00fa\5B"+
		"\"\2\u00fa\u0100\3\2\2\2\u00fb\u00fc\f\4\2\2\u00fc\u00fd\7\33\2\2\u00fd"+
		"\u00ff\5B\"\2\u00fe\u00fb\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101A\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0104"+
		"\b\"\1\2\u0104\u0105\5D#\2\u0105\u010b\3\2\2\2\u0106\u0107\f\4\2\2\u0107"+
		"\u0108\7#\2\2\u0108\u010a\5D#\2\u0109\u0106\3\2\2\2\u010a\u010d\3\2\2"+
		"\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010cC\3\2\2\2\u010d\u010b"+
		"\3\2\2\2\u010e\u010f\5F$\2\u010f\u0110\t\3\2\2\u0110\u0111\5F$\2\u0111"+
		"\u0114\3\2\2\2\u0112\u0114\5F$\2\u0113\u010e\3\2\2\2\u0113\u0112\3\2\2"+
		"\2\u0114E\3\2\2\2\u0115\u0116\5H%\2\u0116\u0117\t\4\2\2\u0117\u0118\5"+
		"H%\2\u0118\u011b\3\2\2\2\u0119\u011b\5H%\2\u011a\u0115\3\2\2\2\u011a\u0119"+
		"\3\2\2\2\u011bG\3\2\2\2\u011c\u011d\b%\1\2\u011d\u011e\5J&\2\u011e\u0124"+
		"\3\2\2\2\u011f\u0120\f\4\2\2\u0120\u0121\t\5\2\2\u0121\u0123\5J&\2\u0122"+
		"\u011f\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125I\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\b&\1\2\u0128\u0129"+
		"\5L\'\2\u0129\u012f\3\2\2\2\u012a\u012b\f\4\2\2\u012b\u012c\t\6\2\2\u012c"+
		"\u012e\5L\'\2\u012d\u012a\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2"+
		"\2\2\u012f\u0130\3\2\2\2\u0130K\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133"+
		"\t\7\2\2\u0133\u0136\5L\'\2\u0134\u0136\5N(\2\u0135\u0132\3\2\2\2\u0135"+
		"\u0134\3\2\2\2\u0136M\3\2\2\2\u0137\u0138\5P)\2\u0138\u0139\7)\2\2\u0139"+
		"\u013a\5> \2\u013a\u013b\7*\2\2\u013b\u013e\3\2\2\2\u013c\u013e\5P)\2"+
		"\u013d\u0137\3\2\2\2\u013d\u013c\3\2\2\2\u013eO\3\2\2\2\u013f\u014a\7"+
		"\25\2\2\u0140\u014a\7\24\2\2\u0141\u014a\7\4\2\2\u0142\u014a\7\26\2\2"+
		"\u0143\u014a\5R*\2\u0144\u014a\7\27\2\2\u0145\u0146\7+\2\2\u0146\u0147"+
		"\5> \2\u0147\u0148\7,\2\2\u0148\u014a\3\2\2\2\u0149\u013f\3\2\2\2\u0149"+
		"\u0140\3\2\2\2\u0149\u0141\3\2\2\2\u0149\u0142\3\2\2\2\u0149\u0143\3\2"+
		"\2\2\u0149\u0144\3\2\2\2\u0149\u0145\3\2\2\2\u014aQ\3\2\2\2\u014b\u014c"+
		"\7\27\2\2\u014c\u014d\7+\2\2\u014d\u014e\5:\36\2\u014e\u014f\7,\2\2\u014f"+
		"S\3\2\2\2\33W]dlv}\u0082\u0096\u009c\u00a8\u00b1\u00ce\u00dc\u00e7\u00ee"+
		"\u00f6\u0100\u010b\u0113\u011a\u0124\u012f\u0135\u013d\u0149";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}