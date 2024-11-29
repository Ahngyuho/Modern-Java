# 람다 표현식
람다 표현식은 익명 함수 전달을 단순화한 것입니다.
문장을 분석해보면서 람다 표현식의 특징에 대해서 알아봅시다.

- 익명 : 이름이 없다. 즉 신경쓸 것이 하나 줄었다.
- 함수 : 객체지향에서는 자신의 상태를 변경하거나 다른 객체에게 메시지를 보낼 때 메서드를 이용하는데 람다에서는 특정 객체 즉 클래스에 종속된 것이 아니기 때문에 함수라고 표현
- 전달 : 람다 표현식을 메서드의 인수로 전달하거나 변수로 저장 가능
- 간결성 : 익명 클래스에 비해 자질구레한 코드를 구현할 필요가 없다.

람다의 실제 구현된 형태를 살펴보면
(String str,int a)      ->            {System.out.println(str + " " + a);} 
  argument list       arrow token          Body of lambda expression

- 파라미터 리스트 : 구현할 함수의 파라미터 전달 
- arrow token : 파라미터 리스트와 바디를 구분하는 역할
- 람다 바디 : 함수, 동작 수행에 해당하는 표현식

# 람다는 어디에 어떻게 사용하는 것일까?
람다는 함수를 값으로 전달할 때 사용합니다.  
```java
    List<Apple> redAndHeavyApples = filter(inventory, apple -> apple.getWeight() > 80 && apple.getColor() == Color.RED);

```
람다로 함수형 인터페이스의 추상 메서드를 구현한 것을 마치 값처럼 파라미터나 변수에 전달할 수 있습니다.  

그래서 결국 람다로 무엇을 할 수 있냐? 라고 질문받았을 때 한 문장으로 표현해야 한다면 다음과 같을 것 같습니다.  
함수를 값으로 다룰 수 있다!

```java
    import java.util.Comparator;

Predicate<Apple> applePredicate = (Apple a) -> a.getColor == Color.GREEN;
Function<String, Integer> getLength1 = (String s) -> s.length();
Comparator<Intger> comp = (Apple a1, Apple a2) -> a1.getWeight().compare(a2.getWeight());
```

# 함수형 인터페이스란?
함수형 인터페이스는 구현해야 할 메서드가 딱 하나뿐인 인터페이스를 뜻합니다.

# 함수 디스크립터
**함수형 인터페이스의 추상 메서드 시그니처**를 람다 표현식으로 나타낸 것을 **함수 디스크립터** 라고 합니다.


예를 들어 Predicate<T> 같은 경우는
```java
  @FunctionalInterface
  public interface Predicate<T> {
    boolean test(T t);
  }
  
  //T -> boolean
```
T -> boolean 이 함수 디스크립터가 되겠습니다.  
함수 디스크립터는 함수형 인터페이스의 추상 메서드 시그니처를 람다 표현식으로 표현한 것 입니다.  



- 함수형 인터페이스가 가지는 추상 메서드의 파라미터와 반환 타입을 정의
- 람다 표현식이 전달되면 이 표현식과 위 시그니처에 호환되는지 확인! - 컴파일러가 람다의 유효성을 확인하는 과정 중 하나...

함수형 인터페이스 Predicate,Consumer, Comparator ... 
함수형 인터페이스의 추상 메서드 시그니처가 람다 표현식의 시그니처를 가리킨다?
람다 표현식의 시그니처를 서술하는 메서드를 함수 디스크립터라고 한다...

# 형식 검사

1. 컴파일러가 람다가 적용된 부분을 보고 어떤 형식이 사용되었는지 확인 합니다.
  ```java
        filter(inventory, apple -> apple.getWeight() > 80 && apple.getColor() == Color.RED);
  ```
2. apple -> apple.getWeight() > 80 && apple.getColor() == Color.RED
   이 부분을 보고 Predicate<Apple> 라는 것을 확인합니다.
3. 이 함수형 인터페이스의 추상 메서드 시그니처를 확인하고 함수 디스크립터로 만듭니다. (Apple) -> boolean
4. 이 함수 디스크립터와 람다 표현식을 비교합니다. 입력이 apple 이고 반환은 boolean 이므로 일치합니다.

이런 식으로 컴파일러는 람다 표현식과  
함수형 인터페이스의 추상 메서드 시그니처를 함수 디스크립터화 하여  
람다 표현식과 함수 디스크립터를 비교해서 형식 검사를 수행합니다.  

# 형식 추론  

