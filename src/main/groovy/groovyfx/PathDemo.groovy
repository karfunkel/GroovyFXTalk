package groovyfx

import groovy.transform.Field
import javafx.scene.shape.*

import static groovyx.javafx.GroovyFX.start

@Field String menuTitle = "Paths"

// tag::start[]
start {
    stage(title: "GroovyFX Path Demo", visible: true) {
        scene(fill: BLACK) {
            path(translateX: 0, translateY: 493.672 + 10, fill: WHITE, stroke: GREY,
                    strokeWidth: 1,
                    strokeLineCap: StrokeLineCap.BUTT,
                    strokeLineJoin: StrokeLineJoin.MITER,
                    strokeMiterLimit: 4.00000000) {
                moveTo(x: 105.367, y: -493.672)
                cubicCurveTo(
                        controlX1: 128.507, controlY1: -478.22,
                        controlX2: 151.465, controlY2: -462.40,
                        x: 173.917, y: -446.100
                )
                cubicCurveTo(
                        controlX1: 128.862, controlY1: -466.995,
                        controlX2: 79.407, controlY2: -482.018,
                        x: 24.547, y: -490.346
                )
// ...
// end::start[]
// tag::code1[]
    cubicCurveTo(controlX1: 71.244, controlY1: -463.626, controlX2: 116.143, controlY2: -434.766,
            x: 160.252, y: -404.822)
    cubicCurveTo(controlX1: 123.049, controlY1: -422.855, controlX2: 82.772, controlY2: -437.042,
            x: 38.650, y: -446.192)
    cubicCurveTo(controlX1: 96.868, controlY1: -411.870, controlX2: 148.018, controlY2: -373.727,
            x: 193.360, y: -331.986)
    cubicCurveTo(controlX1: 136.020, controlY1: -284.773, controlX2: 86.295, controlY2: -227.283,
            x: 45.790, y: -157.820)
    cubicCurveTo(controlX1: 72.900, controlY1: -182.110, controlX2: 100.700, controlY2: -205.365,
            x: 128.658, y: -228.500)
    cubicCurveTo(controlX1: 81.942, controlY1: -172.640, controlX2: 45.050, controlY2: -106.990,
            x: 20.200, y: -29.865)
    cubicCurveTo(controlX1: 40.560, controlY1: -54.485, controlX2: 61.188, controlY2: -78.068,
            x: 82.105, y: -100.682)
    cubicCurveTo(controlX1: 126.805, controlY1: -168.167, controlX2: 171.672, controlY2: -247.792,
            x: 230.961, y: -271.100)
    cubicCurveTo(controlX1: 201.351, controlY1: -240.392, controlX2: 167.601, controlY2: -195.936,
            x: 132.711, y: -152.955)
    cubicCurveTo(controlX1: 173.701, controlY1: -193.392, controlX2: 215.801, controlY2: -230.415,
            x: 259.126, y: -264.467)
    cubicCurveTo(controlX1: 320.724, controlY1: -193.977, controlX2: 369.883, controlY2: -115.087,
            x: 411.271, y: -28.594)
    cubicCurveTo(controlX1: 404.533, controlY1: -73.388, controlX2: 394.475, controlY2: -115.978,
            x: 381.241, y: -156.260)
    lineTo(x: 427.685, y: -90.730)
// ...
// end::code1[]
// tag::code2[]
    cubicCurveTo(controlX1: 427.685, controlY1: -90.730, controlX2: 401.648, controlY2: -163.420,
            x: 384.025, y: -192.717)
    cubicCurveTo(controlX1: 424.785, controlY1: -136.807, controlX2: 462.233, controlY2: -78.289,
            x: 496.353, y: -17.512)
    cubicCurveTo(controlX1: 477.679, controlY1: -106.966, controlX2: 445.841, controlY2: -187.284,
            x: 397.460, y: -255.736)
    cubicCurveTo(controlX1: 432.366, controlY1: -221.046, controlX2: 466.097, controlY2: -184.636,
            x: 498.390, y: -146.691)
    cubicCurveTo(controlX1: 465.048, controlY1: -223.173, controlX2: 423.580, controlY2: -290.180,
            x: 372.214, y: -345.000)
    cubicCurveTo(controlX1: 412.438, controlY1: -370.887, controlX2: 453.694, controlY2: -394.730,
            x: 496.077, y: -416.783)
    cubicCurveTo(controlX1: 464.052, controlY1: -411.223, controlX2: 433.587, controlY2: -403.863,
            x: 404.071, y: -394.849)
    cubicCurveTo(controlX1: 425.907, controlY1: -411.022, controlX2: 448.481, controlY2: -426.973,
            x: 471.095, y: -442.372)
    cubicCurveTo(controlX1: 433.108, controlY1: -430.462, controlX2: 396.462, controlY2: -416.597,
            x: 362.028, y: -400.939)
    cubicCurveTo(controlX1: 404.696, controlY1: -428.612, controlX2: 448.348, controlY2: -454.607,
            x: 493.032, y: -479.541)
    lineTo(x: 493.029, y: -479.541)
    cubicCurveTo(controlX1: 425.559, controlY1: -461.486, controlX2: 362.199, controlY2: -437.351,
            x: 304.031, y: -405.993)
    cubicCurveTo(controlX1: 247.737, controlY1: -447.783, controlX2: 182.021, controlY2: -477.780,
            x: 105.368, y: -493.673)
    lineTo(x: 105.367, y: -493.672)
    closePath()
}   }   }   }
// end::code2[]