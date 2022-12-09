
function setEventMasterIdxToRemove(eventmasterIdx){
    $('#eventMasterIdxToRemove').attr('data-eventmasteridx', eventmasterIdx);
}

function removeSelectedEventBanner(eventMasterIdx) {
    $.ajax({
        url: "/event_banner/" + eventMasterIdx,
        method: 'delete',
        success: function (data) {
            alert(data.message);
            window.location.reload();
        },
        error: function (data) {
            alert(JSON.stringify(data));
        }
    });
}


function setEventBannerDetail(sentOne) {

    let eventBannerDetail = {
        'eventMasterIdxInfo' : sentOne[0].innerText,
        'bannerInfo': sentOne[1].innerText,
        'titleInfo': sentOne[2].innerText,
        'descriptionInfo': sentOne[3].innerText,
        'periodInfo': sentOne[4].innerText,
        'hideInfo': sentOne[5].innerText,
        'eventUrlInfo':sentOne[6].innerText
    }

    $('#eventMasterIdxInfo').text(eventBannerDetail['eventMasterIdxInfo']);
    $('#periodInfo').text(eventBannerDetail['periodInfo']);
    $('#bannerInfo').attr('style','background-image:url(/file/'+eventBannerDetail['bannerInfo']+');  display: inline-block');
    $('#bannerInfo').data('filename',''+eventBannerDetail['bannerInfo']);

    $('#titleInfo').val(eventBannerDetail['titleInfo']);
    $('#descriptionInfo').val(eventBannerDetail['descriptionInfo']);

    if( eventBannerDetail['hideInfo'] == 'true' ){
        $('#hideInfo').prop("checked", true);
    }else{
        $('#hideInfo').prop("checked", false);
    }

    $('#eventUrlInfo').val(eventBannerDetail['eventUrlInfo']);
}

function submitEditEventBanner() {

    let eventBannerObj = {};

    eventBannerObj.evtBannerFileName = $('#bannerInfo').data('filename'); //새 이미지 파일을 올리지 않았을때
    if($('#bannerInfo').attr('data-filename') != undefined){
        eventBannerObj.evtBannerFileName  = $('#bannerInfo').attr('data-filename'); //새 이미지 파일올렸을때
    }
    //드랍존에서 data가 먹히지 않음

    eventBannerObj.eventMasterIdx = $('#eventMasterIdxInfo').text();
    eventBannerObj.period = $('#periodInfo').text();
    eventBannerObj.title = $('#titleInfo').val();
    eventBannerObj.description= $('#descriptionInfo').val();
    eventBannerObj.hide= $('#hideInfo').is(':checked');
    eventBannerObj.evtUrl= $('#eventUrlInfo').val();


    $.ajax({
        url: "/event_banner/"+eventBannerObj.eventMasterIdx,
        method: "post",
        data: JSON.stringify(eventBannerObj),
        contentType: 'application/json; charset=utf-8',
        success: function(data){
            alert(data.message);
            window.location.reload();
        },
        error: function(data){
            alert(JSON.stringify(data));
        }

    });

}

function toggleEditModal(){
    $('#edit-event-modal').toggle();
}

