function updateComment() {
    var commentBean = {
        email:$("#commentEmail").val(),
        content: $("#commentContent").val(),
        post_num: $("#commentPostNum").val()
    };
    $.ajax({
        url: "/view",
        type: "POST",
        data: commentBean,
    })
        .done(function (fragment) {
            $('#commentTable').replaceWith(fragment);
        });
}