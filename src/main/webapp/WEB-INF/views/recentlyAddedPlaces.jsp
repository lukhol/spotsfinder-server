<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="placeOverlayContainer" class="overlay-container" style="width: 0%;">
	<a href="javascript:void(0)" class="closebtn" onclick="closePlaceOverlayContainer()">&times;</a>
	<div id="place-loader-container" class="display-middle" style="display: none;">
		<div class="sf-loader-big"></div>
	</div>
	<div id="place-container">
		
	</div>
	<div class="sf-clearfix"></div>
</div>

<div id="container">
	<input type="hidden" id="base-url" value="${BASE_URL}" />
	
	<ul>
		<c:forEach items="${recentlyAddedPlaces}" var="place">
			<li>
				<div class="sf-border-white sf-card sf-responsive">
					<a href="/views/places/${place.id}">
						<div>
							<img class="sf-image-resizeable" src="data:image/png;base64, ${place.mainPhoto }"/>
							<div class="sf-center">
								<h1> ${place.name} </h1>
								<h2> ${place.description} </h2>
							</div>
						</div>
					</a>
					<button onclick="showPlace(${place.id})">Show</button>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div class="sf-clearfix"></div>
</div>

<script type="text/javascript" src="/js/recentlyAddedPlaces.js"></script>