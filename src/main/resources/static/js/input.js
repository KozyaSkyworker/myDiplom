const input = document.querySelector('.search__form-input');
const clear = document.querySelector('.search__form-clear');

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