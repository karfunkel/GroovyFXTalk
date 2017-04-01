package groovyfx

import groovy.transform.Field
import javafx.scene.control.ToggleGroup

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Actions"

// tag::actions[]
start {
    actions {
        fxaction(id: 'saveAction',
                name: 'Save',
                description: 'This saves something',
                accelerator: 'Ctrl+S',
                enabled: false,
                onAction: { println "Save" })
        fxaction(id: 'exitAction',
                name: 'Exit',
                onAction: { primaryStage.close() })
        fxaction(id: 'copyAction',
                name: 'Copy', icon: 'icons/copy.png',
                onAction: { println "Copy" }
        )
        fxaction(id: 'pasteAction',
                name: 'Paste', icon: 'icons/paste.png',
                onAction: { println "Paste" }
        )
        fxaction(id: 'checkAction',
                name: 'Check',
                selected: true,
                onAction: { println "Check" }
        )
    }
// ...
// end::actions[]
// tag::menu[]
    stage(title: "GroovyFX Menu Demo", width: 650, height: 450, visible: true) {
        scene(fill: WHITE) {
            borderPane {
                top {
                    menuBar {
                        menu("File") {
                            menuItem("Open", onAction: { println "Open" }) {
                                rectangle(width: 16, height: 16, fill: RED)
                            }
                            menuItem(saveAction) {
                                graphic(circle(radius: 8, fill: BLUE))
                            }
                            separatorMenuItem()
                            menuItem(exitAction)
                        }
                        menu(text: "Edit") {
                            menuItem(text: "Cut", onAction: { println "Cut" }) {
                                imageView('/icons/cut.png')
                            }
                            menuItem(copyAction)
                            menuItem(pasteAction)
                            separatorMenuItem()
                            checkMenuItem(checkAction)
                            def toggleGroup = new ToggleGroup()
                            radioMenuItem("Radio1", toggleGroup: toggleGroup, selected: true)
                            radioMenuItem("Radio2", toggleGroup: toggleGroup)
                            menu("Foo") {
                                menuItem("Bar")
                                menuItem("FooBar")
                }   }   }   }
// ...
// end::menu[]
// tag::code[]
                center {
                    vbox(spacing: 20, padding: 10) {
                        checkBox("Enable 'Save' menu", id: 'cb')
                        bean(saveAction, enabled: bind(cb.selectedProperty()))
                    }
                }
                bottom {
                    toolBar {
                        button(onAction: { println "Cut" }) {
                            graphic imageView('/icons/cut.png')
                        }
                        button(copyAction, skipName: true)
                        button(pasteAction, skipName: true)
                    }
                }
            }
        }
    }
}