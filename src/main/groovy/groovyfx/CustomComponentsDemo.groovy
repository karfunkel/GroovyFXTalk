package groovyfx

import groovy.transform.Field
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import org.controlsfx.control.BreadCrumbBar
import org.tbee.javafx.scene.layout.MigPane
import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Custom Components"

// tag::code1[]
start {
    def colors = [BLUE, GREEN, RED, hsb(67, 0.8, 0.91),
                  rgb(39, 209, 100), rgba(20, 100, 150, 0.60),
                  delegate."#AA4411"] as ObservableList
    stage(title: 'Custom Components Demo', visible: true) {
        scene() {
            borderPane {
                treeView(id: 'tree', showRoot: false, prefHeight: 300) {
                    treeItem(expanded: true, value: "Root") {
                        treeItem(value: "one") {
                            treeItem(value: "one.one")
                            treeItem(value: "one.two")
                            treeItem(value: "one.three")
                            graphic { rectangle(width: 20, height: 20, fill: RED) }
                        }
                        treeItem(value: "two") {
                            treeItem(value: "two.one")
                            treeItem(value: "two.two")
                            treeItem(value: "two.three")
                            graphic { rectangle(width: 20, height: 20, fill: GREEN) }
                }   }   }
// ...
// end::code1[]
// tag::code2[]
                tree.selectionModel.selectionMode = SelectionMode.SINGLE
                top {
                    node new BreadCrumbBar(tree.root),
                            selectedCrumb: bind(tree.selectionModel, 'selectedItem'),
                            prefHeight: 30
                }
                bottom {
                    container new MigPane("wrap 2", "20[]5[fill, grow]20", ""), {
                        4.times {
                            button("Button $it")
                        }
                    }
                }
            }
        }
    }
}
// end::code2[]