function doanhthu(nam) {
  $("#bar-chart").remove();
  $("#contain-bar-chart").append("<canvas id=\"bar-chart\" width=\"100%\" height=\"40\"></canvas>");
  $.ajax({
    url: "/api/doanhthu/",
    method: "get",
    success: function (jdata) {
      // Bar chart
      var data = [];
      var label = [];
      jdata.forEach(item => {
        if (item.nam == nam) {
          item.doanhthu.forEach(it => {
            label.push("Tháng "+it.thang.toString());
            data.push(it.doanhThuThang / 1000000);
          });
        }
      });
      new Chart(document.getElementById("bar-chart"), {
        type: 'bar',
        data: {
          labels: label,
          datasets: [
            {
              label: "Doanh thu ( Triệu VNĐ)",
              backgroundColor: ["#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd"],
              data: data
            }
          ]
        },options: {
          scales: {
            xAxes: [{
              time: {
                unit: 'month'
              },
              gridLines: {
                display: true
              },
              ticks: {
                maxTicksLimit: 9
              }
            }],
            yAxes: [{
              gridLines: {
                display: true
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

$(document).ready(function () {
  $("#bar-chart").remove();
  $("#contain-bar-chart").append("<canvas id=\"bar-chart\" width=\"100%\" height=\"40\"></canvas>");
  $.ajax({
    url: "/api/doanhthu/",
    method: "get",
    success: function (jdata) {
      // Bar chart
      var data = [];
      var label = [];
      for (var i = 0; i < 1; i++) {
        var item = jdata[0];
        item.doanhthu.forEach(it => {
          label.push("Tháng " + it.thang.toString());
          data.push(it.doanhThuThang / 1000000);
        });
      }
      new Chart(document.getElementById("bar-chart"), {
        type: 'bar',
        data: {
          labels: label,
          datasets: [
            {
              label: "Doanh thu ( Triệu VNĐ)",
              backgroundColor: ["#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd", "#3e95cd"],
              data: data
            }
          ]
        },options: {
          scales: {
            xAxes: [{
              time: {
                unit: 'month'
              },
              gridLines: {
                display: true
              },
              ticks: {
                maxTicksLimit: 9
              }
            }],
            yAxes: [{
              gridLines: {
                display: true
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
