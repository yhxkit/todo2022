function paginate(totalRows, current, pageSize, pageUnit){
    if(totalRows == 0){ //검색 결과가 없을 경우
        return new Page(1, 1, 1, false, false);
    }

    let totalPageCount = totalRows%pageSize == 0 ? totalRows/pageSize : parseInt(totalRows/pageSize+1);
    let lastUnitSize = totalPageCount % pageUnit == 0? pageUnit : totalPageCount % pageUnit;

    let st = (parseInt((current-1)/pageUnit))*pageUnit+1;
    let end = st+pageUnit-1 > totalPageCount ? totalPageCount : st+pageUnit-1 ;
    let prv = st > pageUnit;
    let nxt = end <= totalPageCount-lastUnitSize;

    return new Page(st, end, current, prv, nxt);
}


function Page(startPage, endPage, currentPage, prev, next){
    let pageList = new Array();

    for(let i=startPage; i<=endPage; i++){
        pageList.push(i);
    };

    this.pageList = pageList;
    this.startPage = startPage;
    this.endPage = endPage;
    this.currentPage = currentPage;
    this.prev = prev;
    this.next = next;
}