google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawIncomeExpenseChart);

/** Fetches monthly income/expense data and uses it to create a chart. */
function drawIncomeExpenseChart() {
  fetch('/process-income-expense').then(response => response.json())
  .then((incomeExpenseData) => {
    // if CSV data has been submitted, create chart
    if (Object.keys(incomeExpenseData).length !== 0) {
      const data = new google.visualization.DataTable();
      // Display income, expense, and cash flow for each month
      data.addColumn('date', 'Month');
      data.addColumn('number', 'Income');
      data.addColumn('number', 'Expense');
      data.addColumn('number', 'Cash Flow');

      // For each month in income/expense data object
      Object.keys(incomeExpenseData).forEach((month) => {
        // Add row with income, expense, and cashflow
        const income = incomeExpenseData[month].income;
        const expense = incomeExpenseData[month].expense;
        const cashFlow = income - expense;
        data.addRow([new Date(month), income, expense, cashFlow]);
      });

      const options = {
        'title': 'Income vs. Expense by Month',
        'width':800,
        'height':500,
        'hAxis':{
          'format': 'MMM y',
          'gridlines': {'color': 'none'}
        },
        'vAxis': {'format': 'currency'},
        'titleTextStyle': {'fontSize': 16}
      };

      const chart = new google.visualization.ColumnChart(
          document.getElementById('income-expense-chart'));
      chart.draw(data, options);
    }
  });
}

//use this function to translate contents of the page
function translatePage() {
    const language = document.getElementById('language').value; //read what language user wants to translate page to

    const textContainer = document.getElementById('translate');

    const params = new URLSearchParams();
 
    params.append('text', textContainer.innerHTML);
    params.append('language', language);

    fetch('/translate', {
          method: 'POST',
          body: params
        }).then(response => response.text())
        .then((translatedMessage) => {
            textContainer.innerHTML = translatedMessage;
        });

}