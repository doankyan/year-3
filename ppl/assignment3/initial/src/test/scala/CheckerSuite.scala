import org.scalatest.FunSuite
import mc.checker._
import mc.utils._

/**
  * Created by nhphung on 4/29/17.
  */
class CheckerSuite extends FunSuite with TestChecker {
  test("1 Redeclared VarDecl Global 1") {
    val input = """int a;
                   string b,a;
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,401))
  }
  test("2 Redeclared VarDecl Global 2") {
    val input = """ int a;
                    boolean b[10];
                    int main(){}
                    float a;
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,402))
  }
  test("3 Redeclared FuncDecl: ") {
    val input = """
                    int a;
                    int main(){ return 1;}
                    float b;
                    float main(){ return 0.1;}
                    
                """
    val expected = """Redeclared Function: main"""
    assert(checkCkr(input,expected,403))
  }
  test("4 Redeclared FuncDecl: int float") {
    val input = """
                    int a;
                    int main(){ return 1;}
                    float b;
                    float main(){ return 1.0;}
                    
                """
    val expected = """Redeclared Function: main"""
    assert(checkCkr(input,expected,404))
  }
  test("5 Redeclared FuncDecl: void and arayType") {
    val input = """
                    int a;
                    void func(){return;}
                    float b;
                    int[] func(){
                      int a[10];
                      return;}
                    
                """
    val expected = """Redeclared Function: func"""
    assert(checkCkr(input,expected,405))
  }
  test("6 Redeclared Parameter: 1") {
    val input = """
                    int a;
                    void func(int a, int b, int a){
                      return;
                    }                    
                """
    val expected = """Redeclared Parameter: a"""
    assert(checkCkr(input,expected,406))
  }
  test("7 Redeclared Parameter: 2") {
    val input = """   
                  int a;
                  void main(string b[], int a, float b){}          
                """
    val expected = """Redeclared Parameter: b"""
    assert(checkCkr(input,expected,407))
  }
  test("8 Redeclared Variable: Variable and Parameter") {
    val input = """   
                  void main(int a){
                    float a,b;
                    return;
                  }          
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,408))
  }
  test("9 Redeclared Variable: Block") {
    val input = """ void main(int a){
                    float a,b;
                    return;
                    }     
                """
    val expected = """Redeclared Variable: a"""
    assert(checkCkr(input,expected,409))
  }
  test("10 No Redeclared: Parameter") {
    val input = """
                  boolean m[5]; 
                  int func(){}
                  int main(int m){
                    return 0;
                  }    
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,410))
  }
  test("11 No Redeclared: local Variable") {
    val input = """
                  boolean m[5]; 
                  void func(){}
                  int main(){
                    int m;
                    return 0;
                  }    
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,411))
  }
  test("12 No Redeclared: Parameter and Function") {
    val input = """
                  void func(){}
                  int main(int func){
                    return 0;
                  }    
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,412))
  }
  test("13 Redeclared Built-in Function") {
    val input = """
                void putLn(int a){}                     
                """
    val expected = """Redeclared Function: putLn"""
    assert(checkCkr(input,expected,413))
  }
  test("14 Redeclared Built-in Function") {
    val input = """
                int getInt;                   
                """
    val expected = """Redeclared Variable: getInt"""
    assert(checkCkr(input,expected,414))
  }
  test("15 Undeclared Identifier") {
    val input = """
                void main(boolean a){a1 = 2;}                     
                """
    val expected = """Undeclared Identifier: a1"""
    assert(checkCkr(input,expected,415))
  }
  test("16 Undeclared Identifier: Global func end Local Scope") {
    val input = """
                int func(){}
                void main(boolean a){
                  func = 2;
                  return;}                     
                """
    val expected = """null"""
    assert(checkCkr(input,expected,416))
  }
  test("17 Undeclared Identifier: local scope and not error") {
    val input = """
                int func(){}
                void main(boolean a){
                  int func;
                  func = 2;
                  return;}                     
                """
    val expected = """null"""
    assert(checkCkr(input,expected,417))
  }
  test("18 Undeclared Identifier: arrayType") {
    val input = """
                int func(){}
                void main(){
                  a1[5] = getInt();
                  return;
                  }                     
                """
    val expected = """Undeclared Identifier: a1"""
    assert(checkCkr(input,expected,418))
  }
  test("19 Undeclared Function simple ") {
    val input = """     
                void main(){
                  func();
                  return;
                }
                """
    val expected = """Undeclared Function: func"""
    assert(checkCkr(input,expected,419))
  }
  test("20 Undeclared Function complex return ") {
    val input = """     
                void main(){
                  return func()[1] + 2/3;
                }
                """
    val expected = """Undeclared Function: func"""
    assert(checkCkr(input,expected,420))
  }
  test("21 Undeclared Function complex 2 - expression ") {
    val input = """
                float a;
                int func(){
                  return 3;
                }     
                void main(){
                  int b[5];
                  a = b[2] + 3/a - foo()[b[2]+4];
                  return;
                }
                """
    val expected = """Undeclared Function: foo"""
    assert(checkCkr(input,expected,421))
  }
  test("22 Undeclared Function complex 3 - forstmt ") {
    val input = """
                float a;
                int func(){
                  return 3;
                }     
                void main(){
                  int a;
                  for(a=1;a<=10;a=a+2) foo(2);
                  return;
                }
                """
    val expected = """Undeclared Function: foo"""
    assert(checkCkr(input,expected,422))
  }
  test("23 Undeclared Function complex 4 - dowhilestmt1") {
    val input = """
                float a;
                int func(){
                  return 3;
                }     
                void main(){
                  int a;
                  do a = 1; func()+2-func1(1.0); while true;
                  return;
                }
                """
    val expected = """Undeclared Function: func1"""
    assert(checkCkr(input,expected,423))
  }
  test("24 Undeclared Function complex 4 - dowhilestmt2") {
    val input = """
                float a;
                int func(){
                  return 3;
                }     
                void main(){
                  int a;
                  do a==2; while (func()+2-foo(1)[2])==true;
                  return;
                }
                """
    val expected = """Undeclared Function: foo"""
    assert(checkCkr(input,expected,424))
  }
   test("25 Undeclared Function complex 4 - ifStmt") {
    val input = """
                float a;
                int func(int m){
                  return 3;
                }     
                void main(){
                  int a;
                  if(a!=2) true; else a % (func(3)+foo(2));
                  return;
                }
                """
    val expected = """Undeclared Function: foo"""
    assert(checkCkr(input,expected,425))
  }
  test("26 Type Mismatch In Expression: Array Cell1") {
    val input = """ 
                void main(){
                  int a[10];
                  a["hello"] = 2;
                }   
                      
                """
    val expected = """Type Mismatch In Expression: ArrayCell(Id(a),StringLiteral(hello))"""
    assert(checkCkr(input,expected,426))
  }
  test("27 Type Mismatch In Expression: Array Cell2") {
    val input = """ 
                void main(){
                  float a[10];
                  a[0.5] = 2;
                }   
                      
                """
    val expected = """Type Mismatch In Expression: ArrayCell(Id(a),FloatLiteral(0.5))"""
    assert(checkCkr(input,expected,427))
  }
  test("28 Type Mismatch In Expression: Array Cell3") {
    val input = """ 
                int[] foo(boolean a){
                }
                void main(){
                  float a[10];
                  a[foo(false)] = 2;
                }   
                      
                """
    val expected = """Type Mismatch In Expression: ArrayCell(Id(a),CallExpr(Id(foo),List(BooleanLiteral(false))))"""
    assert(checkCkr(input,expected,428))
  }
  test("29 Type Mismatch In Expression: Array Cell4") {
    val input = """ 
                int foo(boolean a){ return 0;
                }
                void main(){
                  float b;
                  b[foo(false)] = 2;
                }   
                      
                """
    val expected = """Type Mismatch In Expression: ArrayCell(Id(b),CallExpr(Id(foo),List(BooleanLiteral(false))))"""
    assert(checkCkr(input,expected,429))
  }
  test("30 Type Mismatch In Expression: Array Cell5") {
    val input = """ 
                int foo(boolean a){ return 0;
                }
                boolean func(float n){
                  boolean c[10];
                  return c[9];
                }
                void main(){
                  float b;
                  func(10)[foo(false)] = 2;
                }   
                      
                """
    val expected = """Type Mismatch In Expression: ArrayCell(CallExpr(Id(func),List(IntLiteral(10))),CallExpr(Id(foo),List(BooleanLiteral(false))))"""
    assert(checkCkr(input,expected,430))
  }
  test("31 Type Mismatch In Expression: Array Cell5(not error") {
    val input = """ 
                int foo(boolean a){ return 0;
                }
                boolean[] func(float n){
                  boolean c[10];
                  return c;
                }
                void main(){
                  float b;
                  func(10)[foo(false)] = true;
                }   
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,431))
  }
  test("32 Type Mismatch In Expression: Assign") {
    val input = """
                void main(){
                  string a;
                  a = 2.0;
                  return;
                }
                      
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(a),FloatLiteral(2.0))"""
    assert(checkCkr(input,expected,432))
  }
  test("33 Type Mismatch In Expression: Assign2") {
    val input = """
                float func(float n){
                  return n;
                }
                void main(){
                  boolean a;
                  a = func(2.0);
                  return;
                }
                      
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(a),CallExpr(Id(func),List(FloatLiteral(2.0))))"""
    assert(checkCkr(input,expected,433))
  }
  test("34 Type Mismatch In Expression: Assign3") {
    val input = """
                void main(){
                  boolean arr[10];
                  int a[10];
                  a = arr;
                  return;
                }
                      
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(a),Id(arr))"""
    assert(checkCkr(input,expected,434))
  }
  test("35 Type Mismatch In Expression: Assign4 (arrayType = ArrayPointerType") {
    val input = """
                void main(string str[]){
                  boolean arr[10];
                  int a[10];
                  arr = str;
                  return;
                }
                      
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(arr),Id(str))"""
    assert(checkCkr(input,expected,435))
  }
  test("36 Type Mismatch In Expression: Assign5(not error) float = int") {
    val input = """
                float foo(float n){
                  return n;
                }
                void main(string str[]){
                  int arr[10];
                  float a[10];
                  a[1] = arr[0]+foo(2.0);
                  return;
                }
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,436))
  }
  test("37 Type Mismatch In Expression: Assign6(not error)(not error)") {
    val input = """
                float foo(float n, float list[]){
                  return n+list[1];
                }
                void main(string str[]){
                  int arr[10];
                  float b[10];
                  float a[10];
                  foo(2.0,a) = arr[0]+foo(2.0,b);
                  return;
                }
                      
                """
    val expected = """null"""
    assert(checkCkr(input,expected,437))
  }
  test("38 Type Mismatch In Expression: Assign6(not error) ArrayPointerType = float") {
    val input = """
                float foo(float n, float list[]){
                  return n+list[1];
                }
                void main(string str[]){
                  int arr[10];
                  float b[10];
                  float a[10];
                  str = arr[0]+foo(2.0,b);
                  return;
                }
                      
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(str),BinaryOp(+,ArrayCell(Id(arr),IntLiteral(0)),CallExpr(Id(foo),List(FloatLiteral(2.0),Id(b)))))"""
    assert(checkCkr(input,expected,438))
  }
  test("39 Type Mismatch In Expression: Function Call simple 1") {
    val input = """ 
    string f(string i){
      return i;
    }
      void main(){
        string str;
        str = f();
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List())"""
    assert(checkCkr(input,expected,439))
  }
  test("40 Type Mismatch In Expression: Function Call simple 2") {
    val input = """ 
    string f(boolean i[]){
      return "an";
    }
      void main(){
        string str;
        int a[10];
        str = f(a[1]);
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(ArrayCell(Id(a),IntLiteral(1))))"""
    assert(checkCkr(input,expected,440))
  }
  test("41 Type Mismatch In Expression: Function Call simple 3") {
    val input = """ 
    string f(boolean i[]){
      return "an";
    }
      void main(){
        string str;
        int a[10];
        str = f(a);
        return;
      }                   
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(Id(a)))"""
    assert(checkCkr(input,expected,441))
  }
  test("42 Type Mismatch In Expression: (no-error)Function Call simple 4") {
    val input = """ 
    string f(boolean i[]){
      return "an";
    }
      void main(){
        string str;
        boolean a[10];
        str = f(a);
        return;
    }                    
                """
    val expected = """null"""
    assert(checkCkr(input,expected,442))
  }
  test("43 Type Mismatch In Expression: Function Call size Parameter 1") {
    val input = """ 
    string f(string i){
      return i;
    }
      void main(){
        string str;
        str = f("an",1);
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(StringLiteral(an),IntLiteral(1)))"""
    assert(checkCkr(input,expected,443))
  }
  test("44 Type Mismatch In Expression: Function Call size Parameter 2") {
    val input = """ 
    string f(string i,boolean m, float a){
      return i;
    }
      void main(){
        string str;
        float b;
        b = 1.0;
        str = f("an",true,b,true);
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(StringLiteral(an),BooleanLiteral(true),Id(b),BooleanLiteral(true)))"""
    assert(checkCkr(input,expected,444))
  }
  test("45 Type Mismatch In Expression: Function Call size Parameter 3") {
    val input = """ 
    string f(string i,boolean m, float a,float b){
      return i;
    }
    string func(int a){
      return "demo";
    }
      void main(){
        string str;
        float b;
        b = 1.0;
        str = f("an",true,b,func(5));
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(StringLiteral(an),BooleanLiteral(true),Id(b),CallExpr(Id(func),List(IntLiteral(5)))))"""
    assert(checkCkr(input,expected,445))
  }
  test("46 Type Mismatch In Expression: Function Call size Parameter 4") {
    val input = """ 
    string f(string i,boolean m, float a,float b){
      return i;
    }
    string func(int a){
      return "demo";
    }
      void main(){
        string str;
        float b;
        b = 1.0;
        str = f("an",true,b,func(5,2.0));
        return;
      }                  
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(func),List(IntLiteral(5),FloatLiteral(2.0)))"""
    assert(checkCkr(input,expected,446))
  }
  test("47 Type Mismatch In Expression: Function Call type of Parameter 1") {
    val input = """ 
    int f(int a,float b,string c){
      return 2;
    }
    int main(){
      float m;
      float n;
      string k;
      return f(m,n,k);
    } 
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(Id(m),Id(n),Id(k)))"""
    assert(checkCkr(input,expected,447))
  }
  test("48 Type Mismatch In Expression: Function Call type of Parameter 2(coerce)") {
    val input = """ 
    int f(float a,float b,string c){
      return 2;
    }
    int main(){
      int m;
      float n;
      string k;
      return f(m,n,k);
    } 
                """
    val expected = """null"""
    assert(checkCkr(input,expected,448))
  }
  test("49 Type Mismatch In Expression: (no-error)Function Call - type of Parameter 1(coerce)") {
    val input = """ 
    int f(float a[],float b,string c){
      return 2;
    }
    int main(){
      int m[10];
      float n;
      string k;
      return f(m,n,k);
    } 
                """
    val expected = """null"""
    assert(checkCkr(input,expected,449))
  }
  test("50 Type Mismatch In Expression: (no-error)Function Call - type of Parameter 2(coerce)") {
    val input = """ 
    int f(float a[],float b,string c){
      return 2;
    }
    int main(int m[]){
      float n;
      string k;
      return f(m,n,k);
    } 
                """
    val expected = """null"""
    assert(checkCkr(input,expected,450))
  }
  test("51 Type Mismatch In Expression: Function Call - type of Parameter 3(coerce)") {
    val input = """ 
    int f(float a[],float b,string c, string u){
      return 2;
    }
    int main(int m[]){
      float n;
      string k;
      return f(m,2.5,"str", (n>=10.0)&&true&&(-m[2]==4));
    } 
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(f),List(Id(m),FloatLiteral(2.5),StringLiteral(str),BinaryOp(&&,BinaryOp(&&,BinaryOp(>=,Id(n),FloatLiteral(10.0)),BooleanLiteral(true)),BinaryOp(==,UnaryOp(-,ArrayCell(Id(m),IntLiteral(2))),IntLiteral(4)))))"""
    assert(checkCkr(input,expected,451))
  }
test("52 Type Mismatch In Expression: Not Unary Op - simple") {
    val input = """     
                 float main(){
                  return 2 + (-true); 
                 }     
                """
    val expected = """Type Mismatch In Expression: UnaryOp(-,BooleanLiteral(true))"""
    assert(checkCkr(input,expected,452))
  }
  test("53 Type Mismatch In Expression: Not Unary Op - simple 2") {
    val input = """     
                 float main(){
                  int a;
                  string b[10];
                  return a + (-b[5]); 
                 }     
                """
    val expected = """Type Mismatch In Expression: UnaryOp(-,ArrayCell(Id(b),IntLiteral(5)))"""
    assert(checkCkr(input,expected,453))
  }
  test("54 Type Mismatch In Expression: Not Unary Op - complex ") {
    val input = """     
                 float main(int a[], int b[]){
                  a[1] = -a[2];
                  return -(((a[1]+a[2]+b[1])==2)!=true);
                 }     
                """
    val expected = """Type Mismatch In Expression: UnaryOp(-,BinaryOp(!=,BinaryOp(==,BinaryOp(+,BinaryOp(+,ArrayCell(Id(a),IntLiteral(1)),ArrayCell(Id(a),IntLiteral(2))),ArrayCell(Id(b),IntLiteral(1))),IntLiteral(2)),BooleanLiteral(true)))"""
    assert(checkCkr(input,expected,454))
  }
  test("55 Type Mismatch In Expression: Not Unary Op - complex2 ") {
    val input = """     
                  boolean f(boolean check){
                    return check;
                  }
                 float main(int a[], int b[]){
                  a[1] = -a[2];
                  return -f(f(f(f(a[1]+a[2] == 5)))); 
                 }     
                """
    val expected = """Type Mismatch In Expression: UnaryOp(-,CallExpr(Id(f),List(CallExpr(Id(f),List(CallExpr(Id(f),List(CallExpr(Id(f),List(BinaryOp(==,BinaryOp(+,ArrayCell(Id(a),IntLiteral(1)),ArrayCell(Id(a),IntLiteral(2))),IntLiteral(5)))))))))))"""
    assert(checkCkr(input,expected,455))
  }
  test("56 Type Mismatch In Expression: Notlogic Unary Op ") {
    val input = """     
                  void main(){
                    !(3.0);
                  }
                """
    val expected = """Type Mismatch In Expression: UnaryOp(!,FloatLiteral(3.0))"""
    assert(checkCkr(input,expected,456))
  }
  test("57 Type Mismatch In Expression: Notlogic Unary Op 2 ") {
    val input = """     
                  int[] func(int a[]){
                    a[1] = 0;
                    return a;
                  }
                  void main(){
                    int arr[10];
                    !(func(arr)[1]-3);
                  }
                """
    val expected = """Type Mismatch In Expression: UnaryOp(!,BinaryOp(-,ArrayCell(CallExpr(Id(func),List(Id(arr))),IntLiteral(1)),IntLiteral(3)))"""
    assert(checkCkr(input,expected,457))
  }
  test("58 Type Mismatch In Expression: Notlogic Unary Op 3 ") {
    val input = """     
                  int[] func(int arr[]){
                    arr[1] = 0;
                    return arr;
                  }
                  void main(int b[]){
                    int n;
                    int a;
                    !(-func[b[1]] - a%n);
                    return;
                  }
                """
    val expected = """Type Mismatch In Expression: UnaryOp(!,BinaryOp(-,UnaryOp(-,ArrayCell(Id(func),ArrayCell(Id(b),IntLiteral(1)))),BinaryOp(%,Id(a),Id(n))))"""
    assert(checkCkr(input,expected,458))
  }
  test("59 Type Mismatch In Expression: (not error) Notlogic Unary Op 4 ") {
    val input = """     
                  boolean func(boolean arr){
                    arr = true;
                    return !arr;
                  }
                  void main(){
                    int n;
                    int a;
                    !(!func(a==n));
                    return;
                  }
                """
    val expected = """null"""
    assert(checkCkr(input,expected,459))
  }
  test("60 Type Mismatch In Expression: Notlogic Unary Op 5 ") {
    val input = """     
                  
                  void main(){
                    !(!(!(!("23"))));
                    return;
                  }
                """
    val expected = """Type Mismatch In Expression: UnaryOp(!,StringLiteral(23))"""
    assert(checkCkr(input,expected,460))
  }
  test("61 Type Mismatch In Expression: BinaryOp Add(+)  Sub(-)  Div(/)  Mul(*) 1") {
    val input = """
                void func(int a, float b, float arr[]){
                  string c[10];
                  boolean d; 
                  if((a+b-arr[1]+d+2*3/4)==2)
                    if(true) a = a + 1;
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(+,BinaryOp(-,BinaryOp(+,Id(a),Id(b)),ArrayCell(Id(arr),IntLiteral(1))),Id(d))"""
    assert(checkCkr(input,expected,461))
  }
  test("62 Type Mismatch In Expression: BinaryOp Add(+)") {
    val input = """
                void func(int a, float b, float arr){
                  string c[10];
                  boolean d; 
                  if((d+a)==true)
                    if(true) a = a + 1;
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(+,Id(d),Id(a))"""
    assert(checkCkr(input,expected,462))
  }
  test("63 Type Mismatch In Expression: BinaryOp Sub(-)") {
    val input = """
                void func(string s1, float s2){
                  float c[10];
                  boolean d; 
                  (s2 + c[1])/(d-c);
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(-,Id(d),Id(c))"""
    assert(checkCkr(input,expected,463))
  }
  test("64 Type Mismatch In Expression: BinaryOp Div(/)") {
    val input = """
                void func(string s1, float s2){
                  float c[10];
                  boolean d; 
                  (c[1]+s2-c[2]/2.0)*"chuoi";
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(*,BinaryOp(-,BinaryOp(+,ArrayCell(Id(c),IntLiteral(1)),Id(s2)),BinaryOp(/,ArrayCell(Id(c),IntLiteral(2)),FloatLiteral(2.0))),StringLiteral(chuoi))"""
    assert(checkCkr(input,expected,464))
  }
  test("65 Type Mismatch In Expression: BinaryOp Mul(*)") {
    val input = """
                void func(){
                  int a;
                  string str;
                  float c[10];
                  boolean d; 
                  a*a/c[1]-d;
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(-,BinaryOp(/,BinaryOp(*,Id(a),Id(a)),ArrayCell(Id(c),IntLiteral(1))),Id(d))"""
    assert(checkCkr(input,expected,465))
  }
  test("66 Type Mismatch In Expression: BinaryOp Add(+)  Sub(-)  Div(/)  Mul(*) 1") {
    val input = """
                void func(int a, float b[], float arr[]){
                  string c[10];
                  boolean d; 
                  ((a+3)- 5.2*b[1]+3/2.0*(a/b[2]))*arr;
                  return;
                }  
                """
    val expected = """Type Mismatch In Expression: BinaryOp(*,BinaryOp(+,BinaryOp(-,BinaryOp(+,Id(a),IntLiteral(3)),BinaryOp(*,FloatLiteral(5.2),ArrayCell(Id(b),IntLiteral(1)))),BinaryOp(*,BinaryOp(/,IntLiteral(3),FloatLiteral(2.0)),BinaryOp(/,Id(a),ArrayCell(Id(b),IntLiteral(2))))),Id(arr))""" 
    assert(checkCkr(input,expected,466))
  }
  test("67 Type Mismatch In Expression: BinaryOp Equal(==) and Not equal(!=) 1") {
    val input = """     
              void main(){
                int a;
                float b;
                boolean c[5];
                (((a==1)!=c[2])==((2==3)!=false))=="chuoi";
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(==,BinaryOp(==,BinaryOp(!=,BinaryOp(==,Id(a),IntLiteral(1)),ArrayCell(Id(c),IntLiteral(2))),BinaryOp(!=,BinaryOp(==,IntLiteral(2),IntLiteral(3)),BooleanLiteral(false))),StringLiteral(chuoi))"""
    assert(checkCkr(input,expected,467))
  }
  test("68 Type Mismatch In Expression: BinaryOp Equal(==) and Not equal(!=) 2") {
    val input = """     
              void main(){
                int a;
                float b;
                boolean c[5];
                ((c[1]!=true)==(a==a))!=(a==b);
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(==,Id(a),Id(b))"""
    assert(checkCkr(input,expected,468))
  }
  test("69 Type Mismatch In Expression: BinaryOp Equal(==) and Not equal(!=) 3") {
    val input = """  
              boolean[] func(boolean arr[]){
                arr[1] = (2==3);
                return arr;
              }   
              void main(){
                int a;
                float b;
                boolean c[5];
                ((c[1]!=true)==(a==a))!=func(c);
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(!=,BinaryOp(==,BinaryOp(!=,ArrayCell(Id(c),IntLiteral(1)),BooleanLiteral(true)),BinaryOp(==,Id(a),Id(a))),CallExpr(Id(func),List(Id(c))))"""
    assert(checkCkr(input,expected,469))
  }
  test("70 Type Mismatch In Expression: BinaryOp Compare(< >= > <=) 1") {
    val input = """  
                void main(){
                int a,d[10];
                float b;
                boolean c[5];
                (a>2);
                (b<1);
                (2+3.0)>=5;
                (d[1]+a) <= c[1];
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(<=,BinaryOp(+,ArrayCell(Id(d),IntLiteral(1)),Id(a)),ArrayCell(Id(c),IntLiteral(1)))"""
    assert(checkCkr(input,expected,470))
  }
  test("71 Type Mismatch In Expression: BinaryOp Compare(< >= > <=) 2") {
    val input = """  
                void main(string str[]){
                int a,d[10];
                float b;
                boolean c[5];
                (a>2);
                (b<1);
                (2+3.0)>=5;
                (a-d[10])<str;
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(<,BinaryOp(-,Id(a),ArrayCell(Id(d),IntLiteral(10))),Id(str))"""
    assert(checkCkr(input,expected,471))
  }
  test("72 Type Mismatch In Expression: BinaryOp logic(&&) or(||) 1") {
    val input = """  
                void main(string str[]){
                (true && false)||str[1];
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(||,BinaryOp(&&,BooleanLiteral(true),BooleanLiteral(false)),ArrayCell(Id(str),IntLiteral(1)))"""
    assert(checkCkr(input,expected,472))
  }
  test("73 Type Mismatch In Expression: BinaryOp logic(&&) or(||) 2") {
    val input = """  
                void main(string str[]){
                int a,d[10];
                float b;
                boolean c[5];
                false && (((a + d[1])==2)||(c[1]!=false))&&"string";
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(&&,BinaryOp(&&,BooleanLiteral(false),BinaryOp(||,BinaryOp(==,BinaryOp(+,Id(a),ArrayCell(Id(d),IntLiteral(1))),IntLiteral(2)),BinaryOp(!=,ArrayCell(Id(c),IntLiteral(1)),BooleanLiteral(false)))),StringLiteral(string))"""
    assert(checkCkr(input,expected,473))
  }
  test("74 Type Mismatch In Expression: Mod(%") {
    val input = """  
                void main(string str[]){
                int a,d[10];
                a = 2;
                (7%a)%3;
                ((a%2)==0)%2;
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(%,BinaryOp(==,BinaryOp(%,Id(a),IntLiteral(2)),IntLiteral(0)),IntLiteral(2))"""
    assert(checkCkr(input,expected,474))
  }
  test("75 Type Mismatch In Expression: complex - Logic Or(||)") {
    val input = """  
                void main(string str[]){
                int a;
                int d[10];
                !((a = 1)==2)&&((d[1] = d[10]-2)!=3)||(2);
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(||,BinaryOp(&&,UnaryOp(!,BinaryOp(==,BinaryOp(=,Id(a),IntLiteral(1)),IntLiteral(2))),BinaryOp(!=,BinaryOp(=,ArrayCell(Id(d),IntLiteral(1)),BinaryOp(-,ArrayCell(Id(d),IntLiteral(10)),IntLiteral(2))),IntLiteral(3))),IntLiteral(2))""" 
    assert(checkCkr(input,expected,475))
  }
  test("76 Type Mismatch In Expression: complex Assign(=)") {
    val input = """  
                void main(string str[]){
                int a;
                boolean b[10];
                float c[20];
                str = b[1]=!((b[2]=true)&&((c[1]=3)>= 5.0)||(((a+3)!=2)&&b[3]));
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(str),BinaryOp(=,ArrayCell(Id(b),IntLiteral(1)),UnaryOp(!,BinaryOp(||,BinaryOp(&&,BinaryOp(=,ArrayCell(Id(b),IntLiteral(2)),BooleanLiteral(true)),BinaryOp(>=,BinaryOp(=,ArrayCell(Id(c),IntLiteral(1)),IntLiteral(3)),FloatLiteral(5.0))),BinaryOp(&&,BinaryOp(!=,BinaryOp(+,Id(a),IntLiteral(3)),IntLiteral(2)),ArrayCell(Id(b),IntLiteral(3)))))))"""
    assert(checkCkr(input,expected,476))
  }
  test("77 Type Mismatch In Expression: complex - CallExpr") {
    val input = """  
                float func(float a, string b){
                  return 5.2;
                }
                void main(string str[]){
                int a;
                boolean b[10];
                float c[20];
                func(func(func(((c[1]=0.5)-(c[2])*3),str[1] = "check"),"check"),true);
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(func),List(CallExpr(Id(func),List(CallExpr(Id(func),List(BinaryOp(-,BinaryOp(=,ArrayCell(Id(c),IntLiteral(1)),FloatLiteral(0.5)),BinaryOp(*,ArrayCell(Id(c),IntLiteral(2)),IntLiteral(3))),BinaryOp(=,ArrayCell(Id(str),IntLiteral(1)),StringLiteral(check)))),StringLiteral(check))),BooleanLiteral(true)))"""
    assert(checkCkr(input,expected,477))
  }
  test("78 Type Mismatch In Expression: complex -ArrayCell") {
    val input = """  
                boolean[] func(float a, string b){
                  return func(2.0,b="check");
                }
                void main(string str[]){
                int a[5];
                boolean b[10];
                float c[20];
                func(c[20]+0.5,str[1]=="test")[func(2.0,"a")[5]];
                return;
              }     
                """
    val expected = """Type Mismatch In Expression: ArrayCell(CallExpr(Id(func),List(BinaryOp(+,ArrayCell(Id(c),IntLiteral(20)),FloatLiteral(0.5)),BinaryOp(==,ArrayCell(Id(str),IntLiteral(1)),StringLiteral(test)))),ArrayCell(CallExpr(Id(func),List(FloatLiteral(2.0),StringLiteral(a))),IntLiteral(5)))"""
    assert(checkCkr(input,expected,478))
  }
  test("79 Type Mismatch In Expression: complex - Assign") {
    val input = """  
                float[] func(boolean a, float b[]){
                  {
                  int a[10];
                  float arr[10];
                  arr = func(a[5]*(3+0.5)>=5.5,func(a[1]==2&&false,b));
                  }
                  return arr;
                }
                """
    val expected = """Type Mismatch In Expression: BinaryOp(=,Id(arr),CallExpr(Id(func),List(BinaryOp(>=,BinaryOp(*,ArrayCell(Id(a),IntLiteral(5)),BinaryOp(+,IntLiteral(3),FloatLiteral(0.5))),FloatLiteral(5.5)),CallExpr(Id(func),List(BinaryOp(&&,BinaryOp(==,ArrayCell(Id(a),IntLiteral(1)),IntLiteral(2)),BooleanLiteral(false)),Id(b))))))"""
    assert(checkCkr(input,expected,479))
  }
  test("80 Type Mismatch In Expression: complex - Assign") {
    val input = """  
                int func1(float a, int b, boolean c){
                  func2(b+func1(0.5,2,true),func2(5,b+0.5));
                  return b*2;
                }
                float c[10];
                float func2(int a, float b){
                    return func1(c[1]=0.5,3,func2(5,0.5)<=3) + (a!=2);

                }
                """
    val expected = """Type Mismatch In Expression: BinaryOp(+,CallExpr(Id(func1),List(BinaryOp(=,ArrayCell(Id(c),IntLiteral(1)),FloatLiteral(0.5)),IntLiteral(3),BinaryOp(<=,CallExpr(Id(func2),List(IntLiteral(5),FloatLiteral(0.5))),IntLiteral(3)))),BinaryOp(!=,Id(a),IntLiteral(2)))"""
    assert(checkCkr(input,expected,480))
  }
  test("81 Type Mismatch In Statement: If simple") {
    val input = """     
                float a,b;
                void main(){
                  if(a=1)b = 2;
                }      
                """
    val expected = """Type Mismatch In Statement: If(BinaryOp(=,Id(a),IntLiteral(1)),BinaryOp(=,Id(b),IntLiteral(2)),None)"""
    assert(checkCkr(input,expected,481))
  }
  test("82 Type Mismatch In Statement: If simple 2") {
    val input = """     
                float a,b;
                float func(boolean a){
                  if(a==false) a = true;
                  else false;
                  return 2.5;
                }
                void main(){
                  if(func(a<=b)*3) a = 2;
                  else b = 3;
                }      
                """
    val expected = """Type Mismatch In Statement: If(BinaryOp(*,CallExpr(Id(func),List(BinaryOp(<=,Id(a),Id(b)))),IntLiteral(3)),BinaryOp(=,Id(a),IntLiteral(2)),Some(BinaryOp(=,Id(b),IntLiteral(3))))"""
    assert(checkCkr(input,expected,482))
  }
  test("83 Type Mismatch In Statement: If simple 3") {
    val input = """     
                float a,b;
                string c;
                void main(){
                  if(c = "1123") a = 2;
                  else b = 3;
                }      
                """
    val expected = """Type Mismatch In Statement: If(BinaryOp(=,Id(c),StringLiteral(1123)),BinaryOp(=,Id(a),IntLiteral(2)),Some(BinaryOp(=,Id(b),IntLiteral(3))))"""
    assert(checkCkr(input,expected,483))
  }
  test("84 Type Mismatch In Statement: If 1") {
    val input = """     
                float a,b;
                void main(float arr[]){
                  if(arr) a = 2.5;
                  else b = 3.5;
                }      
                """
    val expected = """Type Mismatch In Statement: If(Id(arr),BinaryOp(=,Id(a),FloatLiteral(2.5)),Some(BinaryOp(=,Id(b),FloatLiteral(3.5))))"""
    assert(checkCkr(input,expected,484))
  }
  test("85 Type Mismatch In Statement: If 2") {
    val input = """     
                float a,b;
                void main(){
                  float arr[10];
                  if(arr) a = 2.5;
                  else b = 3.5;
                }      
                """
    val expected = """Type Mismatch In Statement: If(Id(arr),BinaryOp(=,Id(a),FloatLiteral(2.5)),Some(BinaryOp(=,Id(b),FloatLiteral(3.5))))"""
    assert(checkCkr(input,expected,485))
  }
  test("86 Type Mismatch In Statement: If 3") {
    val input = """     
                float a,b;
                void main(){
                  if(main()) a = 2.5;
                  else b = 3.5;
                }      
                """
    val expected = """Type Mismatch In Statement: If(CallExpr(Id(main),List()),BinaryOp(=,Id(a),FloatLiteral(2.5)),Some(BinaryOp(=,Id(b),FloatLiteral(3.5))))"""
    assert(checkCkr(input,expected,486))
  }
  test("87 Type Mismatch In Statement: For Simple (expr1 = boolean)") {
    val input = """
                int a;
                void main(){
                  a = 2;
                  for((a+3)>=2;a<=3;a = a+1)5;
                  return;
                }     
                      
                """
    val expected = """Type Mismatch In Statement: For(BinaryOp(>=,BinaryOp(+,Id(a),IntLiteral(3)),IntLiteral(2)),BinaryOp(<=,Id(a),IntLiteral(3)),BinaryOp(=,Id(a),BinaryOp(+,Id(a),IntLiteral(1))),IntLiteral(5))"""
    assert(checkCkr(input,expected,487))
  }
  test("88 Type Mismatch In Statement: For Simple (expr2 = float) ") {
    val input = """
                int a;
                void main(){
                  float b[10];
                  for((a = 1); (b[1] = a+5)*5.2;a = a+1) 5;
                  return;
                }     
                      
                """
    val expected = """Type Mismatch In Statement: For(BinaryOp(=,Id(a),IntLiteral(1)),BinaryOp(*,BinaryOp(=,ArrayCell(Id(b),IntLiteral(1)),BinaryOp(+,Id(a),IntLiteral(5))),FloatLiteral(5.2)),BinaryOp(=,Id(a),BinaryOp(+,Id(a),IntLiteral(1))),IntLiteral(5))"""
    assert(checkCkr(input,expected,488))
  }
   test("89 Type Mismatch In Statement: For Simple (expr3 = string)") {
    val input = """
                int a;
                void main(){
                  string str;
                  for(a+3*2;a<=3; str = "test")5;
                  return;
                }     
                      
                """
    val expected = """Type Mismatch In Statement: For(BinaryOp(+,Id(a),BinaryOp(*,IntLiteral(3),IntLiteral(2))),BinaryOp(<=,Id(a),IntLiteral(3)),BinaryOp(=,Id(str),StringLiteral(test)),IntLiteral(5))"""
    assert(checkCkr(input,expected,489))
  }
  test("90 Type Mismatch In Statement: For") {
    val input = """
                void main(float b[]){
                  int a[5];
                  boolean c;
                  string str[10];
                  a[2] = 0;
                  for (a[1] = a[2]+2; a[1] <= (b[1]=100.5);a[1] = a[1] + (a[3] = 2))
                    for (b; true||(!false); !(a[5] = a[5]+3))
                    {
                      1+2+3;
                    }
                    return;
                }     
                      
                """
    val expected = """Type Mismatch In Statement: For(Id(b),BinaryOp(||,BooleanLiteral(true),UnaryOp(!,BooleanLiteral(false))),UnaryOp(!,BinaryOp(=,ArrayCell(Id(a),IntLiteral(5)),BinaryOp(+,ArrayCell(Id(a),IntLiteral(5)),IntLiteral(3)))),Block(List(),List(BinaryOp(+,BinaryOp(+,IntLiteral(1),IntLiteral(2)),IntLiteral(3)))))"""
    assert(checkCkr(input,expected,490))
  }
  test("91 Type Mismatch In Statement: do while StringType") {
    val input = """
                  float a;
                  int b;     
                  void main(){
                    do a = 2.5; b = 1; while("string here");
                    return;
                  } 
                """
    val expected = """Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(a),FloatLiteral(2.5)),BinaryOp(=,Id(b),IntLiteral(1))),StringLiteral(string here))"""
    assert(checkCkr(input,expected,491))
  }
  test("92 Type Mismatch In Statement: do while ArrType") {
    val input = """
                  float a;
                  float d[10];
                  int b;     
                  void main(){
                    do a = 2.5; b = 1; while(d);
                    return;
                  } 
                """
    val expected = """Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(a),FloatLiteral(2.5)),BinaryOp(=,Id(b),IntLiteral(1))),Id(d))"""
    assert(checkCkr(input,expected,492))
  }
  test("93 Type Mismatch In Statement: do while IntType") {
    val input = """
                  float a;
                  int b;     
                  void main(){
                    do a = 2.5; b = 1; while( b -3);
                    return;
                  } 
                """
    val expected = """Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(a),FloatLiteral(2.5)),BinaryOp(=,Id(b),IntLiteral(1))),BinaryOp(-,Id(b),IntLiteral(3)))"""
    assert(checkCkr(input,expected,493))
  }
  test("94 Type Mismatch In Statement: do while VoidType") {
    val input = """
                  float a;
                  int b;     
                  void main(){
                    do a = 2.5; b = 1; while(main());
                    return;
                  } 
                """
    val expected = """Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(a),FloatLiteral(2.5)),BinaryOp(=,Id(b),IntLiteral(1))),CallExpr(Id(main),List()))"""
    assert(checkCkr(input,expected,494))
  }
  test("95 Type Mismatch In Statement: do while (ArrayPointerType") {
    val input = """
                  float a;
                  int b;     
                  void main(float arr[]){
                    do a = 2.5; b = 1; while(arr);
                    return;
                  } 
                """
    val expected = """Type Mismatch In Statement: Dowhile(List(BinaryOp(=,Id(a),FloatLiteral(2.5)),BinaryOp(=,Id(b),IntLiteral(1))),Id(arr))"""
    assert(checkCkr(input,expected,495))
  }
  test("96 Type Mismatch In Statement: return - VoidType") {
    val input = """
                void main(){
                  float b;
                  b = 5.6;
                  return b;
                }     
                      
                """
    val expected = """Type Mismatch In Statement: Return(Some(Id(b)))"""
    assert(checkCkr(input,expected,496))
  }
  test("97 Type Mismatch In Statement: return - VoidType") {
    val input = """
                boolean func(){
                  return true;
                }
                void main(){
                  float b;
                  b = 5.6;
                  return func();
                }     
                      
                """
    val expected = """Type Mismatch In Statement: Return(Some(CallExpr(Id(func),List())))"""
    assert(checkCkr(input,expected,497))
  }
  test("98 Type Mismatch In Statement: return - PimitiveType(String - Float - Int - Boolean) 1") {
    val input = """
                float func(){
                  return 2.5;
                }
                int func2(){
                  return 2;
                }
                string main(){
                  float b;
                  b = 5.6;
                  return func()+func2();
                }     
                      
                """
    val expected = """Type Mismatch In Statement: Return(Some(BinaryOp(+,CallExpr(Id(func),List()),CallExpr(Id(func2),List()))))"""
    assert(checkCkr(input,expected,498))
  }
  test("99 Type Mismatch In Statement: return - ArrayType 2") {
    val input = """

                float arr1[5];
                int[] func(float a[]){
                  int b[5];
                  a[2] = 3;
                  return b;
                }
                string[] func2(int a[]){
                  return func(a);
                }
                      
                """
    val expected = """Type Mismatch In Statement: Return(Some(CallExpr(Id(func),List(Id(a)))))"""
    assert(checkCkr(input,expected,499))
  }
  test("100 Type Mismatch In Statement: return && Built-in Function") {
    val input = """
              string func(string str){
                return func("kyan");
              }
              int getName(){
                if(getInt()==1){
                  return 1;
                  do putFloat(getFloat()); while true;
                  
                }
                else {
                  return getName(putStringLn(getName("Name")));
                }
              }
                      
                """
    val expected = """Type Mismatch In Expression: CallExpr(Id(getName),List(CallExpr(Id(putStringLn),List(CallExpr(Id(getName),List(StringLiteral(Name)))))))"""
    assert(checkCkr(input,expected,500))
  }
   
}