$(function() {

  var siteSticky = function() {
		$(".js-sticky-header").sticky({topSpacing:0});
	};
	siteSticky();

	var siteMenuClone = function() {

		$('.js-clone-nav').each(function() {
			var $this = $(this);
			$this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
		});


		setTimeout(function() {

			var counter = 0;
      $('.site-mobile-menu .has-children').each(function(){
        var $this = $(this);

        $this.prepend('<span class="fa fa-arrow-down collapsed">');

        $this.find('.fa-arrow-down').attr({
          'data-toggle' : 'collapse',
          'data-target' : '#collapseItem' + counter,
        });

        $this.find('> ul').attr({
          'class' : 'collapse',
          'id' : 'collapseItem' + counter,
        });

        counter++;

      });

    }, 1000);

		$('body').on('click', '.fa-caret-down', function(e) {
      var $this = $(this);
      if ( $this.closest('li').find('.collapse').hasClass('show') ) {
        $this.removeClass('active');
      } else {
        $this.addClass('active');
      }
      e.preventDefault();

    });

		$(window).resize(function() {
			var $this = $(this),
				w = $this.width();

			if ( w > 768 ) {
				if ( $('body').hasClass('offcanvas-menu') ) {
					$('body').removeClass('offcanvas-menu');
				}
			}
		})

		$('body').on('click', '.js-menu-toggle', function(e) {
			var $this = $(this);
			e.preventDefault();

			if ( $('body').hasClass('offcanvas-menu') ) {
				$('body').removeClass('offcanvas-menu');
				$this.removeClass('active');
			} else {
				$('body').addClass('offcanvas-menu');
				$this.addClass('active');
			}
		})

		// click outisde offcanvas
		$(document).mouseup(function(e) {
	    var container = $(".site-mobile-menu");
	    if (!container.is(e.target) && container.has(e.target).length === 0) {
	      if ( $('body').hasClass('offcanvas-menu') ) {
					$('body').removeClass('offcanvas-menu');
				}
	    }
		});
	};
	siteMenuClone();

});

function createRegisterSection() {
	var registerSection = document.createElement('section');
	registerSection.id = 'register';
	registerSection.classList.add('register-section');
	registerSection.style.height = '100%';
	registerSection.style.paddingTop = '150px';


	var registerDiv = document.createElement('div');
	registerDiv.classList.add('container');

	var registerRow = document.createElement('div');
	registerRow.classList.add('row', 'justify-content-center');

	var registerCol = document.createElement('div');
	registerCol.classList.add('col-lg-6');

	var registerContent = document.createElement('div');
	registerContent.classList.add('p-5');

	var registerTitle = document.createElement('div');
	registerTitle.classList.add('mb-5');
	registerTitle.innerHTML = '<h3 class="h4 font-weight-bold text-theme">Register</h3>';

	var registerForm = document.createElement('form');
	registerForm.action = '/register'; // Set the appropriate action for the register form
	registerForm.setAttribute('onsubmit', 'checkIfUsernameExists(event)');
	registerForm.method = 'POST'

	var registerEmailGroup = document.createElement('div');
	registerEmailGroup.classList.add('form-group');

	var registerEmailLabel = document.createElement('label');
	registerEmailLabel.htmlFor = 'register-username';
	registerEmailLabel.hasAttribute("username");
	registerEmailLabel.textContent = 'Email address';

	var registerEmailInput = document.createElement('input');
	registerEmailInput.name = 'username';
	registerEmailInput.classList.add('form-control');
	registerEmailInput.id = 'register-username';

	var registerPasswordGroup = document.createElement('div');
	registerPasswordGroup.classList.add('form-group');

	var registerPasswordLabel = document.createElement('label');
	registerPasswordLabel.htmlFor = "register-password";
	registerPasswordLabel.textContent = 'Password';

	var registerPasswordInput = document.createElement('input');
	registerPasswordInput.name = 'password';
	registerPasswordInput.classList.add('form-control');
	registerPasswordInput.id = "register-password";

	var registerButton = document.createElement('button');
	registerButton.type = 'submit';
	registerButton.name = 'register-button'
	registerButton.classList.add('btn', 'btn-theme');
	registerButton.textContent = 'Register';

	// Append the elements to their respective parents
	registerEmailGroup.appendChild(registerEmailLabel);
	registerEmailGroup.appendChild(registerEmailInput);

	registerPasswordGroup.appendChild(registerPasswordLabel);
	registerPasswordGroup.appendChild(registerPasswordInput);

	registerForm.appendChild(registerEmailGroup);
	registerForm.appendChild(registerPasswordGroup);
	registerForm.appendChild(registerButton);

	registerContent.appendChild(registerTitle);
	registerContent.appendChild(registerForm);

	registerCol.appendChild(registerContent);

	registerRow.appendChild(registerCol);

	registerDiv.appendChild(registerRow);

	registerSection.appendChild(registerDiv);

	// Append the register section after the photo-div
	var photoDiv = document.querySelector('.photo-div');
	photoDiv.insertAdjacentElement('afterend', registerSection);
	const elements = document.querySelectorAll('[name="password"]');

}

var registerAnchor = document.getElementById('register-anchor');


function checkIfUsernameExists(event) {
	event.preventDefault();

	const inputUsername = document.getElementById('register-username');
	const inputPassword = document.getElementById('register-password');
	const username = inputUsername.value;
	const password = inputPassword.value;

	if (username !== '') {
		// Make a request to the server to check if the username exists
		fetch('rest/user/exists/' + username)
			.then(response => response.json())
			.then(data => {
				if (data) {
					// Username exists
					Swal.fire({
						icon: 'error',
						title: 'Oops...',
						text: 'Username already exists',
					});
				} else {
					// Username does not exist, send it to the API
					fetch('/register', {
						method: 'POST',
						body: JSON.stringify({username: username, password: password}),
						headers: {
							'Content-Type': 'application/json',
						},
					})
						.then(response => {
							location.reload();

						})
						.catch(error => {
							console.error('Error sending username to API:', error);
						});
				}
			})
			.catch(error => {
				console.error('Error checking username:', error);
			});
	}
}

