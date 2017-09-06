$(function() {

	// Router
	function init() {

        // Routie is a routing library, more info: https://github.com/jgallen23/routie
        routie({
        	'' : dashboard,
        	'guests' : guests,
        	'rooms' : rooms,
        	'bookings' : bookings,
        	'debug' : debug
        });
	}

	// Views
	function dashboard() {
	    console.debug('home view');
		setView('dashboard');
	}

	function guests() {
        console.debug('guests view');
        setView('guests');
	}

	function rooms() {
        console.debug('rooms view');
        setView('rooms');
	}

	function bookings() {
        console.debug('bookings view');
        setView('bookings');
	}

	function debug() {
		console.debug('debug view');
		setView('debug');
	}

	// Helper functions
	function setView(view) {

          // Set active item
          $('.nav-item').removeClass('active');
          $('#' + view).addClass('active')

          // Download HTML and set page as active
		  $.ajax({
            url: '/templates/' + view + '.html',
            cache: false
        }).done(function(html) {
            $('#content').html(html);
        });
	}

	init();
});