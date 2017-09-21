const apiRentalsUrl = "/api/rental/";

homePageRedirect();
loadAndDisplayRentals();


function loadAndDisplayRentals() {

    loadRentals().then(rentals => {
        displayRentals(rentals);
        returnMovie(rentals);
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
    let rentalId = 1;

    if (rentals.length > 0) {

        for (const rental of rentals) {

            html += '<li>' + 'Rental ' + rentalId + ' ====> User: ' + rental.userid + ' rented movie(s): ' + rental.movieid + '</li>';
            rentalId++;
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
        returnmoviebutton.style.display = "none";
    }

    let returnbackbutton = document.querySelector(".returnbackbutton");

    returnbackbutton.onclick = function () {

        document.querySelector(".returninput").value = "";
        document.querySelector(".returninput").style.display = "none";
        document.querySelector(".returnokbutton").style.display = "none";
        document.querySelector(".returnbackbutton").style.display = "none";
        returnmoviebutton.style.display = "initial";
    }

    $(".returnokbutton").click(function () {

        const input = parseInt($('.returninput').val());

        if (input && isNaN(input) === false) {

            // do a callback for getting the movieid of the rental that is going to be deleted
            axios
                .get("/api/rental/" + input)
                .then(response => response.data)
                .then(rental => {
                    console.log("selected rental", rental);
                    const movieid = rental.movieid;

                    // this callback gets the movie by the movieid
                    axios
                        .get("/api/movies/" + movieid)
                        .then(response => response.data)
                        .then(movie => {

                            // this callback sets the movie as available again
                            axios
                                .put("/api/movies/available/" + movieid, movie)
                                .then(response => response.data)
                                .then(returnedMovie => {
                                    console.log("returned movie", returnedMovie);
                                })
                                .catch(error => console.error("Error returning movie!", error));

                        })
                        .catch(error => {
                            console.log("AJAX request finished with an error :(");
                            console.error(error);
                        });

                    // and finally this one deletes the rental
                    axios
                        .delete("/api/rental/" + input)
                        .then(response => response.data)
                        .then(deletedRental => {
                            loadAndDisplayRentals();
                            console.log("deleted rental");
                        })
                        .catch(error => console.error("Error deleting rental!", error))

                })
                .catch(error => console.error("Error showing rental!", error));


            document.querySelector(".returninput").value = "";
            document.querySelector(".returninput").style.display = "none";
            document.querySelector(".returnokbutton").style.display = "none";
            document.querySelector(".returnbackbutton").style.display = "none";

        } else {

            document.querySelector(".returninput").value = "";
        }
    });
}
