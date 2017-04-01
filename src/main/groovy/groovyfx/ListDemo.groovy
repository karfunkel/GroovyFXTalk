package groovyfx

import groovy.transform.Field
import groovyx.javafx.beans.FXBindable

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "List"

// tag::code[]
@FXBindable
class SelectionHolder {
    def selected
}

def selectionHolder = new SelectionHolder()
def colors = ['blue', 'green', 'red']
start { primaryStage ->
    stage(title: "GroovyFX List Demo", width: 400, height: 200, visible: true) {
        scene(fill: WHITE) {
            vbox(padding: 10, spacing: 5) {
                choiceBox(value: bind(selectionHolder.selected()), items: colors)
                listView(id: 'myList', items: colors) {
                    onSelect { control, item ->
                        selectionHolder.selected = item
                    }
                }
                selectionHolder.selected().onChange { source, oldValue, newValue ->
                    myList.selectionModel.select(newValue)
                }
                label(text: bind(selectionHolder.selected()))
}   }   }   }
// tag::code[]