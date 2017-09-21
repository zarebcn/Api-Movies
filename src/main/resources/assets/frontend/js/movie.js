
const apiMoviesUrl = '/api/movies/';

homePageRedirect();
loadAndDisplayMovie();

function loadAndDisplayMovie() {

    loadMovie(getIdFromUrl()).then(movie => {
        displayMovie(movie);
        rentMovie(movie);
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

    var availableText;

    if (movie.available === true) {
        availableText = '<p class=available>Available</p>';
    } else {
        availableText = '<p class=rented>Rented</p>';
    }

    let html = "";

    html += "<h2>" + movie.title + "</h2>";

    html += "<p>" + movie.director + "</p>";

    html += "<p>(" + movie.year + ")</p>";

    html += '<img class=cover src=' + movie.cover + '>';

    html += availableText;

    const resultDiv = document.getElementById("result2");
    resultDiv.innerHTML = html;
}

/** Creates a rental and sets the movie as rented */
function rentMovie(movie) {

    let rentButton = document.querySelector(".rentbutton");

    if (!movie.available) {
        rentButton.style.display = "none";
    }

    rentButton.onclick = function () {

        document.querySelector(".rentinput").style.display = "initial";
        document.querySelector(".okbutton").style.display = "initial";
        document.querySelector(".rentbackbutton").style.display = "initial";
        rentButton.style.display = "none";
    }

    let rentBackButton = document.querySelector(".rentbackbutton");

    rentBackButton.onclick = function () {

        document.querySelector(".rentinput").value = "";
        document.querySelector(".rentinput").style.display = "none";
        document.querySelector(".okbutton").style.display = "none";
        document.querySelector(".rentbackbutton").style.display = "none";
        rentButton.style.display = "initial";
    }

    $(".okbutton").click(function () {

        const input = parseInt($('.rentinput').val());

        const rental = {
            movieid: getIdFromUrl(),
            userid: $('.rentinput').val()
        };

        if (input && isNaN(input) === false) {

            axios
                .put("/api/movies/rented/" + getIdFromUrl(), movie)
                .then(response => response.data)
                .then(rentedMovie => {
                    console.log("rented movie", rentedMovie);
                })
                .catch(error => console.error("Error renting movie!", error));

            axios
                .post("/api/rental/", rental)
                .then(response => response.data)
                .then(createdRental => {
                    console.log("created rental", createdRental);
                    loadAndDisplayMovie(); // to refresh list
                })
                .catch(error => console.error("Error creating rental!", error));

            document.querySelector(".rentinput").value = "";
            document.querySelector(".rentinput").style.display = "none";
            document.querySelector(".okbutton").style.display = "none";
            document.querySelector(".rentbackbutton").style.display = "none";

        } else {

            document.querySelector(".rentinput").value = "";
        }
    });

}