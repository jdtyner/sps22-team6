package com.google.sps.servlets;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/translate")
public class TranslationServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String language = request.getParameter("language");

      String months = request.getParameter("months");
      System.out.println(months);
      /*
      String home = request.getParameter("homeTranslate");
      String utility = request.getParameter("utilityTranslate");
      String transportation = request.getParameter("transportTranslate");
      String food = request.getParameter("foodTranslate");
      String debt = request.getParameter("debtTranslate");
      String medical = request.getParameter("medicalTranslate");
      String other = request.getParameter("otherTranslate");
      */
      
      //do translation on each String
      Translate translateMonths = TranslateOptions.getDefaultInstance().getService();
      Translation translationMonths = translateMonths.translate(months, Translate.TranslateOption.targetLanguage(language));
      String translatedMonths = translationMonths.getTranslatedText();

      // Output the translation.
      //response.setContentType("text/html; charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().println(translatedMonths);
  }
}

