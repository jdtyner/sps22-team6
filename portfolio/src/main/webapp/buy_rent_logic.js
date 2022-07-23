function calcBuyRent()
{
    const home_price = document.getElementById('home_cost').value;
    const rent_price = document.getElementById('rent_cost').value;
    const num_years = document.getElementById('time').value;

    var year_rent = rent_price * 12;
    var total_rent = year_rent;
    var year = 1;

    while (total_rent < home_price)
    {
        total_rent = total_rent + year_rent;
        year = year + 1;
    }
    
    const result_text = document.getElementById('calcResult');
    const advice_text = document.getElementById('calcAdvice');
    result_text.innerText = 'After ' + year + ' years, you would have paid the same amount for rent and a home!';
    
    if (num_years >= year)
    {
        advice_text.innerText = 'Since you expect to stay at the property for ' + num_years + ', then buying a home might be a good option.';
    }
    else
    {
        advice_text.innerText = 'Since you expect to stay at the property for less than ' + year + ', then renting might be a good option.';
    }
}
