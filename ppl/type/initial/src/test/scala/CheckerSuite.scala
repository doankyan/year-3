import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class CheckerSuite extends FunSuite with TestChecker {
  test("Var and Func Decl") {
    val input = "int a, b; void main(int a, float b){}"
    val expected = """List(Symbol(main,FunctionType(List(IntType, FloatType),VoidType),null), Symbol(b,IntType,null), Symbol(a,IntType,null))"""
    assert(checkCkr(input,expected,401))
  }  
  test("Empty") {
    val input = "int main(){}"
    val expected = """List(Symbol(main,FunctionType(List(),IntType),null))"""
    assert(checkCkr(input,expected,402))
  }
  test(" Func Decl with param") {
    val input = "int main(int a, int b){}"
    val expected = """List(Symbol(main,FunctionType(List(IntType, IntType),IntType),null))"""
    assert(checkCkr(input,expected,403))
  }
  test("Var Decl") {
    val input = "int a;"
    val expected = """List(Symbol(a,IntType,null))"""
    assert(checkCkr(input,expected,404))
  }
}