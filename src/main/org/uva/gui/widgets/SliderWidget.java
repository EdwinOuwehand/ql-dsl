package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class SliderWidget extends QuestionWidget implements ChangeListener {

    private final JSlider slider;
    private final JLabel label;
    private QuestionChangeListener questionChangeListener;

    public SliderWidget(Question question, Value value, boolean readOnly, Style style, int start, int end, int steps) {
        super(question);

        int currentValue = (int) value.getValue();
        if (currentValue > end) {
            currentValue = end;
        } else if (currentValue < start) {
            currentValue = start;
        }

        this.slider = new JSlider(start, end, currentValue);
        this.slider.setMajorTickSpacing(steps);
        this.slider.setMinorTickSpacing(steps);
        this.slider.setSnapToTicks(true);
        this.slider.setEnabled(readOnly);

        this.label = new JLabel(Integer.toString(currentValue));

        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BorderLayout());

        widgetPanel.add(slider, BorderLayout.CENTER);
        widgetPanel.add(label, BorderLayout.EAST);
        this.add(widgetPanel, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.questionChangeListener = questionChangeListener;
        slider.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.label.setText(Integer.toString(slider.getModel().getValue()));
        if (!slider.getValueIsAdjusting()) {
            questionChangeListener.onQuestionChanged(question, new IntegerValue(slider.getValue()));
        }
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.slider.setBackground(color);
        this.label.setOpaque(true);
        this.label.setBackground(color);
    }

    @Override
    public void setFontSize(int fontSize) {
        super.setFontSize(fontSize);
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.label.setFont(newFont);
    }

    @Override
    public void setFont(String font) {
        super.setFont(font);
        this.label.setFont(this.questionLabel.getFont());
    }
}
