/**
 * 
 */
"use strict";
//Validate first input, regex, red, yellow, green
function validate1() {
	//more dynamic idk
	validate2()

	//yellow
	if (pattern1.test(pass1.value)) {

		//green
		if (pattern2.test(pass1.value)) {
			pass1.classList.remove("mediumPassword");
			pass1.setCustomValidity("");
			pass1.classList.value = "";
		} else {
			pass1.setCustomValidity("");
			pass1.classList.add("mediumPassword");
		}

	} else {
		//red
		pass1.classList.remove("mediumPassword");
		pass1.setCustomValidity("Invalid field.");
	}
}

//Validate secund input if equal first input
function validate2() {
	//password 1 is equal to password 2 -> green
	if (pass1.value === pass2.value) {
		pass2.setCustomValidity("");

	} else {
		//red
		pass2.setCustomValidity("Invalid field.");
	}
}

//Capital, lower, number, atleast 8 characters
let pattern1 = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})");
//Capital, lower, number, special character, at least 8 characters 
let pattern2 = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");


const pass1 = document.getElementById('pass1');
const pass2 = document.getElementById('pass2');

//default field value
pass1.setCustomValidity("Invalid field.");
pass2.setCustomValidity("Invalid field.");


pass1.addEventListener("input", validate1);
pass2.addEventListener("input", validate2);

validate1();

