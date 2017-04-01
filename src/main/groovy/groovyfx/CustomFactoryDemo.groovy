package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Custom Factory"

// tag::code[]
import groovyfx.javafx.factory.MessageFactory
import org.controlsfx.control.GridView
import org.controlsfx.control.cell.ColorGridCell

groovyx.javafx.GroovyFX.start {
    delegate.registerBeanFactory('gridView', GridView)
    delegate.registerFactory('message', new MessageFactory())
    stage(title: 'Custom Factory Demo', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            vbox {
                label('Hello GroovyFX', styleClass: 'big')
                gridView(items: [RED, GREEN, YELLOW, SLATEGREY],
                        prefHeight: 200,
                        cellFactory: { new ColorGridCell() }
                )
                message(message: 'This is a message')
            }
        }
    }
}
// end::code[]