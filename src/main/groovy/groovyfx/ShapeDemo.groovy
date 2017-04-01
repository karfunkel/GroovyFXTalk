package groovyfx

import groovy.transform.Field
import javafx.animation.Animation

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Shapes"

// tag::code1[]
start {
    stage(title: "GroovyFX Shape Demo", width: 400, height: 400, visible: true) {
        scene(fill: BLACK) {
            group(id: 'group') {
                rectangle x: 25, y: 25,
                        width: 150, height: 75,
                        arcWidth: 20, arcHeight: 20,
                        fill: GROOVYBLUE, stroke: ORANGE, strokeWidth: 2
                circle centerX: 150, centerY: 100,
                        radius: 20, fill: RED
                svgPath content: """\
            M248.91 50c11.882-.006 23.875 1.018 35.857 3.13
            85.207 15.025 152.077 81.895 167.102 167.102 15.023 85.208-24.944
            170.917-99.874 214.178-32.782 18.927-69.254 27.996-105.463
            27.553-46.555-.57-92.675-16.865-129.957-48.15l30.855-36.768c50.95
            42.75 122.968 49.05 180.566 15.797 57.597-33.254 88.152-98.777
            76.603-164.274-11.55-65.497-62.672-116.62-128.17-128.168-51.656-9.108-103.323
            7.98-139.17 43.862L185 192H57V64l46.34 46.342C141.758 71.962 194.17
            50.03 248.91 50z""",
                        translateX: -130, translateY: -200,
                        scaleX: 0.1, scaleY: 0.1,
                        fill: WHITE, stroke: WHITE, strokeWidth: 2
// ...
// end::code1[]
// tag::code2[]
polygon(id: 'triangle',
    points: [0, -10, 10, 10, -10, 10, 0, -10],
    translateX: 70, translateY: 60,
    scaleX: 2.0, scaleY: 2.0, fill: BLUE,
    onMousePressed: {
        if (rotation.status == Animation.Status.RUNNING)
            rotation.pause()
        else
            rotation.play()
    }) {
        rotation = rotateTransition 2.s, tween: LINEAR, to: -360, cycleCount: INDEFINITE
    }
parallelTransition(onFinished: { println "parallel done" }) {
    translateTransition 3.s, tween: EASE_OUT, to: 100,
            onFinished: { println "translate done" }
    scaleTransition 3.s, interpolator: EASE_IN, to: 2.0,
            onFinished: { println "scale done" }
}.playFromStart()
}   }   }   }
// end::code2[]