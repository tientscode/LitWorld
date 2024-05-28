$(document).ready(function () {
    var max_length = 20;

    $(".tree-title").each(function () {
        var text = $(this).text();
        if (text.length > max_length) {
            $(this).text(text.substr(0, max_length) + '...');
        }
    });
});
