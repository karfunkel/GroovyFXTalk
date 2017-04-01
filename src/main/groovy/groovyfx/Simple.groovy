package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Simple"

// tag::code[]
import static groovyx.javafx.GroovyFX.start

start {
    stage(title: 'Hello GroovyFX', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            label('Hello GroovyFX', styleClass: 'big')
        }
    }
}
// end::code[]