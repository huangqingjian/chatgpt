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
	var mobileTarget = $('#mobile_btn');
	(function() {
		if ($(window).width() > 991) {
			chatAppTarget.removeClass('chat-slide');
		} else {
			chatAppTarget.addClass('chat-slide');
			mobileTarget.click(function(){
				chatAppTarget.removeClass('chat-slide');
			});
		}
		$(document).on("click",".chat-window .chat-list a.media",function () {
			if ($(window).width() <= 991) {
				chatAppTarget.addClass('chat-slide');
			}
			return false;
		});
		$(document).on("click","#back_chat_list",function () {
			if ($(window).width() <= 991) {
				chatAppTarget.removeClass('chat-slide');
			}
			return false;
		});
	})();

	if($('.floating').length > 0 ){
		$('.floating').on('focus blur', function (e) {
			$(this).parents('.form-focus').toggleClass('focused', (e.type === 'focus' || this.value.length > 0));
		}).trigger('blur');
	}

	var generateUuid = function() {
		var tick = parseInt(Date.now() / 1000);
		var max = 1e+9;
		var rand = parseInt((1+Math.random()) * max);
		return tick.toString(16) + rand.toString(16);
	}

	var getDate = function(timestamp){
		var date = new Date(timestamp);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		return year + '年' + month + '月' + day;
	};

	var getTime = function(timestamp) {
		var date = new Date(timestamp);
		var hour = date.getHours();
		var minutes = date.getMinutes();
		hour = hour >= 10 ? hour : '0' + hour;
		minutes = minutes >= 10 ? minutes : '0' + minutes;
		return hour + ':' + minutes;
	};

	var getDisplayDate = function(date){
		var displayDate = date;
		var today = getCurrentDate();
		var yesterday = getYesterdayDate();
		if(date == today) {
			displayDate = "今天";
		} else if(date == yesterday) {
			displayDate = "昨天";
		}
		return displayDate;
	}

	var getDisplayDateTime = function(timestamp){
		var today = getCurrentDate();
		var yesterday = getYesterdayDate();
		var date = getDate(timestamp);
		var time = getTime(timestamp);
		if(date == today) {
			return time;
		} else if(date == yesterday) {
			return "昨天" + " " + time;
		}
		return date + " " + time;
	}

	var getCurrentDate = function() {
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		return year + '年' + month + '月' + day;
	};

	var getYesterdayDate = function(){
		var date = new Date();
		date.setDate(date.getDate() -1);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var day = date.getDate();
		return year + '年' + month + '月' + day;
	};

	var getCurrentTime = function() {
		var date = new Date();
		var hour = date.getHours();
		var minutes = date.getMinutes();
		hour = hour >= 10 ? hour : '0' + hour;
		minutes = minutes >= 10 ? minutes : '0' + minutes;
		return hour + ':' + minutes;
	};

	var chatScroll = $('.chat-scroll');
	(function() {
		chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
	})();
	var loginTitle = "登录ChatGpt";
	var loginTipTitle = "请先登录ChatGpt后访问～";
	// 查询会话列表
	if(chatAppTarget.length > 0 ){
		$.ajax({
			url:  "/chat/list",
			dataType: 'json',
			type: 'get',
			contentType: 'application/json',
			success: function(result) {
				if (result.code == 0) {
					if(result.data && result.data.length > 0) {
						for(var i = 0;i < result.data.length; i++){
							var chat =
								'<a href="javascript:void(0);" class="media" id="'+result.data[i].id+'">' +
								'	<div class="media-img-wrap">' +
								'		<div class="avatar avatar-away">' +
								'			<img src="/static/img/comment.png" alt="聊天" class="avatar-img">' +
								'       </div>' +
								'   </div>';
							var lastest = result.data[i].lastest;
							var question = "";
							var datetime = "";
							if(lastest) {
								question = lastest.question;
								datetime = getDisplayDateTime(lastest.chatTime);
							}
							chat +=
								'<div class="media-body">' +
								'	<div>' +
								'		<div class="user-last-chat">'+question+'</div>' +
								'   </div>' +
								'   <div>' +
								'       <div class="last-chat-time block">'+datetime+'</div>' +
								'   </div>' +
								'</div>';
							chat += '</a>';
							$('.chat-cont-left .chat-scroll').append(chat);
						}
					}
				} else {
					console.log(result.message);
				}
			}
		});
		// 创建会话
		var create_chat = function(){
			var flag = false;
			$.ajax({
				url:  "/chat/create",
				dataType: 'json',
				type: 'post',
				async: false,
				contentType: 'application/json',
				success: function(result) {
					if (result.code == 0) {
						if ($(window).width() <= 991) {
							chatAppTarget.addClass('chat-slide');
						}
						$('.chat-cont-left .chat-scroll .media').removeClass('active');
						$('.chat-cont-right .chat-header,.chat-cont-right .chat-scroll').removeClass("hide");
						$('.chat-cont-right .chat-card-group').addClass("hide");
						var chat =
							'<a href="javascript:void(0);" class="media active" id="'+result.data+'">' +
								'<div class="media-img-wrap">' +
									'<div class="avatar avatar-away">' +
										'<img src="/static/img/comment.png" alt="聊天" class="avatar-img">' +
									'</div>' +
								'</div>' +
								'<div class="media-body">' +
									'<div>' +
										'<div class="user-last-chat"></div>' +
									'</div>' +
									'<div>' +
										'<div class="last-chat-time block"></div>' +
									'</div>' +
								'</div>' +
							'</a>';
						$('.chat-cont-left .chat-scroll').prepend(chat);
						var currentDate = getCurrentDate();
						var win =
							'<input type="hidden" id="chat-date" value="'+currentDate+'">' +
							'<ul class="list-unstyled chat-list" id="chat-list">' +
							'<li class="chat-date">'+getDisplayDate(currentDate)+'</li>' +
							'</ul>';
						$('.chat-cont-right .chat-scroll').html(win);
						$('.chat-cont-right .chat-scroll').attr("chatId", result.data);
						flag = true;
					} else if(result.code == 401) {
						$("#login .login-header h3").text(loginTipTitle);
						$("#login").modal("toggle");
					} else {
						console.log(result.message);
					}
				}
			});
			return flag;
		};
		$(".chat-compose").click(function(){
			create_chat();
		});
		$(".chat-talk").click(function(){
			if(create_chat()) {
				send_msg($(this).attr("talk"));
			}
		});
		$(".chat-delete").click(function(){
			var chatId = $('.chat-cont-right .chat-scroll').attr("chatId");
			$.ajax({
				url:  "/chat/delete/" + chatId,
				dataType: 'json',
				type: 'post',
				contentType: 'application/json',
				success: function(result) {
					if (result.code == 0) {
						$('.chat-cont-right .chat-header,.chat-cont-right .chat-scroll').addClass("hide");
						$('.chat-cont-right .chat-card-group').removeClass("hide");
						$('.chat-cont-right .chat-scroll').removeAttr("chatId");
						$('#'+chatId).remove();
					} else {
						console.log(result.message);
					}
				}
			});
		});
		$("#chat-search").keyup(function(){
			var q = $(this).val();
			if(q) {
				$('.chat-cont-left .chat-scroll .media').each(function () {
					if ($(this).find(".user-last-chat").text().indexOf(q) == -1) {
						$(this).addClass("hide");
					}
				});
			} else {
				$('.chat-cont-left .chat-scroll .media').removeClass("hide");
			}
		});
		$(document).on("click",".chat-cont-left .chat-scroll .media",function () {
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
			$('.chat-cont-right .chat-header,.chat-cont-right .chat-scroll').removeClass("hide");
			$('.chat-cont-right .chat-card-group').addClass("hide");
			$('.chat-cont-right .chat-scroll').attr("chatId", $(this).attr("id"));
			$.ajax({
				url:  "/chat/"+$(this).attr("id")+"/record",
				dataType: 'json',
				type: 'get',
				contentType: 'application/json',
				success: function(result) {
					if (result.code == 0) {
						var chatContent = "";
						if(result.data && result.data.length > 0) {
							var lastChatTime = "";
							for(var i = 0; i < result.data.length; i++) {
								var chatTime = getDate(result.data[i].chatTime);
								if(chatTime != lastChatTime) {
									var displayDate = getDisplayDate(chatTime);
									chatContent += '<li class="chat-date">'+displayDate+'</li>';
									lastChatTime = chatTime;
								}
								var msgSend =
									'<li class="media sent">' +
										'<div class="media-body">' +
											'<div class="msg-box">' +
												'<div>' +
													'<p class="msg">'+ result.data[i].question +'</p>' +
													'<ul class="chat-msg-info">' +
														'<li>' +
															'<div class="chat-time">' +
																'<span>'+getTime(result.data[i].chatTime)+'</span>' +
															'</div>' +
														'</li>' +
													'</ul>' +
												'</div>' +
											'</div>' +
										'</div>' +
									'</li>';
								chatContent += msgSend;
								var msgReceived =
									'<li class="media received">' +
										'<div class="avatar">' +
											'<img src="/static/img/robot.jpeg" alt="User Image" class="avatar-img rounded-circle">' +
										'</div>' +
										'<div class="media-body">' +
											'<div class="msg-box">' +
												'<div>' +
													'<p class="msg">' + result.data[i].answer + '</p>' +
												'</div>'
											'</div>' +
										'</div>' +
									'</li>';
								chatContent += msgReceived;
							}
							$("#chat-date").val(lastChatTime);
						}
						$('#chat-list').html(chatContent);
						chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
					} else {
						console.log(result.message);
					}
				}
			});
		});
	}
	$(".msg-send-btn").click(function(){
		send_msg($("input[name=msg]").val());
	});
	var send_msg = function(msg){
		if(msg) {
			var chatDate = $('#chat-date').val();
			var currentDate = getCurrentDate();
			if(chatDate != currentDate) {
				$('#chat-date').val(currentDate);
				$('#chat-list').append('<li class="chat-date">' + getDisplayDate(currentDate) + '</li>');
			}
			var chatId = $('.chat-cont-right .chat-scroll').attr("chatId");
			var msgId = generateUuid();
			$("input[name=msg]").val("");
			var msgSend =
				'<li class="media sent">' +
					'<div class="media-body">' +
						'<div class="msg-box">' +
							'<div>' +
								'<p class="msg">'+ msg +'</p>' +
								'<ul class="chat-msg-info">' +
									'<li>' +
										'<div class="chat-time">' +
											'<span>'+getCurrentTime()+'</span>' +
										'</div>' +
									'</li>' +
								'</ul>' +
							'</div>' +
						'</div>' +
					'</div>' +
				'</li>';
			$('#chat-list').append(msgSend);
			$('#'+chatId+" .user-last-chat").text(msg);
			$('#'+chatId+" .last-chat-time").text(getCurrentTime());
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
			data.contents = contents;
			data.chatId = chatId;
			$.ajax({
				url:  "/chat/ask",
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'post',
				contentType: 'application/json',
				success: function(result) {
					if (result.code == 0) {
						$("#" + msgId).html('<div><p class="msg">' + result.data.content + '</p></div>');
						chatScroll.scrollTop(chatScroll.prop('scrollHeight'));
					} else if(result.code == 401) {
						$("#login .login-header h3").text(loginTipTitle);
						$("#login").modal("toggle");
					} else {
						console.log(result.message);
					}
				}
			});
		}
	};
	if($('.vip-card').length > 0) {
		$('.vip-card').click(function(){
			$('.vip-card').removeClass('selected');
			$(this).addClass('selected');
		});
	}
	if($('.header-login').length > 0) {
		$('.header-login').click(function(){
			$("#login .login-header h3").text(loginTitle);
			$("#login").modal("toggle");
		});
	}
	if($('.login-btn').length > 0) {
		$(".login-btn").click(function(){
			var username = $('input[name=username]').val();
			if(username=='' || username==undefined) {
				$('.alert-login').html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>手机号不能为空～</div>');
				return;
			}
			var password = $('input[name=password]').val();
			if(password=='' || password==undefined) {
				$('.alert-login').html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>密码不能为空～</div>');
				return;
			}
			var data = {};
			data.username = username;
			data.password = password;
			$.ajax({
				url:  "/login",
				dataType: 'json',
				data: data,
				type: 'post',
				success: function(result) {
					if (result.code != 0) {
						$('.alert-login').html('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>'+result.message+'</div>');
					} else {
						window.location.reload();
					}
				}
			});
		});
	};
	// 保存profile
	if($('.profile-save').length > 0) {
		$(".profile-save button").click(function(){
			$('form .alert').remove();
			var mobile = $('input[name=mobile]').val();
			if(mobile=='' || mobile==undefined) {
				$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>手机号不能为空～</div>').insertBefore($('.profile-save'));
				return;
			}
			var data = {};
			data.mobile = mobile;
			data.name = $('input[name=name]').val();
			data.face = $('input[name=face]').val();
			$.ajax({
				url:  "/user/update",
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'post',
				contentType: 'application/json',
				success: function(result) {
					if (result.code != 0) {
						$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>'+result.message+'</div>').insertBefore($('.profile-save'));
					} else {
						window.location.reload();
					}
				}
			});
		});
	};
	// 保存profile
	if($('.change-password').length > 0) {
		$(".change-password button").click(function(){
			$('form .alert').remove();
			var password = $('input[name=password]').val();
			if(password=='' || password==undefined) {
				$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>请输入旧密码～</div>').insertBefore($('.change-password'));
				return;
			}
			var newPassword = $('input[name=newPassword]').val();
			if(newPassword=='' || newPassword==undefined) {
				$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>请输入新密码～</div>').insertBefore($('.change-password'));
				return;
			}
			var confirmPassword = $('input[name=confirmPassword]').val();
			if(confirmPassword=='' || confirmPassword==undefined) {
				$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>请输入确认密码～</div>').insertBefore($('.change-password'));
				return;
			}
			var data = {};
			data.password = password;
			data.newPassword = newPassword;
			data.confirmPassword = confirmPassword;
			$.ajax({
				url:  "/user/updatePassword",
				dataType: 'json',
				data: JSON.stringify(data),
				type: 'post',
				contentType: 'application/json',
				success: function(result) {
					if (result.code != 0) {
						$('<div class="alert alert-warning"><a href="#" class="close" data-dismiss="alert">&times;</a>'+result.message+'</div>').insertBefore($('.change-password'));
					} else {
						window.location.reload();
					}
				}
			});
		});
	};
	// 登出
	if($('.logout').length > 0){
		$('.logout').click(function(){
			$.ajax({
				url: '/logout',
				dataType: 'json',
				contentType: 'application/json',
				type: 'post',
				success: function (result) {
					if (result.code == 0) {
						location.href="/chat.html";
					} else {
						console.log(result.message);
					}
				}
			})
		});
	};
	var getCurrentUser = function(){
		$.ajax({
			url:  "/user/getCurrent",
			dataType: 'json',
			type: 'get',
			contentType: 'application/json',
			success: function(result) {
				if (result.code == 0 && result.data) {
					if(result.data.face) {
						$('.logged-item .user-img img,.logged-item .avatar img').attr('src', result.data.face);
					}
					var username = result.data.mobile;
					if(result.data.name) {
						username = result.data.name;
					}
					$('.logged-item .user-text h6').text(username);
					$('.no-log-item').addClass('hide');
					$('.logged-item').removeClass('hide');
				} else {
					console.log(result.message);
				}
			}
		});
	};
	(function() {
		getCurrentUser();
	})();
	$(document).keydown(function(event){
		if(event.keyCode == 13){
			send_msg($("input[name=msg]").val());
			return false;
		}
	});
})(jQuery);