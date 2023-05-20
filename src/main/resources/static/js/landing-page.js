// the index of the first service provider to show
var startIndex = 0;
var div_index = 0;
// the number of service providers to show at a time
var pageSize = 4;

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
});

// function to load the first page of service providers
function loadFirstServiceProviders(serviceProviders) {
    // hide all the service providers
    for (var i = 0; i < serviceProvidersContainer1.children.length; i++) {
        serviceProvidersContainer1.children[i].style.display = "none";
    }


    // show the first page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (i < 4) {
            showServiceProvider(startIndex + i, serviceProvidersContainer1);
        }
    }
}

// function to show the next page of service providers
function showNextServiceProviders() {
    div_index = 0;
    // hide the current page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (startIndex + i <  4) {
            hideServiceProvider(startIndex + i, serviceProvidersContainer1);
        }
    }

    // update the start index
    startIndex += pageSize;
    var temp = startIndex;
    // show the next page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (startIndex + i < temp + 4) {
            showServiceProvider(startIndex + i, serviceProvidersContainer1);
        }

    }
}

// function to show a service provider
function showServiceProvider(index, serviceProvidersContainer1) {

    if (index < serviceProviders.length) {
        var serviceProvider = serviceProviders[index];
        var serviceProviderDiv, containerDiv, cardDiv;
        serviceProviderDiv = serviceProvidersContainer1.children[div_index++];

        containerDiv = serviceProviderDiv.children[0];
        cardDiv = containerDiv.children[0];
        cardDiv.children[0].innerHTML = serviceProvider.name;
        cardDiv.children[1].innerHTML = serviceProvider.description;
        if(serviceProvider.rating!= null)
            cardDiv.children[2].innerHTML = serviceProvider.rating;
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

