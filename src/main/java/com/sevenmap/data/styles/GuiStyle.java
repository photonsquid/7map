package com.sevenmap.data.styles;

import java.util.Objects;

public class GuiStyle {

  private String text;
  private String textdisabled;
  private String windowbg;
  private String childwindowbg;
  private String popupbg;
  private String border;
  private String bordershadow;
  private String framebg;
  private String framebghovered;
  private String framebgactive;
  private String titlebg;
  private String titlebgactive;
  private String titlebgcollapsed;
  private String menubarbg;
  private String scrollbarbg;
  private String scrollbargrab;
  private String scrollbargrabhovered;
  private String scrollbargrabactive;
  private String combobg;
  private String checkmark;
  private String slidergrab;
  private String slidergrabactive;
  private String button;
  private String buttonhovered;
  private String buttonactive;
  private String header;
  private String headerhovered;
  private String headeractive;
  private String separator;
  private String separatorhovered;
  private String separatoractive;
  private String resizegrip;
  private String resizegriphovered;
  private String resizegripactive;
  private String closebutton;
  private String closebuttonhovered;
  private String closebuttonactive;
  private String plotlines;
  private String plotlineshovered;
  private String plothistogram;
  private String plothistogramhovered;
  private String textselectedbg;
  private String modalwindowdarkening;

  public GuiStyle() {
  }

  public GuiStyle(String text, String textdisabled, String windowbg, String childwindowbg, String popupbg,
      String border, String bordershadow, String framebg, String framebghovered, String framebgactive, String titlebg,
      String titlebgactive, String titlebgcollapsed, String menubarbg, String scrollbarbg, String scrollbargrab,
      String scrollbargrabhovered, String scrollbargrabactive, String combobg, String checkmark, String slidergrab,
      String slidergrabactive, String button, String buttonhovered, String buttonactive, String header,
      String headerhovered, String headeractive, String separator, String separatorhovered, String separatoractive,
      String resizegrip, String resizegriphovered, String resizegripactive, String closebutton,
      String closebuttonhovered, String closebuttonactive, String plotlines, String plotlineshovered,
      String plothistogram, String plothistogramhovered, String textselectedbg, String modalwindowdarkening) {
    this.text = text;
    this.textdisabled = textdisabled;
    this.windowbg = windowbg;
    this.childwindowbg = childwindowbg;
    this.popupbg = popupbg;
    this.border = border;
    this.bordershadow = bordershadow;
    this.framebg = framebg;
    this.framebghovered = framebghovered;
    this.framebgactive = framebgactive;
    this.titlebg = titlebg;
    this.titlebgactive = titlebgactive;
    this.titlebgcollapsed = titlebgcollapsed;
    this.menubarbg = menubarbg;
    this.scrollbarbg = scrollbarbg;
    this.scrollbargrab = scrollbargrab;
    this.scrollbargrabhovered = scrollbargrabhovered;
    this.scrollbargrabactive = scrollbargrabactive;
    this.combobg = combobg;
    this.checkmark = checkmark;
    this.slidergrab = slidergrab;
    this.slidergrabactive = slidergrabactive;
    this.button = button;
    this.buttonhovered = buttonhovered;
    this.buttonactive = buttonactive;
    this.header = header;
    this.headerhovered = headerhovered;
    this.headeractive = headeractive;
    this.separator = separator;
    this.separatorhovered = separatorhovered;
    this.separatoractive = separatoractive;
    this.resizegrip = resizegrip;
    this.resizegriphovered = resizegriphovered;
    this.resizegripactive = resizegripactive;
    this.closebutton = closebutton;
    this.closebuttonhovered = closebuttonhovered;
    this.closebuttonactive = closebuttonactive;
    this.plotlines = plotlines;
    this.plotlineshovered = plotlineshovered;
    this.plothistogram = plothistogram;
    this.plothistogramhovered = plothistogramhovered;
    this.textselectedbg = textselectedbg;
    this.modalwindowdarkening = modalwindowdarkening;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getTextdisabled() {
    return this.textdisabled;
  }

  public void setTextdisabled(String textdisabled) {
    this.textdisabled = textdisabled;
  }

  public String getWindowbg() {
    return this.windowbg;
  }

  public void setWindowbg(String windowbg) {
    this.windowbg = windowbg;
  }

  public String getChildwindowbg() {
    return this.childwindowbg;
  }

  public void setChildwindowbg(String childwindowbg) {
    this.childwindowbg = childwindowbg;
  }

  public String getPopupbg() {
    return this.popupbg;
  }

  public void setPopupbg(String popupbg) {
    this.popupbg = popupbg;
  }

  public String getBorder() {
    return this.border;
  }

  public void setBorder(String border) {
    this.border = border;
  }

  public String getBordershadow() {
    return this.bordershadow;
  }

  public void setBordershadow(String bordershadow) {
    this.bordershadow = bordershadow;
  }

  public String getFramebg() {
    return this.framebg;
  }

  public void setFramebg(String framebg) {
    this.framebg = framebg;
  }

  public String getFramebghovered() {
    return this.framebghovered;
  }

  public void setFramebghovered(String framebghovered) {
    this.framebghovered = framebghovered;
  }

  public String getFramebgactive() {
    return this.framebgactive;
  }

  public void setFramebgactive(String framebgactive) {
    this.framebgactive = framebgactive;
  }

  public String getTitlebg() {
    return this.titlebg;
  }

  public void setTitlebg(String titlebg) {
    this.titlebg = titlebg;
  }

  public String getTitlebgactive() {
    return this.titlebgactive;
  }

  public void setTitlebgactive(String titlebgactive) {
    this.titlebgactive = titlebgactive;
  }

  public String getTitlebgcollapsed() {
    return this.titlebgcollapsed;
  }

  public void setTitlebgcollapsed(String titlebgcollapsed) {
    this.titlebgcollapsed = titlebgcollapsed;
  }

  public String getMenubarbg() {
    return this.menubarbg;
  }

  public void setMenubarbg(String menubarbg) {
    this.menubarbg = menubarbg;
  }

  public String getScrollbarbg() {
    return this.scrollbarbg;
  }

  public void setScrollbarbg(String scrollbarbg) {
    this.scrollbarbg = scrollbarbg;
  }

  public String getScrollbargrab() {
    return this.scrollbargrab;
  }

  public void setScrollbargrab(String scrollbargrab) {
    this.scrollbargrab = scrollbargrab;
  }

  public String getScrollbargrabhovered() {
    return this.scrollbargrabhovered;
  }

  public void setScrollbargrabhovered(String scrollbargrabhovered) {
    this.scrollbargrabhovered = scrollbargrabhovered;
  }

  public String getScrollbargrabactive() {
    return this.scrollbargrabactive;
  }

  public void setScrollbargrabactive(String scrollbargrabactive) {
    this.scrollbargrabactive = scrollbargrabactive;
  }

  public String getCombobg() {
    return this.combobg;
  }

  public void setCombobg(String combobg) {
    this.combobg = combobg;
  }

  public String getCheckmark() {
    return this.checkmark;
  }

  public void setCheckmark(String checkmark) {
    this.checkmark = checkmark;
  }

  public String getSlidergrab() {
    return this.slidergrab;
  }

  public void setSlidergrab(String slidergrab) {
    this.slidergrab = slidergrab;
  }

  public String getSlidergrabactive() {
    return this.slidergrabactive;
  }

  public void setSlidergrabactive(String slidergrabactive) {
    this.slidergrabactive = slidergrabactive;
  }

  public String getButton() {
    return this.button;
  }

  public void setButton(String button) {
    this.button = button;
  }

  public String getButtonhovered() {
    return this.buttonhovered;
  }

  public void setButtonhovered(String buttonhovered) {
    this.buttonhovered = buttonhovered;
  }

  public String getButtonactive() {
    return this.buttonactive;
  }

  public void setButtonactive(String buttonactive) {
    this.buttonactive = buttonactive;
  }

  public String getHeader() {
    return this.header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getHeaderhovered() {
    return this.headerhovered;
  }

  public void setHeaderhovered(String headerhovered) {
    this.headerhovered = headerhovered;
  }

  public String getHeaderactive() {
    return this.headeractive;
  }

  public void setHeaderactive(String headeractive) {
    this.headeractive = headeractive;
  }

  public String getSeparator() {
    return this.separator;
  }

  public void setSeparator(String separator) {
    this.separator = separator;
  }

  public String getSeparatorhovered() {
    return this.separatorhovered;
  }

  public void setSeparatorhovered(String separatorhovered) {
    this.separatorhovered = separatorhovered;
  }

  public String getSeparatoractive() {
    return this.separatoractive;
  }

  public void setSeparatoractive(String separatoractive) {
    this.separatoractive = separatoractive;
  }

  public String getResizegrip() {
    return this.resizegrip;
  }

  public void setResizegrip(String resizegrip) {
    this.resizegrip = resizegrip;
  }

  public String getResizegriphovered() {
    return this.resizegriphovered;
  }

  public void setResizegriphovered(String resizegriphovered) {
    this.resizegriphovered = resizegriphovered;
  }

  public String getResizegripactive() {
    return this.resizegripactive;
  }

  public void setResizegripactive(String resizegripactive) {
    this.resizegripactive = resizegripactive;
  }

  public String getClosebutton() {
    return this.closebutton;
  }

  public void setClosebutton(String closebutton) {
    this.closebutton = closebutton;
  }

  public String getClosebuttonhovered() {
    return this.closebuttonhovered;
  }

  public void setClosebuttonhovered(String closebuttonhovered) {
    this.closebuttonhovered = closebuttonhovered;
  }

  public String getClosebuttonactive() {
    return this.closebuttonactive;
  }

  public void setClosebuttonactive(String closebuttonactive) {
    this.closebuttonactive = closebuttonactive;
  }

  public String getPlotlines() {
    return this.plotlines;
  }

  public void setPlotlines(String plotlines) {
    this.plotlines = plotlines;
  }

  public String getPlotlineshovered() {
    return this.plotlineshovered;
  }

  public void setPlotlineshovered(String plotlineshovered) {
    this.plotlineshovered = plotlineshovered;
  }

  public String getPlothistogram() {
    return this.plothistogram;
  }

  public void setPlothistogram(String plothistogram) {
    this.plothistogram = plothistogram;
  }

  public String getPlothistogramhovered() {
    return this.plothistogramhovered;
  }

  public void setPlothistogramhovered(String plothistogramhovered) {
    this.plothistogramhovered = plothistogramhovered;
  }

  public String getTextselectedbg() {
    return this.textselectedbg;
  }

  public void setTextselectedbg(String textselectedbg) {
    this.textselectedbg = textselectedbg;
  }

  public String getModalwindowdarkening() {
    return this.modalwindowdarkening;
  }

  public void setModalwindowdarkening(String modalwindowdarkening) {
    this.modalwindowdarkening = modalwindowdarkening;
  }

  public GuiStyle text(String text) {
    setText(text);
    return this;
  }

  public GuiStyle textdisabled(String textdisabled) {
    setTextdisabled(textdisabled);
    return this;
  }

  public GuiStyle windowbg(String windowbg) {
    setWindowbg(windowbg);
    return this;
  }

  public GuiStyle childwindowbg(String childwindowbg) {
    setChildwindowbg(childwindowbg);
    return this;
  }

  public GuiStyle popupbg(String popupbg) {
    setPopupbg(popupbg);
    return this;
  }

  public GuiStyle border(String border) {
    setBorder(border);
    return this;
  }

  public GuiStyle bordershadow(String bordershadow) {
    setBordershadow(bordershadow);
    return this;
  }

  public GuiStyle framebg(String framebg) {
    setFramebg(framebg);
    return this;
  }

  public GuiStyle framebghovered(String framebghovered) {
    setFramebghovered(framebghovered);
    return this;
  }

  public GuiStyle framebgactive(String framebgactive) {
    setFramebgactive(framebgactive);
    return this;
  }

  public GuiStyle titlebg(String titlebg) {
    setTitlebg(titlebg);
    return this;
  }

  public GuiStyle titlebgactive(String titlebgactive) {
    setTitlebgactive(titlebgactive);
    return this;
  }

  public GuiStyle titlebgcollapsed(String titlebgcollapsed) {
    setTitlebgcollapsed(titlebgcollapsed);
    return this;
  }

  public GuiStyle menubarbg(String menubarbg) {
    setMenubarbg(menubarbg);
    return this;
  }

  public GuiStyle scrollbarbg(String scrollbarbg) {
    setScrollbarbg(scrollbarbg);
    return this;
  }

  public GuiStyle scrollbargrab(String scrollbargrab) {
    setScrollbargrab(scrollbargrab);
    return this;
  }

  public GuiStyle scrollbargrabhovered(String scrollbargrabhovered) {
    setScrollbargrabhovered(scrollbargrabhovered);
    return this;
  }

  public GuiStyle scrollbargrabactive(String scrollbargrabactive) {
    setScrollbargrabactive(scrollbargrabactive);
    return this;
  }

  public GuiStyle combobg(String combobg) {
    setCombobg(combobg);
    return this;
  }

  public GuiStyle checkmark(String checkmark) {
    setCheckmark(checkmark);
    return this;
  }

  public GuiStyle slidergrab(String slidergrab) {
    setSlidergrab(slidergrab);
    return this;
  }

  public GuiStyle slidergrabactive(String slidergrabactive) {
    setSlidergrabactive(slidergrabactive);
    return this;
  }

  public GuiStyle button(String button) {
    setButton(button);
    return this;
  }

  public GuiStyle buttonhovered(String buttonhovered) {
    setButtonhovered(buttonhovered);
    return this;
  }

  public GuiStyle buttonactive(String buttonactive) {
    setButtonactive(buttonactive);
    return this;
  }

  public GuiStyle header(String header) {
    setHeader(header);
    return this;
  }

  public GuiStyle headerhovered(String headerhovered) {
    setHeaderhovered(headerhovered);
    return this;
  }

  public GuiStyle headeractive(String headeractive) {
    setHeaderactive(headeractive);
    return this;
  }

  public GuiStyle separator(String separator) {
    setSeparator(separator);
    return this;
  }

  public GuiStyle separatorhovered(String separatorhovered) {
    setSeparatorhovered(separatorhovered);
    return this;
  }

  public GuiStyle separatoractive(String separatoractive) {
    setSeparatoractive(separatoractive);
    return this;
  }

  public GuiStyle resizegrip(String resizegrip) {
    setResizegrip(resizegrip);
    return this;
  }

  public GuiStyle resizegriphovered(String resizegriphovered) {
    setResizegriphovered(resizegriphovered);
    return this;
  }

  public GuiStyle resizegripactive(String resizegripactive) {
    setResizegripactive(resizegripactive);
    return this;
  }

  public GuiStyle closebutton(String closebutton) {
    setClosebutton(closebutton);
    return this;
  }

  public GuiStyle closebuttonhovered(String closebuttonhovered) {
    setClosebuttonhovered(closebuttonhovered);
    return this;
  }

  public GuiStyle closebuttonactive(String closebuttonactive) {
    setClosebuttonactive(closebuttonactive);
    return this;
  }

  public GuiStyle plotlines(String plotlines) {
    setPlotlines(plotlines);
    return this;
  }

  public GuiStyle plotlineshovered(String plotlineshovered) {
    setPlotlineshovered(plotlineshovered);
    return this;
  }

  public GuiStyle plothistogram(String plothistogram) {
    setPlothistogram(plothistogram);
    return this;
  }

  public GuiStyle plothistogramhovered(String plothistogramhovered) {
    setPlothistogramhovered(plothistogramhovered);
    return this;
  }

  public GuiStyle textselectedbg(String textselectedbg) {
    setTextselectedbg(textselectedbg);
    return this;
  }

  public GuiStyle modalwindowdarkening(String modalwindowdarkening) {
    setModalwindowdarkening(modalwindowdarkening);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof GuiStyle)) {
      return false;
    }
    GuiStyle guiStyle = (GuiStyle) o;
    return Objects.equals(text, guiStyle.text) && Objects.equals(textdisabled, guiStyle.textdisabled)
        && Objects.equals(windowbg, guiStyle.windowbg) && Objects.equals(childwindowbg, guiStyle.childwindowbg)
        && Objects.equals(popupbg, guiStyle.popupbg) && Objects.equals(border, guiStyle.border)
        && Objects.equals(bordershadow, guiStyle.bordershadow) && Objects.equals(framebg, guiStyle.framebg)
        && Objects.equals(framebghovered, guiStyle.framebghovered)
        && Objects.equals(framebgactive, guiStyle.framebgactive) && Objects.equals(titlebg, guiStyle.titlebg)
        && Objects.equals(titlebgactive, guiStyle.titlebgactive)
        && Objects.equals(titlebgcollapsed, guiStyle.titlebgcollapsed) && Objects.equals(menubarbg, guiStyle.menubarbg)
        && Objects.equals(scrollbarbg, guiStyle.scrollbarbg) && Objects.equals(scrollbargrab, guiStyle.scrollbargrab)
        && Objects.equals(scrollbargrabhovered, guiStyle.scrollbargrabhovered)
        && Objects.equals(scrollbargrabactive, guiStyle.scrollbargrabactive)
        && Objects.equals(combobg, guiStyle.combobg) && Objects.equals(checkmark, guiStyle.checkmark)
        && Objects.equals(slidergrab, guiStyle.slidergrab)
        && Objects.equals(slidergrabactive, guiStyle.slidergrabactive) && Objects.equals(button, guiStyle.button)
        && Objects.equals(buttonhovered, guiStyle.buttonhovered) && Objects.equals(buttonactive, guiStyle.buttonactive)
        && Objects.equals(header, guiStyle.header) && Objects.equals(headerhovered, guiStyle.headerhovered)
        && Objects.equals(headeractive, guiStyle.headeractive) && Objects.equals(separator, guiStyle.separator)
        && Objects.equals(separatorhovered, guiStyle.separatorhovered)
        && Objects.equals(separatoractive, guiStyle.separatoractive) && Objects.equals(resizegrip, guiStyle.resizegrip)
        && Objects.equals(resizegriphovered, guiStyle.resizegriphovered)
        && Objects.equals(resizegripactive, guiStyle.resizegripactive)
        && Objects.equals(closebutton, guiStyle.closebutton)
        && Objects.equals(closebuttonhovered, guiStyle.closebuttonhovered)
        && Objects.equals(closebuttonactive, guiStyle.closebuttonactive)
        && Objects.equals(plotlines, guiStyle.plotlines) && Objects.equals(plotlineshovered, guiStyle.plotlineshovered)
        && Objects.equals(plothistogram, guiStyle.plothistogram)
        && Objects.equals(plothistogramhovered, guiStyle.plothistogramhovered)
        && Objects.equals(textselectedbg, guiStyle.textselectedbg)
        && Objects.equals(modalwindowdarkening, guiStyle.modalwindowdarkening);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, textdisabled, windowbg, childwindowbg, popupbg, border, bordershadow, framebg,
        framebghovered, framebgactive, titlebg, titlebgactive, titlebgcollapsed, menubarbg, scrollbarbg, scrollbargrab,
        scrollbargrabhovered, scrollbargrabactive, combobg, checkmark, slidergrab, slidergrabactive, button,
        buttonhovered, buttonactive, header, headerhovered, headeractive, separator, separatorhovered, separatoractive,
        resizegrip, resizegriphovered, resizegripactive, closebutton, closebuttonhovered, closebuttonactive, plotlines,
        plotlineshovered, plothistogram, plothistogramhovered, textselectedbg, modalwindowdarkening);
  }

  @Override
  public String toString() {
    return "{" + " text='" + getText() + "'" + ", textdisabled='" + getTextdisabled() + "'" + ", windowbg='"
        + getWindowbg() + "'" + ", childwindowbg='" + getChildwindowbg() + "'" + ", popupbg='" + getPopupbg() + "'"
        + ", border='" + getBorder() + "'" + ", bordershadow='" + getBordershadow() + "'" + ", framebg='" + getFramebg()
        + "'" + ", framebghovered='" + getFramebghovered() + "'" + ", framebgactive='" + getFramebgactive() + "'"
        + ", titlebg='" + getTitlebg() + "'" + ", titlebgactive='" + getTitlebgactive() + "'" + ", titlebgcollapsed='"
        + getTitlebgcollapsed() + "'" + ", menubarbg='" + getMenubarbg() + "'" + ", scrollbarbg='" + getScrollbarbg()
        + "'" + ", scrollbargrab='" + getScrollbargrab() + "'" + ", scrollbargrabhovered='" + getScrollbargrabhovered()
        + "'" + ", scrollbargrabactive='" + getScrollbargrabactive() + "'" + ", combobg='" + getCombobg() + "'"
        + ", checkmark='" + getCheckmark() + "'" + ", slidergrab='" + getSlidergrab() + "'" + ", slidergrabactive='"
        + getSlidergrabactive() + "'" + ", button='" + getButton() + "'" + ", buttonhovered='" + getButtonhovered()
        + "'" + ", buttonactive='" + getButtonactive() + "'" + ", header='" + getHeader() + "'" + ", headerhovered='"
        + getHeaderhovered() + "'" + ", headeractive='" + getHeaderactive() + "'" + ", separator='" + getSeparator()
        + "'" + ", separatorhovered='" + getSeparatorhovered() + "'" + ", separatoractive='" + getSeparatoractive()
        + "'" + ", resizegrip='" + getResizegrip() + "'" + ", resizegriphovered='" + getResizegriphovered() + "'"
        + ", resizegripactive='" + getResizegripactive() + "'" + ", closebutton='" + getClosebutton() + "'"
        + ", closebuttonhovered='" + getClosebuttonhovered() + "'" + ", closebuttonactive='" + getClosebuttonactive()
        + "'" + ", plotlines='" + getPlotlines() + "'" + ", plotlineshovered='" + getPlotlineshovered() + "'"
        + ", plothistogram='" + getPlothistogram() + "'" + ", plothistogramhovered='" + getPlothistogramhovered() + "'"
        + ", textselectedbg='" + getTextselectedbg() + "'" + ", modalwindowdarkening='" + getModalwindowdarkening()
        + "'" + "}";
  }

}
