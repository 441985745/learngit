document.domain = 'opple.com';
(function($){
	var isMouseDown    = false;
	var currentElement = null;
	var dropCallbacks = {};
	var dragCallbacks = {};
	var lastMouseX;
	var lastMouseY;
	var lastElemTop;
	var lastElemLeft;
	var dragStatus = {};	
	$.getMousePosition = function(e){
		var posx = 0;
		var posy = 0;
		if (!e) var e = window.event;
		if (e.pageX || e.pageY) {
			posx = e.pageX;
			posy = e.pageY;
		}
		else if (e.clientX || e.clientY) {
			posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			posy = e.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
		}
		return { 'x': posx, 'y': posy };
	};
	$.updatePosition = function(e) {
		var pos = $.getMousePosition(e);
		var spanX = (pos.x - lastMouseX);
		var spanY = (pos.y - lastMouseY);
		$(currentElement).css("top",  (lastElemTop + spanY));
		$(currentElement).css("left", (lastElemLeft + spanX));
	};
	$(document).mousemove(function(e){
		if(isMouseDown && dragStatus[currentElement.id] == 'on'){
			$.updatePosition(e);
			if(dragCallbacks[currentElement.id] != undefined){
				dragCallbacks[currentElement.id](e, currentElement);
			}
			return false;
		}
	});
	$(document).mouseup(function(e){
		if(isMouseDown && dragStatus[currentElement.id] == 'on'){
			isMouseDown = false;
			if(dropCallbacks[currentElement.id] != undefined){
				dropCallbacks[currentElement.id](e, currentElement);
			}
			return false;
		}
	});
	$.fn.ondrag = function(callback){
		return this.each(function(){
			dragCallbacks[this.id] = callback;
		});
	};
	$.fn.ondrop = function(callback){
		return this.each(function(){
			dropCallbacks[this.id] = callback;
		});
	};
	$.fn.dragOff = function(){
		return this.each(function(){
			dragStatus[this.id] = 'off';
		});
	};
	$.fn.dragOn = function(){
		return this.each(function(){
			dragStatus[this.id] = 'on';
		});
	};
	$.fn.Drag = function(allowBubbling){
		return this.each(function(){
			var moveid=$(this).attr("moveid");
			if(undefined == this.id || !this.id.length) this.id = "woody"+(new Date().getTime());
			var ownerId=this.id;
			moveid=(undefined==moveid||!moveid.length)?(moveid=ownerId):moveid;
			dragStatus[moveid] = "on";
			$(this).css("cursor", "move");
			$(this).mousedown(function(e){
				$("#"+moveid).css("position", "absolute");
				$("#"+moveid).css("z-index", "10000");
				isMouseDown    = true;
				currentElement = $("#"+moveid).get(0);
				var pos    = $.getMousePosition(e);
				lastMouseX = pos.x;
				lastMouseY = pos.y;
				lastElemTop  = $("#"+moveid).get(0).offsetTop;
				lastElemLeft = $("#"+moveid).get(0).offsetLeft;
				$.updatePosition(e);
				return allowBubbling ? true : false;
			});
		});
	};
})(jQuery);