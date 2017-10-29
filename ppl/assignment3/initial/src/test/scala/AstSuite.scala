import org.scalatest.FunSuite
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class AstSuite extends FunSuite with TestAst {
  test("a simple program with void as return type of main") {
    val input = "void main () {}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
  assert(checkAst(input,expected,201))
  }

  test("2 function declare in program") {
    val input = "void main () {} void main (){}"
    val expected = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List())),FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,202))
  }

  test("a simple program with var declare") {
    val input = "int a,b;"
    val expected = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)))
    assert(checkAst(input,expected,203))
  }

  test("a simple program with function has parameter") {
    val input = "void main (int a, int b) {}"
    val expected = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,204))
  }

  test("single var declare and single function declare"){
    val input = "int a;void main () {}"
    val expected =Program(List(VarDecl(Id("a"),IntType),FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expected,205))
  }

  test("function has var declare") {
    val input ="""void main () {
                    int a;
                  }"""
    val expect =Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("a"),IntType)),List()))))
    assert(checkAst(input,expect,206))
  }

  test("function has break") {
    val input ="""void main () {
                    break;
                }"""
    val expect =Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Break)))))
    assert(checkAst(input,expect,207))
  }

  test("simple fuction with array pointer type") {
    val input ="""int[] main () {}"""
    val expect =Program(List(FuncDecl(Id("main"),List(),ArrayPointerType(IntType),Block(List(),List()))))
    assert(checkAst(input,expect,208))
  }

  test("simple main function with expression statement"){
    val input = """void main () {
                      a + 3;
                }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("+",Id("a"),IntLiteral(3)))))))
    assert(checkAst(input,expect,209))
  }

  test("a simple program with complicated var declare") {
    val input = "int f[3],b,a,e[5],d; string str;"
    val expected = Program(List(VarDecl(Id("f"),ArrayType(IntLiteral(3),IntType)),VarDecl(Id("b"),IntType),VarDecl(Id("a"),IntType),VarDecl(Id("e"),ArrayType(IntLiteral(5),IntType)),VarDecl(Id("d"),IntType),VarDecl(Id("str"),StringType)))
    assert(checkAst(input,expected,210))
  }

  test("function declare with parameter and var declaration"){
    val input = """boolean[] main (string d[]) {
                      int a;
                      float b,c[4];
                }"""
    val expect = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("d"),ArrayPointerType(StringType))),ArrayPointerType(BoolType),Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),FloatType),VarDecl(Id("c"),ArrayType(IntLiteral(4),FloatType))),List()))))
    assert(checkAst(input,expect,211))
  }

  test("test less and equal expression"){
    val input = """void main () {
                      a<b==c;
                }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("==",BinaryOp("<",Id("a"),Id("b")),Id("c")))))))
    assert(checkAst(input,expect,212))
  }

  test("complicated expression statement"){
    val input = "void main () {a*b+c<d||e/d>f;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("||",BinaryOp("<",BinaryOp("+",BinaryOp("*",Id("a"),Id("b")),Id("c")),Id("d")),BinaryOp(">",BinaryOp("/",Id("e"),Id("d")),Id("f"))))))))
    assert(checkAst(input,expect,213))
  }

  test("if-test (simple)"){
    val input = """void main () {
                      if (a=3) a=4;
                }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",Id("a"),IntLiteral(3)),BinaryOp("=",Id("a"),IntLiteral(4)),None))))))
    assert(checkAst(input,expect,214))
  }

  test("program with vardeclare mix if statement"){
    val input = """void main () {
      boolean b;
      int i,j[5];
      i=3;
      if (i>0) putInt(i);
    }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("b"),BoolType),VarDecl(Id("i"),IntType),VarDecl(Id("j"),ArrayType(IntLiteral(5),IntType))),List(BinaryOp("=",Id("i"),IntLiteral(3)),If(BinaryOp(">",Id("i"),IntLiteral(0)),CallExpr(Id("putInt"),List(Id("i"))),None))))))
    assert(checkAst(input,expect,215))
  }

  test("test simple index expression"){
    val input = """void main () {
      foo(2)[3+x] = a[b[2]]+3;
    }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",ArrayCell(CallExpr(Id("foo"),List(IntLiteral(2))),BinaryOp("+",IntLiteral(3),Id("x"))),BinaryOp("+",ArrayCell(Id("a"),ArrayCell(Id("b"),IntLiteral(2))),IntLiteral(3))))))))
    assert(checkAst(input,expect,216))
  }

  test("if-else statement test (simple)"){
    val input = """void main () {
      if(a=3)
        a+1;
      else
        a+2;
    }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",Id("a"),IntLiteral(3)),BinaryOp("+",Id("a"),IntLiteral(1)),Some(BinaryOp("+",Id("a"),IntLiteral(2)))))))))
    assert(checkAst(input,expect,217))
  }

  test("test if-else extend : if-statement in if part"){
    val input = """void main () {
        if (a=b) {if(b=c) a=1;} else b=1; }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",Id("a"),Id("b")),Block(List(),List(If(BinaryOp("=",Id("b"),Id("c")),BinaryOp("=",Id("a"),IntLiteral(1)),None))),Some(BinaryOp("=",Id("b"),IntLiteral(1)))))))))
    assert(checkAst(input,expect,218))
  }

  test("test if-else extend : if-statement in else part"){
    val input = """void main () {
        if (a=b) a=1; else if (b=c) c=1;
    }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",Id("a"),IntLiteral(1)),Some(If(BinaryOp("=",Id("b"),Id("c")),BinaryOp("=",Id("c"),IntLiteral(1)),None))))))))
    assert(checkAst(input,expect,219))
  }

  test("an if-else statement that has complicated expression") {
    val input =
      """void main () {
      if(get(3)-vector[3]+(4-5||str[def[g]<g[9]]))
        "abc";
      else
        true;
    }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("+",BinaryOp("-",CallExpr(Id("get"),List(IntLiteral(3))),ArrayCell(Id("vector"),IntLiteral(3))),BinaryOp("||",BinaryOp("-",IntLiteral(4),IntLiteral(5)),ArrayCell(Id("str"),BinaryOp("<",ArrayCell(Id("def"),Id("g")),ArrayCell(Id("g"),IntLiteral(9)))))),StringLiteral("abc"),Some(BooleanLiteral(true))))))))
    assert(checkAst(input, expect, 220))
  }

  test("test nested if-else statement : if part"){
    val input = """void main () {
        if (a=b) if(b=c) b=1; else c=1; else a=1;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",Id("a"),Id("b")),If(BinaryOp("=",Id("b"),Id("c")),BinaryOp("=",Id("b"),IntLiteral(1)),Some(BinaryOp("=",Id("c"),IntLiteral(1)))),Some(BinaryOp("=",Id("a"),IntLiteral(1)))))))))
    assert(checkAst(input,expect,221))
  }

  test("test nested if-else statement : else part"){
    val input = """void main () {
        if (true) a=1; else if ("123") b=1; else c=1;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BooleanLiteral(true),BinaryOp("=",Id("a"),IntLiteral(1)),Some(If(StringLiteral("123"),BinaryOp("=",Id("b"),IntLiteral(1)),Some(BinaryOp("=",Id("c"),IntLiteral(1)))))))))))
    assert(checkAst(input,expect,222))
  }

  test("nested if statement"){
    val input = """void main () {
        if (1+arr[3]) if (false) c=1;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("+",IntLiteral(1),ArrayCell(Id("arr"),IntLiteral(3))),If(BooleanLiteral(false),BinaryOp("=",Id("c"),IntLiteral(1)),None),None))))))
    assert(checkAst(input,expect,223))
  }

  test("if-else statement in if"){
    val input = """void main () {
        if (1.2==.2) {if(b=c) a=1; else b=1;}}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("==",FloatLiteral(1.2.toFloat),FloatLiteral(0.2.toFloat)),Block(List(),List(If(BinaryOp("=",Id("b"),Id("c")),BinaryOp("=",Id("a"),IntLiteral(1)),Some(BinaryOp("=",Id("b"),IntLiteral(1)))))),None))))))
    assert(checkAst(input,expect,224))
  }

  test("test simple do while statement"){
    val input = """int main () {do a+1; b[4]*2; "c"-3; d/4; while a=1;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(BinaryOp("+",Id("a"),IntLiteral(1)),BinaryOp("*",ArrayCell(Id("b"),IntLiteral(4)),IntLiteral(2)),BinaryOp("-",StringLiteral("c"),IntLiteral(3)),BinaryOp("/",Id("d"),IntLiteral(4))),BinaryOp("=",Id("a"),IntLiteral(1))))))))
    assert(checkAst(input,expect,225))
  }

  test("test do while statement with complicated expression part"){
    val input = """int main () {do 1; true; "c"; d; while (size[b[f]]*1.6/abc[3-9%f])*3;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(Dowhile(List(IntLiteral(1),BooleanLiteral(true),StringLiteral("c"),Id("d")),BinaryOp("*",BinaryOp("/",BinaryOp("*",ArrayCell(Id("size"),ArrayCell(Id("b"),Id("f"))),FloatLiteral(1.6.toFloat)),ArrayCell(Id("abc"),BinaryOp("-",IntLiteral(3),BinaryOp("%",IntLiteral(9),Id("f"))))),IntLiteral(3))))))))
    assert(checkAst(input,expect,226))
  }

  test("if statement in do while"){
    val input = "void main () {do if(1-b) a<1; b>2; while a;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(If(BinaryOp("-",IntLiteral(1),Id("b")),BinaryOp("<",Id("a"),IntLiteral(1)),None),BinaryOp(">",Id("b"),IntLiteral(2))),Id("a")))))))
    assert(checkAst(input,expect,227))
  }

  test("if-else statement in do while"){
    val input = "string main (boolean x) {do if (a>3) a+1; else a+2; 1+1; 1; while a[3]=0;}"
    val expect = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("x"),BoolType)),StringType,Block(List(),List(Dowhile(List(If(BinaryOp(">",Id("a"),IntLiteral(3)),BinaryOp("+",Id("a"),IntLiteral(1)),Some(BinaryOp("+",Id("a"),IntLiteral(2)))),BinaryOp("+",IntLiteral(1),IntLiteral(1)),IntLiteral(1)),BinaryOp("=",ArrayCell(Id("a"),IntLiteral(3)),IntLiteral(0))))))))
    assert(checkAst(input,expect,228))
  }

  test("nested do while statement testing"){
    val input = "void main () {do do 1;2;3;4; while 0=0; a=1; while 3=3;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(Dowhile(List(IntLiteral(1),IntLiteral(2),IntLiteral(3),IntLiteral(4)),BinaryOp("=",IntLiteral(0),IntLiteral(0))),BinaryOp("=",Id("a"),IntLiteral(1))),BinaryOp("=",IntLiteral(3),IntLiteral(3))))))))
    assert(checkAst(input,expect,229))
  }

  test("do while statement in if"){
    val input = "void main () {if (1>2) do 1+1; while 1<2;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp(">",IntLiteral(1),IntLiteral(2)),Dowhile(List(BinaryOp("+",IntLiteral(1),IntLiteral(1))),BinaryOp("<",IntLiteral(1),IntLiteral(2))),None))))))
    assert(checkAst(input,expect,230))
  }

  test("do while statement in if-else"){
    val input = "void main () {if (0==0) do 1/1; while 3>1; else do 1*1; while 1<3;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("==",IntLiteral(0),IntLiteral(0)),Dowhile(List(BinaryOp("/",IntLiteral(1),IntLiteral(1))),BinaryOp(">",IntLiteral(3),IntLiteral(1))),Some(Dowhile(List(BinaryOp("*",IntLiteral(1),IntLiteral(1))),BinaryOp("<",IntLiteral(1),IntLiteral(3))))))))))
    assert(checkAst(input,expect,231))
  }

  test("simple testcase for statement"){
    val input = "void main () {for (a;1;true)foo(3);}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(Id("a"),IntLiteral(1),BooleanLiteral(true),CallExpr(Id("foo"),List(IntLiteral(3)))))))))
    assert(checkAst(input,expect,232))
  }

  test("test for statement has complicated expression"){
    val input = """float[] main (int b, string str[]) {for (a+b-c*d/e;1<=4==2;true&&false)println(3);}"""
    val expect = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("b"),IntType),VarDecl(Id("str"),ArrayPointerType(StringType))),ArrayPointerType(FloatType),Block(List(),List(For(BinaryOp("-",BinaryOp("+",Id("a"),Id("b")),BinaryOp("/",BinaryOp("*",Id("c"),Id("d")),Id("e"))),BinaryOp("==",BinaryOp("<=",IntLiteral(1),IntLiteral(4)),IntLiteral(2)),BinaryOp("&&",BooleanLiteral(true),BooleanLiteral(false)),CallExpr(Id("println"),List(IntLiteral(3)))))))))
    assert(checkAst(input,expect,233))
  }

  test("if statement in for statement"){
    val input = """void main () {for(1.2e-1;1;"c")if(a=b)a=1;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(FloatLiteral(1.2e-1.toFloat),IntLiteral(1),StringLiteral("c"),If(BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",Id("a"),IntLiteral(1)),None)))))))
    assert(checkAst(input,expect,234))
  }

  test("if-else statement in for statement"){
    val input = "void main () {for(1.;.2;.3e4)if(a=b)a=1;else a=2;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(FloatLiteral(1.0.toFloat),FloatLiteral(0.2.toFloat),FloatLiteral(0.3e4.toFloat),If(BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",Id("a"),IntLiteral(1)),Some(BinaryOp("=",Id("a"),IntLiteral(2))))))))))
    assert(checkAst(input,expect,235))
  }

  test("do while statement in for statement"){
    val input = "void main () {for(a[3];b[c[3]];-1)do 1; 3; while 3=!3;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(ArrayCell(Id("a"),IntLiteral(3)),ArrayCell(Id("b"),ArrayCell(Id("c"),IntLiteral(3))),UnaryOp("-",IntLiteral(1)),Dowhile(List(IntLiteral(1),IntLiteral(3)),BinaryOp("=",IntLiteral(3),UnaryOp("!",IntLiteral(3))))))))))
    assert(checkAst(input,expect,236))
  }

  test("for statement in if statement"){
    val input = "int main () {if (3==3) for(a;b;c) 3+3;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),IntType,Block(List(),List(If(BinaryOp("==",IntLiteral(3),IntLiteral(3)),For(Id("a"),Id("b"),Id("c"),BinaryOp("+",IntLiteral(3),IntLiteral(3))),None))))))
    assert(checkAst(input,expect,237))
  }

  test("for statement in if-else statement"){
    val input = """void main () {if(check(0)) for(true;false;-1-2+!2) print("Hi"); else for("x";"y";"z") 0;  }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(CallExpr(Id("check"),List(IntLiteral(0))),For(BooleanLiteral(true),BooleanLiteral(false),BinaryOp("+",BinaryOp("-",UnaryOp("-",IntLiteral(1)),IntLiteral(2)),UnaryOp("!",IntLiteral(2))),CallExpr(Id("print"),List(StringLiteral("Hi")))),Some(For(StringLiteral("x"),StringLiteral("y"),StringLiteral("z"),IntLiteral(0)))))))))
    assert(checkAst(input,expect,238))
  }

  test("for in do while statement"){
    val input = "void main () {for(a=1;b=2;c=3) do 1+1; a+b; while a*a<=3;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(BinaryOp("=",Id("a"),IntLiteral(1)),BinaryOp("=",Id("b"),IntLiteral(2)),BinaryOp("=",Id("c"),IntLiteral(3)),Dowhile(List(BinaryOp("+",IntLiteral(1),IntLiteral(1)),BinaryOp("+",Id("a"),Id("b"))),BinaryOp("<=",BinaryOp("*",Id("a"),Id("a")),IntLiteral(3)))))))))
    assert(checkAst(input,expect,239))
  }

  test("test nested for statement"){
    val input = "void main () {for(a<3;b>0;a=-2.26) for(1;2;3) a+b;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(BinaryOp("<",Id("a"),IntLiteral(3)),BinaryOp(">",Id("b"),IntLiteral(0)),BinaryOp("=",Id("a"),UnaryOp("-",FloatLiteral(2.26.toFloat))),For(IntLiteral(1),IntLiteral(2),IntLiteral(3),BinaryOp("+",Id("a"),Id("b")))))))))
    assert(checkAst(input,expect,240))
  }

  test("test many break statements"){
    val input = "int main (string a, int arg[]) {int a,b; break; a=1; break; b=2; break;}"
    val expect = Program(List(FuncDecl(Id("main"),List(VarDecl(Id("a"),StringType),VarDecl(Id("arg"),ArrayPointerType(IntType))),IntType,Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(Break,BinaryOp("=",Id("a"),IntLiteral(1)),Break,BinaryOp("=",Id("b"),IntLiteral(2)),Break)))))
    assert(checkAst(input,expect,241))
  }

  test("break statement in for statement"){
    val input = "string fbi () {for (1;2;3) break;}"
    val expect = Program(List(FuncDecl(Id("fbi"),List(),StringType,Block(List(),List(For(IntLiteral(1),IntLiteral(2),IntLiteral(3),Break))))))
    assert(checkAst(input,expect,242))
  }

  test("break statement in do while statement"){
    val input = "float break_test () {do break; break; break; while true;}"
    val expect = Program(List(FuncDecl(Id("break_test"),List(),FloatType,Block(List(),List(Dowhile(List(Break,Break,Break),BooleanLiteral(true)))))))
    assert(checkAst(input,expect,243))
  }

  test("break statement in if statement"){
    val input = "void main () {if(false)break;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BooleanLiteral(false),Break,None))))))
    assert(checkAst(input,expect,244))
  }

  test("break statement in if-else statement"){
    val input = """void main () {if ("string") break; else break;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(StringLiteral("string"),Break,Some(Break)))))))
    assert(checkAst(input,expect,245))
  }

  test("test simple continue statement"){
    val input = "void main () {continue;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Continue)))))
    assert(checkAst(input,expect,246))
  }

  test("test multiple continue statements"){
    val input = "void main () {continue; sum(x,y); continue;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Continue,CallExpr(Id("sum"),List(Id("x"),Id("y"))),Continue)))))
    assert(checkAst(input,expect,247))
  }

  test("continue statement in for statement"){
    val input = """void main () {for ("string"+1;2||3;3[2]) continue;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(BinaryOp("+",StringLiteral("string"),IntLiteral(1)),BinaryOp("||",IntLiteral(2),IntLiteral(3)),ArrayCell(IntLiteral(3),IntLiteral(2)),Continue))))))
    assert(checkAst(input,expect,248))
  }

  test("continue statement in do while statement"){
    val input = "void main () {do continue; continue; while 0;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(Continue,Continue),IntLiteral(0)))))))
    assert(checkAst(input,expect,249))
  }

  test("continue statement in if statement"){
    val input = "void main () {if(0)continue;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(IntLiteral(0),Continue,None))))))
    assert(checkAst(input,expect,250))
  }

  test("continue statement in if-else statement"){
    val input = """void main () {if ("string"-a[2]) continue; else continue;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("-",StringLiteral("string"),ArrayCell(Id("a"),IntLiteral(2))),Continue,Some(Continue)))))))
    assert(checkAst(input,expect,251))
  }

  test("test simple return"){
    val input = "void main () {return;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Return(None))))))
    assert(checkAst(input,expect,252))
  }

  test("another simple test return with expression"){
    val input = "void main () {return 1+1;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Return(Some(BinaryOp("+",IntLiteral(1),IntLiteral(1)))))))))
    assert(checkAst(input,expect,253))
  }

  test("return statement in if statement"){
    val input = "void main () {if(-1)return;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(UnaryOp("-",IntLiteral(1)),Return(None),None))))))
    assert(checkAst(input,expect,254))
  }

  test("return statement in if-else statement"){
    val input = """void main () {if(a)return 1;else return "hello";}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(Id("a"),Return(Some(IntLiteral(1))),Some(Return(Some(StringLiteral("hello"))))))))))
    assert(checkAst(input,expect,255))
  }

  test("return statement in do-while statement"){
    val input = "void main () {do return 0; while a==a;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(Return(Some(IntLiteral(0)))),BinaryOp("==",Id("a"),Id("a"))))))))
    assert(checkAst(input,expect,256))
  }

  test("return statement in for statement"){
    val input = """void main () {for(1=1;1-2;a="string") return happy(3);}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(BinaryOp("=",IntLiteral(1),IntLiteral(1)),BinaryOp("-",IntLiteral(1),IntLiteral(2)),BinaryOp("=",Id("a"),StringLiteral("string")),Return(Some(CallExpr(Id("happy"),List(IntLiteral(3)))))))))))
    assert(checkAst(input,expect,257))
  }

  test("test multi expression statement and funcall expression"){
    val input = """void main (){i=1;foo(1,2);del(stack[1],(stack[0]+1));100;mul("a","b","c");}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",Id("i"),IntLiteral(1)),CallExpr(Id("foo"),List(IntLiteral(1),IntLiteral(2))),CallExpr(Id("del"),List(ArrayCell(Id("stack"),IntLiteral(1)),BinaryOp("+",ArrayCell(Id("stack"),IntLiteral(0)),IntLiteral(1)))),IntLiteral(100),CallExpr(Id("mul"),List(StringLiteral("a"),StringLiteral("b"),StringLiteral("c"))))))))
    assert(checkAst(input,expect,258))
  }

  test("test simple block statement with only var declarations"){
    val input = "void main () {{int a,b,c; float f[5];}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),IntType),VarDecl(Id("f"),ArrayType(IntLiteral(5),FloatType))),List()))))))
    assert(checkAst(input,expect,259))
  }

  test("test simple block statement with only statements"){
    val input = "void main () {{a=b=2;if(a=b) f[0]=1.0;}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("=",Id("b"),IntLiteral(2))),If(BinaryOp("=",Id("a"),Id("b")),BinaryOp("=",ArrayCell(Id("f"),IntLiteral(0)),FloatLiteral(1.0.toFloat)),None))))))))
    assert(checkAst(input,expect,260))
  }

  test("test full block statement"){
    val input = "void main () {{int a,b; a+b=1;}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType)),List(BinaryOp("=",BinaryOp("+",Id("a"),Id("b")),IntLiteral(1)))))))))
    assert(checkAst(input,expect,261))
  }

  test("test many block statements"){
    val input = "void main () {{int x[2],y; -x[2]*b=2;}{break ;}{string f; return f[2]/f[1];}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(VarDecl(Id("x"),ArrayType(IntLiteral(2),IntType)),VarDecl(Id("y"),IntType)),List(BinaryOp("=",BinaryOp("*",UnaryOp("-",ArrayCell(Id("x"),IntLiteral(2))),Id("b")),IntLiteral(2)))),Block(List(),List(Break)),Block(List(VarDecl(Id("f"),StringType)),List(Return(Some(BinaryOp("/",ArrayCell(Id("f"),IntLiteral(2)),ArrayCell(Id("f"),IntLiteral(1))))))))))))
    assert(checkAst(input,expect,262))
  }

  test("block statement in if statement"){
    val input = "void main () {if(1=1){a=1;b=2;}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("=",IntLiteral(1),IntLiteral(1)),Block(List(),List(BinaryOp("=",Id("a"),IntLiteral(1)),BinaryOp("=",Id("b"),IntLiteral(2)))),None))))))
    assert(checkAst(input,expect,263))
  }

  test("block statement in if-else statement"){
    val input = "void main () {if (true) {string a; a=1;} else {float b; b=2;}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BooleanLiteral(true),Block(List(VarDecl(Id("a"),StringType)),List(BinaryOp("=",Id("a"),IntLiteral(1)))),Some(Block(List(VarDecl(Id("b"),FloatType)),List(BinaryOp("=",Id("b"),IntLiteral(2)))))))))))
    assert(checkAst(input,expect,264))
  }

  test("block statement in for statement"){
    val input = "void main () {for (1;3;4) {a+1;b+2;c+3;}}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(IntLiteral(1),IntLiteral(3),IntLiteral(4),Block(List(),List(BinaryOp("+",Id("a"),IntLiteral(1)),BinaryOp("+",Id("b"),IntLiteral(2)),BinaryOp("+",Id("c"),IntLiteral(3))))))))))
    assert(checkAst(input,expect,265))
  }

  test("block statement in do while statement"){
    val input = "void main () {do {3+3;} {} {a;} while true;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(Block(List(),List(BinaryOp("+",IntLiteral(3),IntLiteral(3)))),Block(List(),List()),Block(List(),List(Id("a")))),BooleanLiteral(true)))))))
    assert(checkAst(input,expect,266))
  }

  test("test simple bracket and index expression"){
    val input = "void main () {(3=1)[3=1];}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(ArrayCell(BinaryOp("=",IntLiteral(3),IntLiteral(1)),BinaryOp("=",IntLiteral(3),IntLiteral(1))))))))
    assert(checkAst(input,expect,267))
  }

  test("test complex bracket and index expression"){
    val input = "void main () {(3==1)[a*(2+3)-3<3];}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(ArrayCell(BinaryOp("==",IntLiteral(3),IntLiteral(1)),BinaryOp("<",BinaryOp("-",BinaryOp("*",Id("a"),BinaryOp("+",IntLiteral(2),IntLiteral(3))),IntLiteral(3)),IntLiteral(3))))))))
    assert(checkAst(input,expect,268))
  }

  test("test string operation"){
    val input = """void main () {"string"+"khang";}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("+",StringLiteral("string"),StringLiteral("khang")))))))
    assert(checkAst(input,expect,269))
  }

  test("declaration complicated"){
    val input =
      """int[] print(float e, float f[]){}
         int a,b,c[3];
         void main(){}
         string x,y;
      """
    val expect = Program(List(FuncDecl(Id("print"),List(VarDecl(Id("e"),FloatType),VarDecl(Id("f"),ArrayPointerType(FloatType))),ArrayPointerType(IntType),Block(List(),List())),VarDecl(Id("a"),IntType),VarDecl(Id("b"),IntType),VarDecl(Id("c"),ArrayType(IntLiteral(3),IntType)),FuncDecl(Id("main"),List(),VoidType,Block(List(),List())),VarDecl(Id("x"),StringType),VarDecl(Id("y"),StringType)))
    assert(checkAst(input,expect,270))
  }

  test ("test all kind of statement"){
    val input = """
      void main (){
        if (0) 1-1;
        if (true) "hi"; else "hello";
        for (a[0];"abc";false) 1.0+0.345;
        do "123" + 123 ; while g[h[(1*4*6.0)]];
        return 0;
        break;
        continue;
      }
    """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(IntLiteral(0),BinaryOp("-",IntLiteral(1),IntLiteral(1)),None),If(BooleanLiteral(true),StringLiteral("hi"),Some(StringLiteral("hello"))),For(ArrayCell(Id("a"),IntLiteral(0)),StringLiteral("abc"),BooleanLiteral(false),BinaryOp("+",FloatLiteral(1.0.toFloat),FloatLiteral(0.345.toFloat))),Dowhile(List(BinaryOp("+",StringLiteral("123"),IntLiteral(123))),ArrayCell(Id("g"),ArrayCell(Id("h"),BinaryOp("*",BinaryOp("*",IntLiteral(1),IntLiteral(4)),FloatLiteral(6.0.toFloat))))),Return(Some(IntLiteral(0))),Break,Continue)))))
    assert(checkAst(input,expect,271))
  }

  test("test special string "){
    val input = """void main () {"\n"-"\b";}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("-",StringLiteral("""\n"""),StringLiteral("""\b""")))))))
    assert(checkAst(input,expect,272))
  }

  test("test expression: bracket and assign"){
    val input = "void main () {(a==b)=c;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",BinaryOp("==",Id("a"),Id("b")),Id("c")))))))
    assert(checkAst(input,expect,273))
  }

  test("test expression: index and assign"){
    val input = "void main () {a[1+2*3]==c;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("==",ArrayCell(Id("a"),BinaryOp("+",IntLiteral(1),BinaryOp("*",IntLiteral(2),IntLiteral(3)))),Id("c")))))))
    assert(checkAst(input,expect,274))
  }

  test("test expression: bracket complex"){
    val input = "void main () {((9<3)>3+3*2==a)[1];}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(ArrayCell(BinaryOp("==",BinaryOp(">",BinaryOp("<",IntLiteral(9),IntLiteral(3)),BinaryOp("+",IntLiteral(3),BinaryOp("*",IntLiteral(3),IntLiteral(2)))),Id("a")),IntLiteral(1)))))))
    assert(checkAst(input,expect,275))
  }

  test("all decalre below main"){
    val input = "void main () {} int a; string a; string[]substring(){}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List())),VarDecl(Id("a"),IntType),VarDecl(Id("a"),StringType),FuncDecl(Id("substring"),List(),ArrayPointerType(StringType),Block(List(),List()))))
    assert(checkAst(input,expect,276))
  }

  test("a function that parameter is pointer type"){
    val input = "int sum(int a[],float b [], string c[], boolean d[]){}"
    val expect = Program(List(FuncDecl(Id("sum"),List(VarDecl(Id("a"),ArrayPointerType(IntType)),VarDecl(Id("b"),ArrayPointerType(FloatType)),VarDecl(Id("c"),ArrayPointerType(StringType)),VarDecl(Id("d"),ArrayPointerType(BoolType))),IntType,Block(List(),List()))))
    assert(checkAst(input,expect,277))
  }

  test("test equal operator"){
    val input = "void main () {a=b==c=d; a=b==c=d==e;}"
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("=",Id("a"),BinaryOp("=",BinaryOp("==",Id("b"),Id("c")),Id("d"))),BinaryOp("=",Id("a"),BinaryOp("=",BinaryOp("==",Id("b"),Id("c")),BinaryOp("==",Id("d"),Id("e")))))))))
    assert(checkAst(input,expect,278))
  }

  test("index expression extend"){
    val input = """void main () {3[a+1]; true[1.2]; "arr"[true]; 2.3e4["pointer"]; }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(ArrayCell(IntLiteral(3),BinaryOp("+",Id("a"),IntLiteral(1))),ArrayCell(BooleanLiteral(true),FloatLiteral(1.2.toFloat)),ArrayCell(StringLiteral("arr"),BooleanLiteral(true)),ArrayCell(FloatLiteral(23000.toFloat),StringLiteral("pointer")))))))
    assert(checkAst(input,expect,279))
  }

  test("test line comment"){
    val input = """//This is main
void main () {}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expect,280))
  }

  test("test block comment"){
    val input = """/*This is main*/
void main () {}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expect,281))
  }

  test("bracket expression extend"){
    val input = """void main () {(true);(true+false);("int");((0.2)[2]); }"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BooleanLiteral(true),BinaryOp("+",BooleanLiteral(true),BooleanLiteral(false)),StringLiteral("int"),ArrayCell(FloatLiteral(0.2.toFloat),IntLiteral(2)))))))
    assert(checkAst(input,expect,282))
  }

  test("Unary expression"){
    val input = """void main () {--1; !!2.0; -!true; !-"una"; --!-!-!!!-a;}"""
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(UnaryOp("-",UnaryOp("-",IntLiteral(1))),UnaryOp("!",UnaryOp("!",FloatLiteral(2.0.toFloat))),UnaryOp("-",UnaryOp("!",BooleanLiteral(true))),UnaryOp("!",UnaryOp("-",StringLiteral("una"))),UnaryOp("-",UnaryOp("-",UnaryOp("!",UnaryOp("-",UnaryOp("!",UnaryOp("-",UnaryOp("!",UnaryOp("!",UnaryOp("!",UnaryOp("-",Id("a"))))))))))))))))
    assert(checkAst(input,expect,283))
  }

  test("All kind of vardeclare"){
    val input =
      """
        int a,b[3];
        float c,d[4];
        string e,f[5];
        boolean x,y[6];

      """
    val expect = Program(List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayType(IntLiteral(3),IntType)),VarDecl(Id("c"),FloatType),VarDecl(Id("d"),ArrayType(IntLiteral(4),FloatType)),VarDecl(Id("e"),StringType),VarDecl(Id("f"),ArrayType(IntLiteral(5),StringType)),VarDecl(Id("x"),BoolType),VarDecl(Id("y"),ArrayType(IntLiteral(6),BoolType))))
    assert(checkAst(input,expect,284))
  }

  test("simple function declare - simple type: no parameter and body "){
    val input =
      """
        int cal (){}
        float mul (){}
        string sep (){}
        boolean check (){}
        void main () {}
      """
    val expect = Program(List(FuncDecl(Id("cal"),List(),IntType,Block(List(),List())),FuncDecl(Id("mul"),List(),FloatType,Block(List(),List())),FuncDecl(Id("sep"),List(),StringType,Block(List(),List())),FuncDecl(Id("check"),List(),BoolType,Block(List(),List())),FuncDecl(Id("main"),List(),VoidType,Block(List(),List()))))
    assert(checkAst(input,expect,285))
  }

  test("simple function declare - array pointer type: no parameter and body "){
    val input =
      """
        int[] cal (){}
        float[] mul (){}
        string[] sep (){}
        boolean[] check (){}
      """
    val expect = Program(List(FuncDecl(Id("cal"),List(),ArrayPointerType(IntType),Block(List(),List())),FuncDecl(Id("mul"),List(),ArrayPointerType(FloatType),Block(List(),List())),FuncDecl(Id("sep"),List(),ArrayPointerType(StringType),Block(List(),List())),FuncDecl(Id("check"),List(),ArrayPointerType(BoolType),Block(List(),List()))))
    assert(checkAst(input,expect,286))
  }

  test("full function declare - all simple type"){
    val input =
      """
        int sum (int a, float b[]){int c; return a+b[0]+c;}
        float cal (float x, int y[]){continue;}
        string connect (string head, string tail[]){break;}
        boolean check(boolean a){if(a)a=true;}
        void main (string str[], boolean boolit[]) {return;}
      """
    val expect = Program(List(FuncDecl(Id("sum"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayPointerType(FloatType))),IntType,Block(List(VarDecl(Id("c"),IntType)),List(Return(Some(BinaryOp("+",BinaryOp("+",Id("a"),ArrayCell(Id("b"),IntLiteral(0))),Id("c"))))))),FuncDecl(Id("cal"),List(VarDecl(Id("x"),FloatType),VarDecl(Id("y"),ArrayPointerType(IntType))),FloatType,Block(List(),List(Continue))),FuncDecl(Id("connect"),List(VarDecl(Id("head"),StringType),VarDecl(Id("tail"),ArrayPointerType(StringType))),StringType,Block(List(),List(Break))),FuncDecl(Id("check"),List(VarDecl(Id("a"),BoolType)),BoolType,Block(List(),List(If(Id("a"),BinaryOp("=",Id("a"),BooleanLiteral(true)),None)))),FuncDecl(Id("main"),List(VarDecl(Id("str"),ArrayPointerType(StringType)),VarDecl(Id("boolit"),ArrayPointerType(BoolType))),VoidType,Block(List(),List(Return(None))))))
    assert(checkAst(input,expect,287))
  }

  test("full function declare - array pointer type"){
    val input =
      """
        int[] div (int a, float b[]){if (a=b) a/0; else b*0;}
        float[] modul (float x, int y[]){for (x;y;0) x+y*0;}
        string[] seperate (string head, string tail[]){do "head"-"tail"; while "head"<"tail"[0];}
        boolean[] check(boolean a){if(a)a=true;}
      """
    val expect = Program(List(FuncDecl(Id("div"),List(VarDecl(Id("a"),IntType),VarDecl(Id("b"),ArrayPointerType(FloatType))),ArrayPointerType(IntType),Block(List(),List(If(BinaryOp("=",Id("a"),Id("b")),BinaryOp("/",Id("a"),IntLiteral(0)),Some(BinaryOp("*",Id("b"),IntLiteral(0))))))),FuncDecl(Id("modul"),List(VarDecl(Id("x"),FloatType),VarDecl(Id("y"),ArrayPointerType(IntType))),ArrayPointerType(FloatType),Block(List(),List(For(Id("x"),Id("y"),IntLiteral(0),BinaryOp("+",Id("x"),BinaryOp("*",Id("y"),IntLiteral(0))))))),FuncDecl(Id("seperate"),List(VarDecl(Id("head"),StringType),VarDecl(Id("tail"),ArrayPointerType(StringType))),ArrayPointerType(StringType),Block(List(),List(Dowhile(List(BinaryOp("-",StringLiteral("head"),StringLiteral("tail"))),BinaryOp("<",StringLiteral("head"),ArrayCell(StringLiteral("tail"),IntLiteral(0))))))),FuncDecl(Id("check"),List(VarDecl(Id("a"),BoolType)),ArrayPointerType(BoolType),Block(List(),List(If(Id("a"),BinaryOp("=",Id("a"),BooleanLiteral(true)),None))))))
    assert(checkAst(input,expect,288))
  }

  test("function call simple - 1 or no parameter"){
    val input =
      """
        void main (){
          foo(1);
          check(true);
          print();
          take("abc");
          toInterger(1.2356);
          try(a);
          extend(pointer[0]);
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("foo"),List(IntLiteral(1))),CallExpr(Id("check"),List(BooleanLiteral(true))),CallExpr(Id("print"),List()),CallExpr(Id("take"),List(StringLiteral("abc"))),CallExpr(Id("toInterger"),List(FloatLiteral(1.2356.toFloat))),CallExpr(Id("try"),List(Id("a"))),CallExpr(Id("extend"),List(ArrayCell(Id("pointer"),IntLiteral(0)))))))))
    assert(checkAst(input,expect,289))
  }

  test("function call test  - 2 or more parameter"){
    val input =
      """
        void main (){
          sum(1,2,1.23);
          strcom("str",stringarr[3]);
          checkval(true,false,1,a,"abc",arr[0]);
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("sum"),List(IntLiteral(1),IntLiteral(2),FloatLiteral(1.23.toFloat))),CallExpr(Id("strcom"),List(StringLiteral("str"),ArrayCell(Id("stringarr"),IntLiteral(3)))),CallExpr(Id("checkval"),List(BooleanLiteral(true),BooleanLiteral(false),IntLiteral(1),Id("a"),StringLiteral("abc"),ArrayCell(Id("arr"),IntLiteral(0)))))))))
    assert(checkAst(input,expect,290))
  }

  test("function call in function call"){
    val input =
      """
        void main (){
          test(scan(),detect(list(1,3,node()[3])));
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(CallExpr(Id("test"),List(CallExpr(Id("scan"),List()),CallExpr(Id("detect"),List(CallExpr(Id("list"),List(IntLiteral(1),IntLiteral(3),ArrayCell(CallExpr(Id("node"),List()),IntLiteral(3)))))))))))))
    assert(checkAst(input,expect,291))
  }

  test("test function call in complicated case"){
    val input =
      """
        void main() {
          wait(a,1.4,f[take()+input(ls[0])[0]])[3/a%cont(pre[3])["true"+true]]==0;
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(BinaryOp("==",ArrayCell(CallExpr(Id("wait"),List(Id("a"),FloatLiteral(1.4.toFloat),ArrayCell(Id("f"),BinaryOp("+",CallExpr(Id("take"),List()),ArrayCell(CallExpr(Id("input"),List(ArrayCell(Id("ls"),IntLiteral(0)))),IntLiteral(0)))))),BinaryOp("%",BinaryOp("/",IntLiteral(3),Id("a")),ArrayCell(CallExpr(Id("cont"),List(ArrayCell(Id("pre"),IntLiteral(3)))),BinaryOp("+",StringLiteral("true"),BooleanLiteral(true))))),IntLiteral(0)))))))
    assert(checkAst(input,expect,292))
  }

  test("function call used in if statement"){
    val input =
      """
        void main() {
          if (copy(a,b)==true) println("Done");
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("==",CallExpr(Id("copy"),List(Id("a"),Id("b"))),BooleanLiteral(true)),CallExpr(Id("println"),List(StringLiteral("Done"))),None))))))
    assert(checkAst(input,expect,293))
  }

  test("function call used in if-else statement"){
    val input =
      """
        void main() {
          if (getString(str[0])=="str") println("Finish"); else print();
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(If(BinaryOp("==",CallExpr(Id("getString"),List(ArrayCell(Id("str"),IntLiteral(0)))),StringLiteral("str")),CallExpr(Id("println"),List(StringLiteral("Finish"))),Some(CallExpr(Id("print"),List()))))))))
    assert(checkAst(input,expect,294))
  }

  test("function call used in for statement"){
    val input =
      """
        void main() {
          for (getInt(1,-4,5);getFloat(1.3,3e4,7.3e-3);getString()) exit();
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(For(CallExpr(Id("getInt"),List(IntLiteral(1),UnaryOp("-",IntLiteral(4)),IntLiteral(5))),CallExpr(Id("getFloat"),List(FloatLiteral(1.3.toFloat),FloatLiteral(30000.0.toFloat),FloatLiteral(0.0073.toFloat))),CallExpr(Id("getString"),List()),CallExpr(Id("exit"),List())))))))
    assert(checkAst(input,expect,295))
  }

  test("function call used in do while statement"){
    val input =
      """
        void main() {
          do rec()+res(); find(lowest(1,2,4))-find(highest("215")); while cond()!= true;
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Dowhile(List(BinaryOp("+",CallExpr(Id("rec"),List()),CallExpr(Id("res"),List())),BinaryOp("-",CallExpr(Id("find"),List(CallExpr(Id("lowest"),List(IntLiteral(1),IntLiteral(2),IntLiteral(4))))),CallExpr(Id("find"),List(CallExpr(Id("highest"),List(StringLiteral("215"))))))),BinaryOp("!=",CallExpr(Id("cond"),List()),BooleanLiteral(true))))))))
    assert(checkAst(input,expect,296))
  }

  test("function call used in return statement"){
    val input =
      """
        void main() {
          return main();
          return newmain(main());
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Return(Some(CallExpr(Id("main"),List()))),Return(Some(CallExpr(Id("newmain"),List(CallExpr(Id("main"),List()))))))))))
    assert(checkAst(input,expect,297))
  }

  test("function call used in block statement"){
    val input =
      """
        void main() {
          {
            loop();
            change(3,true);
            record("status",initial,result[0]);
          }
          {
            getFinal(abc,57.88);
            toFinal(getFinal(),getFinal(1+2&&3));
          }
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(),List(Block(List(),List(CallExpr(Id("loop"),List()),CallExpr(Id("change"),List(IntLiteral(3),BooleanLiteral(true))),CallExpr(Id("record"),List(StringLiteral("status"),Id("initial"),ArrayCell(Id("result"),IntLiteral(0)))))),Block(List(),List(CallExpr(Id("getFinal"),List(Id("abc"),FloatLiteral(57.88.toFloat))),CallExpr(Id("toFinal"),List(CallExpr(Id("getFinal"),List()),CallExpr(Id("getFinal"),List(BinaryOp("&&",BinaryOp("+",IntLiteral(1),IntLiteral(2)),IntLiteral(3)))))))))))))
    assert(checkAst(input,expect,298))
  }

  test("test complicated program"){
    val input = """int i;
      int f(){
        return 200;
      }
      void main(){
        int main;
        main = f();
        putIntLn(i);
        {
          int i;
          int main;
          int f;
          main = f = i = 100;
          putIntLn(i);
          putIntLn(main);
          putIntLn(f);
        }
        putIntLn(main);
      }"""
    val expect = Program(List(VarDecl(Id("i"),IntType),FuncDecl(Id("f"),List(),IntType,Block(List(),List(Return(Some(IntLiteral(200)))))),FuncDecl(Id("main"),List(),VoidType,Block(List(VarDecl(Id("main"),IntType)),List(BinaryOp("=",Id("main"),CallExpr(Id("f"),List())),CallExpr(Id("putIntLn"),List(Id("i"))),Block(List(VarDecl(Id("i"),IntType),VarDecl(Id("main"),IntType),VarDecl(Id("f"),IntType)),List(BinaryOp("=",Id("main"),BinaryOp("=",Id("f"),BinaryOp("=",Id("i"),IntLiteral(100)))),CallExpr(Id("putIntLn"),List(Id("i"))),CallExpr(Id("putIntLn"),List(Id("main"))),CallExpr(Id("putIntLn"),List(Id("f"))))),CallExpr(Id("putIntLn"),List(Id("main"))))))))
    assert(checkAst(input,expect,299))
  }

  test("another complicated program"){
    val input=
      """
        void main(){
          int n,x,i,j;
          float e,f,cos,temp;
          printf ("Enter value n: \n");
          scanf("%d", n);
          printf ("Enter value e: \n");
          scanf("%f", e);
          for (i= 1;i <= n;i = i + 1)
          {
            temp = 1;
            for (j= 2;j <= i;j = j + 2)
            {
              temp = temp * j;
            }
            cos = cos + (pow(e, i) / temp) * f;
            f = f * (-1);
          }
          printf("Sum of cos = %2.6f\n", cos);
          printf("The value of cos(%d) = %f\n",x,cos(x));
        }
      """
    val expect = Program(List(FuncDecl(Id("main"),List(),VoidType,Block(List(
                        VarDecl(Id("n"),IntType),VarDecl(Id("x"),IntType),VarDecl(Id("i"),IntType),VarDecl(Id("j"),IntType),
                        VarDecl(Id("e"),FloatType),VarDecl(Id("f"),FloatType),VarDecl(Id("cos"),FloatType),VarDecl(Id("temp"),FloatType)),
                        List(
                          CallExpr(Id("printf"),List(StringLiteral("""Enter value n: \n"""))),
                          CallExpr(Id("scanf"),List(StringLiteral("%d"),Id("n"))),
                          CallExpr(Id("printf"),List(StringLiteral("""Enter value e: \n"""))),
                          CallExpr(Id("scanf"),List(StringLiteral("%f"),Id("e"))),
                          For(BinaryOp("=",Id("i"),IntLiteral(1)),BinaryOp("<=",Id("i"),Id("n")),BinaryOp("=",Id("i"),BinaryOp("+",Id("i"),IntLiteral(1))),
                            Block(List(),List(
                                BinaryOp("=",Id("temp"),IntLiteral(1)),
                                For(BinaryOp("=",Id("j"),IntLiteral(2)),BinaryOp("<=",Id("j"),Id("i")),BinaryOp("=",Id("j"),BinaryOp("+",Id("j"),IntLiteral(2))),
                                  Block(List(),List(
                                    BinaryOp("=",Id("temp"),BinaryOp("*",Id("temp"),Id("j")))))),
                                BinaryOp("=",Id("cos"),BinaryOp("+",Id("cos"),BinaryOp("*",BinaryOp("/",CallExpr(Id("pow"),List(Id("e"),Id("i"))),Id("temp")),Id("f")))),
                                BinaryOp("=",Id("f"),BinaryOp("*",Id("f"),UnaryOp("-",IntLiteral(1))))))),
                          CallExpr(Id("printf"),List(StringLiteral("""Sum of cos = %2.6f\n"""),Id("cos"))),
                          CallExpr(Id("printf"),List(StringLiteral("""The value of cos(%d) = %f\n"""),Id("x"),CallExpr(Id("cos"),List(Id("x"))))))))))
    assert(checkAst(input,expect,300))
  }
}