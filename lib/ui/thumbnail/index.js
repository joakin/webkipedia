import React from 'react'
import component from 'omniscient'

import './thumbnail.less'

let {div} = React.DOM

export default component('Thumbnail', function ({thumbnail}) {
  let styles = thumbnail ? { backgroundImage: `url(${thumbnail.get('source')})` } : {}
  return div({
    className: 'Thumbnail' + (thumbnail ? '' : ' empty'),
    style: styles
  }, null)
})
