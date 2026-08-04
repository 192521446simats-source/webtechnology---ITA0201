function updateClock(){

    let now = new Date();

    let hours = String(now.getHours()).padStart(2,'0');
    let minutes = String(now.getMinutes()).padStart(2,'0');
    let seconds = String(now.getSeconds()).padStart(2,'0');

    document.getElementById("clock").innerHTML =
    hours + ":" + minutes + ":" + seconds;
}

function updateCountdown(){

    // Set your exam date here
    let examDate = new Date("December 15, 2026 09:00:00");

    let now = new Date();

    let difference = examDate - now;

    if(difference <= 0){

        document.getElementById("countdown").innerHTML =
        "Exam Started!";

        return;
    }

    let days = Math.floor(difference / (1000*60*60*24));

    let hours = Math.floor((difference % (1000*60*60*24)) / (1000*60*60));

    let minutes = Math.floor((difference % (1000*60*60)) / (1000*60));

    let seconds = Math.floor((difference % (1000*60)) / 1000);

    document.getElementById("countdown").innerHTML =
    days + " Days " +
    hours + " Hours " +
    minutes + " Minutes " +
    seconds + " Seconds";

}

updateClock();
updateCountdown();

setInterval(function(){

    updateClock();
    updateCountdown();

},1000);