package mc.astgen
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import java.io.{PrintWriter,File}
import org.antlr.v4.runtime.ANTLRFileStream
import mc.utils._
import scala.collection.JavaConverters._
import org.antlr.v4.runtime.tree._
import mc.parser._
import mc.parser.MCParser._

class ASTGeneration extends MCBaseVisitor[Any] {
  /* program : decl+ EOF  */
  override def visitProgram(ctx:ProgramContext) = Program(ctx.decl.asScala.toList.map(_.accept(this).asInstanceOf[List[Decl]]).flatten)
  
  /* decl: varDecl | funcDecl */
  override def visitDecl(ctx: DeclContext) = {
    ctx.getChild(0).accept(this)
  }
  
  /* funcDecl: mctype ID LB listPara RB blockStmt */
  override def visitFuncDecl(ctx: FuncDeclContext) = {
    List(FuncDecl(Id(ctx.ID.getText.toString),ctx.listPara.accept(this).asInstanceOf[List[VarDecl]],
      ctx.mctype.accept(this).asInstanceOf[Type],
      ctx.blockStmt.accept(this).asInstanceOf[Stmt]))
  }


  /* listPara: (non_listPara)? */
  override def visitListPara( ctx: ListParaContext) = {
    if(ctx.non_listPara == null) List()
    else ctx.non_listPara.accept(this)
    }

  /* non_listPara: paraDecl (COMMA paraDecl)* */
  override def visitNon_listPara(ctx: Non_listParaContext) = {
    ctx.paraDecl.asScala.toList.map(_.accept(this).asInstanceOf[VarDecl])
  }
  /* paraDecl: paraDecl1 | paraDecl2  */
  override def visitParaDecl(ctx: ParaDeclContext) = {
    ctx.getChild(0).accept(this)
  }
  /* paraDecl1: primitiveType ID */
  override def visitParaDecl1(ctx : ParaDecl1Context)  = {
    VarDecl(Id(ctx.ID.getText.toString),ctx.primitiveType.accept(this).asInstanceOf[Type])
  }
  /* paraDecl2: primitiveType ID LSB RSB  */
  override def visitParaDecl2(ctx: ParaDecl2Context) = {
    VarDecl(Id(ctx.ID.getText.toString),ArrayPointerType(ctx.primitiveType.accept(this).asInstanceOf[Type]))
  }
  /* blockStmt: LP listCmd RP  */
  override def visitBlockStmt(ctx: BlockStmtContext) = {
    ctx.listCmd.accept(this)
  }

  /* listCmd: listVarDecl listStmt */
  override def visitListCmd(ctx: ListCmdContext) = {
    Block(ctx.listVarDecl.accept(this).asInstanceOf[List[Decl]],ctx.listStmt.accept(this).asInstanceOf[List[Stmt]])
  }
  /* listVarDecl: varDecl* */
  override def visitListVarDecl(ctx: ListVarDeclContext) = {
    if(ctx.varDecl == null) List[Decl]()
    else {
      val t_list = ctx.varDecl.asScala.toList.map(_.accept(this))
      t_list.asInstanceOf[List[List[Decl]]].flatten
    }
  }
  /* varDecl: primitiveType listID SEMI  */
  override def visitVarDecl(ctx: VarDeclContext) = {
      val t_list = ctx.listID.accept(this).asInstanceOf[List[LHS]]
      t_list.map((x) =>
        if(x.isInstanceOf[Id]) 
          VarDecl(x.asInstanceOf[Id], ctx.primitiveType.accept(this).asInstanceOf[Type])
        else
          VarDecl(x.asInstanceOf[ArrayCell].arr.asInstanceOf[Id],ArrayType(x.asInstanceOf[ArrayCell].idx.asInstanceOf[IntLiteral],ctx.primitiveType.accept(this).asInstanceOf[Type]))
      )
  }
  /* listID: termID( COMMA termID )*/
  override def visitListID(ctx: ListIDContext) = {
    ctx.termID.asScala.toList.map(_.accept(this))
  }
  /* termID: ID | ID LSB INTLIT RSB  */
  override def visitTermID(ctx: TermIDContext) = {
    if(ctx.getChildCount() == 1) Id(ctx.ID.getText.toString)
    else ArrayCell(Id(ctx.ID.getText.toString),IntLiteral(ctx.INTLIT.getText.toInt))
  }
  /* listStmt: stmt* */
  override def visitListStmt(ctx: ListStmtContext) = {
    if(ctx.stmt == null) Nil
    else ctx.stmt.asScala.toList.map(_.accept(this).asInstanceOf[Stmt])
  }
  /* stmt: ifStmt
    | callStmt
    | dowhileStmt
    | forStmt
    | breakStmt
    | continueStmt
    | returnStmt
    | expStmt
    | blockStmt
    */
  override def visitStmt(ctx: StmtContext) = {
   ctx.getChild(0).accept(this)
  }
  /* ifStmt: IF LB exp RB stmt (ELSE stmt)? */
  override def visitIfStmt(ctx: IfStmtContext) = {
    if(ctx.stmt(1) != null)
      If(ctx.exp.accept(this).asInstanceOf[Expr],
        ctx.stmt(0).accept(this).asInstanceOf[Stmt],
        Some(ctx.stmt(1).accept(this).asInstanceOf[Stmt])
    )
    else
      If(ctx.exp.accept(this).asInstanceOf[Expr],
      ctx.stmt(0).accept(this).asInstanceOf[Stmt],
      None)
  }
  /* dowhileStmt: DO listStmt WHILE exp SEMI */
  override  def visitDowhileStmt(ctx: DowhileStmtContext) = {
    Dowhile(ctx.listStmt.accept(this).asInstanceOf[List[Stmt]], ctx.exp.accept(this).asInstanceOf[Expr])
  }
  /* forStmt: FOR LB exp SEMI exp SEMI exp RB stmt */
  override def visitForStmt(ctx: ForStmtContext)  = {
    For(ctx.exp(0).accept(this).asInstanceOf[Expr],
      ctx.exp(1).accept(this).asInstanceOf[Expr],
      ctx.exp(2).accept(this).asInstanceOf[Expr],
      ctx.stmt.accept(this).asInstanceOf[Stmt]
    )
  }
  /* breakStmt: BREAK SEMI */
  override def visitBreakStmt(ctx: BreakStmtContext) = {
    Break
  }
  /* callStmt: funccall SEMI  */
  override def visitCallStmt(ctx: CallStmtContext) = {
    ctx.funccall.accept(this)
  }
  /* continueStmt: CONTINUE SEMI  */
  override def visitContinueStmt(ctx: ContinueStmtContext) = {
    Continue
  }
  /* returnStmt: RETURN exp? SEMI */
  override def visitReturnStmt(ctx: ReturnStmtContext) = {
    if(ctx.exp == null) Return(None)
    else Return(Some(ctx.exp.accept(this).asInstanceOf[Expr])) 
  }
  /* expStmt: exp SEMI */
  override def visitExpStmt(ctx: ExpStmtContext) = {
    ctx.exp.accept(this)
  }
  /* mctype: primitiveType | voidType | arrayPointerType */
  override def visitMctype(ctx: MctypeContext) = {
    ctx.getChild(0).accept(this)
  }
  /* primitiveType: BOOLEAN | INT | FLOAT | STRING */
  override def visitPrimitiveType(ctx: PrimitiveTypeContext) = {
    if(ctx.BOOLEAN != null) BoolType
    else if(ctx.INT != null) IntType
    else if(ctx.FLOAT != null) FloatType
    else StringType
  }
  /* voidType: VOID  */
  override def visitVoidType(ctx: VoidTypeContext) = {
    VoidType
  }
  /* arrayPointerType: (BOOLEAN | INT | FLOAT | STRING)  LSB RSB */
  override def visitArrayPointerType(ctx: ArrayPointerTypeContext) = {
    if(ctx.BOOLEAN != null) ArrayPointerType(BoolType)
    else if(ctx.INT != null)  ArrayPointerType(IntType)
    else if(ctx.FLOAT != null)  ArrayPointerType(FloatType)
    else if(ctx.STRING != null) ArrayPointerType(StringType)
  }
  /* listExp: expList? */
  override def visitListExp(ctx: ListExpContext) = {
    if(ctx.expList == null) Nil
    else ctx.expList.accept(this)
  }
  /* expList: exp (COMMA exp)* */
  override def visitExpList(ctx: ExpListContext) = {
    ctx.exp.asScala.toList.map(_.accept(this).asInstanceOf[Expr])
  }
  /* exp: term1 ASSIGN exp
    | term1 */
  override def visitExp(ctx: ExpContext) = {
    if(ctx.getChildCount() == 1) ctx.term1.accept(this)
    else BinaryOp(ctx.ASSIGN.getText.toString,
      ctx.term1.accept(this).asInstanceOf[Expr],
      ctx.exp.accept(this).asInstanceOf[Expr])
  }
  /* term1: term1 LOR term2
    | term2 */
  override def visitTerm1(ctx: Term1Context) = {
    if(ctx.getChildCount() == 1) ctx.term2.accept(this)
    else BinaryOp(ctx.LOR.getText.toString,
      ctx.term1.accept(this).asInstanceOf[Expr],
      ctx.term2.accept(this).asInstanceOf[Expr])
  }
  /* term2: term2 LAND term3
    | term3 */
  override def visitTerm2(ctx: Term2Context) = {
    if(ctx.getChildCount() == 1) ctx.term3.accept(this)
    else BinaryOp(ctx.LAND.getText.toString,
      ctx.term2.accept(this).asInstanceOf[Expr],
      ctx.term3.accept(this).asInstanceOf[Expr]) 
  }
  /* term3: term4 (EQ | NOTEQ) term4
    | term4  */
  override def visitTerm3(ctx: Term3Context) = {
    if(ctx.getChildCount() == 1) ctx.term4(0).accept(this)
    else
      BinaryOp(ctx.getChild(1).getText.toString,
      ctx.term4(0).accept(this).asInstanceOf[Expr],
      ctx.term4(1).accept(this).asInstanceOf[Expr])
  }
  /* term4: term5 (LESS | LESSEQ | GREAT | GREATEQ) term5
    | term5 */
  override def visitTerm4(ctx: Term4Context) = {
    if(ctx.getChildCount() == 1) ctx.term5(0).accept(this)
    else BinaryOp(ctx.getChild(1).getText.toString,
      ctx.term5(0).accept(this).asInstanceOf[Expr],
      ctx.term5(1).accept(this).asInstanceOf[Expr])
  }
  /* term5: term5 (SUB | ADD) term6
    | term6  */
  override def visitTerm5(ctx: Term5Context) = {
    if(ctx.getChildCount() == 1) ctx.term6.accept(this)
    else BinaryOp(ctx.getChild(1).getText.toString,
      ctx.term5.accept(this).asInstanceOf[Expr],
      ctx.term6.accept(this).asInstanceOf[Expr])
  }
  /* term6: term6 (DIV | MUL | MOD) term7
    | term7 */
  override def visitTerm6(ctx: Term6Context) = {
    if(ctx.getChildCount() == 1) ctx.term7.accept(this)
    else BinaryOp(ctx.getChild(1).getText.toString,
      ctx.term6.accept(this).asInstanceOf[Expr],
      ctx.term7.accept(this).asInstanceOf[Expr])    
    }
    /* term7: (LNOT|SUB) term7
     | term8 */
  override def visitTerm7(ctx: Term7Context) = {
    if(ctx.getChildCount() == 1) ctx.term8.accept(this)
    else UnaryOp(ctx.getChild(0).getText.toString, ctx.term7.accept(this).asInstanceOf[Expr])
  }
  /* term8:  term9 LSB exp RSB
    | term9*/
  override def visitTerm8(ctx: Term8Context) = {
      if(ctx.getChildCount() == 1) ctx.term9.accept(this).asInstanceOf[Expr]
      else ArrayCell(ctx.term9.accept(this).asInstanceOf[Expr], ctx.exp.accept(this).asInstanceOf[Expr])
   }
  /* term9: INTLIT
    | FLOATLIT
    | BOOLIT
    | STRINGLIT
    | funccall
    | ID
    | LB exp RB */
  override def visitTerm9(ctx: Term9Context) = {
    if(ctx.getChildCount() == 3) ctx.exp.accept(this).asInstanceOf[Expr]
    else if (ctx.ID != null) Id(ctx.ID.getText)
    else if (ctx.INTLIT != null) IntLiteral(ctx.INTLIT.getText.toInt)
    else if (ctx.FLOATLIT != null) FloatLiteral(ctx.FLOATLIT.getText.toFloat)
    else if (ctx.STRINGLIT != null) StringLiteral(ctx.STRINGLIT.getText.toString) //Co the loi
    else if (ctx.BOOLIT != null) BooleanLiteral(ctx.BOOLIT.getText.toBoolean)  //Co the loi
    else ctx.funccall.accept(this).asInstanceOf[Expr]
  }
  /* funccall: ID LB listExp RB */
  override def visitFunccall(ctx: FunccallContext) = {
    CallExpr(Id(ctx.ID.getText.toString),ctx.listExp.accept(this).asInstanceOf[List[Expr]])
  }
}
