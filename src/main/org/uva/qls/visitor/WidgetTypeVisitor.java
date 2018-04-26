package org.uva.qls.visitor;

import org.uva.qls.ast.Widget.WidgetTypes.*;

public interface WidgetTypeVisitor<W, Q, V, B> {

    W visit(CheckboxType checkboxType, Q question, V value, B readOnly);

    W visit(DropDownType dropDownType, Q question, V value, B readOnly);

    W visit(RadioType radioType, Q question, V value, B readOnly);

    W visit(SliderType sliderType, Q question, V value, B readOnly);

    W visit(SpinboxType spinboxType, Q question, V value, B readOnly);

    W visit(TextType textType, Q question, V value, B readOnly);
}
