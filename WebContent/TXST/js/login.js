/**
 * 
 */
//$(function(){
//
//	
//})

$("#registerForm").validate({

	rules : {
		username : {
			required : true,
			minlength : 2,
		},
		password : {
			required : true,
			minlength : 5
		},
		confirm_password : {
			equalTo : "#password"
		},
		email : {
			required : true,
			email : true
		},
		// phonenumber: {
		// required: true,
		// tel: true
		// },
		agree : "required"
	},
	messages : {

		username : {
			required : "Please enter a username",
		},
		password : {
			required : "Please provide a password",
			minlength : "Your password must be at least 5 characters long"
		},
		confirm_password : {
			equalTo : "Please enter the same password as above"
		},
		email : "Please enter a valid email address",
		// tel: "Please enter a valid phonenumber",
		agree : "Please accept our policy",
	},

	submitHandler : function() {
		ajaxSubmitForm();
	}
});

$('#btn_check').click(					//检验用户名是否已注册
		function ajaxCheckUsername() {

			if ($('#username').val() == '') {

				alert("用户名不能为空！");
				return;
			}
			var postString = "username=" + $('#username').val() + "&command=check";
			console.log(postString);
			$.post("/TEST_SERVER/RegisterServlet", postString, function(data) {
				console.log(data);
				if (data == 'ok') {

					alert("用户名可用~~");
				} else {
					alert('用户名已经被占用了呢~~');
				}
			});

		});

function ajaxSubmitForm() {

	console.log("check");
	var postString = "username=" + $('#username').val() + "&password="
			+ $('#password').val() + "&email=" + $('#email').val()
			+ "&phonenumber=" + $('#phonenumber').val() + "&command=register";
	console.log(postString);

	$.post("/TEST_SERVER/RegisterServlet", postString, function(data) {
		console.log(data);
		if (data == 'register success') {

			alert("注册成功~~");
			localStorage.setItem("username", $('#username').val());
			window.location.href = "/TEST_SERVER/TXST/info.html";
		} else {
			alert(data);
		}
	});

}

$('#btn_success').click(
		function() {

			console.log("check");
			if ($('#uname').val() == '' || $('#upwd').val() == '') {

				alert("用户名或密码为空~~~");
			} else {
				var postString = "username=" + $('#uname').val() + "&password="
						+ $('#upwd').val() + "&client=Browser";
				console.log(postString);

				$.post("/TEST_SERVER/LoginCheckServlet", postString, function(
						data) {
					console.log(data);
					if (data == 'login success') {

						alert(data);
						localStorage.setItem("username", $('#uname').val());
						window.location.href = "/TEST_SERVER/TXST/info.html";
					} else {
						alert(data);
					}
				});
			}

		});
