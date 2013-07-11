
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

function addCategory(form,table,id) {

	category = document.getElementById('configtable').getElementsByClassName('inputcategory')[id].value;
	document.getElementById('configtable').getElementsByClassName('inputcategory')[id].value = "";
	
	sendAjaxGetRequest("category.php?tablename="+table+"&category="+category);
}

function sendAjaxGetRequest(request){
	var ajax = getRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('configtable').innerHTML = ajax.responseText;
		}
	}
	ajax.open("GET", request, true);
	ajax.send(null);
}

function createTable() {

	var tablename = document.forms.categoryform.tablename.value;
	document.forms.categoryform.tablename.value = "";
	
	sendAjaxGetRequest("createtable.php?tablename="+tablename);
}

function deleteTable(tablename){
	answer = confirm("Are you sure you want to delete the table "+tablename+"?");
	if(answer)
		sendAjaxGetRequest("deletetable.php?tablename="+tablename);
}

function addAction(form,table,id) {

	action = document.getElementById('configtable').getElementsByClassName('inputuseraction')[id].value;
	document.getElementById('configtable').getElementsByClassName('inputuseraction')[id].value = "";
	
	sendAjaxGetRequest("useraction.php?tablename="+table+"&action="+action);
}

function deleteRow(table,row){
	answer = confirm("Are you sure you want to delete the row "+row+" from the table "+table+"?");
	if(answer)
		sendAjaxGetRequest("deleterow.php?tablename="+table+"&row="+row);
}

function deleteCol(table,col){
	answer = confirm("Are you sure you want to delete the column "+col+" from the table "+table+"?");
	if(answer)
		sendAjaxGetRequest("deletecol.php?tablename="+table+"&col="+col);
}

function printTables(showall) {
	sendAjaxGetRequest("printtable.php");
}

function saveTable(tablename,formname){
	document.getElementById('configtable').getElementsByClassName('tablestatus')[rowoffset+row]
}

function checkRowSum(id,row,cols,celloffset,rowoffset){
	var sum = 0;
	
	for(var i=0;i<cols;i++){
		sum += parseInt(document.getElementById('configtable').getElementsByClassName('numberinput')[celloffset+row*cols+i].value);
	}
	if(sum !== 100){
		document.getElementById('configtable').getElementsByClassName('tablestatus')[rowoffset+row].innerHTML = "!!! Sum = "+sum+" (should be 100)";
	}
	else{
		document.getElementById('configtable').getElementsByClassName('tablestatus')[rowoffset+row].innerHTML = "";
	}
}