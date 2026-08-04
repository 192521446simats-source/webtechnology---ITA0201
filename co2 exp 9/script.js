let cart = [];

function addProduct(){

let product = document.getElementById("product");

let name = product.options[product.selectedIndex].text.split(" - ")[0];

let price = Number(product.value);

let qty = Number(document.getElementById("qty").value);

let total = price * qty;

let item = {
name:name,
price:price,
qty:qty,
total:total
};

cart.push(item);

displayCart();

}

function displayCart(){

let body = document.getElementById("cartBody");

body.innerHTML="";

let subtotal = 0;

for(let i=0;i<cart.length;i++){

subtotal += cart[i].total;

body.innerHTML += `
<tr>
<td>${cart[i].name}</td>
<td>₹${cart[i].price}</td>
<td>${cart[i].qty}</td>
<td>₹${cart[i].total}</td>
</tr>
`;

}

let discount = subtotal * 0.10;

let afterDiscount = subtotal - discount;

let tax = afterDiscount * 0.05;

let grandTotal = afterDiscount + tax;

document.getElementById("subtotal").innerHTML = subtotal.toFixed(2);

document.getElementById("discount").innerHTML = discount.toFixed(2);

document.getElementById("tax").innerHTML = tax.toFixed(2);

document.getElementById("grandTotal").innerHTML = grandTotal.toFixed(2);

}