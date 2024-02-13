# Tabulator 사용하기

#### 직접 Tabulator을 사용하지 않고, 직접 테뷸레이터를 만들어가는 클래스 생성

----

#### * 사용방법 (1) : html 선언

```html
<div id="table-id"></div>
```
-> 다음과 같이 테이블을 넣어줄 div 생성

---

#### * 사용방법 (2) : 객체 생성

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

---

#### * 사용방법 (3) : 데이터 호출 및 set

(1) url을 통한 데이터 호출

```js
table
  .get('/api/users')
```

(2) local 데이터 이용

```js
const list = []
table
  .setData(list)
```

* 만약 객체 생성을 (1)번으로 하고 데이터 호출을 (2)로 하게 되면 error가 뜬다.
*  만약 객체 생성을 (2)번으로 하고 데이터 호출을 (1)로 하게 되면 error가 뜬다.
* ∴ 서로 섞어서 사용 불가
