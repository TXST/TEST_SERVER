/**
 * Created by Administrator on 2016/5/12.
 */
// start
$(function() {

	var data = [];
	var date = [];

	$('#TEND h2').click(
			function() {

				console.log(this.className);
				console.log($('#endtime').val());
				context.clearRect(0, 0, 600, 400);
				data = [];
				date = [];
				$.ajax({
					type : "post",
					url : "../HistoryQueryServlet",
					data : "command=" + this.className + "&endtime="
							+ $('#endtime').val(),
					dataType : "json",
					success : function(result) {

						$.each(result.dat, function(index, member) {
							console.log(member);
							console.log(index);
							date[index] = member;
						});
						console.log(date);
						$.each(result.avgValue, function(index, member) {
							console.log(member);
							console.log(index);
							data[index] = member;
						});
						console.log(data);

						context.beginPath(); // 坐标轴
						context.moveTo(padding.left, padding.top);
						context.lineTo(padding.left, HEIGHT - padding.bottom);
						context.lineTo(WIDTH - padding.right, HEIGHT
								- padding.bottom);
						context.stroke();

						// 绘制箭头
						context.beginPath();
						context.moveTo(padding.left - 5, padding.top + 10);
						context.lineTo(padding.left, padding.top);
						// context.lineTo(padding.left+5,padding.top+10);
						context.stroke();

						context.beginPath();
						context.moveTo(WIDTH - padding.right - 10, HEIGHT
								- padding.bottom - 5);
						context.lineTo(WIDTH - padding.right, HEIGHT
								- padding.bottom);
						// context.lineTo(WIDTH-padding.right-10,HEIGHT-padding.bottom+5);
						context.stroke();

						// 绘制X轴坐标点
						var xLength = (WIDTH - padding.left - padding.right)
								/ data.length;
						for (var i = 0; i < data.length; i++) {
							context.beginPath();
							context.moveTo(padding.left + xLength * i, HEIGHT
									- padding.bottom);
							context.lineTo(padding.left + xLength * i, HEIGHT
									- padding.bottom - 5);
							context.stroke();
						}
						// 绘制x轴坐标
						for (var i = 1; i <= data.length; i++) {
							context.font = "12px 微软雅黑";
							context.textAlign = "center";

							context.fillText(date[i - 1].slice(6), padding.left
									+ xLength * (i - 1), HEIGHT
									- padding.bottom + 15);
							// context.fillText(i+"月",padding.left+xLength*(i-1),HEIGHT-padding.bottom+15);
						}

						var max = Math.max.apply(Math, data);
						var yLength = HEIGHT - (padding.top + 50)
								- padding.bottom;
						for (var i = 0; i < 4; i++) { // 画纵坐标
														// 按照data最大值等分纵轴（4份）
							context.beginPath();
							context.moveTo(padding.left, padding.top + 50
									+ yLength / 4 * i);
							context.lineTo(padding.left - 5, padding.top + 50
									+ yLength / 4 * i);
							context.stroke();

							context.font = "12px 微软雅黑";
							context.textAlign = "right";
							context.fillText(max - max / 4 * i,
									padding.left - 10, padding.top + 55
											+ yLength / 4 * i)
						}
						context.beginPath();
						for (var i = 0; i < data.length; i++) { // 画折线
							var length = data[i] * (yLength / max);
							context.font = "12px 微软雅黑";
							if (i == 0) {
								context.textAlign = "left";
								context.tetxBaseline = "middle";

								context.moveTo(padding.left + xLength * i,
										HEIGHT - padding.bottom - length);
							} else {
								context.textAlign = "center";
								context.tetxBaseline = "bottom";

								context.lineTo(padding.left + xLength * i,
										HEIGHT - padding.bottom - length);
							}
							context.fillText(data[i], padding.left + xLength
									* i + 5, HEIGHT - padding.bottom - length
									- 10);
						}
						context.stroke();
						for (var i = 0; i < data.length; i++) { // 画数据圆点
							var length = data[i] * (yLength / max);
							context.beginPath();
							context.arc(padding.left + xLength * i, HEIGHT
									- padding.bottom - length, 3, 0,
									Math.PI * 2);
							context.fill();
						}
					}
				});
			});

	// setTimeout(function(){
	//		
	// }, 1000);

	var canvas = $("#zxt")[0];
	var context = canvas.getContext("2d");
	const
	WIDTH = canvas.width;
	const
	HEIGHT = canvas.height;
	var padding = {
		top : 20,
		left : 50,
		bottom : 30,
		right : 22
	}
	context.beginPath(); // 坐标轴
	context.moveTo(padding.left, padding.top);
	context.lineTo(padding.left, HEIGHT - padding.bottom);
	context.lineTo(WIDTH - padding.right, HEIGHT - padding.bottom);
	context.stroke();

	// 绘制箭头
	context.beginPath();
	context.moveTo(padding.left - 5, padding.top + 10);
	context.lineTo(padding.left, padding.top);
	// context.lineTo(padding.left+5,padding.top+10);
	context.stroke();

	context.beginPath();
	context.moveTo(WIDTH - padding.right - 10, HEIGHT - padding.bottom - 5);
	context.lineTo(WIDTH - padding.right, HEIGHT - padding.bottom);
	// context.lineTo(WIDTH-padding.right-10,HEIGHT-padding.bottom+5);
	context.stroke();

	// while(data==''&&date=='');

})