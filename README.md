
webkipedia
==========

An experiment to make a native web application for wikipedia.

```
npm install
npm start
```

Then visit `localhost:8080` or `localhost:8080/webpack-dev-server` for the live
reloading version.

`npm run build` will build the site for deploying in `public`

Development
-----------

* App is built with ES6, React.js and Less css.
* Components
  * UI components can be found at `lib/ui/`.
  * Inside a component folder you will find a `index.js` and
    a `component-name.less` with the styles of the component.
  * There are two types of components: Pure views, and Controller views.
    * Pure views receive props, and render. Nothing else. Try to avoid state in
      these ones if you can.
    * Controller views are the views that you see referenced on the router.
      These views have the responsability of specifying which data is needed to
      be fetched and massaging data for passing down to the other components.
* Styles
  * Use the [SUIT CSS naming conventions](https://github.com/suitcss/suit/blob/master/doc/naming-conventions.md)
  * Styles are placed alongside the component they belong to.
  * Use the mixins in the Webkipedia component for responsive behavior (mobile first).
  * Do not use nested Less. Be 100% concrete on the selectors.

