class Table {

    constructor(id, useUrl = true) {
        this._initBaseVariables(id, useUrl)
        this._initPaginations()
        this._initTableRendering()
        this._table = null
    }

    _initBaseVariables(id, useUrl){
        this._id = id
        this._useUrl = useUrl
        this._layout = "fitDataFill"                        // 테이블 레이아웃
        this._placeholder = "검색결과가 존재하지 않습니다."      // 데이터 0건일 경우
        this._minHeight = 300
        this._maxHeight = 300
        this._columnHeaderVertAlign = ""                    // align header contents to bottom of cell
        this._columns = []
        this._url = ''
        this._data = []
        this._selectedRows = []

        this._useIndex = true
    }

    _initTableRendering(){
        this._resizeable = false                            // 테이블 row resize 가능 여부
        this._rowFormatter = null                           // 테이블 각 row formatter
        this._rowClick = null                               // 테이블 각 row click function
        this._selectable = false                            // 테이블 row select 가능 여부
        this._afterComplete = null                          // 테이블 draw after
        this._checkbox = false
    }

    _initPaginations(){
        this._pagination = false
        this._paging = 'local'
        this._paginationSize = 6
        this._paginationSizeSelector = [3, 6, 8, 10]
        this._movableColumns = true
        this._paginationCounter = "rows"
    }

   /*
    * table data reload
    * if useUrl is true  ) reload by url
    * if useUrl is false ) reload by param data
    */
    submit(localData = []){
        if(this._useUrl) {
            Http.get(`${this._url}`).then((res) => {
                if (res['httpStatus'] === 200) {
                    const data = this._setDataNum(res.message)
                    this._table.replaceData(data)
                }
            })
        } else {
            this._data = this._setDataNum(localData)
            this._table.replaceData(this._data)
        }
    }

    /*
    * table add data
    * use when useUrl is false
    */
    addData(data = {}){
        if(!this._useUrl) {
            this._data.push(data)
            this._data = this._setDataNum(this._data)
            this._table.replaceData(this._data)
        } else console.error("useUrl이 false인 경우에만 사용 가능합니다.")
    }

    /*
    * use pagination
    */
    usePagination() {
        this._pagination = true
        return this
    }

    /*
    * set placeholder
    */
    setPlaceholder(msg = "검색결과가 존재하지 않습니다."){
        this._placeholder = msg
        return this
    }

    /*
    * @param: url
    * use when useUrl is true
    */
    get(url = '') {
        if(this._useUrl) this._url = url
        else console.error("get의 경우 useUrl이 true인 경우에만 사용이 가능합니다.")
        return this
    }

    /*
    * @param: data[]
    * use when useUrl is false
    */
    setData(data = []){
        if(!this._useUrl) this._data = data
        else console.error("setData의 경우 useUrl이 false인 경우에만 사용이 가능합니다.")
        return this
    }

    /*
    * use checkbox
    */
    checkbox() {
        this._checkbox = true
        return this
    }

    /*
    * row selectable true
    */
    selectable() {
        this._selectable = true
        return this
    }

    /*
    * @param: layout
    * - default: fitDataFill
    */
    changeLayout(layout = ''){
        this._layout = layout
        return this
    }

    /*
    * rowClick: rowClick use
    */
    rowClick(callback) {
        this._rowClick = callback
        return this
    }

    /*
    * rowFormatter: rowFormatter use
    * - can get data, element of row
    */
    rowFormatter(callback) {
        this._rowFormatter = callback
        return this
    }

    /*
    * afterComplete: settings after draw table
    */
    afterComplete(callback) {
        this._afterComplete = callback
        return this
    }

    /*
    * cab resize column
    * - default: false
    * */
    resizeable(){
        this._resizeable = true
        return this
    }

    /*
     * header bottom
     * */
    headerBottom() {
        this._columnHeaderVertAlign = "bottom"
        return this
    }

    /*
    * deselect all row
    */
    deSelectAll(){
        this._table.deselectRow();
        this._selectedRows = []
    }

    /*
    * select all row
    */
    selectAll(){
        this._table.selectRow()
    }

    /*
    * get selected rows list
    */
    getSelectedRows() {
        return this._selectedRows
    }

    /*
    * @param: column
    * - table column add
    */
    add(column) {
        if(column instanceof Column) {
            this._columns.push(column.getCol())
        }
        return this
    }


    /*
    * set data index true / false
    */
    notUseIndex(){
        this._useIndex = false
        return this
    }

    /*
    * set data index
    */
    _setDataNum(data = []){
        if(this._useIndex) return data.map((item, index) => ({...item, index: index + 1}))
        else return data
    }

    /*
    * draw table
    * if useUrl is true  ) init table by url
    * if useUrl is false ) init table by local data
    */
    init() {
        if(!this._useUrl) {
            const data = this._setDataNum(this._data)
            this._initOptions(data)
        }else {
            Http.get(`${this._url}`).then((res) => {
                if (res['httpStatus'] === 200) {
                    const data = this._setDataNum(res.message)
                    this._initOptions(data)
                }
            })
        }
    }

    /*
    * table options
    */
    _initOptions(data){

        if(this._checkbox) {
            const checkbox = {
                formatter: "rowSelection",
                titleFormatter: "rowSelection",
                hozAlign: "center",
                headerSort: false,
                width: 42,
                cssClass: "tabulator-checkbox",
                cellClick: function(e, cell) {
                    cell.getRow().toggleSelect()
                }
            }
            this._columns.unshift(checkbox)
        }

        // 2. tabulator option
        const option = {
            data: data,
            selectable: this._selectable,
            placeholder: this._placeholder,
            columnHeaderVertAlign: this._columnHeaderVertAlign,
            layout: this._layout,
            minHeight: this._minHeight,
            columnDefaults: {
                resizable: this._resizeable
            },
            columns:this._columns
        }

        if(this._pagination) {
            option['pagination'] = this._paging
            option['paginationSize'] = this._paginationSize
            option['paginationSizeSelector'] = this._paginationSizeSelector
            option['movableColumns'] = this._movableColumns
            option['paginationCounter'] = this._paginationCounter
        }

        if(this._rowFormatter) {
            option['rowFormatter'] = this._rowFormatter
        }

        const dom = `#${this._id}`
        const table = new Tabulator(dom, option)

        // if clicking row not null
        if(this._rowClick) {
            table.on('rowClick', (e, row) => {
                if(!e.target.classList.contains("tabulator-checkbox") && !e.target.classList.contains("select")) {
                    this._rowClick(row.getData(), row._row)
                }
            })
        }

        // if after complete not null
        if(this._afterComplete) {
            table.on("renderComplete", () => this._afterComplete())
        }

        table.on("rowSelectionChanged", (data, rows) => this._selectedRows = data )

        this._table = table
    }
}

class Column {

    constructor(field) {
        this._title = ''
        this._field = field
        this._headerSort = false
        this._width = '10%'
        this._hozAlign = 'center'
        this._headerHozAlign = 'center'
        this._formatter = null
        this._columns = []
    }

    title(title = '') {
        this._title = title
        return this
    }

    width(width = '10%'){
        this._width = width
        return this
    }

    headerSort(sort = false) {
        this._headerSort = sort
        return this
    }

    center(){
        this._hozAlign = 'center';
        this._headerHozAlign = 'center'
        return this
    }

    left(){
        this._hozAlign = 'left';
        this._headerHozAlign = 'left'
        return this
    }

    right(){
        this._hozAlign = 'right';
        this._headerHozAlign = 'right'
        return this
    }

    formatter(callback) {
        this._formatter = callback;
        return this
    }

    add(column) {
        if(column instanceof Column) {
            this._columns.push(column.getCol())
        }
        return this
    }

    getCol() {
        const obj = {
            title: this._title,
            headerSort: this._headerSort,
            width: this._width,
            hozAlign: this._hozAlign,
            headerHozAlign: this._headerHozAlign
        }

        if(this._field) obj.field = this._field
        if(this._formatter !== null) obj.formatter = this._formatter
        if(this._columns.length) obj.columns = this._columns

        return obj
    }
}