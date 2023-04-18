const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const Dotenv = require('dotenv-webpack');

// Get the name of the appropriate environment variable (`.env`) file for this build/run of the app
const dotenvFile = process.env.API_LOCATION ? `.env.${process.env.API_LOCATION}` : '.env';

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new Dotenv({ path: dotenvFile }),

  ],
  optimization: {
    usedExports: true
  },
  entry: {
    createPlaylist: path.resolve(__dirname, 'src', 'pages', 'createPlaylist.js'),
    viewPlaylist: path.resolve(__dirname, 'src', 'pages', 'viewPlaylist.js'),
    searchPlaylists: path.resolve(__dirname, 'src', 'pages', 'searchPlaylists.js'),

    createItinerary: path.resolve(__dirname, 'src','pages', 'createItinerary.js'),
    //[webpack-cli] Failed to load '/Users/eringeier/workspace/midstone-project-team5/u5-project-group-5/web/webpack.config.js' config
    //  [webpack-cli] TypeError [ERR_INVALID_ARG_TYPE]: The "path" argument must be of type string. Received type number (NaN)
//The following lines produce this error when running webpack
 //   searchItineraries: path.resolve(--__dirname, 'src', 'pages','searchItineraries.js'),
 //   viewItinerary: path.resolve(__dirname, 'src','pages','viewItinerary.js'),
 //The following lines work fine for running npm run run-local:
     viewItinerary: path.resolve(__dirname, 'src', 'pages', 'viewItinerary.js'),
     searchItineraries: path.resolve(__dirname, 'src', 'pages', 'searchItineraries.js'),
     createActivity: path.resolve(__dirname, 'src','pages', 'createActivity.js'),
     getItineraryActivities: path.resolve(__dirname, 'src', 'pages', 'getItineraryActivities.js'),
    // viewItineraryActivities: path.resolve(__dirname, 'src','pages','viewItineraryActivities.js'),
     viewActivitiesItinerary: path.resolve(__dirname, 'src','pages','viewActivitiesItinerary.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
