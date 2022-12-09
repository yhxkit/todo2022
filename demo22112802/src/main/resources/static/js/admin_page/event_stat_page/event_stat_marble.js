function searchUserCompleteLogList(userNum) {
    $.ajax({
        url: root + '/event_stat/marble/' + chanceMasterIdx + '/rank/' + userNum,
        type: 'get',
        success: function (data) {
            console.log(JSON.stringify(data));
            let result = "";

            $.each(data, function (key, val) {
                result += "<tr><td>" + val.masterIdx;
                result += "</td><td>" + val.userNum + "</td><td>" + val.complete;
                result += "</td><td>" + val.diceNum;
                result += "</td><td>" + val.regDate + "</td></tr>";
            });

            if (data.length < 1) {
                result = '<tr><td colspan="8" style="text-align:center;">검색된 결과가 없습니다</td></tr>';
            }

            $('#searchResult').html(result);
        },
        error: function (data) {
            getMessage(data);
        }
    });
}




