/**
 * Student name: Doan Ky An
 * Student ID: 1410160
 */
package mc.checker

/**
 * @author nhphung
 */

import mc.parser._
import mc.utils._
import java.io.{File, PrintWriter}

//import mc.codegen.Val
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree._

import scala.collection.JavaConverters._


case class Symbol(name:String, typ:Type, va:Val) {
        override def toString = "Symbol(" + name + "," + typ + "," + va + ")"
}
trait Val

case class FunctionType(input:List[Type],output:Type) extends Type {
        override def toString = "FunctionType(" + input + "," + output + ")"
}

class StaticChecker(ast:AST) extends BaseVisitor with Utils {
    
    def check() = visit(ast,null)

    def getName_of_Symbol(x:Symbol) = x.name

    def checkReturnOfFunc(funcType:Type,returnType:Type): Boolean = { 
        if(funcType.isInstanceOf[ArrayPointerType]){ 
            if(returnType.isInstanceOf[ArrayType]) 
                return checkReturnOfFunc(funcType.asInstanceOf[ArrayPointerType].eleType, returnType.asInstanceOf[ArrayType].eleType)
            if(returnType.isInstanceOf[ArrayPointerType]) 
                return checkReturnOfFunc(funcType.asInstanceOf[ArrayPointerType].eleType, returnType.asInstanceOf[ArrayPointerType].eleType)
            return false
        }else{
            if(returnType.isInstanceOf[ArrayType]||returnType.isInstanceOf[ArrayPointerType]) return false
            val x = funcType.toString()
            val y  = returnType.toString()
            (x,y) match {
                case("BoolType","BoolType") => true
                case("StringTye","StringTye") => true
                case("FloatType","FloatType") => true
                case("FloatType","IntType") => true
                 case("IntType","IntType") => true
                case("VoidType","VoidType") => true
                case _ => false
                }
            }
    }

    override def visitProgram(ast: Program, c: Any): Any = {
        val t_Symbols = ast.decl.foldLeft(List[Symbol]())((L,y) => y match {
                case VarDecl(i,t) => {
                    if(lookup(i.name,L, getName_of_Symbol) == None)
                        Symbol(i.name, t, null) :: L
                    else throw Redeclared(Variable,i.name)
                }
                case FuncDecl(i,p,t,b) => {
                    if(lookup(i.name,L, getName_of_Symbol) == None)
                        Symbol(i.name, FunctionType(p.map(x=>x.varType), t), null) :: L
                    else
                        throw Redeclared(Function,i.name)
            }
        })
        //return 
        val env = List(t_Symbols)
        ast.decl.filter(_.isInstanceOf[FuncDecl]).map(_.accept(this,env)
        )
        env
		}

	override def visitFuncDecl(ast: FuncDecl, c: Any): Any = {
        
        val env = c.asInstanceOf[List[List[Symbol]]]

        //Tao List[Symbol]() cho parameter
        val temp_Symbols = ast.param.foldLeft(List[Symbol]())((L,x) =>
            if(lookup(x.variable.name,L,getName_of_Symbol) != None) 
                throw Redeclared(Parameter,x.variable.name)
            else 
                Symbol(x.variable.name,x.varType,null)::L
        )

        //truyen vao moi truong moi de kiem tra trong body Function
        if(ast.body.isInstanceOf[Block]){ 
            ast.body.asInstanceOf[Block].decl.foldLeft(temp_Symbols)(
                (L,y) => y match {
                    case VarDecl(i,t) => { 
                        if(lookup(i.name,L, getName_of_Symbol) == None){
                            Symbol(i.name, t, null) :: L
                        }
                        else throw Redeclared(Variable,i.name)
                    }
                    case FuncDecl(i,p,t,b) => {
                        if(lookup(i.name,L, getName_of_Symbol) == None)
                            Symbol(i.name, FunctionType(p.map(x=>x.varType), t), null) :: L
                        else
                            throw Redeclared(Function,i.name)
                    }
                }
            )  
            ast.body.asInstanceOf[Block].stmt.map((x:Stmt) =>
                if(!x.isInstanceOf[Return]) x.accept(this,env)
                else {  //check return Stmt
                    if(checkReturnOfFunc(ast.returnType,x.asInstanceOf[Stmt].accept(this,env).asInstanceOf[Type]) == false)
                        throw TypeMismatchInStatement(x.asInstanceOf[Return])

                }   
                )            
        }
        //truyen lai env Function
        env
	}

    override    def visitBlock(ast: Block, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val t_Symbols = ast.decl.foldLeft(List[Symbol]())((L,y) => y match {
            case VarDecl(i,t) => {
                if(lookup(i.name,L, getName_of_Symbol) == None)
                    Symbol(i.name, t, null) :: L
                else throw Redeclared(Variable,i.name)
            }
            case FuncDecl(i,p,t,b) => {
                if(lookup(i.name,L, getName_of_Symbol) == None)
                    Symbol(i.name, FunctionType(p.map(x=>x.varType), t), null) :: L
                else
                    throw Redeclared(Function,i.name)
            }
        })
        //truyen moi truong moi cho moi stmt
        ast.stmt.filter(_.isInstanceOf[Block]).map(_.accept(this,t_Symbols::env))
        //return env cu
        env
    }
    override def visitIf(ast:If, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        //xu ly boolean
        if(ast.expr.accept(this,env).toString() != "BoolType") 
            throw TypeMismatchInStatement(ast)



        //xu ly thenstmt
        ast.thenStmt.accept(this,env)
        //xy ly elseStmt
        if(ast.elseStmt != None)
            ast.elseStmt.map(_.accept(this,env))

        //return
        env
    }
    override def visitFor(ast: For, c: Any): Any = { 
        val env = c.asInstanceOf[List[List[Symbol]]]
        //xu ly expr1
        if(ast.expr1.accept(this,env).toString() != "IntType")
            throw TypeMismatchInStatement(ast)
        //xu ly expr3
        if(ast.expr3.accept(this,env).toString() != "IntType") 
            throw TypeMismatchInStatement(ast)
        //xu ly expr2
        if(ast.expr2.accept(this,env).toString() != "BoolType") 
            throw TypeMismatchInStatement(ast)
        //visit look
        ast.loop.accept(this,env)
        //return env
        env
    }
    override def visitBreak(ast: Break.type, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        //return 
        env
    }
    override def visitContinue(ast: Continue.type, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        //return
        env
    }
    //visitReturn
    override def visitReturn(ast: Return, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        if(ast.expr == None) VoidType
        else ast.expr.map(_.accept(this,env)).get
    }
    override def visitDowhile(ast: Dowhile, c:Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        if(ast.exp.accept(this,env).toString() != "BoolType")
            throw TypeMismatchInStatement(ast)
        //visit tung stmt trong While
        ast.sl.map(_.accept(this,env))
        //return env
        env
    }


    override def visitBinaryOp(ast: BinaryOp, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        
        //return env
        env
    }
    override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
         val env = c.asInstanceOf[List[List[Symbol]]]
        //return env
        env
    }
    override def visitCallExpr(ast: CallExpr, c: Any): Any = null

    override def visitIntLiteral(ast: IntLiteral, c: Any): Any = IntType
    override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = FloatType
    override def visitStringLiteral(ast: StringLiteral, c: Any): Any = StringType
    override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = BoolType
    override def visitArrayType(ast: ArrayType, c: Any): Any = ast
    override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = ast
    override def visitId(ast: Id, c: Any): Any = ArrayType(IntLiteral(5),BoolType)
    	
}
