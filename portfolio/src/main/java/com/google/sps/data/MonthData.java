package com.google.sps.data;

public class MonthData {
  private float income, expense;

  public void addIncome(float income) {
    this.income += income;
  }

  public void addExpense(float expense) {
    this.expense += expense;
  }

  @Override
  public String toString() {
    return "Income: " + Float.toString(income) + " Expense: " + Float.toString(expense)
        + " Cash Flow: " + Float.toString(income - expense);
  }
}
