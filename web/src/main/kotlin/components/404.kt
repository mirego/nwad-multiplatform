package components

import react.Fragment
import react.PropsWithChildren
import react.dom.div
import react.functionComponent

@ExperimentalJsExport
val Error404NotFound = functionComponent<PropsWithChildren> {
    Fragment {
        div(classes = "container mx-auto p-4 text-gray-500") {
            div(classes = "mb-3 text-3xl font-semibold") {
                +"Page not found"
            }
            div(classes = "text-sm") {
                +"We could not find the page you were looking for."
            }
        }
    }
}
