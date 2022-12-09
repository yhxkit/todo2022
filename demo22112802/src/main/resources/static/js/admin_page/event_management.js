
function setEventIdxToRemove(eventIdx){
    $('#eventIdxToRemove').attr('data-eventidx', eventIdx);
}

function removeSelectedEvent(eventIdx) {
    $.ajax({
        url: "/event/" + eventIdx,
        method: 'delete',
        success: function (data) {
            alert(data.msg);
            window.location.reload();
        },
        error: function (data) {
            alert(JSON.stringify(data));
        }
    });
}


function setEventDetail(sentOne) {
    let eventDetail = new Array(sentOne[0].innerText, sentOne[1].innerText, sentOne[2].innerText, sentOne[3].innerText, sentOne[4].innerText, sentOne[5].innerText, sentOne[6].innerText, sentOne[7].innerText, sentOne[8].innerText);

    $('#eventIdxInfo').text(eventDetail[0]);

    $('#eventTypeInfo option').each(function(){
        if(eventDetail[1] == $(this).val()){
            $(this).prop("selected", true);
        }
    });

    $('#eventNameInfo').val(eventDetail[2]);
    $('#eventSummaryInfo').val(eventDetail[3]);

    if( eventDetail[4] == 'true' ){
        $('#eventHideInfo').prop("checked", true);
    }else{
        $('#eventHideInfo').prop("checked", false);
    }
    $('#trannoInfo').val(eventDetail[5]);
    $('#startDateInfo').val(eventDetail[6]);
    $('#endDateInfo').val(eventDetail[7]);

    $('#regDateInfo').text(eventDetail[8]);
}

function clearEventDetail(){
    const span = document.createElement('span');
    span.textContent='';
    const sentOne = [span,span,span,span,span,span,span,span,span];
    setEventDetail(sentOne);
}

function submitEditEvent() {
    let eventObj = {};
    eventObj.idx = $('#eventIdxInfo').text();
    if(eventObj.idx == ''){
        eventObj.idx = 0;
    }

    eventObj.type = $('#eventTypeInfo option:selected').val();

    eventObj.eventName = $('#eventNameInfo').val();
    eventObj.summary= $('#eventSummaryInfo').val();
    eventObj.hide= $('#eventHideInfo').is(':checked');
    eventObj.tranno= $('#trannoInfo').val();

    eventObj.startDateForString=  $('#startDateInfo').val();
    eventObj.endDateForString=  $('#endDateInfo').val();
    eventObj.regDateForString=$('#regDateInfo').val();

    $.ajax({
        url: "/event/"+eventObj.idx,
        method: "post",
        data: JSON.stringify(eventObj),
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            alert(data);
            window.location.reload();
        },
        error: function(data){
            alert(JSON.stringify(data));
        }

    });


}

