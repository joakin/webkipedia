import React from 'react'
import component from 'omniscient'
import LeadImage from '../lead-image'
import PageHeader from '../page-header'

import './page.less'

let {div, h6} = React.DOM

export default component('Page', function (cursor) {
  let page = cursor.getIn(['page', 'content'], null)
  let loading = !page ? h6(null, 'Loading!') : null
  let lead = page && page.get('thumb') ? LeadImage(page.get('thumb')) : null
  return div({ className: 'Page' },
    lead,
    PageHeader({
      title: cursor.getIn(['page', 'title']),
      description: page && page.get('description')
    }),
    loading)
})
