import React from 'react'
import {Link} from 'react-router'
import component from 'omniscient'

import './logo.less'

let {a} = React.DOM

export default component('Logo', function (cursor) {
  return a({ className: 'Logo', href: '#/' }, 'Wikipedia')
})
