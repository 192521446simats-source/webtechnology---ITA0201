const button = document.getElementById("btn");

button.addEventListener("click", calculate);

function calculate(){

    debugger;

    try{

        let num1 = Number(document.getElementById("num1").value);
        let num2 = Number(document.getElementById("num2").value);

        console.log("First Number:", num1);
        console.log("Second Number:", num2);

        if(isNaN(num1) || isNaN(num2)){
            throw "Please enter valid numbers.";
        }

        let sum = num1 + num2;

        console.log("Sum =", sum);

        document.getElementById("result").innerHTML =
        "Result : " + sum;

    }
    catch(error){

        console.log("Error :", error);

        alert(error);

    }

}