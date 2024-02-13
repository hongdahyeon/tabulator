# Tabulator 사용하기

#### 직접 Tabulator을 사용하지 않고, 직접 테뷸레이터를 만들어가는 클래스 생성

----

#### * 사용방법 (1)

```html
<div id="table-id"></div>
```
-> 다음과 같이 테이블을 넣어줄 div 생성

---

#### * 사용방법 (2)

(1) url을 통한 데이터 호출

```js
const table = new Table("table-id")
// 따로 param2에 값을 안줄 경우 (param2 : useUrl : default is true) , url을 통한 직접 데이터 호출
```

(2) local 데이터 이용
```js
const table = new Table("table-id", false)
// param2: useUrl 여부 (기본값: true)
// -> useUrl이 false일 경우 local 데이터 이용으로 변경됨
```
