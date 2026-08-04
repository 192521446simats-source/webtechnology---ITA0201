document.getElementById("studentForm")
.addEventListener("submit",function(event){


event.preventDefault();



let name=document.getElementById("name").value.trim();

let email=document.getElementById("email").value.trim();

let phone=document.getElementById("phone").value.trim();

let password=document.getElementById("password").value;

let department=document.getElementById("department").value;



let valid=true;



// Regular Expressions

let namePattern=/^[A-Za-z ]+$/;

let emailPattern=/^[^\s@]+@[^\s@]+\.[^\s@]+$/;

let phonePattern=/^[0-9]{10}$/;



// Name validation

if(!namePattern.test(name)){


document.getElementById("nameError").innerHTML=
"Enter valid name";


valid=false;


}

else{

document.getElementById("nameError").innerHTML="";

}



// Email validation

if(!emailPattern.test(email)){


document.getElementById("emailError").innerHTML=
"Enter valid email";


valid=false;


}

else{

document.getElementById("emailError").innerHTML="";

}




// Phone validation

if(!phonePattern.test(phone)){


document.getElementById("phoneError").innerHTML=
"Enter 10 digit phone number";


valid=false;


}

else{

document.getElementById("phoneError").innerHTML="";

}





// Department validation

if(department==""){


document.getElementById("deptError").innerHTML=
"Select department";


valid=false;


}

else{

document.getElementById("deptError").innerHTML="";

}





// Password validation

if(password.length<6){


document.getElementById("passError").innerHTML=
"Password must contain 6 characters";


valid=false;


}

else{

document.getElementById("passError").innerHTML="";

}




if(valid){


document.getElementById("success").innerHTML=
"✅ Registration Successful!";


document.getElementById("studentForm").reset();


}



});