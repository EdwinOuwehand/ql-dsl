package org.uva.gui.widgets;

import org.uva.gui.QuestionChangeListener;
import org.uva.ql.ast.Question;

import javax.swing.*;
import java.awt.*;

public abstract class QuestionWidget extends JPanel {

    final Question question;
    final JLabel questionLabel;

    QuestionWidget(Question question) {
        this.question = question;

        this.setLayout(new GridLayout(1, 2));
        this.questionLabel = new JLabel(question.getContent());
        this.questionLabel.setVisible(true);
        this.add(questionLabel, 0);
        this.setVisible(true);
    }

    public void setFont(String font) {
        int currentSize = questionLabel.getFont().getSize();
        Font newFont = new Font(font, Font.PLAIN, currentSize);
        this.questionLabel.setFont(newFont);
    }

    public void setFontSize(int fontSize) {
        Font newFont = questionLabel.getFont().deriveFont((float) fontSize);
        this.questionLabel.setFont(newFont);
    }

    public void setWidth(int width) {
        this.getLayout().preferredLayoutSize(this).width = width;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public abstract void setQuestionChangeListener(QuestionChangeListener questionChangeListener);
}
