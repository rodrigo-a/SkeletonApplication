$(".input").focusin(function() {
	$(this).find("span").animate({
		"opacity" : "0"
	}, 200);
});

$(".input").focusout(function() {
	$(this).find("span").animate({
		"opacity" : "1"
	}, 300);
});

$(".login").submit(function() {
	validaLogin();
	return false;
});

function verificaLogin(xhr, status, args) {
	if (args.sessao) {
		loginValido();
		return true;
	}

	loginInvalido();
	return false;
}

function loginValido() {
	$(this).find(".submit i").removeAttr('class').addClass("fa fa-check").css({
		"color" : "#fff"
	});
	$(".submit").css({
		"background" : "#2ecc71",
		"border-color" : "#2ecc71"
	});
	$(".feedbackNegativo").hide();
	$(".feedbackPositivo").show().animate({
		"opacity" : "1",
		"bottom" : "-80px"
	}, 400);
	$("input").css({
		"border-color" : "#2ecc71"
	});
	return false;
}

function loginInvalido() {
	$(this).find(".submit i").removeAttr('class').addClass("fa fa-message-o")
			.css({
				"color" : "#fff"
			});
	$(".submit").css({
		"border-color" : "#ff5151"
	});
	$(".feedbackNegativo").show().animate({
		"opacity" : "1",
		"bottom" : "-80px"
	}, 400);
	$("input").css({
		"border-color" : "#ff5151"
	});
	return false;
}