import React from 'react'
import component from 'omniscient'

import './loading.less'

let {div} = React.DOM

export default component('Loading', function () {
  return div({ className: 'Loading' }, null)
})
