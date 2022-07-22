package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.MonthData;
import com.google.sps.data.Transaction;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/** Handle requests sent to /process-income-expense URL */
@WebServlet("/process-income-expense")
/** Handle multipart form data to read file input */
@MultipartConfig
public class IncomeExpenseServlet extends HttpServlet {

  private Map<YearMonth, MonthData> IncomeExpenseChart = new HashMap<>();
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Respond to GET request by returning JSON of income/expense data
    response.setContentType("application/json");
    Gson gson = new Gson();
    String json = gson.toJson(IncomeExpenseChart);
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    // Get contents of uploaded file
    Part filePart = request.getPart("file");
    InputStream fileContent = filePart.getInputStream();

    // Load file contents into CSVReader
    CSVReader reader = new CSVReader(new InputStreamReader(fileContent));

    // For each line in CSV, add Transaction object to array
    ArrayList<Transaction> transactions = new ArrayList<>();
    String nextLine[];
    while ((nextLine = reader.readNext()) != null) {
      transactions.add(new Transaction(
          LocalDate.parse(nextLine[0]), nextLine[1], Float.parseFloat(nextLine[2])));
    }
    // For each transaction in array, get YearMonth and add to IncomeExpenseChart map
    ListIterator<Transaction> itr = transactions.listIterator();
    while (itr.hasNext()) {
      Transaction transaction = itr.next();
      YearMonth month = YearMonth.from(transaction.getDate());
      // Add new YearMonth key if not already in map
      IncomeExpenseChart.putIfAbsent(month, new MonthData());
      // Add to income if transaction has positive amount, expense if negative amount
      if (transaction.getAmount() > 0) {
        IncomeExpenseChart.get(month).addIncome(transaction.getAmount());
      } else {
        IncomeExpenseChart.get(month).addExpense(-1 * transaction.getAmount());
      }
    }
    // Print monthly data to console for testing
    /*
    for (YearMonth month : IncomeExpenseChart.keySet()) {
      System.out.println(month + ": " + IncomeExpenseChart.get(month));
    }
    */
    
    // Redirect to income vs. expense page
    response.sendRedirect("income-expense.html"); 
  }
}
