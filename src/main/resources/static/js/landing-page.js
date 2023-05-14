// the index of the first service provider to show
var startIndex = 0;

// the number of service providers to show at a time
var pageSize = 8;

// get the service providers container
var serviceProvidersContainer = document.getElementById("service-providers");

document.addEventListener("DOMContentLoaded", function() {
    fetch('/rest/service-provider')
        .then(response => response.json())
        .then(data => {
            var serviceProviders = data;
            // here you can use the data to populate your serviceProviders array
            console.log(data); // for example, you can log the data to the console
            loadFirstServiceProviders(serviceProviders);
        })
        .catch(error => console.error(error));
});

// function to load the first page of service providers
function loadFirstServiceProviders(serviceProviders) {
    // hide all the service providers
    console.log(serviceProvidersContainer)
    for (var i = 0; i < serviceProvidersContainer.children.length; i++) {
        serviceProvidersContainer.children[i].style.display = "none";
    }

    // show the first page of service providers
    for (var i = 0; i < pageSize; i++) {
        showServiceProvider(startIndex + i, serviceProviders);
    }
}

// function to show the next page of service providers
function showNextServiceProviders(serviceProviders) {
    // hide the current page of service providers
    for (var i = 0; i < pageSize; i++) {
        hideServiceProvider(startIndex + i, serviceProviders);
    }

    // update the start index
    startIndex += pageSize;

    // show the next page of service providers
    for (var i = 0; i < pageSize; i++) {
        showServiceProvider(startIndex + i, serviceProviders);
    }
}

// function to show a service provider
function showServiceProvider(index, serviceProviders) {
    if (index < serviceProviders.length) {
        var serviceProvider = serviceProviders[index];
        var serviceProviderDiv = serviceProvidersContainer.children[index];
        var containerDiv = serviceProviderDiv.children[0];
        var cardDiv = containerDiv.children[0];
        cardDiv.children[0].innerHTML = serviceProvider.name;
        cardDiv.children[1].innerHTML = serviceProvider.description;
        if(serviceProvider.rating!= null)
            cardDiv.children[2].innerHTML = serviceProvider.description;
        serviceProviderDiv.style.display = "block";
    }
}

// function to hide a service provider
function hideServiceProvider(index, serviceProviders) {
    if (index < serviceProviders.length) {
        var serviceProviderDiv = serviceProvidersContainer.children[index];
        serviceProviderDiv.style.display = "none";
    }
}


