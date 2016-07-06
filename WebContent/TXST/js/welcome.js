/**
 * Created by Administrator on 2016/4/23.
 */
$(function(){

	console.log($("#user").text());
	console.log(localStorage.getItem("username"));
	$("#user").text (localStorage.getItem("username"));
})