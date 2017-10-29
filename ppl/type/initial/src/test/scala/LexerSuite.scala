import org.scalatest.FunSuite

/**
  * Created by nhphung on 4/28/17.
  */
class LexerSuite extends FunSuite with TestLexer {

  test("a simple identifier") {
    val input = "abc"
    val expect = "abc,<EOF>"
    assert(checkLex(input,expect,1))
  }
  test("half function declare") {
    val input = "main int {"
    val expect = """main,int,{,<EOF>"""
    assert(checkLex(input,expect,2))
  }
  test("open and close parentheses"){
    val input = "} int main {"
    val expect = """},int,main,{,<EOF>"""
    assert(checkLex(input,expect,3))
  }
  test("comment block"){
    val input = """/*ab cd*/"""
    val expect = "<EOF>"
    assert(checkLex(input,expect,4))
  }
  test("comment line"){
    val input = """//abc"""
    val expect = "<EOF>"
    assert(checkLex(input,expect,5))
  }
  test("float 1"){
    val input = """1.2"""
    val expect = "1.2,<EOF>"
    assert(checkLex(input,expect,6))
  }
  test("float 2"){
    val input = """1."""
    val expect = "1.,<EOF>"
    assert(checkLex(input,expect,7))
  }
  test("float 3"){
    val input = """.1"""
    val expect = ".1,<EOF>"
    assert(checkLex(input,expect,8))
  }
  test("float 4"){
    val input = """1e2"""
    val expect = "1e2,<EOF>"
    assert(checkLex(input,expect,9))
  }
  test("float 5"){
    val input = """1.2E-2"""
    val expect = "1.2E-2,<EOF>"
    assert(checkLex(input,expect,10))
  }
  test("float 6"){
    val input = """1.2e-2"""
    val expect = "1.2e-2,<EOF>"
    assert(checkLex(input,expect,11))
  }
  test("float 7"){
    val input = """.1E2"""
    val expect = ".1E2,<EOF>"
    assert(checkLex(input,expect,12))
  }
  test("float 8"){
    val input = """9.0"""
    val expect = "9.0,<EOF>"
    assert(checkLex(input,expect,13))
  }
  test("float 9"){
    val input = """12e8"""
    val expect = "12e8,<EOF>"
    assert(checkLex(input,expect,14))
  }
  test("float 10"){
    val input = """0.33E-3"""
    val expect = "0.33E-3,<EOF>"
    assert(checkLex(input,expect,15))
  }
  test("float 11"){
    val input = """128e-42"""
    val expect = "128e-42,<EOF>"
    assert(checkLex(input,expect,16))
  }
  test("float 12"){
    val input = """e-12"""
    val expect = "e,-,12,<EOF>"
    assert(checkLex(input,expect,16))
  }
  test("float 13"){
    val input = """143e"""
    val expect = "143,e,<EOF>"
    assert(checkLex(input,expect,17))
  }
  test("string 1"){
    val input = """ "this is string" """
    val expect = "this is string,<EOF>"
    assert(checkLex(input,expect,18))
  }
  test("string 2"){
    val input = """ "this is \n string" """
    val expect = "this is \\n string,<EOF>"
    assert(checkLex(input,expect,19))
  }
  test("string 3"){
    val input = """ "this is \b string" """
    val expect = "this is \\b string,<EOF>"
    assert(checkLex(input,expect,20))
  }
  test("string 4"){
    val input = """ "this is \f string" """
    val expect = "this is \\f string,<EOF>"
    assert(checkLex(input,expect,21))
  }
  test("string 5"){
    val input = """ "this is \r string" """
    val expect = "this is \\r string,<EOF>"
    assert(checkLex(input,expect,22))
  }
  test("string 6"){
    val input = """ "this is \n string" """
    val expect = "this is \\n string,<EOF>"
    assert(checkLex(input,expect,23))
  }
  test("string 7"){
    val input = """ "this is \t string" """
    val expect = "this is \\t string,<EOF>"
    assert(checkLex(input,expect,24))
  }
  test("string 8"){
    val input = """ "this is \' string" """
    val expect = "this is \\' string,<EOF>"
    assert(checkLex(input,expect,25))
  }
  test("string 9"){
    val input = """ "this is \" string" """
    val expect = "this is \\\" string,<EOF>"
    assert(checkLex(input,expect,26))
  }
  test("string 10"){
    val input = """ "this is \\ string" """
    val expect = "this is \\\\ string,<EOF>"
    assert(checkLex(input,expect,27))
  }
  test("unclosed string 1"){
    val input = """ "this is string"""
    val expect = "Unclosed string: this is string"
    assert(checkLex(input,expect,28))
  }
  test("unclosed string 2"){
    val input = """ "this is \n string"""
    val expect = "Unclosed string: this is \\n string"
    assert(checkLex(input,expect,29))
  }
  test("unclosed string 3"){
    val input = """ "this is \b string"""
    val expect = "Unclosed string: this is \\b string"
    assert(checkLex(input,expect,30))
  }
  test("unclosed string 4"){
    val input = """ "this is \f string"""
    val expect = "Unclosed string: this is \\f string"
    assert(checkLex(input,expect,31))
  }
  test("unclosed string 5"){
    val input = """ "this is \r string"""
    val expect = "Unclosed string: this is \\r string"
    assert(checkLex(input,expect,32))
  }
  test("unclosed string 6"){
    val input = """ "this is \n string"""
    val expect = "Unclosed string: this is \\n string"
    assert(checkLex(input,expect,33))
  }
  test("unclosed string 7"){
    val input = """ "this is \t string"""
    val expect = "Unclosed string: this is \\t string"
    assert(checkLex(input,expect,34))
  }
  test("unclosed string 8"){
    val input = """ "this is \' string"""
    val expect = "Unclosed string: this is \\' string"
    assert(checkLex(input,expect,35))
  }
  test("unclosed string 9"){
    val input = """ "this is \" string"""
    val expect = "Unclosed string: this is \\\" string"
    assert(checkLex(input,expect,36))
  }
  test("unclosed string 10"){
    val input = """ "this is \\ string"""
    val expect = "Unclosed string: this is \\\\ string"
    assert(checkLex(input,expect,37))
  }
  test("illegal escape 1"){
    val input = """ "this is \a string " """
    val expect = "Illegal escape in string: this is \\a"
    assert(checkLex(input,expect,38))
  }
  test("illegal escape 2"){
    val input = """ "this is \z string " """
    val expect = "Illegal escape in string: this is \\z"
    assert(checkLex(input,expect,39))
  }
  test("operator 1"){
    val input = """+"""
    val expect = "+,<EOF>"
    assert(checkLex(input,expect,40))
  }
  test("operator 2"){
    val input = """-"""
    val expect = "-,<EOF>"
    assert(checkLex(input,expect,41))
  }
  test("operator 3"){
    val input = """ *"""
    val expect = "*,<EOF>"
    assert(checkLex(input,expect,42))
  }
  test("operator 4"){
    val input = """/"""
    val expect = "/,<EOF>"
    assert(checkLex(input,expect,43))
  }
  test("operator 5"){
    val input = """!"""
    val expect = "!,<EOF>"
    assert(checkLex(input,expect,44))
  }
  test("operator 6"){
    val input = """%"""
    val expect = "%,<EOF>"
    assert(checkLex(input,expect,45))
  }
  test("operator 7"){
    val input = """||"""
    val expect = "||,<EOF>"
    assert(checkLex(input,expect,46))
  }
  test("operator 8"){
    val input = """&&"""
    val expect = "&&,<EOF>"
    assert(checkLex(input,expect,47))
  }
  test("operator 9"){
    val input = """!="""
    val expect = "!=,<EOF>"
    assert(checkLex(input,expect,48))
  }
  test("operator 10"){
    val input = """=="""
    val expect = "==,<EOF>"
    assert(checkLex(input,expect,49))
  }
  test("operator 11"){
    val input = """<"""
    val expect = "<,<EOF>"
    assert(checkLex(input,expect,50))
  }
  test("seperator 1"){
    val input = """ []{}();, """
    val expect = "[,],{,},(,),;,,,<EOF>"
    assert(checkLex(input,expect,51))
  }
  test("seperator 2"){
    val input = """ [ """
    val expect = "[,<EOF>"
    assert(checkLex(input,expect,52))
  }
  test("seperator 3"){
    val input = """]"""
    val expect = "],<EOF>"
    assert(checkLex(input,expect,53))
  }
  test("seperator 4"){
    val input = """{"""
    val expect = "{,<EOF>"
    assert(checkLex(input,expect,54))
  }
  test("seperator 5"){
    val input = """}"""
    val expect = "},<EOF>"
    assert(checkLex(input,expect,55))
  }
  test("seperator 6"){
    val input = """("""
    val expect = "(,<EOF>"
    assert(checkLex(input,expect,56))
  }
  test("seperator 7"){
    val input = """)"""
    val expect = "),<EOF>"
    assert(checkLex(input,expect,57))
  }
  test("seperator 8"){
    val input = """;"""
    val expect = ";,<EOF>"
    assert(checkLex(input,expect,58))
  }
  test("seperator 9"){
    val input = """, """
    val expect = ",,<EOF>"
    assert(checkLex(input,expect,59))
  }
  test("integer literal"){
    val input = """ 0123 0 1 """
    val expect = "0123,0,1,<EOF>"
    assert(checkLex(input,expect,60))
  }
  test("boolean literal"){
    val input = """ true false """
    val expect = "true,false,<EOF>"
    assert(checkLex(input,expect,61))
  }
  test("primitive types"){
    val input = """ boolean int float string """
    val expect = "boolean,int,float,string,<EOF>"
    assert(checkLex(input,expect,62))
  }
  test("void type"){
    val input = """void"""
    val expect = "void,<EOF>"
    assert(checkLex(input,expect,63))
  }
  test("array"){
    val input = """boolean[3] """
    val expect = "boolean,[,3,],<EOF>"
    assert(checkLex(input,expect,64))
  }
  test("array pointer type"){
    val input = """ boolean[] """
    val expect = "boolean,[,],<EOF>"
    assert(checkLex(input,expect,65))
  }
  test("if"){
    val input = """ if"""
    val expect = "if,<EOF>"
    assert(checkLex(input,expect,66))
  }
  test("do"){
    val input = """ do """
    val expect = "do,<EOF>"
    assert(checkLex(input,expect,67))
  }
  test("while"){
    val input = """ while """
    val expect = "while,<EOF>"
    assert(checkLex(input,expect,68))
  }
  test("boolean"){
    val input = """ boolean"""
    val expect = "boolean,<EOF>"
    assert(checkLex(input,expect,69))
  }
  test("break"){
    val input = """ break """
    val expect = "break,<EOF>"
    assert(checkLex(input,expect,70))
  }
  test("continue"){
    val input = """continue"""
    val expect = "continue,<EOF>"
    assert(checkLex(input,expect,71))
  }
  test("else"){
    val input = """ else """
    val expect = "else,<EOF>"
    assert(checkLex(input,expect,72))
  }
  test("for"){
    val input = """for """
    val expect = "for,<EOF>"
    assert(checkLex(input,expect,73))
  }
  test("float"){
    val input = """ float"""
    val expect = "float,<EOF>"
    assert(checkLex(input,expect,74))
  }
  test("int"){
    val input = """ int """
    val expect = "int,<EOF>"
    assert(checkLex(input,expect,75))
  }
  test("return"){
    val input = """ return """
    val expect = "return,<EOF>"
    assert(checkLex(input,expect,76))
  }
  test("void"){
    val input = """ void """
    val expect = "void,<EOF>"
    assert(checkLex(input,expect,77))
  }
  test("do 2"){
    val input = """ do """
    val expect = "do,<EOF>"
    assert(checkLex(input,expect,78))
  }
  test("while 2"){
    val input = """ while """
    val expect = "while,<EOF>"
    assert(checkLex(input,expect,79))
  }
  test("true"){
    val input = """ true """
    val expect = "true,<EOF>"
    assert(checkLex(input,expect,80))
  }
  test("false"){
    val input = """ false """
    val expect = "false,<EOF>"
    assert(checkLex(input,expect,81))
  }
  test("string"){
    val input = """ string """
    val expect = "string,<EOF>"
    assert(checkLex(input,expect,82))
  }
  test("id 1"){
    val input = """ baont """
    val expect = "baont,<EOF>"
    assert(checkLex(input,expect,83))
  }
  test("id 2"){
    val input = """ _baont """
    val expect = "_baont,<EOF>"
    assert(checkLex(input,expect,84))
  }
  test("id 3"){
    val input = """ _baont4 """
    val expect = "_baont4,<EOF>"
    assert(checkLex(input,expect,85))
  }
  test("id 4"){
    val input = """ _bao4nt """
    val expect = "_bao4nt,<EOF>"
    assert(checkLex(input,expect,86))
  }
  test("id 5"){
    val input = """ Baont """
    val expect = "Baont,<EOF>"
    assert(checkLex(input,expect,87))
  }
  test("id 6"){
    val input = """ Baont4 """
    val expect = "Baont4,<EOF>"
    assert(checkLex(input,expect,88))
  }
  test("id 7"){
    val input = """ _Baont """
    val expect = "_Baont,<EOF>"
    assert(checkLex(input,expect,89))
  }
  test("id 8"){
    val input = """ _Baont4 """
    val expect = "_Baont4,<EOF>"
    assert(checkLex(input,expect,90))
  }
  test("id 9"){
    val input = """ _BaoNT """
    val expect = "_BaoNT,<EOF>"
    assert(checkLex(input,expect,91))
  }
  test("id 10"){
    val input = """ _BaoNT4 """
    val expect = "_BaoNT4,<EOF>"
    assert(checkLex(input,expect,92))
  }
  test("id int"){
    val input = """ baont 4"""
    val expect = "baont,4,<EOF>"
    assert(checkLex(input,expect,93))
  }
  test("id float"){
    val input = """ baont 4.4 """
    val expect = "baont,4.4,<EOF>"
    assert(checkLex(input,expect,94))
  }
  test("id string"){
    val input = """ baont "baont4" """
    val expect = "baont,baont4,<EOF>"
    assert(checkLex(input,expect,95))
  }
  test("id +"){
    val input = """ baont + """
    val expect = "baont,+,<EOF>"
    assert(checkLex(input,expect,96))
  }
  test("id -"){
    val input = """ baont -"""
    val expect = "baont,-,<EOF>"
    assert(checkLex(input,expect,97))
  }
  test("id *"){
    val input = """ baont *"""
    val expect = "baont,*,<EOF>"
    assert(checkLex(input,expect,98))
  }
  test("id /"){
    val input = """ baont / """
    val expect = "baont,/,<EOF>"
    assert(checkLex(input,expect,99))
  }
  test("id %"){
    val input = """ baont % """
    val expect = "baont,%,<EOF>"
    assert(checkLex(input,expect,100))
  }
}