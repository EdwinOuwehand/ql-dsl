package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.BooleanValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public class DropdownWidget extends QuestionWidget {

    private final JComboBox<String> comboBox;

    public DropdownWidget(Question question, Value value, boolean readOnly, Style style, String trueLabel, String falseLabel) {
        super(question);

        this.comboBox = new JComboBox<>();
        this.comboBox.addItem(trueLabel);
        this.comboBox.addItem(falseLabel);

        this.comboBox.setSelectedItem(falseLabel);
        if ((boolean) value.getValue()) {
            this.comboBox.setSelectedItem(trueLabel);
        }

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }

        this.comboBox.setEnabled(readOnly);

        this.add(this.comboBox, 1);
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.comboBox.addActionListener(e -> questionChangeListener.onQuestionChanged(question, new BooleanValue(comboBox.getSelectedIndex() == 0)));
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        this.comboBox.setBackground(color);
    }

    @Override
    public void setFontSize(int fontSize) {
        super.setFontSize(fontSize);
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.comboBox.setFont(newFont);
    }

    @Override
    public void setFont(String font) {
        super.setFont(font);
        this.comboBox.setFont(this.questionLabel.getFont());
    }
}
