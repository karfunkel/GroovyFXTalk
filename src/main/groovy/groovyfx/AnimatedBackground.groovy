package groovyfx

import groovy.transform.Field
import javafx.animation.Interpolator
import javafx.animation.PathTransition
import javafx.scene.paint.RadialGradient

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Animated Background"

// tag::code1[]
start {
    stage(title: 'Animation Demo', visible: true) {
        scene {
            rectangle(width: 800, height: 600,
                    effect: blend(mode: "screen") {
                        topInput {
                            imageInput(source: "background-ripples.png", x: 0, y: 0)
                        }
                        bottomInput {
                            color = colorInput(
                                    paint: radialGradient(
                                            center: [0.7, 0.05],
                                            radius: 0.6,
                                            stops: ["#767A7B", "#222222"]
                                    ).build(),
                                    x: 0, y: 0,
                                    width: 800, height: 600
                            )
                        }
                    }
            )
// ...
// end::code1[]
// tag::code2[]
            circle id: 'circle', radius: 60,
                    fill: radialGradient(
                            center: [0.5, 0.5],
                            radius: 0.7,
                            stops: [ORANGE, DARKORANGE]
                    ),
                    effect: glow(level: 0.5)
            noparent {
                path(id: 'thePath', translateX: 50, translateY: 5) {
                    moveTo(x: 100, y: 100)
                    arcTo(x: 300, y: 100, radiusX: 5, radiusY: 10)
                    lineTo(x: 600, y: 200)
                    lineTo(x: 300, y: 500)
                    arcTo(x: 150, y: 200, radiusX: 50, radiusY: 100)
                    closePath()
    }   }   }   }
    pathTransition(10.s,
            delay: 100.ms,
            node: circle,
            path: thePath,
            orientation: PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT
    ).play()
// ...
// end::code2[]
// tag::code3[]
    timeline(cycleCount: -1, autoReverse: true) {
        at(4.s) {
            change(color, "paint") to(x: 0.3, y: 0.95) tween new CenterInterpolator()
            onFinished { println "4 seconds elapsed" }
        }
    }.play()
}
class CenterInterpolator extends Interpolator {
    @Override
    Object interpolate(Object startValue, Object endValue, double fraction) {
        RadialGradient s = startValue
        return new RadialGradient(
                s.focusAngle,
                s.focusDistance,
                EASE_BOTH.interpolate(s.centerX, endValue.x, fraction),
                EASE_BOTH.interpolate(s.centerY, endValue.y, fraction),
                s.radius,
                s.proportional,
                s.cycleMethod,
                s.stops)
    }
    @Override protected double curve(double t) { return 0 }
}
// end::code3[]
