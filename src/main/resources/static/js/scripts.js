function validate() {
	var pw = document.getElementsByName("password")[0].value;
	var pw2 = document.getElementsByName("re-password")[0].value;
	if(pw != pw2) {
		var ps = document.getElementsByClassName("error");
		ps[0].innerHTML = "The confirm password does not match the password";
	} else {
		var ps = document.getElementsByClassName("error");
		ps[0].innerHTML = "";
	}
}