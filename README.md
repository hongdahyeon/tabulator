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

∴ 서로 섞어서 사용 불가

---

#### * 사용방법 (4) : 컬럼추가

-> 다음 부분의 경우 url을 통해 데이터를 호출하거나, local 데이터를 이용하거나 동일하다.

```js
table
  .add(new Column(#필드명).title(#컬럼제목).width("20%").center().formatter(function(cell) {
         const rowData = cell.getData();                  // 'cell.getData()'를 통해 해당 row 데이터를 가져온다.
         // return을 통해 최종적으로 보여질 값을 리턴해준다.
         if(rowData['delYn'] === 'Y') return '<span style="color: red">삭제됨</span>'
         else return '<span style="color: blue">삭제안됨</span>' 
   }))
```

1.  #필드명: 호출해오는 데이터의 필드명이라고 보면 된다.
2.  #컬럼제목: 화면상의 테이블에서 적혀질 컬럼제목명이라고 보면 된다.
3.  width: 해당 컬럼 가로 폭 길이를 정한다.
4.  center/left/right: 해당 컬럼속 데이터들의 정렬 위치를 정한다.
5.  formatter: 앞선 방식과 같이 cell로 값을 받고, cell.getData()를 통해 해당 row의 데이터값을 받아와서 커스터마이징 한 뒤, return 해준다.
 
__** 필수값: #필드명__
