function createReview() {
    var serviceProviderId = document.getElementById("serviceProviderId").value;
    var title = document.getElementById('title').value;
    var content = document.getElementById('content').value;
    var userId = document.getElementById('userId').value;
    var rating = document.getElementById('rating').value;

    var review = {
        title: title,
        content: content,
        userId: userId,
        serviceProviderId: {serviceProviderId: serviceProviderId},
        rating: rating
    };

// Fetch request
    fetch('/review', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    })
        .then(response => response.json())
        .then(data => {
// Handle response data
            console.log(data);
        })
        .catch(error => {
// Handle error
            console.error(error);
        });
}

document.addEventListener('DOMContentLoaded', function() {
    // Code to execute when the page is fully loaded
    var reviewDiv = document.getElementById('reviewForm');

    reviewDiv.addEventListener('click', function() {
        // Code to execute when the review div is clicked
        fetchUserId();
    });
    let userIdRequestMade = false;

    function fetchUserId() {
        if (!userIdRequestMade) {
            // Make a fetch request to retrieve the userId
            fetch('/review', {
                method: 'GET',
                credentials: 'include' // Include cookies in the request
            })
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
                    var userId = data.userId;
                    // Do something with the userId
                    console.log('User ID:', userId);
                })
                .catch(function (error) {
                    console.error('Error:', error);
                });
            userIdRequestMade = true;
        }
    }
});
