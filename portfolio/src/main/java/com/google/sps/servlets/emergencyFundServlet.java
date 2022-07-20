package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate-emergency-fund")
public class emergencyFundServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String numMonths = request.getParameter("months");
    String home = request.getParameter("home");
    String utility = request.getParameter("utility");
    String transportation = request.getParameter("transportation");
    String food = request.getParameter("food");
    String debt = request.getParameter("debt");
    String medical = request.getParameter("medical");
    String other = request.getParameter("other");

    //convert the values to int for calculations
    int conMonths = Integer.parseInt(numMonths);
    int conHome = Integer.parseInt(home);
    int conUtility = Integer.parseInt(utility);
    int conTransport = Integer.parseInt(transportation);
    int conFood = Integer.parseInt(food);
    int conDebt = Integer.parseInt(debt);
    int conMedical = Integer.parseInt(medical);
    int conOther = Integer.parseInt(other);

    //calculate how much user needs to save for an emergency fund
    int save = conMonths * (conHome + conUtility + conTransport + conFood + conDebt + conMedical + conOther);

    //output how much user needs to save over their span time
    response.getWriter().println("You need to save: $" + save + " in order to have an emergency fund for " + conMonths + " months. Good luck!");
  }
}