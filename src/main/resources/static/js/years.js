const firstParagraph = document.getElementById("first");
const secondParagraph = document.getElementById("second");

let regex = /\b\d{4}\b/g;

firstParagraph.innerHTML = firstParagraph.innerText.replace(regex, `<span class=main__year>$&</span>`);
secondParagraph.innerHTML = secondParagraph.innerText.replace(regex, `<span class=main__year>$&</span>`);

