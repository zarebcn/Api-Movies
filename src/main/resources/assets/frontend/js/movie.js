
const apiMoviesUrl = '/api/movies/';

homePageRedirect();
loadAndDisplayMovie();


function loadAndDisplayMovie() {

    loadMovie(getIdFromUrl()).then(movie => {
        displayMovie(movie);
    });
}

/** Goes to Home page when home button is clicked */
function homePageRedirect() {

    const button = document.querySelector("button");

    button.onclick = function () {

        location.href = "http://localhost:8080/movies.html";
    }
}

function loadMovie(movieid) {

    return axios.get(apiMoviesUrl + movieid)
        .then(response => response.data)
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

    html += "<h2>" + movie.title + "</h2>";

    html += "<p>" + movie.director + "</p>";

    html += "<p>(" + movie.year + ")</p>";

    html += '<img class=cover src=' + movie.cover + '>';

    const resultDiv = document.getElementById("result2");
    resultDiv.innerHTML = html;
}