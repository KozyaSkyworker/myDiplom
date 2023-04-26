let firstDate = termsList[0].first_date;

const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'line',
    data: {
      labels: [firstDate, 'year2'],
      datasets: [{
        label: '# of Votes',
        data: [1, 5],
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

