
const apiMoviesUrl = '/api/movies/';

setupMovieForm();
loadAndDisplayMovies();

function loadAndDisplayMovies() {

    loadMovies().then(movies => {
        displayMovies(movies);
        deleteMovie();
        editAndUpdateMovie();
        exitEditMode();
        viewRentals();
    });
}

function setupMovieForm() {

    $('form').submit(event => {

        console.log("Form submitted");
        event.preventDefault();

        const movie = {
            title: $('#title').val(),
            director: $('#director').val(),
            year: parseInt($('#year').val()),
            cover: $('#cover').val()
        };

        let title = document.getElementById("title").value;
        let director = document.getElementById("director").value;
        let year = document.getElementById("year").value;
        let cover = document.getElementById("cover").value;

        if ($('#title').val() && $('#director').val() && $('#year').val() && $('#cover')
            && isNaN(director) === true && isNaN(cover) === true && isNaN(year) === false) {

            axios
                .post(apiMoviesUrl, movie)
                .then(response => response.data)
                .then(addedMovie => {
                    console.log("Added movie", addedMovie);
                    loadAndDisplayMovies(); // to refresh list
                })
                .catch(error => console.error("Error adding movie!", error));

           clearInputs();
        }
        clearInputs();
    });
}

function loadMovies() {

    return axios.get(apiMoviesUrl)
        .then(response => response.data)
        .catch(error => {
            console.log("AJAX request finished with an error :(");
            console.error(error);
        });
}

/** Displays movies on the HTML */
function displayMovies(movies) {

    let html = "<ul>";

    for (const movie of movies) {

        html += '<li><a href=http://localhost:8080/movie.html?movieid=' + movie.id + '>'
            + movie.title + '</a><button class=deletebutton value=' + movie.id +
            '>Delete</button><button class=editbutton value=' + movie.id +
            '>Edit</button></li>';
    }

    html += "</ul>";

    const resultDiv = document.getElementById("result");
    resultDiv.innerHTML = html;
}

/** Deletes a movie when you click on its delete button only if the movie is available for rent */
function deleteMovie() {

    $('.deletebutton').click(function() {

        let movieid = parseInt($(this).val());

        axios.get(apiMoviesUrl + movieid)
            .then(response => response.data)
            .then(movie => {

                if (movie.available) {

                    axios
                        .delete(apiMoviesUrl + movieid)
                        .then(response => response.data)
                        .then(deletedMovie => {
                            console.log("deleted movie");
                            loadAndDisplayMovies(); // to refresh list
                        })
                        .catch(error => console.error("Error deleting movie!", error));
                }
            })
            .catch(error => {
                console.log("AJAX request finished with an error :(");
                console.error(error);
            });
    });
}

/** Edits a movie info and updates it when clicking the update button only if the movie is not rented */
function editAndUpdateMovie() {

    $('.editbutton').click(function() {

        let movieid = $(this).val();

        axios.get(apiMoviesUrl + movieid)
            .then(response => response.data)
            .then(movie => {

                if (movie.available) {

                    let updateButton = document.querySelector(".updatebutton");
                    updateButton.style.display = "initial";

                    document.querySelector(".backbutton").style.display = "initial";

                    let addButton = document.querySelector("#addbutton");
                    addButton.style.display = "none";

                }

                $('.updatebutton').click(function() {

                    const movie = {
                        title: $('#title').val(),
                        director: $('#director').val(),
                        year: parseInt($('#year').val()),
                        cover: $('#cover').val()
                    };

                    let director = document.getElementById("director").value;
                    let year = document.getElementById("year").value;
                    let cover = document.getElementById("cover").value;

                    if ($('#title').val() && $('#director').val() && $('#year').val() && $('#cover')
                        && isNaN(director) === true && isNaN(cover) === true && isNaN(year) === false) {

                        axios
                            .put(apiMoviesUrl + movieid, movie)
                            .then(response => response.data)
                            .then(editedMovie => {
                                console.log("edited movie", editedMovie);
                                loadAndDisplayMovies(); // to refresh list
                            })
                            .catch(error => console.error("Error editing movie!", error));

                        document.getElementById("addbutton").style.display = "initial";
                        document.querySelector(".updatebutton").style.display = "none";
                        document.querySelector(".backbutton").style.display = "none";
                    }
                    clearInputs();
                });

            })
            .catch(error => {
                console.log("AJAX request finished with an error :(");
                console.error(error);
            });
    });
}

/** Exits edit mode and clears the inputs */
function exitEditMode() {

    $('.backbutton').click(function() {

        clearInputs();
        document.getElementById("addbutton").style.display = "initial";
        document.querySelector(".updatebutton").style.display = "none";
        document.querySelector(".backbutton").style.display = "none";
    });
}

/** Clears the inputs */
function clearInputs() {

    document.getElementById("title").value = "";
    document.getElementById("director").value = "";
    document.getElementById("year").value = "";
    document.getElementById("cover").value = "";
}

/** Redirects to rental page when clicking on button */
function viewRentals() {

   let rentalButton = document.getElementById("rental");

   rentalButton.onclick = function() {
       location.href = "http://localhost:8080/Rental.html";
   }
}
