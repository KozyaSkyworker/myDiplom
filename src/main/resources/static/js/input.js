const input = document.querySelector('.search__form-input');
const clear = document.querySelector('.search__form-clear');
const btn = document.querySelector('.search__form-btn');

input.addEventListener("input", (e) => {
    input.setAttribute("value", e.target.value);
    btn.removeAttribute("disabled");
    clear.classList.remove('none')
    if(e.target.value == "")
        clear.classList.add('none')
});

clear.onclick = () => {
    input.value = ''
    input.setAttribute("value", '');
    clear.classList.add('none')
}
