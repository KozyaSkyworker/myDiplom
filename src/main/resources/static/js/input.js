const input = document.querySelector('.control_input');
const clear = document.querySelector('.top__clear');

input.addEventListener("input", (e) => {
    input.setAttribute("value", e.target.value);
    clear.classList.remove('none')
    if(e.target.value == "")
        clear.classList.add('none')
});

clear.onclick = () => {
    input.value = ''
    input.setAttribute("value", '');
    clear.classList.add('none')
}