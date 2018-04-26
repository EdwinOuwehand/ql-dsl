package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.IntegerValue;
import org.uva.ql.evaluator.value.Value;
import org.uva.qls.ast.Style.Style;
import org.uva.qls.ast.Style.StyleProperty.StyleProperty;

import javax.swing.*;
import java.awt.*;


public class SpinBoxWidget extends QuestionWidget {

    private final JSpinner spinner;

    public SpinBoxWidget(Question question, Value value, boolean readOnly, Style style) {
        super(question);

        this.spinner = new JSpinner();
        this.spinner.setValue(value.getValue());
        this.spinner.setEnabled(readOnly);

        this.add(this.spinner, 1);

        for (StyleProperty property : style.getStyleProperties()) {
            property.apply(this);
        }


    }

    @Override
    public void setQuestionChangeListener(QuestionChangeListener questionChangeListener) {
        this.spinner.addChangeListener(e -> questionChangeListener.onQuestionChanged(question, new IntegerValue((int) this.spinner.getValue())));
    }

    @Override
    public void setFontSize(int fontSize) {
        super.setFontSize(fontSize);
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.spinner.setFont(newFont);
    }

    @Override
    public void setFont(String font) {
        super.setFont(font);
        this.spinner.setFont(this.questionLabel.getFont());
    }
}
