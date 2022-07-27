google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawRentBuyChart);

function drawRentBuyChart()
{
    var data_array = populateChartData();
    var data = google.visualization.arrayToDataTable(data_array);
    var options = {
        title: 'Rent vs Buy Break-even Chart',
        curveType: 'function',
        legend: { position: 'bottom' },
        'width':800,
        'height':500,
      };
    var chart = new google.charts.Line(document.getElementById('rentBuyChart'));
    chart.draw(data, google.charts.Line.convertOptions(options));
}

function populateChartData()
{
    //Get rent and buy values from input
    const home_price = document.getElementById('home_cost').value;
    const rent_price = document.getElementById('rent_cost').value;

    //Variable that holds a 2D Array that populates data for chart
    var populated_data = [];
    const chartElements = ['Year', 'Home Price', 'Rent Price'];
    populated_data.push(chartElements);

    //add starting values to chart data
    const starting_values = [1, home_price, rent_price];
    populated_data.push(starting_values);


    //Values needed to populate data when rent is less than home price
    var year_rent = rent_price * 12;
    var total_rent = year_rent;
    var breakeven_year = 1;
    
    //while loop to add data for a given year
    while (total_rent < home_price)
    {
        total_rent = total_rent + year_rent;
        breakeven_year = breakeven_year + 1;
        const new_data = [breakeven_year, home_price, total_rent];
        populated_data.push(new_data);
    }
    return populated_data;
}



function calcBuyRent()
{
    const home_price = document.getElementById('home_cost').value;
    const rent_price = document.getElementById('rent_cost').value;
    const num_years_expected = document.getElementById('time').value;

    var year_rent = rent_price * 12;
    var total_rent = year_rent;
    var breakeven_year = 1;

    while (total_rent < home_price)
    {
        total_rent = total_rent + year_rent;
        breakeven_year = breakeven_year + 1;
    }
    
    const result_text = document.getElementById('calcResult');
    const advice_text = document.getElementById('calcAdvice');
    result_text.innerText = 'After ' + breakeven_year + ' years, you would have paid the same amount for rent and a home!';
    
    if (num_years_expected >= breakeven_year)
    {
        advice_text.innerText = 'Since you expect to stay at the property for ' + num_years_expected + ', then buying a home might be a better option.';
    }
    else
    {
        advice_text.innerText = 'Since you expect to stay at the property for less than ' + breakeven_year + ', then renting might be a better option.';
    }
}
