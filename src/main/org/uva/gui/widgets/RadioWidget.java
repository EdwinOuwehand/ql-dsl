package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public class RadioWidget extends QuestionWidget {

    private final JRadioButton trueButton;
    private final JRadioButton falseButton;

    public RadioWidget(Question question, Value value, boolean readOnly, Style style, String trueLabel, String falseLabel) {
        super(question);

        this.trueButton = new JRadioButton(trueLabel);
        this.falseButton = new JRadioButton(falseLabel);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.trueButton);
        buttonGroup.add(this.falseButton);

        buttonGroup.setSelected(this.falseButton.getModel(), true);
        if ((boolean) value.getValue()) {
            buttonGroup.setSelected(this.trueButton.getModel(), true);
        }

        this.falseButton.setEnabled(readOnly);
        this.trueButton.setEnabled(readOnly);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        buttonPanel.add(this.trueButton, 0);
        buttonPanel.add(this.falseButton, 1);


        this.add(buttonPanel, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.trueButton.addItemListener(e -> questionChangeListener.onQuestionChanged(question, new BooleanValue(trueButton.isSelected())));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.falseButton.setBackground(color);
        this.trueButton.setBackground(color);
    }

    @Override
    public void setFontSize(int fontSize) {
        super.setFontSize(fontSize);
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.trueButton.setFont(newFont);
        this.falseButton.setFont(newFont);
    }

    @Override
    public void setFont(String font) {
        super.setFont(font);
        this.trueButton.setFont(this.questionLabel.getFont());
        this.falseButton.setFont(this.questionLabel.getFont());
    }
}
