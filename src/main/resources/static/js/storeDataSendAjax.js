var serverAddress = 'https://hacker-news.firebaseio.com/v0/topstories.json';

// jQuery의 .get 메소드 사용
// $.ajax({
//     url: ,
//     type: 'GET',
//     success: function onData (data) {
//         console.log(data);
//     },
//     error: function onError (error) {
//         console.error(error);
//     }
// });

function dataSend() {
    var data=$("#input").val();
    var messageDTO={
        result:data
    };
    $.ajax({
        url: "/dataSend",
        data: messageDTO,
        type:"POST",
    }).done(function (fragment) {
        $("#resultDiv").replaceWith(fragment);
    });
}