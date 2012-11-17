package org.uiautomation.ios.mobileSafari;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.uiautomation.ios.exceptions.IOSAutomationSetupException;

/**
 * in selenium/javascript/atoms.
 * 
 * Edit build.desc find the function in dom.js , for instance isShown edit
 * selenium/javascript/atoms/build.desc add
 * 
 * js_fragment(name="isVisible", module="bot.dom", function="bot.dom.isShown",
 * deps=["//javascript/atoms:all_js"])
 * 
 * selenium ./go //javascript/atoms:isVisible
 */
public class Atoms {

  private static String getText;
  private static String isDisplayed;
  private static String click;
  private static String back;
  private static String forward;
  static {
    try {
      getText = load("atoms/getVisibleText.js");
      isDisplayed = load("atoms/isVisible.js");
      click = load("atoms/click.js");
      back = load("atoms/back.js");
      forward = load("atoms/forward.js");
    } catch (Exception e) {
      throw new RuntimeException("Cannot load atoms");
    }
  }

  public static String getText() {
    return getText;
  }

  public static String isDisplayed() {
    return isDisplayed;
  }

  public static String click() {
    return click;
  }

  public static String back() {
    return back;
  }

  public static String forward() {
    return forward;
  }

  private static String load(String resource) throws IOException {
    InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    if (is == null) {
      throw new IOSAutomationSetupException("cannot load : " + resource);
    }
    StringWriter writer = new StringWriter();
    IOUtils.copy(is, writer, "UTF-8");
    String content = writer.toString();
    return content;
  }

  public static void main(String[] args) {
    System.out.println(getText());
  }

}