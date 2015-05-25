import React from 'react'
import component from 'omniscient'
import LeadImage from '../lead-image'
import PageHeader from '../page-header'
import RelatedPages from '../related-pages'
import Loading from '../loading'
import {Map} from 'immutable'

import './page.less'

let {div} = React.DOM

export default component('Page', function (cursor) {
  let page = cursor.getIn(['page', 'content'], null)
  let loading = !page ? Loading() : null
  let thumb = page && page.get('thumbnail')
  let lead = thumb ? LeadImage(thumb) : null
  return div({ className: 'Page' },
    lead,
    PageHeader({
      title: cursor.getIn(['page', 'title']),
      description: page && page.get('description')
    }),
    div({
      className: 'Page-content',
      dangerouslySetInnerHTML: { __html: (page && page.get('extract')) || '' }
    }),
    loading,
    RelatedPages(cursor.getIn(['page', 'related']) || Map()))
})
