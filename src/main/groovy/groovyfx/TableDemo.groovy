package groovyfx

import groovy.transform.Field
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.scene.control.TableCell
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Table"

// tag::colors[]
start { primaryStage ->
    def colors = [
            BLUE,
            GREEN,
            RED,
            hsb(67, 0.8, 0.91),
            rgb(39, 209, 100),
            rgba(20, 100, 150, 0.60),
            delegate."#AA4411"
    ]
    stage(title: "GroovyFX Table Demo", visible: true) {
        scene(fill: WHITE) {
            vbox(padding: 9, spacing: 5) {
//...
// end::colors[]
// tag::code[]
    tableView(selectionMode: "single", cellSelectionEnabled: true, items: colors) {
        tableColumn text: "Color", prefWidth: 50,
                cellValueFactory: { new ReadOnlyObjectWrapper(it.value) },
                cellFactory: { column ->
                    Rectangle rect = rectangle(width: 40, height: 20)
                    new TableCell<Color, Color>() {
                        void updateItem(Color color, boolean empty) {
                            rect.fill = empty ? Color.TRANSPARENT : color
                            setGraphic(rect)
                }   }   }
        tableColumn text: "Web", prefWidth: 80,
                cellValueFactory: {
                    Color color = it.value
                    int r = Math.round(color.red * 255.0)
                    int g = Math.round(color.green * 255.0)
                    int b = Math.round(color.blue * 255.0)
                    new ReadOnlyObjectWrapper(String.format("#%02X%02X%02X", r, g, b))
                }
        tableColumn(property: "opacity", text: "Opacity", prefWidth: 70,
                converter: { from -> "${Math.round(from * 100)}%" })
        tableColumn(property: "hue", text: "Hue", prefWidth: 120)
        tableColumn(property: "brightness", text: "Brightness", prefWidth: 120)
        tableColumn(property: "saturation", text: "Saturation", prefWidth: 120)
    }   }   }   }   }
// end::code[]