var NotifyMe = function(){};
NotifyMe.prototype.notifyme = function(
	str, str2, str3, successCallback, errorCallback) {
	cordova.exec(
		successCallback,
		errorCallback,
		'NotifyMe',
		'notifyme',
		[str, str2, str3]
	);
};

if(!window.plugins) {
	window.plugins = {};
}
if (!window.plugins.NotifyMe) {
	window.plugins.NotifyMe = new NotifyMe();
}