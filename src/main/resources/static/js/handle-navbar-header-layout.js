$(document).ready(function () {
    $.ajax({
        url: "/api/loaisps/getLoaiSpHangSp",
        method: "get",
        success: function (data) {
            for (let i = 0; i < data.length; i++) {
                var listHangSxs = "";
                data[i].hangSXES.forEach(element=> {
                    listHangSxs += "<li><a href=\"#\">"+element.tenhang+"</a></li>\n";
                })

                $("#navBar").append("                            <li class=\"dropdown\">\n" +
                    "                                <a href=\"#\" class=\"dropdown-toggle\" data-hover=\"dropdown\" data-toggle=\"dropdown\">" + data[i].tenLoai + "</a>\n" +
                    "                                <ul class=\"dropdown-menu\">\n" + listHangSxs +
                    "                                </ul>\n" +
                    "                            </li>");

                $("#allCategories").append("<li role=\"presentation\"><a role=\"menuitem\" tabindex=\"-1\" href=\"#\">"+data[i].tenLoai+"</a></li>");

            }
        },async:false
    })
});