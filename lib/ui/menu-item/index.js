import React from 'react'
import component from 'omniscient'
import extend from 'xtend'

import './menu-item.less'

let {li, a} = React.DOM

export default component('Menu', function (item) {
  let defaults = {}
  let attrs = extend(item.get('attrs').toJS(), defaults)
  return li({ className: 'MenuItem' },
    a(
      attrs,
      item.get('label', '')
    ))
})
