const clearFunc = () => {
    const inputs = document.getElementsByClassName('form__input')
    console.log(inputs)
    for (let input of inputs) {
        input.value = '';
    }
}

console.log("test")

clearFunc();