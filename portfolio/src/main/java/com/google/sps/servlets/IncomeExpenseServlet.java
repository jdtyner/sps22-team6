package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.data.MonthData;
import com.google.sps.data.Transaction;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

  private Map<YearMonth, MonthData> IncomeExpenseGraph = new HashMap<>();
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    /*
    response.setContentType("application/json");
    Gson gson = new Gson();
    String json = gson.toJson(colorVotes);
    response.getWriter().println(json);
    */
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
    // For each transaction in array, get YearMonth and add to IncomeExpenseGraph map
    ListIterator<Transaction> itr = transactions.listIterator();
    while (itr.hasNext()) {
      Transaction transaction = itr.next();
      YearMonth month = YearMonth.from(transaction.getDate());
      // Add new YearMonth key if not already in map
      IncomeExpenseGraph.putIfAbsent(month, new MonthData());
      // Add to income if transaction has positive amount, expense if negative amount
      if (transaction.getAmount() > 0) {
        IncomeExpenseGraph.get(month).addIncome(transaction.getAmount());
      } else {
        IncomeExpenseGraph.get(month).addExpense(-1 * transaction.getAmount());
      }
    }

    for (YearMonth month : IncomeExpenseGraph.keySet()) {
      System.out.println(month + ": " + IncomeExpenseGraph.get(month));
    }

    /*
    String color = request.getParameter("color");
    int currentVotes = colorVotes.containsKey(color) ? colorVotes.get(color) : 0;
    colorVotes.put(color, currentVotes + 1);

    response.sendRedirect("income-expense.html");
    */
  }
}
