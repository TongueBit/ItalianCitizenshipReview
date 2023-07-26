// the index of the first service provider to show
var startIndex = 0;
var div_index = 0;
// the number of service providers to show at a time
var pageSize = 3;

var flag = true;


// get the service providers containers
var serviceProvidersContainer1 = document.getElementById("service-providers");

var loadMoreButton = document.getElementById("load-more-button");

let serviceProviders = [];
window.onload = function() {
    fetch('/rest/service-provider')
        .then(response => response.json())
        .then(data => {
            serviceProviders = data;
            // here you can use the data to populate your serviceProviders array
            console.log(data); // for example, you can log the data to the console
            loadFirstServiceProviders(serviceProviders);
            startStarConversion(ratingElements);

        })
        .catch(error => console.error(error));
};

loadMoreButton.addEventListener("click", function() {
    showNextServiceProviders();
    var newRatingElements = document.getElementsByClassName('rating');
    startStarConversion(newRatingElements);
});

// function to load the first page of service providers
function loadFirstServiceProviders(serviceProviders) {
    // hide all the service providers
    for (var i = 0; i < serviceProvidersContainer1.children.length; i++) {
        serviceProvidersContainer1.children[i].style.display = "none";
    }


    // show the first page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (i < 3) {
            showServiceProvider(startIndex + i, serviceProvidersContainer1);
        }
    }
}

// function to show the next page of service providers
function showNextServiceProviders() {
    div_index = 0;
    // hide the current page of service providers
    for (var i = 0; i < pageSize; i++) {

        hideServiceProvider(startIndex + i, serviceProvidersContainer1);

    }

    // update the start index
    startIndex += pageSize;
    var temp = startIndex;
    // show the next page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (startIndex + i < temp + 3 && startIndex + i < serviceProviders.length) {
            showServiceProvider(startIndex + i, serviceProvidersContainer1);
        } else {
            hideServiceProvider(i, serviceProvidersContainer1);
        }

    }
    if (startIndex + i > serviceProviders.length + pageSize && flag) {
        flag = false;
        var sorry = document.createElement('h1');
        sorry.textContent = "Sorry, that's all!";
        sorry.style.fontFamily = 'Italiana'
        serviceProvidersContainer1.appendChild(sorry);
    }

}
// function to show a service provider
function showServiceProvider(index, serviceProvidersContainer1) {
    if (index < serviceProviders.length) {
        var serviceProvider = serviceProviders[index];
        var serviceProviderDiv = serviceProvidersContainer1.children[div_index++];
        var containerDiv = serviceProviderDiv.children[0];
        var cardDiv = containerDiv.children[0];
        var cardBodyDiv = serviceProviderDiv.querySelector('.card-body');
        var title = cardBodyDiv.children[0];

        var paragraph = cardBodyDiv.querySelector('p');


        if (paragraph) {
            paragraph.innerHTML = serviceProvider.description;
        }
        // Add the name of the service provider
        title.textContent = serviceProvider.name;
        // Add the rating of the service provider
        if (serviceProvider.rating != null) {
            cardBodyDiv.children[1].innerHTML = '<h5 class="rating">' + serviceProvider.rating + '</h5>';
        }

        // Add the logo image
        var logoImg = document.createElement("img");
        logoImg.src = serviceProvider.logoUrl;
        logoImg.alt = "Service Provider Logo";
        logoImg.className = "logo-img";
        containerDiv.prepend(logoImg);

        var cardBodyDiv = serviceProviderDiv.querySelector('.card-body');
        var readMoreLink = cardBodyDiv.querySelector('a.read-more');
        readMoreLink.href = "/service-provider/" + serviceProvider.serviceProviderId;
        readMoreLink.textContent = "Read More";

        cardDiv.appendChild(readMoreLink);
        serviceProviderDiv.addEventListener("click", function() {
            // Redirect to the service provider's page or perform any desired action
            window.location.href = "/service-provider/" + serviceProvider.serviceProviderId;
        });
        serviceProviderDiv.addEventListener("mouseenter", function() {
            serviceProviderDiv.classList.add("hovered");
        });

        // Remove CSS class when mouse leaves the serviceProviderDiv
        serviceProviderDiv.addEventListener("mouseleave", function() {
            serviceProviderDiv.classList.remove("hovered");
        });
        serviceProviderDiv.style.display = "block";
    }
}



// function to hide a service provider
function hideServiceProvider(index, serviceProvidersContainer) {
    if (index < serviceProvidersContainer.children.length) {
        var serviceProviderDiv = serviceProvidersContainer.children[index];
        serviceProviderDiv.style.display = "none";
    }
}
