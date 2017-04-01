package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Border Pane"

// tag::code[]
import static groovyx.javafx.GroovyFX.start

start {
    stage(title: 'Border Pane Demo', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            borderPane {
                top {
                    label('Header')
                }
//              center {
                    textField(id: 'tf')
//              }
                bottom {
                    label text: bind(tf.textProperty())
                }
                right {
                    imageView('GroovyFX_logo.png')
                }
            }
        }
    }
}
// end::code[]