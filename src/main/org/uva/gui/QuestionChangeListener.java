package org.uva.gui;

import org.uva.ql.ast.Question;
import org.uva.ql.evaluator.value.Value;

public class QuestionChangeListener {

    private GUIHandler guiHandler;

    public void registerGUIHandler(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    public void onQuestionChanged(Question question, Value value) {
        if (guiHandler != null) {
            this.guiHandler.onQuestionChange(question, value);
        }
    }
}
