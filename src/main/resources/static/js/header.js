const header = document.querySelector('.header');

const currentScroll = () => window.pageYOffset || document.documentElement.scrollTop;
const isHide = () => header.classList.contains('hide');

let lastScroll = 0;

window.addEventListener('scroll', () => {
  if (currentScroll() > lastScroll && !isHide()) {
    header.classList.add('hide');
  } else if (currentScroll() < lastScroll && isHide()) {
    header.classList.remove('hide');
  }

  lastScroll = currentScroll();
});