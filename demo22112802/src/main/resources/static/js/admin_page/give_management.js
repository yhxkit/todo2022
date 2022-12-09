function submit_give_item(){
    const data =$('#givingitemform').serializeArray();
    let giveinfo = {};
    let isValid = true;
    $.each(data, function(idx, val){
        giveinfo[val['name']] = val['value'];
        $('#'+val['name']).text('');
        if(val['value'] == ''){
            isValid = false;
           $('#'+val['name']).text('값을 입력하세요');
        }
    });
    console.log("아이템 지급 정보 ", giveinfo);

    if(isValid){
        $.ajax({
            type: 'POST',
            url: root + '/give/item',
            data: giveinfo,
            success: function (data) {
                console.log(JSON.stringify(data));
                if(data.code == 0){
                    alert("지급되었습니다");
                    location.reload();
                }else{
                    alert("지급 실패(code: "+data.code+")");
                }
            },
            error: function (data) {
                console.log(JSON.stringify(data));
                alert(JSON.stringify(data));
            }
        });
    }



}
