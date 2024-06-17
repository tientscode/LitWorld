$(document).ready(function () {
    var max_length = 17;
    $(".tree-title").each(function () {
        var text = $(this).text();
        console.log(text.length);
        if (text.length > max_length) {
            $(this).text(text.substr(0, max_length) + '...');
        }
    });
});


// function addStory(storyId) {
//     // Thêm logic xử lý thêm truyện vào giỏ hàng tại đây
//     console.log("Adding story with ID:", storyId);
//     // Ví dụ logic: Gửi yêu cầu AJAX để thêm truyện vào giỏ hàng
//     // $.post('/add-to-cart', { id: storyId }, function(response) {
//     //     alert('Truyện đã được thêm vào giỏ hàng!');
//     // });
// }
