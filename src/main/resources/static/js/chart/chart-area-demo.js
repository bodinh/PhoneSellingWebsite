/**
 * Font chữ và màu
 * BTTu - 22/12/2020
 */
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

/**
 * Data thống kê lượt xem
 * BTTu - 22/12/2020
 */

$(document).ready(function () {
    $("#area-chart").remove();
    $("#contain-area-chart").append("<canvas id=\"area-chart\" width=\"100%\" height=\"40\"></canvas>");
    $.ajax({
        url: "/api/doanhthu/lai",
        method: "get",
        success: function (jdata) {
            // Area chart
            var data = [];
            var label = [];

            for (var i = 0; i < 1; i++) {
                var item = jdata[0];
                item.doanhthu.forEach(it => {
                    console.log(it.thang + "     " + it.doanhThuThang);
                    label.push("Tháng " + it.thang.toString());
                    data.push(it.doanhThuThang / 1000000);
                })
            }

            new Chart(document.getElementById("area-chart"), {
                type: 'line',
                data: {
                    labels: label,
                    datasets: [{
                        label: "Doanh thu (Triệu VNĐ)",
                        lineTension: 0.3,
                        backgroundColor: "rgba(2,117,216,0.2)",
                        borderColor: "rgba(2,117,216,1)",
                        pointRadius: 5,
                        pointBackgroundColor: "rgba(2,117,216,1)",
                        pointBorderColor: "rgba(255,255,255,0.8)",
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(2,117,216,1)",
                        pointHitRadius: 50,
                        pointBorderWidth: 2,
                        data: data,
                    }],
                },
                options: {
                    scales: {
                        xAxes: [{
                            time: {
                                unit: 'month'
                            },
                            gridLines: {
                                display: false
                            },
                            ticks: {
                                maxTicksLimit: 7
                            }
                        }],
                        yAxes: [{
                            gridLines: {
                                color: "rgba(0, 0, 0, .125)",
                            }
                        }],
                    },
                    legend: {
                        display: false
                    }
                }
            });
        }
    })
});

function laiThuDuoc(nam) {
    $("#area-chart").remove();
    $("#contain-area-chart").append("<canvas id=\"area-chart\" width=\"100%\" height=\"40\"></canvas>");
    $.ajax({
        url: "/api/doanhthu/lai",
        method: "get",
        success: function (jdata) {
            // Area chart
            var data = [];
            var label = [];

            jdata.forEach(item => {
                if (item.nam == nam) {
                    item.doanhthu.forEach(it => {
                        console.log(it.thang + "     " + it.doanhThuThang);
                        label.push("Tháng " + it.thang.toString());
                        data.push(it.doanhThuThang / 1000000);
                    })
                }
            })

            new Chart(document.getElementById("area-chart"), {
                type: 'line',
                data: {
                    labels: label,
                    datasets: [{
                        label: "Doanh thu (Triệu VNĐ)",
                        lineTension: 0.3,
                        backgroundColor: "rgba(2,117,216,0.2)",
                        borderColor: "rgba(2,117,216,1)",
                        pointRadius: 5,
                        pointBackgroundColor: "rgba(2,117,216,1)",
                        pointBorderColor: "rgba(255,255,255,0.8)",
                        pointHoverRadius: 5,
                        pointHoverBackgroundColor: "rgba(2,117,216,1)",
                        pointHitRadius: 50,
                        pointBorderWidth: 2,
                        data: data,
                    }],
                },
                options: {
                    scales: {
                        xAxes: [{
                            time: {
                                unit: 'month'
                            },
                            gridLines: {
                                display: false
                            },
                            ticks: {
                                maxTicksLimit: 7
                            }
                        }],
                        yAxes: [{
                            gridLines: {
                                color: "rgba(0, 0, 0, .125)",
                            }
                        }],
                    },
                    legend: {
                        display: false
                    }
                }
            });
        }
    })
}


