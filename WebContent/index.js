initPage();

function initPage(){
$.getJSON("api/testmode",function(testmode){
	var isTestCheckedStart=testmode.testmodes==true;
	if (isTestCheckedStart){
    	$('#testCheck').css({
        	'background-color': 'green'
    	});
    	isTestChecked=true;
    	document.getElementById("testCheck").textContent="TestMode is ON";
	}
	else{
		$('#testCheck').css({
	        'background-color': 'red'
	    });
		isTestChecked=false;
		document.getElementById("testCheck").textContent="TestMode is OFF";
	}
});
}

$('#submitOffset').click(function(){
	var value=document.getElementById("inputOffset").value;
	$.ajax({
	    type: "PUT",
	    data: {traveltime:value},
	    url: "api/timeoffset",
	    success: function() {
	    },
	error: function(){
		alert("An error occured while changing the time offset.")
	}}
	);
});

var isTestChecked=false;
$('#testCheck').click(function() {
	if (!isTestChecked){
    	$('#testCheck').css({
        	'background-color': 'green'
    	});
    	isTestChecked=true;
    	document.getElementById("testCheck").textContent="TestMode is ON";
	}
	else{
		$('#testCheck').css({
	        'background-color': 'red'
	    });
		isTestChecked=false;
		document.getElementById("testCheck").textContent="TestMode is OFF";
	}
	$.ajax({
	    type: "PUT",
	    url: "api/testmode",
	    success: function() {
	    },
	error: function(){
		alert("An error occured while changing to/from testmode.")
	}}
	);
});

// Tabbed Menu
function openMenu(evt, menuName) {
  var i, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" w3-dark-grey", "");
  }
  document.getElementById(menuName).style.display = "block";
  evt.currentTarget.firstElementChild.className += " w3-dark-grey";
}
document.getElementById("myLink").click();