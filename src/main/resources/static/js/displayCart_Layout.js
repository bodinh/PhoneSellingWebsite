function addnewProduct(maSP, maDH) {
    if(maDH < 0){
        window.location="http://localhost:8080/cart";
    }else {
        $.ajax({
            url:"http://localhost:8080/api/cart/addnew/"+maSP+"/"+maDH,
            method:"get",
            success:function (data) {
                $("#basket").empty();
                var totalPrice=0;
                var numberProduct =0;
                var listItem = "<ul class=\"dropdown-menu\">\n";
                var itemInList = "";
                data.forEach(item =>{
                    totalPrice += item.tonggia;
                    numberProduct += item.soluong;
                    var maSP = item.sanpham.maSp;
                    var anh = item.sanpham.anh;
                    itemInList += "                                        <li>\n" +
                        "                                            <div class=\"basket-item\">\n" +
                        "                                                <div class=\"row\">\n" +
                        "                                                    <div class=\"col-xs-4 col-sm-4 no-margin text-center\">\n" +
                        "                                                        <div class=\"thumb\">\n" +
                        "                                                            <img alt='/image/ "+maSP  + "/" + anh +" ' src='/image/"+maSP + "/" + anh + " ' />\n" +
                        "                                                        </div>\n" +
                        "                                                    </div>\n" +
                        "                                                    <div class=\"col-xs-8 col-sm-8 no-margin\">\n" +
                        "                                                        <div class=\"title\">Blueberry</div>\n" +
                        "                                                        <div class=\"price\">$270.00</div>\n" +
                        "                                                    </div>\n" +
                        "                                                </div>\n" +
                        "                                                <a class=\"close-btn\" onclick='cf()' href=\"#\"></a>\n" +
                        "                                            </div>\n" +
                        "                                        </li>\n"
                })

                listItem = listItem + itemInList + " <li class=\"checkout\">\n" +
                    "                                            <div class=\"basket-item\">\n" +
                    "                                                <div class=\"row\">\n" +
                    "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                    "                                                        <a href=\"cart\" class=\"le-button inverse\">View cart</a>\n" +
                    "                                                    </div>\n" +
                    "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                    "                                                        <a href=\"/user/Cart/CheckOut\" class=\"le-button\">Checkout</a>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                            </div>\n" +
                    "                                        </li>\n" +
                    "\n" +
                    "                                    </ul>";

                var totalPriceS = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                $("#basket").append("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\n" +
                    "                                        <div class=\"basket-item-count\">\n" +
                    "                                            <span class=\"count\">"+numberProduct+"</span>\n" +
                    "                                            <img src=\"/assets/images/icon-cart.png\" alt=\"\"/>\n" +
                    "                                        </div>\n" +
                    "\n" +
                    "                                        <div class=\"total-price-basket\">\n" +
                    "                                            <span class=\"lbl\">giỏ hàng:</span>\n" +
                    "                                            <span class=\"total-price\">\n" +
                    "                                                <span class=\"sign\"></span><span class=\"value\">"+totalPriceS +" VNĐ</span>\n" +
                    "                                            </span>\n" +
                    "                                        </div>\n" +
                    "                                    </a>\n" +
                    "\n" + listItem)
            }
        })
    }
}

function cf() {
    if (confirm('Bạn có chắc xoá sản phẩm này?')) {
        // delete it!
    } else {
        // Do nothing!
    }
}

function getFirstCart(maDH){
    $.ajax({
        url:"http://localhost:8080/api/cart/list/"+maDH,
        method:"get",
        success:function (data) {
            var totalPrice=0;
            var numberProduct =0;
            var listItem = "<ul class=\"dropdown-menu\">\n";
            var itemInList = "";
            data.forEach(item =>{
                totalPrice += item.tonggia;
                numberProduct += item.soluong;
                var maSP = item.sanpham.maSp;
                var anh = item.sanpham.anh;
                itemInList += "                                        <li>\n" +
                    "                                            <div class=\"basket-item\">\n" +
                    "                                                <div class=\"row\">\n" +
                    "                                                    <div class=\"col-xs-4 col-sm-4 no-margin text-center\">\n" +
                    "                                                        <div class=\"thumb\">\n" +
                    "                                                            <img alt='/image/ "+maSP  + "/" + anh +" ' src='/image/"+maSP + "/" + anh + " ' />\n" +
                    "                                                        </div>\n" +
                    "                                                    </div>\n" +
                    "                                                    <div class=\"col-xs-8 col-sm-8 no-margin\">\n" +
                    "                                                        <div class=\"title\">Blueberry</div>\n" +
                    "                                                        <div class=\"price\">$270.00</div>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                                <a class=\"close-btn\" onclick='cf()' href=\"#\"></a>\n" +
                    "                                            </div>\n" +
                    "                                        </li>\n"
            })

            listItem = listItem + itemInList + " <li class=\"checkout\">\n" +
                "                                            <div class=\"basket-item\">\n" +
                "                                                <div class=\"row\">\n" +
                "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                "                                                        <a href=\"cart\" class=\"le-button inverse\">View cart</a>\n" +
                "                                                    </div>\n" +
                "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                "                                                        <a href=\"/user/Cart/CheckOut\" class=\"le-button\">Checkout</a>\n" +
                "                                                    </div>\n" +
                "                                                </div>\n" +
                "                                            </div>\n" +
                "                                        </li>\n" +
                "\n" +
                "                                    </ul>";


            var totalPriceS = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            $("#basket").append("        <a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">\n" +
                "                                        <div class=\"basket-item-count\">\n" +
                "                                            <span class=\"count\">"+numberProduct+"</span>\n" +
                "                                            <img src=\"/assets/images/icon-cart.png\" alt=\"\"/>\n" +
                "                                        </div>\n" +
                "\n" +
                "                                        <div class=\"total-price-basket\">\n" +
                "                                            <span class=\"lbl\">giỏ hàng:</span>\n" +
                "                                            <span class=\"total-price\">\n" +
                "                                                <span class=\"sign\"></span><span class=\"value\">"+totalPriceS+" VNĐ</span>\n" +
                "                                            </span>\n" +
                "                                        </div>\n" +
                "                                    </a>\n" +
                "\n" + listItem)
        }
    })
}
