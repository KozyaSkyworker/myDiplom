const clearFunc = () => {
    const inputs = document.getElementsByClassName('form__input')
    for (let input of inputs) {
        input.value = '';
    }
}

clearFunc();