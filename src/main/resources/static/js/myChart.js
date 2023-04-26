const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['year1', 'year2', 'year3', 'year4'],
      datasets: [{
        label: '# of Votes',
        data: [4,3,2,5],
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