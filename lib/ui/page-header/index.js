import React from 'react'
import component from 'omniscient'

import './page-header.less'

let {div, h1, h6} = React.DOM

export default component('LeadImage', function ({title, description}) {
  return div({ className: 'PageHeader' },
    h1({ className: 'PageHeader-title' }, (title || '').replace(/_/g, ' ')),
    h6({ className: 'PageHeader-description' }, description || '')
  )
})

