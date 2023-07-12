## Exception 모듈 사용

1. exception debug 추가 시 아래 프로퍼티 추가
   **application.yml**

```  yaml
stos.exception-handling.debug.enabled: true
```

아래와 같이 debug 프로퍼티 추가됨

``` json
{
"type": "exception:TodoItemNotFoundException",
"title": "Todo Item 을 찾지 못했습니다.",
"status": 404,
"instance": "/todo/1",
"debug": "com.study.template.todo.domain.TodoItemNotFoundException.."
}
```

-----

2. custom error property 추가 가이드

```java
public class CustomErrorProcessor implements ErrorProcessor, Ordered {
	private static final String CUSTOM_PROPERTY_NAME = "custom";

	@Override
	public void process(ProblemDetail problemDetail, Throwable throwable) {
		problemDetail.setProperty(CUSTOM_PROPERTY_NAME, "${custom_value}");
	}

	@Override
	public int getOrder() {
		return 1; // Error Processor들 간의 우선순위 맞춰서 세팅
	}
}
```

-----

