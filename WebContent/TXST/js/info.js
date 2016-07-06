//初始化

$("section>div").hide();

$('#INFO div').click(
		function() {

			console.log(this.className);
			//if(window.localStorage){     alert("浏览支持localStorage") }else{    alert("浏览暂不支持localStorage") }

			$($(this).parent().siblings()[$("nav").children().index(this)])
					.show();
			$($(this).parent().siblings()[$("nav").children().index(this)])
					.siblings("div").hide();

			if (this.className == 'userInfo') {
				
				console.log('info');

				$.ajax({
					type : "post",
					url : "../UserInfoServlet",
					data : "username=" + localStorage.getItem("username") + "&command=" + this.className,
					dataType : "json",
					success : function(result) {

						// console.log(result.username);
						// console.log(result.password);
						// console.log(result.email);
						// console.log(result.phonenumber);

						$("#username").val(result.username);
						$("#password").val(result.password);
						$("#email").val(result.email);
						$("#phonenumber").val(result.phonenumber);

					}
				});

			} else {
				
				console.log('edit');
				$.ajax({
					type : "post",
					url : "../ThresholdServlet",
					data : "username=TXST" + "&command=" + this.className,
					dataType : "json",
					success : function(result) {

						// console.log(result.username);
						// console.log(result.password);

						$("#tem_threshold").val(result.temValue);
						$("#hum_threshold").val(result.humValue);

					}
				});
			}
		});


$('#user_edit').click(function(){
	
	$("#username").removeAttr("readonly");
	$("#password").removeAttr("readonly");
	$("#email").removeAttr("readonly");
	$("#phonenumber").removeAttr("readonly");
//	console.log($("#username").removeAttr("readonly"));
});


$('#value_edit').click(function(){
	
	$("#tem_threshold").removeAttr("readonly");
	$("#hum_threshold").removeAttr("readonly");

});


$('#user_submit').click(
		function() {

			if ($('#username').val() == '' || $('#password').val() == '') {

				alert("不能为空哦~~~");
			} else {

				console.log("user_submit");
				var postString = "username=" + $('#username').val()
						+ "&password=" + $('#password').val() + "&email="
						+ $('#email').val() + "&phonenumber="
						+ $('#phonenumber').val() + "&command=user_edit";
				console.log(postString);

				$.post("/TEST_SERVER/UserInfoServlet", postString, function(
						data) {
					console.log(data);
					if (data == 'update success') {

						alert("修改成功~~");
					} else {
						alert("出了点小问题，请稍后再试~");
					}
				});
			}

		});

$('#value_submit').click(
		function() {

			if ($('#tem_threshold').val() == ''
					|| $('#hum_threshold').val() == '') {

				alert("不能为空哦~~~");
			} else {

				console.log("value_submit");
				var postString = "tem_threshold=" + $('#tem_threshold').val()
						+ "&hum_threshold=" + $('#hum_threshold').val()
						+ "&command=threshold_edit";
				console.log(postString);

				$.post("/TEST_SERVER/ThresholdServlet", postString, function(
						data) {
					console.log(data);
					if (data == 'update tem success&update hum success') {

						alert("修改成功~~");
					} else {
						alert("出了点小问题，请稍后再试~");
					}
				});

			}
		});
