$(document).ready(function () {
    $.ajax({
        url: "api/sanphams/random",
        method: "get",
        success: function (data) {
            data.forEach(element =>{
                var anh = element.anh;
                anh = anh.replaceAll(" ","");
                var gia =element.gia.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
                $("#listProductRelation").append("                            <div class=\"owl-item\" style=\"width: 195px;\">\n" +
                    "                                <div class=\"owl-wrapper-outer\">\n" +
                    "                                    <div class=\"owl-wrapper-outer\">\n" +
                    "                                        <div class=\"owl-wrapper\" style=\"width: 4680px; left: 0px; display: block;\">\n" +
                    "\n" +
                    "                                            <div class=\"owl-item\" style=\"width: 195px;\">\n" +
                    "                                                <div class=\"no-margin carousel-item product-item-holder size-small hover\">\n" +
                    "                                                    <div class=\"product-item\">\n" +
                    "                                                        <div class=\"ribbon blue\"><span>Mới nhập</span></div>\n" +
                    "                                                        <div class=\"image\">\n" +
                    "                                                            <img alt=\""+element.tenSp+"\"\n" +
                    "                                                                 src=\"/image/"+element.maSp+"/"+anh+"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      \">\n" +
                    "                                                        </div>\n" +
                    "                                                        <div class=\"body\">\n" +
                    "                                                            <div class=\"label-discount clear\"></div>\n" +
                    "                                                            <div class=\"title\">\n" +
                    "                                                                <a href=\"/Product/index/4000\">"+element.tenSp+"</a>\n" +
                    "                                                            </div>\n" +
                    "\n" +
                    "                                                        </div>\n" +
                    "                                                        <div class=\"prices\">\n" +
                    "                                                            <div class=\"price-current text-right\">"+gia+" VNĐ</div>\n" +
                    "                                                        </div>\n" +
                    "                                                        <div class=\"hover-area\">\n" +
                    "\n" +
                    "                                                        </div>\n" +
                    "                                                    </div>\n" +
                    "\n" +
                    "\n" +
                    "                                                </div>\n" +
                    "                                            </div>\n" +
                    "\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </div>\n" +
                    "                            </div>")
            })
        }, sync: false
    })
})