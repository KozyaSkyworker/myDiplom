const input = document.querySelector('.top__input');
const clear = document.querySelector('.top__clear');

input.addEventListener("input", (e) => {
    input.setAttribute("value", e.target.value);
    clear.classList.remove('none')
});

clear.onclick = () => {
    input.value = ''
    input.setAttribute("value", '');
    clear.classList.add('none')
}