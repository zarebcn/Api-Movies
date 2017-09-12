
let moviesPromise = loadMovie(getIdFromUrl());

moviesPromise.then(movie => {
    displayMovie(movie);
});

homePageRedirect();


/** Goes to Home page when home button is clicked */
function homePageRedirect() {

    const button = document.querySelector("button");

    button.onclick = function () {

        location.href = "http://localhost:8080/movies.html";
    }
}

function loadMovie(movieid) {

    let url = '/api/movies/' + movieid;

    // We return the promise that fetch() gives us
    return fetch(url)
        .then(response => response.json())
        .catch(error => {
            console.log("AJAX request finished with an error :(");
            console.error(error);
        });
}

/** Extracts the id parameter from the URL and returns it*/
function getIdFromUrl() {

    let url = location.href;
    let equalSignIndex = url.indexOf("=");
    return url.substring(equalSignIndex + 1, url.length);
}

function displayMovie(movie) {

    let html = "";

    html += movie.title + "<br>";

    html += movie.director;

    const resultDiv = document.getElementById("result");
    resultDiv.innerHTML = html;
}