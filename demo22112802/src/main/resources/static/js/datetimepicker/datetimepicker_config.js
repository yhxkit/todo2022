/**
 * Prerequisite : js.datetimepicker.full.js
 *  Requires 2 input elements containing Ids as "startDateInfo" and "endDateInfo" respectively
 * */

jQuery(function(){
    jQuery('#startDateInfo').datetimepicker({
        format:'Y-m-d H:i:00',
        onShow:function( ct ){
            this.setOptions({
                maxDate:jQuery('#endDateInfo').val()?jQuery('#endDateInfo').val():false
            })
        }
    });
    jQuery('#endDateInfo').datetimepicker({
        format:'Y-m-d H:i:00',
        onShow:function( ct ){
            this.setOptions({
                minDate:jQuery('#startDateInfo').val()?jQuery('#startDateInfo').val():false
            })
        }
    });
});