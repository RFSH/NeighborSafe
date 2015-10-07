$(function () {
    $('.ui.accordion')
        .accordion()
    ;
    $("input[type='checkbox']").checkbox();

    $('#delete-modal').modal();
    $(".delete-acc").on('click', function () {
        console.log('click to click');
        $('#delete-modal').modal("show");
    });

    $("#open-advanced").on('click', function () {
        var $t = $("#open-advanced").find("i");
        if ($t.hasClass("down")) {
            $t.removeClass("down").addClass("up");
        } else {
            $t.removeClass("up").addClass("down");
        }
        $("#hidden-parts").transition('slide down');
    });

});