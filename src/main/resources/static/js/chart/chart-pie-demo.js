/**
 * Font chữ và màu
 * BTTu - 22/12/2020
 */
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

/**
 * Data trong thống kê độ tuổi
 * BTTu - 22/12/2020
 */
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: ["< 13 tuổi", "13 - 18 tuổi", "18 - 25 tuổi", "25 - ... tuổi"],
    datasets: [{
      data: [10.21, 20.58, 13.25, 3.32],
      backgroundColor: ['#dc3545', '#007bff', '#ffc107', '#28a745'],
    }],
  },
});
