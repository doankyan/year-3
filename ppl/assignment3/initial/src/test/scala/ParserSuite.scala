import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class ParserSuite extends FunSuite with TestParser {

  test("a simple program") {
    val input = """int a;
    void main(){
      a = b && c == e < k;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,101))
  }
  test("more complex program") {
    val input ="""int main () {
  putIntLn(4);
}"""
    val expect ="sucessful"
    assert(checkRec(input,expect,102))
  }
  test("wrong program"){
    val input = "} int main {"
    val expect = "Error on line 1 col 1: }"
    assert(checkRec(input,expect,103))
  }

  // danger
  test("simple function declare"){
    val input = "int abc;"
    val expect = "sucessful"
    assert(checkRec(input, expect, 104))
  }
  test("simple function declare float"){
    val input = "float dosome ;"
    val expect = "sucessful"
    assert(checkRec(input, expect, 105))
  }

  test("wrong variable declare int"){
    val input = """int vl = 3;"""
    val expect = "Error on line 1 col 8: ="
    assert(checkRec(input,expect,106))
  }

  test("wrong variable declare int array"){
    val input = """int vl [];"""
    val expect = "Error on line 1 col 9: ]"
    assert(checkRec(input,expect,107))
  }

  test("wrong function declare"){
    val input = """float conghaiso {}"""
    val expect = "Error on line 1 col 17: {"
    assert(checkRec(input,expect,108))
  }

  test("wrong function declare miss {}"){
    val input = """float conghaiso ()"""
    val expect = "Error on line 1 col 19: <EOF>"
    assert(checkRec(input,expect,109))
  }

  test("compilex program"){
    val input = """int a;
    int b;
    int main(){ a + b;}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,110))
  }

  test("wrong: unexpect ;"){
    val input = """float conghaiso {};"""
    val expect = "Error on line 1 col 17: {"
    assert(checkRec(input,expect,111))
  }

  test("var declares and function declares"){
    val input = """float b;
    int _meo;    
    int res;
    void main(){
    b = 9.4e4;
    _meo=19;
    res = _meo * b; }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,112))
  }

  test("complex program if stmt"){
    val input = """void main () {
      if (1 == 2) b = 9;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,113))
  }

  test("complex program if else"){
    val input = """void main () {
      if (1 == 2) b = 9;
      else b = "string";
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,114))
  }

  test("complex program do while"){
    val input = """void main () {
      if (1 == 2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       good = "job\t";
      while 9 >0;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,115))
  }

  test("complex program forstmt"){
    val input = """void main () {
      if (1 == 2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
        continue;

      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,116))
  }

  test("complex program lv1 for + break stmt"){
    val input = """void main () {
      if (1 == 2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;



      }

      int sum(){
        return 0;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,117))
  }

  test("complex program : call function"){
    val input = """void main () {
      if (1 == 2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(){
        return 0;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,118))
  }

  test("complex program function return a array pointer type syntax"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1 == 2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(){
        return 0;
      }

      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,119))
  }

  test("complex program : para func type ID[]"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(){
        if(!a) sum();
        return 0;
      }

      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,120))
  }

  test("complex program test expr in if stmt"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(){
        if(true) sum();
        return 0;
      }

      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,121))
  }

  test("complex program: invo_expr"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);


      }

      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,122))
  }

  test("complex program check blockstmt"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;
          dosome(3,4);
        }
        
      }

      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,123))
  }

  test("complex expr"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;

          dosome(1,2);
        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,124))
  }

  test("complex assign expression test"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;

          a=b=c;
        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,125))
  }

  test("complex program blockstmt in a func declare"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;

          a=b=c;
          if(a==c>d) sum();

          for(a =0 ; a <=9; a = a + 1) break;
        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,126))
  }

  test("assign var for negative number"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;

          a=b=c;
          if(a==c>d) sum();

          for(a =0 ; a <=9; a = a + 1) break;

          array[9];

          a = -90;
          a = 91;
        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,127))
  }

  test("complex func call"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;

          a=b=c;
          if(a==c>d) sum();

          for(a =0 ; a <=9; a = a + 1) break;

          array[9];

          a = -90;
          a = 91;
          dosome(x[3],y[9]);
        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,128))
  }

  test("check string var"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;
          string a;
          boolean boo ;
          a=b=c;
          if(a==c>d) sum();

          for(a =0 ; a <=9; a = a + 1) break;

          array[9];

          a = -90;
          a = 91;
          dosome(x[3],y[9]);
          
          a = "aaaa\' do some\'";
          
          boo = false;

        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,129))
  }

  test("check complex expression in if stmt"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          
          if(a==c>d) sum();

          for(a =0 ; a <=9; a = a + 1) break;

          array[9];

          a = -90;
          a = 91;
          dosome(x[3],y[9]);
          
          a = "aaaa\' do some\'";
          
          boo = false;

        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,130))
  }

  test("call function in a function declare"){
    val input = """
    int  a;
    int _ahihi;

    void main () {
      if (1==2) b = 9;
      else b = "string";
      do
       c = 1+1;
       ahihi = "dongoc";
       break;
       good = "job\t";
      while 9 >0;

      sum();
      
      for ( i =1; i <= n; i = i+ 1)
        a = a + 1;
      }

      int sum(int a){
        if(!a) sum();
        return 0;
        a = (a+b)*c/d-(abc%12)+67;
        bc = true;
      }

      string chuoi(){
        sum(b);
        dosome(1.23);
        {
          int a;
          float b;
          string a;
          boolean boo ;

          dosome(a,b,c);
          

        }
        
      }
      float[] dosome (float x[], int y[]){
        return x*y;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,131))
  }

  test("simple call function"){
    val input = """
    void foo(){}
    void goo(float x[]){
      float y[10];
      int z[10];
      foo(x);
      foo(y);
      return;
    }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,132))
  }

  test("list var declare"){
    val input = """int main(){
      int a,b,c[10];
      float x,y,z,_ahihi903[100];
      return (3-1)*1234+990;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,133))
  }

  test("simple program with func declare"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  return 0;
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,134))
  }

  test("simple program with somw stmt"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  
  if (i || false*90) dequy();
  if(true) dequy(1,2,3);
  else dequy(4,5,6);

  return 0;
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,135))
  }

  test("check return stmt"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  
  if (i || false*90) dequy();
  if(true) dequy(1,2,3);
  else dequy(4,5,6);

  a=b=c;
  a=b=c=d=e;

  return 0;
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,136))
  }

  test("some crazy expression"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  
  if (i || false*90) dequy();
  if(true) dequy(1,2,3);
  else dequy(4,5,6);

  a=b=c;
  a=b=c=d=e>3*6/12%(1234+8907)+x[1];

  return 0;
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,137))
  }

  test("return a string"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  
  if (i || false*90) dequy();
  if(true) dequy(1,2,3);
  else dequy(4,5,6);

  a=b=c;
  a=b=c=d=e>3*6/12%(1234+8907)+x[1];

  return 0;
}

string chuoikytu(string a_cxs){
  int chuoiso;
  chuoiso = 1235/23*99+12.3e45%(126/s);
  return "ahihi dongoc \n\t\b";
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,138))
  }

  test("check the expression with stringlit"){
    val input = """int dequy(int a, int b, int x[]){
  a = 9878;
  b= 12345;
  x = extends(a);
  x = extends(b);
  return dequy(x[1],x[2],dequy(x[1],x[2],0));
  
  if (i || false*90) dequy();
  if(true) dequy(1,2,3);
  else dequy(4,5,6);

  a=b=c;
  a=b=c=d=e>3*6/12%(1234+8907)+x[1];

  return 0;
}

string chuoikytu(string a_cxs){
  int chuoiso;
  chuoiso = 1235/23*99+12.3e45%(126/s);
  {
    int ngocanh;
    int hyomin;
    ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
    hyomin = "h"+"y"+"o"+"m"+"i"+"n";
  }
  return "ahihi dongoc \n\t\b";
}"""
    val expect = "sucessful"
    assert(checkRec(input,expect,139))
  }

  test("check for with complex expression"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,140))
  }

  test("if stmt is nested in do while"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,141))
  }

  test("check ifstmt in for stmt"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,142))
  }

  test("check expression ID [ intlit ]"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
        }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,143))
  }

  test("function call: para is a function call"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,144))
  }

  test("check return ;"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();
      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,145))
  }

  test("test big program"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,146))
  }

  test("permute the orther of declare in big program"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,147))
  }

  test("total test for big program"){
    val input = """int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,148))
  }

  test("test all syntax"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,149))
  }

  test("complex program with many kind declare"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,150))
  }

  test("wrong ] in program"){
    val input = """int main (){]"""
    val expect = "Error on line 1 col 13: ]"
    assert(checkRec(input,expect,151))
  }

   test("wrong { in program"){
    val input = """int main {}"""
    val expect = "Error on line 1 col 10: {"
    assert(checkRec(input,expect,152))
  }

   test("wrong ( in program"){
    val input = """int abc ()()]"""
    val expect = "Error on line 1 col 11: ("
    assert(checkRec(input,expect,153))
  }

  test("wrong ( program"){
    val input = """
    void evalu(int sec, int hours)(
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 2 col 35: ("
    assert(checkRec(input,expect,154))
  }

  test("para function wrong program"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[3]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 10 col 35: 3"
    assert(checkRec(input,expect,155))
  }

   test("miss ) wrong program"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]{
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 10 col 36: {"
    assert(checkRec(input,expect,156))
  }

  test("miss function name"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void (){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 123 col 12: ("
    assert(checkRec(input,expect,157))
  }

  test("wrong syntax para declare in function"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a,, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 10 col 21: ,"
    assert(checkRec(input,expect,158))
  }

  test("miss ) in if stmt"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 22 col 24: sup"
    assert(checkRec(input,expect,159))
  }

  test("miss void in function declare"){
    val input = """
    evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 2 col 5: evalu"
    assert(checkRec(input,expect,160))
  }

  test("wrong program : void for var declare"){
    val input = "int main(){void abc;}"
    val expect = "Error on line 1 col 12: void"
    assert(checkRec(input,expect,161))
  }

  test("wrong program : miss operand"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 127 col 54: )"
    assert(checkRec(input,expect,162))
  }

  test("wrong program miss ;"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc"
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 102 col 9: x"
    assert(checkRec(input,expect,163))
  }

  test("wrong program miss ID in para declare"){
    val input = """
    void evalu(int sec, int ){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 2 col 29: )"
    assert(checkRec(input,expect,164))
  }

  test("wrong program missing expression in while"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while();

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "Error on line 114 col 16: )"
    assert(checkRec(input,expect,165))
  }

  test("missing while keyword in do whine stmt"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        };

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 114 col 10: ;"
    assert(checkRec(input,expect,166))
  }


  test("wrong ASSIGN syntax"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=;
        return;
      }

        """
    val expect = "Error on line 128 col 29: ;"
    assert(checkRec(input,expect,167))
  }

  test("miss type ni para declare"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 10 col 15: a"
    assert(checkRec(input,expect,168))
  }

  test("missing name in function declare"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void (){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 123 col 12: ("
    assert(checkRec(input,expect,169))
  }

  test("missing ASSIGN op in assign stmt"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc  "yebin-DIA";
        }

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 109 col 16: yebin-DIA"
    assert(checkRec(input,expect,170))
  }

  test("wrong when number appear in func declare"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(3){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

        """
    val expect = "Error on line 80 col 21: 3"
    assert(checkRec(input,expect,171))
  }

  test("wrong when stmt stand before declare part in block"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          return "ahihi dongoc \n\t\b";
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 51 col 11: int"
    assert(checkRec(input,expect,172))
  }

  test("wrong var declare: array declare"){
    val input = """
      int ac,cd,e[];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 2 col 19: ]"
    assert(checkRec(input,expect,173))
  }

  test("vardeclare stand after stmt in block stmt"){
    val input =
      """void main () {
            int i;
            i = 3;
            if (i > 0) putInt(i);
            boolean b;
          }"""

    val expect = "Error on line 5 col 13: boolean"
    assert(checkRec(input,expect,174))
  }

  test("wrong program: nested function"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        int main(){}
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      

        """
    val expect = "Error on line 60 col 17: ("
    assert(checkRec(input,expect,175))
  }

  test("wrong program: dont have expr in if stmt"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if() sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

        """
    val expect = "Error on line 22 col 14: )"
    assert(checkRec(input,expect,176))
  }

  test("wrong program: dont have stmt after if stmt"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(true)
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

        """
    val expect = "Error on line 23 col 11: else"
    assert(checkRec(input,expect,177))
  }

  test("wrong program: dont have ID in var declare"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(true) a = b;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float;

        """
    val expect = "Error on line 37 col 12: ;"
    assert(checkRec(input,expect,178))
  }

  test("wrong program: wrong syntax || expression"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(true) a = b;
          else sub = sub + chich;
        }
        
        if (|| false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float xs;

        """
    val expect = "Error on line 26 col 13: ||"
    assert(checkRec(input,expect,179))
  }

  test("wrong program: stmt is outside every block"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(true) a = b;
          else sub = sub + chich;
        }
        
        if (true || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      a = 1;

      int ac,cd,e[100];
      float xs;

        """
    val expect = "Error on line 36 col 7: a"
    assert(checkRec(input,expect,180))
  }

  test("wrong program: 2 operator next each other"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(true) a = b;
          else sub = sub + chich;
        }
        
        if (true || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float xs;

        """
    val expect = "Error on line 31 col 17: >"
    assert(checkRec(input,expect,181))
  }

  test("wrong when var declare stand inside para declare"){
    val input = """
    
    int dequy(int a, int b, int x[], int a = 10){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 3 col 44: ="
    assert(checkRec(input,expect,182))
  }

  test("wrong program: missing type and ID of function declare"){
    val input = """
    (int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

        """
    val expect = "Error on line 2 col 5: ("
    assert(checkRec(input,expect,183))
  }

  test("wrong program: var declare in call function"){
    val input = """
    void ahihi(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b = 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],int w));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

        """
    val expect = "Error on line 15 col 48: int"
    assert(checkRec(input,expect,184))
  }

  test("wrong program: have a useless ;"){
    val input = """
    void ahihi(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b = 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2]));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        };
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

        """
    val expect = "Error on line 24 col 10: ;"
    assert(checkRec(input,expect,185))
  }

  test("wrong program: void type in para"){
    val input = """
    void evalu(void abc){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

      void stop(){
        metqua();
        tamdung();
        maiviettiep();
        testcase = dung*roi+lam/khong%noi(nua+di*(ia/thoi));
        variable = testcase=metqua();
        return;
      }

        """
    val expect = "Error on line 2 col 16: void"
    assert(checkRec(input,expect,186))
  }

  test("wrong para declare: int a,v,c"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(int a,b,c){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

        """
    val expect = "Error on line 59 col 23: b"
    assert(checkRec(input,expect,187))
  }

  test("wrong stmt: wrong expression"){
    val input = """int main() {
      ++;
      }"""
    val expect = "Error on line 2 col 7: +"
    assert(checkRec(input,expect,188))
  }

  test("wrong stmt: duplicate operator"){
    val input = """int main() {
      a= a++1;
      }"""
    val expect = "Error on line 2 col 12: +"
    assert(checkRec(input,expect,189))
  }

  test("short test with call function"){
    val input = """int main() {
      a= a+1;
      foo(a+1, b[12]);
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,190))
  }

  test("wrong :unexpect operator in a expression"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <== cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      

        """
    val expect = "Error on line 45 col 19: ="
    assert(checkRec(input,expect,191))
  }

  test("nest do while in a loop"){
    val input = """
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          do{
            a=1;
          }while(1);
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,192))
  }

  test("wrong : SEMI after if"){
    val input = """
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          do{
            a=1;
          }while(1);
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "Error on line 19 col 17: ;"
    assert(checkRec(input,expect,193))
  }

  test("set the para is a assign expression"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt(a=3);
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "sucessful"
    assert(checkRec(input,expect,194))
  }

  test("wrong: func declare in block"){
    val input = """
    int a;
    int b;
    void dosome(){
      {
        void sohai(){}
      }

      }"""
    val expect = "Error on line 6 col 9: void"
    assert(checkRec(input,expect,195))
  }

  test("else part have unexpect }"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else
        lamtiep();
      }
    }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
      }

      float def(int a, int v,float avc, string astr, int x[]){
        astr = a+90;
        x[1] = 999 + astr=v;

        if(x[1]!=null) return 1.90;
        else do a = a + 1;
        avc = 1.3e4*3;
        astr = "ahihi" + "dongoc";
        x[1] = 1001;
        while(1 ==1);

        main();

        if(true){
          i = i + 2;
          avc = "yebin-DIA";
        }

        do{
          somestmt();
        }while(false);

        for(i = 0; i>=90;i = i+3e4){
          dosome();
          break;
        }

      }

        """
    val expect = "Error on line 9 col 5: }"
    assert(checkRec(input,expect,196))
  }

  test("correct initialization variable: array init"){
    val input = "int main () {boolean a[2]; a[1] = true; a[2] = false;}"
    val expect = "sucessful"
    assert(checkRec(input,expect,197))
  }

  test("wrong for stmt condition expressions"){
    val input = """
    int sum(){
      if (abc) makesomenoise(1,2,3,4);

      for(;;) foo()
      }"""
    val expect = "Error on line 5 col 11: ;"
    assert(checkRec(input,expect,198))
  }

  test("test for stmt with strang condition expressions"){
    val input = """
    int sum(){
      if (abc) makesomenoise(1,2,3,4);

      for(1;1;1) foo();
      }"""
    val expect = "sucessful"
    assert(checkRec(input,expect,199))
  }

  test("wrong: test 2d array"){
    val input = """
    void evalu(int sec, int hours){
      if(met == true){
        dingu();
      }
      else{
        lamtiep();
      }
    }
    int dequy(int a, int b, int x[]){
        a = 9878;
        b= 12345;
        x = extends(a);
        x = extends(b);
        return dequy(x[1],x[2],dequy(x[1],x[2],0));

        {
          int sub;
          int sup;
          string chich;

          if(sub < sup) sup = sup + chich;
          else sub = sub + chich;
        }
        
        if (i || false*90) dequy();
        if(true) dequy(1,2,3);
        else dequy(4,5,6);

        a=b=c;
        a=b=c=d=e>3*6/12%(1234+8907)+x[1];

        return 0;
      }

      int ac,cd,e[100];
      float number;

      string chuoikytu(string a_cxs){
        int chuoiso;
        {
          ac = 100;
          cd = 1000;
          e[1] = 30;
          if(ac <= cd) e[1]=40;
          else e[2] = "ahihi";
        }
        chuoiso = 1235/23*99+12.3e45%(126/s);
        {
          int ngocanh;
          int hyomin;
          ngocanh = "n"+"g"+"o"+"c"+"a"+"n"+"h";
          hyomin = "h"+"y"+"o"+"m"+"i"+"n";
          for(i = 0; i>1000; i = n*(m+1)/er-123%1234e54-44)
           donothing("ahihi");
        }
        return "ahihi dongoc \n\t\b";
      }
      void main(){
        string astring;
        string bstring;
        int a;
        int b;
        float c;
        int arr[100];
        float arr1[100][1];
        string arrs[100];
        if(len(arr) == len(arr1)) i = i %1;
        else arr1[33]=arrs11 = arrs[90] + 12/4/3/8*756%arr1[78];

        do
          if(len(arr) != 1) i = i+ 1;
          for(1;3;4) foo();
        while(false);

        for(i = 0; i<= len(arrs); i = i+90/7/5%67*0.11e-2)
          if(i%2 == 1) continue;
          else break;
      }
      int HANGSO;
      int editConst(int HANGSO){
        HANGSO = 10;
        edit(10);
        putIntLn(100);
        putIntLn("chuoiso");
        return;
      }
      int express;
      void something(){
        express = express+1;
        foo(foo(1));
        sum(sum(10),sum(15));
        foo(2)[3+x] = a[b[2]] +3;
      }

        """
    val expect = "Error on line 66 col 24: ["
    assert(checkRec(input,expect,200))
  }
}