<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="sf-container">
	<!-- #2d90a1 -->
	<!--
	<div class="sf-center panel panel-primary" style="margin: 0px;">
		<div class="panel-heading">
			<h2><span class="label label-success">SpotsFinder</span><small style="color: white;"> Spring Boot MVC & RestAPI</small></h2>
			<a href="#" data-toggle="tooltip" title="Hooray!">Hover over me</a>
		</div>
				
		<!-- HANDLEBARS --
		<div id="containerForHandlebars" class="panel-body" style="background-color: #CDCDCD;">
			<!-- Tutaj trafi kod z template poniżej.  --
		</div>
		
		<script id="placeDetailsTemplateScript" type="text/x-handlebars-template">
			{{firstname}} {{lastname}}
		</script>
		
		<script>
			$(document).ready(function() {
				let data = {
					firstname: "Łukasz",
					lastname: "Hołdrowicz",
					age: 24, 
					skills: [
						{
							name: "Java",
							level: 8
						},
						{
							name: "HTML",
							level: 6
						},
						{
							name: "CSS",
							level: 4
						}
					]
				};
				
				//executeHandlebarsTemplate("placeDetailsTemplateScript", "containerForHandlebars", data);
				loadHandlebarsFromFile("person.hbs", "containerForHandlebars", data);
			});
		</script>
	</div>
	
	<br>
	
	<div class="container">
		<div id="placeDetailsDiv">
			<div class="loader">
			</div>
		</div>
		<br>
	</div>
	
	<script>
		$(document).ready(function() {
			fetchPlaceHandlebars(5, "placeDetailsDiv");
			
			$('#placeDetailsDiv').bind("DOMSubtreeModified",function(){
				$('.small-place-img').mouseenter(function() {
					let bigImage = document.getElementById("bigImagePlaceDetails");
					bigImage.src = $(this).attr('src');
				});
			});
		});
	</script>
	<script type="text/javascript" src="/js/placeDetails.js"></script>
	
	-->
	<div style="text-align: justify;">
	    ttt
		<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis ac arcu at hendrerit. Nulla dapibus aliquet est in lobortis. Sed a lacus iaculis, rutrum magna vitae, accumsan leo. Suspendisse sit amet semper nisl, eget aliquet justo. Cras nisl enim, sollicitudin non erat vitae, venenatis tempus orci. Vestibulum viverra mollis augue eu sodales. Morbi mi lacus, fringilla vel blandit et, consectetur consequat ligula. Nulla rutrum pulvinar justo quis imperdiet.
		</p>
	
		<p>
			Vestibulum et dui sed dolor porttitor vehicula vitae eget diam. Maecenas sit amet nibh mattis, maximus odio vitae, porttitor massa. Morbi at massa at tortor facilisis tincidunt eu non quam. Nullam convallis neque in lacus suscipit interdum. Maecenas id nisi semper enim consectetur commodo. Mauris vitae congue lorem. Nam eleifend molestie neque sit amet fringilla. Mauris in erat sapien. Vestibulum libero purus, gravida in sodales a, posuere sit amet orci.
		</p>
		
		<p>
			Nullam eu egestas turpis. Nullam sodales et felis eu sollicitudin. Nullam sit amet ex luctus, molestie orci vel, rhoncus metus. Suspendisse potenti. Fusce id libero libero. Integer commodo sit amet urna quis eleifend. Sed fringilla diam id pellentesque consectetur. Nam a eleifend metus. Curabitur rhoncus libero tempus dolor congue commodo. Duis quis posuere quam. Integer sit amet scelerisque tortor. Aliquam vestibulum, libero ut ultrices suscipit, mauris augue feugiat sapien, nec lacinia quam leo feugiat mi. Sed tempus augue at felis euismod condimentum. Praesent sed imperdiet est, sed tristique elit. Morbi vitae pellentesque eros.
		</p>
	
		<p>
			am eros massa, vulputate eu ligula vitae, placerat dignissim felis. Ut laoreet velit a diam posuere, ac egestas nulla mollis. Nulla sagittis aliquet sapien, id aliquet lorem viverra ut. Maecenas et nisl nec erat porttitor interdum. Phasellus ultrices viverra erat. Phasellus nec nisl vitae urna varius blandit. Suspendisse viverra lacus ac erat condimentum, nec mattis est feugiat. Etiam pretium augue id augue maximus, vestibulum pellentesque arcu aliquet. Vestibulum eget felis dictum, rutrum massa gravida, congue ex.
		</p>
	
		<p>
			Donec sollicitudin eros id tincidunt molestie. In blandit dolor id auctor volutpat. Cras commodo posuere lorem a cursus. Suspendisse commodo, nibh id aliquam egestas, lectus enim mattis libero, vitae sagittis est urna ac orci. Maecenas nec orci id tellus tempor venenatis nec eget mi. Praesent porta eu ex vitae hendrerit. Curabitur id rhoncus eros. Aenean vel scelerisque libero. Pellentesque auctor, lacus quis vehicula congue, nulla mauris lacinia est, eu feugiat nibh felis vel mi.
		</p>

		<p>
			Mauris eu placerat lorem. Integer lobortis, mi quis varius feugiat, odio metus scelerisque odio, et aliquet mi est vel tellus. Donec augue lorem, gravida et consectetur at, ullamcorper ac augue. Vivamus lobortis, sapien vitae malesuada maximus, ante nibh dignissim purus, nec hendrerit ligula arcu in sem. Pellentesque laoreet nulla vehicula mauris pellentesque elementum. Phasellus pulvinar mauris a facilisis rutrum. Phasellus dignissim purus sit amet lorem fringilla, at ornare nulla finibus. Sed dapibus metus gravida nulla tincidunt tristique.
		</p>
	
		<p>
			Vivamus volutpat auctor orci eu interdum. Pellentesque malesuada ante eros, sit amet porta lectus mollis vitae. Integer volutpat, sem vitae tincidunt condimentum, libero tortor gravida justo, quis gravida diam dui sed urna. Sed nec dolor sed libero efficitur elementum interdum vitae ante. Mauris tristique magna odio, eu iaculis diam eleifend at. Maecenas consectetur arcu gravida, dignissim nisi sed, faucibus metus. Quisque interdum odio bibendum, laoreet massa in, fringilla sem. Sed sit amet nisl eget urna tempus malesuada. Quisque egestas gravida diam, et facilisis odio aliquam quis. Quisque condimentum cursus lacus, eu sagittis dolor blandit vitae. Sed pellentesque, est at luctus blandit, odio ligula condimentum libero, at dignissim lacus risus at lectus. Etiam suscipit ex eros, ut tincidunt justo dignissim vel. Maecenas molestie metus at justo euismod tristique.
		</p>
	
		<p>
			Etiam in nulla neque. Aliquam egestas at ligula in interdum. Curabitur hendrerit mattis neque non condimentum. Fusce dui libero, pulvinar sed interdum quis, varius eu nisi. Curabitur efficitur et ligula eu tempor. Sed a sodales urna. In egestas vitae turpis ut blandit. Morbi sem dui, ornare luctus viverra ac, laoreet a quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla elementum elementum pulvinar. Etiam sed risus risus. Donec eget faucibus neque.
		</p>
	
		<p>
			Pellentesque lacus ipsum, ornare ut vehicula vel, mattis a justo. Curabitur elementum risus id justo ullamcorper ullamcorper. Morbi facilisis ipsum in sem ornare vulputate. Donec vel lorem est. Praesent sollicitudin ligula et elit pellentesque, nec tristique magna aliquam. Nunc pretium eros vel sem aliquam, ultricies molestie ex faucibus. Nulla facilisi. Nunc quis molestie dolor. Integer est lorem, tempor vitae feugiat vel, vehicula sit amet purus. Donec at consequat odio. Integer cursus rhoncus dui. Morbi efficitur odio at bibendum maximus. Cras eget est id libero convallis lobortis. Phasellus consectetur tincidunt felis, at feugiat enim sodales non.
		</p>
	
		<p>
			Maecenas luctus arcu diam, et euismod mi gravida eget. Aliquam scelerisque ornare cursus. Sed nec rutrum elit. Proin vel nisl dictum, congue lectus ut, consequat massa. Maecenas euismod arcu mauris, non commodo tortor varius vel. Morbi sed sapien ac purus semper interdum. Etiam commodo sodales placerat.
		</p>
	
		Suspendisse dapibus faucibus efficitur. Aliquam at massa erat. Phasellus sed tristique nisi. Donec aliquam pulvinar tortor in sagittis. Nullam tincidunt porttitor ultrices. Maecenas sed sem at quam malesuada tincidunt. Vivamus ac quam ac lacus fermentum suscipit. Donec sed lectus est. Nulla odio sapien, consequat nec sollicitudin in, elementum sit amet enim. Fusce eget tempus lorem. Cras efficitur semper egestas.
		
		Aenean scelerisque lacus id libero consequat lobortis. Ut eget interdum tortor. Quisque euismod augue sit amet ex tincidunt varius. Phasellus feugiat sagittis libero, sed porttitor augue hendrerit vel. Quisque lectus felis, varius eu justo vitae, malesuada dictum ligula. In nec nunc elit. Etiam tempus purus ut lacus venenatis, vitae mollis mi porttitor. Aliquam eget dui blandit, efficitur risus blandit, mattis quam. In mauris mauris, finibus eget luctus in, consequat nec tellus. Donec non erat arcu. Cras feugiat lorem vitae justo facilisis luctus. Ut vitae dui in urna mattis vulputate et sit amet neque. Vestibulum et metus id sapien commodo condimentum ut pellentesque enim. 
		
	</div>
</div>