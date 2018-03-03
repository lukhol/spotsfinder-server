<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="placeOverlayContainer" class="overlay-container" style="width: 0%;">
	<a href="javascript:void(0)" class="closebtn" onclick="closePlaceOverlayContainer()">&times;</a>
	<div id="place-loader-container" class="display-middle" style="display: none;">
		<div class="sf-loader-big"></div>
	</div>
	<div id="place-container">
	</div>
	<div class="sf-clearfix"></div>
</div>

<div class="sf-container">
	<input type="hidden" id="base-url" value="${BASE_URL}" />
	
		<c:forEach items="${recentlyAddedPlaces}" var="place" varStatus="loop">
			<c:if test="${loop.index == 0 || (loop.index%3) == 0}">
			<div class="row row-eq-height">
			</c:if>
				<div class="card col-md-4 col-sm-4" style="cursor:pointer;">
					<div onclick="openPlaceOverlayContainer(${place.id})" class="card-body sf-border-white sf-big-on-hover" style="margin-top: 30px; height: 100%; padding: 10px;">
						<img class="sf-image-resizeable img-rounded" src="data:image/png;base64, ${place.mainPhoto }"/>
						<div class="sf-center">
							<h1> ${place.name} </h1>
							<h3> ${place.description} </h3>
						</div>
					</div>
				</div>
			<c:if test="${ ((loop.index%2) == 0 && loop.index != 0) || loop.last}">
			</div><br><br>
			</c:if>
		</c:forEach>
	
	<div class="sf-clearfix"></div>
</div>

<script type="text/javascript" src="/js/recentlyAddedPlaces.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBSBIeH17u88T7qlXU34l7dRZgnB47VuM4&callback=myGoogleMapsCallbackFun"></script>