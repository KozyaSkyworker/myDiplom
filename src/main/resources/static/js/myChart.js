let firstDate = termsList[0].first_date;

let dateArray = []

termsList.forEach(el =>
    dateArray.push(el.first_date)
);

let res = dateArray.reduce(function(acc, el) {
    acc[el] = (acc[el] || 0) + 1;
    return acc;
}, {});



const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: [res],
      datasets: [{
        label: 'Термины информатики за год',
        data: [res],
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

