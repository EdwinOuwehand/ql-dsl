package org.uva.gui;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.ExpressionEvaluator;
import org.uva.ql.evaluator.FormEvaluator;
import org.uva.ql.evaluator.value.Value;
import org.uva.ql.validation.ValidationResult;
import org.uva.qls.evaluator.StyleEvaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;


public class GUIHandler {

    private JFrame frame;

    private final FormEvaluator formEvaluator;
    private final StyleEvaluator styleEvaluator;

    private final QuestionChangeListener questionChangeListener;
    private final ExpressionEvaluator expressionEvaluator;

    private Question lastChangedQuestion = null;

    public GUIHandler(FormEvaluator formEvaluator, StyleEvaluator styleEvaluator, ValidationResult validationResult) {
        this.formEvaluator = formEvaluator;
        this.styleEvaluator = styleEvaluator;

        this.questionChangeListener = new QuestionChangeListener();
        this.questionChangeListener.registerGUIHandler(this);
        this.expressionEvaluator = new ExpressionEvaluator();

        initializeFrame();
        checkForErrors(validationResult);

        // Initialize formEvaluator
        this.formEvaluator.evaluateAllExpressions(this.expressionEvaluator);

        generateGUI();
    }

    public void onQuestionChange(Question question, Value value) {
        formEvaluator.addOrUpdateValue(question.getId(), value);
        this.lastChangedQuestion = question;
        generateGUI();
    }

    private void generateGUI() {
        this.frame.getContentPane().removeAll();
        this.styleEvaluator.generateSections();

        WidgetFactory widgetFactory = new WidgetFactory(this.questionChangeListener, this.styleEvaluator);
        this.formEvaluator.evaluateAllExpressions(this.expressionEvaluator);

        for (Question question : formEvaluator.getVisibleQuestions(this.expressionEvaluator)) {
            Value currentValue = formEvaluator.getValueById(question.getId());
            QuestionWidget widget = widgetFactory.makeWidget(question, currentValue, !formEvaluator.questionIsCalculated(question));

            this.styleEvaluator.setWidget(question, widget);
        }
        this.frame.add(styleEvaluator.getLayout(this.lastChangedQuestion));
        this.frame.add(getSaveButton());
        this.frame.pack();
    }


    private void initializeFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(750, 600);
        this.frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        this.frame.setVisible(true);
    }

    private JPanel getSaveButton() {
        JPanel savePanel = new JPanel();
        savePanel.setLayout(new BorderLayout());

        JButton saveButton = new JButton("Submit");
        saveButton.addActionListener(e -> saveAndQuit());

        savePanel.add(saveButton, BorderLayout.SOUTH);
        return savePanel;
    }

    private void saveAndQuit() {
        int dialogResult = JOptionPane.showConfirmDialog(this.frame, "Would you like to save and quit?", "Warning", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            this.formEvaluator.saveState();
            this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

    }

    private void checkForErrors(ValidationResult validationResult) {
        Logger logger = Logger.getGlobal();
        logger.info("Hallo");

        if (validationResult.hasErrors() || validationResult.hasWarnings()) {
            for (String warning : validationResult.getWarnings()) {
                JOptionPane.showMessageDialog(frame, warning, "Error", JOptionPane.ERROR_MESSAGE);
            }

            for (String error : validationResult.getErrors()) {
                JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
            }

            this.frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }


}
