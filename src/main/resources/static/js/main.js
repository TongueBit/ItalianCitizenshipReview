let flag1 = false;

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
	if(flag1 === false) {
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
		registerForm.method = 'POST';

		var registerUsernameGroup = document.createElement('div');
		registerUsernameGroup.classList.add('form-group');

		var registerUsernameLabel = document.createElement('label');
		registerUsernameLabel.htmlFor = 'register-username';
		registerUsernameLabel.textContent = 'Username';

		var registerUsernameInput = document.createElement('input');
		registerUsernameInput.name = 'username';
		registerUsernameInput.classList.add('form-control');
		registerUsernameInput.id = 'register-username';

		var registerEmailGroup = document.createElement('div');
		registerEmailGroup.classList.add('form-group');

		var registerEmailLabel = document.createElement('label');
		registerEmailLabel.htmlFor = 'register-email';
		registerEmailLabel.textContent = 'Email';

		var registerEmailInput = document.createElement('input');
		registerEmailInput.name = 'email';
		registerEmailInput.classList.add('form-control');
		registerEmailInput.id = 'register-email';

		var registerPasswordGroup = document.createElement('div');
		registerPasswordGroup.classList.add('form-group');

		var registerPasswordLabel = document.createElement('label');
		registerPasswordLabel.htmlFor = 'password';
		registerPasswordLabel.textContent = 'Password';

		var registerPasswordInput = document.createElement('input');
		registerPasswordInput.name = 'password';
		registerPasswordInput.classList.add('form-control');
		registerPasswordInput.id = 'password';
		registerPasswordInput.type = 'password';

		var showPasswordCheckbox = document.createElement('input');
		showPasswordCheckbox.type = 'checkbox';
		showPasswordCheckbox.onclick = showPassword;


		var showPasswordLabel = document.createElement('label');
		showPasswordLabel.textContent = 'Show Password';

		// Append the checkbox and label to a container element
		var checkboxContainer = document.createElement('div');
		checkboxContainer.appendChild(showPasswordCheckbox);
		checkboxContainer.appendChild(showPasswordLabel);


		var registerButton = document.createElement('button');
		registerButton.type = 'submit';
		registerButton.name = 'register-button';
		registerButton.classList.add('btn', 'btn-theme');
		registerButton.textContent = 'Register';

		registerEmailGroup.appendChild(registerUsernameLabel);
		registerEmailGroup.appendChild(registerUsernameInput);

		registerEmailGroup.appendChild(registerEmailLabel);
		registerEmailGroup.appendChild(registerEmailInput);

		registerPasswordGroup.appendChild(registerPasswordLabel);
		registerPasswordGroup.appendChild(registerPasswordInput);
		registerPasswordGroup.appendChild(checkboxContainer);


		registerForm.appendChild(registerUsernameGroup);
		registerForm.appendChild(registerEmailGroup);
		registerForm.appendChild(registerPasswordGroup);
		registerForm.appendChild(registerButton);

		registerContent.appendChild(registerTitle);
		registerContent.appendChild(registerForm);

		registerCol.appendChild(registerContent);

		registerRow.appendChild(registerCol);

		registerDiv.appendChild(registerRow);

		registerSection.appendChild(registerDiv);

		var photoDiv = document.querySelector('.photo-div');
		photoDiv.insertAdjacentElement('afterend', registerSection);

		const elements = document.querySelectorAll('[name="password"]');
		flag1 = true;
	}else {
		return;
	}
}



function checkIfUsernameExists(event) {
	event.preventDefault();

	const inputUsername = document.getElementById('register-username');
	const inputEmail = document.getElementById('register-email');
	const inputPassword = document.getElementById('password');

	const username = inputUsername.value;
	const password = inputPassword.value;
	const email = inputEmail.value;

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
					fetch('/rest/register/' + email)
						.then(response => {

							if (!response.ok) {
								throw new Error('Error fetching registration data');
							}

						})
						.then(() => {
							Swal.fire({
								title: 'Enter Verification Code',
								text: 'A verification code has been sent to your email address.',
								input: 'text',
								showCancelButton: true,
								confirmButtonText: 'Submit',
								showLoaderOnConfirm: true,
								preConfirm: (code) => {
									// Send GET request with verification code
									return fetch('/rest/verify/' + code)
										.then(response => {
											if (!response.ok) {
												throw new Error('Error fetching registration data');
											}

										})
										.then(() => {

											// Perform registration
											fetch('/rest/register', {
												method: 'POST',
												body: JSON.stringify({
													username: username,
													email: email,
													password: password
												}),
												headers: {
													'Content-Type': 'application/json',
												},
											})
												.then(response => {
													if (!response.ok) {
														throw new Error('Error fetching registration data');
													}
												})
												.then(() => {
													Swal.fire('Verification Successful!', 'You can now log in.', 'success')
														.then(() => {
															// Redirect to login page
															window.location.href = '/login';
														});
												})
												.catch(error => {
													console.error('Error sending registration data:', error);
													Swal.fire('Registration Failed!', 'An error occurred during registration.', 'error');
												});

										})
										.catch(error => {
											console.error('Error sending verification code:', error);
											Swal.fire('Verification Failed!', 'An error occurred.', 'error');
										});
								},
							});
						})
						.catch(error => {
							console.error('Error fetching registration data:', error);
							Swal.fire('Registration Failed!', 'An error occurred while registering.', 'error');
						});
				}
			})
			.catch(error => {
				console.error('Error checking username:', error);
			});
	}
}

function showPassword(event) {
	var x = document.getElementById("password");
	if (x.type === "password") {
		x.type = "text";
	}	 else {
		x.type = "password";
	}
}

function submitForm(event) {
	var cookies = document.cookie.split(';');

	var userId = null;

	for (var j = 0; j < cookies.length; j++) {
		var cookie = cookies[j].trim();
		if (cookie.startsWith('userId=')) {
			userId = cookie.substring('userId='.length, cookie.length);
			break;
		}
	}
	event.preventDefault(); // Prevent the default form submission behavior

	// Retrieve the form data
	const form = document.getElementById('reviewForm');
	const formData = new FormData(form);

	// Access individual form fields by name
	const title = formData.get('title');
	const content = formData.get('content');
	const rating = formData.get('rating');
	const serviceProviderId = formData.get('serviceProviderId');

	var reviewRequest = {
		serviceProviderId: serviceProviderId,
		title: title,
		content: content,
		rating: rating,
		userId: userId
	};

	fetch('/rest/review', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(reviewRequest)
	})
		.then(response => {
			if (response.ok) {
				window.location.href = "/service-provider/" + serviceProviderId;
			} else if(response.status === 409) {
				Swal.fire('You have already reviewed this service provider.', 'Please edit your review in the user dashboard.', 'error');
			}
			else {
                // Handle error
                console.error('Error creating review');
				throw new Error('Error creating review');
			}
		})
		.catch(error => {
			// Handle error
			console.error(error);
		});
}

function editUsername(event) {
	var cookies = document.cookie.split(';');

	var userId = null;

	for (var j = 0; j < cookies.length; j++) {
		var cookie = cookies[j].trim();
		if (cookie.startsWith('userId=')) {
			userId = cookie.substring('userId='.length, cookie.length);
			break;
		}
	}


	const inputUsername = document.getElementById('username');


	const username = inputUsername.value;


	if (username !== '') {
		// Make a request to the server to check if the username exists
		fetch('/rest/user/exists/' + username)
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
						fetch("/rest/update/" + userId, {
							method: 'POST',
							headers: {
								'Content-Type': 'text/plain'
							},
							body: username



						})
							.then(response => {

								if (!response.ok) {
									throw new Error('Error');
								}
								Swal.fire('Username Updated!', 'Your username has been updated.', 'success')
									.then(() => {
										window.location.href = '/user/dashboard/';

									});

							})
							.catch(error => {
									console.error('Error updating username:', error);
								}
							);
					}
				}
			)
			.catch(error => {
					console.error('Error checking username:', error);
				}
			);
	}
}

