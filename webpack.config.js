var path = require('path')
var webpack = require('webpack')
var out = 'public'

module.exports = {
  entry: {
    app: './lib/index.js',
    vendors: ['react']
  },
  output: {
    path: path.join(__dirname, out),
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      { test: /\.(png|jpg)$/, loader: 'url?limit=25000' },
      { test: /\.less$/, loader: 'style!css!less!autoprefixer' },
      { test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader' }
    ]
  },
  plugins: [
    new webpack.optimize.CommonsChunkPlugin('vendors', 'vendor.js')
  ],
  devServer: {
    contentBase: out
  }
}
