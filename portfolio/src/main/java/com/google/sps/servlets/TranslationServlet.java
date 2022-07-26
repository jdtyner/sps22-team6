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
      
      String text = request.getParameter("text");
      

      Translate translate = TranslateOptions.getDefaultInstance().getService();
      Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(language));
      String translated = translation.getTranslatedText();
 
      // Output the translation.
      response.setContentType("text/html; charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().println(translated);
  }
}

