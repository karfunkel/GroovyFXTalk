package groovyfx.javafx.factory

import groovyx.javafx.factory.LabeledFactory
import javafx.scene.control.Label

class MessageFactory extends LabeledFactory {
    MessageFactory() {
        super(Label)
    }

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
            throws InstantiationException, IllegalAccessException {
        def message = attributes.remove('message')
        if(message)
            attributes.text = message
        return super.newInstance(builder, name, value, attributes)
    }
}
