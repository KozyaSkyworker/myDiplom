const TABS = document.querySelectorAll('.main__btn');

const termForm = document.querySelector('.term-form');
const authorForm = document.querySelector('.author-form');
const authortermForm = document.querySelector('.authorterm-form');

TABS.forEach((tab) => {
  tab.addEventListener('click', () => {
    if (tab.id === 'termBtn') {
      termForm.classList.remove('none')
      authorForm.classList.add('none')
      authortermForm.classList.add('none')
    } else if (tab.id === 'authorBtn') {
      termForm.classList.add('none')
      authorForm.classList.remove('none')
      authortermForm.classList.add('none')
    } else {
      termForm.classList.add('none')
      authorForm.classList.add('none')
      authortermForm.classList.remove('none')
    }
  });
});

document.querySelector('.main__btn').click();
