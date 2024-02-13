# Tabulator 사용하기

#### 직접 Tabulator을 사용하지 않고, 직접 테뷸레이터를 만들어가는 클래스 생성

----

### * 사용방법 (1) : html 선언

```html
<div id="table-id"></div>
```
-> 다음과 같이 테이블을 넣어줄 div 생성

---

### * 사용방법 (2) : 객체 생성

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

### * 사용방법 (3) : 데이터 호출 및 set

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

### * 사용방법 (4-1) : 컬럼추가

-> 다음 부분의 경우 url을 통해 데이터를 호출하거나, local 데이터를 이용하거나 동일하다.

```js
table
  .add(new Column(필드명).title(컬럼제목).width("20%").center().formatter(function(cell) {
         const rowData = cell.getData();                  // 'cell.getData()'를 통해 해당 row 데이터를 가져온다.
         // return을 통해 최종적으로 보여질 값을 리턴해준다.
         if(rowData['delYn'] === 'Y') return '<span style="color: red">삭제됨</span>'
         else return '<span style="color: blue">삭제안됨</span>' 
   }))
```

1.  필드명: 호출해오는 데이터의 필드명이라고 보면 된다.
2.  컬럼제목: 화면상의 테이블에서 적혀질 컬럼제목명이라고 보면 된다.
3.  width: 해당 컬럼 가로 폭 길이를 정한다.
4.  center/left/right: 해당 컬럼속 데이터들의 정렬 위치를 정한다.
5.  formatter: 앞선 방식과 같이 cell로 값을 받고, cell.getData()를 통해 해당 행(가로줄)의 데이터값을 받아와서 커스터마이징 한 뒤, return 해준다.
 
__** 필수값: #필드명__

---

### * 사용방법 (4-2) : 컬럼추가

* 다음 방법은 colspan을 이용해야 할 경우, 즉 cell을 가로로 합병할 경우 필요하다.
![image](https://github.com/hongdahyeon/tabulator/assets/113295805/6522b8ee-810b-48b7-b1c1-d3569d1fd372)

-> 위의 사진과 같이 컬럼을 선언해야 할 경우에는 다음과 같이 컬럼을 추가한다.

```js
/*
* ex data. {... address: {city: 'city1', street: 'street1', zipcode: 'zipcode1'}
*/
table
  .add(new Column().title("주소").width("20%").center()  // 필드명 없는 컬럼 -> 그 하위로 add
      .add(new Column("address").title("city").width("10%").center().formatter(function(cell) {
          const data = cell.getData()
          return `${data['address']['city']}`
      }))
      .add(new Column("address").title("street").width("10%").center().formatter(function(cell) {
          const data = cell.getData()
          return `${data['address']['street']}`
      }))
      .add(new Column("address").title("zipcode").width("10%").center().formatter(function(cell) {
          const data = cell.getData()
          return `${data['address']['zipcode']}`
      }))
  )
```
-> 위의 js 코드를 보게 되면 맨 처음 '주소'를 title로 갖고 있는 Column에 대해서는 필드명이 없다. 그리고 그 뒤로 add를 통해 컬럼을 추가해줌으로써 앞선 그림과 같은 형태로 컬럼명을 선언할 수 있다.

-> '필드명 없는' Column을 add하고 그 뒤로 '계속된 add를 통해' Column객체를 선언하면 colspan처럼 사용이 가능하다.

* 예를 들어, 만일 다음과 같이 코드를 작성하면 
```js
table
.add(new Column().title("주소").width("20%").center()  // 필드명 없는 컬럼 -> 그 하위로 add
    .add(new Column().title("시도").width("10%").center()  // 필드명 없는 컬럼 -> 그 하위로 add
        .add(new Column("address").title("시티").width("10%").center().formatter(function(cell) {
            const data = cell.getData()
            return `${data['address']['city']}`
        }))
        .add(new Column("address").title("스트릿").width("10%").center().formatter(function(cell) {
            const data = cell.getData()
            return `${data['address']['street']}`
        }))
        .add(new Column("address").title("집코드").width("10%").center().formatter(function(cell) {
            const data = cell.getData()
            return `${data['address']['zipcode']}`
        }))
    )
    .add(new Column("address").title("street").width("10%").center().formatter(function(cell) {
        const data = cell.getData()
        return `${data['address']['street']}`
    }))
    .add(new Column("address").title("zipcode").width("10%").center().formatter(function(cell) {
        const data = cell.getData()
        return `${data['address']['zipcode']}`
    }))
)
```
![image](https://github.com/hongdahyeon/tabulator/assets/113295805/6401c4b3-7017-46b3-831e-4c00d394bdcd)

-> 다음과 같은 테이블 헤더가 나올 것이다.


---

### * 사용방법 (5) : 핸들링

-> 다음 부분의 경우 url을 통해 데이터를 호출하거나, local 데이터를 이용하거나 동일하다.

<br/>

### 1. 행클릭 이벤트
```js
table
  .rowClick((data, row) => window.location.href = `/post/view/${data['id']}`)  // 해당 행(가로줄)클릭에 대한 클릭 이벤트를 설정할 수 있다.
```

<br/>

### 2. selectable

* 테이블의 행(가로줄) 셀렉트 가능 여부를 true로 변경한다. (default: false)

```js
table
  .selectable()   // 다음과 같이 할 경우, selectable이 true로 변경된다. (기본값: false)
```
```js
const selectRows = table.getSelectedRows()
```
  -> 위와 같이 테이블의 selectable을 true로 변경하면 다음과 같이 선택된 행(가로줄)에 대한 데이터 리스트를 반환받을 수 있다.

<br/>

### 3. selectAll / deSelectAll

* selectable이 true인 경우에만사용 가능하다. (false인 경우 error)

```js
$("#all-btn-chk").on('click', () => table.selectAll())        // 테이블 행 전체 선택
$("#all-btn-nonChk").on('click', () => table.deSelectAll())   // 테이블 행 전체 선택 해제
```

<br/>

### 4. rowFormatter

* 해당 행(가로줄)에 대한 커스터마이징이 가능하다.

```js
/* example */
table
  .rowFormatter((row) => {
      console.log('row: ', row.getData())                     // 해당 row 데이터 가져오기
      console.log('row: ', row.getElement())                  // 해당 row element 가져오기
      row.getElement().style.backgroundColor = "#e0b4b4";     // -> 다음과 같이 커스터마이징 가능 => 다음과 같이 하게 될 경우 행의 배경색이 '#e0b4b4' 색으로 변경된다.
  })
```

<br/>

### 5. afterComplete

* 테이블이 그려진 뒤 바로 수행될 작업을 선언한다.

```js
table
  .afterComplete(() => {
      console.log("table is completely drawn !")
  })
```
-> 테이블이 그려진 직후 바로 해당 콘솔이 출력된다.

<br/>

### 6. setPlaceholder

* 데이터가 0건일 경우 테이블에 뜨는 placeholder message를 변경할 수 있다.

* 기본값: "검색결과가 존재하지 않습니다."

  ```js
  table
    .setPlaceholder("데이터가 0건입니다.")
  ```

<br/>

### 7. resizeable

* 행 크기를 줄이거나 늘릴 수 있도록 한다. (기본값: false)

* 기본적으로는 행 크기 조절이 안된다.

```js
table
  .resizeable()
```

<br/>

### 8. changeLayout

* 테이블의 layout을 변경할 수 있다. (기본값: fitDataFill)

* 종류: fitData / fitDataStretch / fitDataTable / fitColumns / fitDataFill

```js
table
  .changeLayout("fitDataStretch")
```

<br/>

### 9. 헤더 title위치

* 다음의 경우, 4-2를 적용했을때만 사용 가능하다.

```js
/* (1) 헤더 title 위치: 중간 */
table
  .headerMiddle()
```
![image](https://github.com/hongdahyeon/tabulator/assets/113295805/1c1ced2a-deb3-44a6-a302-be6ff7e104f7)


```js
/* (2) 헤더 title 위치: 아래 */
table
  .headerBottom()
```
![image](https://github.com/hongdahyeon/tabulator/assets/113295805/5f597ca5-9c72-42e7-8b30-e29c1500e54e)
