// the index of the first service provider to show
var startIndex = 0;

// the number of service providers to show at a time
var pageSize = 8;

// get the service providers containers
var serviceProvidersContainer1 = document.getElementById("service-providers");
var serviceProvidersContainer2 = document.getElementById("service-providers2");

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
    for (var i = 0; i < serviceProvidersContainer1.children.length; i++) {
        serviceProvidersContainer1.children[i].style.display = "none";
    }
    for (var i = 0; i < serviceProvidersContainer2.children.length; i++) {
        serviceProvidersContainer2.children[i].style.display = "none";
    }

    // show the first page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (i < 4) {
            showServiceProvider(startIndex + i, serviceProviders, serviceProvidersContainer1);
        } else {
            showServiceProvider(startIndex + i, serviceProviders, serviceProvidersContainer2);
        }
    }
}

// function to show the next page of service providers
function showNextServiceProviders(serviceProviders) {
    // hide the current page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (startIndex + i < 4) {
            hideServiceProvider(startIndex + i, serviceProvidersContainer1);
        } else {
            hideServiceProvider(startIndex + i - 4, serviceProvidersContainer2);
        }
    }

    // update the start index
    startIndex += pageSize;

    // show the next page of service providers
    for (var i = 0; i < pageSize; i++) {
        if (startIndex + i < 4) {
            showServiceProvider(startIndex + i, serviceProviders, serviceProvidersContainer1);
        } else {
            showServiceProvider(startIndex + i - 4, serviceProviders, serviceProvidersContainer2);
        }
    }
}

// function to show a service provider
function showServiceProvider(index, serviceProviders, serviceProvidersContainer1) {
    if (index < serviceProviders.length) {
        var serviceProvider = serviceProviders[index];
        var serviceProviderDiv, containerDiv, cardDiv;
        if (index < serviceProvidersContainer1.children.length) {
            serviceProviderDiv = serviceProvidersContainer1.children[index];
        } else {
            serviceProviderDiv = serviceProvidersContainer2.children[index - serviceProvidersContainer1.children.length];
        }
        containerDiv = serviceProviderDiv.children[0];
        cardDiv = containerDiv.children[0];
        cardDiv.children[0].innerHTML = serviceProvider.name;
        cardDiv.children[1].innerHTML = serviceProvider.description;
        if(serviceProvider.rating!= null)
            cardDiv.children[2].innerHTML = serviceProvider.rating;
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
