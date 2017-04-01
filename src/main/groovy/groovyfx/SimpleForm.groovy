package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Simple Form"

// tag::code[]
import static groovyx.javafx.GroovyFX.start
start {
    stage(title: 'Simple form', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            vbox {
                label('Name')
                textField(onAction: { evt -> result.text = evt.source.text })
                label(id: 'result')
            }
        }
    }
}
// end::code[]