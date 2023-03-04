(function($) {
	function resizeInnerDiv() {
		var height = $(window).height();	
		var header_height = $(".header").height();
		var footer_height = $(".footer").height();
		var setheight = height - header_height;
		var trueheight = setheight - footer_height;
		$(".content").css("min-height", trueheight);
	}
	
	if($('.content').length > 0 ){
		resizeInnerDiv();
	}

	$(window).resize(function(){
		if($('.content').length > 0 ){
			resizeInnerDiv();
		}
	});
	// Chat
	var chatAppTarget = $('.chat-window');
	(function() {
		if ($(window).width() > 991) {
			chatAppTarget.removeClass('chat-slide');
		} else {
			chatAppTarget.addClass('chat-slide');
		}
	})();

	var generateUuid = function() {
		var tick = parseInt(Date.now() / 1000);
		var max = 1e+9;
		var rand = parseInt((1+Math.random()) * max);
		return tick.toString(16) + rand.toString(16);
	}

	var getCurrentDate = function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		month = month >= 10 ? month : '0' + month;
		day = day >= 10 ? day : '0' + day;
		return year + '-' + month + '-' + day;
	};

	var getCurrentTime = function() {
		var date = new Date();
		var hour = date.getHours();
		var minutes = date.getMinutes();
		hour = hour >= 10 ? hour : '0' + hour;
		minutes = minutes >= 10 ? minu : '0' + minutes;
		return hour + ':' + minutes;
	};

	var chatScroll = $('.chat-scroll');
	(function() {
		chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
	})();

	$(".msg-send-btn").click(function(){
		var msg = $("input[name=msg]").val();
		if(msg) {
			var chatDate = $('#chat-date').val();
			var currentDate = getCurrentDate();
			if(chatDate != currentDate) {
				$('#chat-date').val(currentDate);
				$('#chat-list').append('<li class="chat-date">' + currentDate + '</li>');
			}
			var msgId = generateUuid();
			$("input[name=msg]").val("");
			var msgSend =
				'<li class="media sent">' +
					'<div class="media-body">' +
						'<div class="msg-box">' +
							'<div>' +
								'<p class="msg">'+ msg +'</p>' +
							'</div>' +
						'</div>' +
					'</div>' +
					'<div class="avatar">' +
						'<img src="/static/img/human.jpeg" alt="User Image" class="avatar-img rounded-circle">' +
					'</div>' +
				'</li>';
			$('#chat-list').append(msgSend);
			var msgReceived =
					'<li class="media received">' +
						'<div class="avatar">' +
							'<img src="/static/img/robot.jpeg" alt="User Image" class="avatar-img rounded-circle">' +
						'</div>' +
						'<div class="media-body">' +
							'<div class="msg-box" id="' + msgId + '">' +
								'<div>' +
									'<div class="msg-typing">' +
										'<span></span>' +
										'<span></span>' +
										'<span></span>' +
									'</div>' +
								'</div>' +
							'</div>' +
						'</div>' +
					'</li>';
			$('#chat-list').append(msgReceived);
			chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
			var data = {};
			data.id = msgId;
			var contents = [];
			var coherented = $('#coherented').is(':checked');
			if(coherented) {
				$('#chat-list .media').each(function(){
					if($(this).find('p.msg').length > 0) {
						var content = {};
						if ($(this).hasClass('sent')) {
							content.type = 0;
						} else if ($(this).hasClass('received')) {
							content.type = 1;
						}
						content.content = $(this).find('.msg').text();
						contents.push(content);
					}
				});
			} else {
				var content = {};
				content.type = 0;
				content.content = msg;
				contents.push(content);
			}
			data.contents = contents;
			data.coherented = coherented;
			$.ajax({
				url:  "/chat/ask",
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'post',
				contentType: 'application/json',
				success: function(result) {
					if (result.code == 0) {
						$("#" + msgId).html('<div><p class="msg">' + result.data + '</p></div>');
						chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
					} else {
						console.log(result.message);
					}
				}
			});
		}
	});
	$(document).keydown(function(event){
		if(event.keyCode == 13){
			$(".msg-send-btn").click();
			return false;
		}
	});
})(jQuery);