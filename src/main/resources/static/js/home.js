function createReview(userId) {
    var userId =  userId;
    var serviceProviderId = document.getElementById("serviceProviderId").value;
    var title = document.getElementById('title').value;
    var content = document.getElementById('content').value;
    var rating = document.getElementById('rating').value;

    var review = {
        title: title,
        content: content,
        userId: userId,
        serviceProviderId: serviceProviderId,
        rating: rating
    };

    // Fetch request to create the review
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
    var reviewForm = document.getElementById('reviewForm');

    reviewForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent form submission

        // Fetch request to retrieve the userId
        fetch('/review', {
            method: 'GET',
            credentials: 'include' // Include cookies in the request
        })
            .then(response => response.json())
            .then(data => {
                console.log('Response data:', data);
                var userId = data.userId;
                // Do something with the userId
                console.log('User ID:', userId);

                // Call the createReview function to submit the review with userId
                createReview(userId);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
