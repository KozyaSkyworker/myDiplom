let dateArray = []
// получаю все даты
termsList.forEach(el =>
    dateArray.push(el.first_date)
);
//считаю количество упоминаний за один год
let res = dateArray.reduce(function(acc, el) {
    acc[el] = (acc[el] || 0) + 1;
    return acc;
}, {});
//
let yearsArray = [];
let yearCount = [];
for (key in res){
    yearsArray.push(key);
    yearCount.push(res[key]);
}


const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: yearsArray,
      datasets: [{
        label: 'Термины информатики за год',
        data: yearCount,
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

