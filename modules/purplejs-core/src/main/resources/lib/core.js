/* global exports */

/**
 * Core javascript functions.
 *
 * @module core
 */

// Create a new instance of helper and get hold of a resource-loader.
var helper = __.newBean('io.purplejs.core.internal.lib.CoreLibHelper');
var resourceLoader = __.environment.resourceLoader;

/**
 * Read text from a stream.
 *
 * @param stream Stream to read text from.
 * @returns {string} Returns the text read from stream.
 */
exports.readText = function (stream) {
    return helper.readText(stream);
};

/**
 * Read lines from a stream.
 *
 * @param stream Stream to read lines from.
 * @returns {string[]} Returns lines as an array.
 */
exports.readLines = function (stream) {
    return __.toNativeObject(helper.readLines(stream));
};

/**
 * Process lines from a stream.
 *
 * @param stream Stream to read lines from.
 * @param {function} func Callback function to be called for each line.
 */
exports.processLines = function (stream, func) {
    helper.processLines(stream, func);
};

/**
 * Returns the size of a stream.
 *
 * @param stream Stream to get size of.
 * @returns {number} Returns the size of a stream.
 */
exports.getStreamSize = function (stream) {
    return helper.getStreamSize(stream);
};

/**
 * Returns a new stream from a string.
 *
 * @param {string} text String to create a stream of.
 * @returns {*} A new stream.
 */
exports.newStream = function (text) {
    return helper.newStream(text);
};

/**
 * Loads a resource.
 *
 * @param {*} path Resource path to load.
 * @returns {*} A stream for the resource or undefined.
 */
exports.loadResource = function (path) {
    return helper.loadResource(resourceLoader, path);
};
