function randomString() {
    const chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
    const string_length = 30;
    let randomstring = '';
    for (let i = 0; i < string_length; i++) {
        const rnum = Math.floor(Math.random() * chars.length);
        randomstring += chars.substring(rnum, rnum + 1);
    }
    return randomstring;
}

function getMessage(data){
    // alert("받은 에러"+JSON.stringify(data));
    alert(data['responseJSON'].message);
}

Dropzone.options.bannerImgUpload = { //camelized html id
    url: root + "/home_admin/banner/img",
    renameFile: function (file) {
        var oldName = file.name;
        var dotIdx = oldName.lastIndexOf('.');
        var newName = randomString() + oldName.substr(dotIdx);
        return newName;
    },
    dictDefaultMessage: "업로드할 배너 이미지를 드랍하세요",
    addRemoveLinks: true,
    dictCancelUpload: "취소",
    dictUploadCanceled: "취소완료",
    dictResponseError: "업로드 실패",
    thumbnail: function (file, dataUrl) {

        if (file.previewElement) {
            file.previewElement.classList.remove("dz-file-preview");
            var images = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
            for (var i = 0; i < images.length; i++) {
                var thumbnailElement = images[i];
                thumbnailElement.alt = file.name;
                thumbnailElement.src = dataUrl;

                //아래로는 올린 후 미리보기
                const thum = '<div class="image-container"><div class="controls">'
                    + '<a class="control-btn move">'
                    + '<i class="fa fa-arrows"></i>'
                    + '</a>'
                    + ' <a class="control-btn new" data-toggle="modal" data-target="#banner-info"  onclick = "bannerInfoToAppend = $(this).parent().parent().find(\'.uploadingimg\');  $(\'#bnTextInfo\').val(bannerInfoToAppend.attr(\'data-bntext\')); $(\'#bnTargetInfo\').val(bannerInfoToAppend.attr(\'data-bntarget\')); $(\'#bnCssClassInfo\').val(bannerInfoToAppend.attr(\'data-bncssclass\'));  $(\'#bnUrlInfo\').val(bannerInfoToAppend.attr(\'data-bnurl\'));  $(\'#bnNoteInfo\').val(bannerInfoToAppend.attr(\'data-bnnote\')); ">'
                    + '  <i class="fa fa-pencil"></i>'
                    + '</a>'
                    + ' <a class="control-btn remove" data-toggle="modal" data-target="#confirm-modal" onclick="bannerToRemove = $(this).parent().parent();">'
                    + '<i class="fa fa-trash-o"></i>'
                    + '</a>'
                    + '</div>'
                    + '<div class="image uploadingimg" style="background-image:url(' + dataUrl + ')" data-filename=' + file.upload.filename + ' onclick="hideOrNot($(this).find(\'span\').attr(\'class\'), $(this).find(\'span\'), $(this))"></div>'
                    + '</div>';


                $('#banner-thumbnail-container').append(thum);
                $('.modal').modal('hide'); // 올리고 바로 모달 꺼버림

            }
            setTimeout(function () {
                file.previewElement.classList.add("dz-image-preview");
            }, 1);
        }
    },
    init: function () {
        this.on("complete", function (file, serverResponse) {
            this.removeFile(file); //드랍존 리셋
        });
    }
};


Dropzone.options.backgroundImgUpload = { //camelized html id //배경 이미지는 css 쪽에서 관리해야 해서 리네임하지 않음
    url: root + "/home_admin/background/img",
    maxFiles: 1,
    dictDefaultMessage: "업로드할 배경 이미지를 드랍하세요",
    addRemoveLinks: true,
    dictCancelUpload: "취소",
    dictUploadCanceled: "취소완료",
    dictResponseError: "업로드 실패",
    thumbnail: function (file, dataUrl) {

        if (file.previewElement && file.status == "uploading") { // 파일 1개 이상 올라가면 file.status 가 error라서 처리하지 않음
            file.previewElement.classList.remove("dz-file-preview");
            var images = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
            var thumbnailElement = images[0]; //첫번째 이미지만 취급
            thumbnailElement.alt = file.name;
            thumbnailElement.src = dataUrl;

            //아래로는 올린 후 미리보기
            const thum = '<div class="image-container"><div class="controls">'
                + ' <a class="control-btn new" data-toggle="modal" data-target="#bg-info"  onclick = "bgInfoToAppend = $(this).parent().parent().find(\'#bg_img\'); $(\'#bgNoteInfo\').val(bgInfoToAppend.attr(\'data-bgnote\')); ">'
                + '  <i class="fa fa-pencil"></i>'
                + '</a>'
                + ' <a class="control-btn" data-toggle="modal" data-target="#confirm-modal" onclick="bgToRemove = $(this).parent().parent();">'
                + '<i class="fa fa-trash-o"></i>'
                + '</a>'
                + '</div>'
                + '<div id="bg_img" class="image" style="background-image:url(' + dataUrl + ')" data-filename=' + file.upload.filename + '></div>'
                + '</div>';

            $('#bg-thumbnail-container').append(thum); //썸네일 표시
            $('#add_bg_img').hide(); //이미지 올라가는 순간 이미지 추가 버튼 숨김
            $('.modal').modal('hide'); // 올리고 바로 모달 꺼버림

            setTimeout(function () {
                file.previewElement.classList.add("dz-image-preview");
            }, 1);

        }
    },
    init: function () {
        this.on("complete", function (file, serverResponse) {
            this.removeFile(file); //드랍존 썸네일을 삭제해서 계속해서 추가 가능하도록 설정
        });
    }
};


Dropzone.options.itemImgUpload = { //camelized html id
    url: root + "/home_admin/item/img",
    renameFile: function (file) {
        var oldName = file.name;
        var dotIdx = oldName.lastIndexOf('.');
        var newName = randomString() + oldName.substr(dotIdx);
        return newName;
    },
    dictDefaultMessage: "업로드할 아이템 이미지를 드랍하세요",
    addRemoveLinks: true,
    dictCancelUpload: "취소",
    dictUploadCanceled: "취소완료",
    dictResponseError: "업로드 실패",
    thumbnail: function (file, dataUrl) {

        if (file.previewElement) {
            file.previewElement.classList.remove("dz-file-preview");
            var images = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
            for (var i = 0; i < images.length; i++) {
                var thumbnailElement = images[i];
                thumbnailElement.alt = file.name;
                thumbnailElement.src = dataUrl;

                //아래로는 올린 후 미리보기
                const thum = '<div class="image-container"><div class="controls">'
                    + '<a class="control-btn move">'
                    + '<i class="fa fa-arrows"></i>'
                    + '</a>'
                    + ' <a class="control-btn new" data-toggle="modal" data-target="#item-info"  onclick = "itemInfoToAppend = $(this).parent().parent().find(\'.uploadingimg\');  $(\'#itemUrlInfo\').val(itemInfoToAppend.attr(\'data-itemurl\'));  $(\'#itemCssClassInfo\').val(itemInfoToAppend.attr(\'data-itemcssclass\'));  $(\'#itemNameInfo\').val(itemInfoToAppend.attr(\'data-itemname\'));  $(\'#itemNoteInfo\').val(itemInfoToAppend.attr(\'data-itemnote\')); ">'
                    + '  <i class="fa fa-pencil"></i>'
                    + '</a>'
                    + ' <a class="control-btn remove" data-toggle="modal" data-target="#confirm-modal" onclick="itemToRemove = $(this).parent().parent();">'
                    + '<i class="fa fa-trash-o"></i>'
                    + '</a>'
                    + '</div>'
                    + '<div class="image uploadingimg" style="background-image:url(' + dataUrl + ')" data-filename=' + file.upload.filename + '></div>'
                    + '</div>';

                $('#item-thumbnail-container').append(thum);
                $('.modal').modal('hide'); // 올리고 바로 모달 꺼버림

            }
            setTimeout(function () {
                file.previewElement.classList.add("dz-image-preview");
            }, 1);
        }
    },
    init: function () {
        this.on("complete", function (file, serverResponse) {
            this.removeFile(file);
        });
    }
};





Dropzone.options.evtBannerImgUpload = { //camelized html id
    url: root + "/event_banner/img",
    maxFiles: 1,
    renameFile: function (file) {
        var oldName = file.name;
        var dotIdx = oldName.lastIndexOf('.');
        var newName = randomString() + oldName.substr(dotIdx);
        return newName;
    },
    dictDefaultMessage: "업로드할 배너 이미지를 드랍하세요",
    addRemoveLinks: true,
    dictCancelUpload: "취소",
    dictUploadCanceled: "취소완료",
    dictResponseError: "업로드 실패",
    thumbnail: function (file, dataUrl) {

        if (file.previewElement && file.status == "uploading") { // 파일 1개 이상 올라가면 file.status 가 error라서 처리하지 않음
            file.previewElement.classList.remove("dz-file-preview");
            var images = file.previewElement.querySelectorAll("[data-dz-thumbnail]");
            var thumbnailElement = images[0]; //첫번째 이미지만 취급
            thumbnailElement.alt = file.name;
            thumbnailElement.src = dataUrl;

            console.log(dataUrl);
            console.log(file.upload.filename);

            $('#bannerInfo').attr('style', 'display: inline-block; background-image:url('+dataUrl+')');
            $('#bannerInfo').attr('data-filename', file.upload.filename);

            $('#modal-media').modal('hide'); // 올리고 임지 업로드 모달 꺼버림
            $('#edit-event-modal').toggle(); // 다른 에딧 모달 다시 켜


            setTimeout(function () {
                file.previewElement.classList.add("dz-image-preview");
            }, 1);

        }


    },
    init: function () {
        this.on("complete", function (file, serverResponse) {
            this.removeFile(file); //드랍존 리셋
        });
    }
};
