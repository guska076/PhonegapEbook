var SharePhoto = function(){};
SharePhoto.prototype.share = function(
	str, successCallback, errorCallback) {
	cordova.exec(
		successCallback,
		errorCallback,
		'SharePhoto',
		'share',
		[str]
	);
};

if(!window.plugins) {
	window.plugins = {};
}
if (!window.plugins.SharePhoto) {
	window.plugins.SharePhoto = new SharePhoto();
}