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
        //t_Symbols
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
            ast.body.asInstanceOf[Block].stmt.filter(_.isInstanceOf[Block]).map(_.accept(this,env))

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
        ast.stmt.filter(_.isInstanceOf[Block]).map(_.accept(this,t_Symbols::env))
        //return env cu
        env

    }
    	
}
