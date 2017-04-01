package groovyfx

import groovy.transform.Field

@Field String menuTitle = "Bean Binding"

// tag::code[]
import java.time.LocalDate
import groovyx.javafx.beans.FXBindable

@FXBindable
class Person {
    String name
    LocalDate birth
}

def person = new Person(name: 'Sascha', birth: new LocalDate(1975, 4, 1))
groovyx.javafx.GroovyFX.start {
    stage(title: 'Bean binding', visible: true) {
        scene {
            stylesheets('groovyfx.css')
            vbox {
                label 'Name'
                textField(text: bind(person, 'name'))
                label 'Birthday'
                datePicker(value: bind(person.birth()))
                label text: bind { person.birth && person.name }.using {
                    "$person.name is ${LocalDate.now().year - person.birth.year} years old"
}   }   }   }   }
// end::code[]