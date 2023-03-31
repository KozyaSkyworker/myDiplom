const input = document.querySelector('.control_input');

input.addEventListener("input", (e) => {
   input.setAttribute("value", e.target.value);

});