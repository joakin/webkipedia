import immstruct from 'immstruct'

let state = immstruct({
  route: '',
  searchQuery: null,
  searchResults: {query: null, results: []},
  page: {
    title: null,
    content: null
  }
})
export default state
