Project template for reveal.js slides created using AsciiDoc the codecentric-style
----------------------------------------------------------------------------------

The example-manual.adoc is an example file of how to create our documents.
See https://github.com/asciidoctor/asciidoctor-reveal.js for more details.

You have to change your build.gradle file to point to your documents to create:

    asciidoctor {
        sources {
            include 'example-manual.adoc'
        }
    }




