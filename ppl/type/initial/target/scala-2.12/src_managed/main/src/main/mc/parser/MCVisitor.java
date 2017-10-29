// Generated from src/main/mc/parser/MC.g4 by ANTLR 4.6

    package mc.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(MCParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListID(MCParser.ListIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#termID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermID(MCParser.TermIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(MCParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListPara(MCParser.ListParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#non_listPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_listPara(MCParser.Non_listParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#paraDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaDecl(MCParser.ParaDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#paraDecl1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaDecl1(MCParser.ParaDecl1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#paraDecl2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaDecl2(MCParser.ParaDecl2Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#blockStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStmt(MCParser.BlockStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListCmd(MCParser.ListCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listVarDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListVarDecl(MCParser.ListVarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListStmt(MCParser.ListStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(MCParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MCParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#dowhileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDowhileStmt(MCParser.DowhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MCParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MCParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#callStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStmt(MCParser.CallStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MCParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MCParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpStmt(MCParser.ExpStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(MCParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#mctype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMctype(MCParser.MctypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(MCParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#voidType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidType(MCParser.VoidTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#arrayPointerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayPointerType(MCParser.ArrayPointerTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#listExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListExp(MCParser.ListExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#expList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpList(MCParser.ExpListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(MCParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm1(MCParser.Term1Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm2(MCParser.Term2Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm3(MCParser.Term3Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm4(MCParser.Term4Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm5(MCParser.Term5Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm6(MCParser.Term6Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm7(MCParser.Term7Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm8(MCParser.Term8Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#term9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm9(MCParser.Term9Context ctx);
	/**
	 * Visit a parse tree produced by {@link MCParser#funccall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunccall(MCParser.FunccallContext ctx);
}