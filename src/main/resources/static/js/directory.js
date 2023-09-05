function createReview(event) {
    event.preventDefault();
    var index = event.target.getAttribute('data-index');
    var serviceProviderId = document.getElementById('serviceProvider-' + (index-1)).value;
    var title = document.getElementById('title-' + index).value;
    var content = document.getElementById('content-' + index).value;
    var rating = document.getElementById('rating-' + index).value;
    var cookies = document.cookie.split(';');
    var userId = null;

    for (var j = 0; j < cookies.length; j++) {
        var cookie = cookies[j].trim();
        if (cookie.startsWith('userId=')) {
            userId = cookie.substring('userId='.length, cookie.length);
            break;
        }
    }

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
                addReviewToDOM(reviewRequest, index);
                var ratingElement = document.querySelectorAll('#new_rating')
                startStarConversion(ratingElement)
            } else {
                throw new Error('Error creating review');
            }
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
}

var ratingElements = document.getElementsByClassName('rating');

function startStarConversion(ratingElements) {
    if (ratingElements.length === 1) {
        var rating = parseFloat(ratingElements[0].innerText);
        var starRating = convertToStarRating(rating);
        ratingElements[0].innerHTML = starRating;
        // Add a class to mark conversion is done
    }
    else {
        for (var i = 0; i < ratingElements.length; i++) {
            var ratingElement = ratingElements[i];

            var rating = parseFloat(ratingElement.innerText);
            var starRating = convertToStarRating(rating);
            ratingElement.innerHTML = starRating;

            // Add a class to mark conversion is done
            ratingElement.classList.add('star-rating-converted');
        }
    }
}


function convertToStarRating(rating) {

    if (rating === null || rating === 0) {
        return '';
    }


    var fullStars = Math.round(rating);
    var starRating = '';

    for (var i = 0; i < 5; i++) {
        if (i < fullStars) {
            starRating += '<i class="fas fa-star star-yellow" style="color: #ffaa4a;"></i>';
        } else {
            starRating += '<i class="far fa-star star-grey"></i>';
        }
    }

    return starRating;
}

function createReviewFromServiceProviderPage(event) {
    event.preventDefault();
    var index = event.target.getAttribute('data');
    var serviceProviderId = document.getElementsByName('service-provider-id')[0].value;
    var title = document.getElementById('title').value;
    var content = document.getElementById('content').value;
    var rating = document.getElementById('rating').value;
    var cookies = document.cookie.split(';');
    var userId = null;

    for (var j = 0; j < cookies.length; j++) {
        var cookie = cookies[j].trim();
        if (cookie.startsWith('userId=')) {
            userId = cookie.substring('userId='.length, cookie.length);
            break;
        }
    }

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
                location.reload();
            } else {
                throw new Error('Error creating review');
            }
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
}

function addReviewToDOM(review, index) {
    var serviceProviderDiv = document.getElementById(index);
    var reviewList = serviceProviderDiv.querySelector('#reviewList ul');

    var liElement = document.createElement('li');
    var titleElement = document.createElement('p');
    titleElement.innerHTML = '<b>' + review.title + '</b>';

    var contentElement = document.createElement('p');
    contentElement.innerHTML = review.content;

    var ratingElement = document.createElement('p');
    ratingElement.setAttribute('id', 'new_rating');
    ratingElement.innerHTML = '<b>' + review.rating + '</b>';

    liElement.appendChild(titleElement);
    liElement.appendChild(contentElement);
    liElement.appendChild(ratingElement);
    reviewList.appendChild(liElement);
}
startStarConversion(ratingElements);