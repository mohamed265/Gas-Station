function ajaxCall(url, method, successCallBack, failCallBack) {
	var xmlhttp; 

	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (successCallBack != null)
				successCallBack(xmlhttp.responseText);
		} else {
			if (failCallBack != null)
				failCallBack();
		}
	}
	xmlhttp.open(method, url, true);
	xmlhttp.send();
}