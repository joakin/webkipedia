var path = require('path')
var webpack = require('webpack')
var ExtractTextPlugin = require('extract-text-webpack-plugin')
var out = 'public'
var node_modules = path.resolve(__dirname, 'node_modules')
var pathToReact = path.resolve(node_modules, 'react/dist/react.min.js')

module.exports = {
  entry: {
    app: [
      'webpack-dev-server/client?http://0.0.0.0:3000',
      'webpack/hot/only-dev-server',
      './lib/index.js'
    ]
  },
  resolve: {
    alias: {
      'react': pathToReact
    }
  },
  output: {
    path: path.join(__dirname, out),
    filename: '[name].js'
  },
  module: {
    noParse: [pathToReact],
    loaders: [
      { test: /\.(gif|png|jpg)$/, loader: 'url?limit=25000' },
      { test: /\.less$/, loader: ExtractTextPlugin.extract('style', 'css!autoprefixer!less') },
      { test: /\.js$/, exclude: /node_modules/, loader: 'babel-loader' }
    ]
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin(),
    new ExtractTextPlugin('style.css', { allChunks: true }),
    new webpack.optimize.DedupePlugin()
  ],
  devServer: {
    port: 3000,
    contentBase: out,
    noInfo: true,
    inline: true
  }
}
