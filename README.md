# Kotlin
## 1. view binding - xml내 ID 연결
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
        setContentView(binding.root)
        
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
