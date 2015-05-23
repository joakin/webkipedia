import React from 'react'
import component from 'omniscient'

import './menu-item.less'

let {li, a} = React.DOM

export default component('Menu', function (item) {
  return li({ className: 'MenuItem' },
    a(item.get('attrs').toJS(), item.get('label', '')))
})
