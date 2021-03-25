function addnewProduct(maSP, maDH) {
    if(maDH == null){
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
                    var maSP = item.sanPham.sanphamEntity.maSp;
                    var anh = item.sanPham.sanphamEntity.anh;
                    var tenSP = item.sanPham.sanphamEntity.tenSp;
                    var gia = item.sanPham.sanphamEntity.gia;
                    var giaMoi = (gia * (100 - item.sanPham.sPkhuyenmaiEntity.giamgia) / 100).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                    var number = item.soluong;
                    itemInList += "                                        <li>\n" +
                        "                                            <div class=\"basket-item\">\n" +
                        "                                                <div class=\"row\">\n" +
                        "                                                    <div class=\"col-xs-4 col-sm-4 no-margin text-center\">\n" +
                        "                                                        <div class=\"thumb\">\n" +
                        "                                                            <img alt='/image/ "+maSP  + "/" + anh +" ' src='/image/"+maSP + "/" + anh + " ' />\n" +
                        "                                                        </div>\n" +
                        "                                                    </div>\n" +
                        "                                                    <div class=\"col-xs-8 col-sm-8 no-margin\">\n" +
                        "                                                        <div class=\"title\">"+tenSP+"</div>\n" +
                        "                                                        <div class=\"price\">"+giaMoi+" VNĐ * "+number+"</div>\n" +
                        "                                                    </div>\n" +
                        "                                                </div>\n" +
                        "                                                <a class=\"close-btn\" onclick='cf("+maSP+")' href=\"#\"></a>\n" +
                        "                                            </div>\n" +
                        "                                        </li>\n"
                })

                listItem = listItem + itemInList + " <li class=\"checkout\">\n" +
                    "                                            <div class=\"basket-item\">\n" +
                    "                                                <div class=\"row\">\n" +
                    "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                    "                                                        <a style='font-size: 13px;' href=\"/cart\" class=\"le-button inverse\">Giỏ hàng</a>\n" +
                    "                                                    </div>\n" +
                    "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                    "                                                        <a style='font-size: 13px;' href=\"/cart/checkout\" class=\"le-button\">Thanh toán</a>\n" +
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
                alert("Thêm vào giỏ hàng thành công.");
            }
        })
    }
}

function cf(maSP) {
    if (confirm('Bạn có chắc xoá sản phẩm này?')) {
        // delete it!
        window.location = "/cart/deleteInBasket?maSP="+maSP;
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
                var maSP = item.sanPham.sanphamEntity.maSp;
                var anh = item.sanPham.sanphamEntity.anh;
                var tenSP = item.sanPham.sanphamEntity.tenSp;
                var gia = item.sanPham.sanphamEntity.gia;
                var giaMoi = (gia * (100 - item.sanPham.sPkhuyenmaiEntity.giamgia) / 100).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                var number = item.soluong;
                itemInList += "                                        <li>\n" +
                    "                                            <div class=\"basket-item\">\n" +
                    "                                                <div class=\"row\">\n" +
                    "                                                    <div class=\"col-xs-4 col-sm-4 no-margin text-center\">\n" +
                    "                                                        <div class=\"thumb\">\n" +
                    "                                                            <img alt='/image/ "+maSP  + "/" + anh +" ' src='/image/"+maSP + "/" + anh + " ' />\n" +
                    "                                                        </div>\n" +
                    "                                                    </div>\n" +
                    "                                                    <div class=\"col-xs-8 col-sm-8 no-margin\">\n" +
                    "                                                        <div class=\"title\">"+tenSP+"</div>\n" +
                    "                                                        <div class=\"price\">"+giaMoi+" VNĐ * "+number+"</div>\n" +
                    "                                                    </div>\n" +
                    "                                                </div>\n" +
                    "                                                <a class=\"close-btn\" onclick='cf("+maSP+")' href=\"#\"></a>\n" +
                    "                                            </div>\n" +
                    "                                        </li>\n"
            })

            listItem = listItem + itemInList + " <li class=\"checkout\">\n" +
                "                                            <div class=\"basket-item\">\n" +
                "                                                <div class=\"row\">\n" +
                "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                "                                                        <a style='font-size: 13px;' href=\"/cart\" class=\"le-button inverse\">Giỏ hàng</a>\n" +
                "                                                    </div>\n" +
                "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                "                                                        <a style='font-size: 13px;' href=\"/cart/checkout\" class=\"le-button\">Thanh Toán</a>\n" +
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
function addnewProductInLoadMore(maSP) {
        $.ajax({
            url:"http://localhost:8080/api/cart/addnew/"+maSP,
            method:"get",
            success:function (data) {
               try{
                   $("#basket").empty();
                   var totalPrice=0;
                   var numberProduct =0;
                   var listItem = "<ul class=\"dropdown-menu\">\n";
                   var itemInList = "";
                   data.forEach(item =>{
                       totalPrice += item.tonggia;
                       numberProduct += item.soluong;
                       var maSP = item.sanPham.sanphamEntity.maSp;
                       var anh = item.sanPham.sanphamEntity.anh;
                       var tenSP = item.sanPham.sanphamEntity.tenSp;
                       var gia = item.sanPham.sanphamEntity.gia;
                       var giaMoi = (gia * (100 - item.sanPham.sPkhuyenmaiEntity.giamgia) / 100).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                       var number = item.soluong;
                       itemInList += "                                        <li>\n" +
                           "                                            <div class=\"basket-item\">\n" +
                           "                                                <div class=\"row\">\n" +
                           "                                                    <div class=\"col-xs-4 col-sm-4 no-margin text-center\">\n" +
                           "                                                        <div class=\"thumb\">\n" +
                           "                                                            <img alt='/image/ "+maSP  + "/" + anh +" ' src='/image/"+maSP + "/" + anh + " ' />\n" +
                           "                                                        </div>\n" +
                           "                                                    </div>\n" +
                           "                                                    <div class=\"col-xs-8 col-sm-8 no-margin\">\n" +
                           "                                                        <div class=\"title\">"+tenSP+"</div>\n" +
                           "                                                        <div class=\"price\">"+giaMoi+" VNĐ * "+number+"</div>\n" +
                           "                                                    </div>\n" +
                           "                                                </div>\n" +
                           "                                                <a class=\"close-btn\" onclick='cf("+maSP+")' href=\"#\"></a>\n" +
                           "                                            </div>\n" +
                           "                                        </li>\n"
                   })

                   listItem = listItem + itemInList + " <li class=\"checkout\">\n" +
                       "                                            <div class=\"basket-item\">\n" +
                       "                                                <div class=\"row\">\n" +
                       "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                       "                                                        <a style='font-size: 13px;' href=\"/cart\" class=\"le-button inverse\">Giỏ hàng</a>\n" +
                       "                                                    </div>\n" +
                       "                                                    <div class=\"col-xs-12 col-sm-6\">\n" +
                       "                                                        <a style='font-size: 13px;' href=\"/cart/checkout\" class=\"le-button\">Thanh toán</a>\n" +
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
                   alert("Thêm vào giỏ hàng thành công.");
               }catch (e) {
                   window.location="http://localhost:8080/cart";
               }
            },
            fail:function () {
                window.location="http://localhost:8080/cart";
            },
            error:function () {
                window.location="http://localhost:8080/cart";
            }
        })

}
