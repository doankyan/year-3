import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class CheckerSuite extends FunSuite with TestChecker {
  test("Var and Func Decl") {
    val input = """int a;
                   int main( int r) {
                    int m;
                    {
                      int p;
                    }
                   }
                   void func(int a, boolean a){
                    int b;
                   }
                """
    val expected = """List[Symbol]()"""
    assert(checkCkr(input,expected,401))
}
  test("Var and Func Decl 2") {
    val input = """int a,b,c;
                    float a;
                """
    val expected = """List[Symbol]()"""
    assert(checkCkr(input,expected,402))
}
}