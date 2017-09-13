
const apiMoviesUrl = '/api/movies/';

setupMovieForm();
loadAndDisplayMovies();

function loadAndDisplayMovies() {

    loadMovies().then(movies => {
        displayMovies(movies);
        deleteMovie();
        editAndUpdateMovie();
        resetForm();
    });
}

function setupMovieForm() {

    $('form').submit(event => {

        console.log("Form submitted");
        event.preventDefault();

        const movie = {
            title: $('#title').val(),
            director: $('#director').val(),
            year: parseInt($('#year').val())
        };

        if ($('#title').val() && $('#director').val() && $('#year').val()) {

            axios
                .post(apiMoviesUrl, movie)
                .then(response => response.data)
                .then(addedMovie => {
                    console.log("Added movie", addedMovie);
                    loadAndDisplayMovies(); // to refresh list
                })
                .catch(error => console.error("Error adding movie!", error));
        }

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

/** Deletes a movie when you click on its delete button */
function deleteMovie() {

    $('.deletebutton').click(function() {

        let movieid = parseInt($(this).val());

        axios
            .delete(apiMoviesUrl + movieid)
            .then(response => response.data)
            .then(deletedMovie => {
                console.log("deleted movie");
                loadAndDisplayMovies(); // to refresh list
            })
            .catch(error => console.error("Error deleting movie!", error));

    });
}

/** Edits a movie info and updates it when clicking the update button */
function editAndUpdateMovie() {

    $('.editbutton').click(function() {

        let movieid = $(this).val();

        let updateButton = document.querySelector(".updatebutton");
        updateButton.style.display = "initial";
        $('.updatebutton').attr('value', movieid);

        document.querySelector(".resetbutton").style.display = "initial";

        let addButton = document.querySelector("#addbutton");
        addButton.style.display = "none";

    });

    $('.updatebutton').click(function() {

        const movie = {
            title: $('#title').val(),
            director: $('#director').val(),
            year: parseInt($('#year').val())
        };

        const movieid = $(this).val();

        if ($('#title').val() && $('#director').val() && $('#year').val()) {

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
            document.querySelector(".resetbutton").style.display = "none";
        }

        document.getElementById("title").value = "";
        document.getElementById("director").value = "";
        document.getElementById("year").value = "";
        document.getElementById("cover").value = "";
    });

}

/** Resets the form inputs values and shows again the 'add movie' button
 *  Also goes back when you click the edit button and you dont want to edit */
function resetForm() {

    $('.resetbutton').click(function() {

        document.getElementById("title").value = "";
        document.getElementById("director").value = "";
        document.getElementById("year").value = "";
        document.getElementById("cover").value = "";
        document.getElementById("addbutton").style.display = "initial";
        document.querySelector(".updatebutton").style.display = "none";
        document.querySelector(".resetbutton").style.display = "none";
    });
}
