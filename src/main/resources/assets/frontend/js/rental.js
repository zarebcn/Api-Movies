const apiRentalsUrl = "/api/rental/";

homePageRedirect();
loadAndDisplayRentals();


function loadAndDisplayRentals() {

    loadRentals().then(rentals => {
        console.log(rentals);
        displayRentals(rentals);
        returnMovie(rentals);
        viewRentalsById(rentals);
    });
}

function homePageRedirect() {

    const button = document.querySelector("button");

    button.onclick = function () {

        location.href = "http://localhost:8080/movies.html";
    }
}

function loadRentals() {

    return axios.get(apiRentalsUrl)
        .then(response => response.data)
        .catch(error => {
            console.log("AJAX request finished with an error :(");
            console.error(error);
        });
}

function displayRentals(rentals) {

    let html = "<ul>";

    if (rentals.length > 0) {

        for (const rental of rentals) {

            html += '<li>' + 'Rental ' + rental.rentalid + ' ====> User: ' + rental.userid
                + ', rented movie: ' + rental.movie.title + ' (' + 'Movie ID ' + rental.movieid + ')' + '</li>';
        }

        html += "</ul>";

    } else {

        html = "<p>" + "No rentals found" + "</p>";
    }

    const resultDiv = document.getElementById("result3");
    resultDiv.innerHTML = html;
}

/** returns a movie, deletes the rental and sets the movie as available again */
function returnMovie(rentals) {

    let returnmoviebutton = document.querySelector(".returnmoviebutton");

    if (rentals.length === 0) {
        returnmoviebutton.style.display = "none";
    }

    returnmoviebutton.onclick = function () {

        document.querySelector(".returninput").style.display = "initial";
        document.querySelector(".returnokbutton").style.display = "initial";
        document.querySelector(".returnbackbutton").style.display = "initial";
        document.querySelector(".userrentals").style.display = "none";
        document.querySelector(".userbackbutton").style.display = "none";
        returnmoviebutton.style.display = "none";
    }

    let returnbackbutton = document.querySelector(".returnbackbutton");

    returnbackbutton.onclick = function () {

        document.querySelector(".returninput").value = "";
        document.querySelector(".returninput").style.display = "none";
        document.querySelector(".returnokbutton").style.display = "none";
        document.querySelector(".returnbackbutton").style.display = "none";
        document.querySelector(".userrentals").style.display = "initial";
        returnmoviebutton.style.display = "initial";
        loadAndDisplayRentals();
    }

    $(".returnokbutton").click(function () {

        const input = parseInt($('.returninput').val());

        if (input && isNaN(input) === false) {

            // do a callback for getting the movieid and the movie of the rental that is going to be deleted
            axios
                .get("/api/rental/" + input)
                .then(response => response.data)
                .then(rental => {
                    console.log("selected rental", rental);
                    const movieid = rental.movieid;
                    const movie = rental.movie;

                    // this callback sets the movie as available again
                    axios
                        .put("/api/movies/available/" + movieid, movie)
                        .then(response => response.data)
                        .then(returnedMovie => {
                            console.log("returned movie", returnedMovie);
                        })
                        .catch(error => console.error("Error returning movie!", error));

                    // and finally this one deletes the rental
                    axios
                        .delete("/api/rental/" + input)
                        .then(response => response.data)
                        .then(deletedRental => {
                            console.log("deleted rental");
                            loadAndDisplayRentals();
                        })
                        .catch(error => console.error("Error deleting rental!", error));

                })
                .catch(error => console.error("Error showing rental!", error));


            document.querySelector(".returninput").value = "";
            document.querySelector(".returninput").style.display = "none";
            document.querySelector(".returnokbutton").style.display = "none";
            document.querySelector(".returnbackbutton").style.display = "none";
            returnmoviebutton.style.display = "initial";

        } else {

            document.querySelector(".returninput").value = "";
        }
    });
}

function viewRentalsById(rentals) {

    if (rentals.length === 0) {
        document.querySelector(".userrentals").style.display = "none";
    }

    /** Show the rentals of the selected user */
    $('.userrentals').click(function() {

        document.querySelector(".userinput").style.display = "initial";
        document.querySelector(".userokbutton").style.display = "initial";
        document.querySelector(".userbackbutton").style.display = "initial";
        document.querySelector(".userrentals").style.display = "none";
        document.querySelector(".returnmoviebutton").style.display = "none";
    });

    $('.userbackbutton').click(function() {

        location.href = "http://localhost:8080/rental.html";
    });

    $('.userokbutton').click(function() {

        const userInput = parseInt(document.querySelector(".userinput").value);
        document.querySelector(".returnmoviebutton").style.display = "initial";
        document.querySelector(".userinput").value = "";
        document.querySelector(".userinput").style.display = "none";
        document.querySelector(".userokbutton").style.display = "none";

        if (userInput && isNaN(userInput) === false) {

            console.log(userInput);

            axios
                .get("/api/rental/user/" + userInput)
                .then(response => response.data)
                .then(rentals => {
                    console.log("movies rented by this user", rentals);
                    displayRentals(rentals);
                })
                .catch(error => console.error("Error showing user rented movies!", error));

        }

    });
}
