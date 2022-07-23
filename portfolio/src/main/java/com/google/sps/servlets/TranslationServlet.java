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
import javax.servlet.ServletException;

@WebServlet("/translate")
public class TranslationServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String language = request.getParameter("language");
      String months = request.getParameter("months");
      String home = request.getParameter("home");
      String utility = request.getParameter("utility");
      String transportation = request.getParameter("transportation");
      String food = request.getParameter("food");
      String debt = request.getParameter("debt");
      String medical = request.getParameter("medical");
      String other = request.getParameter("other");
 
      
      //do translation on each String
      Translate translateMonths = TranslateOptions.getDefaultInstance().getService();
      Translation translationMonths = translateMonths.translate(months, Translate.TranslateOption.targetLanguage(language));
      String translatedMonths = translationMonths.getTranslatedText();

      Translate translateHome = TranslateOptions.getDefaultInstance().getService();
      Translation translationHome = translateHome.translate(home, Translate.TranslateOption.targetLanguage(language));
      String translatedHome = translationHome.getTranslatedText();

      Translate translateUtility = TranslateOptions.getDefaultInstance().getService();
      Translation translationUtility = translateUtility.translate(utility, Translate.TranslateOption.targetLanguage(language));
      String translatedUtility = translationUtility.getTranslatedText();

      Translate translateTransport = TranslateOptions.getDefaultInstance().getService();
      Translation translationTransport = translateTransport.translate(transportation, Translate.TranslateOption.targetLanguage(language));
      String translatedTransport = translationTransport.getTranslatedText();

      Translate translateFood = TranslateOptions.getDefaultInstance().getService();
      Translation translationFood = translateFood.translate(food, Translate.TranslateOption.targetLanguage(language));
      String translatedFood = translationFood.getTranslatedText();

      Translate translateDebt = TranslateOptions.getDefaultInstance().getService();
      Translation translationDebt = translateDebt.translate(debt, Translate.TranslateOption.targetLanguage(language));
      String translatedDebt = translationDebt.getTranslatedText();

      Translate translateMed = TranslateOptions.getDefaultInstance().getService();
      Translation translationMed= translateMed.translate(medical, Translate.TranslateOption.targetLanguage(language));
      String translatedMed = translationMed.getTranslatedText();

      Translate translateOther = TranslateOptions.getDefaultInstance().getService();
      Translation translationOther = translateOther.translate(other, Translate.TranslateOption.targetLanguage(language));
      String translatedOther = translationOther.getTranslatedText();

      System.out.println(translatedMonths);
      System.out.println(translatedHome);
      System.out.println(translatedUtility);
      System.out.println(translatedTransport);
      System.out.println(translatedFood);
      System.out.println(translatedDebt);
      System.out.println(translatedMed);
      System.out.println(translatedOther);


      // Output the translation.
      //response.setContentType("text/html; charset=UTF-8");
      //response.setCharacterEncoding("UTF-8");
      response.getWriter().println(translatedMonths);
      response.getWriter().println(translatedHome);
      response.getWriter().println(translatedUtility);
      response.getWriter().println(translatedTransport);
      response.getWriter().println(translatedFood);
      response.getWriter().println(translatedDebt);
      response.getWriter().println(translatedMed);
      response.getWriter().println(translatedOther);
  }
}

