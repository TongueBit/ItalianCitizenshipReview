function createReview(event) {
    event.preventDefault();
    var index = event.target.getAttribute('data-index');
    var serviceProviderId = document.getElementById('serviceProvider-' + index).value;
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
                setTimeout(() => {
                    //location.reload();
                }, 1000);
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
    for (var i = 0; i < ratingElements.length; i++) {
        var ratingElement = ratingElements[i];

        var rating = parseFloat(ratingElement.innerText);
        var starRating = convertToStarRating(rating);
        ratingElement.innerHTML = starRating;

        // Add a class to mark conversion is done
        ratingElement.classList.add('star-rating-converted');
    }
}

function convertToStarRating(rating) {
    var nullRating = '';
    if (rating === null) {
        for(i=0; i<5; i++) {
            nullRating += '<i class="far fa-star star-grey"></i>';
        }
        return nullRating;
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
                setTimeout(() => {
                    //location.reload();
                }, 1000);
            } else {
                throw new Error('Error creating review');
            }
        })
        .catch(error => {
            // Handle error
            console.error(error);
        });
}