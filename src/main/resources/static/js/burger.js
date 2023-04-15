const menuBtn = document.querySelector('.header__menu-btn');
const menu = document.querySelector('.header__navigation');

menuBtn.addEventListener('click', function(){
    menu.classList.toggle('header__navigation--active');
    this.classList.toggle('header__menu-btn--active');
})