// This is a subset of the states.
// Use this to actually run the game
// (assume this is the full set of states.
// This will make it easier to test.
var states = ["Idaho", "South Dakota", "Hawaii", "Alaska", "Alabama", "New York"];

// These are all the states. It maps the state name to the number which you'll
// want to use in your API call.
var abvMap = {
    "Alabama": "01",
    "Alaska": "02",
    "Arizona": "04",
    "Arkansas": "05",
    "California": "06",
    "Colorado": "08",
    "Connecticut": "09",
    "Delaware": "10",
    "District Of Columbia": "11",
    "Florida": "12",
    "Georgia": "13",
    "Hawaii": "15",
    "Idaho": "16",
    "Illinois": "17",
    "Indiana": "18",
    "Iowa": "19",
    "Kansas": "20",
    "Kentucky": "21",
    "Louisiana": "22",
    "Maine": "23",
    "Maryland": "24",
    "Massachusetts": "25",
    "Michigan": "26",
    "Minnesota": "27",
    "Mississippi": "28",
    "Missouri": "29",
    "Montana": "30",
    "Nebraska": "31",
    "Nevada": "32",
    "New Hampshire": "33",
    "New Jersey": "34",
    "New Mexico": "35",
    "New York": "36",
    "North Carolina": "37",
    "North Dakota": "38",
    "Ohio": "39",
    "Oklahoma": "40",
    "Oregon": "41",
    "Pennsylvania": "42",
    "Rhode Island": "44",
    "South Carolina": "45",
    "South Dakota": "46",
    "Tennessee": "47",
    "Texas": "48",
    "Utah": "49",
    "Vermont": "50",
    "Virginia": "51",
    "Washington": "53",
    "West Virginia": "54",
    "Wisconsin": "55",
    "Wyoming": "56",
}

var keys = Object.keys(abvMap);
var seconds = 200;
var score = 0;
var found = [];
var missed = [];
var started = false;
var won = false;
var x;

changeInputState(started);

document.getElementById("score").innerHTML = "Score: " + score + "/" + keys.length;
document.getElementById("timer").innerHTML = "Time: " + seconds;

$("#input").keyup(function() {
    var state = $("#input").val().toLowerCase();
    for (var i = 0; i < keys.length; i++) {
        if (keys[i].toLowerCase().localeCompare(state) == 0 && !found.includes(state)) {
            score++;
            found.push(state);
            $("#found").append("<div id=\"state\">" + formatString(state) + "</div>");
            document.getElementById("score").innerHTML = "Score: " + score + "/" + keys.length;
            $("#input").val("");
        }
    }
});

$("#start").on("click", function() {
    document.getElementById("start").disabled = true;
    countdown = seconds;
    started = true;
    changeInputState(started);
    clearInterval(x);
    $("#input").focus();

    x = setInterval(function() {
        document.getElementById("timer").innerHTML = "Time: " + countdown;
        countdown--;

        if (win()) {
            started = false;
            changeInputState(started);
            clearInterval(x);
            document.getElementById("result").innerHTML = "<h2>You Win!</h2>";

        }

        if (countdown < 0 && !win()) {
            started = false;
            changeInputState(started);
            clearInterval(x);
            document.getElementById("result").innerHTML = "<h2>You Lose!</h2>";
            getMissed();
            $("#missed").append("<h3>You missed</h3></br>")
            for (var i in missed) {
                $("#missed").append("<div id=\"state\" style=\"color:red;\">" + formatString(missed[i]) + "</div>");
            }
        }

    }, 1000);

});

$("#reset").on("click", function() {
    found = [];
    missed = [];
    score = 0;
    countdown = seconds;
    document.getElementById("timer").innerHTML = "Time: " + countdown;
    document.getElementById("score").innerHTML = "Score: " + score + "/" + keys.length;
    document.getElementById("result").innerHTML = "";
    document.getElementById("missed").innerHTML = "";
    document.getElementById("spanish").innerHTML = "Spanish Speakers: ";



    started = false;
    clearInterval(x);
    document.getElementById("start").disabled = false;
    changeInputState(started);
    document.getElementById("found").innerHTML = "";
});

$(document).on('mouseenter', '#state', function() {
    var state = $(this).text();
    var num = abvMap[state];
    $.get("https://api.census.gov/data/2013/language?get=EST,LANLABEL,NAME&for=state:" + num + "&LAN=625", function(data) {
       spanishSpeakers = data[1][0];
       spanishSpeakers = numberWithCommas(spanishSpeakers);
       $("#spanish").append("<span id=\"num\">" + spanishSpeakers + "</span>");
    })
}).on('mouseleave', '#state', function() {
    //$("#spanish").find("span:last").remove();
    document.getElementById("spanish").innerHTML = "Spanish Speakers: ";
})

function formatString(str) {
    return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
 }

 function changeInputState(started) {
    if (started == true) {
        document.getElementById("input").disabled = false;
    } else {
        document.getElementById("input").disabled = true;
    }
 }

 function win() {
     if (found.length == keys.length) {
        return true;
     } else {
         return false;
     }
 }

 function getMissed() {
     for (var i in keys) {
         if (!found.includes(keys[i].toLowerCase())) {
             missed.push(formatString(keys[i]));
         }
     }
 }

 function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }

 

/*
 * The majority of this project is done in JavaScript.
 *
 * 1. Start the timer when the click button is hit. Also, you must worry about
 *    how it will decrement (hint: setInterval).
 * 2. Check the input text with the group of states that has not already been
 *    entered. Note that this should only work if the game is currently in
 * 3. Realize when the user has entered all of the states, and let him/her know
 *    that he/she has won (also must handle the lose scenario). The timer must
 *    be stopped as well.
 *
 * There may be other tasks that must be completed, and everyone's implementation
 * will be different. Make sure you Google! We urge you to post in Piazza if
 * you are stuck.
 */
