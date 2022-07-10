package com.google.sps.servlets;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
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
    try {
      Part filePart = request.getPart("file");
      InputStream fileContent = filePart.getInputStream();

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent))) {
        while (reader.ready()) {
          String line = reader.readLine();
          System.out.println(line);
        }
      }

    } catch (ServletException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    /*
    String color = request.getParameter("color");
    int currentVotes = colorVotes.containsKey(color) ? colorVotes.get(color) : 0;
    colorVotes.put(color, currentVotes + 1);

    response.sendRedirect("income-expense.html");
    */
  }
}
