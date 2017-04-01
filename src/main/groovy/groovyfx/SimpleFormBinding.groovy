package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Binding"

// tag::code[]
import static groovyx.javafx.GroovyFX.start
start {
    stage(title: 'Simple form binding', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            vbox {
                label('Name')
                def textField = textField()
                label text: bind(textField.textProperty())
                label text: bind(textField.text())
                label text: bind{textField.text}
                label text: bind(textField, 'text')
                label text: bind(textField, 'text').using{ "Text: $it" }
                label id: 'lastLabel'

                bind lastLabel.text() to textField.text()
            }
        }
    }
}
// end::code[]