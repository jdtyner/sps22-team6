package com.google.sps.data;
import java.time.LocalDate;
/**
 * Transaction object corresponds to line in transaction CSV file
 */
public class Transaction {
  private LocalDate date;
  private String description;
  private float amount;

  public Transaction(LocalDate date, String description, float amount) {
    this.date = date;
    this.description = description;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return date.toString() + " " + description + " " + Float.toString(amount);
  }

  public LocalDate getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public float getAmount() {
    return amount;
  }
}
