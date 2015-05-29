webkipedia
==========

An experiment to make a native web application for wikipedia.

Visit [chimeces.com/webkipedia](https://chimeces.com/webkipedia) for a demo.

Development
-----------

Dev requirements:
* Java 8
* Leiningen

```
lein figwheel # Start the compilation, REPL, and live updates
# In another terminal
lein less auto # Start compilation of CSS
```

Then visit `localhost:3449` for the live reloading version. Any changes to CLJS
or LESS will be incrementally added to the browser.

### Deployment

```
lein cljsbuild once min # Start the advanced compilation
lein less once # Start compilation of CSS
```

Assets are in `resources/public`

Coding
------

* App is built with Clojurescript, Reagent and Less css.
* Components
  * UI components can be found at `src/webkipedia/ui/`.
  * LESS styles can be found in `src/webkipedia/ui/less/`.
  * State of the app is in `src/webkipedia/state.cljs`.
  * Try to make components reusable and simple whenever possible.
* Styles
  * Use the [SUIT CSS naming conventions](https://github.com/suitcss/suit/blob/master/doc/naming-conventions.md)
  * Styles are placed mirroring name of file and folder structure of the
    component they belong to.
  * Use the mixins and variables in
    `src/webkipedia/ui/less/{mixins,variables}.less for responsive behavior
    (mobile first) and color/spacing variables.
  * Do not use nested Less. Be 100% concrete on the selectors. Have a look at
    the existing LESS to get the idea.

