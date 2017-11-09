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

    def built_in_Function():Any = {
        val getInt = Symbol("getInt",FunctionType(List(),IntType),null)
        val putInt = Symbol("putInt",FunctionType(List(IntType),VoidType),null)
        val putIntLn =  Symbol("putIntLn",FunctionType(List(IntType),VoidType),null)
        val getFloat = Symbol("getFloat",FunctionType(List(),FloatType),null) 
        val putFloat = Symbol("putFloat",FunctionType(List(),VoidType),null)
        val putFloatLn = Symbol("putFloatLn",FunctionType(List(FloatType),VoidType),null) 
        val putBool = Symbol("putBool",FunctionType(List(BoolType),VoidType),null)
        val putBoolLn = Symbol("putBoolLn",FunctionType(List(BoolType),VoidType),null)
        val putString = Symbol("putString",FunctionType(List(StringType),VoidType),null)
        val putStringLn =  Symbol("putStringLn",FunctionType(List(StringType),VoidType),null)
        val putLn = Symbol("putLn",FunctionType(List(),VoidType),null)
        val list_symbol = List[Symbol](getInt,putInt,putIntLn,getFloat,putFloat,putFloatLn,putBool, putBoolLn,putString,putStringLn,putLn)  
        list_symbol
    }

    def getName_of_Symbol(x:Symbol) = x.name

    def changeEnvFunction2(name:Id,l:List[Symbol]):List[Symbol] = {
        val functionSymbol = lookup(name.name,l,getName_of_Symbol).get
        val idx = l.indexOf(functionSymbol)
        val range1 = List.range(0,idx)
        val range2 = List.range(idx+1,l.size)
        val temp_list = range2.foldLeft(range1.foldLeft(List[Symbol]())((L,x)=>l(x)::L))((L,x)=>l(x)::L)
        temp_list.foldLeft(List(functionSymbol))((L,x)=>x::L)
    }
    def changeEnvFunction(name: Id, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val sizeenv = env.size
        val globalenv = env.last
        val rangeenv = List.range(0,sizeenv-1)
        val temp_list = rangeenv.foldLeft(List[List[Symbol]]())((L,x) => env(x)::L)
        val newglobalenv = changeEnvFunction2(name,globalenv)
        val newenv = List(newglobalenv)
        temp_list.foldLeft(newenv)((L:List[List[Symbol]],x:List[Symbol]) => x::L)
    }
    def getFunctionType(c: Any): Type = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        env.last.last.typ.asInstanceOf[FunctionType].output
    }
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
                case("StringType","StringType") => true
                case("FloatType","FloatType") => true
                case("FloatType","IntType") => true
                 case("IntType","IntType") => true
                case("VoidType","VoidType") => true
                case _ => false
                }
            }
    }
    def checkUndeclaredIdentifier(expr: Id, c: Any): Boolean = { 

        val list_symbol = c.asInstanceOf[List[List[Symbol]]].flatten
        if(lookup(expr.name, list_symbol, getName_of_Symbol) == None) true
        else false
    }
    def checkAssign(ast: BinaryOp, c: Any): Type = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        if(typeLeft.isInstanceOf[ArrayPointerType])
            throw TypeMismatchInExpression(ast)
        else if(typeLeft.isInstanceOf[ArrayType])
            throw TypeMismatchInExpression(ast)
        else{
            val x = typeLeft.toString()
            val y  = typeRight.toString()
            (x,y) match {
                case("BoolType","BoolType") => typeLeft
                case("StringType","StringType") => typeLeft
                case("FloatType","FloatType") => typeLeft
                case("FloatType","IntType") => typeLeft
                case("IntType","IntType") => typeLeft
                case _ => throw TypeMismatchInExpression(ast)
                }
            }
    }
    def check_add_sub_div_mul(ast:BinaryOp, c:Any): Type = {

        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        val x = typeLeft.toString()
        val y = typeRight.toString()
        (x,y) match {
            case("FloatType","FloatType") => FloatType
            case("FloatType","IntType") => FloatType
            case("IntType","IntType") => IntType
            case("IntType","FloatType") => FloatType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_equal_notequal(ast:BinaryOp,c:Any): Type = {
        
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        val x = typeLeft.toString()
        val y = typeRight.toString()
        (x,y) match {
            case("IntType","IntType") => BoolType
            case("BoolType","BoolType") => BoolType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_compare(ast:BinaryOp,c:Any): Type = {
        
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        val x = typeLeft.toString()
        val y = typeRight.toString()
        (x,y) match {
            case("FloatType","FloatType") => BoolType
            case("FloatType","IntType") => BoolType
            case("IntType","IntType") => BoolType
            case("IntType","FloatType") => BoolType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_logicAnd_logicOr(ast:BinaryOp,c:Any): Type = {
        
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        val x = typeLeft.toString()
        val y = typeRight.toString()
        (x,y) match {
            case("BoolType","BoolType") => BoolType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_mod(ast:BinaryOp,c:Any): Type = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeLeft = ast.left.accept(this,env).asInstanceOf[Type]
        val typeRight = ast.right.accept(this,env).asInstanceOf[Type]
        val x = typeLeft.toString()
        val y = typeRight.toString()
        (x,y) match {
            case("IntType","IntType") => IntType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_UnarySub(ast:UnaryOp, c: Any): Type = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeUnary = ast.body.accept(this,env).asInstanceOf[Type]
        val x = typeUnary.toString()
        (x) match {
            case("IntType") => IntType
            case("FloatType") => FloatType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def check_UnaryNot(ast:UnaryOp, c: Any): Type = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        val typeUnary = ast.body.accept(this,env).asInstanceOf[Type]
        val x = typeUnary.toString()
        (x) match {
            case("BoolType") => BoolType
            case _ =>throw TypeMismatchInExpression(ast)           
        }
    }
    def checkParamOfFunc(funcType:Type,returnType:Type): Boolean = { 
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
                case("StringType","StringType") => true
                case("FloatType","FloatType") => true
                case("FloatType","IntType") => true
                case("IntType","IntType") => true
                case _ => false
                }
            }
    }

    override def visitProgram(ast: Program, c: Any): Any = {
        val globalSymbols = built_in_Function().asInstanceOf[List[Symbol]]
        val t_Symbols = ast.decl.foldLeft(globalSymbols)((L,y) => y match {
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
        ast.decl.filter(_.isInstanceOf[FuncDecl]).map(_.accept(this,env))
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
            val temp2_Symbols = ast.body.asInstanceOf[Block].decl.foldLeft(temp_Symbols)(
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
            val newenv = temp2_Symbols::env 
            val returnenv = changeEnvFunction(ast.name, newenv)
            ast.body.asInstanceOf[Block].stmt.map((x:Stmt) =>  
                if(x.isInstanceOf[Return]) x.accept(this,returnenv)
                else x.accept(this,newenv)
                )            
        }
        //truyen lai env Function
        env
	}
    override def visitBlock(ast: Block, c: Any): Any = {
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
        ast.stmt.map(_.accept(this,t_Symbols::env))
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
        val funcType = getFunctionType(env)
        //return
        val returnType = if(ast.expr == None) VoidType
        else ast.expr.map(_.accept(this,env)).get

        //checkTypeMismatchInStatement
        if(checkReturnOfFunc(funcType,returnType.asInstanceOf[Type]) == false)
            throw TypeMismatchInStatement(ast)
        env 
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
        // //Check Undeclared
        // ast.left.accept(this,env)
        // ast.right.accept(this,env)
        
        //return type
        if(ast.op =="=") return checkAssign(ast, env)
        if(ast.op == "+" || ast.op == "-" || ast.op == "*" || ast.op == "/") return check_add_sub_div_mul(ast,env)
        if(ast.op =="==" || ast.op == "!=") return check_equal_notequal(ast,env)
        if(ast.op == "<" || ast.op == ">" || ast.op == ">=" || ast.op == "<=") return check_compare(ast,env)
        if(ast.op == "&&" || ast.op == "||") return check_logicAnd_logicOr(ast,env)
        if(ast.op == "%") return check_mod(ast,env)
    }
    override def visitUnaryOp(ast: UnaryOp, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        // //Check Undeclared
        // ast.body.accept(this,env)
        //return type
        if(ast.op == "-") return check_UnarySub(ast,env)
        if(ast.op == "!") return check_UnaryNot(ast,env)
    }
    override def visitArrayCell(ast: ArrayCell, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]


        //Check Undeclared
        if(ast.idx.accept(this,env).toString() != "IntType")
            throw TypeMismatchInExpression(ast)

        if(ast.arr.accept(this,env).isInstanceOf[ArrayType])
            return ast.arr.accept(this,env).asInstanceOf[ArrayType].eleType
        else{     
            if(ast.arr.accept(this,env).isInstanceOf[ArrayPointerType]){
                return ast.arr.accept(this,env).asInstanceOf[ArrayPointerType].eleType
            }
            else throw TypeMismatchInExpression(ast)
        }  
    }
    override def visitCallExpr(ast: CallExpr, c: Any): Any = {
        val env =  c.asInstanceOf[List[List[Symbol]]]
        //Check Undeclared Function 
        if(checkUndeclaredIdentifier(ast.method,env) == true)
            throw Undeclared(Function,ast.method.name)

        //check TypeMismatchInExpression
        val list_symbol = env.flatten
        val symbolOfId = lookup(ast.method.name,list_symbol,getName_of_Symbol).get
        if(!symbolOfId.typ.isInstanceOf[FunctionType]) throw Undeclared(Function,symbolOfId.name)
        //check so luong parameter
        val sizeArgument = ast.params.size
        val sizeParameter = symbolOfId.typ.asInstanceOf[FunctionType].input.asInstanceOf[List[Type]].size
        if(sizeArgument != sizeParameter)
            throw TypeMismatchInExpression(ast)

        //check kieu cua tung tham so
        val listType_Parameter = symbolOfId.typ.asInstanceOf[FunctionType].input.asInstanceOf[List[Type]]
        val listType_Argument = ast.params.map(_.accept(this,env).asInstanceOf[Type])
        if(listType_Parameter.zip(listType_Argument).forall(x=>checkParamOfFunc(x._1,x._2)) == false)
            throw TypeMismatchInExpression(ast)
        

        //return type
        symbolOfId.typ.asInstanceOf[FunctionType].output
    }
    override def visitId(ast: Id, c: Any): Any = {
        val env = c.asInstanceOf[List[List[Symbol]]]
        //checkUndeclaredIdentifier
        if(checkUndeclaredIdentifier(ast,env))
            throw Undeclared(Identifier,ast.name)

        //return type Id
        val list_symbol = env.flatten
        val symbolOfId = lookup(ast.name,list_symbol,getName_of_Symbol).get
        //neu la function thi tra ve kieu cua function con id thi tra ve kieu id
        if(symbolOfId.typ.isInstanceOf[FunctionType]) return symbolOfId.typ.asInstanceOf[FunctionType].output
        symbolOfId.typ
    }

    override def visitIntLiteral(ast: IntLiteral, c: Any): Any = IntType
    override def visitFloatLiteral(ast: FloatLiteral, c: Any): Any = FloatType
    override def visitStringLiteral(ast: StringLiteral, c: Any): Any = StringType
    override def visitBooleanLiteral(ast: BooleanLiteral, c: Any): Any = BoolType
    override def visitArrayType(ast: ArrayType, c: Any): Any = ast
    override def visitArrayPointerType(ast:ArrayPointerType, c: Any): Any = ast
    	
}
