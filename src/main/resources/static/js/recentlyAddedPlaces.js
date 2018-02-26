var currentPlace = document.getElementById("place-loader-container");
var placeContainer = document.getElementById("place-container");
var downloadedPlaces = [];

function openPlaceOverlayContainer(placeId) {
	fetchPlace(placeId);
	
	var viewPortWidth = document.documentElement.clientWidth;
	var placeOverlayContainer = document.getElementById("placeOverlayContainer");
	
	if(viewPortWidth > 700) {
		placeOverlayContainer.style.width = "100%";
	} else {
		placeOverlayContainer.style.width = "100%";
	}
}

function closePlaceOverlayContainer() {
	document.getElementById("placeOverlayContainer").style.width = "0%";
}

$(document).keyup(function(e) {
    if (e.keyCode == 27) {
    	closePlaceOverlayContainer();
    }
});

function fetchPlace(placeId) {
	let baseUrl = $("#base-url").val();
	let queryUrl = baseUrl + '/places/' + placeId;
	let loaderContainer = document.getElementById("place-loader-container");
	
	let doesArrayContainsPlace = false;
	for(let i = 0 ; i < downloadedPlaces.length ; i++) {
		if(downloadedPlaces[i].id == placeId){
			doesArrayContainsPlace = true;
			currentPlace = downloadedPlaces[i];
			break;
		}
	}
	
	if(doesArrayContainsPlace) {
		displayPlace();
		return;
	} else {
		loaderContainer.style.display = "block";
		placeContainer.innerHTML = "";
	}
	
	$.ajax({
		url: queryUrl,
		type: 'GET',
		dataType: 'json',
		headers: {
		    'Authorization': 'Basic c3BvdGZpbmRlcjpzcG90ZmluZGVyU2VjcmV0'
		},
		success: function(data) {
			loaderContainer.style.display = "none";
			currentPlace = data;
			downloadedPlaces.push(currentPlace);
			
			displayPlace();
		}, 
		error: function(error) {
			console.log(error);
		}
	});
}

function displayPlace() {
	//placeContainer.innerHTML = '<div class="container">';
	placeContainer.innerHTML = '<h1 class="sf-center">' + currentPlace.name + '</h1> <hr/>';
	placeContainer.innerHTML += '<div class="col-container" style="background-color: yelow;">';
	placeContainer.innerHTML += '<div class="col"> Description: <br>' + currentPlace.description + '</div>';
	placeContainer.innerHTML += '<div class="col" style="background-color: yelow;"> Obstacles: <ul class="obstacles">' + generateBooleanSection() + '</ul> </div>';
	placeContainer.innerHTML += '</div>'
	placeContainer.innerHTML += '<div class="sf-clearfix"></div>';
	for(let i = 0 ; i < currentPlace.images.length ; i++){
		placeContainer.innerHTML += '<div class="sf-responsive-two" style="padding: 10px;"> <img class="sf-image-resizeable" src="data:image/png;base64, ' + currentPlace.images[i].image + '"/> </div>';
	}
	placeContainer.innerHTML += '<div class="sf-clearfix"></div>';
	placeContainer.innerHTML += '<div style="width: 100%; height: 400px; padding: 10px; padding-top: 0px;"><div id="googleMap" style="width:100%; height: 100%;"></div></div>';
	myGoogleMapsCallbackFun();
	//placeContainer.innerHTML += '</div>';
}

function myGoogleMapsCallbackFun() {
	let placePosition =  new google.maps.LatLng(currentPlace.location.latitude, currentPlace.location.longitude);
	let mapProp = {
		center: placePosition,
		zoom: 15,
	}
	let marker = new google.maps.Marker({position: placePosition});
	let map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
	marker.setMap(map);
}

function generateBooleanSection() {
	let htmlCode = "";
	htmlCode += generateBooleanListItemHtml(currentPlace.gap, "gap");
	htmlCode += generateBooleanListItemHtml(currentPlace.stairs, "stairs");
	htmlCode += generateBooleanListItemHtml(currentPlace.rail, "rail");
	htmlCode += generateBooleanListItemHtml(currentPlace.ledge, "ledge");
	htmlCode += generateBooleanListItemHtml(currentPlace.handrail, "handrail");
	htmlCode += generateBooleanListItemHtml(currentPlace.corners, "corners");
	htmlCode += generateBooleanListItemHtml(currentPlace.manualpad, "manualpad");
	htmlCode += generateBooleanListItemHtml(currentPlace.wallride, "wallride");
	htmlCode += generateBooleanListItemHtml(currentPlace.downhill, "downhill");
	htmlCode += generateBooleanListItemHtml(currentPlace.openYourMind, "open your mind");
	htmlCode += generateBooleanListItemHtml(currentPlace.pyramid, "pyramid");
	htmlCode += generateBooleanListItemHtml(currentPlace.curb, "curb");
	htmlCode += generateBooleanListItemHtml(currentPlace.bowl, "bowl");
	htmlCode += generateBooleanListItemHtml(currentPlace.bank, "bank");
	return htmlCode;
		
}

function generateBooleanListItemHtml(fieldValue, fieldName) {
	if(fieldValue)
		return '<li>' + fieldName + '</li>';
	else
		return '';
}