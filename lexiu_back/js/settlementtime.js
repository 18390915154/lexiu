/**
 * Created by my on 2017/4/24.
 */
$(document).ready(function() {
	$("#jzjs").on("keyup", function() {
		if ($("#jzjs").val() < 0) {
			$("#jzjs").val(0);
		} else if ($("#jzjs").val() > 100) {
			$("#jzjs").val(100)
		}
	})
	$("#wxjs").on("keyup", function() {
		if ($("#wxjs").val() > 100) {
			$("#wxjs").val(100);
		} else if ($("#wxjs").val() < 0) {
			$("#wxjs").val(0);
		}

	})

	$.ajax({
		url: "https://www.lerlex.com/lexiu/manage/system/searchcheckout",
		data: ({}),
		type: "post",
		dataType: "json",
		success: function(data) {
			console.log(data);
			$("#jzjs").val(data.checkoutlist[1].number);
			$("#wxjs").val(data.checkoutlist[0].number);
		},
		error: function(res) {
			console.log(res);
		}
	});

	$("#jzjsBtn").on("click", function() {
		var number = $("#jzjs").val();
		console.log(number);
		$.ajax({
			url: "https://www.lerlex.com/lexiu/manage/system/updatecheckout",
			data: ({
				number: number,
				checkoutid: 2
			}),
			type: "post",
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.message == "修改成功") {
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error: function(res) {
				console.log(res);
			}
		});

	});

	$("#wxjsBtn").on("click", function() {
		var number = $("#wxjs").val();
		console.log(number);
		$.ajax({
			url: "https://www.lerlex.com/lexiu/manage/system/updatecheckout",
			data: ({
				number: number,
				checkoutid: 1
			}),
			type: "post",
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.message == "修改成功") {
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error: function(res) {
				console.log(res);
			}
		});

	})


})