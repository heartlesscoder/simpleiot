//author: fivefish. 2007-02-04 

var CallerResponder = new Class({
	debug:		true,
	context: 	new Object(),	
	success: 	function(jsonObj) {	
					var str = jQuery.toJSON(jsonObj);			
					var msg = "ajax success: " + str;
					if (this.debug) {
						alert(msg);
					}
				},
	error:		function(request,settings,e) {
					if (request.status == 500) {
						var jsonError = jQuery.parseJSON(request.responseText);
					}
					if (this.debug) {
						alert("ajax error: " + request.responseText);
						alert(e);
					}
				},
	complete:	function(res, status) {
					if (this.debug) {
						alert("ajax complete");
					}
				},
	beforeSend:	function(xml) {
					if (this.debug) {
						alert("ajax beforeSend:" + xml);
					}		
				}								
});	

var RemoteJsonService = new Class({
	jsonGateway: "/json/",
	async:		 true,
	
	ajaxCall: function(data,callerResponder) {
		jQuery.ajax({				
				type: 		"POST",
				url:  		this.jsonGateway,
				data: 		data,
				dataType: 	"json",
				async: 		this.async,
				debug:		callerResponder.debug,
				context:	callerResponder.context,
				success: 	callerResponder.success,							
				error:   	callerResponder.error,
				complete: 	callerResponder.complete,
				beforeSend: callerResponder.beforeSend			
			});			
		},

	
	 preprocess: function() {
		var callerResponder;
		if (arguments.length > 0 && (arguments[arguments.length-1] instanceof CallerResponder)) {
			callerResponder = arguments[arguments.length-1];
			Array.prototype.splice.apply(arguments,[arguments.length-1,1]);
		}
		var newArgs = new Array();
		for (var i=0; i<arguments.length; i++) {
			newArgs[i] = arguments[i];
		}
		if (!callerResponder) {
			callerResponder = new CallerResponder();
		}	 
		var data = new Object();
		data.service = this.serviceName;		
		data.arguments = jQuery.toJSON(newArgs);		
		var preprocessResult = new Object();
		preprocessResult.callerResponder = callerResponder;
		preprocessResult.data = data;
		return 	preprocessResult;
	 }		
});

//author: fivefish. 2007-02-04 