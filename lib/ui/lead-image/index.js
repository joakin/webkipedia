import React from 'react'
import component from 'omniscient'

import './lead-image.less'

let {div} = React.DOM

export default component('LeadImage', function (thumb) {
  return div({
    className: 'LeadImage',
    style: { backgroundImage: `url(${thumb.get('url')})` }
  })
})

