# Kotlin
## 1. 변수 & Log
### type : 특정화 하지 않으면 알아서 적용
```kotlin
// 실수형
var doubleVal:Double = 3.14
var floatVal:Float = 3.14f

// 정수형
var intVal:Int = 12345678
var longVal:Long = 2_147_483_647 // ,처럼 _표시 가능
var shortVal:Short = 123
var byteVal:Byte = 127

// 문자형
var charVal:Char = 'a'  // ''
var stringVal:String = "문자형 길이에 상관없이 값을 입력할 수 있다"  // "" - 기본형 아님

// boolean형
var booleanVal:Boolean = true
```
### val : 재정의 불가 - 상수(대문자로 표시)
```kotlin
val PI = 3.141592
```
### var : 재정의 가능 - 변수
```kotlin
var myName = "prohk"
```
### Log.d(tag, msg) : Logcat에서 확인 가능 / tag 검색 가능
```kotlin
Log.d("tag","msg")
```
## 2. null safety - null이 있어도 안전하다
#### nullable : 변수명:타입? = null
```kotlin
var number:Int? = null
```
#### Safe Call : 변수명?. - null일 경우 null 출력, 뒤에 ?:값 입력 시 null 값 대체
```kotlin
var length = number?.length ?: 10 // number가 null일 경우 10, null이 아닌 경우 length 출력
```
### 늦은 초기화 - 변수 선언 후 초기화시 null로 선언할 필요가 없을 때 사용
#### lateinit : lateinit var 변수명:타입 - 초기에 null을 지정하지 않고 초기화, var만 가능(나중에 선언해야되니까 val(상수 안됨)), 기본형만 가능(String 안됨)
```kotlin
lateinit var number:Int
```
#### lazy : val 변수명:타입 by lazy { 초기화 코드 } - val만 가능, 선언 후 바로 초기화(호출 시점에서 초기화 -> 늦은 초기화)
```kotlin
val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
```
## 3. 스코프함수(Scope function) : 지연초기화와 함께 safe call 남용을 막음
### run, let, apply, also // with

## 4. view binding - xml내 ID 연결
```kotlin
// build.gradle(Module) 추가
android {
  buildFeatures{
    viewBinding true
  }
}

// binding 적용
class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // ContentView 변경!
        
        // 버튼 클릭 이벤트
        binding.btnSay.setOnClickListener {
            // text 변수에 값 할당(when 함수 -- 자바 switch 비슷)
            val text = when(binding.textView.text){
                "Hello World!" -> "Hello Kotlin!"
                else -> "Hello World!"
            }
            // textView(ID)에 text 변경 -- 자바 setText
            binding.textView.text = text
        }
    }
}
```
## 5. 문법
### 비교연산자 - <, >, <=, >=, ==, != 
```kotlin
val result = 300 < 500
```
### 논리연산자 - &&(교집합), ||(합집합) 
```kotlin
val logic1 = result && (200 > 300)
```
### 부정연산자 - !
```kotlin
val logic2 = !result
```
### if(조건식 : 비교연산 + 논리연산){ scope } else if{ scope} else { scope }
```kotlin
var month:Int = 4
if(month > 9)      Log.d("if","가을 옷을 입습니다.")
else if(month > 6) Log.d("if","해수욕장을 갑니다.")
else if(month > 3) Log.d("if","소풍을 갑니다.")
else               Log.d("if","집에 있습니다.")
```
### when(조건식){ scope } - 대부분의 switch 비슷 // else 가능
```kotlin
when(month){
  1,2,3   -> Log.d("when","집에 있습니다.")
  in 4..6 -> Log.d("when","소풍을 갑니다.")
  in 7..9 -> Log.d("when","해수욕장을 갑니다.")
  else    -> Log.d("when","가을 옷을 입습니다.")
}
```
### 배열(array) - 사이즈 지정 / index는 0부터 시작
```kotlin
var intArr:IntArray = IntArray(3)
intArr[0] = 10 // 값 입력
var intArray = intArrayOf(0,10,20)
```
### 컬렉션(list) - 동적
```kotlin
var mutableList = mutableListOf<Int>()
mutableList.add(5)  // 값 입력, index 자동 할당
mutableList.get(0)  // 값 가져오기
```
### 맵(key,value)
```kotlin
var mutableMap = mutableMapOf<String,String>()
mutableMap.put("key1","value1")  // 값 입력
mutableMap.get("key1")           // 값 가져오기
```
### for(조건) {코드블럭} 
#### in a..b : a <= X <= b / a until b : a <= X < b / a downTo b : a >= X >= b / in array : 배열 전체
```kotlin
for(value in intArray){
  Log.d("반복문","value = $value")
}
```
### while(조건) {코드블럭}
#### control loop - continue : 지나감 / break : 함수 종료
```kotlin
var out = 0
while (out < 3){
  Log.d("반복문","현재 아웃카운트는 $out")
  out++
}
```
### do{ 코드블럭(무조건 실행)} while(조건){ 코드블럭 }
```kotlin
var out1 = 3
do{
  out1++
} while(out<3)
for(i in 0..10){
  if(i == 5) continue
}
```
### 함수 fun 함수이름(파라미터) { 코드블럭 }
```kotlin
// 기본 함수
fun functionName(){}
// 입력 값이 있는 함수
fun functionParam(param1:String){}
// 출력 값이 있는 함수 - 리턴 타입을 정해줘야 함
fun getPi():Double{
  return 3.141592
}
```
### 클래스 class 클래스이름 { 변수(property) + 함수(method) }
```kotlin
class className{
  /*init{
    클래스를 초기화하면 호출 -> 메모리에 올라간다
  }*/
  var variable:String = ""  // 프로퍼티
  fun functionName(){       // 메서드
    var variable_local = ""
  }
}
```
#### override : 상속해주는 곳에 open을 적어줘야 함
```kotlin
// 상속받으면 부모클래스의 프로퍼티와 매서드를 내 것처럼 사용할 수 있다
open class Parent {
    open var house = "강남 200평 아파트"
    open fun showHouse(){
        Log.d("클래스","parent house=$house")
    }
}
class Child:Parent() { // 클래스 상속시 클래스명 뒤에 :부모() 적어줘야함
    override var house = "강남 10평 오피스텔"
    override fun showHouse(){
        Log.d("클래스","child house=$house")
    }
}
```
#### overload
```kotlin
class Son{
    fun getNumber():Int{
        return 1
    }
    fun getNumber(param:String):Int{
        return 2
    }
}
```
