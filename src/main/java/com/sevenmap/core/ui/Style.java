package com.sevenmap.core.ui;

import com.sevenmap.core.Props;
import com.sevenmap.data.styles.GuiStyle;
import com.sevenmap.spinel.gfx.Color;
import com.sevenmap.spinel.math.Vector3f;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;

public class Style {
    public Style(Props props) {
        GuiStyle guiStyle = props.getStyles().findGuiStyle(props.getTheme());
        ImGuiStyle style = ImGui.getStyle();
        style.setWindowBorderSize(0);

        Vector3f c;

        c = getColor(guiStyle.getText());
        style.setColor(ImGuiCol.Text, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getTextdisabled());
        style.setColor(ImGuiCol.TextDisabled, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getWindowbg());
        style.setColor(ImGuiCol.WindowBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getChildwindowbg());
        style.setColor(ImGuiCol.ChildBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getPopupbg());
        style.setColor(ImGuiCol.PopupBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getBorder());
        style.setColor(ImGuiCol.Border, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getBordershadow());
        style.setColor(ImGuiCol.BorderShadow, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getFramebg());
        style.setColor(ImGuiCol.FrameBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getFramebghovered());
        style.setColor(ImGuiCol.FrameBgHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getFramebgactive());
        style.setColor(ImGuiCol.FrameBgActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getTitlebg());
        style.setColor(ImGuiCol.TitleBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getTitlebgactive());
        style.setColor(ImGuiCol.TitleBgActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getTitlebgcollapsed());
        style.setColor(ImGuiCol.TitleBgCollapsed, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getMenubarbg());
        style.setColor(ImGuiCol.MenuBarBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getScrollbarbg());
        style.setColor(ImGuiCol.ScrollbarBg, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getScrollbargrab());
        style.setColor(ImGuiCol.ScrollbarGrab, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getScrollbargrabhovered());
        style.setColor(ImGuiCol.ScrollbarGrabHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getScrollbargrabactive());
        style.setColor(ImGuiCol.ScrollbarGrabActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getCheckmark());
        style.setColor(ImGuiCol.CheckMark, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getSlidergrab());
        style.setColor(ImGuiCol.SliderGrab, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getSlidergrabactive());
        style.setColor(ImGuiCol.SliderGrabActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getButton());
        style.setColor(ImGuiCol.Button, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getButtonhovered());
        style.setColor(ImGuiCol.ButtonHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getButtonactive());
        style.setColor(ImGuiCol.ButtonActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getHeader());
        style.setColor(ImGuiCol.Header, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getHeaderhovered());
        style.setColor(ImGuiCol.HeaderHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getHeaderactive());
        style.setColor(ImGuiCol.HeaderActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getSeparator());
        style.setColor(ImGuiCol.Separator, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getSeparatorhovered());
        style.setColor(ImGuiCol.SeparatorHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getSeparatoractive());
        style.setColor(ImGuiCol.SeparatorActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getResizegrip());
        style.setColor(ImGuiCol.ResizeGrip, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getResizegriphovered());
        style.setColor(ImGuiCol.ResizeGripHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getResizegripactive());
        style.setColor(ImGuiCol.ResizeGripActive, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getPlotlines());
        style.setColor(ImGuiCol.PlotLines, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getPlotlineshovered());
        style.setColor(ImGuiCol.PlotLinesHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getPlothistogram());
        style.setColor(ImGuiCol.PlotHistogram, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getPlothistogramhovered());
        style.setColor(ImGuiCol.PlotHistogramHovered, c.getX(), c.getY(), c.getZ(), 1f);

        c = getColor(guiStyle.getTextselectedbg());
        style.setColor(ImGuiCol.TextSelectedBg, c.getX(), c.getY(), c.getZ(), 1f);

    }

    private Vector3f getColor(String color) {
        return new Color(color).toVector3f();
    }
}
