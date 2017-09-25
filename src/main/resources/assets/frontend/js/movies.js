
const apiMoviesUrl = '/api/movies/';

setupMovieForm();
loadAndDisplayMovies();

function loadAndDisplayMovies() {

    loadMovies().then(movies => {
        console.log(movies);
        displayMovies(movies);
        setupDeleteMovieButton();
        setupEditAndUpdateMovieButton();
        setupExitEditModeButton();
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
            cover: $('#cover').val(),
            copies: parseInt($('#copies').val())
        };

        if (movie.title && movie.director && movie.year && movie.cover && movie.copies
            && isNaN(movie.director) && isNaN(movie.cover) && !isNaN(movie.year) && !isNaN(movie.copies)) {

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

        html +=
            '<li>' +
                '<a href=http://localhost:8080/movie.html?movieid=' + movie.id + '>' + movie.title + '</a>' +
                '<button class=deletebutton value=' + movie.id + '>Delete</button>' +
                '<button class=editbutton value=' + movie.id + '>Edit</button>' +
            '</li>';
    }

    html += "</ul>";

    const resultDiv = document.getElementById("result");
    resultDiv.innerHTML = html;
}

/** Deletes a movie when you click on its delete button only if the movie is available for rent */
function setupDeleteMovieButton() {

    $('.deletebutton').click(function() {

        let movieid = parseInt($(this).val());

        axios
            .delete(apiMoviesUrl + movieid)
            .then(response => response.data)
            .then(deletedMovie => {
                console.log(deletedMovie);
                loadAndDisplayMovies(); // to refresh list
            })
            .catch(error => console.error("Error deleting movie!", error));

    });
}

/** Edits a movie info and updates it when clicking the update button only if the movie is not rented */
function setupEditAndUpdateMovieButton() {

    $('.editbutton').click(function() {

        let id = parseInt($(this).val());

        let updateButton = document.querySelector(".updatebutton");
        updateButton.style.display = "initial";

        document.querySelector(".backbutton").style.display = "initial";

        let addButton = document.querySelector("#addbutton");
        addButton.style.display = "none";
        $('.updatebutton').attr('value', id);

    });

    $('.updatebutton').click(function() {

        const movie = {
            title: $('#title').val(),
            director: $('#director').val(),
            year: parseInt($('#year').val()),
            cover: $('#cover').val(),
            copies: parseInt($('#copies').val())
        };

        let movieId = parseInt($('.updatebutton').val());

        if (movie.title && movie.director && movie.year && movie.cover && movie.copies
            && isNaN(movie.director) && isNaN(movie.cover) && !isNaN(movie.year) && !isNaN(movie.copies)) {

            axios
                .put(apiMoviesUrl + movieId, movie)
                .then(response => response.data)
                .then(editedMovie => {
                    console.log(editedMovie);
                    loadAndDisplayMovies(); // to refresh list
                })
                .catch(error => console.error("Error editing movie!", error));

            document.getElementById("addbutton").style.display = "initial";
            document.querySelector(".updatebutton").style.display = "none";
            document.querySelector(".backbutton").style.display = "none";
        }
        clearInputs();
    });
}

/** Exits edit mode and clears the inputs */
function setupExitEditModeButton() {

    $('.backbutton').click(function() {

        clearInputs();
        document.getElementById("addbutton").style.display = "initial";
        document.querySelector(".updatebutton").style.display = "none";
        document.querySelector(".backbutton").style.display = "none";
    });
}

/** Clears the inputs */
function clearInputs() {

    $('form input').val('');
}

/** Redirects to rental page when clicking on button */
function viewRentals() {

   let rentalButton = document.getElementById("rental");

   rentalButton.onclick = function() {
       location.href = "http://localhost:8080/rental.html";
   }
}
