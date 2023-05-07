function createReview() {
    var serviceProviderId = document.getElementById('serviceProviderId').value;
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
                    location.reload();
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