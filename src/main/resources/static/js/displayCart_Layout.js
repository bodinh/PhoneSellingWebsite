(function( $ ){
    $.fn.UpdateCart = function(sl_sp, tongTien) {
        $('.total-price-basket .total-price .value').text(tongTien);
        $('.basket-item-count .count').text(sl_sp);
    };
    $.fn.CallUpdate = function () {
        $.ajax({
            type: "get",
            url: "/layout/get-data-layout" ,
            dataType: 'json',
            success: function(result) {
                var obj = JSON.parse(result);
                $.fn.UpdateCart(obj.sl, obj.tongTien);
            }
        });
    }
    $.fn.AddToCart = function (masp) {
        return $.ajax({
        type: "post",
        url: "/cart" ,
        dataType: 'html',
        data: {maSP : masp},
        success: function() {
            alert("them thanh cong")
        }
    });
    }
})( jQuery );

