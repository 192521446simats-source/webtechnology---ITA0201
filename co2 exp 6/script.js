let tasks = [];

const input = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const taskList = document.getElementById("taskList");

addBtn.addEventListener("click", addTask);

function addTask(){

    let text = input.value.trim();

    if(text==""){
        alert("Enter a task");
        return;
    }

    let task = {
        name:text
    };

    tasks.push(task);

    input.value="";

    displayTasks();
}

function displayTasks(){

    taskList.innerHTML="";

    tasks.forEach(function(task,index){

        let li=document.createElement("li");

        li.innerHTML=`
        <span class="task">${task.name}</span>

        <button class="edit" onclick="editTask(${index})">Edit</button>

        <button class="delete" onclick="deleteTask(${index})">Delete</button>
        `;

        taskList.appendChild(li);

    });

}

function editTask(index){

    let newTask=prompt("Edit Task",tasks[index].name);

    if(newTask!=null && newTask.trim()!=""){
        tasks[index].name=newTask;
        displayTasks();
    }

}

function deleteTask(index){

    tasks.splice(index,1);

    displayTasks();

}