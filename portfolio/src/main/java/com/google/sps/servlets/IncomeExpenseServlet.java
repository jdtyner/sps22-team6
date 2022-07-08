package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/process-income-expense")
public class IncomeExpenseServlet extends HttpServlet {

  //private Map<String, Integer> colorVotes = new HashMap<>();

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
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    /*
    String color = request.getParameter("color");
    int currentVotes = colorVotes.containsKey(color) ? colorVotes.get(color) : 0;
    colorVotes.put(color, currentVotes + 1);

    response.sendRedirect("income-expense.html");
    */
  }
}
