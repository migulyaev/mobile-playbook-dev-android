setTimeout(function(){
	Java.perform(function (){
		console.log("[*] Script loaded")

		var MainActivity = Java.use("org.owasp.mstgkotlin.MainActivity")

		MainActivity.FUNCTION.overload().implementation = function() {
			console.log("Root detection script triggered")
				
		}

	});

});
