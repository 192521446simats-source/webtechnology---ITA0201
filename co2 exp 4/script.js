let students = [];

function addStudent(){

    let name = document.getElementById("name").value;

    let marks = [
        Number(document.getElementById("m1").value),
        Number(document.getElementById("m2").value),
        Number(document.getElementById("m3").value),
        Number(document.getElementById("m4").value),
        Number(document.getElementById("m5").value)
    ];

    let total = 0;

    for(let i=0;i<marks.length;i++){
        total += marks[i];
    }

    let average = total/marks.length;

    let highest = Math.max(...marks);

    let lowest = Math.min(...marks);

    let grade = "";

    if(average>=90)
        grade="A+";
    else if(average>=80)
        grade="A";
    else if(average>=70)
        grade="B";
    else if(average>=60)
        grade="C";
    else if(average>=50)
        grade="D";
    else
        grade="F";

    let result="Pass";

    for(let i=0;i<marks.length;i++){
        if(marks[i]<35){
            result="Fail";
            break;
        }
    }

    let student={
        name:name,
        total:total,
        average:average.toFixed(2),
        highest:highest,
        lowest:lowest,
        grade:grade,
        result:result
    };

    students.push(student);

    displayStudents();

    document.getElementById("name").value="";
    document.getElementById("m1").value="";
    document.getElementById("m2").value="";
    document.getElementById("m3").value="";
    document.getElementById("m4").value="";
    document.getElementById("m5").value="";
}

function displayStudents(){

    let table=document.querySelector("#resultTable tbody");

    table.innerHTML="";

    for(let i=0;i<students.length;i++){

        let s=students[i];

        table.innerHTML += `
        <tr>
        <td>${s.name}</td>
        <td>${s.total}</td>
        <td>${s.average}</td>
        <td>${s.highest}</td>
        <td>${s.lowest}</td>
        <td>${s.grade}</td>
        <td class="${s.result=='Pass'?'pass':'fail'}">${s.result}</td>
        </tr>
        `;
    }
}