const BTN = document.querySelector('.form__btn');
const FI = document.getElementById('nameInput');

BTN.onclick = (e) => {
    if (FI.value === ''){
        e.preventDefault();
        console.log("empty");
    }
    else{
       console.log("asdasdadadas");
    }
}