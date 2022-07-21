// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

//use this function to translate contents of the page
function translatePage() {
    const language = document.getElementById("langauge"); //read what language user wants to translate page to
    const months = document.getElementById("monthsTranslate");
    console.log(months);
    const params = new URLSearchParams();
    params.append('language', language);
    params.append('months', months);

    fetch('/translate', {
        method: 'POST',
        body: params
      }).then(response => response.text())

}