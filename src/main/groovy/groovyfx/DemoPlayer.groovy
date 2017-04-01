package groovyfx

import groovyx.javafx.beans.FXBindable
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.stage.Stage

import static groovyx.javafx.GroovyFX.start

@FXBindable
class Status {
    int pos
    Class cls
    String menuTitle
}

def classes = [
        HelloGroovyFX,
        Simple,
        SimpleForm,
        SimpleFormBinding,
        BeanBinding,
        BorderPane,
        GridLayout,
        ListDemo,
        TableDemo,
        Actions,
        ChartDemo,
        PathDemo,
        ShapeDemo,
        AnimatedBackground,
        CustomComponentsDemo,
        CustomFactoryDemo
]

File baseDir = new File(".").canonicalFile
while (baseDir.name != "GroovyFX") {
    baseDir = baseDir.parentFile
}

File buildDir = new File(baseDir, "build")

String javaHome = System.getProperty("java.home")
List classpath = [
        "/Users/alexanderklein/.gradle/caches/modules-2/files-2.1/org.codehaus.groovy/groovy-all/2.4.10/4660c69a6fb68c1507d09e3de01e56bc87ffd85d/groovy-all-2.4.10.jar",
        "/Users/alexanderklein/.gradle/caches/modules-2/files-2.1/org.groovyfx/groovyfx/8.0.0/9490a48a49e01be9c0ba73f51a4ea970600cfb93/groovyfx-8.0.0.jar",
        "/Users/alexanderklein/.gradle/caches/modules-2/files-2.1/org.controlsfx/controlsfx/8.40.12/68ea3c6a97296e77c843473dd74636b075b0b654/controlsfx-8.40.12.jar",
        "/Users/alexanderklein/.gradle/caches/modules-2/files-2.1/com.miglayout/miglayout-javafx/5.0/5f9cf895b72bc9581a83cf220eb022063f19edcd/miglayout-javafx-5.0.jar",
        "/Users/alexanderklein/.gradle/caches/modules-2/files-2.1/com.miglayout/miglayout-core/5.0/956daa1576bccb24e727ab75540b078171c7c432/miglayout-core-5.0.jar",
        "$buildDir/resources/main",
        "$buildDir/classes/main"
]

Platform.implicitExit = false

Status status = new Status(pos: 0, cls: classes.first(), menuTitle: classes.first().newInstance().menuTitle)

Closure open = { Stage primaryStage, Class cls, evt ->
    final Stage ps = primaryStage
    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            String cmd = "$javaHome/bin/java -classpath ${classpath.join(":")} ${cls.name}"
            def p = cmd.execute()
            p.err.eachLine { println it }
            p.in.eachLine { println it }
        }

        @Override
        protected void succeeded() {
            Platform.runLater {
                ps.show()
                ps.toFront()
            }
        }

        @Override
        protected void cancelled() {
            succeeded()
        }

        @Override
        protected void failed() {
            succeeded()
        }
    }
    Platform.runLater {
        ps.hide()
    }
    new Thread(task).start()
}

start {
    stage(title: 'Demo Player', visible: true) {
        primaryStage.onCloseRequest = { evt ->
            System.exit(0)
        }
        scene {
            stylesheets('groovyfx.css')
            actions {
                classes.each { cls ->
                    fxaction(id: "${cls.simpleName}Action",
                            name: cls.newInstance().menuTitle,
                            onAction: open.curry(primaryStage, cls)
                    )
                }
                fxaction(id: 'exitAction',
                        name: 'Exit',
                        onAction: { primaryStage.close() }
                )
            }

            borderPane {
                top {
                    menuBar {
                        menu("Demos") {
                            classes.each { cls ->
                                menuItem(delegate."${cls.simpleName}Action")
                            }
                            separatorMenuItem()
                            menuItem(exitAction)
                        }
                    }
                }
                center {
                    vbox {
                        label(text: bind(status, 'menuTitle'))
                        hbox(spacing: 20, padding: 10) {
                            button("",
                                    disable: bind(status, "pos").using { status.pos <= 0 },
                                    onAction: {
                                        status.pos--
                                        status.cls = classes[status.pos]
                                        status.menuTitle = status.cls.newInstance().menuTitle
                                    }
                            ) {
                                graphic imageView('/icons/prev.png')
                            }
                            button("", onAction: { open(primaryStage, status.cls, null) }) {
                                graphic imageView('/icons/play.png')
                            }
                            button("",
                                    disable: bind(status, "pos").using { status.pos >= classes.size() - 1 },
                                    onAction: {
                                        status.pos++
                                        status.cls = classes[status.pos]
                                        status.menuTitle = status.cls.newInstance().menuTitle
                                    }
                            ) {
                                graphic imageView('/icons/next.png')
                            }
                        }
                    }
                }
            }
        }
    }
}