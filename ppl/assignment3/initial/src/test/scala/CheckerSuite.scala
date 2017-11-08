import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class CheckerSuite extends FunSuite with TestChecker {
  test("1: Redeclared VarDecl Global") {
    val input = """int a,b,a;
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,401))
}
  test("2: Redeclared FuncDecl") {
    val input = """int a;
                    int main(){}
                    int b;
                    void main(){}
                """
    val expected = """Redeclared Function: main"""
    assert(checkCkr(input,expected,402))
}
  test("3: Redeclared Parameter") {
    val input = """int a;
                    int main(int a[], float b, boolean a){}
                    
                """
    val expected = """Redeclared Parameter: a"""
    assert(checkCkr(input,expected,403))
}
  test("4: Redeclared Variable in Function") {
    val input = """int a;
                    int main(int a){
                      int a;
                    }
                    
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,404))
}
  test("5: Redeclared Variable in Block of Function") {
    val input = """int a;
                    int main(int a){
                      {
                        int b,b;
                      }
                    }
                    
                """
    val expected = """Redeclared Variable: b"""
    assert(checkCkr(input,expected,405))
}
 test("6: Redeclared Variable in Block of Function 2") {
    val input = """int a;
                    int main(int a){
                      {
                        int b;
                        {
                          int b,m,b;
                        }
                      }
                    }
                    
                """
    val expected = """Redeclared Variable: b"""
    assert(checkCkr(input,expected,406))
}
  test("TestDemo") {
    val input = """
                  void main(){
                    int a;
                    for(2;true;2) a = 3;
                    return;
                  }
                  
                  
                  """
    val expected = """Type Mismatch In Statement: If(BinaryOp(+,IntLiteral(2),IntLiteral(3)),BinaryOp(=,Id(a),IntLiteral(2)),None)"""
    assert(checkCkr(input,expected,407))
}
}