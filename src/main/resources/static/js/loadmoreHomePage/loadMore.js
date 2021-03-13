$(document).ready(function () {
    var startNoiBat = 8;
    var numberLoadMore = 4;
    var startMoi = 8;
    var startGiamGia = 8;
    $("#new").on('click', function () {
        $.ajax({
            url: "/api/sanphams/new/" + startMoi + "/" + numberLoadMore,
            method: "get",
            success: function (data) {
                startMoi += numberLoadMore;
                if (data.length < 1) {
                    $("#new").remove();
                } else {
                    if (data.length != 4) $("#new").remove();
                    data.forEach(item => {
                        var anh = item.anh.toString().replaceAll(" ", "");
                        var gia = item.gia.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        if(item.ishot == 1){
                            var displayHot = "flex";
                        }else {
                            var displayHot = "none";
                        }
                        $("#listnew").append(" <div class=\"col-sm-4 col-md-3  no-margin product-item-holder hover\">\n" +
                            "                                    <div class=\"product-item\">\n" +
                            "                                        <div class=\"ribbon blue\"><span>mới !</span></div>\n" +
                            "                                    <div id=\"divCheckbox\" class=\"ribbon green\" style=\"display: "+displayHot+";\"><span>bán chạy</span></div>"+
                            "                                        <div class=\"image\">\n" +
                            "                                            <img alt= '" + item.tenSp + "' src='/image/" + item.maSp + "/" + anh + " '/>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"body\">\n" +
                            "                                            <div class=\"label-discount green\">-10%</div>\n" +
                            "                                            <div class=\"title\">\n" +
                            "                                                <a href=\"single-product.html\">" + item.tenSp + "</a>\n" +
                            "                                            </div>\n" +
                            "                                            <div class=\"brand\">sony</div>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"prices\">\n" +
                            "                                            <div class=\"price-prev\">" + gia + "</div>\n" +
                            "                                            <div class=\"price-current pull-right\">" + gia + " VNĐ</div>\n" +
                            "                                        </div>\n" +
                            "\n" +
                            "<div class=\"hover-area\">\n" +
                            "                                            <div class=\"add-cart-button\">\n" +
                            "                                                <form action=\"/cart\" method=\"post\">\n" +
                            "                                                    <button type=\"submit\" class=\"le-button\">thêm vào giỏ</button>\n" +
                            "                                                    <input type=\"hidden\" name=\"maSP\" value=\""+item.maSp+"\"/>\n" +
                            "                                                </form>\n" +
                            "                                            </div>\n" +
                            "                                        </div>" +
                            "                                    </div>\n" +
                            "                                </div>");
                    });
                }
            }
        })
    });
    $("#noibat").on('click', function () {
        $.ajax({
            url: "/api/sanphams/hot/" + startNoiBat + "/" + numberLoadMore,
            method: "get",
            success: function (data) {
                startNoiBat += numberLoadMore;
                if (data.length < 1) {
                    $("#noibat").remove();
                } else {
                    if (data.length != 4) $("#noibat").remove();
                    data.forEach(item => {
                        var anh = item.anh.toString().replaceAll(" ", "");
                        var gia = item.gia.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        if(item.isnew == 1){
                            var displayNew = "flex";
                        }else {
                            var displayNew = "none";
                        }
                        $("#listNoiBat").append(" <div class=\"col-sm-4 col-md-3  no-margin product-item-holder hover\">\n" +
                            "                                    <div class=\"product-item\">\n" +
                            "                                         <div class=\"ribbon green\"><span>bán chạy</span></div>\n" +
                            "                                        <div id=\"divCheckbox\" class=\"ribbon blue\" style=\"display: "+displayNew+";\"><span>mới !</span></div>" +
                            "                                        <div class=\"image\">\n" +
                            "                                            <img alt= '" + item.tenSp + "' src='/image/" + item.maSp + "/" + anh + " '/>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"body\">\n" +
                            "                                            <div class=\"label-discount green\">-30%</div>\n" +
                            "                                            <div class=\"title\">\n" +
                            "                                                <a href=\"single-product.html\">" + item.tenSp + "</a>\n" +
                            "                                            </div>\n" +
                            "                                            <div class=\"brand\">sony</div>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"prices\">\n" +
                            "                                            <div class=\"price-prev\">" + gia + "</div>\n" +
                            "                                            <div class=\"price-current pull-right\">" + gia + " VNĐ</div>\n" +
                            "                                        </div>\n" +
                            "\n" +
                            "<div class=\"hover-area\">\n" +
                            "                                            <div class=\"add-cart-button\">\n" +
                            "                                                <form action=\"/cart\" method=\"post\">\n" +
                            "                                                    <button type=\"submit\" class=\"le-button\">thêm vào giỏ</button>\n" +
                            "                                                    <input type=\"hidden\" name=\"maSP\" value=\""+item.maSp+"\"/>\n" +
                            "                                                </form>\n" +
                            "                                            </div>\n" +
                            "                                        </div>" +
                            "                                    </div>\n" +
                            "                                </div>");
                    });
                }
            }
        })
    });
    $("#giamgia").on('click', function () {
        $.ajax({
            url: "/api/sanphams/sale/" + startGiamGia + "/" + numberLoadMore,
            method: "get",
            success: function (data) {
                startGiamGia += numberLoadMore;
                if (data.length < 1) {
                    $("#giamgia").remove();
                } else {
                    if (data.length != 4) $("#giamgia").remove();
                    data.forEach(item => {
                        var anh = item.anh.toString().replaceAll(" ", "");
                        var gia = item.gia.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                        $("#listgiamgia").append(" <div class=\"col-sm-4 col-md-3  no-margin product-item-holder hover\">\n" +
                            "                                    <div class=\"product-item\">\n" +
                            "                                        <div class=\"ribbon red\"><span>giảm giá</span></div>\n" +
                            "                                        <div class=\"image\">\n" +
                            "                                            <img alt= '" + item.tenSp + "' src='/image/" + item.maSp + "/" + anh + " '/>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"body\">\n" +
                            "                                            <div class=\"label-discount green\">-10%</div>\n" +
                            "                                            <div class=\"title\">\n" +
                            "                                                <a href=\"single-product.html\">" + item.tenSp + "</a>\n" +
                            "                                            </div>\n" +
                            "                                            <div class=\"brand\">sony</div>\n" +
                            "                                        </div>\n" +
                            "                                        <div class=\"prices\">\n" +
                            "                                            <div class=\"price-prev\">" + gia + "</div>\n" +
                            "                                            <div class=\"price-current pull-right\">" + gia + " VNĐ</div>\n" +
                            "                                        </div>\n" +
                            "\n" +
                            "<div class=\"hover-area\">\n" +
                            "                                            <div class=\"add-cart-button\">\n" +
                            "                                                <form action=\"/cart\" method=\"post\">\n" +
                            "                                                    <button type=\"submit\" class=\"le-button\">thêm vào giỏ</button>\n" +
                            "                                                    <input type=\"hidden\" name=\"maSP\" value=\""+item.maSp+"\"/>\n" +
                            "                                                </form>\n" +
                            "                                            </div>\n" +
                            "                                        </div>" +
                            "                                    </div>\n" +
                            "                                </div>");
                    });
                }
            }
        })
    });
})