

jQuery(document).ready(function($) {
	'use strict';
	;( function ( document, window, index )
	{
		//jQuery('.files input')

		var inputs = document.querySelectorAll( '.files input');
		Array.prototype.forEach.call( inputs, function( input )
		{
			input.addEventListener( 'change', function( e )
			{
				var fileName = '';
				if( this.files && this.files.length > 1 )
					fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
				else
					fileName = e.target.value.split( '\\' ).pop();

				if( fileName )
					$(input).addClass('icon-added');
				else
					$(input).removeClass('icon-added');

			});

			// Firefox bug fix
			input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
			input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
		});
	}( document, window, 0 ));

	$('#appstore').click(function(){
		ga('send', 'event', 'appstore', 'button');
		console.log('appstore');
	});
	$('#google_play').click(function(){
		ga('send', 'event', 'google_play', 'button');
		console.log('google_play');
	});
	/* Mobile Menu */
	$('#navmobile-btn').click(function(){
		$(this).closest('#navi_block').find('.main_menu').slideToggle();
	});
	
	$('select').each(function(){
		if ($(this).closest('.inp_box').data('placeholder')) {
			$(this).attr('data-placeholder', $(this).closest('.inp_box').data('placeholder'));
		}
	})
	
	/* Select */
	$('select').chosen({
		width:'100%',
		disable_search_threshold:12,
		no_results_text: "",
		allow_single_deselect:true,
		inherit_select_classes:true
	});
	
	$('input[type=checkbox]').ezMark();
	
	/* scrollTo */
	$('.scroll').click(function(){
		$.scrollTo(jQuery(this).attr("href"), {
			'duration' : 1000
		});
		return false;
	});
	
/* 	$('.cities_scroll').click(function() {
		var href = $(this).attr('href').split('#');
		jQuery.scrollTo('#'+href[href.length-1], 1000);
		return false;
	}); */
	
	if (window.location.hash) {
		var link_hash = window.location.hash;
		window.location.hash='';
		jQuery.scrollTo(link_hash, 1000, { onAfter:function(){jQuery.scrollTo(link_hash, 1000);}});
	}
	
	/* Main Slider */
	$('#main_slider').cycle({
		fx: 'fade',
		slides:'li',
		timeout:7000,
		autoHeight: 'container',
		pager:'#slides_nav',
		pagerTemplate:'<li><a href="{{link}}"><span>{{title}}</span></a></li>'
   });

	$("a#cities_scroll").click(function(event){
		event.preventDefault();
		//console.log("test");
		$('#cities').toggle('fast');
	});

	$("#slides_nav a").on("click",function(event){
		
                var href = $(this).attr("href");
                console.log(href);
                if(href != "#") {
                    window.location.href = href;
                }
	});
   
	$("#easypay_link").click(function(event){
	    $('#easypay_form').show('fast');
	});
	$("#easypay_form .close").click(function(event){
	    $('#easypay_form').hide('fast');
	});  
	$("#easypay_form form").submit(function( event ) {
	    $(this).find("input[name=amount]").removeClass("error");
	    var a = $(this).find("input[name=amount]").val();
	    if(a == "" ) {
		$(this).find("input[name=amount]").addClass("error");
		return false;
	    } else {
		return true;
	    }
	    console.log(a);
	    
	});
	
	$(".wpcf7-form .download").click(function(event){
	    $('.wpcf7-form .files').toggle('fast');
	});
	$(".download_link .close").click(function(event){
	   $(".download_link").hide();
	});
	
    setTimeout(function() {
        //$("iframe#WidgetEvosFrame").contents().find("footer a").click();
    }, 1000);
   
});

