import React from 'react'
import {Link} from 'react-router'
import component from 'omniscient'

let {a} = React.DOM

export default component('ButtonLink', function (props) {
  props.className = (props.className || '') + ' ButtonLink button'
  return a(props)
})
