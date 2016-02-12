$(document).ready(function() {
	var url = 'http://calculator.52.21.42.188.xip.io/calculation';
	var apikey;
	var input = $('input');
	
	$.ajax({url: '/apikey', type: 'GET', contentType: 'application/text', success: function(result) {
		apikey = result;
	}});
	
	$('#numbers').find('button').click(function(e) {
		input.val(input.val() + this.innerHTML);
	});
	
	var buttons = $('#func').find('button');
	
	$(buttons[0]).click(function() {
		input.val('');
	});
	$(buttons[1]).click(function() {
		var data = {};
		data.apikey = apikey;
		data.operation = input.val();
		
		data = JSON.stringify(data);
		
		$.ajax({url: url, data: data, type: 'POST', contentType: 'application/json', success: function(result) {
			input.val(result.result);
		}});
	});
	
});