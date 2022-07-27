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

function calculateEmergencyFund() {
    const months = document.getElementById('months').value;
    const home = document.getElementById('home').value;
    const utility = document.getElementById('utility').value;
    const transportation = document.getElementById('transportation').value;
    const food = document.getElementById('food').value;
    const debt = document.getElementById('debt').value;
    const medical = document.getElementById('medical').value;
    const other = document.getElementById('other').value;

    const resultContainer = document.getElementById('result-container');

    const params = new URLSearchParams();
    params.append('months', months);
    params.append('home', home);
    params.append('utility', utility);
    params.append('transportation', transportation);
    params.append('food', food);
    params.append('debt', debt);
    params.append('medical', medical);
    params.append('other', other);

    fetch('/calculate-emergency-fund', {
        method: 'POST',
        body: params
        }).then(response => response.text())
        .then((result) => {
            resultContainer.innerText = result; 
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
