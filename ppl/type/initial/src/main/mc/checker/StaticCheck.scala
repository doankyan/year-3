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

    override def visitProgram(ast: Program, c: Any): Any = 
    ast.decl.foldLeft(List[Symbol]())((L,y) => y match {
        case VarDecl(i,t) => Symbol(i.name, t, null) :: L
        case FuncDecl(i,p,t,b) => Symbol(i.name, FunctionType(p.map(x=>x.varType), t), null) :: L
    })
}
