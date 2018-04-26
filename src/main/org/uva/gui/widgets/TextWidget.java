package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.StringValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;

public class TextWidget extends QuestionWidget {

    private final JTextField textField = new JTextField();

    //todo test
    public TextWidget(Question question, Value value, boolean readOnly, Style style) {
        super(question);

        this.textField.setText(value.getValue().toString());
        this.textField.setHorizontalAlignment(JTextField.CENTER);
        this.textField.setEnabled(readOnly);
        this.add(this.textField, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }
    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.textField.addActionListener(e -> questionChangeListener.onQuestionChanged(question, new StringValue(textField.getText())));
    }

    @Override
    public void setFont(String font) {
        super.setFont(font);
        this.textField.setFont(this.questionLabel.getFont());
    }

    @Override
    public void setFontSize(int fontSize) {
        super.setFontSize(fontSize);
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.textField.setFont(newFont);
    }
}
