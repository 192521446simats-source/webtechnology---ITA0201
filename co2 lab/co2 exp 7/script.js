const quiz = [
{
question:"HTML stands for",
answer:1
},
{
question:"CSS is used for",
answer:1
},
{
question:"JavaScript is used for",
answer:1
}
];

const submitBtn = document.getElementById("submitBtn");

submitBtn.addEventListener("click", calculateScore);

function calculateScore(){

let score = 0;

for(let i=0;i<quiz.length;i++){

let selected = document.querySelector('input[name="q'+(i+1)+'"]:checked');

if(selected){

if(Number(selected.value)===quiz[i].answer){

score++;

}

}

}

let message="";

if(score===3){

message="Excellent!";

}
else if(score===2){

message="Good Job!";

}
else{

message="Keep Practicing!";

}

document.getElementById("result").innerHTML=
"Your Score : "+score+" / "+quiz.length+"<br>"+message;

}