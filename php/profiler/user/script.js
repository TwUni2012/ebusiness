
function getRequest() {
    var req = false;
    try{
        req = new XMLHttpRequest();
    } catch (e){
        try{
            req = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try{
                req = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e){
                return false;
            }
        }
    }
    return req;
}

function sendAjaxGetRequest(request){
	var ajax = getRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('usertable').innerHTML = ajax.responseText;
		}
	}
	ajax.open("GET", request, true);
	ajax.send(null);
}

function printTables() {
	
	sendAjaxGetRequest("printtable.php");
}

function userAction(userid,action){
	sendAjaxGetRequest("profileralgo.php?userid="+userid+"&action="+action);
}